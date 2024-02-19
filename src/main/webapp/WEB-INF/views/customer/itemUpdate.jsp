<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/resources/customers/itemUpdate.css"> <!-- 외부 스타일시트 추가 -->	
 <script>
    $(document).ready(function () {
        // Add a click event handler for the menu items with sub-menus
        $('.vertical-menu-item a').click(function (event) {
            // Prevent the default behavior of the link

            // Toggle the collapse state when the menu item is clicked
            $(this).next('.collapse').collapse('toggle');
        });
    });
    
    
    
 // 스크롤 이벤트 핸들러 함수
    function preventScroll(event) {
      event.preventDefault();
    }

    // 스크롤 이벤트에 preventScroll 함수 연결
    window.addEventListener('scroll', preventScroll, { passive: false });
</script>
<!-- include summernote css/js -->
    <!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>   
     <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
  <%@ include file="../header.jsp" %>
     
     <style>
     header {
    color: black !important;
    
}
     a{color:black;}
   /* 기존 
    .styled-box {
             justify-content: center;
                  margin: 0 5px;
      align-items: center;
      justify-content: center;
    width:60%;
      text-align: center;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
        */
       #pageContent {
        /*     width: 60%;*/
            order: 1; 
                width: 60%;
    text-align: center; margin: auto;
    margin-bottom:5%;
    margin-top:2%;
        }
         #pageContent a{
               	cursor: pointer;
				color:gray;
				font-size: medium;
         }
        
            
    
    /*복붙할것들 */
    /*전체 규격  시작*/
  body {
            font-family: 'Roboto', Arial, sans-serif;
            margin: 0;
        }
        /*전체 규격 끝*/
/* 헤더  */
  .out-table {
            margin: 0 auto;
            align-items: center;
            justify-content: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            
              font-family: 'Arial', sans-serif; /* Change the font family as needed */
    font-size: 24px; /* You can adjust the font size as needed */
    color: #333; /* Change the color as needed */
    font-weight: bold;
}    
/*헤더끝*/
/* 카테고리 시작*/
#bigfont {
    font-weight: bold;
    font-size: 14px; /* You can adjust the font size as needed */
    font-family: 'Poppins', sans-serif; /* Change the font family as needed for bigfont */
    border: 1px solid #ddd;
          width: 140px;
      border-right:none;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         margin-bottom:0;
      
}

/* Add this style for the smallfont element */
#smallfont {
    font-size: 12px; /* You can adjust the font size as needed */
    font-family: 'Quicksand', sans-serif; /* Change the font family as needed for smallfont */
}
 .vertical-menu {
            order: -1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-right: 20px;
            width: 100%;
            margin-top: 0;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
          
        }

        .vertical-menu a {
            margin-bottom: 10px;
            text-align: left;
        }


        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
 .vertical-menu a.btn {
    font-size: 14px; /* You can adjust the font size as needed */
}

/* Add this style for the lower-level menu items (sub-menu items) */
.vertical-menu a.btn + .collapse a.btn {
    font-weight: normal;
    font-size: 14px; /* You can adjust the font size as needed */
}
   .category-menu {
            width: 8%; /* Adjust the width as needed */
            height: 100%; /* Adjust the height as needed */
            position: relative;
            margin-right:0;
            left:25%;
        }

