# PEM Database API

- PEM 연구실 위치 데이터를 저장, 조회 등 기능을 하는 API
---

### 기능 목록
- MobilityData
  - 저장
    - 사용자가 MobilityData를 단일파일로 저장한다
    - 사용자가 MobilityData를 여러파일로 저장한다
    - 사용자가 MobilityData를 폴더형식으로 저장한다
  - 조회(사용)
    - 사용자가 특정 이니셜 MobilityData를 가져온다
    - 사용자가 여러 이니셜 MobilityData를 가져온다
  - 수정
    - 사용자가 특정 이니셜 MobilityData를 수정한다
      - MobilityData 속성 값에 따라 선택하여 수정한다( 어떤 속성값으로 가져올 것인지? )
  - 삭제
    - 사용자가 특정 이니셜 MobilityData를 삭제한다
      - MobilityData 속성 값에 따라 선택하여 삭제한다( 어떤 속성값으로 가져올 것인지? )
  - 예외처리
    - MobilityData가 중복되지 않게 저장하도록 한다 ( pk를 설정해도 좋을 듯 )

- BffData
  - 저장
    - 사용자가 Bff 특정 파일로만 저장한다
  - 조회(사용)
    - 사용자가 모든 BffData를 가져온다
  - 삭제
    - 모든 BffData를 삭제한다
  
- ClusteringData
  - 저장
    - 사용자가 ClusteringData를 단일파일로 저장한다
    - 사용자가 ClusteringData를 여러파일로 저장한다
    - 사용자가 ClusteringData를 폴더형식으로 저장한다
    - 가상머신이 자동으로 ClusteringData를 저장한다
  - 조회(사용)
    - 사용자가 특정 이니셜 ClusteringData를 가져온다
    - 사용자가 여러 이니셜 ClusteringData를 가져온다
  - 수정
    - 사용자가 특정 이니셜 ClusteringData를 수정한다
      - MobilityData 속성 값에 따라 선택하여 수정한다( 어떤 속성값으로 가져올 것인지? )
  - 삭제
    - 사용자가 특정 이니셜 ClusteringData를 삭제한다
      - ClusteringData 속성 값에 따라 선택하여 삭제한다( 어떤 속성값으로 가져올 것인지? )
  - 예외처리
    - ClusteringData가 중복되지 않게 저장하도록 한다 ( pk를 설정해도 좋을 듯 )
  
- Util
  - 운영체제에 따라 basePath 설정한다
  - 자동화에 필요한 폴더 파일 세팅한다
  
---
## 배포
- azure 가상머신
- jenkins

---
### 폴더 구조
### 자동화 방식
### 아키텍처