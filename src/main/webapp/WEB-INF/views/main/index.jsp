<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<!-- <script src="/resources/ryu/index.js"></script>
<script src="/resources/ryu/style.css"></script> -->

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
    <title>Bootstrap demo</title>
    <!--부트스트랩 css-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" 
    rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" 
    crossorigin="anonymous">
    
    <!--내가 만든 css 파일-->
    <link href="/resources/ryu/main.css" rel="stylesheet">

    <!--제이쿼리-->
    <script
    src="https://code.jquery.com/jquery-3.6.1.min.js"
    integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
    crossorigin="anonymous"></script>
</head>



    <button id="login">로그인</button>
    <div class="black-bg">
        <div class="white-bg">
          <h4>로그인하세요</h4>
          <button class="btn btn-danger" id="close">닫기</button>
        </div>
      </div> 

    <script>
       //모달창 띄우기
       $('#login').on('click', function(){
        $('.black-bg').addClass('show-modal');
       });

       //모달창 닫기
       $('#close').on('click', function(){
        $('.black-bg').removeClass('show-modal');
       });
        
    </script>
<!--거의 모든 자바스크립트 라이브러리는 <body> 끝나기전에 넣는거 권장-->    

<!--부트스트랩 JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" 
    crossorigin="anonymous"></script>
    
<%@ include file="../footer.jsp" %>