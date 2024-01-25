<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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


<h1>판매자 페이지 업무</h1>

<div style="width: 600px; height: 400px;">
    <canvas id="sales-chart"></canvas>
</div>






</body>

<script>
        var ctx = document.getElementById('sales-chart').getContext('2d');
        var salesChart = new Chart(ctx, {
            type: 'line', // The type of chart we want to create
            data: { // Data for our dataset
                labels: ['January', 'February', 'March', 'April', 'May', 'June'], // Example labels
                datasets: [{
                    label: '총 판매 금액', // Name of the data
                    backgroundColor: 'rgb(255, 99, 132)', // Color of the dots
                    borderColor: 'rgb(255, 99, 132)', // Color of the line
                    data: [0, 10000, 5000, 15000, 20000, 30000], // Example sales data
                }]
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
                        text: '월별 매출통계'
                    },
                    legend: {
                        display: true
                    }
                }
            }
        });
    </script>
</html>

