# PEM Database API

### 기능 목록
* MobilityData CRUD
  - [x] C
  - [x] R
  - [ ] U
  - [ ] D
  - [x] 배치 인서트
* BffData CRUD
  - [x] C
  - [x] R
  - [ ] U
  - [ ] D
  - [ ] 배치 인서트

* 검증
    * 저장할 때 데이터가 중복되었는지 검증
    
### TODO List
* CRUD
* 배치 인서트
* 검증
* 엔티티 연관관계 설정
* cascade type

### 어려웠던 점 & 아쉬운 점
* 대량 데이터 Insert시 시간이 오래걸림
  * JPA로 해결하지 못하여 jdbc를 통해 시간 단축
  * 채번에 따른 batch insert 동작 오류 이해하지못함
  
* bffData findAll할때 More than one row with the given identifier was found: 26,
에러 발생 -> fetch.Lazy로 해결하였지만 이해하지못함
  
* jenkins로 자동 배포!