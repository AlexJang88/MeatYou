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
    const shop_num = $(this).data("shop_num");
    $(".quantity_delete_form").submit();
    e.preventDefault(shop_num);
    // AJAX를 사용하여 삭제 요청 보내기
    $.ajax({
        type: "POST",
        url: "/member/delete",
        data: {
            shop_num: shop_num
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

// 수량 변경 함수
function update_click(button, operation, shop_num) {
    // 수량을 조절할 입력 요소 가져오기
    var input_element = button.parentElement.querySelector('.shop_quantity');

    // 현재 수량 가져오기
    var current_quantity = parseInt(input_element.value);
    console.log("기존값=" + current_quantity);

    // 버튼에서 가져온 데이터와 함께 현재 수량을 기반으로 새로운 수량 계산
    var shop_quantity = button.dataset.shop_quantity;
    var new_quantity;
    if (operation == 'increase') {
        new_quantity = current_quantity + 1;
    } else if (operation == 'decrease' && current_quantity > 1) {
        new_quantity = current_quantity - 1;
    } else {
        return; // 작업이 유효하지 않으면 아무 작업도 수행하지 않음
    }

    // 서버로 업데이트된 수량 정보를 전송
    console.log(new_quantity);
    updateQuantity(new_quantity, shop_num);
}

// 서버에 수량 업데이트 요청을 보내는 함수
function updateQuantity(new_quantity, shop_num) {
    // 서버 측 컨트롤러 메서드에 대한 AJAX 요청 보내기
    $.ajax({
        type: "POST",
        url: "/member/updateQuantity",
        data: {
        	shop_quantity: new_quantity,
            shop_num: shop_num
        },
        success: function(response) {
            console.log(response);
            // updateUIWithData(response);
            location.reload(); // 또는 location.href = location.href;
        },
    });
}
</script>
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">장바구니</h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
        장바구니 
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>상품 내용</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>금액</th>
            <td>판매자</td>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${shoppingCartList}">
            <tr>
                <td><c:out value="${item.p_name}" /></td>
                <td><c:out value="${item.thumb}" /></td>
                <td>
                    <button type="button" class="quantity-up" data-shop_quantity="${item.shop_quantity}" onclick="update_click(this, 'increase','${item.shop_num}')">+</button>
                    <input type="hidden" class="shop_quantity" value="${item.shop_quantity}" />
                    <c:out value="${item.shop_quantity}" />
                    <button type="button" class="quantity-down" data-quantity="${item.shop_quantity}" onclick="update_click(this, 'decrease','${item.shop_num}')">-</button>
                </td>				    
                <td>
                    <b>총액  </b>
                    <c:out value="${item.p_price * item.shop_quantity}" />
                    <b> 원</b>
                </td> 
                <td><c:out value="${item.p_m_id}" /></td>
                <td>
                    <form action="delete" method="post" class="quantity_delete_form">
                        <input type="hidden" name="shop_num" value="${item.shop_num}" />
                        <input type="hidden" name="shop_num" value="${item.shop_num}" />
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