<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   
    <h1>상품목록</h1>
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
    <table>
    	<tr>
    		<td>썸네일</td>
    		<td>상품이름</td>
    		<td>가격</td>
    		<td>누적판매금액</td>
    		<td>조회수</td>
    		<td>판매자</td>
    		<td>신고수</td>
    		<td>등록일</td>
    		<td>판매상태</td>
    		<td>판매중지</td>
    	</tr>
    	<tbody id="productBody">
    	</tbody>
    </table>
    <div id="pageContent">
    
    </div>
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
                "<td>" + product.thumb + "</td>" +
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
               }else if(product.p_status>0){
            	   tableContent+=
             		  "<td>판매중단(노출안됨)</td>";
               }
               if(product.pd_p_status==0){
               tableContent+="<td>"
               tableContent+="<form action='/admin/memo' method='post'><input type='hidden' name='p_num' value='"+product.p_num+"'>"
               tableContent+="<intput type='hidden' name='check' value='"+1+"'>"
               tableContent+="<intput type='hidden' name='p_status' value=''"product.p_status+"'>"
               
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
    	console.log(pageData)
    	var pageContent = '';
        if (pageData.startPage > 1) {
        	pageContent += '<a href="#" onclick="fetchData(\'' + pageData.keyword + '\', \'' + pageData.searchOpt + '\', \'' + pageData.cate1 + '\', \'' + pageData.cate2 + '\', \'' + pageData.cate3 + '\',\'' + pageData.pstatus + '\',  ' + (pageData.startPage - 1) + '); return false;">[이전]</a> ';
        }
        for (var i = pageData.startPage; i <= pageData.endPage; i++) {
        	pageContent += '<a href="#" onclick="fetchData(\'' + pageData.keyword + '\', \'' + pageData.searchOpt + '\', \'' + pageData.cate1 + '\', \'' + pageData.cate2 + '\', \'' + pageData.cate3 + '\',\'' + pageData.pstatus + '\',  ' + i + '); return false;">[' + i + ']</a> ';
        }
        if (pageData.endPage < pageData.totalPages) {
        	pageContent += '<a href="#" onclick="fetchData(\''+pageData.keyword+'\',\''+pageData.searchOpt+'\',\'' +pageData.cate1+'\',\''+pageData.cate2+'\',\''+pageData.cate3+'\',\'' + pageData.pstatus + '\', \'' + (pageData.endPage + 1) + '); return false;">[다음]</a> ';
        }
        // 여기서 "#pagination"은 페이징 링크를 담을 HTML 요소의 ID
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
    
    