/* 카테고리 끝*/
/* 메인  시작*/
 .main-container{
   display: flex;
        justify-content: center; /* Center the children horizontally */
        align-items: flex-start; /* Align the children at the top */
        margin-top: 40px;
        margin-bottom: 50px;
 }
       .summary-table {
       margin-top:1%;
            width: 100%;
            margin-bottom:10%;
            margin-left: auto;
            margin-right: auto;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
        }   
  .summary-table th, .summary-table td {
    font-family: 'Arial', sans-serif;
    font-size: 12px;
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
} 
.summary-table th {
            background-color: #f2f2f2;
        }
        .summary-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

 .graph-and-summary {
               width: 100%;
        margin: 0 auto; /* Center the element horizontally */
        text-align: center; /* Center the content inside the element */
 		margin-left:auto;
 		margin-right:auto;
 		    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
           
		position: relative;
        }




     .main-table {
            width: 130%;
            margin: 0 auto;
            margin-left:25%;
            height: 50%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
           
            
        }
        
        .main-table th {
    font-family: 'Arial', sans-serif;
    font-size: 16px;
    font-weight: bold;
    background-color: #f2f2f2;
    padding: 10px;
    text-align: center;
}
.main-table td {
    font-family: 'Arial', sans-serif;
    font-size: 14px;
    padding: 10px;
    text-align: center;
}
.inputsix{margin : 0 auto;
	width: 60%; 
	    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
                
       select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 12px;
        margin-right: 5px;
     /*    height: 30px; */
        border:none;
    }

    
 .final{
 margin-top:-8%;
 	width: 95%;
 	   text-align: center; /* 텍스트를 가운데 정렬 */
 }
 
 #addressButton2{
 height:43px;
 width: 70%;
 background-color: white;
   border:none;
 }
 
 #addressButton3{
 height:43px;
 width: 70%;
  background-color: white;
  border:none;
 
 }
 
 #addressButton{
 height:40%;
 width: 70%;
  background-color: white;
    border:none;
 }
 
  
 #address2{
 height:100%;
 width: 100%;
 }
 
 #address3{
 height:100%;
 width: 100%;
 }
 
 #address{
 height:40%;
 width: 100%;
 }
    
  </style>
  <html>
  <body>
  
  <div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2  class="out-table">
    상품 등록
  	</h2>
		</div>
 
  <div class="main-container" >
			    <div class="category-menu">
			        <!-- Your category menu content -->
					        <div class="vertical-menu">
							            <div class="vertical-menu-item">
							                <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">상품</a>
							                <div id="mem" class="collapse">
							           		       <a href="/customers/itemUpdate" class="btn" id="smallfont" >상품 등록</a><br/>
			                                            <a href="/customers/itemList" class="btn"id="smallfont" >상품 목록</a>
							                </div>
							            </div>
					
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">매출</a>
								                <div id="pro" class="collapse">
								        <a href="/customers/profit" class="btn" id="smallfont" >매출현황</a><br/>
                                       <a href="/customers/profitItem"class="btn"id="smallfont" >월별 판매 현황</a>
                                   
								                </div>
								            </div>
								
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">재고</a>
								                <div id="money" class="collapse">
								                    <a href="/customers/stock"  class="btn" id="smallfont" >재고현황</a><br/>
                                       <a href="/customers/onStock" class="btn"id="smallfont" >월별 판매 현황</a>
								                </div>
								            </div>
					
										      
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">회원 관리</a>
								                <div id="check" class="collapse">
								         <a href="/customers/consumerList" class="btn" id="smallfont" >구매회원</a><br/>
                                <a href="/customers/CouponList" class="btn"  id="smallfont" >쿠폰 보유 회원</a>
								                </div>
								            </div>
											
					
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#pay" id="bigfont">결제</a>
								                <div id="pay" class="collapse">
								             
                              <a href="/customers/pay" class="btn"id="smallfont" >유료결제</a><br/>
                                <a href="/customers/powerlink" class="btn"id="smallfont" >파워링크 결제</a><br/>
                                <a href="/customers/itemplus" class="btn"id="smallfont" >품목 확장 결제</a> 
                           		                </div>
								            </div>
								    
								            
								            <div class="vertical-menu-item">
								                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#go" id="bigfont">주문|배송</a>
								                <div id="go" class="collapse">
								             
                              <a href="/customers/delivering" class="btn"id="smallfont" >주문접수 및 배송현황</a><br/>
                                <a href="/customers/deliverout" class="btn"id="smallfont" > 주문 취소 </a><br/>
                           		                </div>
								            </div> 
								              <div class="vertical-menu-item">
		                                    <a  href="#" class="btn" data-toggle="collapse" data-target="#sellerQna"id="bigfont">관리자</a>
		                                    <div id="sellerQna" class="collapse">
		                                <a href="/board/sellerQna" class="btn"id="smallfont" >관리자 문의게시판</a><br/>
		                                </div>
                                    </div>
					        </div>
			    </div>
      <table class="main-table"  >
            <td class="graph-and-summary">
						 		 	 <table class="summary-table" >
						 		 	  
								    	<tr>
								    		    <form method="post" name="productForm" action="/customers/productReg" enctype="multipart/form-data">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     
            <table  border="1" cellspacing="0" cellpadding="0" align="center" class="final">           
                <tr>
                    <td width="70" align="center">상품명</td> 
                    <td  >
                        <input type="text"  class="inputsix" size="40" maxlength="50" name="p_name" required="required" placeholder="판매할 제목을 적어주세요">
                    </td>
                </tr>
                
            	 <tr>
	                <td width="70" align="center">국내 or 수입</td>
	                <td  >
	                    <select id="originSelect" name="originer" required="required"  class="inputsix"onchange="updateCategory()">
	                        <option value="" disabled selected>선택하세요</option>
	                        <option value="100">국내산</option>
	                        <option value="200">수입산</option>
	                    </select>
	                </td>
	            </tr>

	            <tr>
	                <td width="70" align="center">가축 선택</td>
	                <td  >
	                    <select id="livestockSelect" name="livestock" required="required"  class="inputsix"  onchange="updateCategory()">
	                   		 <option value="" disabled selected>선택하세요</option>
	                        <option value="10">돼지</option>
	                        <option value="20">소</option>
	                    </select>
	                </td>
	            </tr>
            
	            <tr>
	                <td width="70" align="center">부위 선택</td>
	                <td >                      
	                    <select id="cutSelect" name="cut" required="required" class="inputsix"  onchange="updateCategoryOriginal()">
						    <option value="" disabled selected>선택하세요</option>
						</select>
	                </td>
	            </tr>
        
		       	 <input type="hidden" name="p_category" id="hiddenCategory">
                
                <tr>		      		      
			      <td width="70"  align="center">썸네일사진</td>
			      <td  id="thumbs">
			        <input type="file" size="40" maxlength="30" name="thumbs"  class="inputsix"   required="required" placeholder="사진 나중에 추가할래"></td>
			   </tr>
			   
			
			   
			    <tr>
                    <td width="70" align="center">품목 단위</td>
                    <td  >             
                        <select name="p_s_category" required="required"  class="inputsix" >
                            <option value="0">단품</option>
                            <option value="1">세트</option>
                            <option value="2">선물세트</option>                     
                        </select>
                    </td>
                </tr>
			
                <tr>
                    <td width="70" align="center">가격</td>
                    <td  >
                        <input type="number"   class="inputsix" size="40" maxlength="30" name="p_price"  class="inputsix"  required="required" placeholder="숫자를 입력하세요"> 원
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">원산지</td> 
                    <td >
                        <input type="text"   class="inputsix" size="40" maxlength="50" name="origin" required="required" placeholder="원산지를 입력하세요">
                    </td>
                </tr>
                
   				<tr>
                    <td width="70" align="center">중량</td>
                    <td width="330">
                        <input type="number" class="inputsix"   size="40" maxlength="30" name="weight" required="required" placeholder="숫자를 입력하세요">g(그람)
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">농장주소</td> 
                    <td width="330">
                           <button type="button" id="addressButton2" class="btn btn-primary btn-block">
                        <input type="text "  class="inputsix"  size="40" maxlength="50" id="address2"  name="local" required="required" placeholder="농장주소를 입력하세요">
             	</button> 
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">도축장</td> 
                    <td width="330">
                         <button type="button"  id="addressButton3" class="btn btn-primary btn-block">

                        <input type="text"  class="inputsix"  size="40" maxlength="50" id="address3" name="butchery" required="required" placeholder="도축장소를 입력하세요">
                   </button>
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">이력(묶음)번호</td>
                    <td width="330">
                        <input type="number"  class="inputsix"  size="40" maxlength="30" name="serialNum" required="required" placeholder="숫자를 입력하세요">
                    </td>
                </tr>
                
                 <tr>
                    <td width="70" align="center">보관방법</td>
                    <td width="330">             
                        <select name="retain" required="required"  class="inputsix"  >
                            <option value="냉장">냉장</option>
                            <option value="냉동">냉동</option>
                            <option value="실온">실온</option>                     
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">재고</td>
                    <td >
                        <input type="number" size="40" maxlength="30" name="stock"   class="inputsix"  required="required" placeholder="재고를 입력하세요">
                    </td>
                </tr>
              
                <tr>
                <td></td>
                
			      <td  >
			       <textarea id="summernote" name="pd_p_desc" required="required"  ></textarea>
			       </td>
			    </tr>
                 
                <tr>
                    <td width="70" align="center">유통기한</td>
                    <td  >
                        <input type="text" size="40" maxlength="30" name="pd_duedate"  class="inputsix"  required="required" placeholder="유통기한을 입력하세요" >
                    </td>
                </tr>
   				
                <tr>
                    <td width="70" align="center">판매시작 날짜</td>
                    <td >
                        <input type="date" size="40" maxlength="30"  class="inputsix"  name="startdate" placeholder="YYYY-MM-DD">
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">판매종료 날짜</td>
                    <td >
                        <input type="date" size="40" maxlength="30"  class="inputsix"  name="enddate"  placeholder="YYYY-MM-DD">
                    </td>
                </tr>
                
                <tr>
				   <td width="70" align="center"> 반품주소 </td>
				   <td  >	
				   	<button type="button" id="addressButton" class="btn btn-primary btn-block">		   
					   	<input type="text" size="40"  id="address" required="required"  class="inputsix"  name="add_mem_address1" placeholder="주소">  
				 </button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		   	
				    </td> 	
			   </tr> 
			   
			   
			   <tr>		      		      
			      <td width="70"  align="center">반품 상세주소</td>
			      <td   id="add_mem_address2">
			        <input type="text" size="40" maxlength="30" name="add_mem_address2"   class="inputsix"  placeholder="반품 상세주소">
			      </td>
			   </tr>
                
              	
 
                <tr><td></td>
