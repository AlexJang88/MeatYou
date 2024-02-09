<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<script>

function updateEmail() {
    const emaillist = document.getElementById('emaillist');
    const emailInput = document.getElementById('email');
    
    if (emaillist.value !== 'type') {
      const selectedDomain = emaillist.value;
      emailInput.value = emailInput.value.split('@')[0] + selectedDomain;
    }
  }
  
//휴대폰번호 유효성 검사 및 포맷팅
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

</head>
<body>

<h1>여기서 아이디 찾을것</h1> 
<form  method="post" action="/member/idfindPro">
	<table>
		<tr>
			<td>이름</td>
			<td><input type="text" name="m_name" required="required" placeholder="이름을 입력하시오" size="15"></td>
		</tr>
		
		<tr>
			 <td width="200">휴대폰번호</td>
    		 <td width="400"> 
			 <input type="text" name="phone" id="phoneInput" size="40" required="required" maxlength="13" placeholder="전화번호를 입력하시오" oninput="formatPhoneNumber(this)">
		    </td>
		</tr>
		
		
		<tr>
           <td  class="hiddenshow"><label for="email">이메일</label></td>              
           <td>
              <input type="text" id="email" class="inputText" name="email" size="14"  required="required"  placeholder="이메일을 입력하시오"/>
              <select class="box" id="emaillist" name="emaillist" onchange="updateEmail()">
	              <option value="type">직접 입력</option>
	              <option value="@naver.com">@naver.com</option>
	              <option value="@google.com">@google.com</option>
	              <option value="@hanmail.net">@hanmail.net</option>
	              <option value="@nate.com">@nate.com</option>
	              <option value="@kakao.com">@kakao.com</option>
              </select>
              <span id="emailError" style="color: red;"  class="error-message"></span>
            </td>           
        </tr>
          		  
        <tr>
			<td>
				<button type="button" class="btn btn-primary" id="mail-Check-Btn">인증번호 전송</button>
			</td>
			<td>
				<input class="form-control mail-check-input" required="required" placeholder="인증번호 6자리를 입력해주세요!" maxlength="6">
				<span id="mail-check-warn"></span>
			</td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-primary" id="success" value="이메일 인증확인">
			</td>
		</tr>

		<tr>
	      <td> 
	          <input type="submit" name="confirm" value="아이디 찾기" id="confirmButton" disabled > 
	          <input type="button" value="돌아가기" onclick="javascript:window.location='../main/main'">
	      </td>
	    </tr>
		
	</table>
</form>
</body>

<script>
		$('#mail-Check-Btn').click(function() { 
			var email = $('#email').val();// 이메일 주소값 얻어오기!
			console.log('완성된 이메일 : ' + email); // 이메일 오는지 확인
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'get',
				url : '/member/mailCheck?email='+email, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
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
	
		 window.onload = function() {
		        var checkValue = <%= request.getAttribute("check") %>;
		        if (checkValue == 0) {
		            alert("정보를 다시 확인하세요.");
		        }
		    };
		
</script>
</html>