<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>     
<%@ include file="../../header.jsp" %>
<form action="" tyepe="post">
	<table border="2"  align="center"> 
			<tr>
				<td  colspan="6">
				       반갑습니다 여기는  ${dto.m_name}님의 장바구니입니다  <br />
				</td>
			</tr>
	
			<tr>
				<td>
					번호
				</td>
				<td>
				상품이름 
				</td>
				<td>
					상품 설명
				</td>
				<td>
					상품 사진
				</td>
				<td>
					수량
				</td>
				<td>
					상품금액 
				</td>
				
			</tr>
			
			
			<tr>
				<td>
				${cdto['shop_num']}
				</td>
			   <%-- Null 또는 빈 문자열이 아닐 때에만 표시 --%>
    <c:if test="${not empty cdto['shoppingcart_' + dto.m_id]}">
        ${cdto['shoppingcart_' + dto.m_id]}
    </c:if>
			        <td>${cdto['c_id']}</td>
			        <td>${cdto['p_num']}</td>
			        <td>${cdto['quantity']}</td>
				
			</tr>
			
			
			
			
			
			
			
			<tr>
				<td>
				전체금액
				</td>
				
			
				<td  colspan="6" align="center"> 
				
				0000 원 
				</td>
				</tr>
				
				
				
				
				<tr> 
      <td colspan="6" align="center"> 
	       <input type="submit" name="modify" value="전체삭제" >
	       <input type="button" value="선택삭제" >      
	       <input type="button" name="modify" value="결제하기 "  onclick="javascript:window.location='/member/sallerInputForm'" style="background-color:orange; ">
      </td>
    </tr>
	</table>
</form>
<%@ include file="../../footer.jsp" %>