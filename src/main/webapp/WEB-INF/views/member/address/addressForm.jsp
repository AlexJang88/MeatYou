<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
        $(document).ready(function () {
            var maxAddresses = 5;
            var currentAddressCount = 

            $('#addAddressBtn').click(function () {
                if (currentAddressCount < maxAddresses) {
                    // 새로운 주소 폼 생성
                    var newAddressForm = $('#addressFormTemplate').clone();
                    newAddressForm.attr('id', 'addressForm' + currentAddressCount);
                    newAddressForm.find('.addressNumber').text(currentAddressCount + 1);

                    // 새로운 폼을 컨테이너에 추가
                    $('#addressContainer').append(newAddressForm.show());
                    currentAddressCount++;

                    // 최대 제한에 도달하면 메시지 표시
                    if (currentAddressCount === maxAddresses) {
                        alert('최대 ' + maxAddresses + '개의 주소까지만 추가할 수 있습니다.');
                    }
                } else {
                    alert('최대 ' + maxAddresses + '개의 주소까지만 추가할 수 있습니다.');
                }
            });

            // 삭제 버튼 클릭 처리
            $(document).on('click', '.deleteAddressBtn', function () {
                $(this).closest('.addressForm').remove();
                currentAddressCount--;
            });
        });
    </script>
</head>

<body>
  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> 
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> 
나를 찜한 회원 </h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
나를 찜한 회원  
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>나를 찜한 회원</th>
            <th>내아이디(히든처리)</th>
            <th>회원이름</th>
            <th>회원이메일</th>
            <th>회원 전화번호</th>
            <th>회원등급</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${SallerPickMeList}">
            <tr>
                <td><c:out value="${item.pm_m_id}"  /></td>
                <td><c:out value="${item.pm_c_id}"/></td>
                <td><c:out value="${item.m_name}" /></td>
                <td><c:out value="${item.email}" /></td>
                <td><c:out value="${item.telep}" />-<c:out value="${item.phone}" /></td>
                    <form action="SallerdeleteHim" method="post" class="quantity_delete_form">
                        <input type="hidden" name="pm_num" value="${item.pm_num}" />
                        
                        <input type="hidden" name="pm_m_id" value="${item.pm_m_id}" />
                        
                        
                        
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
                </td>
              
            </tr>
        </c:forEach>
    </tbody>
</table>

 

    <!-- footer.jsp를 포함 -->
    <%@ include file="../../footer.jsp" %>
</body>
