<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<style>
    body {
        background-image: url('/resources/survey/background.png'); /* 배경 이미지 경로로 변경 */
       background-size: contain;  /* 이미지를 화면에 맞게 조절 */
        background-repeat: no-repeat; /* 이미지 반복 없음 */
        background-attachment: fixed; /* 스크롤 시 배경 이미지 고정 */
        /* 추가적인 스타일 조정을 원하면 여기에 추가할 수 있습니다. */
    }
</style>


<title>설문조사</title>
</head>
<body>


<h1>설문조사 1번</h1>

<h2>선호하는 육류를 선택해 주세요</h2>

<form id="animalForm" action="/customers/testPro" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <table>
        <tr>
            <td><img src="/resources/survey/pig.png/" alt="돼지" onclick="submitForm(10)"></td>
            <td><img src="/resources/survey/cow.png/" alt="소" onclick="submitForm(20)"></td>
        </tr>
    </table>
</form>

<script>
    function submitForm(animal) {
        // 폼 객체 가져오기
        var form = document.getElementById('animalForm');

        // 동적으로 hidden 필드 추가
        var hiddenField = document.createElement('input');
        hiddenField.setAttribute('type', 'hidden');
        hiddenField.setAttribute('name', 'selectedAnimal');
        hiddenField.setAttribute('value', animal);

        // 폼에 hidden 필드 추가
        form.appendChild(hiddenField);

        // 폼 제출
        form.submit();
        
        
    }
</script>



</body>
</html>