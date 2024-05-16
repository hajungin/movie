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

function checkUserId() {
    var userId = document.getElementById("userId").value;

    // AJAX 요청을 생성합니다.
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/user/signup", true); // 서버의 실제 URL로 변경해야 합니다.
    xhr.setRequestHeader("Content-Type", "application/json");

    // 요청이 완료되었을 때 실행되는 콜백 함수
    xhr.onload = function() {
        if (xhr.status == 200) {
            var response = JSON.parse(xhr.responseText);
            if (response.duplicate) {
                alert("이미 사용 중인 ID입니다.");
            } else {
                alert("사용 가능한 ID입니다.");
            }
        } else {
            alert("서버 오류가 발생하였습니다."); // 서버 오류 메시지 표시
        }
    };

    // 요청이 실패한 경우에 대한 처리
    xhr.onerror = function() {
        alert("네트워크 오류가 발생하였습니다."); // 네트워크 오류 메시지 표시
    };

    // 요청을 보냅니다.
    xhr.send(JSON.stringify({ userId: userId }));
}


$(function(){



    $("#checkId").click(function(){

        let member_id = $("#member_id").val();

        $.ajax({
            type:'post', //post 형식으로 controller 에 보내기위함!!
            url:"/spring/checkId.do", // 컨트롤러로 가는 mapping 입력
            data: {"member_id":member_id}, // 원하는 값을 중복확인하기위해서  JSON 형태로 DATA 전송
            success: function(data){
                if(data == "N"){ // 만약 성공할시
                    result = "사용 가능한 아이디입니다.";
                    $("#result_checkId").html(result).css("color", "green");
                    $("#member_pw").trigger("focus");

             }else{ // 만약 실패할시
                 result="이미 사용중인 아이디입니다.";
                     $("#result_checkId").html(result).css("color","red");
                     $("#member_id").val("").trigger("focus");
             }

         },
            error : function(error){alert(error);}
        });

    });

});





