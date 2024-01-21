<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@ include file="../../header.jsp" %>
<style>
table  a{
	font-size: x-large;
}
</style>
<body>

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

<sec:authorize access="isAuthenticated()">



    <table border=1>
	    <thead>
	    
	       <tr>
	     		<th  colspan="3">
					     <c:if test="${dto.m_status eq 1000}">
							탈퇴하신분이 여긴 어인일로 ? 
						</c:if>
					<c:if test="${dto.m_status eq 1050}">
							판매자 승인 대기중입니다  
					</c:if>
				
				<c:if test="${dto.m_status eq 1001}">
						회원님 환영합니다 
				</c:if>
				
				<c:if test="${dto.m_status eq 1002}">
					우수회원님 환영합니다 
				</c:if>
				
				<c:if test="${dto.m_status eq 1003}">
						단골 회원님환영합니다 ~!
				</c:if>
				 
				<c:if test="${dto.m_status eq 2000}">
						판매자님 환영합니다 !
				</c:if>
				
				<c:if test="${dto.m_status eq 2001}">
						우수판매자님 환영합니다 !
				</c:if>
				
				<c:if test="${dto.m_status eq 2002}">
						최우수 판매자님 환영합니다 !
				</c:if>
				
				<c:if test="${dto.m_status eq 2003}">
						단골 판매자님 환영합니다 !
				</c:if>
				
				<c:if test="${dto.m_status eq 7999}">
				관리자님 환영합니다 
				</c:if>
				</th>
				 <th  colspan="8">
				 
			 
					 <c:if test="${dto.m_status eq 1050 }">
						 <a href="/member/sallerDelete" style="color:green;">판매자 신청 취소</a> 
							 <a href="../member/pickMe"  style="color:blue;">내가 마음에드는 업체</a>
							 	<a href="#" style="color:sky;">결제페이지</a>
							<a href="../member/pPickList" style="color:red;">찜 상품 목록</a>
					</c:if>
						<c:if test="${dto.m_status ge 2000 and dto.m_status le 2003}">
						 <a href="/member/sallerDelete" style="color:green;">판매자 취소</a> 
						 	<a href="../member/pickYou" style="color:blue;">나를 찜한사람들</a>
						 	<a href="#" style="color:sky;">유료상품 구매</a> 
					</c:if>
				<c:if test="${dto.m_status eq 1001 and dto.m_status le 1003}">
					  <a href="/member/deleteForm" style="color:green;">회원탈퇴</a> 
					<a href="#" style="color:blue;">내가 마음에드는 업체</a>
					<a href="#s" style="color:sky;">결제페이지</a>
					<a href="../member/pPickList" style="color:red;">찜 상품 목록</a>
					<a href="../member/shoppingCartForm" style="color:orange;">장바구니</a>
				</c:if>
				
					
					
					
				</th>
				</tr> 
				
				


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
         
         			<td>
         			<b>
         			<a href="../member/modifyForm">정보수정</a>
         			</b>
         			</td>
         			</tr>
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
				
	   <!-- 2000 ~ 2003 사이의 상태일 때 -->
   <c:if test="${dto.m_status ge 2000 and dto.m_status le 2003}">
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

 
</sec:authorize>


</body>

<%@ include file="../../footer.jsp" %>