<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<%
    response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
    response.setHeader("Cache-Control", "post-check=0, pre-check=0");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>  
<%
    List<Integer> numbers = (List<Integer>) request.getAttribute("numbers");
%>

<%-- 상품 총액을 계산하기 위한 변수 선언 --%>
<head>
<!--상품총금액 선언-->
<%-- <c:set var="order_p_price"  />
<c:set var="order_quantity"   />
<!--할인금액 선언-->
<c:set var="order_discount"  />
<!--쿠폰 번호 -->
<c:set var="order_cp_num"  /> 
  <!--최종결제금액 선언-->
<c:set var="order_totalprice"   />  
  <!-- 상품 총 수량  -->
    <c:set var="order_quantity"  /> --%>
   
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script >
    document.addEventListener("DOMContentLoaded", function() {
         selectedShopNums = getParameterByName('selectedShopNums');
         //selectedCoupon = getParameterByName('selectedCoupon');
        
        // 실제로 서버로 해당 shop_num을 전송하거나, 필요한 처리를 수행하는 로직을 구현
        console.log("Selected Shop Numbers: " + selectedShopNums);
        //console.log("Selected Shop Numbers: " + selectedCoupon);
    }); 

    function getParameterByName(name) {
        // URL에서 파라미터 값을 가져오는 함수
        // 출처: https://stackoverflow.com/a/901144/1001242
        var url = new URL(window.location.href);
        return url.searchParams.get(name);
    }
    
    
    
    window.onload = function() {
      var selectedAddressCell = document.getElementById('selectedAddressCell');
      var cListCell = document.getElementById('cListCell');

      // 현재 페이지의 URL 쿼리 문자열을 가져옵니다
      var queryString = window.location.search;

      // URL 쿼리 문자열이 있을 경우에만 처리
      if (queryString) {
        var params = new URLSearchParams(queryString);
        var selectedAddress = params.get('selectedAddress');
        var cList = params.get('cList');

        // 선택한 주소 값을 <td> 요소에 넣어줍니다
        if (selectedAddress) {
          selectedAddressCell.innerHTML = selectedAddress,
          cListCell.innerHTML = cList;
        }
      }
    };
    
 // 선택한 상품 주문 함수
    function submitForm() {
        // 여기에서 필요한 폼 데이터를 추가하거나 수정
        // 예: 선택한 상품 목록을 숨겨진 필드에 추가
        var selectedShopNums = getParameterByName('selectedShopNums');
      // var selectedCoupon = document.getElementById('cList').value;

        // 선택한 상품 목록을 숨겨진 필드에 추가
        document.getElementById('checkoutForm').insertAdjacentHTML('beforeend', '<input type="hidden" name="selectedShopNums" value="' + selectedShopNums + '">');

        // 선택한 쿠폰을 숨겨진 필드에 추가
     //   document.getElementById('checkoutForm').insertAdjacentHTML('beforeend', '<input type="hidden" name="selectedCoupon" value="' + selectedCoupon + '">');

        // 폼을 서버로 제출
        document.getElementById('checkoutForm').submit();
    }
    </script>

</head>
<body>
<div>
  

</div>

