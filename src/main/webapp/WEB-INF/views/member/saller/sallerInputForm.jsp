<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
<%@ include file="../../header.jsp" %>

      
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script>
    document.addEventListener('DOMContentLoaded', function () {
      document.getElementById('addressButton').addEventListener('click', function () {
        new daum.Postcode({
          oncomplete: function (data) {
            // This function will be called when the user selects an address
            var fullAddress = data.address; // Full address with postcode
            document.getElementById('address').value = fullAddress; // Update the input field with the address
          }
        }).open();
      });
    });
    
 // 계좌번호
  function formatBankNumber(input) {
    // 숫자만 남기고 다른 문자 제거
    const value = input.value.replace(/[^0-9]/g, "");
    // 현재 커서 위치
    const cursorPos = input.selectionStart;
    // 입력된 숫자 길이
    const valueLength = value.length;

    // 4-3-4-2-1 형식으로 하이픈 삽입
    let formattedValue = "";
    let hyphenCount = 0;
    for (let i = 0; i < valueLength; i++) {
      if (hyphenCount < 4) {
        if (i === 4 || i === 7 || i === 11) {
          hyphenCount++;
          formattedValue += "-";
        }
      }
      formattedValue += value[i];
    }

   

    input.value = formattedValue;
    input.selectionStart = newCursorPos;
    input.selectionEnd = newCursorPos;
  }
 
 
  
</script>


<html>
<head>
	<title>파매자 신청 하신다구여 ~~ ? </title>
</head>
<body >

        

<form method="post" action="sallerInputPro" name="userinput">
   <sec:authorize access="isAuthenticated()">
  <table width="600" border="1"  align="center">
    <tr > 
      <td  colspan="2" height="39"align="center">
	     <font size="+1" ><b>판매자 신청날짜[${dto.m_reg_date.substring(0, 10)}]</b></font></td>
    </tr>
	
    <tr> 
      <td  width="200"> 사업자 ID</td>
      <td  width="400">
        		 <input type="text" name="cus_m_id" size="15" value="${dto.m_id}"  >
      		
      		</td>
      
    </tr>
    
     <tr> 
      <td width="200"><b>대표자 </b></td>
      <td width="400" >
			<input type="text" name="ceoname" size="15" maxlength="20" >
		</td>
    <tr>  
	
    
   
    <tr> 
      <td   width="200">회사 이름 </td>
     	<td  width="400"> 
       		 <input type="text" name="company" size="15"  >
          		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </td>
    </tr>
    
  <tr>  
     <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <input type="hidden" id="apiKey" value="${apiKey}">
   <td width="200"> 사업자 번호 </td>   
   <td width="400">	 
   <input type="number" name="corpno" id="b_no" maxlength="15">
			<button type="button" id="check">조회하기</button></td>
		<script>
$('#check').on('click',function(){
	var b_no = $('#b_no').val();
	var apiKey = $('#apiKey').val();
	 console.log(b_no);
	 var data = {
		  "b_no": [b_no]
		   
		   
		  }; 
		  
	$.ajax({
	 url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey="+apiKey, // serviceKey 값을 xxxxxx에 입력
	 type: "POST",
	 data: JSON.stringify(data), // json 을 string으로 변환하여 전송
	 dataType: "JSON",
	 contentType: "application/json",
	 accept: "application/json",
	 success: function(result) {
	   console.log(result);
	   console.log(result.data[0].tax_type);
	   console.log(result.utcc_yn);
		if(result.data[0].utcc_yn == "N"){
			$('#b_no').parent().append('<span class="error">신청 불가</span>');
		}
	 },
	 error: function(result) {
	   console.log(result.responseText); //responseText의 에러메세지 확인
	
	 }
	});
	});

</script>


  </tr>
   
   
   <tr>  
      <td> 
          <label for="address">사업자 주소</label>
	   </td>
   <td> 
   	<div class="form-group"> 
   		<input type="text" class="form-control" id="address"  name="cus_address1" placeholder="사업자 주소">  
   	</div>
 <button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		   	
   </td>	      
  </tr>
  <tr>
  	<td>
	      <label for="detailAddress">상세주소</label>
	  </td>
	  <td>
	       <div class="form-group">
	        <input type="text" class="form-control" name="cus_address2" placeholder="사업자 상세주소">
	     </div>
	   </td>
  </tr>
  <tr>
  	<td>
	    사업자 전화 번호 
	  </td>
	  <td>
	        <input type="text" name="cus_pnum" placeholder="전화번호 ">
 	<td>
  </tr>
  <tr>
  	<td>
	    사업자 계좌번호 
	  </td>
	  <td>
	  
	      <input type="text" id="cus_accnum" name="cus_accnum" placeholder="계좌번호 입력하세요" oninput="formatBankNumber(this)"  maxlength="22">
	  		   <select class="box"   name="cus_accnum" onchange="whereBank()">
                        <option value="type">직접 입력</option>
                        <option value="농협"></option>
                        <option value="카카오뱅크">카카오뱅크</option>
                        <option value="신한은행">신한은행</option>
                        <option value="국민은행">국민은행</option>
                        <option value="우리은행">우리은행</option>
                        <option value="우체국">우체국</option>
                      
                    </select>
	  
	  
	  
	  
	  
 	<td>
  </tr>
  
  
    <tr> 
      <td colspan="2" align="center"> 
       <input type="submit" name="sallerInputForm" value="판매자 신청"   style="background-color:orange; ">
       <input type="button" value="취  소" onclick="javascript:window.location='/main/main'">      
      </td>
    </tr>
  </table>
      </sec:authorize>
</form>
</body>
</html>
<%@ include file="../../footer.jsp" %>