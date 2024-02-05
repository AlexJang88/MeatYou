<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../../header.jsp" %>
<style>
/* Reset some default table styles */
.custom-table {
  border-collapse: collapse;
  width: 40%;
  margin-top: 20px; /* Adjust as needed */
}

.custom-table th, .custom-table td {
  border: 1px solid #ddd;
  padding: 8px;
  text-align: center;
}

/* Customize your header styles */
.custom-table th {
  background-color: #f2f2f2; /* Light gray background */
}

/* Customize your status message styles */
.custom-table .status-message {
  font-weight: bold;
  font-size: 16px;
}

/* Customize your action link styles */
.custom-table .action-links a {
  margin-right: 10px; /* Adjust spacing between links as needed */
  text-decoration: none;
  font-weight: bold;
}

.custom-table .action-links a:hover {
  color: #333; /* Darken the color on hover */
}

  body {
    font-family: 'Roboto', sans-serif;
    background-color: #f8f9fa;
    margin: 0;
    padding: 0;
  }

  table {
    width: 60%; /* 테이블 넓이를 조절 */
    margin: 20px auto; /* 가운데 정렬 */
    border-collapse: collapse;
    background-color: white;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }

  th, td {
    padding: 15px; /* 셀 내부 간격을 늘림 */
    border-bottom: 1px solid #dee2e6;
    text-align: left;
  }

  th {
    background-color: #f8f9fa;
    border-top: 2px solid #dee2e6;
  }

  table a {
    font-size: 16px;
    color: #007bff;
    text-decoration: none;
  }

  .addressBox {
    width: 4.1%;
    position: absolute;
    margin-top: 0;
    margin-right: 0;
   right:280px;
    cursor: pointer;
    	top:250px;
  }

  .addressBox:hover {
    width: 4.3%;
    cursor: pointer;
  }

  .status-message {
    font-weight: bold;
    font-size: 20px;
    color: #343a40;
  }

  .action-links {
    color: #28a745;
    font-size: 18px;
  }
</style>

<body>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

<sec:authorize access="isAuthenticated()">

   
<table border="1"  class="custom-table">
    <thead>
        <tr>
            <th colspan="4" class="status-message">
                <c:choose>
                    <c:when test="${dto.m_status eq 1000}">
                        탈퇴하신 분이 여긴 어인일로?
                    </c:when>
                    <c:when test="${dto.m_status eq 1050}">
                        판매자 승인 대기 중입니다
                    </c:when>
                    <c:when test="${dto.m_status eq 1001}">
                        회원님 환영합니다
                    </c:when>
                    <c:when test="${dto.m_status eq 1002}">
                        우수회원님 환영합니다
                    </c:when>
                    <c:when test="${dto.m_status eq 1003}">
                        단골 회원님 환영합니다 ~!
                    </c:when>
                    <c:when test="${dto.m_status eq 2000}">
                        판매자님 환영합니다 !
                    </c:when>
                    <c:when test="${dto.m_status eq 2001}">
                        우수판매자님 환영합니다 !
                    </c:when>
                    <c:when test="${dto.m_status eq 2002}">
                        최우수 판매자님 환영합니다 !
                    </c:when>
                    <c:when test="${dto.m_status eq 2003}">
                        단골 판매자님 환영합니다 !
                    </c:when>
                    <c:when test="${dto.m_status eq 2004}">
                        VVIP 판매자님 환영합니다 !
                    </c:when>
                    <c:when test="${dto.m_status eq 7999}">
                        관리자님 환영합니다
                    </c:when>
                </c:choose>
               
            </th>
            </tr>
            <tr>
                <c:choose>
                    <c:when test="${dto.m_status eq 1050}">
                    <tr>
                    <td>
                     <a href="/member/sallerDelete" >판매자 신청 취소</a>
                     </td>
                     <td>
                      <a href="../member/pickMe" >내가 마음에드는 업체</a>
                      </td>
                     <td>
                     <a href="#" >결제 내역</a>
                   </td>
                   <td>
                         <a href="../member/pPickList">찜 상품 목록</a>
                      </td>
                    </tr>
                    </c:when>
                    <c:when test="${dto.m_status ge 2000 and dto.m_status le 2004}">
                    
                       <tr>
                      <td>
                      
                         <a href="/member/sallerDelete" >판매자 취소</a>
                      
                      </td>
               
                      
                        <td>
                      
                      
                        <a href="/customers/customer" >판매자 페이지 바로가기</a>
                      
                      </td>
                      </tr>
       
                    </c:when>
                    
                    
                    <c:when test="${dto.m_status eq 1001 and dto.m_status le 1003}">
                  		<tr>
                  		  <td>
                           <a href="/member/deleteForm" >회원탈퇴</a>
                        </td>
                        
                         <td>
                            <a href="/member/pickMe" s>내가 마음에드는 업체</a>
         					 </td>
                        
                          <td>
                         <a href="#" >결제 내역</a>
                   </td>
                        
                        
                        
                         	  <td>
                         <a href="../member/pPickList" >찜 상품 목록</a>
                    </td>
                    
                    <td>
                            <a href="../member/shoppingCartForm">장바구니</a>
                     </td>
                          </tr>	
                           
                       
                    </c:when>
                </c:choose> 
      		  </tr>
      		  
      		  
      		    <th colspan="4"  style="background-color:white; color:white; ">
      		         <c:if test="${dto.m_status ge 1000 and dto.m_status le 2004}">
						<div >
								<a   href="../member/addressForm"  >
							<img src="/resources/member/img/address.png" class="addressBox" title="너님의 배송지 주소">
						</a>
					
					</div>
					</c:if>
					     		  
      		  </th>
    </thead>
