<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<button id="btn_kakao-pay">kakao</button>
<script>
	$(function(){
	$("#btn_kakao-pay").click(function(){
		// 카카오페이 결제전송
		$.ajax({
			type:'POST'
			,url:'/kakaopay/ready'
			,success:function(response){
				 alert(response.next_redirect_pc_url);
				location.href = response.next_redirect_pc_url;			
			}
		})
		// 버튼 클릭이벤트 해제
	})
	})	
</script>
