<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<script>
    $(document).ready(function () {
        $(".quantity-up, .quantity-down").on("click", function () {
            var shopNum = $(this).data("shop-num");
            var inputField = $("#quantity-" + shopNum);
            var form = $("#form-" + shopNum);

            // Determine whether to increase or decrease based on the button clicked
            var isIncrease = $(this).hasClass("quantity-up");
            var currentQuantity = parseInt(inputField.val());

            // Adjust quantity
            if (isIncrease) {
                inputField.val(currentQuantity + 1);
            } else {
                if (currentQuantity > 1) {
                    inputField.val(currentQuantity - 1);
                }
            }

            // Submit the corresponding form
            form.submit();
        });
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
        <form action="/shoppingCart/numModify" method="post">
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
                            <td><c:out value="${item.p_name}" /> </td>
                            <td>
                                <input type="hidden" name="shop_num" value="${item.shop_num}" />
                                <button type="submit" class="quantity-up" data-shop-num="${item.shop_num}">+</button>
                                <input type="text" class="quantity" id="quantity-${item.shop_num}" data-shop-num="${item.shop_num}" name="quantity" value="${item.quantity}" />
                                <button type="submit" class="quantity-down" data-shop-num="${item.shop_num}">-</button>
                            </td>
                            <td><c:out value="${item.thumb}" /></td>
                            <td><c:out value="${item.p_price * item.quantity}" /> <b>원</b></td>
                            <td><input type="checkbox"></td>
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