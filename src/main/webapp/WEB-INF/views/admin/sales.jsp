<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>
<a href="/admin/memberlist?check=3">판매자 승인대기</a>
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>
<br />
<a href="/admin/sales">매출보기</a>
<br>
<h2>매출 요약정보</h2>
<form method="post" action="/admin/sales">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="text" name="daterange">
	<input type="hidden" name="check" value="100"> <input
		type="submit" value="검색">
</form>

<c:if test="${check==1}">
	<a href="/admin/sales">이번달 매출보기 </a>
</c:if>
<c:if test="${check<=0}">
	<a href="/admin/sales?check=${check-1}">이전달</a>
</c:if>
<c:if test="${check!=100}">
	<c:choose>
		<c:when test="${not empty currentMonth and currentMonth <= 0}">
			<c:out value="${currentYear - 1}" />년 <c:out
				value="${currentMonth + 12}" />월 매출 현황
    </c:when>
		<c:otherwise>
			<c:out value="${currentYear}" />년 <c:out value="${currentMonth}" />월 매출 현황
    </c:otherwise>
	</c:choose>
</c:if>
<c:if test="${check==100}">
검색결과 <a href="/admin/sales">이번달 매출보기 </a>
</c:if>
<c:if test="${check<0}">
	<a href="/admin/sales?check=${check+1}">다음달</a>
</c:if>
<br />
<table>
	<tr>
		<td>이번달 총 매출</td>
		<td>이번달 총 상품 판매금액</td>
		<td>이번달 총 상품 수수료</td>
		<td>이번달 유료결제(품목) 금액</td>
		<td>이번달 유료결제(상위노출) 금액</td>
		<td>이번달 쿠폰 사용금액</td>
	</tr>
	<tr>
		<td>${pt}</td>
		<td>${ps}</td>
		<td>${pc}</td>
		<td>${pi}</td>
		<td>${pa}</td>
		<td>${pc}</td>
	</tr>
</table>
<script>
	$(document).ready(
			function() {
				$('input[name="daterange"]').daterangepicker(
						{
							opens : 'left'
						},
						function(start, end, label) {
							console.log("A new date selection was made: "
									+ start.format('DD-MM-YYYY') + ' to '
									+ end.format('DD-MM-YYYY'));
						});
			});
</script>