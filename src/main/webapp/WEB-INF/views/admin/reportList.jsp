<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<html>
<head>

    <link rel="stylesheet" href="/resources/admin/css/reportList.css"> <!-- 외부 스타일시트 추가 -->	
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
 </head>
 <body>
		<div style="margin-top: 40px; display:flex; margin-bottom: 30px;">
    <h2  class="out-table"> 신고 </h2>  
 
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
<ul class="nav nav-tabs">
    <li class="nav-item">
        <c:choose>
            <c:when test="${check eq 1}">
                <a class="nav-link active-tab" href="/admin/reportList?check=1">미답변 신고/문의</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link inactive-tab" href="/admin/reportList?check=1">미답변 신고/문의</a>
            </c:otherwise>
        </c:choose>
    </li>
    <li class="nav-item">
        <c:choose>
            <c:when test="${check eq 2}">
                <a class="nav-link active-tab" href="/admin/reportList?check=2">답변 신고/문의</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link inactive-tab" href="/admin/reportList?check=2">답변 신고/문의</a>
            </c:otherwise>
        </c:choose>
    </li>
    <li class="nav-item">
        <c:choose>
            <c:when test="${check eq 3}">
                <a class="nav-link active-tab" href="/admin/reportList?check=3" id="graytab3">전체 신고/문의</a>
            </c:when>
            <c:otherwise>
                <a class="nav-link inactive-tab" href="/admin/reportList?check=3">전체 신고/문의</a>
            </c:otherwise>
        </c:choose>
    </li>
</ul>
						 		 	 </tr>
														<tr>
															<td>제목</td>
															<td>등록일</td>
															<td>답변상태</td>
														</tr>
													<c:forEach items="${list}" var="report">
														<tr>
															<td><a href="/admin/reportContent?ma_num=${report.ma_num}">${report.ma_title}</a></td>
															<td><fmt:formatDate value="${report.ma_reg_date}" pattern="yyyy-MM-dd"/></td>
															<td>${report.readcheck}</td>
														</tr>
													</c:forEach>
													</table>
														      					   <div id="graph-container">
												<c:if test="${count>0}">
												<c:if test="${startPage>10}">
										        	<a href="/admin/reportList?pageNum=${startPage-10}&check=${check}">[이전]</a>
												</c:if>
												<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
										        	<a href="/admin/reportList?pageNum=${i}&check=${check}">[${i}]</a>
												</c:forEach>
													<c:if test="${endPage<pageCount}">
										        	<a href="/admin/reportList?pageNum=${startPage+10}&check=${check}">[다음]</a>
												</c:if>
											</c:if>

		

												</div>
			
			   </td>
		</table>
      
 
</div>
</body>
</html>
<%@ include file="../footer.jsp"%>		
		
		
		
		