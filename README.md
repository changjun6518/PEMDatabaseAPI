# PEM Database API

### To-Do-List
- 모든 데이터 디비에 저장 시키자
- mobility와 clustering 결과를 모두 확인할 수 있도록 만들자

### 기능 목록
* MobilityData
  - [x] mobility data 디비에 저장하기(시간, 위도, 경도 등)
  - [x] mobility data 가상머신에 저장하기 (폴더 구조 만들기 & os에 따른 경로 설정)
  - [x] MB data 가져오기
  - [x] MB data 저장하기 (raw level의 데이터 삽입할때)
  - [ ] MB data 수정 및 삭제하기
  - [x] MB data 중복 검증하기
* BffData CRUD
* ClusteringData
* util
  - fileUtil
    - [x] basePath, osPathSign set하기
    - [x] userName file을 통해서 뽑아내기
    - [x] 필요한 폴더(rawdata/userName) 만들어주기
    - [x] list_userName 만들기
  
### TODO List
* CRUD
* 배치 인서트
* 검증
* 엔티티 연관관계 설정
* cascade type
* user가 필요한가?
* nosql써보는 건 어떤가?

### 어려웠던 점 & 아쉬운 점
* 대량 데이터 Insert시 시간이 오래걸림
  * JPA로 해결하지 못하여 jdbc를 통해 시간 단축
  * 채번에 따른 batch insert 동작 오류 이해하지못함
  
* bffData findAll할때 More than one row with the given identifier was found: 26,
에러 발생 -> fetch.Lazy로 해결하였지만 이해하지못함
  
* jenkins로 자동 배포!