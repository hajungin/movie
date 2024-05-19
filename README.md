# 🍿 Ticketing
영화 예매 사이트 구현

---


# 📆 프로젝트 기간
2024.05.03~2024.05.20

---


# 🧑‍🤝‍🧑 맴버구성
<div style="display: flex; gap: 10px;">

  <div style="border: 1px solid #ddd; border-radius: 8px; padding: 16px; width: 30%;">
    <h3>팀원 1 : 하정인</h3>
    <ul>
      <li>메인페이지 구현</li>
      <li>영화 및 지점 목록 관리</li>
      <li>관리자 페이지(CRUD) 관리</li>
      <li>회원가입</li>
      <li>PPT 제작</li>
    </ul>
  </div>

  <div style="border: 1px solid #ddd; border-radius: 8px; padding: 16px; width: 30%;">
    <h3>팀원 2 : 이정민</h3>
    <ul>
      <li>좌석 기능 구현</li>
      <li>티켓 예약 관리</li>
      <li>로그인</li>
      <li>마이 페이지(CRUD)</li>
      <li>예매 내역</li>
      <li>발표</li>
    </ul>
  </div>

  <div style="border: 1px solid #ddd; border-radius: 8px; padding: 16px; width: 30%;">
    <h3>팀원 3 : 이호중</h3>
    <ul>
      <li>게시판 페이징 처리</li>
      <li>게시판 (CRUD) 검색 기능</li>
      <li>영화 평점</li>
      <li>PPT 제작</li>
    </ul>
  </div>

</div>


---


# 🛠 기술 스택
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
### 메인화면
<head>
    <meta charset="UTF-8">
    <title>영화 메인 페이지</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body style="background-image: url('https://search.pstatic.net/sunny/?src=https%3A%2F%2Fimg.freepik.com%2Ffree-vector%2Fcinema-realistic-poster-with-illuminated-bucket-of-popcorn-drink-3d-glasses-reel-tickets-on-blue-background-with-tapes-vector-illustration_1284-77070.jpg&type=sc960_832');
            background-size: cover;
            background-repeat: no-repeat;
            background-position: center;">

<div class="content" layout:fragment="content">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" th:href="@{/cinema}">절찬 상영 중</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <a class="nav-link" th:href="@{cinema/movie}">영화 목록</a>
                </li>
            </ul>
        </div>
    </nav>
    <br>

    <div class="row">
        <div class="col-lg-4 col-md-6 mb-4" th:each="movie : ${movie}" th:object="${movie}">
            <div class="card h-100 bg-light"> <!-- 여기에서 bg-light 클래스 추가 -->
                <img th:src="@{*{img}}" class="card-img-top" alt="영화 포스터" style="height: 350px; object-fit: cover;">
                <div class="card-body">
                    <h5 class="card-title" th:text="*{movieTitle}"></h5>
                    <p class="card-text" th:text="'개봉일 : ' + *{movieDate}"></p>
                    <p class="card-text" th:text="'관람 등급 : ' + *{movieRate}"></p>
                    <p class="card-text" th:text="'평점 : ' + *{goodPointAvg}"></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
![image](https://github.com/hajungin/movie/assets/162389696/74da6146-95d0-4ae5-9437-349cdba6131f)


  
# 🧩 ERD
![ERD_1](https://github.com/hajungin/movie/blob/master/cinemaERD_1.png)


![ERD_2](https://github.com/hajungin/movie/blob/master/cinemaERD_2.png)


![ERD_3](https://github.com/hajungin/movie/blob/master/cinemaERD_3.png)

