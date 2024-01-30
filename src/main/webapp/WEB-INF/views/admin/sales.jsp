<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<style>
        /* Optional: Style for the datepicker */
        .ui-datepicker-calendar {
            display: none;
        }
</style>
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>
<a href="/admin/memberlist?check=3">판매자 승인대기</a>
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>
<br />
<a href="/admin/sales">매출보기</a>
<br>
<h2>매출 요약정보</h2>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	매출연도 검색 : <input name="startYear" id="startYear" class="date-picker-year" readonly />  <button id="Serch_btn">검색</button>
	
	<div style="width: 60%; height: 50%;" id="graph-container">
		<canvas id="sales-chart"></canvas>
	</div>
<form method="post" action="/admin/sales">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="text" name="daterange" >
	<input type="hidden" name="check" value="100"> 
	<input type="submit" value="검색">
</form>

<c:if test="${check==1}">
	<a href="/admin/sales">이번달 매출보기 </a>
</c:if>
<c:if test="${check<=0}">
	<a href="/admin/sales?check=${check-1}">이전달</a>
</c:if>
<c:if test="${check!=100}">
	<c:choose>
		<c:when test="${not empty currentMonth and currentMonth <= 0}">
			<c:out value="${currentYear - 1}" />년 
			<c:out value="${currentMonth + 12}" />월 매출 현황
    </c:when>
		<c:otherwise>
			<c:out value="${currentYear}" />년 <c:out value="${currentMonth}" />월 매출 현황
    </c:otherwise>
	</c:choose>
</c:if>
<c:if test="${check==100}">
검색결과 <a href="/admin/sales">이번달 매출보기 </a>
</c:if>
<c:if test="${check<0}">
	<a href="/admin/sales?check=${check+1}">다음달</a>
</c:if>
<br />
<table>
	<tr>
		<td>이번달 총 매출</td>
		<td>이번달 총 상품 판매금액</td>
		<td>이번달 총 상품 수수료</td>
		<td>이번달 유료결제(품목) 금액</td>
		<td>이번달 유료결제(상위노출) 금액</td>
		<td>이번달 쿠폰 사용금액</td>
	</tr>
	<tr>
		<td>${pt}</td>
		<td>${ps}</td>
		<td>${pc}</td>
		<td>${pi}</td>
		<td>${pa}</td>
		<td>${pc}</td>
	</tr>
</table>
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

            // 데이터를 그래프에 적용
            update(total_profit,net_profit,co_pay,cp_price); // 그래프 업데이트
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

            // 데이터를 그래프에 적용
            update(total_profit,net_profit,co_pay,cp_price); // 그래프 업데이트
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    }

    function update(d1, d2,d3,d4) {
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
                        text: '연별 매출통계'
                    },
                    legend: {
                        display: true
                    }
                }
            }
        });
    }
</script>

