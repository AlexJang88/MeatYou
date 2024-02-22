<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/admin/css/noticeForm.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
<%@ taglib prefix="sec"  	uri="http://www.springframework.org/security/tags"%>
<sec:authorize access="hasRole('ROLE_ADMIN')">

	<link
		href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
		rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

	<!-- include summernote css/js -->
	<link
		href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<div class="bodyArea"> 
<div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2 id="getYear" class="out-table">공지사항 [ 새글 작성하기  ]</h2><br/> 
</div>


<div class="main-container" >
    <div class="category-menu">
        <!-- Your category menu content -->
        <div class="vertical-menu">
            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">회원</a>
                <div id="mem" class="collapse">
                    <a href="/admin/memberlist?check=1" class="btn" id="smallfont" >회원목록 조회(일반)</a><br/>
                    <a href="/admin/memberlist?check=2" class="btn" id="smallfont" >회원목록 조회(판매자)</a><br/>
                    <a href="/admin/memberlist?check=3" class="btn" id="smallfont" >판매자 승인대기</a><br/>
                    <a href="/admin/memberlist?check=4" class="btn" id="smallfont" >판매자(유료회원)목록</a><br/>
                </div>
            </div>
            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">상품</a>
                <div id="pro" class="collapse">
                    <a href="/admin/productList" class="btn" id="smallfont" >상품 목록 보기</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">정산</a>
                <div id="money" class="collapse">
                    <a href="/admin/sales" class="btn" id="smallfont" >매출 보기</a><br/>
                    <a href="/admin/reckon" class="btn" id="smallfont" >정산내역 확인</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">관리자 체크</a>
                <div id="check" class="collapse">
                    <a href="/admin/noticeList" class="btn" id="smallfont" > 공지 사항</a><br/>
                    <a href="/admin/reportList" class="btn"  id="smallfont" >신고글 보기</a>
                </div>
            </div>
            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#Qna" id="bigfont">Qna</a>
                <div id="Qna" class="collapse">
                    <a href="/board/consumerQna" class="btn" id="smallfont" >관리자에게하는소통</a><br/>
                    <a href="/board/sellerQna " class="btn"  id="smallfont" >판매자님이 관리자:나에게</a>
                </div>
            </div>
        </div>
    </div>
      <table class="main-table"  >
            <td class="graph-and-summary">
		 		  <table class="summary-table" >
					 				

	<form method="post" action="/admin/noticeReg"
		enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" /> 
			<input type="hidden" name="category"
			value="10"> 
	  <input type="text" name="n_title" placeholder="제목 작성부탁드립니다 ! "style="margin-bottom:2%;">
		<textarea id="summernote" name="n_content"></textarea>
		<input type="submit" value="전송">
	</form>
						</table>
 
			
			   </td>
		</table>
      
 
</div>

	<script>
		$(document)
				.ready(
						function() {
							$('#summernote')
									.summernote(
											{
												codeviewFilter : false, // 코드 보기 필터 비활성화
												codeviewIframeFilter : false, // 코드 보기 iframe 필터 비활성화

												height : 500, // 에디터 높이
												minHeight : null, // 최소 높이
												maxHeight : null, // 최대 높이
												focus : true, // 에디터 로딩 후 포커스 설정
												lang : 'ko-KR', // 언어 설정 (한국어)
												toolbar : [
														[ 'style', [ 'style' ] ], // 글자 스타일 설정 옵션
														[ 'fontsize',
																[ 'fontsize' ] ], // 글꼴 크기 설정 옵션
														[
																'font',
																[
																		'bold',
																		'underline',
																		'clear' ] ], // 글자 굵게, 밑줄, 포맷 제거 옵션
														[ 'color', [ 'color' ] ], // 글자 색상 설정 옵션
														[ 'table', [ 'table' ] ], // 테이블 삽입 옵션
														[
																'para',
																[ 'ul', 'ol',
																		'paragraph' ] ], // 문단 스타일, 순서 없는 목록, 순서 있는 목록 옵션
														[ 'height',
																[ 'height' ] ], // 에디터 높이 조절 옵션
														[
																'insert',
																[ 'picture',
																		'link',
																		'video' ] ], // 이미지 삽입, 링크 삽입, 동영상 삽입 옵션
														[
																'view',
																[
																		'codeview',
																		'fullscreen',
																		'help' ] ], // 코드 보기, 전체 화면, 도움말 옵션
												],
												fontSizes : [ '8', '9', '10',
														'11', '12', '14', '16',
														'18', '20', '22', '24',
														'28', '30', '36', '50',
														'72', ], // 글꼴 크기 옵션
												styleTags : [ 'p', // 일반 문단 스타일 옵션
												{
													title : 'Blockquote',
													tag : 'blockquote',
													className : 'blockquote',
													value : 'blockquote',
												}, // 인용구 스타일 옵션
												'pre', // 코드 단락 스타일 옵션
												{
													title : 'code_light',
													tag : 'pre',
													className : 'code_light',
													value : 'pre',
												}, // 밝은 코드 스타일 옵션
												{
													title : 'code_dark',
													tag : 'pre',
													className : 'code_dark',
													value : 'pre',
												}, // 어두운 코드 스타일 옵션
												'h1', 'h2', 'h3', 'h4', 'h5',
														'h6', // 제목 스타일 옵션
												],

												callbacks : {
													onImageUpload : function(
															files, editor,
															welEditable) {
														// 파일 업로드 (다중 업로드를 위해 반복문 사용)
														for (var i = files.length - 1; i >= 0; i--) {
															var fileName = files[i].name
															uploadImageFile(
																	files[i],
																	this)
														}
													},
												},
												onMediaDelete : function(
														$target, editor,
														$editable) {
													// 삭제된 이미지의 파일 이름을 알아내기 위해 split 활용
													if (confirm('이미지를 삭제하시겠습니까?')) {
														var deletedImageUrl = $target
																.attr('src')
																.split('/')
																.pop()
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
				data : data,
				type : 'POST',
				url : '/admin/uploadImageFile',
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
				success : function(data) {
					$(el).summernote('editor.insertImage', data.url
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
				data : data,
				type : 'POST',
				url : '/admin/deleteImageFile',
				contentType : false,
				enctype : 'multipart/form-data',
				processData : false,
			})
		}
	</script>
</sec:authorize> 










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