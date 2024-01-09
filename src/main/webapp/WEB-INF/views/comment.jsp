<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>


<div class="container">
	<div class="form-group">
		<c:if test="${sessionScope.mid == null}">
			</c:if>
			<c:if test="${sessionScope.mid == null}">
			int numId = (Integer)session.getAttribute("numId");
			String cmdCheck = (String)session.getAttribute("cmdCheck");%>
			<form action="postsComment.jsp?num=${ dto.getPost_num()%>&numId=${numId%>" >
				<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
					<tr>
						<td style="border-bottom:none;" valign="middle"><br>[${cmdCheck%>]</td>
							<input type="hidden" name="num" value="${ dto.getPost_num()%>"/>
							<td><input type="text" name = "comment" style="height:50px;" class="form-control" placeholder="상대방을 존중하는 댓글을 남깁시다."></td>
						<td><br><button type="submit" class="btn btn-success">작성</button></td>
					</tr>
				</table>
			</form>
		</c:if>
	</div>	
</div>


<div class="container">
	<div class="form-group">
		<form action="postsComment.jsp">
			<table class="table table-striped" style="text-align: center; border: 1px solid #dddddd">
				<tr>

					<hr>
	
					<hr>
				</tr>
			</table>
		</form>
	</div>	
</div>