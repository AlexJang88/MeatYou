<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문현황</title>
</head>
<body>
<a href="/customers/customer">홈으로</a>

<a href="/customers/delivering">주문 접수 및 배송중</a>


<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 취소 현황</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 취소 현황</h2>
    </c:otherwise>
</c:choose>

	<c:if test="${check==1}">
		<a href="/customers/deliverout">이번달 매출보기 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/deliverout?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/deliverout?check=${check+1}">다음달</a>
	</c:if>
	<br />
	
	<c:if test="${count==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td align="center">취소된 상품이 없습니다!</td>
			</tr>
		</table>
	</c:if>
<c:if test="${count>0}">
	<table border="1" width="1550" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="150" align="center">주문 번호</td>
			<td width="150" align="center">주문한 회원</td> 
			<td width="150" align="center">주문 상품</td>
			<td width="100" align="center">주문 갯수</td>				
			<td width="150" align="center">주문 금액</td>
			<td width="300" align="center">배송지</td>
			<td width="150" align="center">주문 상태</td>	
			<td width="200" align="center">주문 날짜</td>	
			<td width="200" align="center">주문 취소날짜</td>	
		</tr>	
		<c:forEach var="out" items="${deliverOutList}">
			<tr align="center">
				<td>${out.order_num}</td>
				<td>${out.order_m_id}</td>
				<td>${out.p_num}</td>
				<td>${out.order_quantity}</td>
				<td>${out.order_totalprice}</td>
				<td>${out.order_addr}</td>
				<td>
					<c:choose>
						<c:when test="${out.order_status == 0}">주문취소</c:when>							       
					</c:choose>			
				</td>		
				<td><fmt:formatDate value="${out.order_paydate}" pattern="yyyy-MM-dd" /></td>		
				<td><fmt:formatDate value="${out.order_canceldate}" pattern="yyyy-MM-dd" /></td>		
			</tr>
		</c:forEach>
	</table>
 </c:if>
	 
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/deliverout?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/deliverout?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/deliverout?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
	
	


</body>
</html>