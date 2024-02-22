<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

<%@ include file="../../header.jsp" %>
<head>
<style>



</style>
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
 
    <script>
    document.addEventListener('DOMContentLoaded', function () {
        for (var i = 0; i <= 4; i++) {
            document.getElementById('addressButton' + i).addEventListener('click', function (index) {
                return function () {
                    handleAddressButtonClick('address' + index);
                };
            }(i));
        }

        document.getElementById('addressButton10').addEventListener('click', function () {
            handleAddressButtonClick('address10');
        });
    });

    function handleAddressButtonClick(targetId) {
        new daum.Postcode({
            oncomplete: function (data) {
                var fullAddress = data.address;
                document.getElementById(targetId).value = fullAddress;
            }
        }).open();
    }
    
    
    
    function showAlert() {
        alert('배송지는 5개 까지 저장할수있습니다 !');
	    }
    function deletethat() {
        alert('1개의 주소가 삭제되었습니다 !');
	    }
    
    function updatethat() {
        var address1Input = document.getElementById('address${loop.index + 1}');
        var address2Input = document.getElementById('address2+${loop.index + 1}');
        var originalAddress1 = address1Input.defaultValue.trim();
        var currentAddress1 = address1Input.value.trim();
        var originalAddress2 = address2Input.defaultValue.trim();
        var currentAddress2 = address2Input.value.trim();

        if (
            (currentAddress1 === "" || currentAddress1 === originalAddress1) &&
            (currentAddress2 === "" || currentAddress2 === originalAddress2)
        ) {
            console.log("ssssssssssss");
            alert('주소를 입력하거나 수정해주세요.');
            return false; // 폼 제출 방지
        } else {
            alert('1개의 주소가 수정되었습니다');
        }

        // 유효성 검사 통과 시 폼 제출 허용
        return true;
    }
	    function validateAndSubmit() {
	        var address3Value = document.getElementById('address0').value.trim();
	     
	        if (address3Value === "") {
	            console.log("ssssssssssss");
	            alert('주소를 입력해주세요.');
	            return false; // Prevent form submission
	        }else{
	        	alert('1개의 주소가 추가되었습니다');
	        }
	        // Allow form submission if validation passes
	        return true;
	    }
     
	   
	  
    
    </script>
</head>

<body>
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"></h1>
        </div>
    </div>

    <div class="panel panel-default">
        <div class="panel-body">
        <table class="table table-striped table-bordered table-hover" style="width: 60%; margin: 0 auto; margin-top:30px;">
                <thead>
                    <tr>
                        <th>주소 검색</th>
                        <th>상세주소</th>
                        <th>수정</th>
                        <th>삭제</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${AddrList}" varStatus="loop">
                        <tr>
                            <td>
                                   <form action="updateAddr" method="post"  >
                                <div class="form-group">
                                    <label for="address"></label>
                                    <button type="button" id="addressButton${loop.index + 1}" class="btn btn-primary btn-block" 
                                    		style="	 background-color: transparent; /* 완전히 투명한 배경 */ border:none;">
                                        <input type="text" class="form-control" id="address${loop.index + 1}" name="add_mem_address1"  value="${item.add_mem_address1}">
                                        
                                    </button>
                                </div>
                            </td>
                            <td>
                                <label for="detailAddress"></label>
                                <div class="form-group">
                                    <input type="text" name="add_mem_address2" value="${item.add_mem_address2}"  id="address2+${loop.index + 1}" class="form-control">
                                </div>
                            </td>
                            <td>
                     <%-- 
                                    <input type="hidden" name="add_mem_address1" value="${item.add_mem_address1}" />
                                    <input type="hidden" name="add_mem_address2" value="${item.add_mem_address2}" /> --%>
                                    <input type="hidden" name="add_num" value="${item.add_num}" />
                                    <input type="hidden" name="add_m_id" value="${item.add_m_id}" />
                                    <input type="submit" value="수정" style="width: 60%; margin: 0 auto;" class="form-control" onclick="updatethat()" > 
                                </form>
                                
                                
                            </td>
                            <td>
                                <form action="deleteAddr" method="post" class="quantity_delete_form">
                                    <input type="hidden" name="add_num" value="${item.add_num}" />
                                    <input type="hidden" name="add_m_id" value="${item.add_m_id}" />
                                    <button type="submit" class="delete_btn  form-control" style="width: 60%; margin: 0 auto;" onclick="deletethat()">삭제</button>
                                </form>
                            </td>  
                            
                            
                        </tr>
                    </c:forEach>
                    
                   <tr><td colspan="4">
              <div class="insertAdress" style="width: 60%; margin: 0 auto;">
              <c:choose>
    <c:when test="${count< 5}">
        <form action="insertAddr" method="post"  onsubmit="return validateAndSubmit()">
            <div class="form-group" style="display: flex;">
                <button type="button" id="addressButton0" class="btn btn-primary btn-block" style="background-color: transparent;border:none; width: 50%;" >
                    <input type="text" class="form-control" id="address0" placeholder="주소검색" name="add_mem_address1" value="${item.add_mem_address1}"  style="width: 100%; ">
                      </button>
                <input type="text" name="add_mem_address2" placeholder="상세주소" value="${item.add_mem_address2}" class="form-control" style="width: 40%; margin-top:5px; margin-left:4px;">
            <input type="submit" value="추가하기"  class="form-control" style="width: 30%; margin: 0 auto;  margin-top:5px; margin-left:10px; " onsubmit="return validateAndSubmit()"> 
            </div>
		    </c:when>



<c:when test="${count >= 5}">
        <form action="insertAddr" method="post"  onsubmit="return validateAndSubmit()">
            <div class="form-group">
                <button type="hidden" id="addressButton0" class="btn btn-primary btn-block"  style="background-color: transparent; /* 완전히 투명한 배경 */
	 border:none;" >
                    <input type="hidden" class="form-control" id="address0" placeholder="주소검색" name="add_mem_address1" value="${item.add_mem_address1}" style="display:none;">
                </button>
            </div>
            <div class="form-group">
                <input type="hidden" name="add_mem_address2" placeholder="상세주소" value="${item.add_mem_address2}">
            </div>
            
  			<input type="button" value="배송지는 5개까지만 추가할수있습니다 "   onclick="showAlert()"    class="form-control"   style="width: 60%; margin: 0 auto; border:none ">
        </form>
		    </c:when>
         
</c:choose>
</div>
</td>
</tr>
                </tbody>
            </table>
                
        </div>
    </div>

    <!-- footer.jsp를 포함 -->
    <%@ include file="../../footer.jsp" %>
</body>
