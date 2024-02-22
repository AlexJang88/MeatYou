<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<div id="view">
	
</div>
<input type="hidden" id="key" value="${key}">
<input type="date" id="checkdate" />
<button type="button" id="check">조회</button>

<script>
$('#check').click(function(){
	var key = $('#key').val();
	var check = $('#checkdate').val();
	if(check=='' || check==null){
		var today = new Date();
	    var year = today.getFullYear();
	    var month = today.getMonth() + 1; 
	    var day = today.getDate();
	
	    if (month < 10) {
	        month = '0' + month;
	    }
	    if (day < 10) {
	        day = '0' + day;
	    }
	    var getdate = '' + year + month + day;
	}else{
		var dateParts = check.split('-'); // 날짜를 '-'로 구분하여 배열로 분리
		var getdate = dateParts.join('');
	}
	$.ajax({
		  url:"https://cors-anywhere.herokuapp.com/http://211.237.50.150:7080/openapi/"+key+"/json/Grid_20151204000000000316_1/1/5?OCCRRNC_DE="+getdate+"",
		  type: "GET",
		  contentType: "application/json , charset:UTF-8",
		  dataType: "JSON",
		  success: function(result) {
		      update(result);
		  },
		  error: function() {
		  }
		});
	});
 function update(d1){
	 $.ajax({
		  url: "/admin/dapi",
		  type: "post",
		  traditional: true,
		  contentType: "application/json; charset:UTF-8",
		  data: JSON.stringify({ data: d1 }),
		  dataType: "JSON",
		  success: function(result) {
		      console.log(result);
		  },
		  error: function(result) {
		  }
		});
	}; 
</script>