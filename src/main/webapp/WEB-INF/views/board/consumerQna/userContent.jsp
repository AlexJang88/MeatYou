<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../header.jsp" %>
<html>
<head>
 <link rel="stylesheet" href="/resources/qna/css/userContent.css"> <!-- 외부 스타일시트 추가 -->	
 
</head>
<body>

 
 <div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
		    <h2  class="out-table">
		 소비비자님의 문의하기 
		  	</h2>
	 	</div>
 
<div class="main-container" >  

    <table class="main-table"  >
    <tr>
	<td>	
				<div class="right"> 
				<a href="/board/consumerQna?pageNum=${pageNum}"> < 목록으로</a>  
				</div>
	  </td>
</tr>
            <td class="graph-and-summary">
									
								<table  class="summary-table" >
								
											<tr  >
											<td >문의한사람</td>
											<td >문의 날짜</td>
											<td  >문의제목</td>
											<td >문의내용</td>
										</tr>
											<tr  >
												<td>${content.ma_m_id}</td>
												<td>
												<fmt:formatDate value="${content.ma_reg_date}" pattern="yyyy-MM-dd" />
												</td>
												<td>${content.ma_title}</td>
												<td>${content.ma_content}</td>
											</tr>
								</table>
								<table  class="summary-table" >
								
											<tr >
											<td >문의내용</td>
										</tr>
											<tr>
												<td>${content.ma_content}</td>
											</tr>
								</table>
					
									 		  	 
									 		  	 
									 		  	 <tr>
									 		  	 <td>
									 		  	 
																							 		  	 
														<c:if test="${answerCount <2}">
															<c:if test="${m_Status == 7999}">
																<form method="post" action="userContentPro">
																	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
																	<input type="hidden" name="ma_ref" value="${content.ma_ref}">
																	<input type="hidden" name="ma_num" value="${content.ma_num}">
																	<input type="hidden" name="pageNum" value="${pageNum}">
														
																	<table  >
																	 <tr>
																	 	<td >답글달기</td>
																	 	<td>
																		 <textarea rows="7" cols="70" name="ma_content" required="required"></textarea>
																	 	</td>
																	 </tr>
																	 <tr>
																	 	<td colspan="2"><input type="submit" value="답글달기"> </td>
																	 </tr>
																	</table>
																</form>
															
															</c:if>
														</c:if>
																							 		  	 
									 		  	 
									 		  	 
									 		  	 </td>
									 		  	 </tr>
									 		  	 
								<tr><td>	 		  	 
								<div class="graph-container" >
						  	      <c:if test="${contentView.ma_status >=1}">
						            <table  >
						                <tr>
						                    <td style="width=100%; background-color: lightgray;  ">${contentView.ma_title}</td>
						                </tr>
						                <tr>
						                    <td>${contentView.ma_content}</td>
						                </tr>
						            </table>
						        </c:if>
						    </div>

						</td>
		</table>
</div>

<body>
</html>