<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../header.jsp" %>
    <link rel="stylesheet" href="/resources/board/css/sellerContent.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
							
							<table class="summary-table" style="margin-bottom:0;">
							            		 <tr>
														<h1>판매자 - 관리자 문의하기</h1> 
															<a href="/board/sellerQna?pageNum=${pageNum}" style="right:0;"> <  목록으로</a>  <br/>
													</tr>
																						
										 	<tr>
									 		  	<c:if test="${m_Status ge 2000 and m_Status le 7000}">
									 		  			<a href="/board/sellerQna">판매자용 문의하기</a> <br/>
														</c:if>
														</tr>
								 
							
												<tr> 
													<td>문의한사람</td>		
													<td>문의 날짜</td>	
													<td>상품이름</td>
													
											 </tr>
													
													<tr>
															<td>${content.ma_m_id}</td>
																<td><fmt:formatDate value="${content.ma_reg_date}" pattern="yyyy-MM-dd" /></td>	
															<td>${content.ma_title}</td>
									
													</tr>
												</table>
												<table class="summary-table" style="margin-bottom:2%; margin-top:0">
														<tr>
														</tr>
								 
													<td>문의내용</td>
														</tr>
														<tr>
														<td>${content.ma_content} </td>
													</td>
														</tr>
												</table>
										 
												<table class="summary-table">
														<tr>
														</tr>
											 				   <div id="graph-container">
																<c:if test="${contentView.ma_status >=1}">
																		<tr>
																			<td>${contentView.ma_title}</td>
																		</tr>
																		<tr>
																			<td>${contentView.ma_content}</td>
																		</tr>
															</c:if>
											
															</div>	  
													</td>
														</tr>
												</table>
									<c:if test="${answerCount <2}">
										<c:if test="${m_Status == 7999}">
														<form method="post" action="sellerContentPro">
															<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
															<input type="hidden" name="ma_ref" value="${content.ma_ref}">
															<input type="hidden" name="ma_num" value="${content.ma_num}">
															<input type="hidden" name="pageNum" value="${pageNum}">
												
															 <tr>
															 	<td width="150">답글달기</td>
															 	<td>
																 <textarea rows="7" cols="70" name="ma_content" required="required"></textarea>
															 	</td>
															 </tr>
															 <tr>
															 	<td colspan="2"><input type="submit" value="답글달기"> </td>
															 </tr>
														</form>	
										</c:if>
									</c:if>
									      </td>
									   </tr> 							   
									 		  		<tr> 
									 		  		<td>
									 		  		<a href="/board/questionSA">판매자관리자에게문의글쓰기</a> <br/>
									 		  		</td>
									 		  		</tr>
		            				<tr> <td>
												</td>
										</tr>		
		</table>
      
 
</div>

<%@ include file="../../footer.jsp" %>











