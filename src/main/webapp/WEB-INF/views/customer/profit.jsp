<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />


<!DOCTYPE html>
<html>
<head>

<title>매출현황</title>

</head>
<body>
	<a href="/customers/customer">홈으로</a>
	<h2>여기는 매출현황  현재매출 , 지난 매출 확인</h2>
	
	<input type="text" name="daterange">

	
	<table border="1" width="1400" cellpadding="0" cellspacing="0" align="center">
		<tr height="30"> 
			<td width="200" align="center">매출액</td>
			<td width="200" align="center">판매수량</td>
			<td width="200" align="center">쿠폰사용료</td>
			<td width="200" align="center">배송비</td>
			<td width="200" align="center">순수익</td>				
			<td width="200" align="center">판매 HOT</td>				
			<td width="200" align="center">파워링크</td>					
		</tr>
	</table>




</body>
</html>

<script>
$(function() {
  $('input[name="daterange"]').daterangepicker({
    opens: 'left'
  }, function(start, end, label) {
    console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
  });
});
</script>