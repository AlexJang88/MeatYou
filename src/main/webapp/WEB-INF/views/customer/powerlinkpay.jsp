<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파워링크 결제페이지</title>
</head>
<body>
<h2>파워링크 결제페이지</h2>
<a href="/customers/customer">홈으로</a> <br/>

  <h3 align="center">해당 상품으로 결제를 진행합니다!</h3> 
  <h3 align="center">비용은 클릭당 100원으로 진행됩니다!</h3> 
	<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">				
		<tr height="30"> 
			<td width="300" align="center">썸네일 사진</td>
			<td width="200" align="center">상품번호</td>
			<td width="300" align="center">제품이름</td>								
			<td width="200" align="center">현재상태</td>				
			<td width="200" align="center">클릭수 및 비용</td>							
			<td width="100" align="center"> 비용</td>						
			<td width="100" align="center"> 결제</td>						
		</tr>			
		<form action="/customers/powerlinkpayPro" method="post" >
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">           		
            <input type="hidden" name="p_num" value="${payMentItem.p_num}">	
            <input type="hidden" name="clickpay" value="${clickpay}">	
			<tr align="center">
		        <td>${payMentItem.thumb}</td>
		        <td>${payMentItem.p_num} </td>
		        <td>${payMentItem.p_name}</a></td>
		        <td>
				  <c:choose>
				   <c:when test="${payMentItem.p_status == 0}">판매중</c:when>
				   <c:when test="${payMentItem.p_status == 1}">판매중(유료결제 ${co_num})</c:when>							       
				   <c:when test="${payMentItem.p_status == 2}">판매대기</c:when>
				   <c:when test="${payMentItem.p_status == 3}">판매종료</c:when>							       
				  </c:choose>
				</td>				
				<td valign="top">
				    <c:choose>
						<c:when test="${clickpay == 10000}"> <input type="hidden" name="clickcount" value="110"/> 110회 </c:when>
						<c:when test="${clickpay == 30000}"><input type="hidden" name="clickcount" value="330"/> 330회</c:when>
						<c:when test="${clickpay == 50000}"><input type="hidden" name="clickcount" value="565"/> 565회</c:when>
						<c:when test="${clickpay == 100000}"><input type="hidden" name="clickcount" value="1160"/>1160회</c:when>											       
						<c:when test="${clickpay == 20000}"><input type="hidden" name="clickcount" value="2400"/>2400회</c:when>											       
					</c:choose>
				</td>
				<td>${clickpay}원</td>	
		       	<td>		   						
			       <input type="submit" value="결제하기">
			    </td> 					 
	         </tr>
	       </form>										
		</table>	
		<h3 align="center">파워링크 결제 후 수정 및 환불은 불가합니다..!</h3> 
</body>
</html>