<b> 결제 페이지 </b>
    <table border="1">
        <thead  >
            <tr >
                <th>아이디</th>
                <th>성함</th>
                <th>생년월일</th>
                <th>이메일</th>
                <th>통신사</th>
                <th>휴대폰번호</th>
                <th>가입일</th>
                <th>등급</th>
                <th>배송받으실주소</th>
                
            </tr>
        </thead>
      <tbody>
            <tr>
                <td>${mdto.m_id}</td>
                <td>${mdto.m_name}</td>
                <td>${mdto.birth.substring(0, 10)}</td>
                <td>${mdto.email}</td>
                <td>${mdto.telep}</td>
                <td>${mdto.phone}</td>
                <td>${mdto.m_reg_date.substring(0, 10)}</td>
                <td>
                    <c:choose>
                        <c:when test="${mdto.m_status eq 1000}">탈퇴회원</c:when>
                        <c:when test="${mdto.m_status eq 1001}">일반회원</c:when>
                        <c:when test="${mdto.m_status eq 1002}">우수회원</c:when>
                        <c:when test="${mdto.m_status eq 1003}">단골회원</c:when>
                        <c:when test="${mdto.m_status eq 1050}">판매자 승인대기 중</c:when>
                        <c:when test="${mdto.m_status eq 2000}">일반회원 (전 판매자)</c:when>
                        <c:when test="${mdto.m_status eq 2001}">판매자</c:when>
                        <c:when test="${mdto.m_status eq 2002}">파워링크결제</c:when>
                        <c:when test="${mdto.m_status eq 2004}">상위노출 및 품목결제</c:when>
                        <c:when test="${mdto.m_status eq 7999}">운영자</c:when>
                    </c:choose>
                </td>
                 <td>${dto.selectedAddress}</td>
               <!-- 기존 코드... -->
      
    </tr>
                 
                   
            </tr>
        </tbody> 
        
    </table>				
      <table border="2">
    <thead>
        <tr>
            <th>상품 고유번호</th>
            <th>상품 이름</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>적용된 쿠폰</th>
            <th>금액</th>
        </tr>
    </thead>
    <tbody>
     	
        <div id="selectedItemsContainer"></div>

		<c:set var="totalquantity" value="0"/>    
         <c:forEach var="item" items="${cdto}">
      <td>    
      
    </td>
            	<tr>
                <td><c:out value="${item.p_num}"/>
                <%-- 고유번호 : ${item.p_num} --%>
                </td>
                <td><c:out value="${item.p_name}"/></td>
               	
                <td><c:out value="${item.thumb}"/></td>
                <td><c:out value="${item.shop_quantity}" /></td>
                  <td id="cListCell">
            <c:choose>
                <c:when test="${item.cp_num!='' && item.cp_num!=null}">
                    ${item.cp_price}
                </c:when>
                <c:otherwise>
                    쿠폰이 존재하지 않습니다.
                </c:otherwise>
            </c:choose>
        </td>
        <!-- 나머지 코드... -->
                
                <td>
                    <b>총액  </b>
                    <div>${item.p_price * item.shop_quantity} </div>
               
                    <b> 원</b>
                   			<!-- 총 수량 -->
				          	       <!-- 총 수량 누적 -->
				            <%-- <c:set var="order_quantity" value="${item.shop_quantity}" />
				        
                   			<!-- 상품 다 더한금액  -->
                     <c:set var="order_p_price" value="${dto.tot_price}" />
                   		<!-- 할인금액 쿠폰 +-->
                     <c:set var="order_discount" value="${cdto.cp_price}" />
                   <!--   쿠폰번호 변수 -->
                     <c:set var="order_cp_num" value="${cdto.cp_num}" />
						<!-- 선택된 상품들-->
                  
                   				<!-- 배송비,할인뺀금액 -->
                     <c:set var="order_totalprice" value="${dto_tot_price -cdto.cp_price+dto.selectedAddress}" />
             --%>
                </td>
            </tr>
        </c:forEach>
        	
        
			<tr>
				<td>
			 
				</td>
			</tr>	        		
            <thead>
    		</table>	
    		    <!-- 배송 메모 입력란 -->

    		<div> 배송비  : ${dto.order_dere_pay}</div>
    				   <b>총 상품 가격</b>
   				 <div><c:out value="${dto.tot_price}" /></div> 
   				 <div>적용 된 총할인 금액  :         ${dto.total_amount}</div>
   					<div> 최종 결제금액 :      ${dto.total_amount}</div>
   				   
   				   	<form action="/kakaopay/memready" method="post" id="checkoutForm" name="payform">    
   		 <button id="btn_kakao-pay" type="button">kakao</button>
   		 <input type="hidden" name="total_quantity" value="${dto.tot_price}">
   		 <input type="hidden" name="partner_order_id" value="${dto.shop_num}">
   		 <input type="hidden" name="partner_user_id" value="${mdto.m_id}">
   		 <input type="hidden" name="quantity" value="${dto.total_quantity}">
   		 <input type="hidden" name="total_amount" value="${dto.total_amount}">
   		 <input type="hidden" name="tax_free_amount" value="${dto.total_amount*90/100 }">
   		 <input type="hidden" name="vat_amount" value="${dto.total_amount*10/100 }">
		<br/>	   		 
    하고싶은말씀 있으시면 여기서하시죠 : <input type="text" name="order_memo" /><br/>
    
    
    
    	 
					
    <c:forEach var="item" items="${cdto}">
    	 <input type="hidden" name="arr_order_m_id" value="${item.shop_m_id}"/>
         <input type="hidden" name="arr_shop_num" value="${item.shop_num}"/>
         <input type="hidden" name="arr_order_cp_num" value="${item.cp_num}"/>
         <input type="hidden" name="arr_p_name" value="${item.p_name}"/>
     </c:forEach>
    <br/>
    
   
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    
    
</form> 

					
   				   
   				   
   		
							   
<%@ include file="../../footer.jsp" %>
</body>

<script>
	
	$("#btn_kakao-pay").click(function(){
	var formValues = $("form[name=payform]").serialize();
		$.ajax({
			type:'POST'
			,url:'/kakaopay/memready'
			,data : formValues
			,success:function(response){
				 alert(response.next_redirect_pc_url);
				location.href = response.next_redirect_pc_url;			
			}
		})
		// 버튼 클릭이벤트 해제
	});

</script>