<td style="display: flex; justify-content: center; border-radius: 10px; border: none; text-align: center;">
    <input type="submit" value="상품 상세등록 가기" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1)">
    <input type="reset" class="sizesix" value="다시 작성" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1)">
    <input type="button" value="홈으로" onclick="window.location='/customers/customer'" style="box-shadow: 0 0 10px rgba(0, 0, 0, 0.1)">
</td>   </tr>
            </table>
        </form>
								    	</tr>
					
								       	<tbody id="productBody">
			    						</tbody>
									</table>
									 <div id="pageContent">
								    </div>
 					</td>
			</table>
</div>  
     
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
        
        document.addEventListener('DOMContentLoaded', function () {
            document.getElementById('addressButton2').addEventListener('click', function () {
              new daum.Postcode({
                oncomplete: function (data) {
                  // This function will be called when the user selects an address
                  var fullAddress = data.address; // Full address with postcode
                  document.getElementById('address2').value = fullAddress; // Update the input field with the address
                }
              }).open();
            });
          });

        document.addEventListener('DOMContentLoaded', function () {
            document.getElementById('addressButton3').addEventListener('click', function () {
              new daum.Postcode({
                oncomplete: function (data) {
                  // This function will be called when the user selects an address
                  var fullAddress = data.address; // Full address with postcode
                  document.getElementById('address3').value = fullAddress; // Update the input field with the address
                }
              }).open();
            });
          });
        
        
        
    </script>
	<script>
