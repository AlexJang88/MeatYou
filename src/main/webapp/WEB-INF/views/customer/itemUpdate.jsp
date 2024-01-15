<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
           
         
        <form method="post" name="productForm" action="/customers/itemUpdatePro" >
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
        

		        <!-- Hidden input으로 선택한 값을 전달할 변수 설정 -->
		       	 <input type="hidden" name="p_category" id="hiddenCategory">
                
                <tr>		      		      
			      <td width="70"  align="center">썸네일사진</td>
			      <td width="330" id="thumb">
			        <input type="text" size="40" maxlength="30" name="thumb"  placeholder="사진 나중에 추가할래"></td>
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
                    <td width="70" align="center">중량</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="weight" required="required" placeholder="숫자를 입력하세요">g(그람)
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">가격</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="p_price" required="required" placeholder="숫자를 입력하세요"> 원
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">원산지</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="origin" required="required" placeholder="원산지를 입력하세요">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">농장주소</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="local" required="required" placeholder="농장주소를 입력하세요">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">도축장</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="butchery" required="required" placeholder="도축장소를 입력하세요">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">이력(묶음)번호</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="serialNum" required="required" placeholder="숫자를 입력하세요">
                    </td>
                </tr>
                
                 <tr>
                    <td width="70" align="center">보관방법</td>
                    <td width="330">             
                        <select name="retain" required="required" >
                            <option value="냉장">냉장</option>
                            <option value="냉동">냉동</option>
                            <option value="실온">실온</option>                     
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">재고</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="stock" required="required" placeholder="재고를 입력하세요">
                    </td>
                </tr>
              
                <tr>
			      <td width="70" align="center" >상품설명</td>
			      <td width="330" >
			       <textarea name="pd_p_desc" rows="13" cols="40" required="required"></textarea> </td>
			    </tr>
                 
                <tr>
                    <td width="70" align="center">유통기한</td>
                    <td width="330">
                        <input type="text" size="40" maxlength="30" name="pd_duedate" required="required" placeholder="유통기한을 입력하세요" >
                    </td>
                </tr>
                 
                
                <tr>
                    <td width="70" align="center">판매시작 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="startdate" required="required" placeholder="YYYY-MM-DD">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">판매종료 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="enddate" required="required" placeholder="YYYY-MM-DD">
                    </td>
                </tr>
              	
 
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="상품 등록">
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
    </script>

