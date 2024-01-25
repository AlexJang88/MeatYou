<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<h2>정산내역 확인</h2>
<div style="width: 70%; height: 50%;">
	<canvas id="sales-chart"></canvas>
</div>
<a href="/admin/reckon?check=1">정산중</a>
<a href="/admin/reckon?check=2">정산완료</a>
<a href="/admin/reckon?check=3">전체</a>
<form action="/admin/reckon" method="post">
	<input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}" /> <input type="hidden" name="check" value="1">
	<select name="start">
		<option value="2022">2022년</option>
		<option value="2023">2023년</option>
		<option value="2024">2024년</option>
	</select> <select name="end">
		<option value="1">1월</option>
		<option value="2">2월</option>
		<option value="3">3월</option>
		<option value="4">4월</option>
		<option value="5">5월</option>
		<option value="6">6월</option>
		<option value="7">7월</option>
		<option value="8">8월</option>
		<option value="9">9월</option>
		<option value="10">10월</option>
		<option value="11">11월</option>
		<option value="12">12월</option>
	</select> <input type="submit" value="조회">
</form>
<table border="1">
	<tr>
		<td>판매자</td>
		<td>총매출</td>
		<td>배송비</td>
		<td>정산금액</td>
		<td>쿠폰할인금액</td>
	</tr>
	<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.p_m_id}</td>
			<td>${dto.totalprice}</td>
			<td>${dto.order_dere_pay}</td>
			<td>${dto.deposit}</td>
			<td>${dto.cp_price}</td>
		</tr>
	</c:forEach>
</table>
${month}
<c:if test="${count>0}">
	<c:if test="${startPage>10}">
		<a href="/admin/memberlist?pageNum=${startPage-10}&check=${check}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="/admin/memberlist?pageNum=${i}&check=${check}">[${i}]</a>
	</c:forEach>
	<c:if test="${endPage<pageCount}">
		<a href="/admin/memberlist?pageNum=${startPage+10}&check=${check}">[다음]</a>
	</c:if>
</c:if>
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

<%@ include file="../footer.jsp"%>