<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>아이디 찾기</title>
   <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f5f6f7;
        }

        h1 {
            text-align: center;
            font-size: 28px;
            margin-bottom: 20px;
        }

        form {
            width: 400px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
        }

        table tr {
            margin-bottom: 10px;
        }

        table td {
            padding: 10px;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .mail-check-input {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        #success {
            background-color: #dddddd;
             color: black;
            padding: 15px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 90%;
        }

        #success:disabled {
            background-color: #ddd;
            color: #555;
            cursor: not-allowed;
        }

        #confirmButton {
            background-color: #3498db;
           color: black;
            padding: 15px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            width: 84%;
        }

        #confirmButton:disabled {
            background-color: #ddd;
            color: black;
            cursor: not-allowed;
        }

        #mail-Check-Btn {
            background-color: #dddddd;
                  color: black;
            padding: 15px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 12px;
            width: 84%;
        }
    </style>
    <script>
        function updateEmail() {
            const emaillist = document.getElementById('emaillist');
            const emailInput = document.getElementById('email');

            if (emaillist.value !== 'type') {
                const selectedDomain = emaillist.value;
                emailInput.value = emailInput.value.split('@')[0] + selectedDomain;
            }
        }

        function formatPhoneNumber(input) {
            var cursorPosition = input.selectionStart;
            var phoneNumber = input.value.replace(/\D/g, '');

            if (phoneNumber.length >= 3 && phoneNumber.length <= 6) {
                phoneNumber = phoneNumber.replace(/(\d{3})(\d{0,4})/, '$1-$2');
            } else if (phoneNumber.length >= 7) {
                phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{0,4})/, '$1-$2-$3');
            }

            if (phoneNumber.length > 13) {
                phoneNumber = phoneNumber.substring(0, 13);
            }

            input.value = phoneNumber;
            input.setSelectionRange(cursorPosition, cursorPosition);
        }
    </script>
</head>
<body>
    	<table>
    		<th><h1>아이디 찾기</h1></th>
    	</table>
    <form method="post" action="/member/idfindPro">
    
        <table>
            <tr>
                <td colspan="3"><input type="text" id="m_name" name="m_name" required="required" placeholder="이름을 입력하시오" size="15"></td>
            </tr>
            <tr>
          <td colspan="3">
                    <input type="text" name="phone" id="phoneInput" size="40" required="required" maxlength="13"
                        placeholder="전화번호를 입력하시오" oninput="formatPhoneNumber(this)">
                </td>
            </tr>
            <tr>
         <td colspan="2">
                    <input type="text" id="email" class="inputText" name="email" size="14" required="required"
                        placeholder="이메일을 입력하시오" />
                
                    <span id="emailError" style="color: red;" class="error-message"></span>
                </td>
                <td colspan="1">
                    <select class="box" id="emaillist" name="emaillist" onchange="updateEmail()">
                        <option value="type">직접 입력</option>
                        <option value="@naver.com">@naver.com</option>
                        <option value="@google.com">@google.com</option>
                        <option value="@hanmail.net">@hanmail.net</option>
                        <option value="@nate.com">@nate.com</option>
                        <option value="@kakao.com">@kakao.com</option>
                        <option value="@gmail.com">@gmail.com</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td  colspan="2">
                    <input class="form-control mail-check-input" required="required"
                        placeholder="인증번호 입력해주세요!" maxlength="6">
                    <span id="mail-check-warn"></span>
                    </td>
                    <td  colspan="1">
                    <button type="button" class="btn btn-primary" id="mail-Check-Btn" style="">인증번호 전송</button> 
                </td>
            </tr>
            <tr>
                <td colspan="1">
                    <input type="button" class="btn btn-primary" id="success" value="이메일 인증확인">
                </td>
                <td colspan="2">
                    <input type="submit" name="confirm" value="아이디 찾기" id="confirmButton" disabled>
                </td>
            </tr>
        </table>
    </form>
    <script>
        $('#mail-Check-Btn').click(function () {
            var email = $('#email').val();
            var m_name = $('#m_name').val();
            var phoneInput = $('#phoneInput').val();
            
            console.log('완성된 이메일 : ' + email);

            const checkInput = $('.mail-check-input');

            $.ajax({
                type: 'get',
                url: "/member/mailCheck?email="+email+"&m_name="+m_name+"&phoneInput="+phoneInput+"",
                success: function (data) {
                    console.log("data : " + data);
                    if(data!=0){
                    checkInput.attr('disabled', false);
                    code = data;
                    alert('인증번호가 전송되었습니다.');}
                    else{
                    	alert('가입했던 정보를 작성해주세요');
                    }
                }
            });
        });

        $('.mail-check-input').blur(function () {
            const email = $('#email').val() + $('#emaillist').val();
            const inputCode = $(this).val();
            const $resultMsg = $('#mail-check-warn');

            if (inputCode === code) {
                $resultMsg.html('인증번호가 일치합니다.');
                $resultMsg.css('color', 'green');
                $('#mail-Check-Btn').attr('disabled', true);
                $('#email').attr('readonly', true);
                $('#emaillist').attr('readonly', true);
                $('#emaillist').attr('onFocus', 'this.initialSelect = this.selectedIndex');
                $('#emaillist').attr('onChange', 'this.selectedIndex = this.initialSelect');
                $('#checkS').attr('disabled', false);
                $('#success').prop('disabled', false);
            } else {
                $resultMsg.html('인증번호가 불일치 합니다. 다시 확인해주세요!.');
                $resultMsg.css('color', 'red');
                $('#success').prop('disabled', true);
            }
        });

        $('#success').click(function () {
            const inputCode = $('.mail-check-input').val();

            if (inputCode === code) {
                alert('인증이 완료되었습니다.');
                $('#confirmButton').prop('disabled', false);
            } else {
                alert('인증번호가 일치하지 않습니다. 다시 확인해주세요.');
            }
        });

        window.onload = function () {
            var checkValue = <%= request.getAttribute("check") %>;
            if (checkValue == 0) {
                alert("정보를 다시 확인하세요.");
            }
        };
    </script>
</body>
</html>
