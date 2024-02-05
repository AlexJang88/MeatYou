<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>|
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>|
<a href="/admin/memberlist?check=3">판매자 승인대기</a>|
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>|
<a href="/admin/sales">매출보기</a>|
<a href="/admin/reckon">정산내역 확인</a>|
<a href="/admin/noticeList">공지사항</a>|
<a href="/admin/reportList">신고글 보기</a>|
<a href="/admin/productList">상품목록보기</a>


<h2 id="getYear">매출 요약정보</h2>
<div style="width: 60%; height: 50%;" id="graph-container">
		<canvas id="sales-chart"></canvas>
	</div>
<table>
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
            update(total_profit,net_profit,co_pay,cp_price); // 그래프 업데이트
            	$('#getYear').text(getYear+" 년 매출 요약정보");
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
<br /><%@ include file="../footer.jsp" %>
