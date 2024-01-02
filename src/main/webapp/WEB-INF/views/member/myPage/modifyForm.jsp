<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
<%@ include file="../../header.jsp" %>




<html>
<head>
	<title>회원정보수정</title>
</head>
<body >

<form method="post" action="modifyPro" name="userinput"
 onsubmit="return modifyCheckIt()">

  <table width="600" border="1"  align="center">
    <tr > 
      <td  colspan="2" height="39"align="center">
	     <font size="+1" ><b>회원 정보수정[${dto.m_reg_date}]</b></font></td>
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
        <input type="text" name="name" size="15" maxlength="20" value="${dto.m_name}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
      </td>
    </tr>
<tr>     
   <!--  <td width="200">생년월일</td>
            <td width="400">
                <div class="form-group">
                    <label for="birthdate">생년월일</label>
                    <input type="date" class="form-control" name="birth" id="birth" value="${dto.birth}">
                </div>
            </td>
        </tr>
     <tr> --> 
  <div class="form-group">
          <label for="address">주소</label>
          <input type="text" class="form-control" id="address"  name="m_addr1" placeholder="주소" value="${dto.m_addr1}">
   </div>
        <!-- Button to trigger address search -->
        <button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
  
      <!-- Second input field for detailed address -->
      <div class="form-group">
          <label for="detailAddress">상세주소</label>
          <input type="text" class="form-control" name="m_addr2" placeholder="상세주소"  value="${dto.m_addr2}">
      </div>
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
      </td>
    </tr>
  </table>
</form>
</body>
</html>
<%@ include file="../../footer.jsp" %>