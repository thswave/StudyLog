# message pack 적용기

> third party libraray object 를 기존 json으로 처리/저장하던 방식에서 message pack으로 변경하기로 함
 (json 에 invalid character 가 있는 경우 json 변환 시 에러 발생하는데 원문 데이터를 손상/변형하지 않기 위해 바이너리 형태로 저장되는 msgpack 선택)

* 시도 1.
@Message 어노테이션
third party object에 @Message annotation 붙인 후 저장 시도 -> 실패

Cannot find template for class com.dooray.smtp.mime.DoorayMimeMessage class.  Try to add @Message annotation to the class or call MessagePack.register(Type).

* 시도 2.
에러 메세지에 나온 대로 MessagePack.register(Type.class)  등록 후 시도 -> 실패

* 시도 3.
third-party object라서 @Message 어노테이션이 적용되지 않는 것인가 싶어 extends 로 상속 후 시도 -> 실패
(ClassCastException. 이유는 아직 찾지 못함)

* 시도 4.
AbstractTemplate<T> 상속 후 write/read 메서드 상속 -> 실패
StackOverFlow 발생. 각 필드를 일일이 write/read 안에서 작성해 줘야 함(굉장히 번거러움 = 빠른 포기)

- @Message 어노테이션은 nested object,map,list 지원하지 않음.

* 시도 5.
ObjectOutputWriter/Reader -> 실패
OOW Serialized Object -> byte[] -> msgpack write -> msgpack read -> byte[] -> OOR  과정이 자연스럽지 않음.

```
ByteArrayOutputStream bos = new ByteArrayOutputStream();
ObjectOutputStream oos = new ObjectOutputStream(bos);
oos.writeObject(message);

ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
ObjectInput in = new ObjectInputStream(bis);
TT o =(TT) in.readObject();
System.out.println(o.subject);

oos.close();
bos.close();
```

* 시도 6.

jackson-dataformat-msgpack, msgpack-core 의존성 추가 -> 성공

ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());
byte[] bytes = objectMapper.writeValueAsBytes(message);
Object obj = objectMapper.readValue(bytes, new TypeReference<XXX>() {});

* spring message pack converter 등록

- ContentNegotiationManager 에 Converter 등록
- Jackson2ObjectMapperFactoryBean (ObjectMapper에 MessagePackFactory 생성자 등록)

```
<mvc:annotation-driven conversion-service="typeConversionService" content-negotiation-manager="contentNegotiationManager">
    <mvc:path-matching suffix-pattern="false" />
    <mvc:message-converters>
        <!-- REST API 설정 -->
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper" ref="objectMapper" />
            <property name="supportedMediaTypes">
                <value>application/json;charset=UTF-8</value>
            </property>
        </bean>

        <!-- msgpack mapper -->
        <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
            <property name="objectMapper" ref="msgpackMapper"/>
            <property name="supportedMediaTypes">
                <value>application/x-msgpack;charset=UTF-8</value>
            </property>
        </bean>
    </mvc:message-converters>
    <mvc:argument-resolvers>
        <bean class="org.springframework.data.web.PageableHandlerMethodArgumentResolver" >
            <property name="maxPageSize" value="#{T(java.lang.Integer).MAX_VALUE}" />
        </bean>
    </mvc:argument-resolvers>
</mvc:annotation-driven>

<bean id="msgpackMapper" class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
    <property name="objectMapper" >
        <bean class="com.fasterxml.jackson.databind.ObjectMapper">
            <constructor-arg name="jf">
                <bean class="org.msgpack.jackson.dataformat.MessagePackFactory"/>
            </constructor-arg>
        </bean>
    </property>
    <property name="modulesToInstall" value="com.fasterxml.jackson.datatype.jdk8.Jdk8Module" />
</bean>
```

### Controller

```
@RequestMapping(value = “/url", consumes = "application/x-msgpack")
parameter : @RequestBody Model model
```
