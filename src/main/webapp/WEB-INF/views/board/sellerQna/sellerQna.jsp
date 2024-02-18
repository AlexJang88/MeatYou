<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
<!DOCTYPE html>

    <link rel="stylesheet" href="/resources/qna/css/sallerQna.css"> <!-- 외부 스타일시트 추가 -->	

<html>
<head>
<meta charset="UTF-8">
    <%@ include file="../../header.jsp" %>
</head>
<body>

 
  
  <div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
		    <h2  class="out-table">
		   판매자- 관리자
		  	</h2>
				</div>
<div class="main-container" >  
    <table class="main-table"  >
            <td class="graph-and-summary">
            
							<c:if test="${count==0}">
							
									<table class="summary-table">
									   <tr>
									      <td >등록된 문의사항이 없습니다!</td>
									   </tr>
									</table>
								</c:if>
								
								<c:if test="${count >  0}">	
								
									<table class="summary-table">
										<tr height="30">
											<td  >글 번호</td>
											<td >글 제목</td>
											<td  >질문자</td>
											<td  >작성 날짜</td>
										</tr>
										<c:forEach var="Qna" items="${list}">
											<tr  >
												<td>${Qna.r}</td>
												<td><a href="/board/sellerContent?ma_num=${Qna.ma_num}&ma_ref=${Qna.ma_ref}&pageNum=${pageNum}">${Qna.ma_title}</a></td>
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
									 		  		<tr><td>
									 		  		<a href="/board/questionSA">판매자관리자에게문의글쓰기</a> <br/>
									 		  		</td>
									 		  		
									 		  		</tr>
									 		  	</tr>
									 		  	
									 		  	
									 		  	
	
												 <tr>
												 <td>
					      					  		 <div id="graph-container"> 
					      					  		 
															      					  		 <c:if test="${count>0}">
													<c:if test="${startPage>10}">
											        	<a href="/board/sellerQna?pageNum=${startPage-10}">[이전]</a>
													</c:if>
													<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
											        	<a href="/board/sellerQna?pageNum=${i}">[${i}]</a>
													</c:forEach>
														<c:if test="${endPage<pageCount}">
											        	<a href="/board/sellerQna?pageNum=${startPage+10}">[다음]</a>
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