
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>


<!DOCTYPE html>
<html>
<head>


 <head>
    <link rel="stylesheet" href="/resources/customers/consumerList.css"> <!-- 외부 스타일시트 추가 -->	
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
		
	

		 		  			</td>
		 		  			</tr>			
		 		   			<tr>
		 		   			<td>
		 		   				
 				
 				
 				
 				
		 		   			
		 		   			</td>
		 		   			
		 		   			</tr>
							 
							
							
							
							
 													<tr> <td>
									      					   <div id="graph-container">
																									
												<c:if test="${count>0}">
										<c:if test="${startPage>10}">
								        	<a href="/customers/CouponList?pageNum=${startPage-10}">[이전]</a>
										</c:if>
										<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
								        	<a href="/customers/CouponList?pageNum=${i}">[${i}]</a>
										</c:forEach>
											<c:if test="${endPage<pageCount}">
								        	<a href="/customers/CouponList?pageNum=${startPage+10}">[다음]</a>
										</c:if>
								</c:if>
							
									
		
												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>






