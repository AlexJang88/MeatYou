<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../header.jsp" %>

<style>

 
  .wrap {
    width: 400px;
    margin: 0 auto;
    padding: 40px 20px 20px 20px;
    background-color: #f5f6f7;
    border-radius: 30px;
  }

  .title {
    text-align: center;
    font-size: 25px;
    margin-bottom: 20px;
    height: 60px;
    width: 360px;
  }

  .kakao {
    margin-top: 15px;
    height: 60px;
    border: solid 1px #FEE500;
    background: #FEE500;
    color: #3c1d1e;
    font-size: 18px;
    box-sizing: border-box;
    border-radius: 5px;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
  }

  .btn-primary {
    width: 100%; /* 로그인 버튼을 100% 너비로 설정하여 카카오톡 로그인 버튼과 크기를 동일하게 함 */
    height: 60px;
  }

  a {
    text-decoration: none;
  }

  /* 가운데 정렬을 위한 스타일 추가 */
  .center-text {
    text-align: center;
  }
</style>

<script>
  function openNewWindow(url, width, height) {
    var left = (screen.width - width) / 2;
    var top = (screen.height - height) / 2;
    window.open(url, "_blank", "width=" + width + ", height=" + height + ", left=" + left + ", top=" + top);
  }
</script>
<br/>
&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   
<div class="wrap">
  <div class="title">로그인</div>
  <form action="/login" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <div class="mb-3">
      <label for="username" class="form-label">아이디</label>
      <input type="text" class="form-control" id="username" name="username">
    </div>
    <div class="mb-3">
      <label for="password" class="form-label">비밀번호</label>
      <input type="password" class="form-control" id="password" name="password">
    </div>
    <div class="mb-3 form-check">
      <input type="checkbox" class="form-check-input" id="remember-me" name="remember-me">
      <label class="form-check-label" for="remember-me">자동로그인</label>
    </div>
<br/>
    <!-- 아이디 찾기와 비밀번호 찾기를 가운데 정렬 -->
    <div class="center-text">
      <a href="javascript:void(0);" onclick="openNewWindow('/member/idfind', 500, 450)">
        <span>아이디 찾기</span>
      </a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;          
      <a href="javascript:void(0);" onclick="openNewWindow('/member/pwfind', 500, 540)">
        <span>비밀번호 찾기</span>
      </a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;     
      <a href="/member/inputForm"><i class="fa"></i>회원가입 </a>
    </div><br/>

    <button type="submit" class="btn btn-primary">로그인</button><br/>
  <a class="kakao" href="https://kauth.kakao.com/oauth/authorize?client_id=${key}&redirect_uri=${uri}&response_type=code">
    <div class="kakao_txt">카카오톡 로그인</div>
  </a>
</div>

<%@ include file="../../footer.jsp" %>
