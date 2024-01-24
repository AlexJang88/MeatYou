<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 등록</title>
</head>
<body>
    <a href="/customers/customer">홈으로</a>
    <h1>상품 등록 페이지</h1>	
    <center>
        <b>상품 등록하기</b><br> 
           
         //sajin  itemUpdateDetail
        <form method="post" name="productForm" action="/customers/sajin" enctype="multipart/form-data">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        	
        
            <table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
            
                <tr>
                    <td width="70" align="center">상품명</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="p_name" required="required" placeholder="판매할 제목을 적어주세요">
                    </td>
                </tr>
                
            	 <tr>
	                <td width="70" align="center">국내 or 수입</td>
	                <td width="330">
	                    <select id="originSelect" name="originer" required="required" onchange="updateCategory()">
	                        <option value="" disabled selected>선택하세요</option>
	                        <option value="100">국내산</option>
	                        <option value="200">수입산</option>
	                    </select>
	                </td>
	            </tr>

	            <tr>
	                <td width="70" align="center">가축 선택</td>
	                <td width="330">
	                    <select id="livestockSelect" name="livestock" required="required" onchange="updateCategory()">
	                   		 <option value="" disabled selected>선택하세요</option>
	                        <option value="10">돼지</option>
	                        <option value="20">소</option>
	                    </select>
	                </td>
	            </tr>
            
	            <tr>
	                <td width="70" align="center">부위 선택</td>
	                <td width="330">                      
	                    <select id="cutSelect" name="cut" required="required" onchange="updateCategoryOriginal()">
						    <option value="" disabled selected>선택하세요</option>
						</select>
	                </td>
	            </tr>
        
		       	 <input type="hidden" name="p_category" id="hiddenCategory">
                
                <tr>		      		      
			      <td width="70"  align="center">썸네일사진</td>
			      <td width="330" id="thumbs">
			        <input type="file" size="40" maxlength="30" name="thumbs"  placeholder="사진 나중에 추가할래"></td>
			   </tr>
			   
			    <tr>
                    <td width="70" align="center">품목 단위</td>
                    <td width="330">             
                        <select name="p_s_category" required="required">
                            <option value="0">단품</option>
                            <option value="1">세트</option>
                            <option value="2">선물세트</option>                     
                        </select>
                    </td>
                </tr>
			
                <tr>
                    <td width="70" align="center">가격</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="p_price" required="required" placeholder="숫자를 입력하세요"> 원
                    </td>
                </tr>
   
                <tr>
                    <td width="70" align="center">판매시작 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="startdate" placeholder="YYYY-MM-DD">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">판매종료 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="enddate"  placeholder="YYYY-MM-DD">
                    </td>
                </tr>
                
                <tr>
				   <td width="70" align="center"> 반품주소 </td>
				   <td width="330">			   
					   	<input type="text" size="40"  id="address" required="required" name="add_mem_address1" placeholder="주소">  
						<button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		   	
				    </td> 	
			   </tr> 
			   
			   
			   <tr>		      		      
			      <td width="70"  align="center">반품 상세주소</td>
			      <td width="330" id="add_mem_address2">
			        <input type="text" size="40" maxlength="30" name="add_mem_address2"  placeholder="반품 상세주소">
			      </td>
			   </tr>
                
              	
 
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="상품 상세등록 가기">
                        <input type="reset" value="다시 작성">
                        <input type="button" value="홈으로" onclick="window.location='/customers/customer'">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>

<script>
        function updateCategory() {
            var livestockSelect = document.getElementById("livestockSelect");
            var cutSelect = document.getElementById("cutSelect");

            // 초기화
            cutSelect.innerHTML = '<option value="0">기타부위</option>';

            // 선택된 <option> 엘리먼트의 값을 가져오기
            var livestockValue = parseInt(livestockSelect.options[livestockSelect.selectedIndex].value, 10);

            // 소를 선택한 경우
            if (livestockValue === 20) {
                // 새로운 부위 선택 셀렉트 박스 옵션 추가
                cutSelect.innerHTML += `
                	<option value="0">기타부위</option>
                    <option value="1">등심</option>
                    <option value="2">안심</option>
                    <option value="3">갈비</option>
                    <option value="4">채끝</option>
                    <option value="5">목심</option>
                    <option value="6">부채살</option>`;
            } else if (livestockValue === 10) {
                // 돼지를 선택한 경우
                cutSelect.innerHTML += `
                	<option value="0">기타부위</option>
                    <option value="1">삼겹살</option>
                    <option value="2">목살</option>
                    <option value="3">안심</option>
                    <option value="4">등심</option>
                    <option value="5">앞다리살</option>
                    <option value="6">갈매기살</option>`;
            }

            // 기존의 updateCategory 함수 호출
            updateCategoryOriginal();
        }

        function updateCategoryOriginal() {
            var originSelect = document.getElementById("originSelect");
            var livestockSelect = document.getElementById("livestockSelect");
            var cutSelect = document.getElementById("cutSelect");
            var hiddenCategory = document.getElementById("hiddenCategory");

            // 선택된 <option> 엘리먼트의 값을 가져오기
            var originValue = parseInt(originSelect.options[originSelect.selectedIndex].value, 10);
            var livestockValue = parseInt(livestockSelect.options[livestockSelect.selectedIndex].value, 10);
            var cutValue = parseInt(cutSelect.options[cutSelect.selectedIndex].value, 10);

            // P_CATEGORY 생성 (예: 국내 100, 가축 10,20 + 부위 0~1-> 111 
            var categoryValue = originValue + livestockValue + cutValue;

            // hidden input에 할당
            hiddenCategory.value = categoryValue;
        }
        
        //주소
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
        
        
    </script>

    



