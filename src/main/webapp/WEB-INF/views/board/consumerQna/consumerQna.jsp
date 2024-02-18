<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../header.jsp" %>
    <link rel="stylesheet" href="/resources/qna/css/cssconsumerQna.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> 
  
		  <div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
		    <h2  class="out-table">
		   고객의 소리 
		  	</h2>
				</div>
<div class="main-container" >  
    <table class="main-table"  >
            <td class="graph-and-summary">
								<c:if test="${count==0}">
									<table  class="summary-table" >
									   <tr>
									      <td align="center">등록된 문의사항이 없습니다!</td>
									   </tr>
									</table>
								</c:if>
								
								<c:if test="${count >  0}">	
									<table  class="summary-table" >
										<tr  >
											<td >글 번호</td>
											<td >글 제목</td>
											<td  >질문자</td>
											<td >작성 날짜</td>
										</tr>
										<c:forEach var="Qna" items="${list}">
											<tr  >
												<td>${Qna.r}</td>
												<td><a href="/board/userContent?ma_num=${Qna.ma_num}&ma_ref=${Qna.ma_ref}&pageNum=${pageNum}">${Qna.ma_title}</a></td>
												<td>${Qna.ma_m_id}</td>
												<td><fmt:formatDate value="${Qna.ma_reg_date}" pattern="yyyy-MM-dd" /></td>
											</tr>
										</c:forEach>	
									</table>
								</c:if>
									 		  	<tr>
									 		  		<td>
									 		  		<a href="/board/questionCA">문의글쓰기</a> <br/>
									 		  		</td>
									 		  	</tr>
	
									 		  	<tr>
									 		  		<td>
									 		  	<c:if test="${m_Status ge 2000 and m_Status le 7000}">
									 		  	<a href="/board/sellerQna">판매자용 문의하기</a> <br/>
														</c:if>
									 		  		</td>
									 		  	</tr>
	
 <tr><td>
					      					  		 <div id="graph-container">
														<c:if test="${count>0}">
																<c:if test="${startPage>10}">
														        	<a href="/board/consumerQna?pageNum=${startPage-10}">[이전]</a>
																</c:if>
																<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
														        	<a href="/board/consumerQna?pageNum=${i}">[${i}]</a>
																</c:forEach>
																	<c:if test="${endPage<pageCount}">
														        	<a href="/board/consumerQna?pageNum=${startPage+10}">[다음]</a>
																</c:if>
															</c:if>
													</div>
													</td>
	</tr>										
		</table>
      
 
</div>

</head>
<body>

<%@ include file="../../footer.jsp" %>
 