<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<table>
	<tr>
		<td>제목</td>
		<td>등록일</td>
		<td></td>
	</tr>
	<c:forEach items="${list}" var="notice">
		<form action="/admin/noticedelete" method="post">
			<tr>
				<td><a href="/admin/noticeContent?num=${notice.n_num}">${notice.n_title}</a></td>
				<td><fmt:formatDate value="${notice.n_reg_date}"
						pattern="yyyy-MM-dd" /></td>
				<td><input type="hidden" name="n_num" value="${notice.n_num}">
					<input type="submit" value="삭제"></td>
			</tr>
		</form>
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
<button onclick="window.location='/admin/noticeForm'">글작성</button>
<jsp:include page="/WEB-INF/views/footer.jsp" />