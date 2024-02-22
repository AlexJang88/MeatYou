<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sales Graph</title>
<!-- Include Chart.js from CDN -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<div style="width: 600px; height: 400px;">
    <canvas id="sales-chart"></canvas>
</div>
<script>
    // AJAX 요청 보내기
    window.onload = function () {
        $.ajax({
            url: '/admin/getChartData', // 서버의 데이터 엔드포인트
            method: 'GET',
            dataType: 'json', // 응답 데이터 타입
            success: function (data) {
                // ArrayList를 JavaScript 배열로 변환
                var totalSalesArray = data.totalSales;
                var actualSalesArray = data.actualSales;

                // 데이터를 그래프에 적용
                update(totalSalesArray, actualSalesArray); // 그래프 업데이트
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    }

    function update(d1, d2) {
        var ctx = document.getElementById('sales-chart').getContext('2d');
        var salesChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
                datasets: [
                    {
                        label: '총 매출',
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: d1
                    },
                    {
                        label: '실 매출', // Name of the new data
                        backgroundColor: 'rgb(54, 162, 235)', // Blue color for the dots
                        borderColor: 'rgb(54, 162, 235)', // Blue color for the line
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
