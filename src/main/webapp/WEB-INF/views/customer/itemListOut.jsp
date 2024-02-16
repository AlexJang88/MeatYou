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
<a href="/customers/customer">홈으로</a> <br/>
	<a href="/customers/itemList">등록된 상품 목록</a> </br>
	<h2> 여기는 판매 종료 상품목록</h2>
	<c:if test="${countout==0}">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
			<tr>
				<td align="center">종료된 상품이 없습니다!</td>
			</tr>
		</table>
	</c:if>
	
	<c:if test="${countout >  0}">		
			<h3 align="center">판매 종료 및 대기 상품목록 : ${countout} </h3>
			<table border="1" width="1100" cellpadding="0" cellspacing="0" align="center">			
				<tr height="30"> 
					<td width="300" align="center">썸네일 사진</td>
					<td width="300" align="center">등록된상품번호</td>
					<td width="200" align="center">제품이름</td>
					<td width="200" align="center">가격 </td>				
					<td width="200" align="center">현재상태</td>				
					<td width="100" align="center">노출상태변경</td>	
					<td width="100" align="center">변경하기</td>	
				</tr>
					
				<c:forEach var="product" items="${listout}">	
				<form action="/customers/statusChangeout" method="post">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            		 <input type="hidden" name="p_m_id" value="${memId}">	
            		 <input type="hidden" name="p_num" value="${product.p_num}">
            		 <input type="hidden" name="pageNum" value="${pageNum}">		 			 			   			
            		 	<tr align="center">
            		 		<td><img src="<%= request.getContextPath() %>/resources/file/product/${product.p_num}/${product.thumb}/" alt="썸네일"></td>
            		 		<td>${product.p_num} </td>
            		 		<td>${product.p_name}</a></td>
            		 		<td><fmt:formatNumber value="${product.p_price}" type="number" pattern="#,##0"/></td>
            		 		<td>
							    <c:choose>
							        <c:when test="${product.p_status == 3}">판매종료</c:when>							       
							    </c:choose>
							</td>
							<td valgn="top">
							    <select name="p_status">
							        <c:choose>
							            <c:when test="${M_status == 2001 || M_status == 2002}">							                
							                <option value="2" ${product.p_status == 2 ? 'selected' : ''}>판매대기</option>
							            </c:when>
							            <c:when test="${M_status == 2003 || M_status == 2004}">							             
							                <option value="2" ${product.p_status == 2 ? 'selected' : ''}>판매대기</option>			               														             						                
							            </c:when>							         
							        </c:choose>
							</select>
							</td>						  
							  <td align="center" >   
			                  <input type="submit" value="변경">
			                 </td> 
			                 <td align="center" >  
				             	  <input type="button" value="글수정" onclick="document.location.href='/customers/productUpdate?num=${product.p_num}'">
				              </td> 
			                 
		                </tr>		
		            </form>	  
				</c:forEach>
			</table>		
		</c:if>
		
		<c:if test="${countout>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/itemListOut?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/itemListOut?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/itemListOut?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
	
</body>
</html>