</table>

    <table border=1>
	    <thead>
	     

	        	<tr>

	            <th>아이디</th>
	            <th>성함</th>
	     		  <th>생년월일</th>	    
	            <th>주소 </th>
				<th>상세주소  </th>
				<th>이메일  </th>
				<th>통신사 </th>
				<th>휴대폰번호 </th>
				<th>가입일 </th>
				<th>등급 </th>
		 
			
				
		</tr>
	    </thead>
	    <tbody>
	  
            <tr>
                <td>${dto.m_id}</td>
                <td>${dto.m_name}</td>
                <td>${dto.birth.substring(0, 10)}</td>
                <td>${dto.m_addr1}</td>
                <td>${dto.m_addr2}</td>
                <td>${dto.email}</td>
                <td>${dto.telep}</td>
                <td>${dto.phone}</td>
                <td>${dto.m_reg_date.substring(0, 10)}</td>
                <td>
				<c:if test="${dto.m_status eq 1000}">
							탈퇴회원							
				</c:if>
				<c:if test="${dto.m_status eq 1001}">
							일반회원
				</c:if>
				<c:if test="${dto.m_status eq 1002}">
							우수회원
				</c:if>
				
				<c:if test="${dto.m_status eq 1003}">
							단골회원
				</c:if>
				
				<c:if test="${dto.m_status eq 1050}">
							판매자 승인대기 중 
				</c:if>
				
				<c:if test="${dto.m_status eq 2000}">
							 일반회원 (전 판매자)
				</c:if>
				
				<c:if test="${dto.m_status eq 2001}">
							판매자
				</c:if>
				
				<c:if test="${dto.m_status eq 2002}">
							파워링크결제
				</c:if>
				
				<c:if test="${dto.m_status eq 2004}">
							상위노출 및 품목결제
				</c:if>
				
				<c:if test="${dto.m_status eq 7999}">
							운영자
				</c:if>
		</td>
         
    </tbody>
</table>

 <!-- 1001 ~ 1050 사이의 상태일 때 -->
    <c:if  test="${dto.m_status ge 1001 and dto.m_status le 1050}">
        <table border=2>
            <tbody>
                <tr>
                    <td>일반인 쿠폰  몇개~</td>
                     <td>결제한상품어떤거~</td>
                </tr>
             
                <tr>
                    <td>아직 모르지~</td>
                       <td>몰라~</td>
                </tr>
                 
            </tbody>
        </table>
   </c:if>

   
   
   
   
   
   
   
				<div style="position:relative;">
	   <!-- 2000 ~ 2003 사이의 상태일 때 -->
   <c:if test="${dto.m_status ge 2000 and dto.m_status le 2004}">
        <table  border=2>
        
            <tbody>
            
                 <tr>
                    <td>판매자 클릭횟수</td>
                     <td>결제한상품어떤거~</td>
                </tr>
             
                <tr>
                    <td>품목횟수</td>
                       <td>몰라~</td>
                </tr>
                 
            </tbody>
        </table>
        
</c:if>

 </div>

    <table style="border-collapse: collapse; width: 30%; margin: auto;">
        <tbody>
            <tr>
                <th style="  color: white; padding: 15px; text-align: center;">
                    <div>
                        <b>
                            <a href="../member/modifyForm" style="color: gray; text-decoration: none;">정보수정</a>
                        </b>
                    </div>
                </th>
            </tr>
        </tbody>
    </table>

 
</sec:authorize>
		
         		
</body>

<%@ include file="../../footer.jsp" %>