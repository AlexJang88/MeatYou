<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>
<table width="270" border="0"  align="center">
  <tr > 
    <td height="39"  align="center">
	  <font size="+1" ><b> 축하합니다 </b></font></td>
  </tr>
  <tr>
    <td align="center"> 
      <p>판매자 신청이 완료 돠었습니다 승인까지 3일에서 최소 5일정도 소요될수있습니다.</p>
    </td>
  </tr>
  <tr>
    <td  align="center"> 
      <form>
	    <input type="button" value="메인으로" onclick="window.location='/main/main'">
      </form>
      5초후에 메인으로 이동합니다.<meta http-equiv="Refresh" content="5;url=/main/main" >    
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />                   
    </td>
  </tr>
</table>

<%@ include file="../../footer.jsp" %>