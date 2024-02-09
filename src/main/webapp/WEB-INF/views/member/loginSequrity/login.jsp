<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>




<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.6.0/kakao.min.js" integrity="sha384-6MFdIr0zOira1CHQkedUqJVql0YtcZA1P0nbPrQYJXVJZUkTk/oX4U9GhUIs3/z8" crossorigin="anonymous"></script>
<script>Kakao.init('995dae66ae429982c698a333c5a4fd80'); Kakao.isInitialized();</script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <form action="/login" method="post">
		    <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />                      
			<input type="text" name="username" /> <br />
			<input type="password" name="password" /> <br />
			<input type="submit" value="로그인" />
			<div class="checkbox">
			<label> <input name="remember-me" type="checkbox">자동로그인
			</label>
			</div>
			</form>
			<ul>
	<li onclick="kakaoLogin();">
      <a href="javascript:void(0)">
          <span>카카오 로그인</span>
      </a>
	</li>
	<li onclick="kakaoLogout();">
      <a href="javascript:void(0)">
          <span>카카오 로그아웃</span>
      </a>
	</li>
	
	<li>
      <a href="/member/idfind">
          <span>아이디 찾기</span>
      </a>
	</li>
	<li>
      <a href="/member/pwfind">
          <span>비밀번호 찾기</span>
      </a>
	</li>
	
	
</ul>

<script>
Kakao.init('8890a67c089173194979845f9389950d'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
      success: function (response) {
        Kakao.API.request({
          url: '/v2/main/main',
          success: function (response) {
        	  console.log(response)
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
//카카오로그아웃  
function kakaoLogout() {
    if (Kakao.Auth.getAccessToken()) {
      Kakao.API.request({
        url: '/v1/main/main',
        success: function (response) {
        	console.log(response)
        },
        fail: function (error) {
          console.log(error)
        },
      })
      Kakao.Auth.setAccessToken(undefined)
    }
  }  
</script>
  	<!-- 	<p class="mt-3 mb-3"> -------------------또는-------------------- </p>
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
</form>
 -->

<%@ include file="../../footer.jsp" %>