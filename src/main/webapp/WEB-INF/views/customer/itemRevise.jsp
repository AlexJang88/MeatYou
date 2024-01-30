<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
   <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script>
    function updateThumbnail(input) {
        var thumbnailImage = document.getElementById('thumbnailImage');
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                thumbnailImage.src = e.target.result;
            };
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<meta charset="UTF-8">
<title>상품 수정 페이지</title>
</head>
<body>
    <a href="/customers/customer">홈으로</a>
    <h1>상품 수정 페이지</h1>	
    <center>
        <b>상품 수정하기</b><br>      
         
        <form method="post" name="productForm" action="/customers/productUpdateReg" enctype="multipart/form-data">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        	<input type="hidden" name="p_num" value="${lister.p_num}">
        	<input type="hidden" name="pd_p_num" value="${lister.p_num}">
        
            <table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
            
                <tr>
                    <td width="70" align="center">상품명</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="p_name" required="required" value="${lister.p_name}" placeholder="판매할 제목을 적어주세요">
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
				    <td width="70" align="center">썸네일사진</td>
				    <td width="330" id="thumbs">
				        <img id="thumbnailImage" src="/resources/file/product/${lister.p_num}/${lister.thumb}/">
				        변경시 아래 파일 선택 클릭
				        <input type="file" size="40" maxlength="30" name="thumbs" onchange="updateThumbnail(this)" value="${lister.thumb}">
				    </td>
				</tr>
			   
			    <tr>
                    <td width="70" align="center">품목 단위</td>
                    <td width="330">             
                        <select name="p_s_category" value="${lister.p_s_category}"  required="required">
                            <option value="0">단품</option>
                            <option value="1">세트</option>
                            <option value="2">선물세트</option>                     
                        </select>
                    </td>
                </tr>
			             
			     <tr>
                    <td width="70" align="center">가격</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="p_price"  value="${lister.p_price}" required="required" placeholder="숫자를 입력하세요"> 원
                    </td>
                </tr>
                
                
                <tr>
                    <td width="70" align="center">원산지</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50"   name="origin"  value="${listerPD.origin}" required="required" placeholder="원산지를 입력하세요">
                        
                    </td>
                </tr>
			             
                <tr>
                    <td width="70" align="center">중량</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="weight" value="${listerPD.weight}" required="required" placeholder="숫자를 입력하세요">g(그람)
                    </td>
                </tr>
                          
                
                <tr>
                    <td width="70" align="center">농장주소</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" id="address2" name="local"  value="${listerPD.local}" required="required" placeholder="농장주소를 입력하세요">
                        <button type="button" id="addressButton2" class="btn btn-primary btn-block">주소 검색</button>
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                    </td>
                </tr>
           
                
                 <tr>
                    <td width="70" align="center">도축장</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" id="address3" name="butchery"  value="${listerPD.butchery}"required="required" placeholder="도축장소를 입력하세요">
                        <button type="button" id="addressButton3" class="btn btn-primary btn-block">주소 검색</button>
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                    </td>
                </tr>
               
                <tr>
                    <td width="70" align="center">이력(묶음)번호</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="serialNum"  value="${listerPD.serialNum}" required="required" placeholder="숫자를 입력하세요">
                    </td>
                </tr>
                
                 <tr>
                    <td width="70" align="center">보관방법</td>
                    <td width="330">             
                        <select name="retain" required="required" value="${listerPD.retain}" >
                            <option value="냉장">냉장</option>
                            <option value="냉동">냉동</option>
                            <option value="실온">실온</option>                     
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">재고</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="stock" value="${listerPD.stock}" required="required" placeholder="재고를 입력하세요">
                    </td>
                </tr>
              
                <tr>
				    <td width="70" align="center">상품설명</td>
				    <td width="330">
				        <div id="temp" style="display: none">${listerPD.pd_p_desc}</div> 
				        <textarea id="summernote" name="pd_p_desc"></textarea>
				    </td>
				</tr>
                 
                <tr>
                    <td width="70" align="center">유통기한</td>
                    <td width="330">
                        <input type="text" size="40" maxlength="30" name="pd_duedate" value="${listerPD.pd_duedate}" required="required" placeholder="유통기한을 입력하세요" >
                    </td>
                </tr>
                 
                
                <tr>
                    <td width="70" align="center">판매시작 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="startdate" placeholder="YYYY-MM-DD">     ${lister.startdate} 
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">판매종료 날짜</td>
                    <td width="330">
                        <input type="date" size="40" maxlength="30" name="enddate"   placeholder="YYYY-MM-DD">   ${lister.enddate}         
                    </td>
                </tr>
              	
              	<tr>
				   <td width="70" align="center"> 반품주소 </td>
				   <td width="330">			   
					   	<input type="text" size="40"  id="address" required="required" value="${listemd.add_mem_address1}" name="add_mem_address1" placeholder="주소">  
						<button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />		   	
				    </td> 	
			   </tr> 
			   
			   
			   <tr>		      		      
			      <td width="70"  align="center">반품 상세주소</td>
			      <td width="330" id="add_mem_address2">
			        <input type="text" size="40" maxlength="30" name="add_mem_address2" value="${listemd.add_mem_address2}" placeholder="반품 상세주소">
			      </td>
			   </tr>
              	
              	
 
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="상품 수정">
                        <input type="reset" value="다시 작성">
                        <input type="button" value="홈으로" onclick="window.location='/customers/customer'">
                    </td>
                </tr>
            </table>
        </form>
    </center>


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
    <script>
    
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
    
    
    
    
    
    
    
    
    
    
    // 수정페이지에서 원시 코드 보존을 통한 코드 깨짐 방지
   

    $(document).ready(function () {
        $('#summernote').summernote({
            codeviewFilter: false, // 코드 보기 필터 비활성화
            codeviewIframeFilter: false, // 코드 보기 iframe 필터 비활성화

            height: 500, // 에디터 높이
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
         $('#summernote').summernote('code', '${listerPD.pd_p_desc}')
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

</body>
</html>