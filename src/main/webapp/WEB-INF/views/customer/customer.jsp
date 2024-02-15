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
    $(document).ready(function() {
        $('.vertical-menu-item').hover(function() {
            var $menu = $(this).children('.dropdown-menu');
            $menu.toggle();
        });
    });</script>
    <style>
        body {
            font-family: 'Roboto', Arial, sans-serif; /* Google font */
            background-color: #f4f4f4;
        }
        .outer-table {
            display: flex;
            justify-content: center;
        }

        .inner-table {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: flex;
            align-items: flex-start; /* 메뉴 및 콘텐츠를 상단에 정렬 */
        }
        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
            font-weight: bold; /* Added font weight */
        }
        .btn:hover {
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

        .graph-container {
            background-color: #fff;
            padding: 20px;
            margin-top: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        #sales-chart {
            width: 100%;
            height: 300px; /* Adjust height as needed */
        }
        .vertical-menu {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-right: 20px; /* 테이블과 메뉴 사이의 간격 설정 */
        }
        .vertical-menu a {
            margin-bottom: 10px;
            width: 100%; /* 메뉴 아이템이 전체 너비를 차지하도록 설정 */
            text-align: left; /* 텍스트를 왼쪽으로 정렬 */
        }
        .dropdown-menu {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
            z-index: 1;
            top: 100%; /* Position dropdown below the parent item */
            left: 0; /* Align dropdown with the parent item */
        }

        .vertical-menu-item:hover .dropdown-menu {
            display: block;
        }

        .dropdown-menu a {
            display: block;
            padding: 10px;
            text-decoration: none;
            color: #333;
        }

        .dropdown-menu a:hover {
            background-color: #ddd;
        }
    </style>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"> <!-- Google font -->
</head>
<body>
    <table class="outer-table">
        <tr>
            <td>
                <table class="inner-table">
                    <tr>
                        <td>
                           <div class="vertical-menu">
                                <div class="vertical-menu-item">
                                    <a href="/customers/customer" class="btn dropdown-toggle" data-toggle="dropdown">홈으로</a>
                                    <div class="dropdown-menu">
                                        <a class="dropdown-item" href="#">하위 메뉴 1</a>
                                        <a class="dropdown-item" href="#">하위 메뉴 2</a>
                                        <a class="dropdown-item" href="#">하위 메뉴 3</a>
                                        <a class="dropdown-item" href="#">하위 메뉴 4</a>
                                    </div>
                                </div>
                                <a href="/customers/itemUpdate" class="btn">상품등록</a>
                                <a href="/customers/itemList" class="btn">상품목록</a>
                                <a href="/customers/profit" class="btn">매출현황</a>
                                <a href="/customers/stock" class="btn">재고현황</a>
                                <a href="/customers/consumerList" class="btn">구매회원</a>
                                <a href="/customers/pay" class="btn">유료결제</a>
                                <a href="/customers/delivering" class="btn">주문접수 및 배송현황</a>
                                <a href="/board/sellerQna" class="btn">관리자 문의게시판</a>
                                <a href="/member/modify" class="btn">마이페이지</a>
                            </div>
                        </td>
                        <td>
                            <div class="sales-summary">
                                총 누적 매출액 : <fmt:formatNumber value="${totalmoneysum}" type="number" pattern="#,##0"/> 원
                                <div>
                                    <label for="year"></label>
                                    <select id="year" name="year">
                                        <c:forEach var="i" begin="2023" end="2030">
                                            <option value="${i}">${i}년</option>
                                        </c:forEach>
                                    </select>
                                    <button id="Serch_btn" class="btn">업데이트</button>
                                </div>
                            </div>
                            <div class="graph-container">
                                <canvas id="sales-chart"></canvas>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>

    var selectedYear = '';
    $('#Serch_btn').on('click', function () {
        selectedYear = $('#year').val();
        $.ajax({
            url: '/customers/chartdata',
            method: 'GET',
            data: {selectedYear: selectedYear},
            dataType: 'json',
            success: function (data) {
                var mon_sal = data.mon_sal;
                var net_profit = data.net_profit;
                update(mon_sal, net_profit, selectedYear);
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    });

    window.onload = function () {
        $.ajax({
            url: '/customers/chartdata',
            method: 'GET',
            dataType: 'json',
            success: function (data) {
                var mon_sal = data.mon_sal;
                var net_profit = data.net_profit;
                var selectedYear = data.selectedYear;
                update(mon_sal, net_profit, selectedYear);
                $('#selectedYear').text(selectedYear + "년월 매출요약");
            },
            error: function (xhr, status, error) {
                console.error('데이터 가져오기 실패:', error);
            }
        });
    }

    function update(d1, d2, d3) {
        $('#sales-chart').remove();
        $('.graph-container').append('<canvas id="sales-chart"></canvas>');
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
                        label: '순 매출',
                        backgroundColor: 'rgb(0,128,0)',
                        borderColor: 'rgb(0,128,0)',
                        data: d2
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
</body>
</html>

<%@ include file="../footer.jsp" %>