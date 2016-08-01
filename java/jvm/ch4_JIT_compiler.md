# JIT 컴파일러로 작업하기

JIT(just-in-time)
JVM 내에서 컴파일러보다 성능에 가장 큰 영향을 주는 요소

* 개요

인터프리터로 언어 프로그램은 이식성이 높다
인터프리터된 코드는 거의 언제나 컴파일된 코드보다 느릴 것

자바 앱들은 컴파일되지만, 특정 CPU에 맞는 특정 바이너리로 컴파일되는 대신 최전화된 어셈플리 언어로 컴파일(바이트 코드) 어셈블리 언어는 자바 바이너리로 구동 = 독립적인 플랫폼 제공. 
최적화된 바이너리 코드를 실행하기 때문에 자바 프로그램은 실행하면서 코드를 플랫폼 바이너리로 컴파일할 수 있다. 이 컴파일 방식은 프로그램이 실행되면서 수행되며 just in time으로 일어난다

* 핫프맛 컴파일

자주 호출되는 메소드/루프를 컴파일

ex>
obj1.equals(obj2)
equals()의 클래스 타입을 찾아야 하며 룩업 시간이 걸리는데 jvm이 obj1의 타입이 String이라는 사실을 호출이 누적됨에 따라 인지고 String.equals 메소드를 직접 호출하는 컴파일된 코드를 만들 수 있다. 

* 기본 튜닝

-server
-client
-XX:+TieredCompilation

java 7 의 경우이면 java 8에서는 다를 수 있다.


* 스타트업 최적화, 배치 동작 최적화의 결론: 티어드로 시작시 스타트업이 좋다!

```
java -XX:+UnlockDiagnosticVMOptions -XX:+PrintFlagsFinal -version

```

`bool TieredCompilation  = true  {pd product}`

* 중급 튜닝


* 코드 캐시 튜닝

jvm이 실행할 수 있는 컴파일된 코드가 캐시될 영역 크기를 지정할 수 있다.

-XX:ReservedCodeCacheSize  : 251658240
-XX:InitialCodeCacheSize : 2555904


* 컴파일 임계치

CompileThreshold : 10000

* 컴파일 프로세스 점검

-XX:+PrintCompilation

timestamp compilation_id attributes (tiered_level) method_name size deopt

attributes 목록
% : OSR(on stack replacement)
s : 메서드가 동기화 된다
! : exception handler 있다
b : 블로킹 모드에서 컴파일된다
n : 네이티브 메서드에 대한 래퍼를 위해 컴파일

jstat으로 컴파일 점검

jstat -compiler pid

jstat -printcompilation pid time_interval

* 고급 컴파일러 튜닝

* 컴파일 스레드

CICompilerCount  := 3

컴파일 큐 내에 있는 메서드들을 대상으로 비동기적으로 컴파일
큐는 정확하게 순차적이지 않다.

* 인라이닝

-XX:MaxInlineSize
-XX:MaxFreqInlineSize (java8 에 없다!?)

메소드 인라이닝은 35

인라이닝은 컴파일러가 할 수 있는 최적화 방법 중 가장 효과적
인라이닝 플래그 튜닝은 수정하지 말자!


* 탈출 분석

-XX:+DoEscapeAnalysis : true

탈출 분석은 컴파일러가 수행할 수 있는 최정화 중에서 가장 복잡

ex>
동기화 락이 불필요한 경우 제거
메모리에 필드를 저장할 필요 레지스터에 저장하고 사용 객체 레퍼런스 역시 레지스터에 저장하고 사용
실제 필요없는 객체를 개별적인 필드로 기록

* 역최적화

역최적화는 원상태(인터프리트 코드)로 돌려야 하는 상태 
더는 유효하지 않는 코드(대상 객체의 타입 변경)는 역최적화
코드가 역치적화 될 때 성능에 작은 영향을 끼치지만 빠르게 다시 준비함

역최적화 조건

1. 진입불가

timestamp compilation_id attributes (tiered_level) method_name size deopt
형태에서 마지막 deopt에 “made not entrant”

ex> 컴파일된 이후 메서드가 컴파일 된 방식과는 다른 방식으로 사용될 경우 
티어드 컴파일이 동작하는 방식으로 클라이언트 컴파일에서 서버 컴파일러로 컴파일 시 기존 클라이언트 컴파일러의 결과물을 진입 불가로 만든다.


2. 좀비 코드 역최적화 

timestamp compilation_id attributes (tiered_level) method_name size deopt
형태에서 마지막 deopt에 “made zombie”


* 티어드 컴파일 레벨 

tiered level

0 : 인터프린트
1 : 단순 c1 컴파일된 코드
2 : 제한된 c1 컴파일된 코드
3 : 전체 c1 컴파일된 코드
4 : server

대부분은 3 레벨에서 컴파일된다 
가장 좋은 상황은 0 > 3 > 4

서버 컴파일러 큐가 가득 찬경우 2 레벨을 거쳐 3 > 4 로 진행되기도 한다.