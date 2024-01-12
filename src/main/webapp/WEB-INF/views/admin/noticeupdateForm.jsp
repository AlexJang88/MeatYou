<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
  
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	
	
<form method="post" action="/admin/noticeupdate" enctype="multipart/form-data">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  <input type="hidden" name="category" value="10">
  <input type="hidden" name="n_num" id="n_num" value="${dto.n_num}"/>
  제목 : <input type="text" name="n_title" value="${dto.n_title}">
  <textarea id="summernote" name="n_content">
  	${dto.n_content}
  </textarea>
  <input type="submit" value="수정">
</form>

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
            onImageUpload: function (files, editor) {
                // 파일 업로드 (다중 업로드를 위해 반복문 사용)
                var n_num = $('#n_num').val();
                for (var i = files.length - 1; i >= 0; i--) {
                    var fileName = files[i].name
                    updateSummernoteImageFile(files[i], this,n_num)
                }
            },
        },
    })
})

function updateSummernoteImageFile(file, el,n_num) {
		data = new FormData()
        data.append('file', file)
        $.ajax({
            data: {data:data,n_num:n_num},
            type: 'POST',
            url: '/admin/updateSummernoteImageFile',
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (fdata) {
                $(el).summernote(
                    'editor.insertImage',
                    data.url
                )
            },
        })
    }

</script>
</sec:authorize>