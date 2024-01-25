<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="../header.jsp"%>
<table>
	<tr>
		<td>제목</td>
		<td>${dto.n_title}</td>
		<td><a href="/admin/noticeUpdate?num=${dto.n_num}">수정하기</a> <a
			href="/admin/noticeDelete?num=${dto.n_num}">삭제하기</a></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="2">${dto.n_content}</td>
	</tr>
	<tr>
		<td>${notice.n_title}</td>
		<td colspan="2"><fmt:formatDate value="${notice.n_reg_date}"
				pattern="yyyy-MM-dd" /></td>
	</tr>
</table>
<%@ include file="../footer.jsp"%>