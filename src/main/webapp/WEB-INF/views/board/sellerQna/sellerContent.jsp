<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의하기 작성</title>
</head>
<body>
<h1>판매자 - 관리자 문의하기</h1> 
<a href="/main/main">메인으로</a> <br/>
<a href="/board/sellerQna?pageNum=${pageNum}">목록으로</a> <br/>

<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="100" align="center">문의한사람</td><td>${content.ma_m_id}</td>		
	</tr>
	<tr>
		<td width="100" align="center">문의 날짜</td><td><fmt:formatDate value="${content.ma_reg_date}" pattern="yyyy-MM-dd" /></td>		
	</tr>
	<tr> 
		<td width="100" align="center">문의제목</td><td>${content.ma_title}</td>
	</tr>
	<tr>
		<td colspan="2" width="100" align="center">문의내용</td>
	</tr>	
		<td colspan="2">${content.ma_content}</td> 
</table>

</br>

<c:if test="${answerCount <2}">
	<c:if test="${m_Status == 7999}">
		<form method="post" action="sellerContentPro">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="ma_ref" value="${content.ma_ref}">
			<input type="hidden" name="ma_num" value="${content.ma_num}">
			<input type="hidden" name="pageNum" value="${pageNum}">

			<table width="700" border="1" cellspacing="0" cellpadding="0" align="center">
			 <tr>
			 	<td width="150">답글달기</td>
			 	<td>
				 <textarea rows="7" cols="70" name="ma_content" required="required"></textarea>
			 	</td>
			 </tr>
			 <tr>
			 	<td colspan="2"><input type="submit" value="답글달기"> </td>
			 </tr>
			</table>
		</form>	
	</c:if>
</c:if>

</br>

<c:if test="${contentView.ma_status >=1}">
	<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>${contentView.ma_title}</td>
		</tr>
		<tr>
			<td>${contentView.ma_content}</td>
		</tr>
	</table>
</c:if>


</body>
</html>