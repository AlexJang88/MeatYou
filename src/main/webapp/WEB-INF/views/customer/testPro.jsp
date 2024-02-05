<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>설문 감사</title>

<!-- 추가된 부분 시작 -->
<style>
    /* 스타일을 추가하여 버튼이 좀 더 시각적으로 나타나도록 합니다. */
    .close-button {
        padding: 10px;
        background-color: #3498db;
        color: #fff;
        border: none;
        cursor: pointer;
    }
</style>
<!-- 추가된 부분 끝 -->

</head>
<body>

<h2>설문을 참여해주셔서 감사합니다</h2>

<!-- 추가된 부분 시작 -->
<button class="close-button" onclick="closeWindow()">창 닫기</button>

<script>
    // 창 닫기 함수
    function closeWindow() {
        window.close(); // 현재 창을 닫습니다.
    }
</script>
<!-- 추가된 부분 끝 -->

</body>
</html>
