<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<table width="270" border="0" cellspacing="0" cellpadding="5" align="center">
    <tr bgcolor="${title_c}"> 
        <td height="39" align="center">
            <font size="+1" ><b>가입 완료 되었습니다 축하합니다 </b></font>
        </td>
    </tr>
    <tr>
        <td > 
        </td>
    </tr>
    <tr>
        <td > 
            <form>
                <a href="/main/main"><i class="fa"></i>메인으로</a>
                <a href="/member/customLogin"><i class="fa"></i> 로그인</a>
            </form>
            5초후에 메인으로 이동합니다.<meta http-equiv="Refresh" content="5;url=../main/main" >                       
        </td>
    </tr>
</table>

<%@ include file="../footer.jsp" %>
