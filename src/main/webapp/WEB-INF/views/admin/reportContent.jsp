<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            text-align: center;
        }
 

        .summary-table {
            width: 80%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
            margin: 0 auto;
            border:1px solid black;
        }

        .summary-table th, .summary-table td {
            font-family: 'Arial', sans-serif;
            font-size: 12px;
            border: 1px solid #ddd;
            padding: 10px;
            text-align: center;
             border:1px solid black;
        }

 

 

     
        input[type="submit"], input[type="reset"], input[type="button"] {
            padding: 8px 20px;
            border-radius: 4px;
            background-color: lightgray;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover, input[type="reset"]:hover, input[type="button"]:hover {
            background-color: gray;
        }

    </style>
    <%@ include file="../header.jsp" %>
    <meta charset="UTF-8">
    <title>Q&A Details</title>
</head>

<body>
    <div class="main-container"style="border=1px solid black; width: 70%; align:center; margin:0 auto; " >
        <table class="summary-table" >
			<c:forEach items="${reports}" var="report">
<c:if test="${reprot.ma_m_id!='admin'}">
<tr align="center">
<td>
제목 : ${report.ma_title} <br/>
<fmt:formatDate value="${report.ma_reg_date}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/>
	</td>
</tr>
 
 
<tr>
<td border=1>
<div align="center">${report.ma_content}</div><br/>

</td>
</tr>
<c:if test="${report.ma_status==1000}">
	<form action="/admin/reportReply" method="post"align="center">
		<input type="hidden" name="ma_title" value="[답변]${report.ma_title}"align="center">
		<input type="hidden" name="ma_num" value="${report.ma_num}"align="center">
		<table style="border=1px solid black; width: 70%; align:center; margin:0 auto; " >
		<textarea rows="5" cols="30" name="ma_content"></textarea>
			</table>
			<table style="border=1px solid black; width: 70%; align:center; margin:0 auto; " >
		<input type="submit" value="답변하기">
		</table>
	</form>
	</table>
</c:if>
</c:if>
<c:if test="${report.ma_m_id=='admin'}">
<c:if test="${report.ma_status!=1000}">
		<div>답변 내용</div><br>
		<div>${report.ma_reply}</div>
</c:if>
</c:if>
</c:forEach>
                
</table>
    </div>









    <%@ include file="../footer.jsp" %>
