<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        document.getElementById('addressButton').addEventListener('click', function () {
            new daum.Postcode({
                oncomplete: function (data) {
                    var fullAddress = data.address;
                    document.getElementById('address').value = fullAddress;
                }
            }).open();
        });
    });
</script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
<%@ include file="../../header.jsp" %>

<html>
<head>
    <title>회원정보수정</title>
    <style>
    .container {
        margin: auto;
    }

    .form-group {
        width: 100%; /* Adjust the value as needed */
    }

    .text-center input[type="submit"],
    .text-center input[type="button"] {
        width: 80px; /* Adjust the value as needed */
    }
    </style>
</head>
<body>

<form method="post" action="modifyPro" name="userinput">
<div class="container" style="width:26%;">
    <h1 class="text-center" style="margin-top:20px;">회원 정보수정 </h1>
    <div class="form-group">
        <label for="userId"  style=" margin-top:10px;align-items: center; ">고객님께서 가입하신날짜는 ${dto.m_reg_date.substring(0, 10)}입니다</label>
    </div>
			
    <div class="form-group">
        <label for="userId">고객님의 아이디</label>
        <input type="text" class="form-control" id="userId" name="userId" readonly value="${dto.m_id}">
    </div>

    <div class="form-group">
        <label for="password">수정하실 비밀번호</label>
        <input type="password" class="form-control" id="password" name="passwd" value="${dto.passwd}">
    </div>

    <div class="form-group">
        <label for="userName">고객님 성함</label>
        <input type="text" class="form-control" id="userName" readonly value="${dto.m_name}">
    </div>
    
<div class="form-group" style="margin-top: 20px; ">
    <label for="address">주소와 상세주소를 입력해주세요</label> <br/>
    <div style="display: flex; align-items: center; justify-content: center; margin-top: 10px;">
        <button type="button" id="addressButton" class="btn btn-light" style="margin-left: 0px; background-color:white; width:120%;">
        <input type="text" class="form-control" id="address" name="m_addr1"  placeholder="주소" style="align-items: center;  width: 100%;" value="${dto.m_addr1}"/> </button>
        <input type="text" class="form-control" name="m_addr2" style="width: 110%; margin-left:10px;" placeholder="상세주소" value="${dto.m_addr2}">
    </div>
</div>
    <div class="form-group">
        <label for="email">E-Mail</label>
        <input type="text" class="form-control" id="email" name="email" value="${dto.email}" size="40" maxlength="30">
    </div>

    <div class="form-group">
        <label for="telep">전화번호</label>
        <input type="text" class="form-control" id="telep" name="telep" size="40" maxlength="30" value="${dto.telep}">
    </div>

    <div class="form-group">
        <label for="phone">휴대폰번호</label>
        <input type="text" class="form-control" id="phone" name="phone" size="40" maxlength="30" value="${dto.phone}">
    </div>

    <div class="text-center" style="margin-bottom:30px;margin-top:30px;">
        <input type="submit" class="btn btn-primary" name="modify" value="수정">
        <input type="button" class="btn btn-secondary" value="취소" onclick="javascript:window.location='/main/main'">
    </div>
</div>
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</body>
</html>
<%@ include file="../../footer.jsp" %>
