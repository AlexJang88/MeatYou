<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>품목확장</title>
</head>
<body>

<a href="/customers/customer">홈으로</a>  <a href="/customers/pay">유료결재 목록보기</a> <br/>
<a href="/customers/powerlink">파워링크 결제하기</a>  <a href="/customers/itemplus">품목확장 결제하기</a>

<h2>여기는 품목확장</h2>

	<c:if test="${listPayCount==0}">
		 <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		     <tr>
		         <td align="center">결제된 품목 확장 상품이 없습니다!</td>
		     </tr>
		 </table>
	</c:if>
	
  	<c:if test="${listPayCount >= 0}">
		<h3 align="center">총 결제 진행 횟수 : ${listPayCount}  / 현재 사용가능한 유료 품목 수 : ${listpaynowcount} </h3> 
		<table border="1" width="900" cellpadding="0" cellspacing="0" align="center">		
			<tr height="30">				
				<td width="150" align="center">구매자아이디</td>
				<td width="150" align="center">사용중인코드</td>
				<td width="150" align="center">사용중인 상품번호</td>
				<td width="200" align="center">사용한 금액</td>
				<td width="200" align="center">결제일</td>				
				<td width="200" align="center">종료일</td>				
			</tr>
			<c:forEach var="pay" items="${paylist}" >			 	
				  <tr align="center">
					 <td>${pay.co_m_id}</td>
					 <td>${pay.co_num}</td>
					 <td>${pay.co_p_num}</td>
					 <td>${pay.co_pay}</td>
					 <td>
	                    <fmt:formatDate value="${pay.co_paydate}" pattern="yyyy-MM-dd" />
	                </td>
	                <td>
	                    <fmt:formatDate value="${pay.co_payenddate}" pattern="yyyy-MM-dd" />
	                </td>			 			 			 
				  </tr>			  
			</c:forEach> 
		</table>
	</c:if>

	 <c:if test="${listPayCount>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/payTwo?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/payTwo?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/payTwo?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>


</body>
</html>