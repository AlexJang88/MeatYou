<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<style>

button{
	background-color: white;
	border:none;
}
.main-container{
   display: flex;
        justify-content: center; /* Center the children horizontally */
        align-items: flex-start; /* Align the children at the top */
        margin-top: 40px;
        margin-bottom: 50px;
 }
 
       .summary-table {
       margin-top:3%;
            width: 65%;
            margin-bottom:10%;
            margin-left: auto;
            margin-right: auto;
            
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
        }   

  .summary-table th, .summary-table td {
    font-family: 'Arial', sans-serif;
    font-size: 12px;
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
} 
         
      .summary-table th {
            background-color: #f2f2f2;
        }

        .summary-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        #graph-container {
        /*     width: 60%;*/
            order: 1; 
                width: 60%;
    text-align: center; margin: auto;
    margin-bottom:5%;
    margin-top:2%;
        }
         #graph-container a{
               	cursor: pointer;
				color:gray;
				font-size: medium;
         }
        
 #graph-container {
        /*     width: 60%;*/
            order: 1; 
                width: 60%;
    text-align: center; margin: auto;
    margin-bottom:5%;
    margin-top:2%;
        }
         #graph-container a{
               	cursor: pointer;
				color:gray;
				font-size: medium;
         }


   select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        margin-right: 5px;
    }

    /* Style for submit button */
    input[type="button"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:white;
        color: black;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: gray;
    }
</style>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<script>
//선택주문 
// 선택한 상품 주문 함수
// 선택된 쿠폰을 저장하는 변수
//var selectedCoupon = "";
function orderSelectedItems() {
    var selectedShopNums = getSelectedShopNums(); // 선택된 상품의 shop_num을 가져오는 함수
    var selectedCoupon = getSelectedCoupons(); // select 태그에서 선택된 쿠폰 값을 가져오기
    console.log(selectedShopNums);
    console.log(selectedCoupon);
    // 선택한 상품의 shop_num을 URL 파라미터로 세 번째 페이지로 전달
    window.location.href = "/member/orderPageOne?selectedShopNums=" + selectedShopNums.join(',') +	"&selectedCoupon=" + selectedCoupon.join(',');
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
function getSelectedCoupons() {
    var selectedCoupons = [];
    var selectedShopNums = getSelectedShopNums(); // 이미 정의된 함수를 사용하여 선택된 상품 번호를 가져옵니다.

    selectedShopNums.forEach(function(shop_num) {
        var selectElement = document.getElementById("cp_num_" + shop_num); // 올바른 요소 선택 방법
        if (selectElement) {
            selectedCoupons.push(selectElement.value); // .value 속성을 사용하여 선택된 쿠폰 번호를 배열에 추가
        }
    });

    return selectedCoupons; // 상품 번호별 선택된 쿠폰 번호가 저장된 배열을 반환합니다.
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

// 선택한 상품 삭제 함수
function deleteSelectedItems() {
	var selectedShopNums = getSelectedShopNums();
	
    if (selectedShopNums.length === 0) {
        alert("삭제할 상품을 선택해주세요.");
        return;
    }
	console.log(selectedShopNums.join(','));
    

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
    <div class="col-lg-12"align="center"> 
        <h1 class="page-header""align="center"> >장바구니</h1>
    </div>
</div>

<div class="panel panel-default">
 

    <div class="main-container">
      

      <table id="productTable" class="summary-table">
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
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${shoppingCartList}">
            	<tr>
            	<td>
            	   <input type="checkbox" name="selectedShopNums" value="${item.shop_num}"/>
       			 </td>
       			  <td>
       			  <a href="/member/delete?shop_num=${item.shop_num}&pd_p_num=${item.pd_p_num}&p_num=${item.p_num}">삭제</a>
                   
                </td>
                <td><c:out value="${item.shop_num}" />
               <%--  ${item.p_num}
                 --%></td>
                   <td><c:out value="${item.p_m_id}" /></td>
                <td><c:out value="${item.p_name}" /></td>
                <td><c:out value="${item.pd_p_desc}" /></td>
                <td><c:out value="${item.thumb}" /></td>
                <td>
                    <button type="button" class="quantity-up" data-shop_quantity="${item.shop_quantity}" onclick="update_click(this, 'increase','${item.shop_num}')">+</button>
                    <input type="hidden" class="shop_quantity" value="${item.shop_quantity}" />
                    <c:out value="${item.shop_quantity}" />
                    <button type="button" class="quantity-down" data-quantity="${item.shop_quantity}" onclick="update_click(this, 'decrease','${item.shop_num}')">-</button>
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
							   <select id="cp_num_${item.shop_num}" class="coupon-select">
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
        <tr>
       
     
        
         
 


<tr> <td colspan="10"> 



      
 <b>전체금액 : </b> 
 <c:out value="${totalAmount}" />
 <input name=" totalAmount" value="${totalAmount}" type="hidden"/>
	   <b> 원</b><br/>

   
<div class="graph-container" style="height: 10px;">
	선택한 상품 : 
	
  	
       
       	<input name="totalAmount" value="${totalAmount}" type="hidden"/>
      			<input type="button" value="주문" onclick="orderSelectedItems()">
      			
      			
      			  <input type="hidden" id="selectedShopNums" name="selectedShopNums" />
							    <a href="#" id="deleteSelectedBtn" onclick="deleteSelectedItems()">삭제</a>
</div>

<br/>

	         
<br/>
	         
<br/>
	          
<div class="graph-container" style="height: 10px;">
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





</td></tr>
      
   
                        
    </tbody>
    
 
			
			 
			</table>
    </div>
</div>
<%@ include file="../../footer.jsp" %>