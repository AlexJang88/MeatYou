<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송예정 및 배송중</title>
</head>
<body>
<h3>여기는 배송 예정</h3>
<a href="/customers/customer">홈으로</a>
<a href="/customers/deliver">주문접수</a>
<a href="/customers/delivered">배송완료</a>


<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">
	<tr height="30"> 
		<td width="200" align="center">주문 번호</td>
		<td width="200" align="center">주문한 회원(등급)</td> 
		<td width="200" align="center">주문 종류</td>
		<td width="100" align="center">주문 갯수</td>				
		<td width="100" align="center">주문 금액</td>
		<td width="300" align="center">배송지</td>			
		<td width="100" align="center">배송 현황</td>			
		<td width="100" align="center">상세정보</td>			
	</tr>
</table>


</body>
</html>