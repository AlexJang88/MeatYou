<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>   
    <h1>상품목록</h1>
   	<input type="text" name="keyword" id="keyword">
    	<select name="serchOpt" id="serchOpt">
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

    <table id="tb">
    	<tr>
    		<td>썸네일</td>
    		<td>상품이름</td>
    		<td>가격</td>
    		<td>누적판매금액</td>
    		<td>조회수</td>
    		<td>판매자</td>
    		<td>신고수</td>
    		<td>등록일</td>
    	</tr>
    	<tbody id="productBody">
    		
    	</tbody>
    <c:forEach var="product" items="${list}">
    	<tr>
			<td>${product.thumb}</td>
			<td>${product.p_name}</td>
			<td>${product.p_price}</td>
			<td>${product.totalsales}</td>
			<td>${product.p_rcount}</td>
			<td>${product.p_m_id}</td>
			<td>${product.report}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd" value="${product.p_reg_date}"/></td>    		
    	</tr>
    </c:forEach>
    </table>
    <c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/admin/productList?pageNum=${startPage-10}&keyword=${keyword}&searchOpt=${searchOpt}&cate1=${cate1}&cate2=${cate2}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/admin/productList?pageNum=${i}&keyword=${keyword}&searchOpt=${searchOpt}&cate1=${cate1}&cate2=${cate2}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/admin/productList?pageNum=${startPage+10}&keyword=${keyword}&searchOpt=${searchOpt}&cate1=${cate1}&cate2=${cate2}">[다음]</a>
			</c:if>
		</c:if>

<script>
$(document).ready(function () {
    // 검색 버튼 클릭 이벤트
    $("#serch").click(function() {
        var keyword = $('#keyword').val();
        var searchOpt = $('#serchOpt').val();
        fetchData(keyword, searchOpt, $('#cate1').val(), $('#cate2').val(),$('#cate3').val());
    });

    // 카테고리 변경 이벤트
    $("#cate1, #cate2, #cate3").change(function() {
        var keyword = $('#keyword').val();
        var searchOpt = $('#serchOpt').val();
        fetchData(keyword, searchOpt, $('#cate1').val(), $('#cate2').val(),$('#cate3').val());
    });

    // 데이터 가져오는 함수
    function fetchData(keyword, searchOpt, cate1, cate2, cate3) {
        $.ajax({
            url: '/admin/serchProductList', // 서버의 URL을 입력하세요.
            type: 'GET',
            dataType: 'json',
            data: {
                keyword: keyword,
                searchOpt: searchOpt,
                cate1: cate1,
                cate2: cate2,
                cate3: cate3
            },
            success: function(response) {
                updateTable(response.list);
            },
            error: function(xhr, status, error) {
                alert("An error occurred: " + error);
            }
        });
    }

    // 테이블 업데이트 함수
    function updateTable(products) {
    	console.log(products);
        var tableContent = "";
        $.each(products, function(index, product) {
            tableContent += "<tr>" +
                "<td>" + product.thumb + "</td>" +
                "<td>" + product.p_name + "</td>" +
                "<td>" + product.p_price + "</td>" +
                "<td>" + product.totalsales + "</td>" +
                "<td>" + product.p_rcount + "</td>" +
                "<td>" + product.p_m_id + "</td>" +
                "<td>" + product.report + "</td>" +
                "<td>" + formatDate(product.p_reg_date) + "</td>" +
                "</tr>";
        });
        $('#productBody').append(tableContent);
    }

    // 날짜 포맷 함수
    function formatDate(date) {
    	console.log(dae)
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('-');
    }
});
</script>
    
    