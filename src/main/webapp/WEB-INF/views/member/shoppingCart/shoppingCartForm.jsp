<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script>
    function orderSelectedItems() {
    	  var selectedShopNums = getSelectedShopNums();

    	  // 선택된 상품이 없을 경우 알림 표시 및 이동 막기
    	  if (selectedShopNums.length === 0) {
    	    alert('선택된 상품이 없습니다.');
    	    return;
    	  }

    	  // 서버로 전송할 데이터
    	  var data = {
    	    selectedShopNums: selectedShopNums
    	  };

    	  // jQuery ajax 사용하여 서버로 요청 전송
    	  $.ajax({
    	    url: '/member/orderPageTwo',
    	    type: 'POST',
    	    data: JSON.stringify(data),
    	    contentType: 'application/json',
    	    success: function(response) {
    	      // 요청 성공 시 알림 표시 및 페이지 새로고침
    	      alert('주문이 완료되었습니다.');
    	      location.href = '/member/orderPageOne';
    	    //  location.reload();
    	    },
    	    error: function(xhr, status, error) {
    	      // 요청 실패 시 에러 메시지 표시
    	      alert('주문에 실패했습니다. 다시 시도해주세요.');
    	    }
    	  });
    	}

        // 주문하기 버튼 클릭 시 이벤트 추가
        $(document).ready(function () {
            $('#orderButton').click(function (event) {
                // 폼 전송 막기
                event.preventDefault();
                orderSelectedItems();
            });
        });

        function getSelectedShopNums() {
        	  // 실제로 선택된 상품의 shop_num을 가져오는 로직을 구현
        	  var selectedShopNums = [];
        	  var checkboxes = document.getElementsByName('selectedShopNums');

        	  for (var i = 0; i < checkboxes.length; i++) {
        	    if (checkboxes[i].checked) {
        	      selectedShopNums.push(checkboxes[i].value);
        	    }
        	  }

        	  return selectedShopNums;
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
                // 1 미만의 수량은 허용하지 않음
                alert("상품 수량은 1보다 작을 수 없습니다.");
                return;
            }

            // 서버로 업데이트된 수량 정보를 전송
            console.log(new_quantity);
            sendUpdateQuantityRequest(new_quantity, shop_num);
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

        // 선택한상품 삭제

        // 선택한 상품의 shop_num을 저장할 배열
        var selectedShopNums = [];

        // 체크박스를 클릭할 때 호출되는 함수
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

            console.log("현재 선택한 상품들: ", selectedShopNums);
        }

        function deleteSelectedItems() {
        	  var selectedShopNums = getSelectedShopNums();

        	  // 선택된 상품이 없을 경우 알림 표시 및 이동 막기
        	  if (selectedShopNums.length === 0) {
        	    alert('선택된 상품이 없습니다.');
        	    return;
        	  }

        	  // 서버로 전송할 데이터
        	  var data = {
        	    selectedShopNums: selectedShopNums
        	  };

        	  // jQuery ajax 사용하여 서버로 요청 전송
        	  $.ajax({
        	    url: '/member/deleteSelectedItems',
        	    type: 'POST',
        	    data: JSON.stringify(data),
        	    contentType: 'application/json',
        	    success: function(response) {
        	      // 요청 성공 시 페이지 새로고침
        	      location.reload();
        	    },
        	    error: function(xhr, status, error) {
        	      // 요청 실패 시 에러 메시지 표시
        	      alert('상품 삭제에 실패했습니다. 다시 시도해주세요.');
        	    }
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
        <button id="toggleButton" onclick="toggleTableVisibility()">숨기기</button>

        <table id="productTable" class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>선택</th>
                    <th>상품 내용</th>
                    <th>상품 분류</th>
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
                        <td>
                            <input type="checkbox" name="selectedShopNums" value="${item.shop_num}" 
                                   onclick="toggleSelectedShopNum(this, '${item.shop_num}')"/>
                        </td>
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
                            <c:out value="${item.p_price * item.shop_quantity}" />
                            <b> 원</b>
                            <c:set var="totalAmount" value="${totalAmount + (item.p_price * item.shop_quantity)}" />
                        </td> 
                        <td><c:out value="${item.p_m_id}" /></td>
                        <td>
                            <form action="delete" method="post" class="quantity_delete_form">
                                <input type="hidden" name="shop_num" value="${item.shop_num}" />
                                <input type="hidden" name="pd_p_num" value="${item.pd_p_num}" />
                                <input type="hidden" name="p_num" value="${item.p_num}" />
                                <button type="submit" class="delete_btn">삭제</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <form action="orderPageOne"  method="post"> 
                    <input type="submit" value="주문"  onclick="orderSelectedItems()" id="orderButton">
                </form>
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
                    <td>
                    </td>

                    <td><b>전체금액: </b></td>
                    <td> <c:out value="${totalAmount}" />
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
