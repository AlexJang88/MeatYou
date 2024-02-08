<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

<button type="button" id="check">조회</button>

<script>
$('#check').click(function(){
	$.ajax({
		  url: "/dapi",
		  type: "POST",
		  contentType: "application/json , charset:UTF-8",
		  dataType: "JSON",
		  success: function(result) {
		      console.log(result);
		  },
		  error: function(result) {
		  }
		});
	});
</script>
 
 
 
 
 
 
    