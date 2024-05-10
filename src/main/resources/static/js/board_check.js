function check() {
    // 작성자
    var userId = document.getElementById("userId").value.trim();
    if (userId.length === 0) {
        alert("작성자를 입력하세요.");
        return false;
    }

    // 영화제목
    var movieTitle = document.getElementById("movieTitle").value.trim();
    if (movieTitle.length === 0) {
        alert("영화를 선택하세요.");
        return false;
    }

    // 제목
    var title = document.getElementById("title").value.trim();
    if (title.length === 0) {
        alert("제목을 입력하세요.");
        return false;
    }

    // 내용
    var content = document.getElementById("content").value.trim();
    if (content.length === 0) {
        alert("내용을 입력하세요.");
        return false;
    }

    // 별점
    var goodPoint = document.getElementById("goodPoint").value.trim();
    if (goodPoint.length === 0) {
        alert("별점을 선택하세요.");
        return false;
    }

    // 모든 유효성 검사 통과 후 form 제출
    document.getElementById("frm").submit();

    // 제출을 위해 false 반환
    return false;
}
