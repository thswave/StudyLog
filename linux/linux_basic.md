# 리눅스 기본 공부

참고 도서: 김태용의 리눅스 쉘 스크립트 프로그래밍 입문

> 필요할 때 마다 검색해 가며 해결한 쉘스크립트 프로그래밍을 기본부터 배워보기 위해 시작 

# 1. 리눅스 쉘과 명령어 기초

* 다중 명령어 사용

`;` : 여러 명령 실행. 단, 첫 번째 명령이 실패해도 두 번째 명령 실행 됨
`&&` : 첫 번째 명령 성공 시 두 번째 명령 수행
`||` : 첫 번째 명령의 결과에서 에러 발생해도 각각의 모든 명령을 수행


* `!` 

`!-1` : 가장 최근 명령
`!!` : 가장 최근 명령
`!-2` : 가장 최근 명령부터 두번 째의 명령

* tree

디렉토리 트리구조 출력
ex> tree -L 1 /

* su -

수퍼유저 전환

* ls 

ls -l 
퍼미션 | 하드링크 수 | 소유자 | 그룹 | 파일크기 | 수정시간 | 파일명

time format

ls -l --time-style=long-iso
ls -l --time-style=full-iso
ls -l --time-style=locale

-i : inode info
하드링크: 동일한 파일시스템 내의 파티션에서 동일한 inode 정보를 가지는 파일
소프트링크: 이름만 링크


* less, more

more file 
v 입력시 vi 모드로 바로 편집 가능


* directory

/etc : 시스템의 환경 설정. 대부분 텍스트 파일
 /etc/passwd : 유저의 각종 정보 
 /etc/shadow : 유저의 암호화된 패스워드
 /etc/rc.d/init.d : 부팅 시 시작할 여러 시스템 스크립트
 /etc/sysconfig/iptables : 리눅스 방화벽 iptables 환경 설정 저장 파일
 /etc/sysconfig/network-scripts/ifcfg-eth0 : 부팅 시 사용할 첫 번째 이더넷 카드 정보

/sbin, /usr/sbin : 시스템 관리자를 위한 프로그램 위치(수퍼유저를 위한 프로그램)

/usr : 사용자 애플리케이션 지원하기 위한 파일

/usr/local : 소프트웨어 설치 시 또는 로컬머신에서 사용할 파일 위치. 다운로드받은 소스파일들을 기본 옵션으로 컴파일하면 /usr/local 디렉토리가 기본 설치 위치. 이 때 실행 파일은 /usr/local/bin 디렉토리에 위치

/var : 운영 중인 시스템의 변화를 체크할 수 있는 각종 로그 파일 위치
	/var/log : 로그 파일 위치
	/var/spool: 메일 메세지와 프린트 작업같은 프로세스를 위한 큐를 잡아 놓기 위한 디렉토리 

/lib : 공유 라이브러리

/proc : 이 디렉토리는 파일을 포함하지 않는다. 실제로 존재하지 않는 가상 파일시스템. /proc 디렉토리는 커널 괄년 정보, 시스템에 운영중인 모든 프로세스에 대한 번호가 붙어진 그룹

/media, /mnt : 마운트 포인트를 위해 사용되는 디렉토리


* umask

root(UID가 99보다 작은경우 root는 UID가 0) : 022
나머지 : 002

디렉토리는 777 - umask
ex> 777 - 022 = 755
파일은 666 - umask

useradd 로 사용자 추가시 500번 이후부터 UID를 부여받는다

find . xargs : xargs 이후 명령의 인자로 사용하는 명령


* `su` vs `su -`

`su` 로 전환 시 이전 유저의 환경설정을 가짐
`su -` 새로 전환될 유저의 환경변수 적용


* chmod : 권한변경
* chown : 소유자와 그룹 변경

ex> 

```
chown root:root
chown multi.multi

```

* chgrp : 그룹 변경


* lsattr, chattr : 퍼미션, 파일에 대한 속성 부여

ex>
chattr +i <filename>
i(immutable) 속성이 추가되면 수퍼유저라도 변경, 삭제 등 어떠한 조작이 불가능
a: 내용 추가는 가능하지만 삭제 불가능

man chattr 참조



