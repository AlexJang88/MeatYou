<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의사항 작성하기</title>

<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

</head>
<body>
<h1> 문의사항 소비자 관리자 작성</h1>

<form action="questionCAPro" method="post" enctype="multipart/form-data">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	<input type="hidden" name="ma_m_id" value="${id}">
	<table width="500" border="1" cellspacing="0" cellpadding="0" align="center">

		<tr>
			<td width="70" align="center" >제목</td>
			<td><input type="text" name="ma_title" size="40" maxlength="50" placeholder="제목을 적어주세요" required="required"></td>
		</tr>
		
		<tr>
		    <td width="70" align="center" >문의내용  입력</td> 
			<td width="330" >
			    <textarea id="summernote" name="ma_content" required="required"  placeholder="문의하실 내용을 적어주세요"></textarea>
			</td>
		</tr> 
		
		<tr>
             <td colspan="2" align="center">
                <input type="submit" value="문의하기 등록">
                <input type="reset" value="다시 작성">
                <input type="button" value="목록으로" onclick="window.location='/board/consumerQna'">
             </td>
        </tr>
		
	</table>	
</form>
</body>

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
            url: '/board/uploadImageFileCA',
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
    data.append('pq_p_num', ${pq_p_num})
    $.ajax({
        data: data,
        type: 'POST',
        url: '/board/deleteImageFileCA',
        contentType: false,
        enctype: 'multipart/form-data',
        processData: false,
    })
} 
 


</script>

</html>