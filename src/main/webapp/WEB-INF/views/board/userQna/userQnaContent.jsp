<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내용보기</title>
</head>
<body>
<h1>문의한 내용 보는곳</h1>
<a href="/main/main">메인으로</a> <br/>
<a href="/board/userQna?p_num=${content.pq_p_num}">목록으로</a> <br/>


<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
	<tr>
		<td width="100" align="center">문의한사람</td><td>${content.pq_m_id}</td>		
	</tr>
	<tr>
		<td width="100" align="center">문의 날짜</td><td><fmt:formatDate value="${content.pq_reg_date}" pattern="yyyy-MM-dd" /></td>		
	</tr>
	<tr> 
		<td width="100" align="center">문의제목</td><td>${content.pq_title}</td>
	</tr>
	<tr>
		<td colspan="2" width="100" align="center">문의내용</td>
	</tr>	
		<td colspan="2">${content.pq_content}</td> 
</table>



</br>
</br>
  
<c:if test="${answerCount <2}">
	<c:if test="${p_id eq id}">
		<form method="post" action="userQnaContentPro">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="pq_p_num" value="${content.pq_p_num}">
			<input type="hidden" name="pq_num" value="${content.pq_num}">
			<input type="hidden" name="pq_ref" value="${content.pq_ref}">
			<input type="hidden" name="pq_status" value="${content.pq_status}">


			<table width="700" border="1" cellspacing="0" cellpadding="0" align="center">
			 <tr>
			 	<td width="150">답글달기</td>
			 	<td>
				 <textarea rows="7" cols="70" name="pq_content" required="required"></textarea>
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
</br>


<c:if test="${cuserContent.pq_status >=1}">
	<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
		<tr>
			<td>${cuserContent.pq_title}</td>
		</tr>
		<tr>
			<td>${cuserContent.pq_content}</td>
		</tr>
	</table>
</c:if>

</body>
</html>