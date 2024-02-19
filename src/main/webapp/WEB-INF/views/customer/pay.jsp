<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/customers/profit.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
  
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
		 
		 
<h2 align="center">유료결제된 항목 보기</h2>
		 
		 </td>
		 </tr>
		 		  			<tr>
		 		  			<td>
		 		  			
<h2 align="center">파워링크</h2>
	<c:if test="${powerPayCount==0 }">
		 <table >
	
		     <tr>
		         <td align="center">결제된 노출 상품이 없습니다!</td>
		     </tr>
		 </table>
	</c:if>
	
	<c:if test="${powerPayCount > 0 }">		
		<h3 align="center">총 결제 진행 횟수 : ${powerPayCount}  </h3> 
		<table border="1" width="900" cellpadding="0" cellspacing="0" align="center">		
			<tr height="30">				
				<td width="150" align="center">구매자아이디</td>
				<td width="150" align="center">등록된 상품번호</td>
				<td width="200" align="center">결제 금액</td>
				<td width="200" align="center">남은 클릭 갯수</td>				
				<td width="200" align="center">결제일</td>				
			</tr> 
			<c:forEach var="pay" items="${powerlist}" varStatus="loop">
			 	<c:if test="${loop.index lt 5}">
					  <tr align="center">
						 <td>${pay.co_m_id}</td>			
						 <td>${pay.co_p_num}</td>
						 <td><fmt:formatNumber value="${pay.co_pay}" type="number" pattern="#,##0"/></td>	
						 <td>${pay.co_quantity}</td>
						 <td>
		                    <fmt:formatDate value="${pay.co_paydate}" pattern="yyyy-MM-dd" />
		                </td>			 
					  </tr>
				</c:if>				
			</c:forEach>			
		</table>		
		  <c:if test="${powerPayCount >= 6 }">				
			<h3 align="center"> <a href="/customers/payOne?pageNum=${pageNum}">파워링크 구매기록 더보기 </a> </h3>				
		  </c:if>			
	 </c:if>
	
<br/>

 <h2 align="center">품목 추가 유료결제 </h2>
 	<c:if test="${listPayCount==0}">
		 <table >
		     <tr>
		         <td align="center">결제된 품목 확장 상품이 없습니다!</td>
		     </tr>
		 </table>
	</c:if>
	
  	<c:if test="${listPayCount > 0}"> 
		<h3 align="center">총 결제 진행 횟수 : ${listPayCount}  / 현재 사용가능한 유료 품목 수 : ${listpaynowcount} </h3> 
		<table border="1"  >		
			<tr height="30">				
				<td width="150" align="center">구매자아이디</td>
				<td width="150" align="center">등록된 상품번호</td>
				<td width="200" align="center">사용한 금액</td>
				<td width="200" align="center">결제일</td>				
				<td width="200" align="center">종료일</td>				
			</tr>
			<c:forEach var="pay" items="${paylist}" varStatus="loop">
			 	<c:if test="${loop.index lt 5}">
				  <tr align="center">
					 <td>${pay.co_m_id}</td>
					 <td>${pay.co_p_num}</td>
					 <td><fmt:formatNumber value="${pay.co_pay}" type="number" pattern="#,##0"/></td>	
					 <td>
	                    <fmt:formatDate value="${pay.co_paydate}" pattern="yyyy-MM-dd" />
	                </td>
	                <td>
	                    <fmt:formatDate value="${pay.co_payenddate}" pattern="yyyy-MM-dd" />
	                </td>			 			 			 
				  </tr>
			  </c:if>
			</c:forEach> 
		</table>
		<c:if test="${listPayCount >= 6 }">				
			<h3 align="center"> <a href="/customers/payTwo?pageNum=${pageNum}"> 품목 확장 구매 기록 더보기 </a> </h3>				
		</c:if>
	</c:if>





												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>
