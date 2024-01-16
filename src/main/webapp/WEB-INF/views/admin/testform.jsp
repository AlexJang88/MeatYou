<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<c:if test="${n_num==0}">
<form method="post" action="/admin/reg" enctype="multipart/form-data">
    <!-- 수정 페이지에서 원시 코드 보존을 위한 temp 부분 -->
    <div id="temp" style="display: none">${board.n_content}</div>

    <!-- 썸머노트 에디터 -->
    <textarea id="summernote" name="content"></textarea>

    <!-- 임시저장과 저장 버튼 -->
    <input type="submit" name="doit" value="저장" />
</form>
</c:if>
<c:if test="${n_num==1}">
<form method="post" action="/admin/update" enctype="multipart/form-data">
    <!-- 수정 페이지에서 원시 코드 보존을 위한 temp 부분 -->
    <div id="temp" style="display: none">${board.contents}</div>

    <!-- 썸머노트 에디터 -->
    <textarea id="summernote" name="content"></textarea>

    <!-- 임시저장과 저장 버튼 -->
    <input type="submit" name="doit" value="저장" />
</form>
</c:if>
<script>
    // 수정페이지에서 원시 코드 보존을 통한 코드 깨짐 방지
    window.onload = function () {
        $('#summernote').summernote('code', document.getElementById('temp').innerHTML)
    }

    $(document).ready(function () {
        $('#summernote').summernote({
            // 썸머노트 옵션 설정
            codeviewFilter: false,
            codeviewIframeFilter: false,
            disableXSSProtection: true,
            height: 500,
            minHeight: null,
            maxHeight: null,
            focus: true,
            lang: 'ko-KR',
            toolbar: [
                // 스타일 관련 기능
                ['style', ['style']],
                // 글자 크기 설정
                ['fontsize', ['fontsize']],
                // 글꼴 스타일
                ['font', ['bold', 'underline', 'clear']],
                // 글자 색상
                ['color', ['color']],
                // 테이블 삽입
                ['table', ['table']],
                // 문단 스타일
                ['para', ['paragraph']],
                // 에디터 높이 설정
                ['height', ['height']],
                // 이미지, 링크, 동영상 삽입
                ['insert', ['picture', 'link', 'video']],
                // 코드 보기, 전체화면, 도움말
                ['view', ['codeview', 'fullscreen', 'help']],
            ],
            fontSizes: [
                // 글자 크기 선택 옵션
                '8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'
            ],
            styleTags: [
                // 스타일 태그 옵션
                'p',
                { title: 'Blockquote', tag: 'blockquote', className: 'blockquote', value: 'blockquote' },
                'pre',
                { title: 'code_light', tag: 'pre', className: 'code_light', value: 'pre' },
                { title: 'code_dark', tag: 'pre', className: 'code_dark', value: 'pre' },
                'h1', 'h2', 'h3', 'h4', 'h5', 'h6'
            ],

            callbacks: {
                onImageUpload: function (files, editor, welEditable) {
                    // 파일 업로드
                    for (var i = files.length - 1; i >= 0; i--) {
                        var fileName = files[i].name
                        var caption = prompt('이미지 설명:', fileName)
                        if (caption == '') {
                            caption = '이미지'
                        }
                        uploadSummernoteImageFile(files[i], this, caption)
                    }
                },
                onMediaDelete: function ($target, editor, $editable) {
                    // 삭제된 이미지의 파일 이름을 알아내기 위해 split 활용
                    if (confirm('이미지를 삭제하시겠습니까?')) {
                        var deletedImageUrl = $target.attr('src').split('/').pop()

                        // ajax 함수 호출
                        deleteSummernoteImageFile(deletedImageUrl)
                    }
                },
            },
        })
    })

    // 이미지 업로드 ajax
    function uploadSummernoteImageFile(file, el, caption) {
        data = new FormData()
        data.append('file', file)
        $.ajax({
            data: data,
            type: 'POST',
            url: 'uploadSummernoteImageFile',
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
            success: function (data) {
                $(el).summernote('editor.insertImage', data.url, function ($image) {
                    $image.attr('alt', caption) // 캡션 정보를 이미지의 alt 속성에 설정
                })
            },
        })
    }

    // 이미지 삭제 ajax
    function deleteSummernoteImageFile(imageName) {
        data = new FormData()
        data.append('file', imageName)
        $.ajax({
            data: data,
            type: 'POST',
            url: 'deleteSummernoteImageFile',
            contentType: false,
            enctype: 'multipart/form-data',
            processData: false,
        })
    }
</script>
    