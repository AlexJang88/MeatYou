<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
       <style>
        /* Custom styles go here */
        body {
            font-family: 'Roboto', sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .page-header {
            color: #2196F3;
        }
        .panel-default {
            border-color: #ddd;
        }
        .panel-heading {
            background-color: #2196F3;
            color: white;
        }
        .table th, .table td {
            text-align: center;
        }
        .btn-danger {
            background-color: #F44336;
            color: white;
        }
    </style>
    <script>
//선택주문 
// 선택한 상품 주문 함수
// 선택된 쿠폰을 저장하는 변수
//var selectedCoupon = "";
/* function orderSelectedItems() {
    var selectedShopNums = getSelectedShopNums(); // 선택된 상품의 shop_num을 가져오는 함수
    var selectedCoupon = $('#cList').val(); // select 태그에서 선택된 쿠폰 값을 가져오기
 //   on
    // 선택한 상품의 shop_num을 URL 파라미터로 세 번째 페이지로 전달
    window.location.href = "/member/orderPageTwo?check=1&selectedShopNums=" + selectedShopNums.join(',');
    //	+	"&selectedCoupon=" + selectedCoupon;;
} */

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
        // 1 미만의 수량은 허용하지 않음
        alert("상품 수량은 1보다 작을 수 없습니다.");
        return;
    }

    // 서버로 업데이트된 수량 정보를 전송
    console.log(new_quantity);
    sendUpdateQuantityRequest(new_quantity, shop_num);
}





// 상품 수량 업데이트 요청을 보내는 함수
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
//선택한상품 삭제

//선택한 상품의 shop_num을 저장할 배열
var selectedShopNums = [];

//체크박스를 클릭할 때 호출되는 함수
function toggleSelectedShopNum(checkbox, shop_num) {
 if (checkbox.checked) {
     // 체크된 경우 배열에 추가
     selectedShopNums.push(shop_num);
 } else {
     // 체크가 해제된 경우 배열에서 제거
     var index = selectedShopNums.indexOf(shop_num);
     if (index !== -1) {
         selectedShopNums.splice(index, 1);
     }
 }
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

<div class="container mt-5">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">장바구니</h1>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-heading"></div>

        <div class="panel-body">
        <!-- 수량 조절 폼 -->
        상품목록 
 <table class="table table-striped table-bordered table-hover mt-4">
    <thead>
        <tr>
        	<th>선택</th>
            <th>삭제</th> 
            <th>카트고유번호(히든)shop_num</th>
            <th>상품 고유번호(히든)shop_P_num</th>
            <td>판매자</td>
            <th>상품 분류</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>금액</th>
             <th>쿠폰</th>
             <th>판매상태</th> 
        </tr>
    </thead>
    <tbody>
    <form action="orderPageOne"  method="post"> 
        <c:forEach var="item" items="${cartdto}">
            	<tr>
            	<td>
            	   <%-- <input type="checkbox" name="shop_num" value="${item.shop_num}"/> --%>
            	      <input type="checkbox" name="arr_shop_num" value="${item.shop_num}" 
                   onclick="toggleSelectedShopNum(this, '${item.shop_num}')"/>
       			 </td>
       			  <td>
       			  <a href="/delete?shop_num=${item.shop_num}&pd_p_num=${item.pd_p_num}&p_num=${item.p_num}">삭제</a>
                   
                </td>
                <td><c:out value="${item.shop_num}" />
               <%--  ${item.p_num}
                 --%></td>
                <td><c:out value="${item.shop_p_num}" />
               <%--  ${item.p_num}
                 --%></td>
                   <td>${item.p_m_id}</td>
                <td>${item.p_name}</td>
                <td>${item.thumb}</td>
                <td>
                    <button type="button" class="quantity-up" data-shop_quantity="${item.shop_quantity}" onclick="update_click(this, 'increase','${item.shop_num}')">+</button>
                    <input type="hidden" class="arr_shop_quantity" value="${item.shop_quantity}" />
                    <c:out value="${item.shop_quantity}" />
                    <button type="button" class="quantity-down" data-quantity="${item.shop_quantity}" onclick="update_click(this, 'decrease','${item.shop_num}')">-</button>
                </td>	
                			    
                <td>
                    <b>총액  </b>
                    <input type="hidden" name="arr_p_price" value="${item.p_price}">
                    <c:out value="${item.p_price * item.shop_quantity}" />
                    <b> 원</b>
                      <c:set var="totalAmount" value="${totalAmount + (item.p_price * item.shop_quantity)}" />
                </td> 
                
               
                <td>
                <div>	
            
             	
                
						   <select name="arr_cp_num" id="cList"  >
						  
						     <c:if test="${  empty item.coupons}">
						      <option value="0" selected="selected">사용에 적합한  쿠폰이없습니다 </option>	        
						   </c:if>
						    <c:if test="${ not empty item.coupons}">
						    <option value="0">사용안함</option>
						    
						     <c:forEach var="cp" items="${item.coupons}" >
						   		  <option value="${cp.cp_num}">쿠폰번호 : ${cp.cp_num}  쿠폰 가격 : ${cp.cp_price}  원 </option>
						   	 </c:forEach>
						   </c:if>	
							</select>
						
								        
                </div>
                    <form action="delete" method="post" class="quantity_delete_form">
                        <input type="hidden" name="shop_p_num" value="${item.shop_p_num}" />
                        <input type="hidden" name="pd_p_num" value="${item.pd_p_num}" />
                        <input type="hidden" name="p_num" value="${item.p_num}" />
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
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


<c:if test="${count>0}">
	<c:if test="${startPage>10}">
		<a href="/member/shoppingCartForm?page=${startPage-10}">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="/member/shoppingCartForm?page=${i}">[${i}]</a>
	</c:forEach>
	<c:if test="${endPage<pageCount}">
		<a href="/member/shoppingCartForm?page=${startPage+10}">[다음]</a>
	</c:if>
</c:if>        	
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
		  <table class="table mt-4">
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