# how browsers work

[http://www.html5rocks.com/en/tutorials/internals/howbrowserswork/?redirect_from_locale=ko](http://www.html5rocks.com/en/tutorials/internals/howbrowserswork/?redirect_from_locale=ko)

브라우저의 메인 기능은 내가 선택한 웹 리소스를 보여주는 것.(서버에 요청하여 그걸 브라우저에서 뿌려준다) 
html, pdf, image 등등
과거에는 개발자들이 웹 표준을 지키지 않아 점점 더 표준을 준수하고 있다

웹 브라우저의 공통 유저 인터페이스에는
* 주소를 입력할 수 있는 주소창
* 뒤고가기 버튼
* 북마크
* 새로고침
* 홈버튼

브라우저의 유저 인터페이스는 표준이 없다 그냥 good practice를 따르는 것
HTML5 스펙에도 UI관련 부분은 없다

1. The user interface: 주소 입력창, 뒤로가기 버튼 등 웹 페이지 를 보여주는 화면을 제외한 나머지

2. The browser engine: UI와 렌더링 엔진 사이에서 이를 정리(marshals) 

3. The rendering engine : 요청한 컨텐츠를 화면에 보여주는 역활 html 컨텐츠를 요청했다면 렌더링 엔진에서 html, css 를 파싱하여 화면에 보여준다.

4. Networking: HTTP 리퀘스트 네트워크 요청  using different implementations for different platform behind a platform-independent interface.

5. UI backend: used for drawing basic widgets like combo boxes and windows. This backend exposes a generic interface that is not platform specific. Underneath it uses operating system user interface methods.

6. JavaScript interpreter. Used to parse and execute JavaScript code.

7. Data storage. 쿠키 와 같이 로컬에 저장하는 persistence layer(localStorage, IndexedDB, WebSQL and FileSystem.)

크롬과 같은 브라우저는 각 탭이 별도의 프로세스로 여러 개의 렌더링 엔진 인스턴스를 구동

## 렌더링 엔진

브라우저 화면에 요청한 컨텐츠를 보여주어야 한다
렌더링 엔진은 기본적으로 html, xml 문서, 이미지를 보여준다. 그외 플러그인을 통해서도 다른 타입도 보여준다

## 렌더링 엔진들

파폭의 Gecko, 사파리 웹킷, 크롬의 Blink , IE의 Trident (현재는 바뀌었을 것 같다.)

##  The main flow

렌더링 엔진은 networking layer에서 전달한 request 문서 컨텐츠로 부터 시작 

1. Parsing HTML to construct the DOM tree
2. Render tree contruction
3. Layout of the render tree
4. Painting the render tree
  

 렌더링 엔진은 HTML 문서를 파싱하고 각 요소를 DOM node로 전환하여 `Dom tree (content tree) 구성`. 엔진은 external css, style element를 style 데이터와 파싱 
 스타일링 정보와 HTML의 비주얼 instruction과 함께 또 `render tree`라는 또다른 트리를 만든다.  

렌터 트리는 사각형의 비주얼 속성(색상, dimensions)을 가진다  사각형에는 화면에 표현되기위한 순서가 있다.  

렌더 트리를 만든 후 레이아웃 프로세스로 넘어간다. 각 노드가 화면에 표현될 수 있도록 조정한다. 다음은 `Painting`, 렌더 트리를 순회하며 각 노드가  UI 백엔드 레이어를 통해 그려진다.  

이 과정은 점진적(gradual) 절차 즉, 각 단계를 모두 수행 후 다음 단계로 넘어가는게 아니라 보여질 수 있는 부분(노드 혹은 렌더 트리를 구성하는 각 사각형)부터 빨리 뿌려주어 사용자 경험을 향상 시킨다
렌더 엔진은 가능한 빨리 화면에 컨텐츠를 표현하려고 한다. 그래서 HTML이 모두 파싱하여 렌더 트리를 구성할때까지 기다리지 않는다

webkit과 Gecko 엔진 은 조금 다른 용어를 사용한지만 기본적인 플로우는 같다.

Gecko 에서는 프레임 트리라고 부르고 webkit에서는 렌더 트리라 부른다.

## Parsing-general

파싱은 렌더링 엔징의 매우 중요한 절차로 좀 더 깊게 살펴보자
문서를 파싱한다는 것은 사용할 수 있는 구조로 변환 한다는 뜻으로 파싱의 결과는 문서의 구조를 표현하는 노드 트리. 이를 파스 트리, syntax 트리라 부른다.

## Grammers

파싱은 document가 따라야할 syntax 규칙을 기반으로 진행된다.
`[context free grammer](http://www.html5rocks.com/en/tutorials/internals/howbrowserswork/?redirect_from_locale=ko#context_free_grammar)`로 불리는 vocabularay와 syntax 룰로 구성된 문법으로 구성되있다면 파싱할 수 있다
사람의 언어는 전통적인 파싱 기술로 분석할 수 없다.

## Parser-Lexer combination

```
Document -> Lexical analysis -> syntax analysis -> parser tree
```

파싱은 `Lexical analysis` 와 `syntax analysis` 로 나뉘어 진다

Lexical analysis은 토큰으로 나누는 절차로 토큰들은 언어 어휘(vocabulary: 유효한 빌딩 블록의 묶음)로 이루어진다. 사람으로 치면 사전에 나열되어 단어들로 사람이 쓰는 언어를 구성한다

Syntax Analysis 는 언어 문법 규칙을 적용한다 

파서는 Lexer(tokenizer)와 언어 문법 규칙을 따르는 문서를 분석하여 parse 트리를 구성하는 Parser 두 개의 컴포넌트로 나뉜다.

lexer로 불법한 문자나 공백, 개행을 제거(strip)한다.

파싱 절차는 반복적(iterative)이다 파서는 lexer에게 문법 규칙에 매칭되는 토큰을 요청하고 매칭되는 것이 있으면 parse 트리에 추가하고 다시 또다른 토큰을 요청한다. 매칭되지 않은 경우 내부적으로 토큰을 저장하고 다시 다음 토큰을 받아 내부에 저장된 토큰과 매칭될 때 까지 처리한다. 만약 매칭되는 룰을 찾지 못한경우 exception을 발생시킨다 

## Translation

대부분의 경우 parse tree가 최종 산물이 아니다. 파싱은 변환(Translation: 입력된 문서를 다른 포멧으로 변환)을 거친다. 
예를 들어 compilation. (컴파일러는 컴파일 소스를 머신 코드로 파싱)
파싱을 거처 파스 트리를 만들고 이를 변환하여 machine code 문서가 된다.

```
source code -> parsing -> parse tree -> translation -> machine code
```

## Type of Parsers

Top down parser
Bottom up parser : 매칭되는 룰을 찾을때 까지 스캔. shift-reduce parser 입력에서 부터 오른쪽으로 파싱(포인터 관점에서 오른쪽으로 이동)


## Generating parsers automatically

파서를 만들기 위한 툴은 많이 있다. 파서를 만들기 위해선 파서를 깊이있게 이해해야 하며 직접 최적화를 거쳐야 한다. 

Webkit은 lexer를 만들기 위해 Flex, 파서를 만들기 위해 Bison Flex의 입력은 토큰들의 expression이 정의된 정규표현식 파일
Bison의 입력값은 BNF 포멧의 문법 룰


## HTML parser

HTML parser는 HTML 마크업을 parse tree로 만들어준다

## The HTML grammer definition

W3C 스팩 참고

## Not a context free grammer

BNF와 같은 포멧으로 grammer syntax를 만들 수 있다.
HTML-DTD(Document Type Definition) 은 context free grammer가 아니다

XML과 HTML 파서의 차이
HTML은 "forgiving": 특정 태그를 생략해도 큰 문제가 되지 않는다.
이 점이 HTML 이 인기를 얻은 큰 이유라 할 수 있다. HTML 태그 입력 중 실수가 있어도 웹 문서 제쟉이 가능하다. 또한 이 점이 HTML 파서가 기존의 전통적인 파서들로는 쉽게 파싱되지 않는 점이기도 하다. XML 파서로 HTML 파싱을 할 수 없다.

## HTML DTD

HTML의 정의는 DTD 포멧이다. 이 포멧은 SGML 군의 언어를 정의 할 때 사용한다. 포멧은 모든 엘레멘트,속성과 계층관계를 정의한다.

DTD에도 몇 가지 다른 변이(variation)이 있다. strict mode 모드나 기타 다른 모드

## DOM

parse tree는 DOM(Document Object Model.) 엘레멘트와 속성 노드들의 트리이다. object는 HTML 문서와 자바스크립트와 같은 외부에서 접근할 수 있는 HTML 엘레멘트의 인터페이스를 나타낸다.
트리의 루트는 Document object

 HTML고 같이 DOM 역시 W3C 에서 정의
[http://www.w3.org/TR/2003/REC-DOM-Level-2-HTML-20030109/idl-definitions.html](http://www.w3.org/TR/2003/REC-DOM-Level-2-HTML-20030109/idl-definitions.html)

## The parsing algorithm

HTML은 일반적인  top down, bottom up 파서로는 파싱할 수 없다.
이유
1.  언어의 forgiving 속성
2. 브라우저에서 잘 알려진 invalid HTML에 대한 에러 허용(error tolerance)가 있다
3. 파싱 절차는 reentrant. HTML은 파싱하는 동안 변경이 될 수 있는 dynmic code가 가능

일반적인 파싱 기술로는 불가능하며 브라우저들은 커스텀 파서를 통해 HTML을 파싱한다

[https://whatwg.org/specs/web-apps/current-work/multipage/parsing.html](https://whatwg.org/specs/web-apps/current-work/multipage/parsing.html) 을 참고하면 파싱 알고리즘의 자세한 절차를 알 수 있다. 알고리즘은 두 가지 전략으로 구성되는다: tokenization, tree contruction

Tokenization : 어휘 분석lexical analysis, 입력 토큰을 파싱 
Tokenizer가 토큰을 인식하고 tree 생성자에 전달하고 다큼 문자로 다음 토큰을 인지하는 절차를 거치며 입력의 끝까지 진행

```
Network -> Tokenizer -> Tree Contruction -> DOM
                       ㄴ Script Execution <-                                     
```

## The Tokenization algorithm

알고리즘의 결과는 HTML 토큰. 알고리즘은 State machine으로 표현
각 상태state 는 다음 상태로 업데이트 될 때까지 하나 이상의 문자 input stream을 사용cosume . 














