<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>재고현황</title>
</head>
<body>
<a href="/customers/customer">홈으로</a>
<a href="/customers/stock">전체상품 재고현황</a>

<h2>여기는 ${memId} 님의 판매중인 상품 재고 현황</h2>

		<c:if test="${stockcount==0}">
		    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		        <tr>
		            <td align="center">등록된 상품이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
			
		<c:if test="${stockcount >=  0}">		
			<h3 align="center">판매중인 상품 갯수 : ${stockcount} </h3> 
			<table border="1" width="1000" cellpadding="0" cellspacing="0" align="center">		
				<tr height="30"> 				
					<td width="300" align="center">썸네일 사진</td>
					<td width="300" align="center">제품 이름</td>
					<td width="300" align="center">판매 상태</td>
					<td width="100" align="center">보유 재고</td>				
					<td width="100" align="center">판매량</td>									
					<td width="100" align="center">재고 변경</td>	
					<td width="100" align="center">변경하기</td>	
				</tr>
				
				<c:forEach var="product" items="${stockonlist}">			 			 			   				             		
            		 <tr align="center">
            		 	<td>${product.thumb}</td>
            		 	<td><a href="/customers/content?p_num=${product.p_num}">${product.p_name}</a></td>
            		 	<td>
							 <c:choose>
							   <c:when test="${product.p_status == 0}">판매중</c:when>
							   <c:when test="${product.p_status == 1}">판매중(유료결제)</c:when>
							   <c:when test="${product.p_status == 2}">판매대기</c:when>
							   <c:when test="${product.p_status == 3}">판매종료</c:when>							       
							 </c:choose>
						</td>
            		 	<td>${product.stock}</td>
            		 	<td>우선은 0</td>
            		 		<form action="/customers/stockOnPro" method="post"  >
            		 			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            		 			<input type="hidden" name="p_m_id" value="${memId}">
            		 			<input type="hidden" name="pd_p_num" value="${product.pd_p_num}">
            		 			
		            		 	<td> <input type="number" size="40" maxlength="30" name="stock" required="required" placeholder="변경할 재고를 입력하세요"></td>
		            		 		
		            		 	<td align="center" >   
					                 <input type="submit" value="변경">
					            </td> 		          	    			   				        		
             	 			 </form>
             	 	</tr>	
				</c:forEach>
			</table>
		</c:if>		

</body>
</html>