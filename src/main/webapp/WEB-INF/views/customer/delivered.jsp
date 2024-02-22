<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송완료</title>
</head>
<body>
<a href="/customers/customer">홈으로</a>
<a href="/customers/deliverout">주문취소</a>
<a href="/customers/delivering">주문 접수 및 배송중</a>
<h3>여기는 구매 확정  status4</h3>

<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 배송완료</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 배송 완료</h2>
    </c:otherwise>
</c:choose>

<c:if test="${count==0}">
	<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td align="center">배송완료된 상품이 없습니다!</td>
		</tr>
	</table>
</c:if>


<c:if test="${count==0}">
	<table border="1" width="1550" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="150" align="center">주문 번호</td>
			<td width="150" align="center">주문한 회원</td> 
			<td width="150" align="center">주문 상품</td>
			<td width="100" align="center">주문 갯수</td>				
			<td width="150" align="center">주문 금액</td>
			<td width="300" align="center">배송지</td>
			<td width="150" align="center">주문 상태</td>	
			<td width="150" align="center">배송 출발 날짜</td>	
			<td width="150" align="center">주문 도착 날짜</td>	
			<td width="200" align="center">주문 날짜</td>	
		</tr>
		<c:forEach var="" items="">
			<tr>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</c:forEach>	
	</table>
</c:if>

<c:if test="${count>0}">
	<c:if test="${startPage>10}">
	<a href="/customers/delivered?pageNum=${startPage-10}">[이전]</a>
	</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		 	<a href="/customers/delivered?pageNum=${i}">[${i}]</a>
		</c:forEach>
	<c:if test="${endPage<pageCount}">
	    <a href="/customers/delivered?pageNum=${startPage+10}">[다음]</a>
	</c:if>
</c:if>


</body>
</html>