<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form action="/admin/pschange" method="post">
<input type="hidden" name="check" value="${check}"/>
<input type="hidden" name="p_num" value="${p_num}">
<textarea name="memo">
	
</textarea>
<input type="submit" value="작성">
</form>