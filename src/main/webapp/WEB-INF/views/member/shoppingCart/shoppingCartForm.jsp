<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
//선택주문 
// 선택한 상품 주문 함수
// 선택된 쿠폰을 저장하는 변수
//var selectedCoupon = "";
function orderSelectedItems() {
    var selectedShopNums = getSelectedShopNums(); // 선택된 상품의 shop_num을 가져오는 함수
    var selectedCoupon = $('#cList').val(); // select 태그에서 선택된 쿠폰 값을 가져오기
    on
    // 선택한 상품의 shop_num을 URL 파라미터로 세 번째 페이지로 전달
    window.location.href = "/member/orderPageTwo?check=1&selectedShopNums=" + selectedShopNums.join(',') +	"&selectedCoupon=" + selectedCoupon,join(',');
    //	+	"&selectedCoupon=" + selectedCoupon;;
}

// 선택된 상품의 shop_num을 가져오는 함수
function getSelectedShopNums() {
    var selectedShopNums = [];
    var checkboxes = document.getElementsByName('selectedShopNums');

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            selectedShopNums.push(checkboxes[i].value);
        }
    }
    return selectedShopNums;
}

// 상품 수량 업데이트 함수
function update_click(button, operation, shop_p_num) {
    var input_element = button.parentElement.querySelector('.shop_quantity');
    var current_quantity = parseInt(input_element.value);

    var shop_quantity = button.dataset.shop_quantity;
    var new_quantity;

    if (operation == 'increase') {
        new_quantity = current_quantity + 1;
    } else if (operation == 'decrease' && current_quantity > 1) {
        new_quantity = current_quantity - 1;
    } else {
        // 1 미만의 수량은 허용하지 않음
        alert("상품 수량은 1보다 작을 수 없습니다.");
        return;
    }

    // 서버로 업데이트된 수량 정보를 전송
    console.log(new_quantity);
    sendUpdateQuantityRequest(new_quantity, shop_p_num);
}

// 상품 수량 업데이트 요청을 보내는 함수
function sendUpdateQuantityRequest(new_quantity, shop_p_num) {
    var requestData = {
        shop_quantity: new_quantity,
        shop_p_num: shop_p_num
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

// 선택한 상품 삭제 함수
function deleteSelectedItems() {
    var selectedShopNumsInput = document.getElementById('selectedShopNums');

    if (selectedShopNums.length === 0) {
        alert("삭제할 상품을 선택해주세요.");
        return;
    }

    selectedShopNumsInput.value = selectedShopNums.join(',');

    // 선택한 상품들 삭제를 위한 서버 요청
    $.ajax({
        type: "POST",
        url: "/member/deleteSelectedItems",
        data: { selectedShopNums: selectedShopNums.join(',') },
        success: function(response) {
            console.log(response);
            location.reload();
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
   
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
        상품목록 

      <table id="productTable" class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
        	<th>선택</th>
            <th>삭제</th> 
            <th>상품 고유번호(히든)</th>
            <td>판매자</td>
            <th>상품 분류</th>
            <th>상품 내용</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>금액</th>
             <th>쿠폰</th>
             <th>판매상태</th> 
        </tr>
    </thead>
    <tbody>
    <form action="orderPageOne"  method="post"> 
        <c:forEach var="item" items="${shoppingCartList}">
            	<tr>
            	<td>
            	   <input type="checkbox" name="shop_p_num" value="${item.shop_p_num}"/>
       			 </td>
       			  <td>
       			  <a href="/delete?shop_p_num=${item.shop_p_num}&pd_p_num=${item.pd_p_num}&p_num=${item.p_num}">삭제</a>
                   
                </td>
                <td><c:out value="${item.shop_p_num}" />
               <%--  ${item.p_num}
                 --%></td>
                   <td><c:out value="${item.p_m_id}" /></td>
                <td><c:out value="${item.p_name}" /></td>
                <td><c:out value="${item.pd_p_desc}" /></td>
                <td><c:out value="${item.thumb}" /></td>
                <td>
                    <button type="button" class="quantity-up" data-shop_quantity="${item.shop_quantity}" onclick="update_click(this, 'increase','${item.shop_p_num}')">+</button>
                    <input type="hidden" class="shop_quantity" value="${item.shop_quantity}" />
                    <c:out value="${item.shop_quantity}" />
                    <button type="button" class="quantity-down" data-quantity="${item.shop_quantity}" onclick="update_click(this, 'decrease','${item.shop_p_num}')">-</button>
                </td>	
                			    
                <td>
                    <b>총액  </b>
                    <input type="hidden" name="p_price" value="${item.p_price}">
                    <c:out value="${item.p_price * item.shop_quantity}" />
                    <b> 원</b>
                      <c:set var="totalAmount" value="${totalAmount + (item.p_price * item.shop_quantity)}" />
                </td> 
                
               
                <td>
                <div>	
            
             	
                
						   <select name="cp_num" id="cList"  >
								    <option type="hidden" value="0">선택안함</option>
								    <c:forEach var="cp" items="${item.coupons}" varStatus="loop">
								         <c:if test="${ empty  item.coupons}">
								         <option value="0" hidden>
								         사용에 적합한  쿠폰이없습니다 
										</c:if> 
								         <c:if test="${ not empty item.coupons}">
								        <option value="${cp.cp_num}">쿠폰번호 : ${cp.cp_num}  쿠폰 가격 : ${cp.cp_price}  원 (쿠폰 만료일 : <fmt:formatDate value="${cp.exdate}" pattern="yyyy/MM/dd"/>)</option>
								        </c:if>
								    </c:forEach>
							</select>
						
								        
                </div>
                </td>
              
            </tr>
        </c:forEach>
       
       	<input name="totalAmount" value="${totalAmount}" type="hidden"/>
      			<input type="submit" value="주문" >
        
        <tr>
        	<td></td>
        	<td></td>
        	<td></td>
        	<td style="height: 10px;">
       </form> 	
        	
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
        	<td>
</td>

		<td><b>전체금액: </b></td>
        	<td> <c:out value="${totalAmount}" />
        		<input name=" totalAmount" value="${totalAmount}" type="hidden"/>
                        <b> 원</b>
                        
                        </td>
        </tr>
    </tbody>
    
</table>
			<table border="1">
					<tbody>
							<tr>
							<td>선택한 상품</td>
							<td>
			 
				</td>
									<td>
							    <input type="hidden" id="selectedShopNums" name="selectedShopNums" />
							    <a href="#" id="deleteSelectedBtn" onclick="deleteSelectedItems()">삭제</a>
							</td>
							</tr>	
					</tbody>
			</table>
			
			
			<table>
					<tbody>
					
						<tr>
							<td>
							</td>
							<td></td>
						</tr>	
					</tbody>
					
					
			</table>
    </div>
</div>
<%@ include file="../../footer.jsp" %>