$(document).ready(function () {
    $('#summernote').summernote({
        codeviewFilter: false, // 코드 보기 필터 비활성화
        codeviewIframeFilter: false, // 코드 보기 iframe 필터 비활성화

        height: 300, // 에디터 높이
        minHeight: null, // 최소 높이
        maxHeight: null, // 최대 높이
        focus: true, // 에디터 로딩 후 포커스 설정
        lang: 'ko-KR', // 언어 설정 (한국어)

        toolbar: [
            ['style', ['style']], // 글자 스타일 설정 옵션
            ['fontsize', ['fontsize']], // 글꼴 크기 설정 옵션
            ['font', ['bold', 'underline', 'clear']], // 글자 굵게, 밑줄, 포맷 제거 옵션
            ['color', ['color']], // 글자 색상 설정 옵션
            ['table', ['table']], // 테이블 삽입 옵션
            ['para', ['ul', 'ol', 'paragraph']], // 문단 스타일, 순서 없는 목록, 순서 있는 목록 옵션
            ['height', ['height']], // 에디터 높이 조절 옵션
            ['insert', ['picture', 'link', 'video']], // 이미지 삽입, 링크 삽입, 동영상 삽입 옵션
            ['view', ['codeview', 'fullscreen', 'help']], // 코드 보기, 전체 화면, 도움말 옵션
        ],

        fontSizes: [
            '8', '9', '10', '11', '12', '14', '16', '18',
            '20', '22', '24', '28', '30', '36', '50', '72',
        ], // 글꼴 크기 옵션

        styleTags: [
            'p',  // 일반 문단 스타일 옵션
            {
                title: 'Blockquote',
                tag: 'blockquote',
                className: 'blockquote',
                value: 'blockquote',
            },  // 인용구 스타일 옵션
            'pre',  // 코드 단락 스타일 옵션
            {
                title: 'code_light',
                tag: 'pre',
                className: 'code_light',
                value: 'pre',
            },  // 밝은 코드 스타일 옵션
            {
                title: 'code_dark',
                tag: 'pre',
                className: 'code_dark',
                value: 'pre',
            },  // 어두운 코드 스타일 옵션
            'h1', 'h2', 'h3', 'h4', 'h5', 'h6',  // 제목 스타일 옵션
        ],

        callbacks: {
            onImageUpload: function (files, editor, welEditable) {
                // 파일 업로드 (다중 업로드를 위해 반복문 사용)
                for (var i = files.length - 1; i >= 0; i--) {
                    var fileName = files[i].name
                    uploadImageFile(files[i], this)
                }
            },
        },
        onMediaDelete: function ($target, editor, $editable) {
            // 삭제된 이미지의 파일 이름을 알아내기 위해 split 활용
            if (confirm('이미지를 삭제하시겠습니까?')) {
                var deletedImageUrl = $target.attr('src').split('/').pop()
                // ajax 함수 호출
                deleteImageFile(deletedImageUrl)
            }
        },
    })
})

