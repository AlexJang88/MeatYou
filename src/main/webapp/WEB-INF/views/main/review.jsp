<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<!DOCTYPE html>
<div class="jumbotron">
    <h1>${ book.title }</h1>
    <p>${ book.author } 저</p>
</div>
<div class="thumbnail">
    <img src="/resources/img/product01.png">
</div>
<div class="page-header">
    <h2>리뷰</h2>
</div>
<c:url var="reviewsPath" value="/reviews" />
<f:form modelAttribute="review" action="${ reviewsPath }" method="post">
    <c:forEach var="error" items="${ fieldErrors }">
        <div class="alert alert-warning">
            <strong>${ error.getField() }</strong>: ${error.getDefaultMessage() }
        </div>
    </c:forEach>
    <f:textarea path="text" cssClass="form-control" rows="5" />
    <f:hidden path="bookId" />
    <f:hidden path="userId" />
    <button class="btn btn-block btn-primary" type="submit">리뷰 등록</button>
</f:form>