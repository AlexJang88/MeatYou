<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/customers/itemplus.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>  
<meta charset="UTF-8">
<script>
   $("#btn_kakao-paytwo").click(function(){
      var totalpay ="50000";  
      var quantity="1";
      var co_num=$('#co_num').val();
      var co_name = '품목결제('+quantity+'건)';
      
      console.log('totalpay:'+totalpay);
      console.log('quantity:'+quantity);
      console.log('co_num:'+co_num);
      console.log('co_name:'+co_name);
      kakao(totalpay,quantity,co_num,co_name);
   });  
      function kakao(totalpay,quantity,co_num,co_name){
      // 카카오페이 결제전송
      $.ajax({
         type:'POST'
         ,url:'/kakaopay/readytwo'
         ,data: {
          totalpay:totalpay,
          quantity:quantity,
          co_num:co_num,
          co_name:co_name
         }
         ,success:function(response){
             alert(response.next_redirect_pc_url);
            location.href = response.next_redirect_pc_url;         
         }
      })
      }
      // 버튼 클릭이벤트 해제
   
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
<h1> 품목확장 결제페이지</h1>
		 		  				 		  	<h3 align="center">${id} 판매자님의 아이디로 품목확장 1회가 결제됩니다.</h3> 
<a align="center">* 결제 후 상품목록 페이지로 이동하여 원하시는 상품을 추가해주세요..!</a> 
		 		  		
		 		  		
		 		  		</td>
		 		  	</tr>
		 		  
		 		  			<tr>
		 		  			<td>

<table  >		
	<tr height="30"> 
		<td width="300" align="center">결제자이름</td>
		<td width="200" align="center">상품종류</td>
		<td width="300" align="center">결제금액</td>
		<td width="200" align="center">기간</td>				
		<td width="200" align="center">환불여부</td>				
		<td width="100" align="center">결제하기</td>			
	</tr>
	
	 <tr height="30"> 
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <input type="hidden" id="id" name="p_m_id" value="${id}">	
      <input type="hidden" id="co_num" name="co_num" value="${co_num}">	
      <input type="hidden"id="quantity" name="quantity" value="${quantity}">	
		<td width="300" align="center">${id}</td>
		<td width="200" align="center">품목확장 1건</td>
		<td width="300" align="center">50,000</td>
		<td width="200" align="center">1개월 </td>				
		<td width="200" align="center">불가</td>					
		<td align="center" >   
			<input type="button" id="btn_kakao-paytwo" value="결제하기">
		 </td> 
	 </tr>	
</table>	

	
		
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
