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
        body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }

        .category-menu {
            width: 8%; /* Adjust the width as needed */
            height: 100%; /* Adjust the height as needed */
            position: relative;
            margin-right:0;
            left:18%;
        }
/* 
        .main-container {
            display: flex;

            justify-content: space-between;
            
             
        }


 */
 .vertical-menu a.btn {
    font-size: 14px; /* You can adjust the font size as needed */
}

/* Add this style for the lower-level menu items (sub-menu items) */
.vertical-menu a.btn + .collapse a.btn {
    font-weight: normal;
    font-size: 14px; /* You can adjust the font size as needed */
}
 .main-container{
   display: flex;
        justify-content: center; /* Center the children horizontally */
        align-items: flex-start; /* Align the children at the top */
        margin-top: 40px;
        margin-bottom: 50px;
 }
        .graph-and-summary {
               width: 60%;
        margin: 0 auto; /* Center the element horizontally */
        text-align: center; /* Center the content inside the element */
 
		position: relative;
        }
       
       
        .main-table {
            width: 50%;
            margin: 0 auto;
            margin-left:18%;
            height: 60%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
           
            
        }
        
        .main-table th {
    font-family: 'Arial', sans-serif;
    font-size: 16px;
    font-weight: bold;
    background-color: #f2f2f2;
    padding: 10px;
    text-align: center;
}
.main-table td {
    font-family: 'Arial', sans-serif;
    font-size: 14px;
    padding: 10px;
    text-align: center;
}
        .out-table {
            margin: 0 auto;
            align-items: center;
            justify-content: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            
              font-family: 'Arial', sans-serif; /* Change the font family as needed */
    font-size: 24px; /* You can adjust the font size as needed */
    color: #333; /* Change the color as needed */
    font-weight: bold;
            
        }

        .summary-table {
            width: 76%;
            margin-bottom:15%;
            margin-left: auto;
            margin-right: auto;
            
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
        }   

  .summary-table th, .summary-table td {
    font-family: 'Arial', sans-serif;
    font-size: 12px;
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

        #graph-container {
        /*     width: 60%;
            order: 1; */
                width: 60%;
    text-align: center; margin: auto;
        }

        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

   /* Add this style for the h1 element */
h1 {
    font-family: 'Roboto', Arial, sans-serif; /* Change the font family as needed */
    font-size: 32px; /* You can adjust the font size as needed */
    color: #333; /* Change the color as needed */
    font-weight: bold;
}
  

        #sales-chart {
            flex: 1;
            font-size:12px;
            height: 60%;
            width: 100%;
            margin-bottom: 15%;
            margin-top: 10%;
            margin-left: auto;
            margin-right: auto;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .vertical-menu {
            order: -1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-right: 20px;
            width: 100%;
            margin-top: 0;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }

        .vertical-menu a {
            margin-bottom: 10px;
            text-align: left;
        }

#bigfont {
    font-weight: bold;
    font-size: 14px; /* You can adjust the font size as needed */
    font-family: 'Poppins', sans-serif; /* Change the font family as needed for bigfont */
    border: 1px solid #ddd;
          width: 140px;
      border-right:none;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         margin-bottom:0;
      
}

/* Add this style for the smallfont element */
#smallfont {
    font-size: 12px; /* You can adjust the font size as needed */
    font-family: 'Quicksand', sans-serif; /* Change the font family as needed for smallfont */
}
        
    </style>
</head>
<body>

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
            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#Qna" id="bigfont">Qna</a>
                <div id="Qna" class="collapse">
                    <a href="/board/consumerQna" class="btn" id="smallfont" >관리자에게하는소통</a><br/>
                    <a href="/board/sellerQna " class="btn"  id="smallfont" >판매자님이 관리자:나에게</a>
                </div>
            </div>
        </div>
    </div>
    <table class="main-table"  >
        <tr>
            <td class="graph-and-summary">
                <div id="graph-container">
                    <canvas id="sales-chart" ></canvas>
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
            </td>
        </tr>
    </table>
</div>

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
                $('#getYear').text(getYear + " 년 매출 요약정보 [관리자님 메인 페이지]");
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
                        label: '판매수수료 매출',
                        backgroundColor: 'rgb(0,128,0)',
                        borderColor: 'rgb(0,128,0)',
                        data: d2
                    },
                    {
                        label: '유료결제 매출',
                        backgroundColor: 'rgb(255,165,0)',
                        borderColor: 'rgb(255,165,0)',
                        data: d3
                    },
                    {
                        label: '쿠폰 지출',
                        backgroundColor: 'rgb(255,0,0)',
                        borderColor: 'rgb(255,0,0)',
                        data: d4
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
