<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>  
<meta charset="UTF-8">
<title>여기는 품목확장</title>
</head>
<body>
<h2>여기는 품목확장 결제페이지</h2>
<a href="/customers/customer">홈으로</a> <br/>
<h3 align="center">${id} 판매자님의 아이디로 품목확장 1회가 결제됩니다.</h3> 
<h3 align="center">* 결제 후 상품목록 페이지로 이동하여 원하시는 상품을 추가해주세요..!</h3> 

<table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">		
	<tr height="30"> 
		<td width="300" align="center">결제자이름</td>
		<td width="200" align="center">상품종류</td>
		<td width="300" align="center">결제금액</td>
		<td width="200" align="center">기간</td>				
		<td width="200" align="center">환불여부</td>				
		<td width="100" align="center">결제하기</td>			
	</tr>
	
	 <tr height="30"> 
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
      <input type="hidden" id="id" name="p_m_id" value="${id}">	
      <input type="hidden" id="co_num" name="co_num" value="${co_num}">	
      <input type="hidden"id="quantity" name="quantity" value="${quantity}">	
		<td width="300" align="center">${id}</td>
		<td width="200" align="center">품목확장 1건</td>
		<td width="300" align="center">50,000</td>
		<td width="200" align="center">1개월 </td>				
		<td width="200" align="center">불가</td>					
		<td align="center" >   
			<input type="button" id="btn_kakao-paytwo" value="결제하기">
		 </td> 
	 </tr>	
</table>	

<script>
   $("#btn_kakao-paytwo").click(function(){
      var totalpay ="50000";  
      var quantity="1";
      var co_num=$('#co_num').val();
      var co_name = '품목결제('+quantity+'건)';
      
      console.log('totalpay:'+totalpay);
      console.log('quantity:'+quantity);
      console.log('co_num:'+co_num);
      console.log('co_name:'+co_name);
      kakao(totalpay,quantity,co_num,co_name);
   });  
      function kakao(totalpay,quantity,co_num,co_name){
      // 카카오페이 결제전송
      $.ajax({
         type:'POST'
         ,url:'/kakaopay/readytwo'
         ,data: {
          totalpay:totalpay,
          quantity:quantity,
          co_num:co_num,
          co_name:co_name
         }
         ,success:function(response){
             alert(response.next_redirect_pc_url);
            location.href = response.next_redirect_pc_url;         
         }
      })
      }
      // 버튼 클릭이벤트 해제
   
</script>

</body>
</html>