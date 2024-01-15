<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기는 품목확장</title>
</head>
<body>
<h2>여기는 품목확장 결제페이지</h2>
<a href="/customers/customer">홈으로</a> <br/>
<h3 align="center">${id} 판매자님의 아이디로 품목확장 1회가 결제됩니다.</h3> 
<h3 align="center">* 결제 후 상품목록 페이지로 이동하여 원하시는 상품을 추가해주세요..!</h3> 

<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">		
	<tr height="30"> 
		<td width="300" align="center">결제자이름</td>
		<td width="200" align="center">상품종류</td>
		<td width="300" align="center">결제금액</td>
		<td width="200" align="center">기간</td>				
		<td width="200" align="center">환불여부</td>				
		<td width="100" align="center">결제하기</td>			
	</tr>
	<form action="/customers/itemplusPro" method="post" >
	 <tr height="30"> 
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <input type="hidden" name="p_m_id" value="${id}">	
		<td width="300" align="center">${id}</td>
		<td width="200" align="center">품목확장 1건</td>
		<td width="300" align="center">50,000</td>
		<td width="200" align="center">1개월</td>				
		<td width="200" align="center">불가</td>					
		<td align="center" >   
			<input type="submit" value="결제">
		 </td> 
	 </tr>
	</form>	
</table>	


</body>
</html>