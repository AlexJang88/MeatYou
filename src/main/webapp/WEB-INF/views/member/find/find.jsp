<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<head>
	<title>아이디/비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	
	<link href="/resources/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
	<%@ include file="../../header.jsp" %>
	<form class="form-signin" action="/member/checkSuccess" method="POST">
		<div class="form-group email-form">
		<h1 class="h3 mb-3 font-weight-normal">아이디/비밀번호 찾기</h1>
		<label for="email">이메일</label>
			<div class="input-group">
				<input type="text" class="form-control" name="userEmail1" id="userEmail1" placeholder="이메일" >
				<select class="form-control" name="userEmail2" id="userEmail2" >
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
					<option>@hanmail.com</option>
				</select>
			</div>   
			<div class="input-group-addon">
				<button type="button" class="btn btn-primary" id="mail-Check-Btn">본인인증</button>
			</div><br />
			<div class="mail-check-box">
				<input class="form-control mail-check-input" placeholder="인증번호 6자리를 입력해주세요!" disabled="disabled" maxlength="6">
			</div>
				<span id="mail-check-warn"></span>
			</div>
				<input type="submit" id="checkS" class="btn btn-primary" disabled="disabled" value="인증 완료" />
			</form>
			
		<script>
		$('#mail-Check-Btn').click(function() {
			const eamil = $('#userEmail1').val() + $('#userEmail2').val(); // 이메일 주소값 얻어오기!
			console.log('완성된 이메일 : ' + eamil); // 이메일 오는지 확인
			const checkInput = $('.mail-check-input') // 인증번호 입력하는곳 
			
			$.ajax({
				type : 'get',
				url : '/member/mailCheck?email='+eamil, // GET방식이라 Url 뒤에 email을 뭍힐수있다.
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
			const eamil = $('#userEmail1').val() + $('#userEmail2').val();
			const inputCode = $(this).val();
			const $resultMsg = $('#mail-check-warn');
			
			if(inputCode === code){
				$resultMsg.html('인증번호가 일치합니다.');
				$resultMsg.css('color','green');
				$('#mail-Check-Btn').attr('disabled',true);
				$('#userEamil1').attr('readonly',true);
				$('#userEamil2').attr('readonly',true);
				$('#userEmail2').attr('onFocus', 'this.initialSelect = this.selectedIndex');
		        $('#userEmail2').attr('onChange', 'this.selectedIndex = this.initialSelect');
		        $('#checkS').attr('disabled',false);
		        
			}else{
				$resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
				$resultMsg.css('color','red');
			}
		});
		
		</script>
	<%@ include file="../../footer.jsp" %>
</body>