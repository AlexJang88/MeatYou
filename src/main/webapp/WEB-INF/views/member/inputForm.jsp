<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<style>
    /* Form container styles */
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 30px;
            border: 1px solid #ddd;
            border-radius: 10px;
            margin-top: 50px;
            background-color: #fff;
        }

        /* Form title styles */
        .form-title {
            text-align: center;
            font-size: 24px;
            margin-bottom: 20px;
            color: #333;
        }

        /* Input field styles */
        .form-input {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f8f9fa;
            color: #495057;
        }

        /* Button styles */
        .form-button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: darkgray; /* Light blue */
            color: #fff;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .form-button:hover {
                    background-color: #6c757d; /* Grayish color */
        }

        .form-button-secondary {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            background-color: #6c757d; /* Grayish color */
            color: #fff;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .form-button-secondary:hover {
            background-color: #495057; /* Slightly darker gray on hover */
        }

        /* Error message styles */
        .error-message {
            color: red;
            margin-top: 5px;
        }

        /* Success message styles */
        .success-message {
            color: green;
            margin-top: 5px;
        }

        /* Dropdown styles */
        .form-dropdown {
            width: 48%;
            padding: 10px;
            margin: 10px 0;
            box-sizing: border-box;
            display: inline-block;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f8f9fa;
            color: #495057;
        }
.m_id_ok{
color:#008000;
display: none;
}

.m_id_already{
color:red; 
display: none;
}
.email_ok{
color:#008000;
display: none;
}
.email_already{
color:red; 
display: none;
}
</style>
<script>

function checkId(){
    var m_id = $('#m_id').val(); //id값이 "id"인 입력란의 값을 저장
    $.ajax({
        url:'./idCheck', //Controller에서 요청 받을 주소
        type:'post', //POST 방식으로 전달
        data:{m_id:m_id},
        success:function(check){ //컨트롤러에서 넘어온 check값을 받는다 
            if(check == 0){ //check가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                $('.m_id_ok').css("display","inline-block"); 
                $('.m_id_already').css("display", "none");
             //   $('.confirm_ok').css("display","inline-block"); 
             //   $('.cofrim_already').css("display", "none");
                
            } else { // check가 1일 경우 -> 이미 존재하는 아이디
                $('.m_id_already').css("display","inline-block");
                $('.m_id_ok').css("display", "none");
              //  $('.confirm_ok').css("display","inline-block"); 
              //  $('.cofrim_already').css("display", "none");
                
                // 이미 사용 중인 아이디일 때 회원가입 버튼 숨김
              //  $('#confirmButton').prop('disabled', true);
               // alert("아이디를 다시 입력해주세요");
               // $('#m_id').val('');
            }
        },
        error:function(){
            alert("에러입니다");
        }
    });
    };
    function checkEmail(){
        var email = $('#email').val(); //id값이 "email"인 입력란의 값을 저장
        $.ajax({
            url:'./eMailCheck', //Controller에서 요청 받을 주소
            type:'post', //POST 방식으로 전달
            data:{email:email},
            success:function(check){ //컨트롤러에서 넘어온 check값을 받는다 
                if(check == 0){ //check가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
                    $('.email_ok').css("display","inline-block"); 
                    $('.email_already').css("display", "none");
                } else { // check가 1일 경우 -> 이미 존재하는 아이디
                    $('.email_already').css("display","inline-block");
                    $('.email_ok').css("display", "none");
                    // 이미 사용 중인 이메일일 때 회원가입 버튼 숨김
                    $('#confirmButton').prop('disabled', true);
                   // alert("아이디를 다시 입력해주세요");
                   // $('#m_id').val('');
                }
            },
            error:function(){
                alert("에러입니다");
            }
        });
        };
document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('addressButton').addEventListener('click', function () {
        new daum.Postcode({
            oncomplete: function (data) {
                var fullAddress = data.address;
                document.getElementById('address').value = fullAddress;
            }
        }).open();
    });
});

function combineAndSubmit() {
    const passwd = document.getElementById('passwd').value;
    const passwd2 = document.getElementById('passwd2').value;

    if (passwd === "") {
        alert("비밀번호를 입력해주세요.");
        return false; // 폼 제출 방지
    }

    if (passwd !== passwd2) {
        alert("비밀번호가 일치하지 않습니다. 확인해주세요.");
        return false; // 폼 제출 방지
    }
 
}

