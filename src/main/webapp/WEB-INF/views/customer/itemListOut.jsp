<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>판매종료</title>
</head>
<body>
	<a href="/customers/itemList">등록된 상품 목록</a> </br>
	<a href="/customers/itemListing">판매중인 상품</a> <br/>
	<h2> 여기는 판매 종료 상품목록</h2>
	<c:if test="${count==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td align="center">종료된 상품이 없습니다!</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${countout >=  0}">		
			<h2 align="center">판매 종료 및 대기 상품목록 : ${countout}</h2>
			<table border="1" width="1100" cellpadding="0" cellspacing="0" align="center">			
				<tr height="30"> 
					<td width="300" align="center">썸네일 사진</td>
					<td width="100" align="center">등록된 상품번호</td>
					<td width="200" align="center">제품이름</td>
					<td width="200" align="center">가격</td>				
					<td width="200" align="center">현재상태</td>	
					
				</tr>
					
				<c:forEach var="product" items="${listout}">			 			 			   			
            		 	<tr align="center">
            		 		<td>${product.thumb}</td>
            		 		<td>${product.p_num}</td>
            		 		<td>${product.p_name}</td>
            		 		<td>${product.p_price}</td>
            		 		<td>
							    <c:choose>
							        <c:when test="${product.p_status == 0}">판매중</c:when>
							        <c:when test="${product.p_status == 1}">판매중(유료결제)</c:when>
							        <c:when test="${product.p_status == 2}">판매대기</c:when>
							        <c:when test="${product.p_status == 3}">판매종료</c:when>							       
							    </c:choose>
							</td>							
		                </tr>		    			   				        		
				</c:forEach>
			</table>
		</c:if>
	
</body>
</html>


