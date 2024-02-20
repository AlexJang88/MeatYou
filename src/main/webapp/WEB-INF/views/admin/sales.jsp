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
<body >
<div class="bodyArea"> 
<div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2 id="getYear" class="out-table">매출 요약정보</h2><br/> 
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
        <tr>
            <td class="graph-and-summary">
            				
            				<h2  style="margin-top:2%;">
            				<c:if test="${check!=100}">
						<c:choose>
							<c:when test="${not empty currentMonth and currentMonth <= 0}">
								<c:out value="${currentYear - 1}" />년 
								<c:out value="${currentMonth + 12}" />월 매출 현황
					    	</c:when>
								<c:otherwise>
									${currentYear}년 ${currentMonth}월 매출 현황
						    	</c:otherwise>
						</c:choose>
					</c:if>
					</h2>
					<br/>
            		
							
							
							<c:if test="${check<=0}">
								<a href="/admin/sales?check=${check-1}">   이전달</a>
							</c:if>
									<c:if test="${check!=0}">
								<a href="/admin/sales"> | 이번달 매출보기 | </a>
							</c:if>
							<c:if test="${check<0}">
								<a href="/admin/sales?check=${check+1}">다음달</a>
							</c:if>
				
 						 
				
            				<br/>
					
					
					매출연도 검색 : <input name="startYear" id="startYear" class="date-picker-year" type="text"   />  
					<button id="Serch_btn">검색</button>
				            				
            
            
                    	<div  id="graph-container">
							<canvas id="sales-chart"></canvas>
						</div>
                
                
			                <table class="summary-table" >
											<tr>
												<td>총 매출</td>
												<td>순 매출</td>
												<td>판매 수수료 수익</td>
												<td>유료결제(품목) 수익</td>
												<td>유료결제(상위노출) 수익</td>
												<td>쿠폰 지출</td>
											</tr>
											<tr>
											  <td>${total}</td>
										      <td>${net_profit}</td>
										      <td>${productComm}</td>
										      <td>${item}</td>
										      <td>${Adv}</td>
										      <td>${coupon}</td>
											</tr>
										</table>
										
										
										 <form method="post" action="/admin/sales">
											<input type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" /> <input type="text" name="daterange" >
											<input type="hidden" name="check" value="100"> 
											<input type="submit" value="검색">
										</form>
        
            </td>
        </tr>
           
    </table>
    
   
 

</div>


</div>
</body>
</html>
 

<%-- <div class="indcludFooter" style="display:none;"> 
<%@ include file="../footer.jsp" %>
</div> --%>
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
		
 
			<script src="/resources/js/bootstrap.min.js"></script>
<script type="text/javascript">
$('#Serch_btn').on('click',function(){
    var period = $('#startYear').val();
    $.ajax({
        url: '/admin/getChartData', // 서버의 데이터 엔드포인트
        method: 'GET',
        data: { period: period },
        dataType: 'json', // 응답 데이터 타입
        success: function (data) {
            // ArrayList를 JavaScript 배열로 변환
            var total_profit = data.total_profit;
            var net_profit = data.net_profit;
            var co_pay = data.co_pay;
            var cp_price = data.cp_price;
            var getYear = data.getYear;
            // 데이터를 그래프에 적용
            
            update(total_profit,net_profit,co_pay,cp_price,getYear); // 그래프 업데이트
        },
        error: function (xhr, status, error) {
            console.error('데이터 가져오기 실패:', error);
        }
    });
});

        $(function() {
            $('.date-picker-year').datepicker({
                changeYear: true,
                showButtonPanel: true,
                dateFormat: 'yy',
                onClose: function(dateText, inst) { 
                    var year = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
                    $(this).datepicker('setDate', new Date(year, 1));
                }
            });
        $(".date-picker-year").focus(function () {
                $(".ui-datepicker-month").hide();
            });
        });

	$(document).ready(
			function() {
				$('input[name="daterange"]').daterangepicker(
						{
							opens : 'left'
						},
						function(start, end, label) {
							console.log("A new date selection was made: "
									+ start.format('DD-MM-YYYY') + ' to '
									+ end.format('DD-MM-YYYY'));
						});
			});
</script>
<script>
    // AJAX 요청 보내기
    window.onload = function () {
        $.ajax({
            url: '/admin/getChartData', // 서버의 데이터 엔드포인트
            method: 'GET',
            dataType: 'json', // 응답 데이터 타입
            success: function (data) {
                // ArrayList를 JavaScript 배열로 변환
            	var total_profit = data.total_profit;
            	var net_profit = data.net_profit;
            	var co_pay = data.co_pay;
            	var cp_price = data.cp_price;
            	var getYear = data.getYear;
            // 데이터를 그래프에 적용
            update(total_profit,net_profit,co_pay,cp_price,getYear); // 그래프 업데이트
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    }

    function update(d1, d2,d3,d4,d5) {
    	 $('#sales-chart').remove();
         $('#graph-container').append('<canvas id="sales-chart"></canvas>');
        var ctx = document.getElementById('sales-chart').getContext('2d');
        var salesChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                datasets: [
                    {
                        label: '총 매출',
                        backgroundColor: 'rgb(0, 0, 255)',
                        borderColor: 'rgb(0, 0, 255)',
                        data: d1
                    },
                    {
                        label: '판매수수료 매출', // Name of the new data
                        backgroundColor: 'rgb(0,128,0)', // Blue color for the dots
                        borderColor: 'rgb(0,128,0)', // Blue color for the line
                        data: d2 // Example data for the new dataset
                    },
                    {
                        label: '유료결제 매출', // Name of the new data
                        backgroundColor: 'rgb(255,165,0)', // Blue color for the dots
                        borderColor: 'rgb(255,165,0)', // Blue color for the line
                        data: d3 // Example data for the new dataset
                    },
                    {
                        label: '쿠폰 지출', // Name of the new data
                        backgroundColor: 'rgb(255,0,0)', // Blue color for the dots
                        borderColor: 'rgb(255,0,0)', // Blue color for the line
                        data: d4 // Example data for the new dataset
                    }
                ]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true,
                        title: {
                            display: true,
                            text: '단위(원)'
                        }
                    }
                },
                plugins: {
                    title: {
                        display: true,
                        text: d5+'년 매출통계'
                    },
                    legend: {
                        display: true
                    }
                }
            }
        });
    }
</script>