function updateEmail() {
    const emaillist = document.getElementById('emaillist');
    const emailInput = document.getElementById('email');
    const emailDomainInput = document.getElementById('emailDomain');

    if (emaillist.value === 'type') {
        // 직접 입력한 경우
        const selectedDomain = emailDomainInput.value;
        emailInput.value = emailInput.value.split('@')[0] + selectedDomain;
    } else {
        // 옵션에서 선택한 경우
        emailInput.value = emailInput.value.split('@')[0] + emaillist.value;
        
        // 선택한 도메인으로 이메일 중복 체크
        checkEmail();
    }
}

// 휴대폰번호 유효성 검사 및 포맷팅
function formatPhoneNumber(input) {
    var cursorPosition = input.selectionStart; // Save cursor position
    var phoneNumber = input.value.replace(/\D/g, ''); // 숫자만 남기기

    // 휴대폰 번호에 하이픈 추가
    if (phoneNumber.length >= 3 && phoneNumber.length <= 6) {
        phoneNumber = phoneNumber.replace(/(\d{3})(\d{0,4})/, '$1-$2');
    } else if (phoneNumber.length >= 7) {
        phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{0,4})/, '$1-$2-$3');
    }

    // 휴대폰 번호 길이 제한 (최대 13자)
    if (phoneNumber.length > 13) {
        phoneNumber = phoneNumber.substring(0, 13);
    }

    input.value = phoneNumber;

    // Restore cursor position
    input.setSelectionRange(cursorPosition, cursorPosition);
}
</script>

<body>

<div class="form-container">
    <form method="post" action="/member/inputPro" name="userinput" >
        <div class="form-title">회원가입</div>

<!--         <label for="m_id">아이디 입력</label> -->
        <input type="text" class="form-input" name="m_id" id="m_id" required="required" placeholder="아이디를 입력해주세요" oninput="checkId()">
        <span class="m_id_ok">사용 가능한 아이디입니다.</span>
        <span class="m_id_already">이미 사용중인 아이디입니다</span>
<!-- 
        <label for="passwd">비밀번호</label> -->
        <input type="password" class="form-input" name="passwd" id="passwd" size="15" maxlength="12"  placeholder="비밀번호를 입력해주세요" required="required">

<!--         <label for="passwd2">비밀번호 확인</label> -->
        <input type="password" class="form-input" size="15" id="passwd2" maxlength="12" placeholder="비밀번호를 입력해주세요" required="required">
 		
 		
        <div class="form-title" style="margin-top:30px;">개인정보 입력</div>

        <label for="m_name">사용자 이름</label>
        <input type="text" class="form-input" name="m_name"  placeholder="이름을 입력해주세요" size="15" maxlength="10" required="required">

        <label for="birth">생년월일</label>
        <div class="form-group">
            <input type="date" class="form-input" name="birth" id="birth" required="required">
        </div>

        <label for="email">이메일</label><br/>
        <input type="text" class="form-input" id="email" name="email" size="8" style="width:50%"  required="required" placeholder="이메일을 입력하시오" oninput="checkEmail()">
        <select class="form-dropdown" id="emaillist" name="emaillist" onchange="updateEmail()">
            <option value="type">직접 입력</option>
            <option value="@naver.com">@naver.com</option>
            <option value="@gmail.com">@gmail.com</option>
            <option value="@hanmail.net">@hanmail.net</option>
            <option value="@nate.com">@nate.com</option>
            <option value="@kakao.com">@kakao.com</option>
        </select><br/>
        <span id="emailError" class="error-message"></span>
        <span class="email_ok">사용 가능한 이메일입니다.</span>
        <span class="email_already">이미 사용중인 이메일입니다</span>
        <div class="form-group">
            <input class="form-input mail-check-input"  style="width:50%" required="required" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
            <span id="mail-check-warn"></span>
          <button type="button" class="form-button" style="width:22%; font-size: 12px;" id="mail-Check-Btn">인증번호 전송</button>
