<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 성공</title>
  <style>
        table {
            border-collapse: collapse; /* 테이블 셀 테두리를 합치도록 설정 */
            width: 100%;
        }
        td {
            padding: 8px; /* 셀 내부 여백 설정 */
            border: 1px solid #ddd; /* 테두리 스타일 설정 */
        }
        .label-cell {
            width: 70px;
            text-align: center;
        }
    </style>
     <script>
        function validateForm() {
            var passwd = document.getElementsByName("passwd")[0].value;
            var passwd2 = document.getElementsByName("passwd2")[0].value;

            if (passwd !== passwd2) {
                alert("비밀번호와 비밀번호 재입력이 일치하지 않습니다.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body>
<script>
function hidePassword(password) {
    let hiddenPassword = '';
    for (let i = 0; i < password.length; i++) {
        if (i % 2 === 0) {
            hiddenPassword += password[i]; // 짝수 번째 문자는 그대로 유지
        } else {
            hiddenPassword += '*'; // 홀수 번째 문자는 *로 대체
        }
    }
    return hiddenPassword;
}
</script>

${id} 님의 비밀번호는  <script>document.write(hidePassword("${passwd}"));</script> 입니다.
<h1>${id}</h1>
<h1>${phone}</h1>

<form method="post" action="/member/pwChange" onsubmit="return validateForm()">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="hidden" name="m_id" value="${id}">            
    <input type="hidden" name="phone" value="${phone}">
    <table>
        <tr>
            <td class="label-cell">변경할 비밀번호</td>
            <td>
                <input type="text" size="15" maxlength="20" name="passwd" required="required" placeholder="변경할 비밀번호 입력하세요">
            </td>
        </tr>
        <tr>
            <td class="label-cell">변경할 비밀번호 재입력</td>
            <td>
                <input type="text" size="15" maxlength="20" name="passwd2" required="required" placeholder="변경할 비밀번호 입력하세요">
            </td>
        </tr>                   
    </table>
    <input type="submit" value="비밀번호 변경">
</form>

<input type="button" value="다음에 변경하기" onclick="javascript:window.location='../main/main'">
<input type="button" value="로그인하기" onclick="javascript:window.location='../member/customLogin'">
</body>

</html>
