function check() {
    var movieTitle = document.getElementById("movieTitle").value.trim();
    var movieDate = document.getElementById("movieDate").value.trim();
    var movieRate = document.getElementById("movieRate").value;



//    if (movieTitle.length == 0) {
//        alert("아이디가 입력되지 않았습니다.");
//        document.getElementById("movieTitle").focus();
//        return false;
//    }
    if (movieTitle.length == 0) {
        alert("영화 제목이 입력되지 않았습니다.");
        document.getElementById("movieTitle").focus();
        return false;
    }
    if (movieDate.length == 0) {
        alert("영화 개봉일이 입력되지 않았습니다.");
        document.getElementById("movieDate").focus();
        return false;
    }
    if (movieRate.length == 0) {
            alert("영화 등급이 입력되지 않았습니다.");
            document.getElementById("movieRate").focus();
            return false;
    }
    alert("입력이 완료되었습니다.");
    document.getElementById("frm").submit();
    return true;
}
