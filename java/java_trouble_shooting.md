# 자바 트러블 슈팅

> 강의 내용을 정리하여 추가 작성 예정!

장애 발생 시 개인적인 판단으로 `단정` 지으면 안된다. 근거를 기반으로 판단해야 한다. 시스템 로그를 분석이 필요


thread stack 
	ctrl + break
	kill -3 pid
	jstack 이 멈추는 경우가 있다.(느리다)


ps -ef|grep <>
jps -v


TDA 무료
JProfiler 상용
ThreadLogic


top 
o 입력 후 cpu 입력
1 입력: 코어별 사용율
c  command 입력
CPU 사용량이 높을 때

ps -eLF | grep 
4번째 컬럼 : native Id


```
[appadmin@dev-dooray ~]$ ps -eLF|grep 14038
appadmin 14038 12526 14038  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14039 98   20 2816881 844448 1 11:55 pts/1   00:03:19 java InfinitLoop
appadmin 14038 12526 14040  0   20 2816881 844448 0 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14041  0   20 2816881 844448 1 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14042  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14043  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14044  0   20 2816881 844448 0 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14045  0   20 2816881 844448 5 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14046  0   20 2816881 844448 7 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14047  0   20 2816881 844448 4 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14048  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14049  0   20 2816881 844448 3 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14050  0   20 2816881 844448 1 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14051  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14052  0   20 2816881 844448 3 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14053  0   20 2816881 844448 5 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14054  0   20 2816881 844448 6 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14055  0   20 2816881 844448 1 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14056  0   20 2816881 844448 1 11:55 pts/1   00:00:00 java InfinitLoop
appadmin 14038 12526 14057  0   20 2816881 844448 4 11:55 pts/1   00:00:00 java InfinitLoop
```


00:03:19 cpu time

ps -eL : L 스레드 목록 출력


online thread dump analyzer

http://spotify.github.io/threaddump-analyzer/



예제 thread dump 분석 시간

file의 변경 발생 시 watcher를 등록 할 수 있다.
java 기반 was가 hang 나는 경우 disk를 의심해 보자. disk 사용량을 모니터링 하자




irteam    2250     1  2254  1  608 Sep15 ?        06:12:00 org.apache.catalina.startup.Bootstrap start
irteam    2250     1  2255  6  608 Sep15 ?        1-07:48:47 org.apache.catalina.startup.Bootstrap start

1-07:48:47  : 하루가 넘게 cpu 타임 먹음

풀GC가 0.3 초 이내에 수행되는게 좋은 것 같다


* jstat

jstat -gcutil 10913
  S0     S1     E      O      M     CCS    YGC     YGCT    FGC    FGCT     GCT
  0.00  42.08  63.39   8.57  97.64  95.34     33    0.849     4    0.607    1.457

추가 옵션 없이 실행 시 한번 만 실행 

jstat -gcutil 10913 1s

s 없이 실행 할 경우 ms 단위로 실행


O(Old 열역) 과 FGC 값을 살펴보면 메모리 릭을 판단할 수 있다.

```
jstat -gccapacity 10913
 NGCMN    NGCMX     `NGC`     S0C   S1C       EC      OGCMN      OGCMX       `OGC`         OC       MCMN     MCMX      MC     CCSMN    CCSMX     CCSC    YGC    FGC
698880.0 698880.0 698880.0 69120.0 73216.0 549376.0  1398272.0  1398272.0  1398272.0  1398272.0      0.0 1146880.0 109400.0      0.0 1048576.0  12416.0     33     4
```

* 힙 덤프 

STW 발생
많은 시간 소요
힙 만큼의 용량으로 덤프파일 생성됨


힙 덤프 생성 방법
hprof
jmap
java visual vm
gcore


* jmap

jmap -histo pid


* gcore : linux 전용, 절대 사용하지 말것(대용량 파일이 생성됨)

gcore -o output_file_name pid
결과 파일로 jmap, jstack 모두 활용 가능


* 힙 덤프 분석

MAT Heap analyzer
IBM Heap analyzer
jhat(not recommended)
visualvm (not recommended)


* 시스템 측정

* mpstat

mpstat 5 10
mpstat -p 0 1 2
-p (processor number)


* vmstat

swap이 높으면 문제가 있다
(가장 앞)r(런큐) 값이 높으면 서버에 부하가 있다 (코어 수보다 높으면 문제가 있다.)

* pmap

pmap pid 
메모리/파일을 쓰는 정보 확인 가능

* df, du, lsof, top


* collectl, collectd
