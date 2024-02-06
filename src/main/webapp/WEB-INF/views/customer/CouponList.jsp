<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  



<!DOCTYPE html>
<html>
<head>
<title>쿠폰제공리스트</title>
</head>
<body>

	<a href="/customers/customer">홈으로</a>
    <a href="/customers/consumerList">구매한 회원 목록</a>
    
    
    <c:if test="${count==0}">
		    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		        <tr>
		            <td align="center">쿠폰을 제공한 기록이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
    
    
  	<c:if test="${count >  0}">
  		<h2 align="center">쿠폰을 제공한 갯수 : ${count}</h2>
  		<table border="1" width="1400" cellpadding="0" cellspacing="0" align="center">
			<tr height="30"> 
				<td width="200" align="center">회원이름</td>
				<td width="200" align="center">쿠폰 종류</td>
				<td width="200" align="center">쿠폰 금액</td>				
				<td width="200" align="center">쿠폰 사용 여부</td>	
				<td width="200" align="center">쿠폰 발급날짜</td>
				<td width="200" align="center">만료기한</td>	
			</tr>
			<c:forEach var="coupon" items="${couponList}">
				<tr height="30" align="center">
					<td>${coupon.cp_m_id}</td>
					<td>
						<c:choose>
							<c:when test="${coupon.c_type == 1}"> 판매자 상품 전체</c:when>
							<c:when test="${coupon.c_type == 2}"> ${coupon.cp_p_num} 판매상품 한정</c:when>
						</c:choose>
					</td>
					<td><fmt:formatNumber value="${coupon.cp_price}" type="number" pattern="#,##0"/></td>	
					<td>
						<c:choose>
							<c:when test="${coupon.cp_status == 0}"> 사용함</c:when>
							<c:when test="${coupon.cp_status == 1}"> 미사용</c:when>
						</c:choose>
					</td>
					<td> <fmt:formatDate value="${coupon.publishdate}" pattern="yyyy-MM-dd" /></td>
					<td>
					 <fmt:formatDate value="${coupon.exdate}" pattern="yyyy-MM-dd" />
					 </td>
				</tr>
			</c:forEach>
		</table>		
	</c:if>
	
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/CouponList?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/CouponList?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/CouponList?pageNum=${startPage+10}">[다음]</a>
			</c:if>
	</c:if>
	
</body>
</html>