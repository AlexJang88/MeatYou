<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />

<!DOCTYPE html>
<html>
<head>
    <title>구매 회원 고객리스트</title>
</head>
<body>


    <a href="/customers/customer">홈으로</a>
    <a href="/customers/CouponList">쿠폰제공리스트</a>

	<c:choose>
	    <c:when test="${not empty currentMonth and currentMonth <= 0}">
	        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 구매 회원 확인</h2>
	    </c:when>
	    <c:otherwise>
	        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 구매 회원 확인</h2>
	    </c:otherwise>
	</c:choose>
	
	<c:if test="${check==1}">
		<a href="/customers/consumerList">이번달 구매회원 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/consumerList?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/consumerList?check=${check+1}">다음달</a>
	</c:if>
	<br />


    
    <c:if test="${count==0}">
	  <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		 <tr>
		     <td align="center">아직 상품을 구매한 고객이 없습니다!</td>
		 </tr>
	  </table>
	</c:if>
    
    
    
    
  <c:if test="${count>0}">
  	<h2>구매를 진행한 회원 목록 : ${count}</h2>
  	 <table border="1" width="1900" cellpadding="0" cellspacing="0" align="center">
		 <tr height="30"> 
			<td width="200" align="center">구매자이름</td>
			<td width="200" align="center">상품 번호</td>
			<td width="300" align="center">구매 상품 제목</td>
			<td width="200" align="center">결제 갯수</td>
			<td width="200" align="center">결제금액</td>
			<td width="200" align="center">결제 날짜</td>				
			<td width="200" align="center">환불 여부</td>				
			<td width="200" align="center">회원 등급</td>			
			<td width="200" align="center">쿠폰 주기</td>						
		</tr>
		<c:forEach var="mber" items="${memberlist}">
			<form action="/customers/cusCoupon" method="post">
				 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				 <input type="hidden" name="p_m_id" value="${mber.order_m_id}">				 
				<tr align="center">
				 	<td>${mber.order_m_id}</td>
				 	<td>${mber.p_num}</td>
				 	<td><a href="/customers/content?p_num=${mber.p_num}">${mber.p_name}</a>	</td>
				 	<td>${mber.order_quantity}</td>
				 	<td>${mber.order_totalprice}</td>
				 	<td><fmt:formatDate value="${mber.order_paydate}" pattern="yyyy-MM-dd" /></td>
				 	<td>
				 		<c:choose>
							<c:when test="${mber.order_status == 0}">결제취소</c:when>
							<c:when test="${mber.order_status == 1}">결제완료</c:when>							       
							<c:when test="${mber.order_status == 2}">배송중</c:when>
							<c:when test="${mber.order_status == 3}">배송완료</c:when>							       
							<c:when test="${mber.order_status == 4}">구매확정</c:when>							       
						</c:choose>				 	
				 	</td>
				 	<td>${mber.mstat_detail}</td>
				 	<td align="center" >
				 		<input type="submit" value="쿠폰주기">
				 	</td>
				</tr>
			</form>
		</c:forEach> 
	 </table>	 
   </c:if> 
    
    
    
    <c:if test="${count>0}">
		<c:if test="${startPage>10}">
	       	<a href="/customers/consumerList?pageNum=${startPage-10}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        <a href="/customers/consumerList?pageNum=${i}">[${i}]</a>
		</c:forEach>
			<c:if test="${endPage<pageCount}">
	        <a href="/customers/consumerList?pageNum=${startPage+10}">[다음]</a>
		</c:if>
	</c:if>
</body>
</html>
