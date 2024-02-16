<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <%@ include file="../header.jsp" %>
<meta charset="UTF-8">
<title>재고현황</title>
<script>
window.onload = function () {
    // JSP 페이지에서 설정한 statusFromJSP 값을 읽어옴
    var statusFromJSP = '${status}';
	
    // status 값이 0인 경우에만 동작
    if (statusFromJSP === 0) {
        var testPageUrl = '/customers/test';
        var newWindow = window.open(testPageUrl, 'TestPage', 'width=800,height=600');
        if (window.focus) {
            newWindow.focus();
        }
    }
}
</script>


</head>
<body>
<a href="/customers/customer">홈으로</a>
<a href="/customers/stock">전체상품 재고현황</a>

<h2>여기는 ${memId} 님의 판매중인 상품 재고 현황</h2>



		<c:if test="${stockcount==0}">
		    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		        <tr>
		            <td align="center">판매중인 상품이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
			
		<c:if test="${stockcount >  0}">		
			<h3 align="center">판매중인 상품 재고 : ${stockcount} 건 </h3> 
			<table border="1" width="1500" cellpadding="0" cellspacing="0" align="center">		
				<tr height="30"> 				
					<td width="300" align="center">썸네일 사진</td>
					<td width="200" align="center">상품 번호</td>
					<td width="300" align="center">제품 이름</td>
					<td width="200" align="center">판매 상태</td>
					<td width="100" align="center">등록 재고</td>				
					<td width="100" align="center">재고 변경</td>	
					<td width="100" align="center">변경하기</td>	
					<td width="100" align="center">판매량</td>									
					<td width="100" align="center">남은재고</td>									
				</tr>
				
				<c:forEach var="product" items="${stockonlist}" varStatus="loopStatus">
					<c:set var="i" value="${loopStatus.index}" />			 			 			   				             		
					<c:set var="nam" value="${product.stock - aree[i]}" />			 			 			   				             		
            		 <tr align="center">
            		 	<td><img src="<%= request.getContextPath() %>/resources/file/product/${product.p_num}/${product.thumb}/" alt="썸네일"></td>
            		 	<td>${product.p_num}</td>
            		 	<td><a href="/customers/productContent?num=${product.p_num}">${product.p_name}</a></td>
            		 	<td>
							 <c:choose>
							   <c:when test="${product.p_status == 0}">판매중</c:when>
							   <c:when test="${product.p_status == 1}">판매중(유료결제)</c:when>
							   <c:when test="${product.p_status == 2}">판매대기</c:when>
							   <c:when test="${product.p_status == 3}">판매종료</c:when>							       
							 </c:choose>
						</td>
            		 	<td>${product.stock}</td>           		 	
            		 		<form action="/customers/stockOnPro" method="post"  >
            		 			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            		 			<input type="hidden" name="p_m_id" value="${memId}">
            		 			<input type="hidden" name="pd_p_num" value="${product.pd_p_num}">         		 			
			           				<td> <input type="number" size="40" maxlength="30" name="stock" required="required" placeholder="변경할 재고를 입력하세요"></td>		            		 		
			            		 	<td align="center" >   
						                 <input type="submit" value="변경">
						            </td> 		          	    			   				        		
             	 			 </form>
             	 		<td>${aree[i]}</td>
             	 		<td>${nam}</td>
             	 	</tr>	
				</c:forEach>
			</table>
		</c:if>		
		
		<c:if test="${stockcount>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/onStock?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/onStock?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/onStock?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
		
		
		
</body>
</html>

<%@ include file="../footer.jsp" %>