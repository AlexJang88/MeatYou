<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
  <tr bgcolor="${title_c}"> 
    <td height="39"  align="center">
	  <font size="+1" ><b>권한이 차이가 있습니다 ..</b></font></td>
  </tr>
  <tr>
    <td > 
    </td>
  </tr>
  <tr>
    <td > 
      <form>
	    <input type="button" value="메인으로" onclick="window.location='../main/main'">
      </form>
      5초후에 메인으로 이동합니다.<meta http-equiv="Refresh" content="5;url=../main/main" >                       
    </td>
  </tr>
</table>

<%@ include file="../../footer.jsp" %>