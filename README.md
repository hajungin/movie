# 🚩 프로젝트명
#### 영화관 본사 사이트 

---
# 목차
1. [프로젝트 소개](#-프로젝트-소개)
2. [프로젝트 주제](#-프로젝트-주제)
3. [프로젝트 개요](#-프로젝트-개요)
4. [프로젝트 기간](#-프로젝트-기간)
5. [팀원 소개 및 역할](#-팀원-소개-및-역할)
6. [기술 스택](#-기술-스택)
7. [주요 기능](#-주요-기능)
8. [프로젝트 package](#-프로젝트-package)
9. [화면 구성](#-화면구성)
10. [ERD](#-ERD)
11. [느낀점](#-느낀점)
    
# 🎬 프로젝트 소개
#### 하나의 영화관 본사 통합 관리 사이트 구현하여 영화 예매 및 통합 관리 기능 구현

---
# 📰 프로젝트 주제
#### 고객은 회원가입을 통하여 금액을 충전 후 영화 예매, 관람후기 작성이 가능하며 관리자는 고객관리, 영화관리, 티켓관리, 게시판관리, 총 매출현황 조회

---
# 📚 프로젝트 개요
#### 영화 예매 및 통합 관리 웹 서비스
- 영화예매 : 회원이 원하는 영화, 지점, 날짜, 좌석 선택
- 영화 목록 및 관람후기 : 등록된 영화 정보와 해당 영화 관람후기 조회
- 고객관리 : 등록되어 있는 고객 정보 관리
- 영화관리 : 등록된 영화정보 관리 및 영화 등록
- 티켓관리 : 회원이 예매한 티켓 목록
- 게시판관리 : 회원이 작성한 영화 관람 후기 목록
- 총 매출 관리 : 모든 지점에서 판매한 총 매출 현황 목록

---
# 📆 프로젝트 기간
2024.05.03~2024.05.20

---

# 🧑‍🤝‍🧑 팀원 소개 및 역할
- 하정인
  - 메인페이지 : 등록되어 있는 영화 3편씩 랜덤으로 출력
  - 영화 목록 : 등록되어 있는 영화 정보 목록 출력
  - 관람 후기 : 해당 영화 관람 후기 조회
  - 지점 목록 : 상영 가능한 지점 목록 조회
  - 회원가입 : 회원과 관리자 권한 분리

  관리자
  - 회원 관리 : 등록된 회원정보 조회 및 수정, 삭제
  - 영화 관리 : 등록된 영화정보 조회 및 등록, 수정, 삭제
  - 티켓 관리 : 회원들이 예매한 티켓 내용 조회 및 예매 취소
  - 게시판 관리 : 회원들이 등록한 게시판 내용 조회 및 삭제
  - 총 매출 현황 : 전체 매출 조회, 영화별 매출 조회, 회원별 매출 조회, 지점별 매출 조회, 날짜별 매출 조회
    
- 이정민 - 좌석 기능 구현, 티켓 예약 관리, 로그인, 마이 페이지(CRUD), 예매 내역
- 이호중 - 게시판 페이징 처리, 게시판 (CRUD) 검색 기능,영화 평점


---


# 🛠 기술 스택
## __IDE__
![image](https://github.com/user-attachments/assets/7a402f12-e2be-486e-9067-327f6257271d)

## __Frontend__
![HTML5](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">


## __Backend__
#### 1)_Language_
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
- JSP

## __FrameWork__
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

## __Server__
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## __DataBase__
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
- DBeaver

## __GitHub__
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

---
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

### 메인페이지
- 절찬 상영 중인 영화 3편씩 랜덤
- 영화 정보 및 포스터
  
### 로그인
- DataBase 값 검증
- 관리자와 사용자 권한 분류

### 회원가입
- DB 저장
- 유효성 검사
- ID 중복 체크
- 유효성 검사
- 이메일 "@ezen" 포함 시 관리자권한 부여


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

### 관리자페이지
- 메인페이지 평점 순위 및 예매 순위 조회  
- 등록된 회원관리 (조회, 수정 및 삭제)
- 등록된 영화관리 (조회, 등록, 수정, 삭제)
- 등록된 티켓관리 (조회 및 삭제)
- 게시판관리 (조회 및 삭제) 및 페이징 처리
- 카테고리별 총 매출 현황 분석
    

### 게시판
- 게시판 등록 및 (CRUD)
- 게시판 페이징 처리
- 게시판 검색 기능

---

# 📘 프로젝트 package
| 패키지명 | 클래스명 | 설명 |
| ---- | ---- | ---- |
| **config** | | |
| | PrincipalDetails | pring Security에서 사용자 인증과 권한 부여를 위한 사용자 정보를 담고 있는 클래스 |
| | SecurityConfig | Spring Security를 설정하는 클래스 |
| **constant** | | |
| | SeatCordinates | 좌석정보를 담는 클래스 |
| | TotalPrice | 매출현황 값을 담을 클래스 |
| | UserRole | 사용자의 권한 정의|
| **controller** | | |
| | AdminController | 관리자 View 요청처리 및 서비스 호출, View 선택|
| | BoardController | 게시판 View 요청처리 및 서비스 호출, View 선택|
| | BookController | 예매 View 요청처리 및 서비스 호출, View 선택|
| | MainController | 메인화면 View 요청처리 및 서비스 호출, View 선택|
| | UserController | 사용자 View 요청처리 및 서비스 호출, View 선택|
| **dto** | | |
| | BoardDto | 게시판DTO 및 정적 팩토리 메서드|
| | LocationDto | 지점DTO 및 정적 팩토리 메서드|
| | MoviesDto | 영화DTO 및 정적 팩토리 메서드|
| | SeatDto | 좌석DTO 및 정적 팩토리 메서드|
| | TicketDto | 티켓DTO 및 정적 팩토리 메서드|
| | UserDto | 사용자DTO 및 정적 팩토리 메서드|
| **entity** | | |
| | Board | 게시판 객체 정의 및 데이터 저장|
| | Location | 지점 객체 정의 및 데이터 저장|
| | Movies | 영화 객체 정의 및 데이터 저장|
| | Seat | 좌석 객체 정의 및 데이터 저장|
| | Ticeket | 티켓 객체 정의 및 데이터 저장|
| | User | 사용자 객체 정의 및 데이터 저장|
| **repository** | | |
| | BoardRepository | 게시판 테이블 데이터 추상화 접근 및 검색 쿼리 기능|
| | LocationRepository | 지점 테이블 데이터 추상화 접근|
| | MoviesRepository | 영화 테이블 데이터 추상화 접근|
| | SeatRepository | 좌석 테이블 데이터 추상화 접근|
| | TicketRepository | 티켓 테이블 데이터 추상화 접근|
| | UserRepository | 사용자 테이블 데이터 추상화 접근|
| **service** | | 트랜잭션|
| | BoardService | 게시판 데이터 관리 및 처리|
| | BookService | 예매 데이터 관리 및 처리|
| | LocationService | 지점 데이터 관리 및 처리|
| | MoviesService | 영화 데이터 관리 및 처리|
| | UserDetailService | 로그인 DB값 검증|
| | UserService | 사용자 데이터 관리 및 처리|







---
# 🎬 화면구성
### 메인 화면
![image](https://github.com/user-attachments/assets/372af8dc-e6d1-49e8-bda0-a46cf643e84a)

---
### 영화 정보 화면
| 영화 목록 | 관람후기 | 지점목록 |
| ------------- | ------------- | ------------- |
| ![image](https://github.com/user-attachments/assets/c7f889ff-8f7d-4404-9ee1-e9b541dd51e6) | ![image](https://github.com/user-attachments/assets/0f76eac2-44e8-4ebd-8df8-ff96011895ff) | ![image](https://github.com/user-attachments/assets/8fa66cd4-e1eb-4f38-a486-e27a1e5cb837) |


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
| ![image](https://github.com/user-attachments/assets/31ddff12-a03f-4686-b42e-53079fb5fef2) | ![image](https://github.com/user-attachments/assets/04432eb5-c3bb-47ce-b886-628c8fffa13b) | ![image](https://github.com/user-attachments/assets/05d6e3e7-f160-4c6e-9022-bbc86d9d9b88) |




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
![image](https://github.com/user-attachments/assets/b6d5fbd1-87c1-4341-b40d-23cfded6a799)

---
# ✍ 느낀점
첫 프로젝트를 진행하면서 데이터베이스 설계를 처음으로 직접 경험했는데, 개발 과정에서 차질이 없도록 사전에 충분히 설계하는 것이 얼마나 중요한지 깨달았습니다. 소규모 프로젝트였음에도 불구하고, 테이블 구조와 데이터 흐름을 미리 명확하게 잡아두지 않으면 이후 단계에서 문제가 발생할 수 있다는 점을 체감했습니다. 특히, 이번 경험을 통해 프로젝트 설계가 개발의 성공을 좌우하는 핵심 단계라는 것을 확실히 이해하게 되었습니다.

또한, 연관관계 매핑 오류를 해결하는 과정에서 데이터베이스의 기본 개념과 관계 설정에 대해 깊이 이해하게 되었습니다. 오류를 해결하기 위해 다양한 접근 방식을 시도하며 학습한 점이 많았고, 이를 통해 데이터베이스 설계와 최적화에 대한 자신감을 얻었습니다. 문제를 해결하면서 성장할 수 있는 좋은 기회가 되었습니다.

아쉬웠던 점은 Ajax를 프로젝트에 도입하지 못한 것입니다. 페이지를 리로드하지 않고 비동기적으로 데이터를 처리할 수 있었더라면 사용자 경험이 크게 향상되었을 텐데, 그 점이 많이 아쉽습니다. 앞으로는 Ajax를 적극 활용해 더 효율적인 사용자 인터페이스를 구현하는 것을 목표로 하고 있으며, 이를 위해 현재 추가적으로 학습하고 있습니다.


#### [맨위로](#-프로젝트명)


