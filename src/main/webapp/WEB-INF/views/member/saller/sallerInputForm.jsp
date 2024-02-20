<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 
<html>
<head>
<%@ include file="../../header.jsp" %>
   
<!-- 이 스크립트 태그를 HTML의 head 섹션으로 이동하세요 -->
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>

 <style>
  body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
        }

        .containers {
            width: 50%;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            margin-top: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-top: 10px;
        }

        .form-group {
            margin-bottom: 20px;
        }

    

     

        .form-control {
            background-color: #ffa500;
            color: #fff;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            border-radius: 4px;
        }

        button:hover {
            background-color: #ff8c00;
        }

        .btn-light {
            background-color: #fff;
            color: #000;
        }
    </style>
 
 
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
<script>
$(function () {
    $('#check').on('click', function () {
        var b_no = $('#b_no').val();
        var apiKey = $('#apiKey').val();
        var resultMessage = $('#resultMessage'); // 결과 메시지를 표시할 input 요소
        var submitButton = $('input[name="sallerInputForm"]'); // 신청 버튼

        // 이전 오류 메시지 초기화
        $('.error').remove();

        var data = {
            "b_no": [b_no]
        };
        var resultMsg = "";
        $.ajax({
            url: "https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=" + apiKey,
            type: "POST",
            data: JSON.stringify(data),
            dataType: "JSON",
            contentType: "application/json",
            accept: "application/json",
            success: function (result) {
                if (result.data[0].tax_type != "국세청에 등록되지 않은 사업자등록번호입니다.") {
                    resultMessage.text('인증완료');
                    // Ajax 요청이 성공했으므로 버튼을 활성화합니다.
                    submitButton.prop('disabled', false);
                } else {
                    resultMessage.text(result.data[0].tax_type);
                    // Ajax 요청이 실패했으므로 버튼을 비활성화합니다.
                    submitButton.prop('disabled', true);
                }
            },
            error: function (result) {
                console.log(result.responseText);
                // API 호출이 실패한 경우 오류 메시지 표시
                resultMessage.val('서버 오류: 국세청 정보를 확인할 수 없습니다.');
                // Ajax 요청이 실패했으므로 버튼을 비활성화합니다.
                submitButton.prop('disabled', true);
            }
        });
    });
});
</script>

   
   
</head>
<body >

        

<form method="post" action="sallerInputPro" name="userinput" style="margin-top:70px;">
<div class="containers"  style="width:26%;">
   <sec:authorize access="isAuthenticated()">

    <h1 class="text-center" style="margin-top:20px;">신입 판매자,<br/>  ${dto.m_name}님 환영합니다 .    </h1>
       <input type="hidden" name="ceoname" size="15" value="${dto.m_name}"    >
    
      <div class="form-group"  style="margin-top:50px;">
        <label for="userId"> 판매자님 아이디는     ${dto.m_id} 입니다 .</label>
 <input type="hidden"  name="cus_m_id" size="15" value="${dto.m_id}"   >

    </div>
    
     
    
 
       <div class="form-group">
        <label for="companyname">회사 이름</label> <br/>
              <input type="text" class="form-control" required="required" name="company" size="15" placeholder="운영하시는 업체 이름알려주세요" />
                  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  
                  </div>



     
   
   <div class="form-group"  >
       <label for="companyNum"  >사업자 번호</label><br/>
      <input type="hidden" id="apiKey" value="${apiKey}"/>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 10px;">
    
    <input type="number" class="form-control" name="corpno" id="b_no" required="required" maxlength="15" style="width:58%;" placeholder="입력 후 인증해주세요"/>
      <button type="button" class="btn btn-primary" style="width:35%; margin-left:20px; " id="check">조회하기</button>
    </div>
     <div id="resultMessage"></div>
</div>

   
   <div class="form-group"  >
    <label for="address">사장님의 회사 주소를 입력해주세요</label> <br/>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 10px;">
        <button type="button"   id="addressButton" class="btn btn-light" style="margin-left: 0px; background-color:white; width:120%;">
        <input type="text" class="form-control" id="address" name="cus_address1"  required="required" placeholder="주소" style="align-items: center;  width: 100%;" /> </button>
        <input type="text" class="form-control" name="cus_address2"  placeholder="상세주소" >
    </div>
</div>
   <div class="form-group">
        <label for="cus_pnum">사업자 전화번호 </label>
<input type="text" class="form-control" required="required" name="cus_pnum" placeholder="전화번호 " >        
    </div>
    
        <label for="cus_accnum">  사업자 계좌번호   </label><br/>
   <div class="form-group"style="display: flex;">
   <input type="text" id="cus_accnum" name="cus_accnum" style="width:58%;" required="required" placeholder="계좌번호 입력하세요" oninput="formatBankNumber(this)" class="form-control"  maxlength="22">
              <select class="box form-control"   name="cus_accnum"  style="width:35%; margin-left:10px;" onchange="whereBank()"  >
                        <option value="type">직접 입력</option>
                        <option value="농협"></option>
                        <option value="카카오뱅크">카카오뱅크</option>
                        <option value="신한은행">신한은행</option>
                        <option value="국민은행">국민은행</option>
                        <option value="우리은행">우리은행</option>
                        <option value="우체국">우체국</option>
                    </select>
    </div>
     
   <div class="form-group">
    <input type="submit" name="sallerInputForm" value="판매자 신청" style="width:100%; margin-top:10px; margin-bottom:10px;"  class="btn btn-primary" disabled>
</div>

   
      </sec:authorize>
      </div>
</form>

</body>
</html>
<%@ include file="../../footer.jsp" %>