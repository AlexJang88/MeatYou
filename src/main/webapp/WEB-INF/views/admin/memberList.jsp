<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
    <link rel="stylesheet" href="/resources/admin/css/sales.css"> <!-- 외부 스타일시트 추가 -->	
   <script>
        $(document).ready(function () {
            // Add a click event handler for the menu items with sub-menus
            $('.vertical-menu-item a').click(function () {
                // Toggle the collapse state when the menu item is clicked
                $(this).next('.collapse').collapse('toggle');
            });
        });
    </script>
 
<div style="display: flex; margin-top: 40px; margin-bottom: 50px;">
    <h2 id="getYear" class="out-table">
    
    	<c:if test="${check==1}">
    	회원목록 조회(일반)
    	</c:if>
    	<c:if test="${check==2}">
    	회원목록 조회(판매자)
    	</c:if>
    	
    	<c:if test="${check==3}">
    	판매자 승인대기
    	</c:if>
    	
    	<c:if test="${check==4}">
    	판매자(유료회원)목록
    	</c:if>
    	</h2>
</div>

<div class="main-container" >
    <div class="category-menu">
        <!-- Your category menu content -->
        <div class="vertical-menu">
            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#mem" id="bigfont">회원</a>
                <div id="mem" class="collapse">
                    <a href="/admin/memberlist?check=1" class="btn" id="smallfont" >회원목록 조회(일반)</a><br/>
                    <a href="/admin/memberlist?check=2" class="btn" id="smallfont" >회원목록 조회(판매자)</a><br/>
                    <a href="/admin/memberlist?check=3" class="btn" id="smallfont" >판매자 승인대기</a><br/>
                    <a href="/admin/memberlist?check=4" class="btn" id="smallfont" >판매자(유료회원)목록</a><br/>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" data-toggle="collapse" data-target="#pro" id="bigfont">상품</a>
                <div id="pro" class="collapse">
                    <a href="/admin/productList" class="btn" id="smallfont" >상품 목록 보기</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#money" id="bigfont">정산</a>
                <div id="money" class="collapse">
                    <a href="/admin/sales" class="btn" id="smallfont" >매출 보기</a><br/>
                    <a href="/admin/reckon" class="btn" id="smallfont" >정산내역 확인</a>
                </div>
            </div>

            <div class="vertical-menu-item">
                <a href="#" class="btn" class="btn" data-toggle="collapse" data-target="#check" id="bigfont">관리자 체크</a>
                <div id="check" class="collapse">
                    <a href="/admin/noticeList" class="btn" id="smallfont" > 공지 사항</a><br/>
                    <a href="/admin/reportList" class="btn"  id="smallfont" >신고글 보기</a>
                </div>
            </div>
        </div>
    </div>
      <table class="main-table"  >
            <td class="graph-and-summary">
		 		  <table class="summary-table" >
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
								<td>
									<c:set var ="reg_date" value="${d.m_reg_date}"/>
									${fn:substring(reg_date,0,10) }
								</td>
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
										<c:if test="${check==4}">
											<td></td>
										</c:if>
							</tr>
							</form>
							</c:forEach>
						</table>
	
 
					      					   <div id="graph-container">
															<c:if test="${count>0}">
															<c:if test="${startPage>10}">
													        	<a href="/admin/memberlist?pageNum=${startPage-10}&check=${check}">[이전]</a>
															</c:if>
															<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
													        	<a href="/admin/memberlist?pageNum=${i}&check=${check}">${i}</a>
															</c:forEach>
																<c:if test="${endPage<pageCount}">
													        	<a href="/admin/memberlist?pageNum=${startPage+10}&check=${check}">[다음]</a>
															</c:if>
														</c:if>
												</div>
			
			   </td>
		</table>
      
 
</div>

<%@ include file="../footer.jsp" %>