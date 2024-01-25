<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<%@ include file="../../header.jsp" %>
<head>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

    <script>
    
    
    //주소검색 api
    
        document.addEventListener('DOMContentLoaded', function () {
      document.getElementById('addressButton').addEventListener('click', function () {
        new daum.Postcode({
          oncomplete: function (data) {
            // This function will be called when the user selects an address
            var fullAddress = data.address; // Full address with postcode
            document.getElementById('address').value = fullAddress; // Update the input field with the address
          }
        }).open();
      });
    });

    
    
    
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
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header"> 
  </h1>
    </div>
</div>

<div class="panel panel-default">

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
       <form action="updateAddr" method="post" onsubmit="submitForm();"> 
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>주소 검색</th>
            <th>상세주소</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${AddrList}">
            <tr>
                <td>   <label for="address"> </label>
				 <button type="button" id="addressButton" class="btn btn-primary btn-block"> 
				 <input type="text" class="form-control" id="address"  name="m_addr1" placeholder="주소" value="${item.add_mem_address1}">  
	 		 	</button>
	 		 </td>
                <td> 
	      <label for="detailAddress"> </label>
	       <div class="form-group">
	        <input type="text" class="form-control" name="m_addr2"    value="${item.add_mem_address2}">   
	     </div>
              </td>
                
                
                
                
               
            
                   <td>
                    <form action="deleteAddr" method="post" class="quantity_delete_form">
                    <c:out value="${item.add_m_id}"  />
                        <input type="hidden" name="add_num" value="${item.add_num}" />
                        <input type="hidden" name="add_m_id" value="${item.add_m_id}" />
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
               	 </td>            
            </tr>
        </c:forEach>
        
         <input type="submit" name="modify" value="수   정" >
    </tbody>
</table>
    </form>
</div></div>
        
    <!-- footer.jsp를 포함 -->
    <%@ include file="../../footer.jsp" %>
</body>
