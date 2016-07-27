# 자바 성능 튜닝 책 내용

2장. 성능 테스트 접근법

실제 애플리케이션을 테스트 하자

원칙1. 제품이 실제로 사용되는 방식으로 진짜 제품을 테스트해야 한다.

Test를 위한 벤치마크

1.마이크로벤치마크
  잘 구성하기 힘들다. 실제 코드와는 다른 최적화가 되기 쉽고 정밀하게 만들기 어렵다.
  스레드 마이크로벤치마크
    싱글 스레드일 때조차 volatile 변수를 사용할 필요가 있다.(volatile: 간단히 말하면 변수를 읽을 때 cpu 캐시를 무시하고 항상 메모리에서부터 읽어들인다)
  마이크로벤치마크에는 관련 없는 동작을 포함해서는 안 된다.
  마이크로벤치마크는 정확한 입력 값을 측정해야 한다.

2.매크로벤치마크
  전체 서비스를 구성하는 외부 자원을 포함한 애플리케이션 테스트. 실제 환경과 동일하게 맞춰야 한다.(DB, Network 등)
  로직 처리부분이 100 RPS, 데이터 베이스를 활용한 데이터 로드가 100 RPS라면 로직 처리 부분을 200 RPS 처리할 수 있도록 개선해도 데이터베이스 RPS의 한계로 성능 향상이 되지 않는다.
  여러 개의 JVM으로 테스트할 경우 다른 JVM의 영향으로 실제 CPU 다른 작업을 처리하는데 쓸 수 있어 성능 측정에 혼란을 줄 수 있다.

3.메조벤치마크
  전체 서비스 중 일부 기능에 대한 테스트(완전한 형태를 갖추지 않는 애플리케이션을 벤치마크)
  ex> 애플리케이션 서버에서 JSP로 응답이 오는 속도 측정

빠르게 자주 테스트하자
개발 주기 중 성능 테스트를 필수적인 요소로 하길 권장(현실에서 적용하기 어렵다. 개발 일정을 맞추기 바쁘기 때문에..)

해결/보안책
전부 자동화 한다. -> 모든 성능 테스트는 스크립트로 만들어야 한다.
전부 측정한다. -> 자동화 할 때 이후 분석에 유용할 데이터에 대한 수집 가능한 요소를 전부 모아야 한다.(CPU, network, 메모리 등)
대상 시스템에서 실행한다 -> 개인 노트북과 실제 환경과 다르기 때문에 최대한 비슷한 환경에서 테스트해야 의미가 있다.

시스템 내 어딘가에 있을 병목 구간에 공수를 들이면 성능에 대한 효과가 나타날 것
테스트를 하기 전에 JVM이 최적화 할 수 있는 준비기간이 필요하다.(실제 서비스와 유사한 상황에서의 성능을 측정하기 위함)

처리율 측정에는 TPS, RPS, OPS 등이 있다.

응답시간 테스트와 처리율 테스트의 차이
응답시간 테스트: 클라이언트 스레드가 동작 사이 일정 기간동안 대기(think time)
사고 시간이 테스트에 도입되면 처리율이 고정된다.

변경사항에 대해 수행하는 코드 테스트를 회귀 테스트

멀티 스래드, CPU 상황에서도 프로그램에서 처리할 수 있는 가용 스래드가 없으면 CPU 유휴 상태가 될 수 있다.

3장. 자바 성능 도구 상자

JDK에서 제공하는 도구 + 시스템 측정 도구

운영체제 도구와 분석
vmstat, iostat, prstat

CPU 사용률
시스템 시간은 CPU가 커널 코드를 실행시키는 시간의 백분율(ex> I/O작업)

성능 최적화를 한다는것 CPU 사용율을 낮춘다는 의미가 아니고 최적화를 통해 단위 시간당 더 많은 작업을 수행하게 되므로 CPU 사용율이 더 높아지고 idle이 낮아짐을 의미한다.

CPU 런 큐

Java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version

-XX:+UnlockDiagnosticVMOptions
-XX:+PrintFlagsFinal

참고:
http://q-redux.blogspot.kr/2011/01/inspecting-hotspot-jvm-options.html

1.Datatype. bool for booleans, uintx for unsigned integers etc
2.Name
3.Assignment. = means this is the default. := means this option was modified from the default value by command line or ergonomics.
4.Value
5.Category. There are two large categories diagnostic or product. (I find this a better grouping than Behavioural, Performance and Debugging.) and also manageable if this option can be set via JMX. C1 relates to the JIT compiler used in the Client JVM. C2 means the same but for the Server JVM and I suspect pd means Platform Dependent as most pd options are Solaris only.

    uintx HeapSizePerGCThread                       = 87241520                            {product}
     bool IgnoreUnrecognizedVMOptions               = false                               {product}
    uintx IncreaseFirstTierCompileThresholdAt       = 50                                  {product}
     bool IncrementalInline                         = true                                {C2 product}
    uintx InitialBootClassLoaderMetaspaceSize       = 4194304                             {product}

java -XX:+UnlockDiagnosticVMOptions -XX:+PrintCompilation -XX:+PrintAssembly Test >output.txt

https://blog.jooq.org/2016/07/19/the-java-jit-compiler-is-darn-good-in-optimization/