function uploadImageFile(file, el) {
		data = new FormData()
        data.append('file', file)
        $.ajax({
            data: data,
            type: 'POST',
            url: '/customers/uploadImageFile',
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (data) {
                $(el).summernote(
                    'editor.insertImage',
                    data.url
                 /* ,   function ($image) {
                        $image.attr('alt', caption) // 캡션 정보를 이미지의 alt 속성에 설정
                    } */
                )
            },
        })
    }
 function deleteImageFile(imageName) {
    data = new FormData()
    data.append('file', imageName)
    $.ajax({
        data: data,
        type: 'POST',
        url: '/customers/deleteImageFile',
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
    })
} 
 
 
</script>
<!-- FOOTER -->
			<footer id="footer">
			<!-- top footer -->
			<div class="section">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">About Us</h3>
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
									<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
									<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Categories</h3>
								<ul class="footer-links">
									<li><a href="#">Hot deals</a></li>
									<li><a href="#">Laptops</a></li>
									<li><a href="#">Smartphones</a></li>
									<li><a href="#">Cameras</a></li>
									<li><a href="#">Accessories</a></li>
								</ul>
							</div>
						</div>

						<div class="clearfix visible-xs"></div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Information</h3>
								<ul class="footer-links">
									<li><a href="#">About Us</a></li>
									<li><a href="#">Contact Us</a></li>
									<li><a href="#">Privacy Policy</a></li>
									<li><a href="#">Orders and Returns</a></li>
									<li><a href="#">Terms & Conditions</a></li>
								</ul>
							</div>
						</div>

						<div class="col-md-3 col-xs-6">
							<div class="footer">
								<h3 class="footer-title">Service</h3>
								<ul class="footer-links">
									<li><a href="#">My Account</a></li>
									<li><a href="#">View Cart</a></li>
									<li><a href="#">Wishlist</a></li>
									<li><a href="#">Track My Order</a></li>
									<li><a href="#">Help</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /top footer -->

			<!-- bottom footer -->
			<div id="bottom-footer" class="section">
				<div class="container">
					<!-- row -->
					<div class="row">
						<div class="col-md-12 text-center">
							
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</span>


						</div>
					</div>
						<!-- /row -->
				</div>
				<!-- /container -->
			</div>
			<!-- /bottom footer -->
		</footer>
		<!-- /FOOTER -->
    
</body>
</html>

