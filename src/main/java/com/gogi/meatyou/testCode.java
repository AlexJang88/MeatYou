package com.gogi.meatyou;

public class testCode {
	//차트
/*	@Controller
	public class ChartController {

	    @RequestMapping("/salesChart")
	    public String getSalesData(Model model) {
	        // 데이터베이스에서 또는 서비스를 통해 데이터를 가져옵니다.
	        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June");
	        List<Integer> sales = Arrays.asList(0, 10000, 5000, 15000, 20000, 30000);
	        
	        // 모델 객체에 데이터를 추가합니다.
	        model.addAttribute("months", months);
	        model.addAttribute("sales", sales);

	        // JSP 페이지를 반환합니다.
	        return "salesChart";
	    }
	}
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <!-- 나머지 head 내용 -->
	</head>
	<body>
	    <div style="width: 600px; height: 400px;">
	        <canvas id="sales-chart"></canvas>
	    </div>
	    <script>
	        var ctx = document.getElementById('sales-chart').getContext('2d');
	        var salesChart = new Chart(ctx, {
	            type: 'line',
	            data: {
	                labels: [
	                    // 컨트롤러로부터 받은 months 데이터 사용
	                    <c:forEach var="month" items="${months}" varStatus="status">
	                        '<c:out value="${month}"/>'<c:if test="${!status.last}">,</c:if>
	                    </c:forEach>
	                ],
	                datasets: [{
	                    label: 'Sales',
	                    backgroundColor: 'rgb(255, 99, 132)',
	                    borderColor: 'rgb(255, 99, 132)',
	                    data: [
	                        // 컨트롤러로부터 받은 sales 데이터 사용
	                        <c:forEach var="sale" items="${sales}" varStatus="status">
	                            <c:out value="${sale}"/><c:if test="${!status.last}">,</c:if>
	                        </c:forEach>
	                    ],
	                }]
	            },
	            options: {
	                // 옵션 설정은 동일하게 유지
	            }
	        });
	    </script>
	</body>
	</html>
*/

}
