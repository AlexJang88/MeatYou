<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기 성공</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f5f5;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 100%;
            margin-top: 20px;
        }

        td {
            padding: 12px;
            border: 1px solid #ddd;
        }

        .label-cell {
            width: 150px;
            text-align: left;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"], input[type="button"] {
            background-color: #3498db;
            color: #fff;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
            border-radius: 4px;
        }

        input[type="submit"]:hover, input[type="button"]:hover {
            background-color: #2c3e50;
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
    <div class="container">
        <h1>${id} 님의 비밀번호</h1>
        <p><strong>${phone}</strong></p>
        <p>변경할 비밀번호를 입력하세요.</p>

        <form method="post" action="/member/pwChange" onsubmit="return validateForm()">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input type="hidden" name="m_id" value="${id}">
            <input type="hidden" name="phone" value="${phone}">

            <table>
                <tr>
                    <td class="label-cell">변경할 비밀번호</td>
                    <td>
                        <input type="text" size="15" maxlength="20" name="passwd" required="required" placeholder="비밀번호 입력">
                    </td>
                </tr>
                <tr>
                    <td class="label-cell">비밀번호 재입력</td>
                    <td>
                        <input type="text" size="15" maxlength="20" name="passwd2" required="required" placeholder="비밀번호 재입력">
                    </td>
                </tr>
            </table>
            <input type="submit" value="비밀번호 변경">
        </form>
 
    </div>
</body>
</html>
