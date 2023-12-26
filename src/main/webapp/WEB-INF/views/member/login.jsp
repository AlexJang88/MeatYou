<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>


<h1> 로그인 하시나요 ~ </h1>



    <form action="/login" method="post">
		    	<input type="hidden" name="${_csrf.parameterName}" 
								 value="${_csrf.token}" />                      
			<input type="text" name="username" /> <br />
			<input type="password" name="password" /> <br />
			<input type="submit" value="로그인" />
    </form>


<%@ include file="../footer.jsp" %>