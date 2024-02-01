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
   <style>
     .addressBox {
    width: 25%;
    	cursor: pointer;
    	position: relative ;
  }
.box{
		 width: 23%;
}
 
  .addressBox:hover {
    width: 26%;
    cursor: pointer;
  }
   
   
   </style>
  <script>
  function submitForm() {
	  // 선택한 주소 값을 가져옵니다
	  var selectedAddress = document.getElementById('selectedAddress').value;

	  // AJAX를 사용하여 서버로 데이터 전송
	  $.ajax({
	    url: '/member/updateAddress',
	    type: 'POST',
	    data: {
	      selectedAddress: selectedAddress
	    },
	    success: function(response) {
	      // 서버에서 처리 후 페이지 이동
	      if (response.success) {
	        location.href = '/member/orderPageTwo';
	      } else {
	        alert('주소 업데이트에 실패했습니다.');
	      }
	    }
	  });
	}
</script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<form action="orderPageTwo" method="post" onsubmit="submitForm()">
<b>  잠깐~~!   개인정보 및 배송지 확인하고 가실게여~</b>
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
                <th>배송지 수정</th>
                
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
                <td>
 <select name="selectedAddress" id="selectedAddress">
            <c:forEach var="item" items="${AddrList}" varStatus="loop">
              <option value="${item.combined_address}">${item.combined_address}</option>
            </c:forEach>
          </select>
		
                </td>
                <td>
                <div  class="addressBox">
								 <a   href="../member/addressForm"  >
							<img src="/resources/member/img/address.png"   class="box" title="너님의 배송지 주소"  >
						</a>
						</div>
                </td>
            </tr>
        </tbody> 
    </table>				<input type="submit"  value="확정 및 결제 ">
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
<%@ include file="../../footer.jsp" %>
</body>
