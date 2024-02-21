<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
 
  
<html>
<head>
<style>
@charset "utf-8";


 body {
            font-family: 'Roboto', Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
        }



#bigfont {
    font-weight: bold;
    font-size: 14px; /* You can adjust the font size as needed */
    font-family: 'Poppins', sans-serif; /* Change the font family as needed for bigfont */
    border: 1px solid #ddd;
          width: 140px;
      border-right:none;
         box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
         margin-bottom:0;
      
}

/* Add this style for the smallfont element */
#smallfont {
    font-size: 12px; /* You can adjust the font size as needed */
    font-family: 'Quicksand', sans-serif; /* Change the font family as needed for smallfont */
}
 .vertical-menu {
            order: -1;
            display: flex;
            flex-direction: column;
            align-items: flex-start;
            margin-right: 20px;
            width: 100%;
            margin-top: 0;
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
          
        }

        .vertical-menu a {
            margin-bottom: 10px;
            text-align: left;
        }


        .btn {
            display: inline-block;
            padding: 8px 20px;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s;
        }
 .vertical-menu a.btn {
    font-size: 14px; /* You can adjust the font size as needed */
}

/* Add this style for the lower-level menu items (sub-menu items) */
.vertical-menu a.btn + .collapse a.btn {
    font-weight: normal;
    font-size: 14px; /* You can adjust the font size as needed */
}
   .category-menu {
            width: 8%; /* Adjust the width as needed */
            height: 100%; /* Adjust the height as needed */
            position: relative;
            margin-right:0;
            left:18%;
        }

 .main-container{
   display: flex;
        justify-content: center; /* Center the children horizontally */
        align-items: flex-start; /* Align the children at the top */
        margin-top: 40px;
        margin-bottom: 50px;
 }
 
       .summary-table {
       margin-top:3%;
            width: 100%;
            margin-bottom:10%;
            margin-left: auto;
            margin-right: auto;
            
                    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
            font-size: xx-small;
        }   

  .summary-table th, .summary-table td {
    font-family: 'Arial', sans-serif;
    font-size: 12px;
    border: 1px solid #ddd;
    padding: 10px;
    text-align: center;
} 
         
      .summary-table th {
            background-color: #f2f2f2;
        }

        .summary-table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        #graph-container {
        /*     width: 60%;*/
            order: 1; 
                width: 60%;
    text-align: center; margin: auto;
    margin-bottom:5%;
    margin-top:2%;
        }
         #graph-container a{
                  cursor: pointer;
            color:gray;
            font-size: medium;
         }
        
        
         select {
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        font-size: 14px;
        margin-right: 5px;
    }

    /* Style for submit button */
    input[type="submit"] {
        padding: 8px 20px;
        border: none;
        border-radius: 4px;
        background-color:lightgray;
        color: #fff;
        cursor: pointer;
    }

    input[type="submit"]:hover {
        background-color: gray;
    }
        
        
        
        
    
        .main-table {
            width: 50%;
            margin: 0 auto;
            margin-left:18%;
            height: 60%;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
           
            
        }
        
        .main-table th {
    font-family: 'Arial', sans-serif;
    font-size: 16px;
    font-weight: bold;
    background-color: #f2f2f2;
    padding: 10px;
    text-align: center;
}
.main-table td {
    font-family: 'Arial', sans-serif;
    font-size: 14px;
    padding: 10px;
    text-align: center;
}

         
 .graph-and-summary {
               width: 80%;
        margin: 0 auto; /* Center the element horizontally */
        text-align: center; /* Center the content inside the element */
       margin-left:auto;
       margin-right:auto;
       
      position: relative;
        }
       
  .out-table {
            margin: 0 auto;
            align-items: center;
            justify-content: center;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            
              font-family: 'Arial', sans-serif; /* Change the font family as needed */
    font-size: 24px; /* You can adjust the font size as needed */
    color: #333; /* Change the color as needed */
    font-weight: bold;
         
 

}


</style>
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
<meta charset="UTF-8">
<title>파워링크 결제페이지</title>
</head>
<body>

 

