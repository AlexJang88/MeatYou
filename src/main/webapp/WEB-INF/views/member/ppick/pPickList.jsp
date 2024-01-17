<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
	.heartHim:{
		cursor: pointer;
	}
	.heartHim:hover:{
		font-weight: bold;
	}
</style>
<script>
// 삭제 메서드
/* 장바구니 삭제 버튼 */
// JavaScript 코드 (페이지 내에 추가)

$(document).on("click", ".delete_btn", function(e) {
    e.preventDefault();

    // 현재 클릭된 버튼이 속한 행에서 ppic_num 값을 가져옴
    const ppic_num = $(this).closest("tr").find(".ppic_num").val();

    // AJAX를 사용하여 삭제 요청 보내기
    $.ajax({
        type: "POST",
        url: "/member/deletePick",
        data: {
            ppic_num: ppic_num
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
        <h1 class="page-header">내가 관심있는상품 </h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
내가 관심있는상품
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>상품이름 </th>
            <th>상품사진</th>
            <th>매장 위치</th>
            <th>판매등록일 또는 등록예정일</th>
            <th>판매 시작 날짜</th>
            <th>판매 종료 날짜 </th>
            <th>상품상태</th>
            <th>판매자아이디</th>
            <th>가격</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${pPickList}">         
            <tr>
                <td><c:out value="${item.p_name}" /></td>
                <td><c:out value="${item.thumb}" /></td>
                <td>
           		<c:out value="${item.cus_address1}" /> <c:out value="${item.cus_address2}" />  
                </td>
                  <td><c:out value="${item.p_reg_date}" /></td>
                  
                  <td><c:out value="${item.startdate}" /></td>
                  
                  <td><c:out value="${item.enddate}" /></td>
                  
                  <td><c:out value="${item.p_status}" /></td>
                  <td>
                  	<a href="#" class="heartHim"> 
                  	<c:out value="${item.p_m_id}" />
                  	</a>
                  	</td>
                  <td><c:out value="${item.p_price}" /></td>
                <td>
                    <form action="deleteP_item" method="post" class="quantity_delete_form">
                        <input type="hidden" name="ppic_num" value="${item.ppic_num}" />
                        
                              <input type="hidden"  class="ppic_num"  value="${item.ppic_num}"/> 	
               				 	<input type="hidden"  class="ppic_p_num"  value="${item.ppic_p_num}"/>  
             			  	  <input type="hidden"  class="p_num"  value="${item.p_num}"/>  
                   
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