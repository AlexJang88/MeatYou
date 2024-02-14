<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ include file="../../header.jsp" %>
<head>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<style>
	    .heartHim {
        background-color: transparent; /* 투명 배경으로 설정 */
        border: none; /* 테두리 제거 */
        cursor: pointer;
        outline: none; /* 클릭된 상태의 외곽선 제거 */
        font-size: inherit; /* 폰트 크기를 상속받음 */
        padding: 0; /* 내부 여백 제거 */
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-family: 'YourTextureFont', sans-serif; /* 텍스쳐 폰트로 변경 */
        /* 그 외 다른 스타일링 속성 추가 가능 */
    }
	.heartHim:hover:{
		font-weight: bold;
		
	}
</style>
<script>
// 삭제 메서드
/* 장바구니 삭제 버튼 */
// JavaScript 코드 (페이지 내에 추가)

$(document).on("click", ".delete_btn", function(e) {
    e.preventDefault();

    // 현재 클릭된 버튼이 속한 행에서 ppic_num 값을 가져옴
    const ppic_num = $(this).closest("tr").find(".ppic_num").val();

    // AJAX를 사용하여 삭제 요청 보내기
    $.ajax({
        type: "POST",
        url: "/member/deletePick",
        data: {
            ppic_num: ppic_num
        },
        success: function(response) {
            // 성공적으로 삭제된 경우 페이지 새로고침
            location.reload();
        },
        error: function(error) {
            console.log("Error:", error);
            // 오류가 발생한 경우 적절히 처리
        }
    });
});
 
</script>
</head>
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">내가 관심있는상품 </h1>
    </div>
</div>

<div class="panel panel-default">
    <div class="panel-heading">
내가 관심있는상품
    </div>

    <div class="panel-body">
        <!-- 수량 조절 폼 -->
 <!--        <form action="/updateQuantity" method="post" onsubmit="submitForm();"> -->
          <table class="table table-striped table-bordered table-hover">
    <thead>
        <tr>
            <th>상품이름 </th>
            <th>상품사진</th>
            <th>매장 위치</th>
            <th>판매 시작 날짜</th>
            <th>판매 종료 날짜 </th>
            <th>상품상태</th>
            <th>판매자아이디</th>
            <th>가격</th>
            <th>삭제</th>
        </tr>
    </thead>
    <tbody>
    
        <c:forEach var="item" items="${pPickList}">         
            <tr>
                <td><c:out value="${item.p_name}" /></td>
        
                <td><c:out value="${item.thumb}" /></td>
                <td>
           		<c:out value="${item.cus_address1}" /> <c:out value="${item.cus_address2}" />  
                </td>
                  
                  <td>
                   <c:out value="${item.startdate.substring(0, 10)}" />
              </td>

          		   
          		   
                  
                  <td>    <c:out value="${empty item.enddate.substring(0, 10) ? '미지정' : item.enddate.substring(0, 10)}" /></td>
                <%--   <td><c:out value="${item.p_status}" /></td> --%>
                <td>
				    <c:choose>
				        <c:when test="${item.p_status eq 0}">판매중</c:when>
				        <c:when test="${item.p_status eq 1}">판매중 (유료)</c:when>
				        <c:when test="${item.p_status eq 2}">재고가 부족해요 조금만기다려주세용 !</c:when>
				        <c:when test="${item.p_status eq 3}">당장은 판매가 종료되었어요 ㅠㅠ</c:when>
				        <c:otherwise>알 수 없는 상태</c:otherwise>
				    </c:choose>
				</td>
                  <td>
  				  <form action="/member/pickInsert" method="post" class="pick_mem_Insert">
		                    <input type="hidden" name="ppic_num" value="${item.ppic_num}" />
			                        <input type="hidden" name="pm_m_id" value="${item.pm_m_id}" /> 
			                           <input type="hidden"  name="ppic_m_id"  value="${item.ppic_m_id}"/> 
		               					<input type="hidden"  name="ppic_p_num"  value="${item.ppic_p_num}"/> 
             	  	  	  <input type="hidden"  name="p_num"  value="${item.p_num}"/>   
             	  	  	  <input type="hidden"  name="pm_num"  value="${item.pm_num}"/>   
             	  	  	  
                   		          <input type="hidden"  name="pm_m_id"  value="${item.ppic_m_id}"/> 
                      <input type="hidden"  name="pm_c_id"  value="${item.p_m_id}"/>  
                      <input type="hidden"  name="p_m_id"  value="${item.p_m_id}"/>  
                        <button type="submit"  name="pm_c_id" class="heartHim">${item.p_m_id}</button>
                    </form>
  							
  					
  					
                  		<%-- 	<c:out value="${item.p_m_id}" /> --%>
               		 
                  	</td>
                  <td><c:out value="${item.p_price}" /></td>
                <td>
                    <form action="deleteP_item" method="post" class="quantity_delete_form">
                        <input type="hidden" name="ppic_num" value="${item.ppic_num}" />
                              <input type="hidden"  class="ppic_num"  value="${item.ppic_num}"/> 	
               				 	<input type="hidden"  class="ppic_p_num"  value="${item.ppic_p_num}"/>  
             			  	  <input type="hidden"  class="p_num"  value="${item.p_num}"/>  
                   
                        <button type="submit" class="delete_btn">삭제</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<!-- 페이징 -->
<div class="pagination">
    <c:if test="${page > 1}">
        <a href="?page=${page - 1}&pageSize=${pageSize}">&laquo; 이전</a>
    </c:if>

    <c:forEach var="pageNumber" begin="1" end="${totalPage}">
        <c:choose>
            <c:when test="${pageNumber == page}">
                <span class="current">${pageNumber}</span>
            </c:when>
            <c:otherwise>
                <a href="?page=${pageNumber}&pageSize=${pageSize}">${pageNumber}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${page < totalPage}">
        <a href="?page=${page + 1}&pageSize=${pageSize}">다음 &raquo;</a>
    </c:if>
</div>
    </div>
</div>
<%@ include file="../../footer.jsp" %>