* jobs: 현재 쉘에서 자신의 프로세스 목록 포기
* fg: 포그라운드로 가져오기
* bg: 백그라운드로 보내기
* ps: process list
* kill: send kill signal to process

`jobs` 명령어로 프로세스 백그라운드 프로세스 목록 확인
혹은 `ps`로 확인
`kill` 로 시그널 전달 

`kill -l` : 시그널 목록 확인 

 
 1) SIGHUP	: 로그아웃/접속 종료 시 발생 시그널. 설정 파일을 변경시키고 변화된 내용을 바로 적용할 때 사용 ex> httpd.con 변경 후 killall -HUP httpd 로 설정 리로딩
 2) SIGINT	: 현재 작동중인 프로세스의 동작을 멈출 때 사용 일반적인 ctrl-c
 3) SIGQUIT	: SIGINT와 같이 사용자가 터미널에서 종료키를 누를 때 커널에 의해 보내짐(crtl-\) 이 시그널로 비정상 종료하게되어 코어 파일을 생성하고 종료(gdb 등의 디버거로 분석할 수 있는 프로세스 이미지 파일)
 4) SIGILL	 
 5) SIGTRAP
 
 6) SIGABRT	 
 7) SIGBUS	 
 8) SIGFPE	 
 9) SIGKILL	: 해당 프로세스 강제 중지
 10) SIGUSR1

11) SIGSEGV	: 메모리 접근이 잘못되었을 때, 프로세스가 포인터를 잘못 사용하여 정해진 영역 이외의 메모리 영역 침범 시 
12) SIGUSR2	
13) SIGPIPE	
14) SIGALRM	
15) SIGTERM : 정상적인 종료 프로세스에 의해 정의되는 정상적인 종료 방법에 의해 프로세스 종료 kill에서 시그널 지정하지 않으면 사용

16) SIGSTKFLT	
17) SIGCHLD	
18) SIGCONT	
19) SIGSTOP	
20) SIGTSTP

21) SIGTTIN	
22) SIGTTOU	
23) SIGURG	
24) SIGXCPU	
25) SIGXFSZ

26) SIGVTALRM	
27) SIGPROF	
28) SIGWINCH	
29) SIGIO	
30) SIGPWR

31) SIGSYS	
34) SIGRTMIN	
35) SIGRTMIN+1	
36) SIGRTMIN+2	
37) SIGRTMIN+3

38) SIGRTMIN+4	
39) SIGRTMIN+5	
40) SIGRTMIN+6	
41) SIGRTMIN+7	
42) SIGRTMIN+8

43) SIGRTMIN+9	
44) SIGRTMIN+10	
45) SIGRTMIN+11	
46) SIGRTMIN+12	
47) SIGRTMIN+13

48) SIGRTMIN+14	
49) SIGRTMIN+15	
50) SIGRTMAX-14	
51) SIGRTMAX-13	
52) SIGRTMAX-12

53) SIGRTMAX-11	
54) SIGRTMAX-10	
55) SIGRTMAX-9	
56) SIGRTMAX-8	
57) SIGRTMAX-7

58) SIGRTMAX-6	
59) SIGRTMAX-5	
60) SIGRTMAX-4	
61) SIGRTMAX-3	
62) SIGRTMAX-2


* users : user list 

* groups : group list

* useradd, userdel

ex>
useradd asdf
passwd asdf

userdel -r asdf
-r 옵션으로 홈 디렉토리 모두 삭제

* usermod, groupmod

# group allocation 
usermod -g multi linux
groups asdf
asdf: multi

# groupt name swap
groupmod -n tobe-group orig-group


* who : 로그인 되어 있는 유저 목록

* w : 로그인한 모든 유저 정보

etc> 
ps -a 
skill -9 username  # 유저가 로그인한 아이디를 쓴다.


* logname


* sudoer

/etc/sudoer 슈퍼유저 권한 지정
visudo 로 /etc/sudoer 파일 수정가능


* ac : 유저의 로그인 시간을 시간단위 출력

ac
ac -d


* last : 모든 유저의 마지막 로그인 시간 출력


* newgrp : 자신이 소속된 그룹을 새 그룹으로 변경/추가 (쉘을 빠져나오면 초기화)


* tty : 현재 유저의 터미널 출력


