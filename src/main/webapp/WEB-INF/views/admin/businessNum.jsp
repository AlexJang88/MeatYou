<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <input type="hidden" id="apiKey" value="${apiKey}">
사업자 번호 : <input type="number" id="b_no" maxlength="10">
<button type="button" id="check">조회하기</button>
<script>
$('#check').on('click',function(){
var b_no = $('#b_no').val();
var apiKey = $('#apiKey').val();
 var data = {
	    "b_no": [b_no]
	   }; 
	   
$.ajax({
  url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="+apiKey,  // serviceKey 값을 xxxxxx에 입력
  type: "POST",
  data: JSON.stringify(data), // json 을 string으로 변환하여 전송
  dataType: "JSON",
  contentType: "application/json",
  accept: "application/json",
  success: function(result) {
      console.log(result);
      console.log(result.data[0].tax_type);
  },
  error: function(result) {
      console.log(result.responseText); //responseText의 에러메세지 확인
  }
});
});
</script>