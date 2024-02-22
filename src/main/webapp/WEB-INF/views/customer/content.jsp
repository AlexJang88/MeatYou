<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용</title>
</head>
<body>
<center>

	<h2>이런저런 내용들 </h2>	
	${p_num} 번 글입니다 <br/>
	
	<input type="button" value="글수정" 
	 onclick="document.location.href='/customers/productUpdate?num=${p_num}'">
</center>
</body>
</html>













