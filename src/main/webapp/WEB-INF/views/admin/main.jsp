<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>판매자 페이지</title>
    <%@ include file="../header.jsp" %>
    <script>
    $(document).ready(function () {
        // Add a click event handler for the menu items with sub-menus
        $('.vertical-menu-item a').click(function () {
            // Toggle the collapse state when the menu item is clicked
            $(this).next('.collapse').collapse('toggle');
        });
    });
    </script>
    <style>
        .main-table {
        width: 50%; /* Set the desired width */
        margin: 0 auto; /* Center-align the table */
           box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
           height: 80%;
        
    }
    .out-table{
        margin: 0 auto; /* Center-align the element */
      	 align-items: center; 
      	 justify-content: center;
      	
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
          
    }
  .summary-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        margin-right: 10px;
   
    }

    .summary-table th, .summary-table td {
        border: 1px solid #ddd;
        padding: 10px;
        text-align: center;
    }

    .summary-table th {
        background-color: #f2f2f2;
    }

    .summary-table tr:nth-child(even) {
        background-color: #f9f9f9;
    }
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }

      


       .category-menu {
       margin-top:0;
        width: 20%;
        flex-shrink: 0; /* 추가: 카테고리 메뉴가 축소되지 않도록 설정 */
    }

        .graph-container {
            width: 60%; /* Adjust the width as needed */
            order: 1; /* Set the order for the graph */
        }

        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
            font-weight: bold; /* Added font weight */
        }


        h1 {
            color: #333;
            font-weight: bold; /* Added font weight */
        }

        .sales-summary {
            margin-top: 20px;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        #sales-chart {
            flex: 1; /* Adjust the width of the graph as needed */
            height: 350px; /* Adjust the height of the graph as needed */
			margin-bottom:15%; 
			

        }
        
          .vertical-menu {
        order: -1;
        display: flex;
        flex-direction: column;
        align-items: flex-start;
        margin-right: 20px;
        width: 100%; /* 추가: 수직 메뉴 전체 너비를 100%로 설정 */
		margin-top:0;
    }

      .vertical-menu a {
        margin-bottom: 10px;
        /* width: 100%; 수직 메뉴 아이템은 전체 너비로 설정하지 않음 */
        text-align: left;
    }
 
        #category-list {
            flex: 1; /* Adjust the width of the category list as needed */
            padding: 20px;
            margin-top: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

    @media only screen and (max-width: 768px) {
            .outer-table {
                flex-direction: column;
            }


            .category-menu,
            .graph-container {
                width: 100%;
            }
        }

        @media only screen and (min-width: 769px) {

            .category-menu {
                width: 20%;
            }

            .graph-container {
                width: 80%;
            }
        }
    </style>
</head>
<body>

    <div style="display: flex;   margin-top:50px; margin-bottom:50px;">
 
      <h2 id="getYear" class="out-table">매출 요약정보</h2>
</div>
    <table class="main-table">
    
        <tr>
      
            <td class="category-menu">
                <div class="vertical-menu">
             
                     <div class="vertical-menu-item">
								        <a href="/customers/customer" class="btn" data-toggle="collapse" data-target="#mem">회원</a>
								        <div id="homeSubMenu" class="collapse">
                                    <a href="/admin/memberlist?check=1" ">회원목록 조회(일반)</a><br/>
                                 
                    <a href="/admin/memberlist?check=2" class="btn">회원목록 조회(판매자)</a>
                    <a href="/admin/memberlist?check=3" class="btn">판매자 승인대기</a>
                    <a href="/admin/memberlist?check=4" class="btn">판매자(유료회원)목록</a>
                             
                                    </div>
                
                    <a href="/admin/sales" class="btn">매출보기</a>
                    <a href="/admin/reckon" class="btn">정산내역 확인</a>
                    <a href="/admin/noticeList" class="btn">공지사항</a>
                    <a href="/admin/reportList" class="btn">신고글 보기</a>
                    <a href="/admin/productList" class="btn">상품목록보기</a>
                    </div>
                </div>
            </td>
            <td class="graph-and-summary">
                <div id="graph-container">
                    <canvas id="sales-chart"></canvas>
                </div>
                <table class="summary-table">
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
            </td>
            
        </tr>
    </table>
</body>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                update(total_profit, net_profit, co_pay, cp_price); // 그래프 업데이트
                $('#getYear').text(getYear + " 년 매출 요약정보");
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    }

    function update(d1, d2, d3, d4) {
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
</html>
