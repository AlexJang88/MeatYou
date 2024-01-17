<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
// 삭제 메서드
/* 장바구니 삭제 버튼 */
// JavaScript 코드 (페이지 내에 추가)
$(document).on("click", ".delete_btn", function(e) {
;
    console.log();
    const pm_num = $(this).data("pm_num");
    $(".quantity_delete_form").submit();
    e.preventDefault(pm_num);
    // AJAX를 사용하여 삭제 요청 보내기
    $.ajax({
        type: "POST",
        url: "/member/deleteHim",
        data: {
            pm_num: pm_num
        },
        success: function(response) {
            // 성공적으로 삭제된 경우 페이지 새로고침
            location.reload();
        },
        error: function(error) {
            console.log("Error:", error);
            // 오류가 발생한 경우 적절히 처리
        }
    });
});
 
</script>
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">내가 관심있는 업체</h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
내가 관심있는 판매자  
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>내가 찜한 업체</th>
            <th>매장 위치</th>
            <th>업체 전화번호</th>
            <th>대표자 성함</th>
            <th>판매자아이디</th>
            <th>입금계좌</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${pickMekList}">
            <tr>
                <td><c:out value="${item.company}"  /></td>
                <td><c:out value="${item.cus_address1}"/><c:out value="${item.cus_address2}" /></td>
                <td><c:out value="${item.cus_pnum}" /></td>
                <td><c:out value="${item.ceoname}" /></td>
                <td><c:out value="${item.p_m_id}" /></td>
                  <td><c:out value="${item.acc_num}" /></td>
                <td>
                    <form action="deleteHim" method="post" class="quantity_delete_form">
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        
                        <input type="hidden" name="pm_c_id" value="${item.pm_c_id}" />
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