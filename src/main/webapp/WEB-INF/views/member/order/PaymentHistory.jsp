<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> 
결제내역입니다  </h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>결제일</th>
            <th>주문한내아이디(히든예정)</th>
            <th>배송주소</th>
            <th>수량</th>
            <th>할인금액</th>
            <th>배송상태</th>
            <th>배송메모</th>
            <th>총결제금액</th>
            <th>배송시작일</th>
            <th>배송도착일</th>
        </tr>
    </thead>
    <tbody>
    
 
        <c:forEach var="item" items="${paypageList}">
            <tr>
                <td><fmt:formatDate value="${item.order_paydate}" pattern="yyyy/MM/dd"/> </td>
                <td><c:out value="${item.order_m_id}" /></td>
                <td><c:out value="${item.order_addr}" /></td>
                <td><c:out value="${item.order_quantity}" /></td>
                <td><c:out value="${item.order_discount}" /></td>
                <td>
                <c:if test="${item.order_status eq 0}">
               취소된 상품
                </c:if>
                <c:if test="${item.order_status eq 1}">
             	결제 완료
                </c:if>
                
                <c:if test="${item.order_status eq 2}">
               배송중
                </c:if>
                
                <c:if test="${item.order_status eq 3}">
                배송완료
                </c:if>
                
                <c:if test="${item.order_status eq 4}">
           			구매확정
                </c:if>
                
                <td><c:out value="${item.order_memo}" /></td>
                <td><c:out value="${item.order_totalprice}" /> 원</td>
                <td><fmt:formatDate value="${item.order_dere_start}" pattern="yyyy/MM/dd"/> </td>
                  <td><fmt:formatDate value="${item.order_canceldate}" pattern="yyyy/MM/dd"/> </td>
                    <td><fmt:formatDate value="${item.ordere_end}" pattern="yyyy/MM/dd"/> </td>
               
                  
                  
              
            </tr>
        </c:forEach>
    </tbody>
</table>
     	
<div class="pagination" style="height: 10px;">
    <c:if test="${page > 1}">
        <a href="?page=${page - 1}&pageSize=${pageSize}">&laquo; 이전</a>
    </c:if>

    <c:forEach var="pageNumber" begin="1" end="${totalPage}">
        <c:choose>
            <c:when test="${pageNumber == page}">
                <span class="current">${pageNumber}</span>
            </c:when>
            <c:otherwise>
                <a href="?page=${pageNumber}&pageSize=${pageSize}">${pageNumber}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page < totalPage}">
        <a href="?page=${page + 1}&pageSize=${pageSize}">다음 &raquo;</a>
    </c:if>
</div>
<%@ include file="../../footer.jsp" %>