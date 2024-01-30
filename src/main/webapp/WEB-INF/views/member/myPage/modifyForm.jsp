<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
<%@ include file="../../header.jsp" %>




<html>
<head>
	<title>회원정보수정</title>
</head>
<body >

<form method="post" action="modifyPro" name="userinput">

  <table width="600" border="1"  align="center">
    <tr > 
      <td  colspan="2" height="39"align="center">
	     <font size="+1" ><b>회원 정보수정[${dto.m_reg_date.substring(0, 10)}]</b></font></td>
    </tr>
    <tr>
      <td colspan="2" class="normal" align="center">회원의 정보를 수정합니다.</td>
    </tr>
     <tr> 
      <td width="200"><b>아이디 입력</b></td>
      <td width="400" >&nbsp;</td>
    <tr>  

    <tr> 
      <td  width="200"> 사용자 ID</td>
      <td  width="400">${dto.m_id}</td>
    </tr>
    
     <tr> 
      <td width="200"> 비밀번호</td>
      <td width="400"> 
        <input type="password" name="passwd" size="10" 
        maxlength="10" value="${dto.passwd}">
      </td>
    <tr>  
    <tr> 
      <td  width="200" ><b>개인정보 입력</b></td>
      <td width="400" >&nbsp;</td>
    <tr>  
    <tr> 
      <td   width="200">사용자 이름</td>
      <td  width="400"> 
       ${dto.m_name}
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </td>
    </tr>
 
     <tr>  
      <td> 
          <label for="address">주소</label>
	   </td>
   <td> 
   	<div class="form-group"> 
   		<input type="text" class="form-control" id="address"  name="m_addr1" placeholder="주소" value="${dto.m_addr1}">   
   	</div>
 <button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
     <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		   	
     <input type="hidden" name="cus_num" />		   	
   </td>	      
  </tr>
  <tr>
  	<td>
	      <label for="detailAddress">상세주소</label>
	  </td>
	  <td>
	       <div class="form-group">
	        <input type="text" class="form-control" name="m_addr2" placeholder="상세주소"  value="${dto.m_addr2}">   
	     </div>
 	<td>
  </tr>
  
  
  
  
  
   <tr> 
      <td width="200">E-Mail</td>
      <td width="400">
          <input type="text" name="email" value="${dto.email}" size="40" maxlength="30" >	
      </td>
    </tr>
 <tr> 
      <td width="200">전화번호 </td>
      <td width="400"> 
        <input type="text" name="telep" size="40" maxlength="30" value="${dto.telep}">
      </td>
    </tr>
    <tr> 
      <td width="200">휴대폰번호 </td>
      <td width="400"> 
        <input type="text" name="phone" size="40" maxlength="30" value="${dto.phone}">
      </td>
    </tr>  
    <tr> 
      <td colspan="2" align="center"> 
       <input type="submit" name="modify" value="수   정" >
       <input type="button" value="취  소" onclick="javascript:window.location='/main/main'">      
      
      
      
 			<c:if test="${dto.m_status eq 1001 and dto.m_status le 1003}">
				 <input type="button" name="modify" value="판매자 신청"  onclick="javascript:window.location='/member/sallerInputForm'" style="background-color:orange; ">
						</c:if>
      </td>
    </tr>
  </table>
</form>
</body>
</html>
<%@ include file="../../footer.jsp" %>