<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<a href="/admin/main">Home</a>
<c:forEach items="${reports}" var="report">
<c:if test="${reprot.ma_m_id!='admin'}">
<div>제목 : ${report.ma_title}</div><div><fmt:formatDate value="${report.ma_reg_date}" pattern="yyyy년 MM월 dd일 hh시 mm분 ss초"/></div><br>
<div>${report.ma_content}</div><br>
<c:if test="${report.ma_status==1000}">
	<form action="/admin/reportReply" method="post">
		<input type="hidden" name="ma_title" value="[답변]${report.ma_title}">
		<input type="hidden" name="ma_num" value="${report.ma_num}">
		<textarea rows="5" cols="30" name="ma_content"></textarea>
		<input type="submit" value="답변하기">
	</form>
</c:if>
</c:if>
<c:if test="${report.ma_m_id=='admin'}">
<c:if test="${report.ma_status!=1000}">
		<div>답변 내용</div><br>
		<div>${report.ma_reply}</div>
</c:if>
</c:if>
</c:forEach>