<div class="main-container" >
             <div class="category-menu">
                 <!-- Your category menu content -->
                       <div class="vertical-menu">
                                 <div class="vertical-menu-item">
                                     <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">상품</a>
                                     <div id="mem" class="collapse">
                                             <a href="/customers/itemUpdate" class="btn" id="smallfont" >상품 등록</a><br/>
                                                     <a href="/customers/itemList" class="btn"id="smallfont" >상품 목록</a>
                                     </div>
                                 </div>
               
                                    <div class="vertical-menu-item">
                                        <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">매출</a>
                                        <div id="pro" class="collapse">
                                <a href="/customers/profit" class="btn" id="smallfont" >매출현황</a><br/>
                                       <a href="/customers/profitItem"class="btn"id="smallfont" >월별 판매 현황</a>
                                   
                                        </div>
                                    </div>
                        
                                    <div class="vertical-menu-item">
                                        <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">재고</a>
                                        <div id="money" class="collapse">
                                            <a href="/customers/stock"  class="btn" id="smallfont" >재고현황</a><br/>
                                       <a href="/customers/onStock" class="btn"id="smallfont" >월별 판매 현황</a>
                                        </div>
                                    </div>
               
                                    
                                    <div class="vertical-menu-item">
                                        <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">회원 관리</a>
                                        <div id="check" class="collapse">
                                 <a href="/customers/consumerList" class="btn" id="smallfont" >구매회원</a><br/>
                                <a href="/customers/CouponList" class="btn"  id="smallfont" >쿠폰 보유 회원</a>
                                        </div>
                                    </div>
                                 
               
                                    <div class="vertical-menu-item">
                                        <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#pay" id="bigfont">결제</a>
                                        <div id="pay" class="collapse">
                                     
                              <a href="/customers/pay" class="btn"id="smallfont" >유료결제</a><br/>
                                <a href="/customers/powerlink" class="btn"id="smallfont" >파워링크 결제</a><br/>
                                <a href="/customers/itemplus" class="btn"id="smallfont" >품목 확장 결제</a> 
                                                 </div>
                                    </div>
                            
                                    
                                    <div class="vertical-menu-item">
                                        <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#go" id="bigfont">주문|배송</a>
                                        <div id="go" class="collapse">
                                     
                              <a href="/customers/delivering" class="btn"id="smallfont" >주문접수 및 배송현황</a><br/>
                                <a href="/customers/deliverout" class="btn"id="smallfont" > 주문 취소 </a><br/>
                                                 </div>
                                    </div> 
                                      <div class="vertical-menu-item">
                                          <a  href="#" class="btn" data-toggle="collapse" data-target="#sellerQna"id="bigfont">관리자</a>
                                          <div id="sellerQna" class="collapse">
                                      <a href="/board/sellerQna" class="btn"id="smallfont" >관리자 문의게시판</a><br/>
                                      </div>
                                    </div>
                       </div>
             </div>
      <table class="main-table"  >
            <td class="graph-and-summary">
               <table class="summary-table" >
                                 <tr>
                                      <td>
                                                   <h2>파워링크 결제페이지</h2>
            
                                         <h3 align="center">해당 상품으로 결제를 진행합니다!</h3> 
                                         <h3 align="center">비용은 클릭당 100원으로 진행됩니다!</h3> 
                                               <h3 align="center">파워링크 결제 후 수정 및 환불은 불가합니다..!</h3> 
                                  </td>
                                 </tr>
                                 
                           <tr>
                        <td>
                      
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
                             <td><img src="<%= request.getContextPath() %>/resources/file/product/${payMentItem.p_num}/${thumb}/" alt="썸네일"></td>
                             <td>${payMentItem.p_num} </td>
                             <td>${p_name}</a></td>
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
    
          
                          
                         
   
         
            </td>
      </table>
      
 
</div>


<script>
   $("#btn_kakao-pay").click(function(){
      var totalpay =$('#totalpay').val();
      var p_num=$('#p_num').val();
      var quantity=$('#quantity').val();
      var co_num=$('#co_num').val();
      var co_name = '파워링크('+quantity+'회)';
       
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
                       

<%@ include file="../footer.jsp" %>