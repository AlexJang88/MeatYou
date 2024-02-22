<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<style>
 
 

.custom-table {
    width: 65%;
    margin: 20px auto;
    border-collapse: collapse;
    background-color: white;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }

  .custom-table th, .custom-table td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: center;
  }

  .custom-table th {
    background-color: #f2f2f2;
  }

  .custom-table .status-message {
    font-weight: bold;
    font-size: 16px;
  }

  .custom-table .action-links a {
    margin-right: 10px;
    text-decoration: none;
    font-weight: bold;
  }

  .custom-table .action-links a:hover {
    color: black;
    font-weight:  bold;
  }

  table {
    width: 60%;
    margin: 20px auto;
    border-collapse: collapse;
    background-color: white;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  }

  th, td {
    padding: 15px;
    border-bottom: 1px solid #dee2e6;
    text-align: left;
  }

  th {
    background-color: #f8f9fa;
    border-top: 2px solid #dee2e6;
  }

  table a {
    font-size: 16px;
    color: #blakc;
    text-decoration: none;
  }

  .addressBox {
    position: relative;
    width: 10%;
    cursor: pointer;
  }

  .addressBox:hover {
    width: 11%;
    cursor: pointer;
  }

  .status-message {
    font-weight: bold;
    font-size: 20px;
    color: #343a40;
  }

  .action-links {
    font-size: 18px;
  }
  
  
  
    .action-buttons {
    text-align: center;
    margin-top: 20px;
  }

  .action-buttons a {
    display: inline-block;
    padding: 10px 20px;
    margin: 0 10px;
    text-decoration: none;
    border-radius: 5px;
  }

   
</style>

<body>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

<sec:authorize access="isAuthenticated()">

     <div class="table-container">
<table border="1"  class="custom-table" style="width: 50%">
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
                      <td>
                        	<a href="/customers/CouponList"> 내가 발급한 쿠폰목록</a>
                      </td>
                      
                      </tr>
       
                    </c:when>
                    
                    
                    <c:when test="${dto.m_status eq 1001 and dto.m_status le 1003}">
                  		<tr>
                  		  <td>
                           <a href="/member/deleteForm" >회원탈퇴</a>
                        </td>
                        
                         <td>
                            <a href="/member/pickMe">내가 마음에드는 업체</a>
         					 </td>
                        
                          <td>
                         <a href="/member/PaymentHistory" >결제 내역</a>
                   </td>
                        
                        
                        
                         	  <td>
                         <a href="../member/pPickList" >찜 상품 목록</a>
                    		</td>
              
                    	
                    
                          </tr>	
                           
                       
                    </c:when>
                </c:choose> 
      		  </tr>
      		  
      	 
    </thead>
</table >

    <table border=1 class="custom-table" style="width: 50%">
	    <thead>
	     

	        	<tr>

	            <th>아이디</th>
	            <th>성함</th>
	     		 <th>생년월일</th>	    
	            <th style="width: 24%;">주소 </th>
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
                <td style="font-size: small;">${dto.m_name}</td>
               <td style="font-size: small;">${dto.birth.substring(0, 10)}</td>
                <td>${dto.m_addr1} 
               		${dto.m_addr2}  
						<a   href="../member/addressForm"  >
							<img src="/resources/member/img/address.png" class="addressBox" title="배송지 주소 변경"></a>
			 </td>
			 </td>
               
                <td style="font-size: small;">${dto.email}</td>
                <td>${dto.telep}</td>
                <td style="font-size: small;">${dto.phone}</td>
                <td style="font-size: small;">${dto.m_reg_date.substring(0, 10)}</td>
              <td style="font-size: small;">
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

	
     <c:if  test="${count > 0}">
    <c:if  test="${dto.m_status ge 1001 and dto.m_status le 1050}">
        <table border=2 class="custom-table">
          <thead>
         	 <tr>

                    <th>사업자 식별번호(제거대상) </th>
               		<th>쿠폰 번호</th>
                    <th>쿠폰 가격</th>
                    <th>만료일</th>
                    <th>발행일</th>
                    <th>사용일</th>
                </tr>
            </thead>
                
                <tbody>
                	 <c:forEach var="item" items="${cList}">
				<tr>
					<td>
					<c:out value="${item.cp_cus_num}"/>					
					</td>
					<td>
					<c:out value="${item.cp_num}"/>					
					</td>
					<td>
					<c:out value="${item.cp_price}"/>					
					</td>
				<td>
					<fmt:formatDate value="${item.exdate}" pattern="yyyy/MM/dd"/>
					</td>
					<td>
					<fmt:formatDate value="${item.publishdate}" pattern="yyyy/MM/dd"/>
					</td>
					<td>
					<fmt:formatDate value="${item.usedate}" pattern="yyyy/MM/dd"/>
					</td>
						</tr>
                		</c:forEach>
                </tbody>
                <tr>
                <td colspan="6" class="text-center">
    <div> 보유한 쿠폰 총  : <c:out value="${count}"/> 개 </div>
</td>
                </tr>
        </table>
   </c:if>
   </c:if>
   
    
   
				<div style="position:relative;">
	   <!-- 2000 ~ 2003 사이의 상태일 때 -->
   <c:if test="${dto.m_status ge 2000 and dto.m_status le 2004}">
        <table  border=2 class="custom-table">
        
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

 <div class="action-buttons">
  <a href="../member/modifyForm"  class="btn btn-primary"  >정보수정</a>
  
<c:if test="${dto.m_status ge 1001 and dto.m_status le 1004}">
    <a href="/member/sallerInputForm"  class="btn btn-primary"  >판매자 신청</a>
  </c:if>
</div>
 
</sec:authorize>
		
         		
</body>

<%@ include file="../../footer.jsp" %>