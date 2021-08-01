# PEM Database API

### 기능 목록
* MobilityData CRUD
* BffData CRUD
* 검증
    * 저장할 때 데이터가 중복되었는지 검증
    * 
    
### TODO List
* CRUD
  * ~~생성~~
  * 읽기
  * 수정
  * 삭제
  
* ~~배치 인서트~~
* 검증
* 엔티티 연관관계 설정
* cascade type

### 어려웠던 점 & 아쉬운 점
* [문제]대량 데이터 Insert시 시간이 오래걸림
  * JPA로 해결하지 못하여 jdbc를 통해 시간 단축
  * 채번에 따른 batch insert 동작 오류