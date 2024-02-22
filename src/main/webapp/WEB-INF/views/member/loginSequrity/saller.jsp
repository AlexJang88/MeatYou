<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>




    <h1>업체 사업자분들~환영합니다 </h1>
    
    <form action="/member/customLogout" method="post">
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    	<input type="submit" value="로그아웃" />>
    </form>


<%@ include file="../../footer.jsp" %>