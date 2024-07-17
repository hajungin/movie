# 🎬 Ticketing
하나의 영화관 본사 통합 관리 사이트 구현

---

# 📆 프로젝트 기간
2024.05.03~2024.05.20

---

# 🧑‍🤝‍🧑 맴버구성
- 팀원 1 : 하정인 - 메인페이지 구현, 영화 및 지점 목록관리, 관리자페이지(CRUD) (회원, 영화, 티켓, 게시판, 매출)관리 및 회원가입, ppt 제작
- 팀원 2 : 이정민 - 좌석 기능 구현, 티켓 예약 관리, 로그인, 마이 페이지(CRUD), 예매 내역, 발표
- 팀원 3 : 이호중 - 게시판 페이징 처리, 게시판 (CRUD) 검색 기능,영화 평점, ppt 제작


---


# 🛠 기술 스택
## _Frontend_
- HTML
- CSS
- JavaScript
- BootStrap


## _Backend_
#### 1)_Language_
- JAVA 17
- JSP

#### 2)_FrameWork_
- Spring Boot

#### 3)_Server_
- Tomcat

#### 4)_DataBase_
- PostgreSQL

  ----
- **JAVA 17**: 최신 기능과 성능 향상을 제공하는 프로그래밍 언어
- **Spring Boot**: 빠르고 간편한 설정으로 효율적인 개발을 지원
  - Spring Data JPA: 데이터베이스와의 효율적인 연동
  - Thymeleaf: 서버 사이드 템플릿 엔진
  - PostgreSQL: 신뢰성과 성능이 뛰어난 데이터베이스
  - Lombok: 반복되는 코드를 줄여주는 개발 도구
  - Spring Boot DevTools: 개발 편의성을 위한 도구
  - Spring Security: 강력한 보안 기능 제공
  - Validation: 입력 데이터의 유효성 검증
  - Spring Web: 웹 애플리케이션 개발을 위한 필수 모듈


---


# 📌 주요 기능
### 로그인
- DB값 검증


### 회원가입
- DB 저장
- 유효성 검사
- ID 중복 체크
- 유효성 검사


### 마이페이지
- 회원정보 수정
- 금액 충전
- 예매 내역 확인
- 회원 탈퇴

### 영화예매
- 영화 선택
- 날짜 선택
- 지점 선택 
- 좌석 기능 구현
- 결제 기능 구현
- 영화 예매 기능
  
  
### 메인페이지
- 절찬 상영 중인 영화 3편씩 랜덤
- 영화 정보 및 포스터


### 관리자페이지
- 실시간 평점 및 예매 순서 반영  
- 등록된 회원관리 (CRUD)
- 등록된 영화관리 (CRUD) 및 영화 등록
- 등록된 티켓관리 (삭제)
- 게시판관리 (삭제) 및 페이징 처리
- 카테고리별 총 매출 현황 분석
    

### 게시판
- 게시판 등록 및 (CRUD)
- 게시판 페이징 처리
- 게시판 검색 기능

---

# 🎬 화면구성
### 메인 화면
![image](https://github.com/user-attachments/assets/372af8dc-e6d1-49e8-bda0-a46cf643e84a)

---
### 영화 정보 화면
| 영화 목록 | 관람후기 |
| ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/c7f889ff-8f7d-4404-9ee1-e9b541dd51e6) | ![image](https://github.com/user-attachments/assets/0f76eac2-44e8-4ebd-8df8-ff96011895ff) |


---
### 로그인 및 내 정보 화면
![image](https://github.com/hajungin/movie/assets/162389696/b1e9e446-45a5-4b48-9e1a-9e55f40dfad2)


---
### 영화 예매 화면 
![image](https://github.com/hajungin/movie/assets/162389696/67f37aee-59c9-4e7f-b8b2-b61b49431b75)


---
### 영화 게시판 화면
![image](https://github.com/hajungin/movie/assets/162389696/d232dc3d-c39c-410c-8751-9c141b812605)

---
### 영화 관리자 메인 화면
| 평점 Top3 | 예매율 Top3 |
| ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/d61fabad-a4c8-481c-bd6a-f1db2c30536d) | ![image](https://github.com/user-attachments/assets/4ef5a23f-1b6a-480a-b247-2fe74207e95d) |

---
### 관리자 회원관리 화면
|회원조회|회원수정|
| ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/e4f0b887-104f-48a8-8f98-d1e5d7b85954) | ![image](https://github.com/user-attachments/assets/8b9e819e-fb0a-4445-a9b6-0caa20b270bb) |

---
### 관리자 영화관리 화면
|영화조회|영화수정|영화등록|
| ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/61e8f250-3fae-4610-a735-13e41d5b087d) | ![image](https://github.com/user-attachments/assets/57b44430-d674-42db-868c-83d3ff6159ff) | ![image](https://github.com/user-attachments/assets/d1c4cc95-9502-4c8c-8d12-60f9f279110c)
 |




  
# 🧩 ERD
![ERD_1](https://github.com/hajungin/movie/blob/master/cinemaERD_1.png)
---


