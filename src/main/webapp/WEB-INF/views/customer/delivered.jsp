<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>배송완료</title>
</head>
<body>
<a href="/customers/customer">홈으로</a>
<a href="/customers/deliver">주문접수</a>
<a href="/customers/delivering">배송예정</a>
<h3>여기는 배송완료</h3>

<table border="1" width="1000" cellpadding="0" cellspacing="0" align="center">
	<tr height="30"> 
		<td width="200" align="center">주문 번호</td>
		<td width="200" align="center">주문한 회원(등급)</td> 
		<td width="200" align="center">주문 종류</td>
		<td width="200" align="center">주문 갯수</td>				
		<td width="200" align="center">주문 금액</td>
		<td width="200" align="center">배송지</td>			
	</tr>
</table>


</body>
</html>