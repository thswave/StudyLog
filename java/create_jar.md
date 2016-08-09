@ create jar with dependencies

참고:

[http://www.mkyong.com/maven/how-to-create-a-jar-file-with-maven/](http://www.mkyong.com/maven/how-to-create-a-jar-file-with-maven/)
[http://stove99.tistory.com/79](http://stove99.tistory.com/79)

간단한 Java 프로그램을 만들어 jar파일로 만들고 싶을 때, 외부 라이브러리 파일이 포함된 상태에서 jar 파일을 만드는 방법입니다.

1.라이브러리 jar에 대한 path 정보만 MANIFEST.MF 파일에 명시하는 방법

이 방법은 packaging(jar파일 생성 시점)의 라이브러리 경로를 MANIFEST 에 명시하기 때문에 생성 후 다른 서버에서 실행하고자 하는 경우 문제가 발생

* pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.nhnent.test</groupId>
  <artifactId>tape-test</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <dependencies>
    <dependency>
      <groupId>com.squareup</groupId>
      <artifactId>tape</artifactId>
      <version>1.2.3</version>
    </dependency>
    <dependency>
      <groupId>org.msgpack</groupId>
      <artifactId>msgpack</artifactId>
      <version>0.6.11</version>
    </dependency>
    <dependency>
      <groupId>org.msgpack</groupId>
      <artifactId>msgpack-core</artifactId>
      <version>0.8.7</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>tape-test</finalName>
    <plugins>
      <plugin>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.3</version>
        <configuration>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.6</version>
        <configuration>
          <finalName>tape-test</finalName>
          <archive>
            <manifest>
              <mainClass>com.nhnent.test.TapeTest</mainClass>
              <addClasspath>true</addClasspath>
              <classpathPrefix>${project.build.directory}/libs/</classpathPrefix>
            </manifest>
          </archive>
          <useDefaultManifestFile>true</useDefaultManifestFile>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-dependency-plugin</artifactId>
        <version>2.5.1</version>
        <executions>
          <execution>
            <id>copy-dependencies</id>
            <phase>package</phase>
            <goals>
              <goal>copy-dependencies</goal>
            </goals>
            <configuration>
              <includeScope>runtime</includeScope>
              <outputDirectory>${project.build.directory}/libs/</outputDirectory>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
</project>
```


mvn package 실행하면 target 디렉토리에 jar 파일이 생긴다.

jar를 열어 META-INF/MANIFEST.MF에 다음과 같은 정보를 확인할 수 있다.

```
jar xvf <jar>
jar tf <jar>
```

```
Manifest-Version: 1.0
Archiver-Version: Plexus Archiver
Built-By: nhnent
Class-Path: /Users/nhnent/git/study/tape-test/target/libs/tape-1.2.3.j
 ar /Users/nhnent/git/study/tape-test/target/libs/msgpack-0.6.11.jar /
 Users/nhnent/git/study/tape-test/target/libs/json-simple-1.1.1.jar /U
 sers/nhnent/git/study/tape-test/target/libs/javassist-3.18.1-GA.jar /
 Users/nhnent/git/study/tape-test/target/libs/msgpack-core-0.8.7.jar
Created-By: Apache Maven 3.2.2
Build-Jdk: 1.8.0_20
Main-Class: com.nhnent.test.TapeTest
```

2. 외부라이브러리를 포함하는 jar 만들기

참고
[http://www.mkyong.com/maven/maven-create-a-fat-jar-file-one-jar-example/](http://www.mkyong.com/maven/maven-create-a-fat-jar-file-one-jar-example/)

one-jar 플러그인 추가


```
<plugin>
    <groupId>org.dstovall</groupId>
    <artifactId>onejar-maven-plugin</artifactId>
    <version>1.4.4</version>
    <executions>
        <execution>
            <goals>
                <goal>one-jar</goal>
            </goals>
        </execution>
    </executions>
</plugin>

<pluginRepositories>
    <pluginRepository>
        <id>onejar-maven-plugin.googlecode.com</id>
        <url>http://onejar-maven-plugin.googlecode.com/svn/mavenrepo</url>
    </pluginRepository>
</pluginRepositories>
```

```
mvn package

```

실행 시  <>.one-jar.jar 파일 생성되어 jar에서 외부 라이브러리를 포함하는 형태의 jar 파일 생성됨
