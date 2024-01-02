<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h1>로그인 화면</h1>
<form method="post" action="/login">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	아이디 : <input type="text" name="username" /> <br />
	비밀번호 : <input type="password" name="password" /> <br />
	
	<input type="submit" value="로그인" />자동로그인<input type="checkbox" name="remember-me" />
</form>
    