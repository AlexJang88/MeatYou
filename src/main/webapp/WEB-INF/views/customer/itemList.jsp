<%@ page language="java" contentType="text/html; charset=UTF-8"     pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
</head>
<body>



<a href="/customers/customer">홈으로</a> <br/>
<a href="/customers/itemListOut">판매 종료된 상품</a> 
<h2>여기는 ${memId} 님의 판매중인상품 목록</h2>
		<c:if test="${count==0}">
		    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		        <tr>
		            <td align="center">판매중인 상품이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
			
		<c:if test="${count >  0}">		
			<h3 align="center"> 판매 및 대기 상품 목록 : ${count} / 사용 가능한 유료 품목 확장 갯수 : ${paycount} 개  </h3> 
			<h2>${erro}</h2>
			<table border="1" width="1200" cellpadding="0" cellspacing="0" align="center">			
				<tr height="30"> 
					<td width="300" align="center">썸네일 사진</td>
					<td width="200" align="center">상품번호</td>
					<td width="200" align="center">제품이름</td>
					<td width="200" align="center">가격</td>				
									
					<td width="200" align="center">현재상태</td>				
					<td width="100" align="center">노출상태변경</td>	
					<td width="100" align="center">변경하기</td>	
				</tr>
					
				<c:forEach var="product" items="${list}"  varStatus="loop">			 			 			   
				  <form action="/customers/statusChange" method="post" onsubmit="return validateForm();">
            		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            		 <input type="hidden" name="p_m_id" value="${memId}">	
            		 <input type="hidden" name="p_num" value="${product.p_num}">	      		 
            		 <input type="hidden" name="pageNum" value="${pageNum}">	      		 
						
            		 		<tr align="center">
	            		 		<td>
	            		 		<img src="<%= request.getContextPath() %>/resources/file/product/${product.p_num}/${product.thumb}/" alt="썸네일">
	            		 		</td>
	            		 		 <td>${product.p_num}				               				                
					            </td>
	            		 		<td><a href="/customers/productContent?num=${product.p_num}">${product.p_name}</a></td>
	            		 		<td>${product.p_price}</td>            		 		
	            		 		<td>
								    <c:choose>
								        <c:when test="${product.p_status == 0}">판매중</c:when>
								        <c:when test="${product.p_status == 1}">유료결제 진행상품</c:when>							       
								        <c:when test="${product.p_status == 2}">판매대기</c:when>
								        <c:when test="${product.p_status == 3}">판매종료</c:when>							       
								    </c:choose>
								</td>
							<td valgn="top">
							    <select name="p_status" >
							        <c:choose>
							            <c:when test="${M_status == 2001 || M_status == 2002}">
							                <option value="0" ${product.p_status == 0 ? 'selected' : ''}>판매중</option>
							                <option value="2" ${product.p_status == 2 ? 'selected' : ''}>판매대기</option>
							                <option value="3" ${product.p_status == 3 ? 'selected' : ''}>판매종료</option>
							            </c:when>
							            <c:when test="${M_status == 2003 || M_status == 2004 }">							             
							                <option value="0" ${product.p_status == 0 ? 'selected' : ''}>판매중</option>				               								
							                <c:forEach var="cus" items="${cus_order}" varStatus="status">
							                <option value="${cus.co_num}" ${product.co_num == cus.co_num ? 'selected' : ''}>판매중 (품목결제:  ${status.index +1})</option>
							                </c:forEach>
							                <option value="2" ${product.p_status == 2 ? 'selected' : ''}>판매대기</option>
							                <option value="3" ${product.p_status == 3 ? 'selected' : ''}>판매종료</option>
							            </c:when>							         
							        </c:choose>
							    </select>
							</td>						  
							  <td align="center" >   
							
			                  <input type="submit" value="변경" >
			                 </td> 
		                </tr>		    			   				        		
             	  </form>
				</c:forEach>
			</table>			
		</c:if>
		
		<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/itemList?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/itemList?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/itemList?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>

</body>
</html>


