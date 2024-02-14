<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<input type="hidden" id="apiKey" value="${apiKey}">
	<table border="1">
		<tr>
			<td>카테고리 번호</td>
			<td>카테고리 이름</td>
			<td>당일가격</td>
			<td>1일전 가격</td>
			<td>1개월전 가격</td>
			<td>1년전 가격</td>
			<td>조회기준날짜</td>
			<td>상품이름</td>
			<td>상품번호</td>
			<td>단위</td>
		</tr>
		<tbody id="itemBody">
    	</tbody>
	</table>	
<button type="button" id="check">조회하기</button>

<script>
window.onload = function(){
	$.ajax({
	  url: "/admin/api",
	  type: "POST",
	  dataType: "JSON",
	  success: function(result) {
	      update(result.price);
	      console.log(result.price);
	  },
	  error: function(result) {
	  }
	});
};
function update(data){
	if(data.length>0){
		tableContent="";
        $.each(data, function(index, item) {
        if(item.category_code==500 && item.product_cls_code==01){
        	 tableContent += "<tr>" +
        	 "<td>" + item.category_code + "</td>" +
             "<td>" + item.category_name + "</td>" +
             "<td>" + item.dpr1 + "</td>" +
             "<td>" + item.dpr2 + "</td>" +
             "<td>" + item.dpr3 + "</td>" +
             "<td>" + item.dpr4 + "</td>" +
             "<td>" + item.lastest_day + "</td>" +
             "<td>" + item.productName + "</td>" +
             "<td>" + item.productno + "</td>" +
             "<td>" + item.unit + "</td></tr>";
        }
       	 $('#itemBody').html(tableContent);
        });
        
        }
}

</script>