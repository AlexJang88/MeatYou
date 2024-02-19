<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/admin/css/memberList.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
 





		
		





<div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2 id="getYear" class="out-table">
<a href="/customers/itemListOut">판매 종료된 상품</a> 
</div>

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
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">결제</a>
								                <div id="check" class="collapse">
								             
                              <a href="/customers/pay" class="btn"id="smallfont" >유료결제</a><br/>
                                <a href="/customers/powerlink" class="btn"id="smallfont" >파워링크 결제</a><br/>
                                <a href="/customers/itemplus" class="btn"id="smallfont" >품목 확장 결제</a> 
                           		                </div>
								            </div>
								            
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">결제</a>
								                <div id="check" class="collapse">
								             
                              <a href="/customers/pay" class="btn"id="smallfont" >유료결제</a><br/>
                                <a href="/customers/powerlink" class="btn"id="smallfont" >파워링크 결제</a><br/>
                                <a href="/customers/itemplus" class="btn"id="smallfont" >품목 확장 결제</a> 
                           		                </div>
								            </div>
								            
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">주문|배송</a>
								                <div id="check" class="collapse">
								             
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
		 		  
		 		  								<tr><td>	
		<h2>여기는 ${memId} 님 의 판매중인상품 목록</h2>
				<c:if test="${count==0}">
				    <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
				        <tr>
				            <td align="center">판매중인 상품이 없습니다!</td>
				        </tr>
				    </table>
				</c:if>
				</td>
							</tr>
		 		  		
		 		  
		 		  
							<tr><c:if test="${count >  0}">		
							
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
	            		 		<td><fmt:formatNumber value="${product.p_price}" type="number" pattern="#,##0"/></td>            		 		
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
							</tr>
							
							
 													<tr> <td>
					      					   <div id="graph-container">
													
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

												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>