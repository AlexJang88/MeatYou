package com.gogi.meatyou;

public class testCode {
	//��Ʈ
/*	@Controller
	public class ChartController {

	    @RequestMapping("/salesChart")
	    public String getSalesData(Model model) {
	        // �����ͺ��̽����� �Ǵ� ���񽺸� ���� �����͸� �����ɴϴ�.
	        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June");
	        List<Integer> sales = Arrays.asList(0, 10000, 5000, 15000, 20000, 30000);
	        
	        // �� ��ü�� �����͸� �߰��մϴ�.
	        model.addAttribute("months", months);
	        model.addAttribute("sales", sales);

	        // JSP �������� ��ȯ�մϴ�.
	        return "salesChart";
	    }
	}
	<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE html>
	<html lang="en">
	<head>
	    <!-- ������ head ���� -->
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
	                    // ��Ʈ�ѷ��κ��� ���� months ������ ���
	                    <c:forEach var="month" items="${months}" varStatus="status">
	                        '<c:out value="${month}"/>'<c:if test="${!status.last}">,</c:if>
	                    </c:forEach>
	                ],
	                datasets: [{
	                    label: 'Sales',
	                    backgroundColor: 'rgb(255, 99, 132)',
	                    borderColor: 'rgb(255, 99, 132)',
	                    data: [
	                        // ��Ʈ�ѷ��κ��� ���� sales ������ ���
	                        <c:forEach var="sale" items="${sales}" varStatus="status">
	                            <c:out value="${sale}"/><c:if test="${!status.last}">,</c:if>
	                        </c:forEach>
	                    ],
	                }]
	            },
	            options: {
	                // �ɼ� ������ �����ϰ� ����
	            }
	        });
	    </script>
	</body>
	</html>
*/

}
