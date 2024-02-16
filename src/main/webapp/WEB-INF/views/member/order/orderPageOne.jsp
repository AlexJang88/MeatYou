<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

  
  
  //one.jsp 내의 script 태그 내부
  function submitForm() {
    // 선택한 주소 값을 가져옵니다
    var selectedAddress = document.getElementById('selectedAddress').value;

    // 이제 이 값을 사용하여 다음 작업을 수행할 수 있습니다
    // 예: AJAX를 사용하여 서버에 데이터 전송 또는 로컬 스토리지에 저장

    // 예시: 로컬 스토리지에 저장
    localStorage.setItem('selectedAddress', selectedAddress);

    // 여기서는 서버로의 전송은 하지 않았습니다. 만약 서버로의 전송이 필요하다면 AJAX 등을 사용하세요.

    // 페이지 이동 등의 추가 작업을 수행할 수 있습니다.
    // 예: window.location.href = 'yourNextPage.html';
  }
  
  
  function submitForm() {
    var "cList" = document.getElementById('"cList"').value;


    localStorage.setItem('"cList"', "cList");

  }
 
  
  
</script>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
</head>
<body>
<form action="orderPageTwo" method="post"  onsubmit="submitForm()">
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
              
                
                      		
                <!-- 
						      <th>쿠폰선택하십쇼</th>   -->
				
                           	
                <th>배송지 수정</th>
                
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
                <td>
 <select name="selectedAddress" id="selectedAddress">
            <c:forEach var="item" items="${AddrList}">
              <option value="${item}">${item}</option>
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
    </table>
    
   <c:forEach items="${dto.arr_shop_num}" var="sitem">
    	<input type="hidden" name="arr_shop_num" value="${sitem}" >
    </c:forEach>
    <c:forEach items="${dto.arr_cp_num}" var="citem">
    	<input type="hidden" name="arr_cp_num" value="${citem}" >
    </c:forEach>
    <c:forEach items="${dto.arr_p_name}" var="pitem">
    	<input type="hidden" name="arr_p_name" value="${pitem}" >
    </c:forEach>
     <c:forEach items="${dto.arr_shop_p_num}" var="pitem">
    	<input type="hidden" name="arr_shop_p_num" value="${pitem}" >
    </c:forEach>
         <c:forEach items="${dto.arr_shop_quantity}" var="pitem">
    	<input type="hidden" name="arr_shop_quantity" value="${pitem}" >
    </c:forEach>
         <c:forEach items="${dto.arr_p_price}" var="pitem">
    	<input type="hidden" name="arr_p_price" value="${pitem}" >
    </c:forEach>
    
    				
    <input type="submit"  value="확정 및 결제 ">
    
    	 
 
   
    
    
 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
 <input type="hidden" name="check" value="2" />
					</form>
<%@ include file="../../footer.jsp" %>
</body>