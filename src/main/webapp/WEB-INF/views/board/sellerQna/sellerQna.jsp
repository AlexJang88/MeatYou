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
<a href="/board/questionSA">판매자관리자에게문의글쓰기</a> <br/>
<h1>판매자- 관리자</h1>


	<c:if test="${count==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		   <tr>
		      <td align="center">등록된 문의사항이 없습니다!</td>
		   </tr>
		</table>
	</c:if>
	
	<c:if test="${count >  0}">	
		<table border="1" width="1200" cellpadding="0" cellspacing="0" align="center">
			<tr height="30">
				<td width="200" align="center">글 번호</td>
				<td width="300" align="center">글 제목</td>
				<td width="200" align="center">질문자</td>
				<td width="300" align="center">작성 날짜</td>
			</tr>
			<c:forEach var="Qna" items="${list}">
				<tr align="center">
					<td>${Qna.r}</td>
					<td><a href="/board/sellerContent?ma_num=${Qna.ma_num}&ma_ref=${Qna.ma_ref}&pageNum=${pageNum}">${Qna.ma_title}</a></td>
					<td>${Qna.ma_m_id}</td>
					<td><fmt:formatDate value="${Qna.ma_reg_date}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/board/sellerQna?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/board/sellerQna?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/board/sellerQna?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>

</body>
</html>