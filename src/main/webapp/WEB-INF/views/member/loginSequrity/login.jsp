<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>


<h1> 로그인 하시나요 ~ </h1>



    <form action="/login" method="post">
		    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />                      
			<input type="text" name="username" /> <br />
			<input type="password" name="password" /> <br />
			<input type="submit" value="로그인" />
			<div class="checkbox">
			<label> <input name="remember-me" type="checkbox">자동로그인
			</label>
			</div>
  		<p class="mt-3 mb-3"> -------------------또는-------------------- </p>
	<div>
		<a id="kakao-login-btn" href="javascript:loginWithKakao()">
		  <img src="https://developers.kakao.com/tool/resource/static/img/button/login/full/ko/kakao_login_medium_narrow.png" width="150" alt="카카오 로그인 버튼" />
		</a>
		<p id="token-result"></p>
		
		<script>
		  function loginWithKakao() {
		    Kakao.Auth.authorize({
		      redirectUri: 'http://localhost:8080/login/oauth2/code/kakao',
		     
		    });
		  }
		  function requestUserInfo() {
			  Kakao.API.request({
				  url: '/v2/user/me',
				})
				  .then(function(response) {
				    console.log(response);
				  })
				  .catch(function(error) {
				    console.log(error);
				  });
			  }
		
		</script>
	</div>
	<p class="mt-5 mb-3 text-muted">loginForm.jsp</p>
</form>


<%@ include file="../../footer.jsp" %>