<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
// 삭제 메서드
function deleteSelectedItems(button,shop_num) {
  var selectedItems = [];
  $(".checkbox:checked").each(function () {
    selectedItems.push($(this).data("shop-num"));
  });

  if (selectedItems.length === 0) {
    alert("삭제할 상품을 선택하세요.");
    return;
  }

  $.ajax({
    type: "POST",
    url: "/member/deleteSelectedItems",
    data: {
      selectedItems: JSON.stringify(selectedItems),
    },
    success: function (response) {
      console.log(response);
      $("#상품목록").html(response.상품목록);
      $("#성공메시지").show();
    },
    error: function (error) {
      console.error(error);
      alert("삭제 중 오류가 발생했습니다. 다시 시도해주세요.");
      $("#오류메시지").show();
    },
  });
}
function update_click(button, operation,shop_num) {
    var input_element = button.parentElement.querySelector('.quantity');
    
    var current_quantity = parseInt(input_element.value);
    console.log("기존값="+current_quantity);
    // 기존 수량과 버튼에서 가져온 데이터
    	
    var quantity =button.dataset.quantity;
    var new_quantity;
    if (operation == 'increase') {
        new_quantity = current_quantity + 1;
    } else if (operation == 'decrease' && current_quantity > 1) {
        new_quantity = current_quantity - 1;
    } else {
        return; // Do nothing if the operation is not valid
    }

    // 서버로 업데이트된 수량 정보를 전송
    console.log(new_quantity);
    updateQuantity(new_quantity , shop_num);

}

function updateQuantity(new_quantity, shop_num) {
    // Make an AJAX request to the server-side controller method
    $.ajax({
        type: "POST",
        url: "/member/updateQuantity",
        data: { 
        quantity: new_quantity ,shop_num:shop_num 

        
        },
        success: function(response) {
            console.log(response);
           // updateUIWithData(response);
            location.reload(); // or location.href = location.href;
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
        <form action="/updateQuantity" method="post" onsubmit="submitForm();">
            <table class="table table-striped table-bordered table-hover">
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>상품 내용</th>
                        <th>상품 사진</th>
                        <th>상품 수량</th>
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
                    <td><c:out value="${item.thumb}" /></td>
			 <td>
			  	<button type="button" class="quantity-up" data-quantity="${item.quantity}"  onclick="update_click(this, 'increase','${item.shop_num}')">+</button>
		 		<input type="hidden" class="quantity" value="${item.quantity}" />
		 		<c:out value="${item.quantity}" />
				<button type="button" class="quantity-down" data-quantity="${item.quantity}"onclick="update_click(this, 'decrease','${item.shop_num}')">-</button>
              </td>				    
                    
				    <td><b>총액  </b>
						<%--	${item.p_price * item.quantity} --%>
				 	  		<c:out value="${item.p_price * item.quantity}" />
				 	  	<b> 원</b></td> 
				  <td><input type="checkbox" value="item1" class="checkbox" data-shop-num="1"></td>
				    <td><c:out value="${item.shop_m_id}" /></td>
				  </tr>
				 
				</c:forEach>
                </tbody>
            </table>
        </form>
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

<%-- <form id="deleteForm"  action="/deleteSelectedItems" method="post"  >
   <c:forEach var="items" items="${deleteforyou}">
    <button type="button" class="btn btn-danger" onclick="deleteSelectedItems(${items.shop_num})">선택된 상품 삭제</button>
		</c:forEach>
</form>
 --%>
<form id="deleteForm" method="post">
    <c:forEach var="items" items="${selectedItems}">
        <button type="button" class="btn btn-danger" onclick="deleteSelectedItems(${items.shop_num})">
            선택된 상품 삭제 - ${items.shop_num}
        </button>
    </c:forEach>
</form>

    </div>
</div>
<%@ include file="../../footer.jsp" %>