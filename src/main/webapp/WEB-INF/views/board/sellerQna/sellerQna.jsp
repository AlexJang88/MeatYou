<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매자 문의</title>
</head>
<body>

<a href="/main/main">메인으로</a> <br/>
<h1>판매자- 관리자</h1>


	<c:if test="${count==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		   <tr>
		      <td align="center">등록된 문의사항이 없습니다!</td>
		   </tr>
		</table>
	</c:if>
	
	<c:if test="${count >  0}">	
		<table>
		
		</table>
	</c:if>


</body>
</html>