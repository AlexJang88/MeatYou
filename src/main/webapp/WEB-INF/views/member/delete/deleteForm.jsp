<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ include file="../../header.jsp" %>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<html>
 
<%-- 실패한 경우에만 errorMsg가 존재하므로 해당 경우에 메시지를 출력합니다. --%>
  
<head>
  <script>
  function withdrawButton() {
	  	var passwd=$('#passwd').val();
	  			$.ajax({
	  						
	  				 url:'./deleteCheck', //Controller에서 요청 받을 주소
	  		        type:'post', //POST 방식으로 전달
	  		        data:{passwd:passwd},
	  		      success:function(check){ //컨트롤러에서 넘어온 check값을 받는다 
	  	            if(check == 0){ //check가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
	  	         
	  	              alert("비밀번호 틀렸잖아");
	  	            window.location.href = "/member/deleteForm";
	  	            } else { // check가 1일 경우 -> 이미 존재하는 아이디
	  	              
	  	             	 window.location.href = "/member/deletePro";
	  	            	 alert("지금까지 I Meat You를 이용해주셔서 감사합니다...잘가요");
	  	            }
	  	        },
	  	        error:function(){
	  	            alert("에러입니다");
	  	        }
	  	    });
	  	    };
	 
    </script>
    <title>회원탈퇴</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+Wy5Nxv3DlXtIKAj5I9pYj4me1upNBdPyqD" crossorigin="anonymous">
    <style>
        body {
            margin-top: 50px;
        }

        .form-container {
            width: 300px;
            margin: 0 auto;
            margin-top: 50px;
        }

        .table-container {
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            overflow: hidden;
            width: 400px; /* Adjust the width as needed */
            margin: 0 auto;
            padding: 20px;
        }

        .btn-main {
            margin-top: 10px;
        }
    </style>
</head>

<body onload="begin()">
    <div class="container" style="margin-top:150px; margin-bottom:100px;">
        <div class="table-container">
<form name="myform" action="deletePro" method="post"  oninput="withdrawButton()">
                <div class="card">
                    <div class="card-header text-center">
                        <h5 class="card-title">회원탈퇴</h5>
                    </div>
                    <div class="card-body">
                        <div class="form-group">
                            <label for="passwd">비밀번호</label>
                            <input type="password" class="form-control" id="passwd" name="passwd" size="15" maxlength="12" required>
                        </div>
                        <input type="hidden" name="m_id" value="${m_id}">
                        <div class="form-group">
                            <input type="submit" value="회원탈퇴" class="btn btn-danger btn-block"">
                        </div>
                        <div class="form-group">
                            <input type="button" value="메인으로" class="btn btn-secondary btn-block btn-main"
                                onclick="javascript:window.location='/main/main'">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-JXrtIpgTzko5s9f3fAENshIjD5zJvnw6vyeK7W2K7k2Mfy+K6v7/iJcyDGVgiqAg"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
        integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8sh+Wy5Nxv3DlXtIKAj5I9pYj4me1upNBdPyqD"
        crossorigin="anonymous"></script>
</body>

<%@ include file="../../footer.jsp" %>
</html>
