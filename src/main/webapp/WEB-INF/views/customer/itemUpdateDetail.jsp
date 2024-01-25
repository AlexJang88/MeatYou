<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
 <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
 
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>상품 등록 상세</title>
</head>
<body>
    <a href="/customers/customer">홈으로</a>
    <h1>상품 등록 상세페이지</h1>	
    <center>
        <b>상품 상세 등록하기</b><br> 
           
         
        <form method="post" name="productForm" action="/customers/itemUpdatePro" >
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        	
        
            <table width="500" border="1" cellspacing="0" cellpadding="0" align="center">
            
 			   <tr>
                    <td width="70" align="center">원산지</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" name="origin" required="required" placeholder="원산지를 입력하세요">
                    </td>
                </tr>
			  
			             
                <tr>
                    <td width="70" align="center">중량</td>
                    <td width="330">
                        <input type="number" size="40" maxlength="30" name="weight" required="required" placeholder="숫자를 입력하세요">g(그람)
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">농장주소</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" id="address"  name="local" required="required" placeholder="농장주소를 입력하세요">
                        <button type="button" id="addressButton" class="btn btn-primary btn-block">주소 검색</button>
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
                    </td>
                </tr>
                
                <tr>
                    <td width="70" align="center">도축장</td> 
                    <td width="330">
                        <input type="text" size="40" maxlength="50" id="address2" name="butchery" required="required" placeholder="도축장소를 입력하세요">
                        <button type="button" id="addressButton2" class="btn btn-primary btn-block">주소 검색</button>
    					 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />	
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
			       <textarea id="summernote" name="pd_p_desc" rows="13" cols="40" required="required"></textarea> </td>
			    </tr>
                 
                <tr>
                    <td width="70" align="center">유통기한</td>
                    <td width="330">
                        <input type="text" size="40" maxlength="30" name="pd_duedate" required="required" placeholder="유통기한을 입력하세요" >
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
                    uploadSummernoteImageFile(files[i], this)
                }
            },
        },
        onMediaDelete: function ($target, editor, $editable) {
            // 삭제된 이미지의 파일 이름을 알아내기 위해 split 활용
            if (confirm('이미지를 삭제하시겠습니까?')) {
                var deletedImageUrl = $target.attr('src').split('/').pop()

                // ajax 함수 호출
                deleteSummernoteImageFile(deletedImageUrl)
            }
        },
    })
})

function uploadSummernoteImageFile(file, el) {
		data = new FormData()
        data.append('file', file)
        $.ajax({
            data: data,
            type: 'POST',
            url: '/customer/uploadProductImageFile',
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
/* function deleteSummernoteImageFile(imageName) {
    data = new FormData()
    data.append('file', imageName)
    $.ajax({
        data: data,
        type: 'POST',
        url: '/admin/deleteSummernoteImageFile',
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
    })
} */

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


</script>
