<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 문의하기</title>
</head>
<body>

<a href="/main/main">메인으로</a> <br/>
<a href="/board/userquestion?pq_p_num=${pq_p_num}">${pq_p_num} 상품 문의글쓰기</a> <br/>
<h1>소비자 - 판매자</h1>

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
				<td width="200" align="center">질문자</td>
				<td width="200" align="center">상품</td>
				<td width="300" align="center">글 제목</td>
				<td width="300" align="center">작성 날짜</td>
			

			</tr>
			
			<c:forEach var="userQna" items="${list}">
				<tr align="center">
					<td>${userQna.r}</td>
					<td>${userQna.pq_m_id}</td>
					<td>${userQna.pq_p_num}</td>
					 <td> 
			            <c:choose>
			                <c:when test="${userQna.pq_status == 0}">
			                    <a href="/board/userQnaContent?pq_p_num=${userQna.pq_p_num}&pq_num=${userQna.pq_num}&pq_ref=${userQna.pq_ref}">${userQna.pq_title}</a>
			                </c:when>
			                
			                
			                <c:when test="${userQna.pq_status == 3 and id ne userQna.pq_m_id}">
							    비공개글입니다.
							</c:when>
			                
			                <c:when test="${userQna.pq_status == 3 and id eq userQna.pq_m_id}">
							    <a href="/board/userQnaContent?pq_p_num=${userQna.pq_p_num}&pq_num=${userQna.pq_num}&pq_ref=${userQna.pq_ref}">${userQna.pq_title}</a>
							</c:when>
			               	
			               
							
							
			                	                
			            </c:choose>
			        </td>
					<td><fmt:formatDate value="${userQna.pq_reg_date}" pattern="yyyy-MM-dd" /></td>
	
				</tr>			
			</c:forEach>
		</table>
	</c:if>
	
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/board/userQna?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/board/userQna?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/board/userQna?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
	
</body>
</html>