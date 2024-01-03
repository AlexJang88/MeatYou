<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<a href="/admin/memberlist?check=1">회원목록 조회(일반)</a>
<a href="/admin/memberlist?check=2">회원목록 조회(판매자)</a>
<a href="/admin/memberlist?check=3">판매자 승인대기</a>
<a href="/admin/memberlist?check=4">판매자(유료회원)목록</a>
<br />
<a href="/admin/sales">매출보기</a>
<br>
	
	
			<table>
				<tr>
					<td>아이디</td>
					<td>이름 </td>
					<td>이메일 </td>
					<td>연락처</td>
					<td>가입일</td>
					<td>현재 등급</td>
					<td>바꿀등급</td>
				</tr>
		<c:forEach var="d" items="${list}">
		<form action="/admin/statChange" method="post">
		<input type="hidden" name="check" value="${check}">
		<input type="hidden" name="m_id" value="${d.m_id}">
		<input type="hidden" name="pageNum" value="${pageNum}">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<tr>
					<td>${d.m_id}</td>
					<td>${d.m_name} </td>
					<td>${d.email} </td>
					<td>${d.phone}</td>
					<td>${d.m_regDate}</td>
					<td>${d.mstat_detail}</td>
					<c:if test="${check==1}">
						<td>
							<select name="m_status">
								<option value="1001">일반회원 
								<option value="1002">우수회원 
								<option value="1003">단골회원
							</select>
							<input type="submit" value="변경"> 
						</td>
					</c:if>
					<c:if test="${check==2}">
						<td>
							<select name="m_status">
								<option value="2001">일반판매자 
								<option value="2002">상위노출결제 판매자
								<option value="2003">품목결제 판매자
								<option value="2004">품목,상위노출결제 판매자
							</select>
							<input type="submit" value="변경">
						</td>
					</c:if>
						<c:if test="${check==3}">
						<td>
							<select name="m_status">
								<option value="2001">판매자 승인  
								<option value="1051">판매자 승인보류 
								<option value="1052">판매자 승인취소 
							</select>
							<input type="submit" value="전송">
						</td>
					</c:if>
				</tr>
				</form>
				</c:forEach>
			</table>
		
	
	<c:if test="${count>0}">
			<c:if test="${startPage>10}">
	        	<a href="/admin/memberlist?pageNum=${startPage-10}&check=${check}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/admin/memberlist?pageNum=${i}&check=${check}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/admin/memberlist?pageNum=${startPage+10}&check=${check}">[다음]</a>
			</c:if>
		</c:if>
<%@ include file="../footer.jsp" %>