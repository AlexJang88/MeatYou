<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript"  src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript"  src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css"  href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <link rel="stylesheet" href="/resources/admin/css/sales.css"> <!-- 외부 스타일시트 추가 -->	
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
<style>
 .fontone:hover{
    	font-weight:bold;
    }</style>
<body>
 <div style="margin-top: 40px; display:flex; margin-bottom: 30px;">
    <h2  class="out-table">공지 사항</h2>  
 
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
														<td>제목</td>
														<td>등록일</td>
														<td>삭제</td>
													</tr>
													<c:forEach items="${list}" var="notice">
														<form action="/admin/noticedelete" method="post">
															<tr>
																<td><a href="/admin/noticeContent?num=${notice.n_num}">${notice.n_title}</a></td>
																<td><fmt:formatDate value="${notice.n_reg_date}"
																		pattern="yyyy-MM-dd" /></td>
																<td><input type="hidden" name="n_num" value="${notice.n_num}">
																	<input type="submit" value="삭제"></td>
															</tr>
														</form>
													</c:forEach>
													<tr>
													  <td colspan="3">
										        <button class="newW" style="float: right; border: none; background-color: transparent; cursor: pointer;" onclick="window.location='/admin/noticeForm'">
										         <a class="fontone">글 쓰기</a>
										        </button>
												    </td>
												    
												    </tr>
												</table>
												
						 				
								
					      					   <div id="graph-container">
													<c:if test="${count>0}">
														<c:if test="${startPage>10}">
															<a href="/admin/noticeList?pageNum=${startPage-10}">[이전]</a>
														</c:if>
														<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
															<a href="/admin/noticeList?pageNum=${i}">[${i}]</a>
														</c:forEach>
														<c:if test="${endPage<pageCount}">
															<a href="/admin/noticeList?pageNum=${startPage+10}">[다음]</a>
														</c:if>
													</c:if>
												</div>
			
			   </td>
		</table>
      
 
</div>
</body>
</html>
<jsp:include page="/WEB-INF/views/footer.jsp" />
<!-- FOOTER -->
			<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>


						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		<!-- /FOOTER -->

</body>
</html>

