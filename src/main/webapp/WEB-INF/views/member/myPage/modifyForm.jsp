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
        body {
            padding: 20px;
        }
    </style>
</head>
<body>

<form method="post" action="modifyPro" name="userinput">

    <div class="container">
        <h1 class="text-center">회원 정보수정[${dto.m_reg_date.substring(0, 10)}]</h1>
        <p class="text-center">회원의 정보를 수정합니다.</p>

        <div class="row">
            <div class="col-md-6">
                <div class="form-group">
                    <label for="userId">아이디 입력</label>
                    <input type="text" class="form-control" id="userId" name="userId" readonly value="${dto.m_id}">
                </div>
                <div class="form-group">
                    <label for="password">비밀번호</label>
                    <input type="password" class="form-control" id="password" name="passwd" value="${dto.passwd}">
                </div>
            </div>

            <div class="col-md-6">
                <div class="form-group">
                    <label for="userName">사용자 이름</label>
                    <input type="text" class="form-control" id="userName" readonly value="${dto.m_name}">
                </div>
                <div class="form-group">
                    <label for="address">주소</label>
                    <input type="text" class="form-control" id="address" name="m_addr1" placeholder="주소" value="${dto.m_addr1}">
                    <button type="button" id="addressButton" class="btn btn-primary btn-block mt-3">주소 검색</button>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    <input type="hidden" name="cus_num" />
                </div>
                <div class="form-group">
                    <label for="detailAddress">상세주소</label>
                    <input type="text" class="form-control" id="detailAddress" name="m_addr2" placeholder="상세주소" value="${dto.m_addr2}">
                </div>
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

        <div class="text-center">
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
