<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>


   
   <h1> /member </h1>
    <h1>로그인된 사용자 이용 가능</h1>
    <form action="/member/customLogout" method="post">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    	<input type="submit" value="로그아웃" />
    </form>


<%@ include file="../../footer.jsp" %>