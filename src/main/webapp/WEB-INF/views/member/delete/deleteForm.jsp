<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
	<title>회원탈퇴</title>
</head>

<BODY onload="begin()" >
<form name="myform" action="/member/deletePro" method="post" onSubmit="return deleteCheckIt()">
<TABLE border=1 align="center" >
  
  <TR height="30">
    <TD align="middle">
	  <font size="+1" ><b>회원 탈퇴</b></font></TD></TR>
  
  <TR height="30">
    <TD width="110" align=center>비밀번호</TD>
    <TD width="150" align=center>
      <INPUT type="password" name="passwd"  size="15" maxlength="12"></TD></TR>
      <INPUT type="hidden" name="m_id"  size="15" maxlength="12"></TD></TR>
  <TR height="30">
    <TD  >
      <INPUT type=submit value="회원탈퇴"> 
      <input type="button" value="취  소" onclick="javascript:window.location='/main/main'"></TD></TR>
          	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</TABLE>
</form>
</BODY>
</HTML>


<%@ include file="../../footer.jsp" %>

