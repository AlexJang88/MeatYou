<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>

 <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<title>판매자 페이지</title>

<html> 
<head> 
</head>
<body>

<a href="/customers/customer">홈으로</a>
<a href="/customers/itemUpdate">상품등록</a>
<a href="/customers/itemList">상품목록</a>
<a href="/customers/profit">매출현황</a>
<a href="/customers/stock">재고현황</a>
<a href="/customers/consumerList">구매회원</a>
<a href="/customers/pay">유료결제</a>
<a href="/customers/delivering">주문접수 및 배송현황</a>
<a href="/customers/cusQna">문의게시판</a>
<a href="/member/modify"></i> 마이페이지</a>

<h1>판매자 페이지 업무</h1>


총 누적 매출액 : <fmt:formatNumber value="${totalmoneysum}" type="number" pattern="#,##0"/> 원



<div>
    <label for="year"></label>
    <select id="year" name="year">
        <c:forEach var="i" begin="2023" end="2030">
            <option value="${i}">${i}년</option>
        </c:forEach>
    </select>
    <button id="Serch_btn">업데이트</button>
</div>



<div style="width: 60%; height: 50%;" id="graph-container">
		<canvas id="sales-chart"></canvas>
	</div>



</body>

<script>
var selectedYear = '';
$('#Serch_btn').on('click',function(){
	selectedYear = $('#year').val();
    $.ajax({
        url: '/customers/chartdata', // 서버의 데이터 엔드포인트
        method: 'GET',
        data: { selectedYear: selectedYear },
        dataType: 'json', // 응답 데이터 타입
        success: function (data) {
            // ArrayList를 JavaScript 배열로 변환
            var mon_sal = data.mon_sal;
            var net_profit = data.net_profit;

            // 데이터를 그래프에 적용
            update(mon_sal,net_profit, selectedYear); // 그래프 업데이트
        },
        error: function (xhr, status, error) {
            console.error('데이터 가져오기 실패:', error);
        }
    });
});


window.onload = function () {
    $.ajax({
        url: '/customers/chartdata', // 서버의 데이터 엔드포인트
        method: 'GET',
        dataType: 'json', // 응답 데이터 타입
        success: function (data) {
            // ArrayList를 JavaScript 배열로 변환
        	 var mon_sal = data.mon_sal;
            var net_profit = data.net_profit;
			var selectedYear = data.selectedYear
            // 데이터를 그래프에 적용
            update(mon_sal,net_profit, selectedYear); // 그래프 업데이트
            $('#selectedYear').text(selectedYear+"년월 매출요약");
        },
        error: function (xhr, status, error) {
            console.error('데이터 가져오기 실패:', error);
        }
    });
}

function update(d1, d2, d3) {
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
                    label: '순 매출', // Name of the new data
                    backgroundColor: 'rgb(0,128,0)', // Blue color for the dots
                    borderColor: 'rgb(0,128,0)', // Blue color for the line
                    data: d2 // Example data for the new dataset
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
                    text: d3 + '년 매출통계' 
                },
                legend: {
                    display: true
                }
            }
        }
    });
}





    </script>
</html>

