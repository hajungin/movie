function check() {
    var userId = document.getElementById("userId").value.trim();
    var userName = document.getElementById("userName").value.trim();
    var password1 = document.getElementById("password1").value;
    var password2 = document.getElementById("password2").value;
    var birth = document.getElementById("birth").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var email = document.getElementById("email").value.trim();

    if (userId.length == 0) {
        alert("아이디가 입력되지 않았습니다.");
        document.getElementById("userId").focus();
        return false;
    }
    if (userName.length == 0) {
        alert("이름이 입력되지 않았습니다.");
        document.getElementById("userName").focus();
        return false;
    }
    if (password1.length == 0) {
        alert("비밀번호가 입력되지 않았습니다.");
        document.getElementById("password1").focus();
        return false;
    }
    if (password2.length == 0) {
        alert("비밀번호 확인이 입력되지 않았습니다.");
        document.getElementById("password2").focus();
        return false;
    }
    if (password1 !== password2) {
        alert("비밀번호가 일치하지 않습니다.");
        document.getElementById("password2").focus();
        return false;
    }
    if (birth.length == 0) {
        alert("생일이 입력되지 않았습니다.");
        document.getElementById("birth").focus();
        return false;
    }
    if (phone.length == 0) {
        alert("전화번호가 입력되지 않았습니다.");
        document.getElementById("phone").focus();
        return false;
    }
    if (!/^\d+$/.test(phone)) {
        alert("전화번호는 숫자만 입력해주세요.");
        document.getElementById("phone").focus();
        return false;
    }
    if (email.length == 0) {
        alert("이메일이 입력되지 않았습니다.");
        document.getElementById("email").focus();
        return false;
    }
    if (!/^.+@.+\..+$/.test(email)) {
        alert("올바른 이메일 형식이 아닙니다.");
        document.getElementById("email").focus();
        return false;
    }
    alert("입력이 완료되었습니다.");
    document.getElementById("frm").submit();
    return true;
}

function res(){
    alert("처음부터 다시 입력합니다.")
    document.getElementById("frm").reset();
    document.getElementById("userId").focus();
}
