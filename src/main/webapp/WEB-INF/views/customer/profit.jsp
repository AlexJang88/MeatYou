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
 
</head>
 
<body>
 
<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
  });
});
</script>
</body>
</html>

<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
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
		 		  			
		 		  				<c:choose>
    <c:when test="${not empty currentMonth and currentMonth <= 0}">
        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 매출 확인</h2>
    </c:when>
    <c:otherwise>
        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 매출 확인</h2>
    </c:otherwise>
</c:choose><br/>		
									<c:if test="${check==1}">
										<a href="/customers/profit">이번달 매출보기 </a>
									</c:if>
									<c:if test="${check<=0}">
									<a href="/customers/profit?check=${check-1}">이전달</a>
									</c:if>
									<c:if test="${check<0}">
									<a href="/customers/profit?check=${check+1}">다음달</a>
									</c:if>
									<br />
									
									<c:if test="${ptm==0}">
										 <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
										    <tr>
										        <td align="center">결재 내역이 없습니다!</td>
										    </tr>
										 </table>
									</c:if>
									
		 		  			</td>
		 		  			</tr>			
		 		   			
							 
							
							
							
							
 													<tr> <td>
					      					   <div id="graph-container">
													
									
										<c:if test="${ptm>0}">
										<table >
											<tr height="30"> 
												<td width="200" align="center">매출액</td>
												<td width="200" align="center">전체판매수량</td>
												<td width="200" align="center">쿠폰사용료</td>
												<td width="200" align="center">배송비</td>
												<td width="200" align="center">정산 금액</td>				
												<td width="200" align="center">이번달 판매 HOT </td>				
												<td width="200" align="center">파워링크</td>					
											</tr>
											<tr>
												<td align="center"><fmt:formatNumber value="${ptm}" type="number" pattern="#,##0"/> 원</td>
												
												<td align="center">${totalCount}</td> 
												<td align="center"><fmt:formatNumber value="${coponPay}" type="number" pattern="#,##0"/> 원</td> 
												<td align="center"><fmt:formatNumber value="${deliveryPay}" type="number" pattern="#,##0"/> 원</td>
												<c:if test="${check == 0}">
													<td align="center">정산 진행 중</td>
												</c:if>
												<c:if test="${check < 0}">				
													<td align="center"><fmt:formatNumber value="${(ptm * 0.9) - deliveryPay - coponPay}" type="number" pattern="#,##0"/> 원</td>
													</td>
												</c:if>
												
												<td align="center">${HOT}</td>
												<td align="center"><a href="/customers/pay">파워링크 결제하기</a></td>
												
											</tr>
										</table>
									
										* 정산예정금액은 총 매출액에서 수수료 10% 제외한 후 배송비, 쿠폰사용료를 제외하고 정산됩니다.
										</c:if>
										
												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>