<input type="button" class="form-button" id="success" style="width:22%; font-size: 12px;" value="이메일 인증확인" style="text-align: center;">     
 </div>
        <label for="address" style="maring-top:20px;">주소 와 </label> 
        <label for="deltailadress">상세주소를 입력해주세요  </label>   <br/>
        <div class="form-group" style="text-align: center; maring-top:20px; ">
        <button type="button" id="addressButton" class="form-button"   style="background-color: white; width: 50%;" >
        <input type="text" class="form-input" id="address"  name="m_addr1" placeholder="주소" style="width: 100%;">
        </button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <input type="text" class="form-input" name="m_addr2" style="width: 48%;" placeholder="상세주소">
        </div>
 
 
   <div class="form-group" style="maring-top:10px;">
        <label for="telep">휴대폰 번호를 입력해주세요</label>  <br/>
        <select name="telep" id="telep" class="form-dropdown" style="width: 30%;">
            <option value="SKT">SKT</option>
            <option value="KT">KT</option>
            <option value="LGU+">LGU+</option>
        </select> 
<!--        <label for="phoneInput">듀대폰 번호를 입력해주세요 </label>    -->
        <input type="text"  style="width: 65%;    margin-left:15px; " placeholder="휴대폰번호를 입력해주세요"  class="form-input" name="phone" id="phoneInput" size="40" maxlength="13" oninput="formatPhoneNumber(this)">
</div>
<br/>

        <div class="form-group">
            <input type="button" value="가입안함" onclick="javascript:window.location='../main/main'" class="form-button-secondary">
</div> 
  <div class="form-group">
            <input type="submit" name="confirm" value="회원가입" class="form-button" id="confirmButton" >
    
</div>
    </form>
</div>

</body>

<script>
function validateForm() {
    var m_id = $('#m_id').val().trim();
    var passwd = $('#passwd').val().trim();
    var passwd2 = $('#passwd2').val().trim();
    var email = $('#email').val().trim();

    // Check if any field is empty
    if (m_id === "" || passwd === "" || passwd2 === "" || email === "") {
        alert("모든 필수 입력란을 채워주세요."); // "Please fill in all required fields."
        return false;
    }

    // Check if passwords match
    if (passwd !== passwd2) {
        alert("비밀번호가 일치하지 않습니다. 확인해주세요."); // "Passwords do not match. Please check again."
        return false;
    }

    // Additional validation checks can be added here (e.g., email format, password strength)

    // If all checks pass
    return true;
}

// Step 2: Bind the validation to form submission
document.forms['userinput'].onsubmit = function() {
    return validateForm();
};
$('#mail-Check-Btn').click(function() {
    var email = $('#email').val();// 이메일 주소값 얻어오기!
    console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
    const checkInput = $('.mail-check-input') // 인증번호 입력하는곳

    $.ajax({
        type : 'get',
        url : '/member/joinmailCheck?email='+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
        success : function (data) {
            console.log("data : " +  data);
            checkInput.attr('disabled',false);
            code =data;
            alert('인증번호가 전송되었습니다.')
        }
    }); // end ajax
}); // end send eamil

// 인증번호 비교
// blur -> focus가 벗어나는 경우 발생
$('.mail-check-input').blur(function () {
    const eamil = $('email').val() + $('#emaillist').val();
    const inputCode = $(this).val();
    const $resultMsg = $('#mail-check-warn');

    if(inputCode === code){
        $resultMsg.html('인증번호가 일치합니다.');
        $resultMsg.css('color','green');
        $('#mail-Check-Btn').attr('disabled',true);
        $('#email').attr('readonly',true);
        $('#emaillist').attr('readonly',true);
        $('#emaillist').attr('onFocus', 'this.initialSelect = this.selectedIndex');
        $('#emaillist').attr('onChange', 'this.selectedIndex = this.initialSelect');
        $('#checkS').attr('disabled',false);
        $('#success').prop('disabled', false); // 버튼 활성화

    }else{
        $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
        $resultMsg.css('color','red');
        $('#success').prop('disabled', true); // 버튼 비활성화
    }
});

$('#success').click(function () {
    const inputCode = $('.mail-check-input').val(); // 입력된 인증번호 가져오기

    // 서버에서 받은 인증번호와 입력된 인증번호 비교
    if (inputCode === code) {
        alert('인증이 완료되었습니다.');
        $('#confirmButton').prop('disabled', false); // 회원가입 버튼 활성화
    } else {
        alert('인증번호가 일치하지 않습니다. 다시 확인해주세요.');
    }
});
</script>

</html>

<%@ include file="../footer.jsp" %>
