<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<input type="hidden" id="apiKey" value="${apiKey}">
<button type="button" id="check">조회하기</button>

<script>
$('#check').on('click',function(){
	var apiKey =$('#apiKey').val();
	var id='jaus0708@gamil.com';
	$.ajax({
	  url: "http://www.kamis.co.kr/service/price/xml.do?action=dailySalesList&p_cert_key="+apiKey+"&p_cert_id="+id+"&p_returntype=json",
	  type: "POST",
	  dataType: "JSON",
	  success: function(result) {
	      console.log(result);
	  },
	  error: function(result) {
	  }
	});
});
</script>