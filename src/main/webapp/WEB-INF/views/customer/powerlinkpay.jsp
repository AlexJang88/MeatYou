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

 
<script>
   $("#btn_kakao-pay").click(function(){
      var totalpay =$('#totalpay').val();
      var p_num=$('#p_num').val();
      var quantity=$('#quantity').val();
      var co_num=$('#co_num').val();
      var co_name = '파워링크('+quantity+'회)';
       
      kakao(totalpay,p_num,quantity,co_num,co_name);
   });  
      function kakao(totalpay,p_num,quantity,co_num,co_name){
      // 카카오페이 결제전송
      $.ajax({
         type:'POST'
         ,url:'/kakaopay/ready'
         ,data: {
          totalpay:totalpay,
          p_num:p_num,
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

		 		  			 <h3 align="center">해당 상품으로 결제를 진행합니다!</h3> 
  <h3 align="center">비용은 클릭당 100원으로 진행됩니다!</h3> 
		 		  		
		 		  		</td>
		 		  	</tr>
		 		  
		 		  			<tr>
		 		  			<td>
							   <table  >            
							      <tr height="30"> 
							         <td width="300" align="center">썸네일 사진</td>
							         <td width="200" align="center">상품번호</td>
							         <td width="300" align="center">제품이름</td>                        
							         <td width="200" align="center">현재상태</td>            
							         <td width="200" align="center">클릭수</td>                     
							         <td width="100" align="center"> 비용</td>                  
							         <td width="100" align="center"> 결제</td>                  
							      </tr>         
							            <input type="hidden" id="p_num" name="p_num" value="${payMentItem.p_num}">   
							            <input type="hidden" id="totalpay" name="clickpay" value="${clickpay}">   
							            <input type="hidden" id="id" name="id" value="${id}">                 
							            <input type="hidden" id="quantity" name="quantity" value="${quantity}">                 
							            <input type="hidden" id="co_num" name="co_num" value="${co_num}">                 
							         <tr align="center">         
							              <td><img src="<%= request.getContextPath() %>/resources/file/product/${payMentItem.p_num}/${thumb}/" alt="썸네일"></td>
							              <td>${payMentItem.p_num} </td>
							              <td>${p_name}</a></td>
							              <td>
							              <c:choose>
							               <c:when test="${payMentItem.p_status == 0}">판매중</c:when>
							               <c:when test="${payMentItem.p_status == 1}">판매중(품목결제 상품)</c:when>                            
							               <c:when test="${payMentItem.p_status == 2}">판매대기</c:when>
							               <c:when test="${payMentItem.p_status == 3}">판매종료</c:when>                            
							              </c:choose>
							            </td>            
							            <td valign="top">
							                <c:choose>
							                  <c:when test="${clickpay == 10000}">  110회 </c:when>
							                  <c:when test="${clickpay == 30000}"> 330회</c:when>
							                  <c:when test="${clickpay == 50000}"> 565회</c:when>
							                  <c:when test="${clickpay == 100000}">1160회</c:when>                                        
							                  <c:when test="${clickpay == 200000}">2400회</c:when>                                        
							               </c:choose>
							            </td>
							            <td>${clickpay}원</td>   
							                <td>                           
							                <input type="button" id="btn_kakao-pay" value="결제하기">
							             </td>                 
							            </tr>
							                                     
							      </table>   
		<tr><td>

							      <h3 align="center">파워링크 결제 후 수정 및 환불은 불가합니다..!</h3> 
		
		 		  			</td>
		 		  			</tr>			
		 		   			
							 
							
							
							
							
 													<tr> 
 													<td>
			 

												 
												</div>
													</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>

</html>