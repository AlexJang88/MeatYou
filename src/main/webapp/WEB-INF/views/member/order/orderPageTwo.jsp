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
    <!-- 추가적인 head 정보 추가 -->
</head>
<body>
    <form action="/path-to-server-handler" method="post" id="paymentForm">
        <b> 결제 페이지 </b>
        <table border="1">
            <!-- 사용자 정보 출력 -->
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
                <!-- 결제 페이지에서의 출력 수정 -->
                <c:forEach var="product" items="${selectedItems}">
                    <tr>
                        <td><c:out value="${product.p_name}" /></td>
                        <td><c:out value="${product.pd_p_desc}" /></td>
                        <td><c:out value="${product.thumb}" /></td>
                        <td><c:out value="${product.shop_quantity}" /></td>
                        <td><c:out value="${product.p_price * product.shop_quantity}" /></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <!-- 추가적인 결제 정보 입력 폼 등을 필요에 따라 구현 -->
        <button type="submit">결제</button>
    </form>
    <!-- 주문 확인 페이지로 이동 -->
    <form action="orderPageOne" method="post"> 
        <c:forEach var="product" items="${selectedItems}">
            <input type="hidden" name="selectedProducts" value="${product.shop_num}" />
        </c:forEach>
        <input type="submit" value="다시 주문 확인">
    </form>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <%@ include file="../../footer.jsp" %>
</body>
