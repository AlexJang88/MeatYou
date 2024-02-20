<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<html>
<head>

    <link rel="stylesheet" href="/resources/admin/css/reckon.css"> <!-- 외부 스타일시트 추가 -->	
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
<div style="margin-top: 40px; display:flex; margin-bottom: 30px;">
    <h2  class="out-table">정산내역 확인</h2>  
 
</div>





<div class="main-container" >

    <div class="category-menu">
        <!-- Your category menu content -->
        <div class="vertical-menu">
            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">회원</a>
                <div id="mem" class="collapse">
                    <a href="/admin/memberlist?check=1" class="btn" id="smallfont" >회원목록 조회(일반)</a><br/>
                    <a href="/admin/memberlist?check=2" class="btn" id="smallfont" >회원목록 조회(판매자)</a><br/>
                    <a href="/admin/memberlist?check=3" class="btn" id="smallfont" >판매자 승인대기</a><br/>
                    <a href="/admin/memberlist?check=4" class="btn" id="smallfont" >판매자(유료회원)목록</a><br/>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">상품</a>
                <div id="pro" class="collapse">
                    <a href="/admin/productList" class="btn" id="smallfont" >상품 목록 보기</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">정산</a>
                <div id="money" class="collapse">
                    <a href="/admin/sales" class="btn" id="smallfont" >매출 보기</a><br/>
                    <a href="/admin/reckon" class="btn" id="smallfont" >정산내역 확인</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">관리자 체크</a>
                <div id="check" class="collapse">
                    <a href="/admin/noticeList" class="btn" id="smallfont" > 공지 사항</a><br/>
                    <a href="/admin/reportList" class="btn"  id="smallfont" >신고글 보기</a>
                </div>
            </div>
        </div>
    </div>
    
    
    <table class="main-table"  >
            <td class="graph-and-summary">
            							
            
						 		 	 <table class="summary-table" >
						 		 	 
						 		 	 
						 		 	 						 		 	 <tr>
													 	<div style="margin-top: 10px; display: flex; margin-bottom: 10px;">
																	    <div class="out-table2">
																	        <a href="/admin/reckon?check=1" class="outa" style="margin-right: 10px;">정산중</a>
																	        <a href="/admin/reckon?check=2" class="outb" style="margin-right: 10px;">정산완료</a>
																	        <a href="/admin/reckon?check=3" class="outc">전체</a>
																	    </div>
																	</div>
													 		 	 </tr>
											 		 	 	<tr>
					 									<form action="/admin/reckon" method="post" >
																	<input type="hidden" name="${_csrf.parameterName}"
																		value="${_csrf.token}" /> <input type="hidden" name="check" value="1">
																	<select name="start" style="  margin-bottom:0%;" >
																		<option value="2022">2022년</option>
																		<option value="2023">2023년</option>
																		<option value="2024">2024년</option>
																	</select> <select name="end">
																		<option value="1">1월</option>
																		<option value="2">2월</option>
																		<option value="3">3월</option>
																		<option value="4">4월</option>
																		<option value="5">5월</option>
																		<option value="6">6월</option>
																		<option value="7">7월</option>
																		<option value="8">8월</option>
																		<option value="9">9월</option>
																		<option value="10">10월</option>
																		<option value="11">11월</option>
																		<option value="12">12월</option>
																	</select> 
																	<input type="submit" value="조회">
																</form>  
					            								  </tr>
					            								 
																	<tr   >
																			<td style=" margin-top:0;">판매자</td>
																			<td>총매출</td>
																			<td>배송비</td>
																			<td>정산금액</td>
																			<td>쿠폰할인금액</td>
																 
																		<c:forEach var="dto" items="${list}">
																			<tr>
																				<td>${dto.p_m_id}</td>
																				<td>${dto.totalprice}</td>
																				<td>${dto.order_dere_pay}</td>
																				<td>${dto.deposit}</td>
																				<td>${dto.cp_price}</td>
																			</tr>
																		</c:forEach>
																	
																	</tr>
																 
												</table>
												
						 				
								
					      					   <div id="graph-container">
														
														${month}
														<c:if test="${count>0}">
															<c:if test="${startPage>10}">
																<a href="/admin/memberlist?pageNum=${startPage-10}&check=${check}">[이전]</a>
															</c:if>
															<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
																<a href="/admin/memberlist?pageNum=${i}&check=${check}">[${i}]</a>
															</c:forEach>
															<c:if test="${endPage<pageCount}">
																<a href="/admin/memberlist?pageNum=${startPage+10}&check=${check}">[다음]</a>
															</c:if>
														</c:if>

												</div>
			
			   </td>
		</table>
</div>
</body>
</html>
<%@ include file="../footer.jsp"%>