<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/customers/powerlink.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>

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
                                       <a href="/customers/onStock" class="btn"id="smallfont" >판매중  재고</a>
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

		 		  		
		 		  		
		 		  		</td>
		 		  	</tr>
		 		  
		 		  			<tr>
		 		  			<td>
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
		 		  		 

	< 
	
	
	
		
		 		  			</td>
		 		  			</tr>			
		 		   			
							 
							
							
							
							
 													<tr> 
 													<td>
					      					   <div id="graph-container">
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

												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>
