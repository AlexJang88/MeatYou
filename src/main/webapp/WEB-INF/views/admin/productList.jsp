<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
    <h1>상품목록</h1>
    <form method="post" action="/admin/searchPdList">
    	<input type="text" name="keyword">
    	<select name="serchOption">
    		<option value="title">제목</option>
    		<option value="customer">판매자</option>
    		<option value="all">전체</option>
    	</select>
    	<input type="submit" value="검색">
    </form> 
    <table>
    	<tr>
    		<td>썸네일</td>
    		<td>상품이름</td>
    		<td>가격</td>
    		<td>누적판매량</td>
    		<td>조회수</td>
    		<td>등록일</td>
    		<td>판매자</td>
    		<td>신고수</td>
    	</tr>
    <c:forEach var="product" items="${list}">
    	<tr>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>
			<td>${product. }</td>    		
    	</tr>
    </c:forEach>
    </table>
    
    