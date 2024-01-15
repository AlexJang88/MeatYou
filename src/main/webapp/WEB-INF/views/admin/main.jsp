<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>
<a href="/admin/memberlist?check=3">판매자 승인대기</a>
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>
<br />
<a href="/admin/sales">매출보기</a>
<br>
<a href="/admin/reckon">정산내역 확인</a>
<br>
<h2>매출 요약정보</h2>
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
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
</table>

<br /><%@ include file="../footer.jsp" %>