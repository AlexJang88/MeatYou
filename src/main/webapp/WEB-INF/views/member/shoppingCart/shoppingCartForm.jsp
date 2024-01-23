<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
//삭제 메서드
/* 장바구니 삭제 버튼 */
// JavaScript 코드 (페이지 내에 추가)

// 기존의 ".delete_btn" 클릭 이벤트
$(document).on("click", "#deleteSelectedBtn", function(e) {
    var selectedCheckboxes = $('input[name="product"]:checked');

    if (selectedCheckboxes.length === 0) {
        alert("선택된 상품이 없습니다.");
        return;
    }

    var selectedShopNums = [];
    selectedCheckboxes.each(function() {
        var shop_num = $(this).closest('tr').find('.delete_btn').data('shop_num');
        selectedShopNums.push(shop_num);
    });

    // AJAX를 사용하여 선택된 상품 삭제 요청 보내기
    sendDeleteRequest(selectedShopNums);

    e.preventDefault();
});
// 새로운 함수: 상품 삭제 요청을 보내는 함수
function sendDeleteRequest(selectedShopNums) {
    $.ajax({
        type: "POST",
        url: "/member/deleteSelected",
        data: {
            shop_num: selectedShopNums
        },
        // traditional: true,  // traditional 옵션 제거
        success: function(response) {
            location.reload();
        },
        error: function(error) {
            console.log("Error:", error);
        }
    });
}

// 새로운 함수: 상품 수량 업데이트 요청을 보내는 함수
function sendUpdateQuantityRequest(new_quantity, shop_num) {
    var requestData = {
        shop_quantity: new_quantity,
        shop_num: shop_num
    };
    $.ajax({
        type: "POST",
        url: "/member/updateQuantity",
        data: requestData,
        success: function(response) {
            console.log(response);
            location.reload();
        },
    });
}

// 기존의 "update_click" 함수 수정
function update_click(button, operation, shop_num) {
    var input_element = button.parentElement.querySelector('.shop_quantity');
    var current_quantity = parseInt(input_element.value);

    var shop_quantity = button.dataset.shop_quantity;
    var new_quantity;

    if (operation == 'increase') {
        new_quantity = current_quantity + 1;
    } else if (operation == 'decrease' && current_quantity > 1) {
        new_quantity = current_quantity - 1;
    } else {
        return;
    }

    // 서버로 업데이트된 수량 정보를 전송
    console.log(new_quantity);
    sendUpdateQuantityRequest(new_quantity, shop_num);
}

// 기존의 "#deleteSelectedBtn" 클릭 이벤트 수정
$(document).on("click", "#deleteSelectedBtn", function(e) {
    var selectedCheckboxes = $('input[name="product"]:checked');

    if (selectedCheckboxes.length === 0) {
        alert("선택된 상품이 없습니다.");
        return;
    }

    var selectedShopNums = [];
    selectedCheckboxes.each(function() {
        var shop_num = $(this).closest('tr').find('.delete_btn').data('shop_num');
        selectedShopNums.push(shop_num);
    });

    // AJAX를 사용하여 선택된 상품 삭제 요청 보내기
    sendDeleteRequest({ shop_num: selectedShopNums });
    
    e.preventDefault();
});

</script>
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">장바구니</h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
   
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
        상품목록 
<button id="toggleButton" onclick="toggleTableVisibility()">숨기기</button>

      <table id="productTable" class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
        	<th>
		<input type='checkbox'
       value='selectall'
       onclick='selectAll(this)'/></th>
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
            	<tr><td>
            	<input type="checkbox" name="product"/>
            </td>
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
                        <input type="hidden" name="p_num" value="${item.p_num}" />
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
        	<td></td>
        	<td></td>
        	<td></td>
        	<td style="height: 10px;">
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
        	</td>
        	<td></td>
        	<td></td>
        </tr>
    </tbody>
    
</table>
			<table>
					<tbody>
							<tr>
							<td>선택한 상품</td><td><a href="#">주문하기</a></td>
							
  <td><a href="#" id="deleteSelectedBtn">선택된 상품 삭제하기</a></td>
							</tr>	
					</tbody>
			</table>
			
			
			<table>
					<tbody>
					
						<tr>
							<td>
							<input type="submit" value="결제하기"> 
							</td>
							<td></td>
						</tr>	
					</tbody>
					
					
			</table>
    </div>
</div>
<%@ include file="../../footer.jsp" %>