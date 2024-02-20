<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
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
    <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script> <!-- jQuery 라이브러리 로드 -->
    <link rel="stylesheet" href="/resources/customers/stock.css">
    <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
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
		 	
			 				<c:if test="${stockcount==0}">
		    <table  >
		        <tr>
		            <td align="center">등록된 상품이 없습니다!</td>
		        </tr>
		    </table>
		</c:if>
			
		<c:if test="${stockcount >=  0}">		
			<h3 align="center">전체 상품 목록 : ${stockcount} 건 </h3> 
			<table >		
				<tr height="30"> 				
					<td width="300" align="center">썸네일 사진</td>
					<td width="200" align="center">상품 번호</td>
					<td width="300" align="center">제품 이름</td>
					<td width="200" align="center">판매 상태</td>
					<td width="100" align="center">등록 재고</td>				
					<td width="100" align="center">재고 변경</td>	
					<td width="100" align="center">변경하기</td>	
					<td width="100" align="center">판매량</td>									
					<td width="100" align="center">남은재고</td>									
														
				</tr>				
				<c:forEach var="product" items="${stocklist}" varStatus="loopStatus">
					<c:set var="i" value="${loopStatus.index}" />			 			 			   				             		
					<c:set var="nam" value="${product.stock - aree[i]}" />			 			 			   				             		
            		 <tr align="center">
            		 	<td><img src="<%= request.getContextPath() %>/resources/file/product/${product.p_num}/${product.thumb}/" alt="썸네일"></td>
            		 	<td>${product.p_num}</td>
            		 	<td>${product.p_name}</td>
            		 	<td>
							 <c:choose>
							   <c:when test="${product.p_status == 0}">판매중</c:when>
							   <c:when test="${product.p_status == 1}">판매중(유료결제)</c:when>
							   <c:when test="${product.p_status == 2}">판매대기</c:when>
							   <c:when test="${product.p_status == 3}">판매종료</c:when>							       
							 </c:choose>
						</td>
            		 	<td>${product.stock}</td>            		 	
            		 		<form action="/customers/stockPro" method="post"  >
            		 			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            		 			<input type="hidden" name="p_m_id" value="${memId}">
            		 			<input type="hidden" name="pd_p_num" value="${product.pd_p_num}">         		 			
			            		 	<td> <input type="number" size="40" maxlength="30" name="stock" required="required" placeholder="변경할 재고를 입력하세요"></td>		            		 		
			            		 	<td align="center">   
						                 <input type="submit" value="변경">
						            </td> 		          	    			   				        		
             	 			</form>
             	 		<td>${aree[i]}</td>
             	 		<td>${nam}</td>
             	 	</tr>	
				</c:forEach>
			</table>
		</c:if>
		
	
			 			
			 			
			 				
			 					
			 						
			 							
			 								
		 		  			</td>
		 		  			</tr>			
		 		   			<tr>
		 		   			<td>
		 		   				
 				
 				
 				
 				
		 		   			
		 		   			</td>
		 		   			
		 		   			</tr>
							 
							
							
							
							
 													<tr> <td>
					      					   <div id="graph-container">
					 				
					 										<c:if test="${stockcount>0}">
			<c:if test="${startPage>10}">
	        	<a href="/customers/stock?pageNum=${startPage-10}">[이전]</a>
			</c:if>
			<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
	        	<a href="/customers/stock?pageNum=${i}">[${i}]</a>
			</c:forEach>
				<c:if test="${endPage<pageCount}">
	        	<a href="/customers/stock?pageNum=${startPage+10}">[다음]</a>
			</c:if>
		</c:if>

												 
												</div>	</td> </tr>
								 
						</table>
	
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>