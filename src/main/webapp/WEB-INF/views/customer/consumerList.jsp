<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
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
<html>
<head>
    <title>구매 회원 고객리스트</title>
    <script>
 // 썸머노트 초기화
    $('#summernote').summernote();

    // 현재 커서 위치 가져오기
    function getCurrentCursorPosition() {
      var range = $('#summernote').summernote('focus').summernote('getRange');
      var node = range.sc;
      var offset = range.so;
      
      console.log('현재 커서 위치 - 노드:', node, '오프셋:', offset);
    }

    // 예제: 버튼 클릭 시 현재 커서 위치 출력
    $('#getCursorPositionButton').on('click', function() {
      getCurrentCursorPosition();
    });
    </script>
</head>
<body onscroll="return false;">
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
		 	
		<c:choose>
	    <c:when test="${not empty currentMonth and currentMonth <= 0}">
	        <h2><c:out value="${currentYear - 1}"/>년 <c:out value="${currentMonth + 12}"/>월 구매 회원 확인</h2>
	    </c:when>
	    <c:otherwise>
	        <h2><c:out value="${currentYear}"/>년 <c:out value="${currentMonth}"/>월 구매 회원 확인</h2>
	    </c:otherwise>
	</c:choose>
	
	<c:if test="${check==1}">
		<a href="/customers/consumerList">이번달 구매회원 </a>
	</c:if>
	<c:if test="${check<=0}">
	<a href="/customers/consumerList?check=${check-1}">이전달</a>
	</c:if>
	<c:if test="${check<0}">
	<a href="/customers/consumerList?check=${check+1}">다음달</a>
	</c:if>
	<br />


    
    <c:if test="${count==0}">
	  <table width="700" border="1" cellpadding="0" cellspacing="0" align="center">
		 <tr>
		     <td align="center">아직 상품을 구매한 고객이 없습니다!</td>
		 </tr>
	  </table>
	</c:if>
    
    
    
    
  <c:if test="${count>0}">
  	 구매를 진행한 회원 목록 : ${count} 
  	 <table  >
		 <tr height="30"> 
			<td   align="center">구매자이름</td>
			<td   align="center">상품 번호</td>
			<td   align="center">구매 상품 제목</td>
			<td   align="center">결제 갯수</td>
			<td  align="center">결제금액</td>
			<td   align="center">결제 날짜</td>				
			<td  align="center">환불 여부</td>				
			<td   align="center">회원 등급</td>			
			<td   align="center">쿠폰 주기</td>						
		</tr>
		<c:forEach var="mber" items="${memberlist}">
			<form action="/customers/cusCoupon" method="post">
				 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				 <input type="hidden" name="p_m_id" value="${mber.order_m_id}">				 
				<tr align="center">
				 	<td>${mber.order_m_id}</td>
				 	<td>${mber.p_num}</td>
				 	<td><a href="/customers/content?p_num=${mber.p_num}">${mber.p_name}</a>	</td>
				 	<td>${mber.order_quantity}</td>
				 	<td><fmt:formatNumber value="${mber.order_totalprice}" type="number" pattern="#,##0"/></td>		
				 	<td><fmt:formatDate value="${mber.order_paydate}" pattern="yyyy-MM-dd" /></td>
				 	<td> 
				 		<c:choose>
							<c:when test="${mber.order_status == 0}">결제취소</c:when>
							<c:when test="${mber.order_status == 1}">결제완료</c:when>							       
							<c:when test="${mber.order_status == 2}">배송중</c:when>
							<c:when test="${mber.order_status == 3}">배송완료</c:when>							       
							<c:when test="${mber.order_status == 4}">구매확정</c:when>							       
						</c:choose>				 	
				 	</td>
				 	<td>${mber.mstat_detail}</td>
				 	<td align="center" >
				 		<input type="submit" value="쿠폰주기">
				 	</td>
				</tr>
			</form>
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
														
																		
    <c:if test="${count>0}">
		<c:if test="${startPage>10}">
	       	<a href="/customers/consumerList?pageNum=${startPage-10}">[이전]</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        <a href="/customers/consumerList?pageNum=${i}">[${i}]</a>
		</c:forEach>
			<c:if test="${endPage<pageCount}">
	        <a href="/customers/consumerList?pageNum=${startPage+10}">[다음]</a>
		</c:if>
	</c:if>





		
												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>