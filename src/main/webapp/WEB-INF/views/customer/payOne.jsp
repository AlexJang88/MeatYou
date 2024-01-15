<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파워링크 기록</title>
</head>
<body>
<h2>파워링크 구매 기록</h2>

<a href="/customers/customer">홈으로</a> <a href="/customers/pay">유료결재 목록보기</a> <br/>

<a href="/customers/powerlink">파워링크 결제하기</a>  <a href="/customers/itemplus">품목확장 결제하기</a>
<br/>

<h2 align="center">유료결제된 항목 보기</h2>

<h2 align="center">파워링크</h2>
	<c:if test="${powerPayCount==0 }">
		 <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		     <tr>
		         <td align="center">결제된 노출 상품이 없습니다!</td>
		     </tr>
		 </table>
	</c:if>
	
	<c:if test="${powerPayCount >= 0 }">		
		<h3 align="center">총 결제 진행 횟수 : ${powerPayCount}  </h3> 
		<table border="1" width="900" cellpadding="0" cellspacing="0" align="center">		
			<tr height="30">				
				<td width="150" align="center">구매자아이디</td>
				<td width="150" align="center">사용중인코드</td>
				<td width="150" align="center">등록된 상품 번호</td>
				<td width="200" align="center">클릭당 금액</td>
				<td width="200" align="center">남은 클릭 갯수</td>				
				<td width="200" align="center">결제일</td>				
			</tr> 
			<c:forEach var="pay" items="${powerlistOne}">			 	
					  <tr align="center">
						 <td>${pay.co_m_id}</td>
						 <td>${pay.co_num}</td>
						 <td>${pay.co_p_num}</td>
						 <td>${pay.co_pay}</td>
						 <td>${pay.co_quantity}</td>
						 <td>
		                    <fmt:formatDate value="${pay.co_paydate}" pattern="yyyy-MM-dd" />
		                </td>			 
					  </tr>							
			</c:forEach>			
		</table>				 			
	 </c:if>
	 
	 <c:if test="${powerPayCount>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/payOne?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/payOne?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/payOne?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>

</body>
</html>