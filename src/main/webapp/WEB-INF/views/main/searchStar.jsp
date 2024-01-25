<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<%@ include file="../searchSort.jsp" %>

<!DOCTYPE html>

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<!-- ASIDE --> 
					<div id="aside" class="col-md-2">
					</div>
					<!-- /ASIDE -->

					<!-- STORE -->
					<div id="store" class="col-md-9">
						<!-- store top filter -->
						<div class="store-filter clearfix">
							<div class="store-sort">
							</div>
						</div>
						<!-- /store top filter -->
						<h3 class="title">검색한 ${search}의 결과</h3>
						
						<!-- store products -->
						<div class="row">
							<!-- product -->
							<c:forEach var="sear" items="${searchListStar}">
							<div class="col-md-4 col-xs-6">
								<div class="product">
									<div class="product-img">
										<img src="/resources/img/product01.png" alt="">
										<div class="product-label">
											<span class="sale" ><a>목록확인</a></span>
											<span class="sale" ><a>상품설명</a></span>
											<span class="sale" ><a>상품평점</a></span>
										</div>
									</div>
									<div class="product-body">
										<a href="../main/product?p_num=${sear.p_num}&p_m_id=${sear.p_m_id}"><h3 class="product-name">${sear.p_name}</h3></a>
										<a href="../main/product?p_num=${sear.p_num}&p_m_id=${sear.p_m_id}"><h4 class="product-price">${sear.p_price}원</h4></a>
										<ul class="product-links">
											<a href="../main/product?p_num=${sear.p_num}&p_m_id=${sear.p_m_id}"><li><h6>${sear.category1} / ${sear.category2} / ${sear.category3}</h6></li></a>
										</ul>
										<div class="rating-avg">${sear.star}
														<c:if test="${sear.star == 5.0 && sear.star > 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${sear.star >= 4.0 && sear.star < 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${sear.star >= 3.0 && sear.star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${sear.star >= 2.0 && sear.star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${sear.star > 1.0 && sear.star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${sear.star >= 0.0 && sear.star < 0.9}">
															<div class="rating-stars">
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
													</div>
										<c:if test="${m_id == null}">
											<div class="product-btns">
												찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
												리뷰 : ${sear.reviewAllCNT}개
											</div>
										</c:if>

										<c:if test="${m_id != null}">
											<div class="product-btns">
												<form class="product-btns" action="pickInsertSearchStar" method="post">
													<input type="hidden" name="ppic_m_id" 	 value="${sear.ppic_m_id}">
													<input type="hidden" name="ppic_p_num" 	 value="${sear.ppic_p_num}">
													<input type="hidden" name="searchOption" value="${searchOption}">
													<input type="hidden" name="search" 		 value="${search}">
													찜하기<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
													리뷰 : ${sear.reviewAllCNT}개
												</form>
											</div>
										</c:if>
									</div>
									<c:if test="${m_id != null}">
										<div class="add-to-cart">
										<form action="ShoppingCartInsertSearchStar" method="post">
											<input type="hidden" name="m_id" value="${m_id}">
											<input type="hidden" name="p_num" value="${sear.p_num}">
											<input type="hidden" name="shop_quantity" value="1">
											<input type="hidden" name="searchOption" value="${searchOption}">
											<input type="hidden" name="search" value="${search}">
											<button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
										</form>
										</div>
										
									</c:if>
									<c:if test="${m_id == null}">
										<div class="add-to-cart">
											<button class="add-to-cart-btn" onclick="location.href='/member/customLogin'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
										</div>
									</c:if>
								</div>
							</div>
							</c:forEach>
							<!-- /product -->
						</div>
						<!-- /store products -->

						<!-- store bottom filter -->
						<div class="store-filter clearfix">
							<span class="store-qty">Showing 20-100 products</span>
							<ul class="store-pagination">
								<c:if test="${count > 0}"> 
									<c:if test="${startPage > 10}">
										<li><a href="/main/searchStar?searchOption=total&search=${search}&pageNum${startPage-10}">[이전]</a></li>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<li><a href="/main/searchStar?searchOption=total&search=${search}&pageNum=${i}">${i}</a></li>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<li><a href="/main/searchStar?searchOption=total&search=${search}&pageNum${startPage+10}">[다음]</a></li>
									</c:if>	
								<li><a href="#"><i class="fa fa-angle-right"></i></a></li>
								</c:if>
							</ul>
						</div>
						<!-- /store bottom filter -->
					</div>
					<!-- /STORE -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

<%@ include file="../footer.jsp" %>
	</body>
</html>
