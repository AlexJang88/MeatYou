<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<script>
$(document).ready(function () {
    // 수량 변경 이벤트 처리
    $(".panel-body").on("click", ".quantity-up, .quantity-down", function () {
        var quantity = $(this).data("quantity");
        var inputField = $("#quantity-" + quantity);
        var new_quantity = parseInt(inputField.val());

        var isIncrease = $(this).hasClass("quantity-up");
        new_quantity = isIncrease ? Math.min(new_quantity + 1, 999) : Math.max(new_quantity - 1, 1);

        inputField.val(new_quantity);

        // data-quantity 속성 갱신
        $(this).data("quantity", new_quantity);

        // shop_m_id와 shop_num 추가
        var shop_m_id = $(this).data("shop-m-id");
        var shop_num = $(this).data("shop-num");

        updateQuantity(quantity, shop_m_id, shop_num);
    });

    // 총 금액 계산 및 화면 갱신
    function updateQuantity(quantity, shop_m_id, shop_num) {
        $.ajax({
            type: "POST",
            url: "/member/updateQuantity",
            data: {
                shop_num: shop_num,
                quantity: quantity,
                shop_m_id: shop_m_id
            },
            dataType: 'json', // 서버로부터 JSON 데이터를 기대
            success: function (data) {
                $("#quantity-" + quantity).val(data.quantity); // 서버에서 받아온 새로운 수량으로 업데이트
                var totalPrice = 0;
                $(".quantity").each(function () {
                    var itemQuantity = parseFloat($(this).val());
                    var itemPrice = parseFloat($(this).parents("tr").find(".price").text());
                    totalPrice += itemQuantity * itemPrice;
                });
                $("#totalPrice").text(totalPrice);
            },
            error: function (xhr, error) {
                alert("수량 변경에 실패했습니다.");
            }
        });
    }

    // 폼 제출 처리
    function submitForm() {
        // 필요한 경우 추가적인 폼 유효성 검증 로직을 여기에 추가
        // ...

        // Ajax로 서버에 데이터 전송
        // 여기서 필요한 경우 서버로의 추가적인 동작을 정의
        // ...

        // 서버로의 기본 제출을 막기 위해 false 반환
        return false;
    }

    // 폼에 이벤트 핸들러 등록
    $("form").submit(submitForm);
});
</script>


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
        <form action="/shoppingCart/updateQuantity" method="post" onsubmit="submitForm();">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>상품 내용</th>
                        <th>상품 수량</th>
                        <th>상품 사진</th>
                        <th>금액</th>
                        <th>체크</th>
                        <td>판매자</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${shoppingCartList}">
				  <tr>
				    <td><c:out value="${item.shop_num}" /></td>
				    <td><c:out value="${item.p_name}" /></td>
				    <td>
				    <%--   <input type="hidden" name="quantity" value="${item.quantity}" /> --%>
				      <button type="button" class="quantity-up" data-quantity="${item.quantity}">+</button>
				      <input type="text" class="quantity" id="quantity-${item.quantity}" value="${item.quantity}" />
				      <button type="button" class="quantity-down" data-quantity="${item.quantity}">-</button>
				    </td>
				    <td><c:out value="${item.thumb}" /></td>
				    <td><b>총액  <span class="totalPrice">${item.p_price * item.quantity}</span> 원</b></td> <td><input type="checkbox"></td>
				    <td><c:out value="${item.shop_m_id}" /></td>
				  </tr>
				</c:forEach>
                </tbody>
            </table>
        </form>

        <!-- 삭제 버튼 -->
        <form action="/shoppingCart/delete" method="post">
            <button type="submit" class="btn btn-danger">선택된 상품 삭제</button>
        </form>
    </div>
</div>

<%@ include file="../../footer.jsp" %>