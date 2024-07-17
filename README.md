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
| 영화 목록 | 관람후기 | 지점목록 |
| ------------- | ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/c7f889ff-8f7d-4404-9ee1-e9b541dd51e6) | ![image](https://github.com/user-attachments/assets/0f76eac2-44e8-4ebd-8df8-ff96011895ff) | ------------- |


---
### 회원가입 및 로그인 화면
| 회원가입 | 로그인 |
| ------ | ------ |
| ![image](https://github.com/user-attachments/assets/8c1fb3c5-e12f-49db-9c58-c83f88a3eb6a) | ![image](https://github.com/user-attachments/assets/0e3a6fba-59e2-4e92-b56b-467668f8d4c8) |

### 회원정보 및 My Page
| My Page 메인 | 내 정보수정 | 예매내역 |  금액충전 |
| ------ | ------ | ------ | ------ |
| ![image](https://github.com/user-attachments/assets/f02dd471-07a3-4ff2-adc2-e944faf4d40b) | ![image](https://github.com/user-attachments/assets/190cc0fa-5961-4acd-999c-a093a1d45a28) | ![image](https://github.com/user-attachments/assets/2711ed17-3b0b-44e3-b4fc-f8f8144d869d) | ![image](https://github.com/user-attachments/assets/c1728bae-a5eb-4694-937b-49a342517791) |

---
### 영화 예매 화면 
| 영화 및 지점,날짜 선택 | 좌석 선택 | 
| ----- | ----- |
| ![image](https://github.com/user-attachments/assets/4edf6a67-7d79-416f-9677-4dd7248ee210) | ![image](https://github.com/user-attachments/assets/1527233e-eca9-4064-a178-656e0ee05552) | 

---
### 영화 게시판 화면
| 게시판 메인 화면 | 게시판 작성 | 게시판 수정 |
| ----- | ------ | ----- |
| ![image](https://github.com/user-attachments/assets/31ddff12-a03f-4686-b42e-53079fb5fef2) | ![image](https://github.com/user-attachments/assets/5b249aef-0241-4563-b48d-92951421d824) | ![image](https://github.com/user-attachments/assets/05d6e3e7-f160-4c6e-9022-bbc86d9d9b88) |




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
| 영화조회 | 영화수정 | 영화등록 |
| -------- | -------- | -------- | 
| ![image](https://github.com/user-attachments/assets/61e8f250-3fae-4610-a735-13e41d5b087d) | ![image](https://github.com/user-attachments/assets/57b44430-d674-42db-868c-83d3ff6159ff) | ![image](https://github.com/user-attachments/assets/d1c4cc95-9502-4c8c-8d12-60f9f279110c) |

---
### 관리자 티켓관리 화면
| 티켓 관리 |
| -------- | 
| ![image](https://github.com/user-attachments/assets/dd41f3e1-7f46-4d06-b86d-67491559adc2) |

---
### 관리자 게시판관리 화면
| 게시판 관리 |
| -------- | 
| ![image](https://github.com/user-attachments/assets/ce12388c-1008-4ed6-9451-241e64140eb0) |

---
### 관리자 총 매출 관리 화면
| 총 매출 |
| ------- |
| ![image](https://github.com/user-attachments/assets/f50be4dc-7dbc-417f-9a0f-37514b820a61) |

---
| 영화별 | 회원별 | 지점별 | 날짜별 |
| ------- | ------- | ------- | ------- |
| ![image](https://github.com/user-attachments/assets/933c516b-eb42-429e-8c02-548c30c26c69) | ![image](https://github.com/user-attachments/assets/62fb511b-e284-4afb-9d85-345138fd635e) | ![image](https://github.com/user-attachments/assets/cb17ce05-5fa9-4be5-ab7e-552ef0bb8075) | ![image](https://github.com/user-attachments/assets/c6984a2b-2d6e-43e0-b938-cb8947e72211) |



---
# 🧩 ERD
![ERD_1](https://github.com/hajungin/movie/blob/master/cinemaERD_1.png)
---


