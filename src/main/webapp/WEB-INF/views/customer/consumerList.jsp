<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <title>고객리스트</title>
</head>
<body>
    <h2>고객리스트 구매회원, 단골회원, 쿠폰리스트, 쿠폰 주기 기능 추가 </h2>
    <a href="/customers/customer">홈으로</a>
    <a href="/customers/cusCoupon">쿠폰제공리스트</a>
  
  	<table border="1" width="1900" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="200" align="center">구매자이름</td>
			<td width="300" align="center">구매 상품</td>
			<td width="200" align="center">결제 갯수</td>
			<td width="200" align="center">결제금액</td>
			<td width="200" align="center">결제 날짜</td>				
			<td width="200" align="center">환불 여부</td>				
			<td width="200" align="center">회원 등급</td>			
			<td width="200" align="center">쿠폰 주기</td>			
			<td width="200" align="center">쿠폰 제공</td>			
		</tr>
	</table>
    
</body>
</html>
