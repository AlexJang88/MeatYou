<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
   
<%@ include file="../../header.jsp" %>

<body>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

    <sec:authorize access="isAuthenticated()">
           id: ${dto.m_id} <br />
        name: ${dto.m_name} <br />
    </sec:authorize>

    <p>
        <a href="../member/modifyForm">정보수정</a>
        <a href="../member/shoppingCartForm">장바구니</a>
        <a href="/member/deleteForm">탈퇴</a>
    </p>
</body>

<%@ include file="../../footer.jsp" %>
