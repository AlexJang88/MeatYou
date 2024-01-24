<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>  
<meta charset="UTF-8">
<title>파워링크 결제페이지</title>
</head>
<body>
<h2>파워링크 결제페이지</h2>
<a href="/customers/customer">홈으로</a> <br/>

  <h3 align="center">해당 상품으로 결제를 진행합니다!</h3> 
  <h3 align="center">비용은 클릭당 100원으로 진행됩니다!</h3> 
   <table border="1" width="1300" cellpadding="0" cellspacing="0" align="center">            
      <tr height="30"> 
         <td width="300" align="center">썸네일 사진</td>
         <td width="200" align="center">상품번호</td>
         <td width="300" align="center">제품이름</td>                        
         <td width="200" align="center">현재상태</td>            
         <td width="200" align="center">클릭수</td>                     
         <td width="100" align="center"> 비용</td>                  
         <td width="100" align="center"> 결제</td>                  
      </tr>         
            <input type="hidden" id="p_num" name="p_num" value="${payMentItem.p_num}">   
            <input type="hidden" id="totalpay" name="clickpay" value="${clickpay}">   
            <input type="hidden" id="id" name="id" value="${id}">                 
            <input type="hidden" id="quantity" name="quantity" value="${quantity}">                 
            <input type="hidden" id="co_num" name="co_num" value="${co_num}">                 
         <tr align="center">
              <td>${payMentItem.thumb}</td>
              <td>${payMentItem.p_num} </td>
              <td>${payMentItem.p_name}</a></td>
              <td>
              <c:choose>
               <c:when test="${payMentItem.p_status == 0}">판매중</c:when>
               <c:when test="${payMentItem.p_status == 1}">판매중(품목결제 상품)</c:when>                            
               <c:when test="${payMentItem.p_status == 2}">판매대기</c:when>
               <c:when test="${payMentItem.p_status == 3}">판매종료</c:when>                            
              </c:choose>
            </td>            
            <td valign="top">
                <c:choose>
                  <c:when test="${clickpay == 10000}">  110회 </c:when>
                  <c:when test="${clickpay == 30000}"> 330회</c:when>
                  <c:when test="${clickpay == 50000}"> 565회</c:when>
                  <c:when test="${clickpay == 100000}">1160회</c:when>                                        
                  <c:when test="${clickpay == 200000}">2400회</c:when>                                        
               </c:choose>
            </td>
            <td>${clickpay}원</td>   
                <td>                           
                <input type="button" id="btn_kakao-pay" value="결제하기">
             </td>                 
            </tr>
                                     
      </table>   
      <h3 align="center">파워링크 결제 후 수정 및 환불은 불가합니다..!</h3> 
</body>


<script>
   $("#btn_kakao-pay").click(function(){
      var totalpay =$('#totalpay').val();
      var p_num=$('#p_num').val();
      var quantity=$('#quantity').val();
      var co_num=$('#co_num').val();
      var co_name = '파워링크('+quantity+'회)';
      
      console.log('totalpay:'+totalpay);
      console.log('p_num:'+p_num);
      console.log('quantity:'+quantity);
      console.log('co_num:'+co_num);
      console.log('co_name:'+co_name);
      kakao(totalpay,p_num,quantity,co_num,co_name);
   });  
      function kakao(totalpay,p_num,quantity,co_num,co_name){
      // 카카오페이 결제전송
      $.ajax({
         type:'POST'
         ,url:'/kakaopay/ready'
         ,data: {
          totalpay:totalpay,
          p_num:p_num,
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
</html>