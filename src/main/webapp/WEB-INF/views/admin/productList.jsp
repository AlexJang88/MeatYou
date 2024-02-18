<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <%@ include file="../header.jsp" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
     <style>
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
            background-color: #f4f4f4;
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
            left:18%;
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
       margin-top:3%;
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
            margin-left:18%;
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
        
 
    
  </style>
  <html>
  <body>
  
  <div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2  class="out-table">
    상품 목록 
  	</h2>
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
					        </div>
			    </div>
    
 
    
    
    
      <table class="main-table"  >
            <td class="graph-and-summary">
						 		 	 <table class="summary-table" >
						 		 	 			<tr>
 	   <input type="hidden" id="pageNum" name="pageNum" value="pageNum">
			   	<input type="text" name="keyword" id="keyword">
			    	<select name="searchOpt" id="searchOpt">
			    		<option value="1">전체</option>
			    		<option value="2">제목</option>
			    		<option value="3">판매자</option>
			    	</select>
			    <input type="button" value="검색" id="serch">
			    <hr/>
			    <select name="cate1" id="cate1">
			    		<option value="1">전체</option>
			    		<option value="2">국내산</option>
			    		<option value="3">수입산</option>
			    </select>
			    <select name="cate2" id="cate2">
			    	<option value="1">전체</option>
			    	<option value="2">돼지</option>
			    	<option value="3">소</option>
			    </select>
			    <select name="cate3" id="cate3">
			    	<option value="1">최신순</option>
			    	<option value="2">매출순</option>
			    	<option value="3">조회수순</option>
			    	<option value="4">신고순</option>
			    </select>
			    <select name="pstatus" id="pstatus">
			    	<option value="1">전체</option>
			    	<option value="2">판매중</option>
			    	<option value="3">판매중단(이슈)</option>
			    </select>
 	</tr>	
								    	<tr>
								    		<td style="width:7%; height: 5%;">썸네일</td>
								    		<td>상품이름</td>
								    		<td>가격</td>
								    		<td style="width:9%;">누적판매<br/>금액</td>
								    		<td style="width:8%;">조회수</td>
								    		<td>판매자</td>
								    		<td style="width:8%;">신고수</td>
								    		<td>등록일</td>
								    		<td>판매상태</td>
								    		<td>판매중지</td>
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
// 데이터 가져오는 함수
function fetchData(keyword, searchOpt, cate1, cate2, cate3,pstatus,pageNum) {
    console.log(pstatus);
	 $.ajax({
        url: '/admin/serchProductList', // 서버의 URL을 입력하세요.
        type: 'GET',
        dataType: 'json',
        data: {
            keyword: keyword,
            searchOpt: searchOpt,
            cate1: cate1,
            cate2: cate2,
            cate3: cate3,
            pstatus:pstatus,
            pageNum: pageNum
        },
        success: function(response) {
           	console.log(response);
        	updateTable(response.products);
        	updatePage(response.pageData);
        },
        error: function(xhr, status, error) {
            alert("An error occurred: " + error);
        }
    }); 
}
    window.onload=function(){
	// 초기 검색 조건 설정
    var initialKeyword = '550e8400-e29b-41d4-a716-446655440000'; // 초기 키워드는 빈 문자열
    var initialSearchOpt = '1'; // 초기 검색 옵션
    var initialCate1 = '1'; // 초기 카테고리 1
    var initialCate2 = '1'; // 초기 카테고리 2
    var initialCate3 = '1'; // 초기 카테고리 3
    var initialpageNum='1';
    var initialpstatus='1';

    // 페이지 로드 시 초기 상품 목록 가져오기
    fetchData(initialKeyword, initialSearchOpt, initialCate1, initialCate2, initialCate3,initialpstatus,initialpageNum);
    }
	
	// 검색 버튼 클릭 이벤트
    $("#serch").click(function() {
        var keyword = $('#keyword').val();
        if(!keyword){
        	keyword='550e8400-e29b-41d4-a716-446655440000';
        }
        var searchOpt = $('#searchOpt').val();
        var pageNum ='1'
        fetchData(keyword, searchOpt, $('#cate1').val(), $('#cate2').val(),$('#cate3').val(),$('#pstatus').val(),pageNum);
    });

    // 카테고리 변경 이벤트
    $("#cate1, #cate2, #cate3, #pstatus").change(function() {
        var keyword = $('#keyword').val();
        if(!keyword){
        	keyword='550e8400-e29b-41d4-a716-446655440000';
        }
        var searchOpt = $('#searchOpt').val();
        var pageNum ='1'
        fetchData(keyword, searchOpt, $('#cate1').val(), $('#cate2').val(),$('#cate3').val(),$('#pstatus').val(),pageNum);
    });

   

    // 테이블 업데이트 함수
    function updateTable(products) {
        var tableContent = "";
        if(products.length>0){
        $.each(products, function(index, product) {
            tableContent += "<tr>" +
                "<td> <img style='width:100%; height:100%;' src='/resources/file/product/"+product.p_num+"/"+ product.thumb + "'/></td>" +
                "<td>" + product.p_name + "</td>" +
                "<td>" + product.p_price + "</td>" +
                "<td>" + product.totalsales + "</td>" +
                "<td>" + product.p_rcount + "</td>" +
                "<td>" + product.p_m_id + "</td>" +
                "<td>" + product.report + "</td>" +
                "<td>" + formatDate(product.p_reg_date) + "</td>";
                
               if(product.pd_p_status==0){
            	   tableContent+=
            		  "<td>판매중(이슈없음)</td>";
               }else if(product.pd_p_status>0){
            	   tableContent+=
             		  "<td>판매중단(노출안됨)</td>";
               }
               if(product.pd_p_status==0){
               tableContent+="<td>"
               tableContent+="<form action='/admin/memo' method='post'><input type='hidden' name='p_num' value='"+product.p_num+"'>"
               tableContent+="<intput type='hidden' name='check' value='"+1+"'>"
               tableContent+="<intput type='hidden' name='p_status' value=''"+product.p_status+"'>"
               
               tableContent+="<input type='submit' value='판매중지' onsubmit='return confirmsbm();'>"
               tableContent+="</td>";
               tableContent+="</tr>";
               }else if((product.pd_p_status/10)%10>0){
            	   tableContent+="<td>"
                   tableContent+="<form action='/admin/pschange' method='post'><input type='hidden' name='p_num' value='"+product.p_num+"'>"
                   tableContent+="<intput type='hidden' name='check' value='"+2+"'>"
                   tableContent+="<intput type='hidden' name='p_status' value='"+product.p_status+"'>"
                   tableContent+="<input type='submit' value='판매중지' onsubmit='return confirmsbm();'>"
                   tableContent+="</td>";
                   tableContent+="</tr>";
               }
        });
        }else{
        	tableContent += "검색결과가 없습니다.";	
        }
        $('#productBody').html(tableContent);
    }

    // 날짜 포맷 함수
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }
    
    function updatePage(pageData){
        console.log(pageData);
        var pageContent = '';
        if (pageData.startPage > 1) {
            pageContent += '<a href="#" onclick="fetchData(\'' + pageData.keyword + '\', \'' + pageData.searchOpt + '\', \'' + pageData.cate1 + '\', \'' + pageData.cate2 + '\', \'' + pageData.cate3 + '\',\'' + pageData.pstatus + '\',  ' + (pageData.startPage - 10) + '); return false;">[이전]</a> ';
        }
        for (var i = pageData.startPage; i <= pageData.endPage; i++) {
            pageContent += '<a href="#" onclick="fetchData(\'' + pageData.keyword + '\', \'' + pageData.searchOpt + '\', \'' + pageData.cate1 + '\', \'' + pageData.cate2 + '\', \'' + pageData.cate3 + '\',\'' + pageData.pstatus + '\',  ' + i + '); return false;">[' + i + ']</a> ';
        }
        if (pageData.endPage < pageData.pageCount) {
            pageContent += '<a href="#" onclick="fetchData(\''+pageData.keyword+'\',\''+pageData.searchOpt+'\',\'' +pageData.cate1+'\',\''+pageData.cate2+'\',\''+pageData.cate3+'\',\'' + pageData.pstatus + '\', ' + (pageData.endPage + 1) + '); return false;">[다음]</a> ';
        }
        $('#pageContent').html(pageContent);
    }

</script>
<script>
 function confirmsbm(){
	 var check=confirm("판매중지 시키겠습니까?");
	 if(check){
		 return true;
	 }else{
		 return false;
	 }
 }
</script>
  <%@ include file="../footer.jsp" %>
    