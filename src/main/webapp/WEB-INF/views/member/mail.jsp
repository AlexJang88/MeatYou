<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>


<html>
<head>
    <title>메일인증 성공!</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    <link href="/resources/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="../header.jsp" %>
	<div class="form-signin" style="display: flex; flex-direction: column;">
	    <c:if test="${result eq 1}">
	    	<c:if test="${site eq 1}">
		        <h1 class="h4 mb-4">고객님의 아이디는<br /> ${username}입니다.</h1>
		        <h1 class="h5 mb-5">비밀번호 변경을 원하신다면<br /> 아래를 눌러주세요.</h1><br />
		        <div style="display: flex; float: left; margin-left: auto; margin-right: auto; text-align: center; ">
		        <form action="/member/renamePass" method="post">
		        	<input type="hidden" name="username" value="${username}" />
		        	<input type="submit" class="btn btn-link" value="변경하기" />
		        	<a href="/main/main" class="link-body-emphasis link-underline-opacity-25">지금은 됐어요</a>
		        </form>
		        </div>
		        <p class="mt-5 mb-3 text-muted">checkSuccess.jsp</p>
		    </c:if>
		    <c:if test="${site eq 2}">
		        <h1 class="h4 mb-4">고객님은 <b>카카오 로그인</b><br /> 계정입니다.</h1>
		        <h1 class="h5 mb-5">로그인 화면에서<br /> 카카오 로그인을 눌러주세요.</h1><br />
		        <a href="/member/form" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">로그인</a>
		        <a href="/main/main" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">지금은 됐어요</a>
		        <p class="mt-5 mb-3 text-muted">checkSuccess.jsp</p>
		    </c:if>
	    </c:if>
	    <c:if test="${result eq 0}">
	        <h1 class="h4 mb-4">입력하신 이메일로 조회된 정보가 없습니다.</h1>
	        <h1 class="h5 mb-5">회원가입을 원하신다면<br /> 아래를 눌러주세요.</h1><br />
	        <a href="/member/regForm" class="link-primary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover">회원가입</a>
	        <a href="/main/main" class="link-body-emphasis link-offset-2 link-underline-opacity-25 link-underline-opacity-75-hover">지금은 됐어요</a>
	        <p class="mt-5 mb-3 text-muted">checkSuccess.jsp</p>
	    </c:if>
	</div>
<%@ include file="../footer.jsp" %>

</body>
</html>