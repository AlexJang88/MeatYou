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
<c:set var="order_p_price"  />
<c:set var="order_quantity"   />
<!--할인금액 선언-->
<c:set var="order_discount"  />
<!--쿠폰 번호 -->
<c:set var="order_cp_num"  /> 
  <!--최종결제금액 선언-->
<c:set var="order_totalprice"   />  
  <!-- 상품 총 수량  -->
    <c:set var="order_quantity"  />
   
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script >
    document.addEventListener("DOMContentLoaded", function() {
        var selectedShopNums = getParameterByName('selectedShopNums');
        
        // 실제로 서버로 해당 shop_num을 전송하거나, 필요한 처리를 수행하는 로직을 구현
        console.log("Selected Shop Numbers: " + selectedShopNums);
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
    
    function submitForm() {
        // 여기에서 필요한 폼 데이터를 추가하거나 수정
        // 예: 선택한 상품 목록을 숨겨진 필드에 추가
        var selectedShopNums = getParameterByName('selectedShopNums');
        document.getElementById('checkoutForm').insertAdjacentHTML('beforeend', '<input type="hidden" name="selectedShopNums" value="' + selectedShopNums.join(',') + '">');

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
                <th>쿠폰가격</th>
                
            </tr>
        </thead>
      <tbody>
            <tr>
                <td>${dto.m_id}</td>
                <td>${dto.m_name}</td>
                <td>${dto.birth.substring(0, 10)}</td>
                <td>${dto.email}</td>
                <td>${dto.telep}</td>
                <td>${dto.phone}</td>
                <td>${dto.m_reg_date.substring(0, 10)}</td>
                <td>
                    <c:choose>
                        <c:when test="${dto.m_status eq 1000}">탈퇴회원</c:when>
                        <c:when test="${dto.m_status eq 1001}">일반회원</c:when>
                        <c:when test="${dto.m_status eq 1002}">우수회원</c:when>
                        <c:when test="${dto.m_status eq 1003}">단골회원</c:when>
                        <c:when test="${dto.m_status eq 1050}">판매자 승인대기 중</c:when>
                        <c:when test="${dto.m_status eq 2000}">일반회원 (전 판매자)</c:when>
                        <c:when test="${dto.m_status eq 2001}">판매자</c:when>
                        <c:when test="${dto.m_status eq 2002}">파워링크결제</c:when>
                        <c:when test="${dto.m_status eq 2004}">상위노출 및 품목결제</c:when>
                        <c:when test="${dto.m_status eq 7999}">운영자</c:when>
                    </c:choose>
                </td>
                 <td id="selectedAddressCell">${param.selectedAddress}</td>
               <!-- 기존 코드... -->
        <td id="cListCell">
            <c:choose>
                <c:when test="${not empty cdto}">
                    ${cdto.cp_price}
                </c:when>
                <c:otherwise>
                    쿠폰이 존재하지 않습니다.
                </c:otherwise>
            </c:choose>
        </td>
        <!-- 나머지 코드... -->
    </tr>
                 
                   
            </tr>
        </tbody> 
        
    </table>				
      <table border="2">
    <thead>
        <tr>
            <th>상품 고유번호</th>
            <th>상품 내용</th>
            <th>상품 분류</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>금액</th>
        </tr>
    </thead>
    <tbody>
     	
        <div id="selectedItemsContainer"></div>

    
         <c:forEach var="item" items="${shopList}">
      <td>    
      
    </td>
            	<tr>
                <td><c:out value="${item.shop_p_num}"/>
                <%-- 고유번호 : ${item.p_num} --%>
                </td>
                <td><c:out value="${item.p_name}"/></td>
               	
                <td><c:out value="${item.pd_p_desc}" /></td>
                <td><c:out value="${item.thumb}"/></td>
                <td><c:out value="${item.shop_quantity}" /></td>
                <td>
                    <b>총액  </b>
                    <div>${item.p_price * item.shop_quantity} </div>
               
                    <b> 원</b>
                   			<!-- 총 수량 -->
				          	       <!-- 총 수량 누적 -->
				            <c:set var="order_quantity" value="${order_quantity + item.shop_quantity}" />
                   			<!-- 상품 다 더한금액  -->
                     <c:set var="order_p_price" value="${order_p_price + (item.p_price * item.shop_quantity)}" />
                   		<!-- 할인금액 쿠폰 +-->
                     <c:set var="order_discount" value="${cdto.cp_price}" />
                   <!--   쿠폰번호 변수 -->
                     <c:set var="order_cp_num" value="${cdto.cp_num}" />
						<!-- 선택된 상품들-->
                  
                   				<!-- 배송비,할인뺀금액 -->
                     <c:set var="order_totalprice" value="${order_p_price -cdto.cp_price+order_dere_pay}" />
            
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

    		<div> 배송비  : ${order_dere_pay}</div>
    				   <b>총 상품 가격</b>
   				 <div><c:out value="${order_p_price}" /></div> 
   				 <div>적용 된 총할인 금액  :         ${order_discount}</div>
   					<div> 최종 결제금액 :      ${order_totalprice}</div>
   				   
   				   	<form action="showMeTheMoney" method="post" id="checkoutForm">    
    <input type="submit" value="결제하기"><br/>
    하고싶은말씀 있으시면 여기서하시죠 : <input type="text" name="order_memo" value="${order_memo}"/><br/>
    
    <c:forEach var="item" items="${shopList}">
		    
        <c:if test="${not empty cdto}">
            <input type="text" name="order_cp_num" value="${order_cp_num}"/>
            <input type="text" name="order_discount" value="${order_discount}"/>
            <!-- 쿠폰이 있는 경우에만 해당 데이터를 전송 -->
        </c:if>
         <c:if test="${empty cdto}">
            <input type="text" name="order_discount" value="0"/>
            <input type="text" name="order_cp_num" value="0"/>
            <!-- 쿠폰이비어있는경우 데이터를 전송 -->
        </c:if>
    </c:forEach>
    
    
    	 
					
    <c:forEach var="item" items="${shopList}">
        <input type="hidden" name="order_m_id" value="${item.shop_m_id}"/><br/>
    </c:forEach>
    
    <c:forEach var="number" items="${numbers}">
        <input type="hidden" name="order_p_num" value="${number}"/><br/>
    </c:forEach>
    
    <input type="hidden" name="order_p_price" value="${order_p_price}"/> <br/>
    <input type="hidden" name="order_dere_pay" value="${order_dere_pay}"/><br/>
    <input type="hidden" name="order_addr" value="${param.selectedAddress}"/><br/>
    <input type="hidden" name="order_quantity" value="${order_quantity}"/><br/>
    
    <c:if test="${not empty cdto}">
        <input type="hidden" name="order_discount" value="${order_discount}"/><br/>
    </c:if>

    <input type="hidden" name="order_totalprice" value="${order_totalprice}"/><br/>
    
    <c:forEach var="item" items="${shopList}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </c:forEach>
</form>

					
   				   
   				   
   		
							   
<%@ include file="../../footer.jsp" %>
</body>