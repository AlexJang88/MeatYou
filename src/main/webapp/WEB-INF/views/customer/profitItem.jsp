<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html> 
<html>
<head>
    <%@ include file="../header.jsp" %>
<title>판매 확인</title>
</head>

<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 판매된 상품 확인</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 판매된 상품 확인</h2>
    </c:otherwise>
</c:choose>

<body>
<a href="/customers/customer">홈으로</a>
<a href="/customers/profit">매출현황</a> </br>
	
	 
	<c:if test="${check==1}">
		<a href="/customers/profitItem">이번달 판매상품 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/profitItem?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/profitItem?check=${check+1}">다음달</a>
	</c:if>
	<br />
	
	<c:if test="${count==0}">
		 <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		        <td align="center">상품이 구매된 기록이 없습니다.!</td>
		    </tr>
		 </table>
	</c:if>	
		
	
	<c:if test="${count>0}">	
		<h2>구매를 진행 횟수 : ${count} </h2>		
		<table border="1" width="1400" cellpadding="0" cellspacing="0" align="center">
			<tr height="30"> 
				<td width="150" align="center">판매상품 번호</td>
				<td width="200" align="center">판매상품 이름</td>			
				<td width="150" align="center">판매상품 가격</td>
				<td width="150" align="center">판매상품 조회수</td>
				<td width="150" align="center">판매 수량</td>							
				<td width="150" align="center">결제 금액</td>							
				<td width="150" align="center">판매 날짜</td>							
				<td width="200" align="center">파워링크</td>					
			</tr>
			<c:forEach var="item" items="${itemList}" >
				<tr align="center">	
					<td>${item.p_num}</td>
					<td><a href="/customers/content?p_num=${item.p_num}">${item.p_name}</td>
					<td>${item.p_price}</td>
					<td>${item.p_rcount}</td>
					<td>${item.order_quantity}</td>
					<td>${item.order_totalprice}</td>
					<td>			
					<fmt:formatDate value="${item.order_paydate}" pattern="yyyy-MM-dd" />
					</td>			
					<td align="center"><a href="/customers/pay">파워링크 결제하기</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>	
	
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/profitItem?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/profitItem?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/profitItem?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
	
</body>
</html>

<%@ include file="../footer.jsp" %>