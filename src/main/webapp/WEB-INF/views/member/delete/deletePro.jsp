<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
<title>회원탈퇴</title>
</head>
<body >

<sec:authorize access="isAuthenticated()">
<form method="post" action="/main/main" name="userinput" >
    	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
  <tr bgcolor="${title_c}"> 
    <td height="39" align="center">
	  <font size="+1" ><b>회원정보가 삭제되었습니다.</b></font></td>
  </tr>
  
  <tr bgcolor="${value_c}">
    <td align="center"> 
      <p>흑흑.... 서운합니다. 안녕히 가세요.</p>
      <meta http-equiv="Refresh" content="5;url=/main/main" >
    </td>
  </tr>
  
  <tr bgcolor="${value_c}">
    <td align="center"> 
      <input type="submit" value="확인">
    </td>
  </tr>
</table>
</form>
		    
						</sec:authorize>
				<sec:authorize access="isAnonymous()">
						<script> 
		  alert("비밀번호가 맞지 않습니다.");
	      history.go(-1);
		</script>
			    
						</sec:authorize>
</body>
</html>



			



<%@ include file="../../footer.jsp" %>
