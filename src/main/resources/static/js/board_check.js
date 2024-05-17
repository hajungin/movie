function check() {
    // 작성자
    var username = document.getElementById("username").value.trim();
    if (username.length === 0) {
        alert("작성자를 입력하세요.");
        return false;
    }

    // 영화제목
    var movieNo = document.getElementById("movieNo").value.trim();
    if (movieNo.length === 0) {
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
    alert('입력이 완료되었습니다');
    // 모든 유효성 검사 통과 후 form 제출
    document.getElementById("frm").submit();

    // 제출을 위해 false 반환
    return false;
}

function check1() {
    var title = document.getElementById('title').value;
    var content = document.getElementById('content').value;

    if (title.trim() === '' || content.trim() === '') {
        alert('제목과 내용은 필수 입력 항목입니다.');
        return false;
    }
    alert('수정이 완료되었습니다');
    document.getElementById("frm").submit();
    return true;
}


