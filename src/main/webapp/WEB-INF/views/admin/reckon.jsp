<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>

<h2>정산내역 확인</h2>
<form action="/admin/reckon" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	<select name="start">
		<option value="2022">2022년</option>
		<option value="2023">2023년</option>
		<option value="2024">2024년</option>
	</select>
	<select name="end">
		<option value="1">1월</option><option value="2">2월</option>
		<option value="3">3월</option><option value="4">4월</option>
		<option value="5">5월</option><option value="6">6월</option>
		<option value="7">7월</option><option value="8">8월</option>
		<option value="9">9월</option><option value="10">10월</option>
		<option value="11">11월</option><option value="12">12월</option>
	</select>
	<input type="submit" value="조회">
</form>
<c:forEach var="dto" items="${list}">
		${dto.rk_num} / ${dto.rk_status} / ${dto.rk_deposit_date} / ${dto.totalprice} /${dto.p_price} / ${dto.dere_pay}<br />
</c:forEach>

<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/admin/memberlist?pageNum=${startPage-10}&check=${check}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/admin/memberlist?pageNum=${i}&check=${check}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/admin/memberlist?pageNum=${startPage+10}&check=${check}">[다음]</a>
			</c:if>
		</c:if>
<%@ include file="../footer.jsp" %>