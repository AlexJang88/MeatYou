<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<a href="/admin/reportList?check=1">미답변 신고/문의 </a>
<a href="/admin/reportList?check=2">답변 신고/문의 </a>
<a href="/admin/reportList?check=3">전체 신고/문의 </a>

	<table>
		<tr>
			<td>제목</td>
			<td>등록일</td>
			<td>답변상태</td>
		</tr>
	<c:forEach items="${list}" var="report">
		
		<tr>
			<td><a href="/admin/reportContent?ma_num=${report.ma_num}">${report.ma_title}</a></td>
			<td><fmt:formatDate value="${report.ma_reg_date}" pattern="yyyy-MM-dd"/></td>
			<td>
				<form action="/admin/ReplyReport" method="post">
				<input type="hidden" name="ma_num" value="${report.ma_num}">
				<input type="hidden" name="check" value="${check}" >
				<c:if test="${report.ma_status==1000}">
				<input type="submit" value="답변하기">
				</c:if>
				<c:if test="${report.ma_status==1001}">
					답변 완료
				</c:if>
				</form>
			</td>
		</tr>
	</c:forEach>
	</table>

<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/admin/noticeList?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/admin/noticeList?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/admin/noticeList?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>
<jsp:include page="/WEB-INF/views/footer.jsp" />