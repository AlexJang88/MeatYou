<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기 성공</title>
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

비밀번호는  <script>document.write(hidePassword("${passwd}"));</script> 입니다.
<h1>${id}</h1>
<h1>${phone}</h1>
<input type="button" value="변경하기" onclick="javascript:window.location='../member/pwChange'">
<input type="button" value="다음에 변경하기" onclick="javascript:window.location='../main/main'">
<input type="button" value="로그인하기" onclick="javascript:window.location='../member/customLogin'">
</body>

</html>