* stty: 터미널 설정을 출력하거나 변경 


* wc -c : 바이트 카운트

wc -c < asdf.txt

* wall, write : 접속해 있는 모든 유저에게 메세지 전송할 때 사용

wall msg
write appadmin


* uname : system info

Linux

* arch

x86_64

* lastlog

```
root             pts/1    103.243.200.78   Tue Jul 26 19:49:01 +0900 2016
bin                                        **Never logged in**
daemon                                     **Never logged in**
nobody                                     **Never logged in**
vcsa                                       **Never logged in**
saslauth                                   **Never logged in**
postfix                                    **Never logged in**
sshd                                       **Never logged in**
ntp                                        **Never logged in**
appadmin         pts/2    103.243.200.78   Tue Jul 26 20:59:45 +0900 2016
ntop                                       **Never logged in**
mysql                                      **Never logged in**
newrelic                                   **Never logged in**
tcpdump                                    **Never logged in**
```


* lsof : 오픈된 파일 목록  -i 옵션시 네트워크 소켓 파일 출력


* strace : 주어진 명령시 실행되는 시스템 콜

strace df

* ltrace : 주어진 명령이 호출하는 라이브러리 콜


* nc : TCP/UDP 포트 커넥션과 리슨 출력


* free : 메모리와 캐시 사용량


* procinfo : /proc 파일 시스템에 대한 정보 출력 

* lsdev : 설치된 디바이스리스트

* stat : 주어진 파일의 각종 정보 출력 

* netstat, vmstat, uptime

* hostname  : 호스트명 출력 /etc/sysconfig/network 파일에 지정 

* readelf : elf 바이너리 파일 정보 출력 

* size : 바이너리 실행 파일 또는 아카이브 파일의 세그먼트 크기 출력 

* logger : 시스템 로그 기록 /var/log/message 시스템 로그 기록 파일에 저장

ex>
logger
입력!

* ps, pgrep, pkill  

(나중에 자세히 다뤄보자)

* pstree : 프로세스 목록을 트리 형식 출력 -p 옵션으로 pid 

* nice : 스케쥴링 우선권 조정

-20 : 최 우선 : 더 많은 cpu 리소스 할당
19 : 가장 낮은 우선권

ex>
nice telnet
nice -n -20 telnet 


* nohup : 명령 인자를 hangup 신호를 무시한 채 수행


* pidof : 실행중인 프로세스 아이디를 검색/출력

* fuser : 파일/소켓을 사용하는 프로세스 출력

ex>
fuse -u /usr/sbin/sshd


* cron : 스케줄러

/etc/crontab 설정파일


### 프로세스 관리와 부팅

* init : 모든 프로세스의 부포 프로세스 pid 1 /etc/inittab 파일에 설정된 런레벨 결정

```
Default runlevel. The runlevels used are:
0 - halt (Do NOT set initdefault to this)
1 - Single user mode
2 - Multiuser, without NFS (The same as 3, if you do not have networking)
3 - Full multiuser mode
4 - unused
5 - X11
6 - reboot (Do NOT set initdefault to this)
```


* runlevel : 멀티유저모드는 3 


* halt, shutdown, poweroff, reboot

shutdown -h now : 재부팅


* service : 시스템 서비스를 시작/중지

리눅스 시작스크립트 원본 경로: /etc/rc.d/init.d 
부팅 시 7개 런레벨별 시작 스크립트 위치 : /etc/rc.d


* ifconfig : 네트워크 인터페이스 출력/튜

ifconfig eth0 down|up

* iwconfig : 무선랜 네트워크 인터페이스 환경 출력 무선장치만 출력

* ip : 라우팅, 디바이스, 터널 정책 출력/조정

ip link show

* route : 커널 라우팅 테이블 정보 출력/변경 

ip route list 

* chkconfig : 시스템 서비스를 위한 런레벨 정보를 업데이트하고 검색 /etc/rc?.d 디렉토리에서 부팅 시 시작되는 런레벨 별 시스템 서비스를 출력/관리 

chkconfig --level 35 service_name on : 3,5 레벨 on (3: 멀티유저, 5: gui 모드)

* tcpdump : 네트워크 패킷 실시간 출력

tcpdump tcp port 21
(나중에 자세히 다뤄보자)


