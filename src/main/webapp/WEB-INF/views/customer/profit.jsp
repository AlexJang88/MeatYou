<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<title>매출현황</title>

</head>
<body>
	<a href="/customers/customer">홈으로</a>
	<a href="/customers/profitItem">월별 판매상품</a>
	
<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 매출 확인</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 매출 확인</h2>
    </c:otherwise>
</c:choose>
	
	
		
	
	<c:if test="${check==1}">
		<a href="/customers/profit">이번달 매출보기 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/profit?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/profit?check=${check+1}">다음달</a>
	</c:if>
	<br />
	
	<c:if test="${ptm==0}">
		 <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		        <td align="center">결재 내역이 없습니다!</td>
		    </tr>
		 </table>
	</c:if>
	
	<c:if test="${ptm>0}">
	<table border="1" width="1400" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="200" align="center">매출액</td>
			<td width="200" align="center">전체판매수량</td>
			<td width="200" align="center">쿠폰사용료</td>
			<td width="200" align="center">배송비</td>
			<td width="200" align="center">정산 금액</td>				
			<td width="200" align="center">이번달 판매 HOT </td>				
			<td width="200" align="center">파워링크</td>					
		</tr>
		<tr>
			<td align="center"><fmt:formatNumber value="${ptm}" type="number" pattern="#,##0"/> 원</td>
			
			<td align="center">${totalCount}</td> 
			<td align="center"><fmt:formatNumber value="${coponPay}" type="number" pattern="#,##0"/> 원</td> 
			<td align="center"><fmt:formatNumber value="${deliveryPay}" type="number" pattern="#,##0"/> 원</td>
			<c:if test="${check == 0}">
				<td align="center">정산 진행 중</td>
			</c:if>
			<c:if test="${check < 0}">				
				<td align="center"><fmt:formatNumber value="${(ptm * 0.9) - deliveryPay - coponPay}" type="number" pattern="#,##0"/> 원</td>
				</td>
			</c:if>
			
			<td align="center">${HOT}</td>
			<td align="center"><a href="/customers/pay">파워링크 결제하기</a></td>
			
		</tr>
	</table>

	* 정산예정금액은 총 매출액에서 수수료 10% 제외한 후 배송비, 쿠폰사용료를 제외하고 정산됩니다.
	</c:if>
<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
  });
});
</script>
</body>
</html>

<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
  });
});
</script>