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
	     <font size="+1" ><b>판매자 신청날짜[${dto.m_reg_date}]</b></font></td>
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
      <td  width="200"> 사업자 번호 </td>    
      <td  width="400">	 <input type="text" name="corpno" size="15" ></td>
      
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
 	<td>
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