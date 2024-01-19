<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<%@ include file="../mainMeatSort.jsp" %>

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
						
						
					<!-- section title -->
					<c:if test="${category == 1}">
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">돼지고기 목록</h3>
							<div class="section-nav">
							</div>
						</div>
					</div>
					</c:if>
					<c:if test="${category == 2}">
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">소고기 목록</h3>
							<div class="section-nav">
							</div>
						</div>
					</div>
					</c:if>
					<!-- /section title -->
						
						
						<!-- store products -->
						<div class="row">
							<!-- product -->
							<c:forEach var="meat" items="${mainMeat}">
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
										<p class="product-category">Category</p>
										<h3 class="product-name"><a href="../main/product?p_num=${meat.p_num}">${meat.p_name}</a></h3>
										<h4 class="product-price">${meat.p_price}</h4>
										<div class="rating-avg">${meat.star}
														<c:if test="${meat.star == 5.0 && meat.star > 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${meat.star >= 4.0 && meat.star < 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${meat.star >= 3.0 && meat.star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${meat.star >= 2.0 && meat.star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${meat.star > 1.0 && meat.star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${meat.star >= 0.0 && meat.star < 0.9}">
															<div class="rating-stars">
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
													</div>
										<div class="product-btns">
											<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
											<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
											<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
										</div>
									</div>
									<c:if test="${m_id != null}">
										<div class="add-to-cart">
											<input type="hidden" name="shop_m_id" value="${m_id}">
											<input type="hidden" name="shop_p_num" value="${p_num}">
											<input type="hidden" name="shop_quantity" value="1">
											<input type="hidden" name="category" value="${category}">
											<input type="hidden" name="price" value="desc">
											<button class="add-to-cart-btn" onclick="location.href='ShoppingCartInsertMainMeat?p_num=${p_num}&m_id=${m_id}&shop_quantity=1&category=${category}&price=desc'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
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
							<span class="store-qty"></span>
							<ul class="store-pagination">
							<c:if test="${count > 0}">
								<c:if test="${startPage > 10}">
									<li><a href="/main/mainMeat?category=${category}&price=desc&pageNum=${startPage-10}">[이전]</a></li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li><a href="/main/mainMeat?category=${category}&price=desc&pageNum=${i}">${i}</a></li>
								</c:forEach>
								<c:if test="${endPage > pageCount}">
									<li><a href="/main/mainMeat?category=${category}&price=desc&pageNum=${startPage+10}">[다음]</a></li>
								</c:if>	

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
