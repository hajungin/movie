//function check() {
//    // 충전 금액 확인
//    var money = document.getElementById('money').value;
//    var password = document.getElementById('password').value;
//    var hiddenPassword = document.getElementById('hiddenPassword').value;
//    if (money == "0") {
//        alert('충전할 금액을 선택해주세요.');
//        return false;
//    }
//
//    // 비밀번호 확인
//
//    if (password !== hiddenPassword) {
//        alert('비밀번호가 일치하지 않습니다.');
//        return false;
//    }
//
//    // 폼 제출 후 충전 완료 메시지 표시
//    alert('충전이 완료되었습니다.');
//    document.getElementById('f').submit();
//    return true;
//}
$(function() {
            $('#f').submit(function("click") {
                // 충전 금액 확인
                var money = $('#money').val();
                var password = $('#password').val();
                var hiddenPassword = $('#hiddenPassword').val();

                if (money == "0") {
                    alert('충전할 금액을 선택해주세요.');
                    event.preventDefault();
                    return false;
                }

                // 비밀번호 확인
                if (password !== hiddenPassword) {
                    alert('비밀번호가 일치하지 않습니다.');
                    event.preventDefault();
                    return false;
                }

                // 폼 제출 후 충전 완료 메시지 표시
                alert('충전이 완료되었습니다.');
                // event.preventDefault()를 호출하지 않으므로 폼이 제출됩니다.
            });
        });


