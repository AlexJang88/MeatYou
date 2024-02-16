<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파워링크 결제하기</title>

    <%@ include file="../header.jsp" %>
</head>
<body>
<a href="/customers/customer">홈으로</a> <br/>
<a href="/customers/pay">유료결제 페이지로</a>

<h2>여기는 파워링크 결제 페이지</h2>

	<c:if test="${powerPayCount==0 }">
		<table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		   <tr>
			    <td align="center"> 결재된 노출 상품이 없습니다!</td>
		   </tr>
		</table>
	</c:if>

	<c:if test="${powerPayCount > 0 }">
		<h3 align="center">현재 판매중인 상품목록 : ${counting} </h3>
		<table border="1" width="1000" cellpadding="0" cellspacing="0" align="center">	
			
				<tr height="30"> 
					<td width="300" align="center">썸네일 사진</td>
					<td width="100" align="center">등록된상품번호</td>
					<td width="300" align="center">제품이름</td>								
					<td width="200" align="center">현재상태</td>				
					<td width="200" align="center">결제금액 선택</td>				
					<td width="100" align="center">상위노출 결제</td>						
				</tr>
				<c:forEach var="power" items="${poweredlist}" >
				  <form action="/customers/powerlinkpay" method="post" >
            		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">           		
            		<input type="hidden" name="p_num" value="${power.p_num}">	
            		<input type="hidden" name="co_num" value="${power.co_num}">	            			
            		<input type="hidden" name="p_name" value="${power.p_name}">	            			
            		<input type="hidden" name="thumb" value="${power.thumb}">	            			
					<tr align="center">		             
		              <td><img src="<%= request.getContextPath() %>/resources/file/product/${power.p_num}/${power.thumb}/" alt="썸네일"></td>
		              <td>${power.p_num} </td>  
		              <td><a href="/customers/productContent?num=${power.p_num}">${power.p_name}</a></td>
		              <td>
						<c:choose>
						   <c:when test="${power.p_status == 0}">판매중</c:when>
						   <c:when test="${power.p_status == 1}">판매중(품목결제 상품)</c:when>							       
						   <c:when test="${power.p_status == 2}">판매대기</c:when>
						   <c:when test="${power.p_status == 3}">판매종료</c:when>							       
					    </c:choose>
					   </td>
					   <td valign="top" >
						    <select name="clickpay">
						        <c:choose> 
						            <c:when test="${power.p_num >0}" >
						                <option value="10000">110회 & 1만원 (10% up)</option>				    
						                <option value="30000">330회 & 3만원 (11% up)</option>				     
						                <option value="50000">565회 & 5만원(13% up) </option>				  
						                <option value="100000">1160회 & 10만원 (16% up)</option>			
						                <option value="200000">2400회 & 20만원 (20% up)</option>
						            </c:when>
						        </c:choose>			        
						    </select>
						</td>	   
					   <td align="center" >   						
			              <input type="submit" value="결제하기">
			           </td> 					  
	            	</tr>	
	               </form> 				
				</c:forEach>
				
		</table>	
	</c:if>




</body>
</html>


<%@ include file="../footer.jsp" %>