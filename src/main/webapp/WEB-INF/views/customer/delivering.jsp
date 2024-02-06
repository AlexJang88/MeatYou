<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송예정 및 배송중</title>
</head>
<body>
<h3>여기는 배송 중 및 배송완료</h3>
<a href="/customers/customer">홈으로</a>
<a href="/customers/deliverout">주문취소</a>



<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 배송완료</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 배송 완료</h2>
    </c:otherwise>
</c:choose>

<c:if test="${check==1}">
		<a href="/customers/delivering">이번달 배송현황 보기 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/delivering?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/delivering?check=${check+1}">다음달</a>
	</c:if>
	<br />
	
	<c:if test="${count==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td align="center">결제된 상품이 없습니다!</td>
			</tr>
		</table>
	</c:if>
	

<c:if test="${count>0}">
	<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="150" align="center">주문 번호</td>
			<td width="200" align="center">주문한 회원</td> 
			<td width="200" align="center">주문 종류</td>
			<td width="100" align="center">주문 갯수</td>				
			<td width="100" align="center">주문 금액</td>
			<td width="300" align="center">배송지</td>
			<td width="150" align="center">결제일</td>							
			<td width="150" align="center">주문 상태</td>		 		
			<td width="150" align="center">배송 출발일</td>							
			<td width="150" align="center">배송 완료일</td>	
			<td width="100" align="center">상태변경</td>							
			<td width="100" align="center">변경하기</td>							
		</tr>
		<c:forEach var="ing" items="${deliveringList}">
		 <form action="/customers/deliverStatus" method="post">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="order_num" value="${ing.order_num}">			
			<input type="hidden" name="pageNum" value="${pageNum}">			
			<input type="hidden" name="check" value="${check}">			
			<tr align="center">
				<td>${ing.order_num}</td>
				<td>${ing.order_m_id}</td>
				<td>${ing.p_num}</td>
				<td>${ing.order_quantity}</td>
				<td><fmt:formatNumber value="${ing.order_totalprice}" type="number" pattern="#,##0"/></td>				
				<td>${ing.order_addr}</td>
				<td><fmt:formatDate value="${ing.order_paydate}" pattern="yyyy-MM-dd" /></td>
				<td>
					<c:choose>
						<c:when test="${ing.order_status == 1}">결제완료</c:when>							       
						<c:when test="${ing.order_status == 2}">배송중</c:when>							       
						<c:when test="${ing.order_status == 3}">배송완료</c:when>							       
						<c:when test="${ing.order_status == 4}">구매확정</c:when>							       
					</c:choose>	
				</td>
				<td><fmt:formatDate value="${ing.order_dere_start}" pattern="yyyy-MM-dd" /></td>
				<td><fmt:formatDate value="${ing.order_dere_end}" pattern="yyyy-MM-dd" /></td>
							
					<td>
					    <select name="order_status" ${ing.order_status == 4 ? 'disabled' : ''}>
					        <c:choose>
					            <c:when test="${ing.order_status >= 1}">
					                <option value="1" ${ing.order_status == 1 ? 'selected' : ''}>결제완료</option>   
					                <option value="2" ${ing.order_status == 2 ? 'selected' : ''}>배송중</option>   
					                <option value="3" ${ing.order_status == 3 ? 'selected' : ''}>배송완료</option>            
			          
					            </c:when>
					        </c:choose>
					    </select>
					</td>
					<td><input type="submit" value="변경" ${ing.order_status == 4 ? 'disabled' : ''}></td>
				</tr>
			   </form>
		</c:forEach>
	</table>
</c:if>

<c:if test="${count>0}">
	<c:if test="${startPage>10}">
	   <a href="/customers/delivering?check=${check}&pageNum=${startPage-10}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	  <a href="/customers/delivering?check=${check}&pageNum=${i}">[${i}]</a>
	</c:forEach>
		<c:if test="${endPage<pageCount}">
	     <a href="/customers/delivering?check=${check}&pageNum=${startPage+10}">[다음]</a>
	</c:if>
</c:if>
 

<h2>* 소비자가 구매확정 확인 또는 후기 작성 시  구매확정으로 변경됩니다.</h2>

</body>
</html>