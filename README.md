# PEM Database API

### To-Do-List
- mbService에서 jpa를 사용해서 데이터를 저장하려고 함
- 근데 쿼리가 엄청 많이 나감 saveAll    vs     jdbc batch insert 는 1초컷!
- 배치 처리 해주면 시간이 감소하는지 확인 (OMG, 1/6 데이터 기준 - 17초 걸렸음)
- 


### 기능 목록
* MobilityData
  - [ ] mobility data 디비에 저장하기(시간, 위도, 경도 등)
  - [ ] mobility data 가상머신에 저장하기 (폴더 구조 만들기 & os에 따른 경로 설정)
  - [ ] MB data 가져오기
  - [ ] MB data 저장하기 (raw level의 데이터 삽입할때)
  - [ ] MB data 수정 및 삭제하기
  - [ ] MB data 중복 검증하기
* util
  - fileUtil
    - [x] basePath, osPathSign set하기
    - [x] userName file을 통해서 뽑아내기
    - [x] 필요한 폴더(rawdata/userName) 만들어주기
    - [x] list_userName 만들기
* BffData CRUD
* emClusteringData
  
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