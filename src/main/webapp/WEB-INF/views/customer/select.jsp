<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>설문조사2</title>
</head>
<body>

<h1>설문조사 2번</h1>

<h2>선호하는 육류 부위를 선택해 주세요</h2>

<form id="surveyForm" action="/customers/selectPro" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="hidden" id="selectedAnimal" name="selectedAnimal" value="${selectedAnimal}">
    <table>
        <tr>
            <th colspan="7"><h1>돼지고기</h1></th>
        </tr>
        <tr>
            <td><img src="/resources/survey/pig/Samgyeopsal.png" alt="삼겹살" onclick="submitForm(1)"></td>
            <td><img src="/resources/survey/pig/Moksal.png" alt="목살" onclick="submitForm(2)"></td>
            <td><img src="/resources/survey/pig/Deungshim.png" alt="등심" onclick="submitForm(4)" ></td>
            <td><img src="/resources/survey/pig/Ahnshim.png" alt="안심" onclick="submitForm(3)"></td>
            <td><img src="/resources/survey/pig/Galmaegisal.png" alt="갈매기살" onclick="submitForm(6)"></td>
            <td><img src="/resources/survey/pig/Ahpdarisal.png" alt="앞다리살" onclick="submitForm(5)"></td>
            <td><img src="/resources/survey/pig/GitaBuwi.png" alt="기타부위" onclick="submitForm(0)"></td>
        </tr>
        <tr>
            <th colspan="7"><h1>소고기</h1></th>
        </tr>
        <tr>
            <td><img src="/resources/survey/cow/Ahnshim.png" alt="안심" onclick="submitForm(2)"></td>
            <td><img src="/resources/survey/cow/Buchaesal.png" alt="부채살" onclick="submitForm(6)"></td>
            <td><img src="/resources/survey/cow/Chaekkeut.png" alt="채끝" onclick="submitForm(4)"></td>
            <td><img src="/resources/survey/cow/Deungshim.png" alt="등심" onclick="submitForm(1)"></td>
            <td><img src="/resources/survey/cow/Galbisal.png" alt="갈비살" onclick="submitForm(3)"></td>
            <td><img src="/resources/survey/cow/Mokshim.png" alt="목심" onclick="submitForm(5)"></td>
            <td><img src="/resources/survey/cow/GitaBuwi.png" alt="기타부위" onclick="submitForm(0)"></td>
        </tr>
    </table>
</form>

<script>
    function submitForm(animal) {
        // 폼 객체 가져오기
        var form = document.getElementById('surveyForm');

        // 동적으로 hidden 필드 추가 또는 갱신
        var hiddenField = document.getElementById('selectedAnimal');
        if (!hiddenField) {
            hiddenField = document.createElement('input');
            hiddenField.setAttribute('type', 'hidden');
            hiddenField.setAttribute('id', 'selectedAnimal');
            hiddenField.setAttribute('name', 'selectedAnimal');
            form.appendChild(hiddenField);
        }

        // hidden 필드 값 갱신
        hiddenField.value = animal;

        // 폼 제출
        form.submit();       
    }
   
  
</script>

</body>
</html>