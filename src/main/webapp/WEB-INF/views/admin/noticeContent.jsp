<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript"  src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript"  src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css"  href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
    <link rel="stylesheet" href="/resources/admin/css/sales.css"> <!-- 외부 스타일시트 추가 -->	
  <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
    <style>
    .fontone:hover{
    	font-weight:bold;
    }
    
    .fonttwo:hover{
    	font-weight:bold;
    }
    </style>
    



<div class="bodyArea"> 
<div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2 id="getYear" class="out-table">내용</h2><br/> 
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
        <tr>
            <td class="graph-and-summary">
            	    
                
			               <table class="summary-table">
                    
                        <tr>
                            <td>제목</td>
                           <td colspan="2" >${dto.n_title}</td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td colspan="2">${dto.n_content}</td>
                        </tr>
                        <tr>
                            <td>${notice.n_title}</td>
                            <td colspan="2"><fmt:formatDate value="${notice.n_reg_date}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                            <tr>
                            
                            <td colspan="3" ><div style="margin-right:0;"> 
                            	<a href="/admin/noticeUpdate?num=${dto.n_num}" class="fontone" >수정하기</a>
                            <a href="/admin/noticeDelete?num=${dto.n_num}"   class="fonttwo">삭제하기</a>
                            </div>
                            </td>
                            
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
<%@ include file="../footer.jsp"%>