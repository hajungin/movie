function validateForm() {
        var password = document.getElementById("password1").value;
        var hiddenPassword = document.getElementById("hiddenPassword").value;
        var money = document.getElementById("money").value;
    if (money === "0") {
        alert("충전할 금액을 선택해주세요.");
        return false;
    }
    if (password !== hiddenPassword) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
        }
    return true;
}

function showConfirmation() {
    if (validateForm()) {
            alert("충전이 완료되었습니다!");
    }
}