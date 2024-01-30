<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> 
나를 찜한 회원 </h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
나를 찜한 회원  
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>나를 찜한 회원</th>
            <th>내아이디(히든처리)</th>
            <th>회원이름</th>
            <th>회원이메일</th>
            <th>회원 전화번호</th>
            <th>회원등급</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${SallerPickMeList}">
            <tr>
                <td><c:out value="${item.pm_m_id}"  /></td>
                <td><c:out value="${item.pm_c_id}"/></td>
                <td><c:out value="${item.m_name}" /></td>
                <td><c:out value="${item.email}" /></td>
                <td><c:out value="${item.telep}" />-<c:out value="${item.phone}" /></td>
                  <td><c:out value="${item.m_status}" />
                   <c:if test="${dto.m_status eq 1000}">
							탈퇴회원
						</c:if>
					<c:if test="${dto.m_status eq 1050}">
							판매자승인대기자  
					</c:if>
				
				<c:if test="${dto.m_status eq 1001}">
				일반회원
				</c:if>
				
				<c:if test="${dto.m_status eq 1002}">
					우수회원
				</c:if>
				
				<c:if test="${dto.m_status eq 1003}">
						단골회원 
				</c:if>
                  
                  
                  
                  </td>
                <td>
                    <form action="SallerdeleteHim" method="post" class="quantity_delete_form">
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        
                        <input type="hidden" name="pm_m_id" value="${item.pm_m_id}" />
                        
                        
                        
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
                </td>
              
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- 페이징 -->
<div class="pagination">
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
    </div>
</div>
<%@ include file="../../footer.jsp" %>