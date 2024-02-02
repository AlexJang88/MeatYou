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
<head>
 
   
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


<form>
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
                 <td id="cListCell">${cdto.cp_price}</td>
                  
                  
                
              	<%-- 	   ${cList.cp_price}
 				<%-- 	<input type="text" name="order_cp_num" value="${cList.cp_num}"/> --%>
 			<%-- 		폰번호 : ${item.cp_num}  쿠폰 가격 : ${item.cp_price} --%>
            </tr>
        </tbody> 
        
    </table>				
      <table>
    <thead>
        <tr>
            <th>상품 내용</th>
            <th>상품 분류</th>
            <th>상품 사진</th>
            <th>상품 수량</th>
            <th>금액</th>
        </tr>
    </thead>
    <tbody>
     		 <form action="/path-to-server-handler" method="post" id="checkoutForm">
        여기에 선택한 상품을 출력하는 영역
        <div id="selectedItemsContainer"></div>
<!-- 
     
        <button type="button" onclick="submitForm()"> 결제 하기</button> -->
    </form>  
    
    
         <c:forEach var="item" items="${shopList}">
            	<tr>
                <td><c:out value="${item.p_name}"/></td>
                <td><c:out value="${item.pd_p_desc}" /></td>
                <td><c:out value="${item.thumb}"/></td>
                <td><c:out value="${item.shop_quantity}" /></td>
                <td>
                    <b>총액  </b>
                    <div>${item.p_price * item.shop_quantity} </div>
                    <c:out value="" />
                    <b> 원</b>
                </td>
            </tr>
        </c:forEach>
			<tr>
				<td>
					<b>총액 ${totalprice} 원</b>
					<input type="hidden" name="order_TOTALPRICE" value=" ${totalprice}">
				</td>
			</tr>	        		
            <thead>
    		</table>
   					
    					<input type="submit"  value="결제하기">
					</form>
							  			 <form action="orderPageOne"  method="post"> 
      								<input type="submit" value="다시확인하기">
     							  		 </form>
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<%@ include file="../../footer.jsp" %>
</body>