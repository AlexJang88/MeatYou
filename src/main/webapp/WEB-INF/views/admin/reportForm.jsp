<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>

<style>
body{
 align:center;
  margin: 0 auto;
}
  
    
    #summernote {
        margin: 0 auto;
        text-align: center;
    }

    .note-editor {
        display: inline-block;
        text-align: left;
    }
        .ui-datepicker-calendar {
            display: none;
        }
        
     /*      .bodyArea {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        } */
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
         color:black;
      
}

/* Add this style for the smallfont element */
#smallfont {
    font-size: 12px; /* You can adjust the font size as needed */
    font-family: 'Quicksand', sans-serif; /* Change the font family as needed for smallfont */
            color:black;
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
            left:18%;
            z-index: 2
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
            margin-bottom:5%;
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
               width: 80%;
        margin: 0 auto; /* Center the element horizontally */
        text-align: center; /* Center the content inside the element */
 		margin-left:auto;
 		margin-right:auto;
 		
		position: relative;
        }




     .main-table {
            width: 50%;
            margin: 0 auto;
            margin-left:24%;
            height: 60%;
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


                
       select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 12px;
        margin-right: 5px;
     /*    height: 30px; */
        border:none;
    }

    /* Style for submit button */
    input[type="submit"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: gray;
    }
 
    
    input[type="submit"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
    input[type="text"] {
        border-radius: 4px;
        margin-top:3%;
    }
        
           button{
           padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer; 
           }
button:hover {
        background-color: gray;
    }
    input[type="button"]:hover {
        background-color: gray;
    }
    input[type="button"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
    input[type="reset"]:hover {
        background-color: gray;
    }
    input[type="reset"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }
      


</style>
    <%@ include file="../header.jsp" %>
    <meta charset="UTF-8">
    <title>Q&A Details</title>
</head>

<body>		
    <div class="main-container" align="center" style="width: 100%;"> 
      
				<form method="post" action="/admin/reportReg" enctype="multipart/form-data"  align="center">
				   <table class="summary-table" align="center">
                <td class="graph-and-summary" align="center">
				  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <input type="hidden" name="ma_ref" value="${p_num}">
				  제목 : <input type="text" name="ma_title"><br/>
				 
				  <textarea id="summernote" name="ma_content"  ></textarea>
				  <input type="hidden" name="category" value="40">
				   
				
				   </td><tr>
				   <td>
				    <input type="submit" value="전송">
				   </td>
				   </tr>
				          	</table>
				</form>

               

    </div>
<script>
$(document).ready(function () {
    $('#summernote').summernote({
        codeviewFilter: false, // 코드 보기 필터 비활성화
        codeviewIframeFilter: false, // 코드 보기 iframe 필터 비활성화
		width:600,
        height: 450, // 에디터 높이
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
            url: '/admin/uploadReportImageFile',
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
 
</script>

</body>




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
</html>
