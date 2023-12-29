<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../header.jsp" %>

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


</script>

<body>

<form method="post" action="/member/inputPro" name="userinput" onSubmit="return checkIt()">
  <table>
    <tr> 
    <td colspan="2" height="39" align="center" >
       <font size="+1"><b>회원가입</b></font></td>
    </tr>
    <tr> 
      <td width="200"><b>아이디 입력</b></td>
      <td width="400"></td>
    </tr>  

    <tr> 
      <td width="200"> 사용자 ID</td>
      <td width="400"> 
       <input type="text" name="m_id" size="10" maxlength="12">
      </td>
    </tr>
    <tr> 
      <td width="200"> 비밀번호</td>
      <td width="400"> 
        <input type="password" name="passwd" size="15" maxlength="12">
      </td>
    </tr>
    <tr>  
      <td width="200">비밀번호 확인</td>
      <td width="400"> 
        <input type="password" size="15" maxlength="12">
      </td>
    </tr>
    
    <tr> 
      <td width="200"><b>개인정보 입력</b></td>
      <td width="400">&nbsp;</td>
    <tr>  
    <tr> 
      <td width="200">사용자 이름</td>
      <td width="400"> 
        <input type="text" name="m_name" size="15" maxlength="10">
      </td>
    </tr>
 <tr>
 <td width="200">생년월일</td>
            <td width="400">
                <div class="form-group">
                    <label for="birthdate">생년월일</label>
                    <input type="date" class="form-control" name="birth" id="birth">
                </div>
            </td>
        </tr>
        <tr>
  <div class="form-group">
          <label for="address">주소</label>
          <input type="text" class="form-control" id="address"  name="m_addr1" placeholder="주소">
   </div>
        <!-- Button to trigger address search -->
        <button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
     	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		
      <!-- Second input field for detailed address -->
      <div class="form-group">
          <label for="detailAddress">상세주소</label>
          <input type="text" class="form-control" name="m_addr2" placeholder="상세주소">
      </div>
  </tr>
  
    <tr> 
      <td width="200">E-Mail</td>
      <td width="400"> 
        <input type="text" name="email" size="40" maxlength="30">
      </td>
    </tr>
    <tr> 
      <td width="200">전화번호 </td>
      <td width="400"> 
        <input type="text" name="telep" size="40" maxlength="30">
      </td>
    </tr>
    <tr> 
      <td width="200">휴대폰번호 </td>
      <td width="400"> 
        <input type="text" name="phone" size="40" maxlength="30">
      </td>
    </tr>
    
<tr>
      <td> 
          <input type="submit" name="confirm" value="회원가입">
          <input type="button" value="가입안함" onclick="javascript:window.location='../main/main'">
      </td>
    </tr>
    
  

  </table>
</form>
</body>
</html>

<%@ include file="../footer.jsp" %>
