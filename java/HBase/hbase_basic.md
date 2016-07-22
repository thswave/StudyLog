
Guidelines for HBase Schema Design

참고 : https://www.mapr.com/blog/guidelines-hbase-schema-design


### RDBMS vs HBase 
RDBMS: 정규화된 엔티티(테이블)간의 상호작용(조인)
HBase: "Query-first" 스키마 디자인 즉, 가능한 모든 쿼리를 미리 가늠해 보아야 한다. 거기에 맞춰 스키마 모델을 잡아나간다.
  접근 패턴을 고려하고 이를 통해 보여져야할 데이터들을 함께 저장하고 읽어올 수 있도록 고려

> Data that is accessed together is stored together

* Distributed data is stored and accessed together
* It is query-centric, so focus on how the data is read : 어떻게 데이터를 읽어 들일지 고려
* Design for the questions

### 정규화

RDB에서 정규화를 통해 반복(중복) 데이터를 제거하고 저장할 때의 이점

1. 업데이트가 발생할 때 중복이 없어 흩어진 데이터를 일일이 업데이트할 필요가 없다 -> 빠른 쓰기 및 무결성
2. 정규화로 인해 중복 데이터가 없어 데이터 사이즈가 작다

### 역정규화

조인 횟수를 줄이기 위해 역정규화가 필요할 수 있다
HBase에서도 역정규화로 역시 중복된 데이터가 저장될 수 있다.

### 부모-자식 관계, 중첩(Nested) 엔티티

역 정규화의 예로 1:N 관계에서 HBase는 한 로우에 모두 저장 가능
ex> 주문-상품 에서 한 주문에 상품을 컬럼 패밀리로 묶어 같이 저장 -> 조인이 필요없어 빨리 읽어낼 수 있다
로우키는 주문 ID, 컬럼 패밀리는 상품, 각 상품은 상품:상품ID1, 상품:상품ID2 방식으로 중첩(Nested)되어 저장
부모 엔티티(주문)을 통해서만 자식 엔티티(상품)에 접근할 수 있게 된다.

?. 상품이 주문에 들어가는 경우 만약 해당 상품이 주문이 완료되기 전 시점에 사라지는 경우 어떻게 처리해야 할까?
최종 주문전 상품이 유요한지 확인이 필요할 것 같다.


### N:M

RDB에서는 중간 연결 테이블이 생긴다
HBase에서는 각 테이블에 중첩으로 데이터를 넣어준다

?. 정합성이 깨지는건 어떻게 맞춰야 할까?


### Generic Data, Event Data, and Entity-Attribute-Value

RDB는 데이터의 성격에 맞춰 테이블을 구성하고 성격이 다른 경우 다른 테이블에 저장한다
HBase에서는 column을 동적으로 정의할 수 있다. 유사 성격을 column family로 묶어준다

event type의 데이터인 경우(ex> 환자의 치료 기록) id + timestamp


### Self-Join Relationship - HBase

rowkey 구성에 관계를 넣어준다


### Tree, Graph Data

Column family 구성에 상위/하위 관계를 명시

### Inheritance Mapping

각 하위 테이블이 가져야할 column을 모두 하나의 테이블로 만들고 유요한 데이터만 해당 컬럼에 넣어준다 (RDB에서 만능 테이블을 만드는 것과 유사)


### Data Access Patterns 1. Use Cases: Large-scale offline ETL analytics and generating derived data

offline 분석으로 온라인 사용자가 사용할 데이터 정재

Bulk Import(via Hadoop) -> HBase -> Analyze(via Hadoop)

### Data Access Patterns 2. Use Cases: Materialized view, pre-calculated summaries

hadoop Map-Reduce으로 분석 

Raw data from HDFS or HBase
MapReduce for data transformation and ETL from raw data.
Use bulk import from MapReduce to HBase
Serve data for online reads from HBase

### Data Access Patterns 3. Lambda Architecture

Lambda Architecture
* Serving Layer: provides pre-computed view, Law Latency reads
* Batch Layer: Pre-computes data for serving layer
* Speed Layer: computes latest data

?. 분석이 필요없는 일반적인 OLTP 서비스에 HBase를 적용할 때의 특이점은 무엇이 있을까?
(본 글이 MapR 서비스 관련 글이라 hadoop 관련 정리만 있다.)







