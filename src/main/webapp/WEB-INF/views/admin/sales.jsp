<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../header.jsp" %>
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>
<a href="/admin/memberlist?check=3">판매자 승인대기</a>
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>
<br />
<h2>매출 요약정보</h2>

<a href="/admin/sales?check=${check-1}">이전달</a>
<c:if test="${check<0}">
<a href="/admin/sales?check=${check+1}">다음달</a>
</c:if>
<br />
<table>
	<tr>
		<td>이번달 총 매출 </td>
		<td>이번달 총 상품 판매금액 </td>
		<td>이번달 총 상품 수수료 </td>
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
<br /><%@ include file="../footer.jsp" %>