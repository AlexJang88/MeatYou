<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/customers/onStock.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
 






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
 <div class="main-container" >
			    <div class="category-menu">
			        <!-- Your category menu content -->
					        <div class="vertical-menu">
							            <div class="vertical-menu-item">
							                <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">상품</a>
							                <div id="mem" class="collapse">
							           		       <a href="/customers/itemUpdate" class="btn" id="smallfont" >상품 등록</a><br/>
			                                            <a href="/customers/itemList" class="btn"id="smallfont" >상품 목록</a>
							                </div>
							            </div>
					
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">매출</a>
								                <div id="pro" class="collapse">
								        <a href="/customers/profit" class="btn" id="smallfont" >매출현황</a><br/>
                                       <a href="/customers/profitItem"class="btn"id="smallfont" >월별 판매 현황</a>
                                   
								                </div>
								            </div>
								
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">재고</a>
								                <div id="money" class="collapse">
								                    <a href="/customers/stock"  class="btn" id="smallfont" >재고현황</a><br/>
                                       <a href="/customers/onStock" class="btn"id="smallfont" >월별 판매 현황</a>
								                </div>
								            </div>
					
										      
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">회원 관리</a>
								                <div id="check" class="collapse">
								         <a href="/customers/consumerList" class="btn" id="smallfont" >구매회원</a><br/>
                                <a href="/customers/CouponList" class="btn"  id="smallfont" >쿠폰 보유 회원</a>
								                </div>
								            </div>
											
					
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#pay" id="bigfont">결제</a>
								                <div id="pay" class="collapse">
								             
                              <a href="/customers/pay" class="btn"id="smallfont" >유료결제</a><br/>
                                <a href="/customers/powerlink" class="btn"id="smallfont" >파워링크 결제</a><br/>
                                <a href="/customers/itemplus" class="btn"id="smallfont" >품목 확장 결제</a> 
                           		                </div>
								            </div>
								    
								            
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#go" id="bigfont">주문|배송</a>
								                <div id="go" class="collapse">
								             
                              <a href="/customers/delivering" class="btn"id="smallfont" >주문접수 및 배송현황</a><br/>
                                <a href="/customers/deliverout" class="btn"id="smallfont" > 주문 취소 </a><br/>
                           		                </div>
								            </div> 
								              <div class="vertical-menu-item">
		                                    <a  href="#" class="btn" data-toggle="collapse" data-target="#sellerQna"id="bigfont">관리자</a>
		                                    <div id="sellerQna" class="collapse">
		                                <a href="/board/sellerQna" class="btn"id="smallfont" >관리자 문의게시판</a><br/>
		                                </div>
                                    </div>
					        </div>
			    </div>
      <table class="main-table"  >
            <td class="graph-and-summary">
		 		  <table class="summary-table" >
		 		  			<tr>
		 		  			<td>
		 	
									
		<c:if test="${stockcount==0}">
		    <table >
		        <tr>
		            <td align="center">판매중인 상품이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
			
		<c:if test="${stockcount >  0}">		
			<h3 align="center">판매중인 상품 재고 : ${stockcount} 건 </h3> 
			<table >		
				<tr height="30"> 				
					<td  align="center">썸네일 사진</td>
					<td   align="center">상품 번호</td>
					<td  align="center">제품 이름</td>
					<td   align="center">판매 상태</td>
					<td align="center">등록 재고</td>				
					<td align="center">재고 변경</td>	
					<td align="center">변경하기</td>	
					<td   align="center">판매량</td>									
					<td   align="center">남은재고</td>									
				</tr>
				
				<c:forEach var="product" items="${stockonlist}" varStatus="loopStatus">
					<c:set var="i" value="${loopStatus.index}" />			 			 			   				             		
					<c:set var="nam" value="${product.stock - aree[i]}" />			 			 			   				             		
            		 <tr align="center">
            		 	<td><img src="<%= request.getContextPath() %>/resources/file/product/${product.p_num}/${product.thumb}/" alt="썸네일"></td>
            		 	<td>${product.p_num}</td>
            		 	<td>${product.p_name}</td>
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
		 		  			</td>
		 		  			</tr>			
		 		   			<tr>
		 		   			<td>
		 		   				
 				
 				
 				
 				
		 		   			
		 		   			</td>
		 		   			
		 		   			</tr>
							 
							
							
							
							
 													<tr> <td>
					      					   <div id="graph-container">
														
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
		
		
												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>