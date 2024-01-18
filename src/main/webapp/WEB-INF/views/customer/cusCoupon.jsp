<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<a href="/customers/customer">홈으로</a>
<title>쿠폰제공리스트</title>
</head>
<body>
<h2>여기는 누구한테 쿠폰을 줬는지 확인</h2>

<a href="/customers/customer">홈으로</a>
    <a href="/customers/consumerList">구매한 회원 목록</a>
  	
  	<h2>아래고객에게 쿠폰을 제공하시겠습니까?</h2>
  	<table border="1" width="1200" cellpadding="0" cellspacing="0" align="center">
		<tr height="30" align="center"> 
			<td width="200" align="center">쿠폰 받는 회원</td>
			<td width="200" align="center">쿠폰 제공하는 사람</td>
			<td width="200" align="center">쿠폰 사용처</td>
			<td width="200" align="center">판매상품 번호</td>
			<td width="200" align="center">쿠폰 금액 </td>				
			<td width="200" align="center">쿠폰 사용기간</td>
		</tr>
		<tr align="center">
		<form action="cusCouponPro" method="post">  
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="p_m_id" value="${p_m_id}">			
			<input type="hidden" name="companynum" value="${companynum}">			
				<td>${p_m_id}</td>
				<td>${id}  </td>
				<td>
				   <select name="couponUse">
					  <c:choose>
						 <c:when test="${id != ''}">
						 	  <option value="1" >판매자상품 전체</option>				               								
							  <option value="2" >특정 상품만</option>													  
						 </c:when>
				  	  </c:choose>			
				   </select>
				</td>
				<td>
				    <select name="p_status">
				        <c:forEach var="product" items="${itemList}">
				            <c:choose>
				                <c:when test="${id != ''}">
				                    <option value="${product.p_num}">${product.p_num}</option>
				                </c:when>
				            </c:choose>
				        </c:forEach>
				    </select>
				</td>
				<td>
				   <select name="point">
					  <c:choose>
						 <c:when test="${id != ''}">
						 	  <option value="3000" >3,000원</option>				               								
							  <option value="5000" >5,000원</option>						
							  <option value="8000" >8,000원</option>
							  <option value="10000" >10,000원</option>
						 </c:when>
				  	  </c:choose>			
				   </select>
				</td>
				<td>제공 후 일주일</td>
			</tr>
			<tr >
				<td colspan="6" align="center">                       
	                <input type="submit" value="제공하기">         
	        	</td>
			</tr>
	   </form>		
	</table>
		
	
</body>
</html>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        var couponUseSelect = document.querySelector('select[name="couponUse"]');
        var pStatusSelect = document.querySelector('select[name="p_status"]');

        // 초기 상태에서 두 번째 select를 숨김
        pStatusSelect.style.display = 'none';

        // 첫 번째 select의 변경 이벤트를 감지
        couponUseSelect.addEventListener('change', function () {
            // 선택된 값이 2이면 두 번째 select를 보여주고, 그렇지 않으면 숨김
            pStatusSelect.style.display = (couponUseSelect.value == '2') ? 'block' : 'none';
        });
    });
</script>