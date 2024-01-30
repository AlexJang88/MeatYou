<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<%@ include file="../sort.jsp" %> 

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row --> 
				<div class="row"> 

					<!-- section title --> 
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">검색한 ${search}의 결과</h3>
							<div class="section-nav">
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab1" class="tab-pane active">
									<div class="products-slick" data-nav="#slick-nav-1">
										<!-- product -->
										<c:forEach var="sear" items="${searchList}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/img/product01.png" alt="">
												<div class="product-label">
													<span class="sale" ><a>목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<p class="product-category">Category1 , Category2</p>
												<h3 class="product-name"><a href="../main/product?p_num=${sear.p_num}">${sear.p_name}</a></h3>
												<h4 class="product-price">${sear.p_price}원</h4>
												<div class="rating-avg">${sear.star}
														<c:if test="${sear.star <= 5.0 && sear.star > 4.6}">
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
												<div class="product-btns">
													<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">add to wishlist</span></button>
													<button class="add-to-compare"><i class="fa fa-exchange"></i><span class="tooltipp">add to compare</span></button>
													<button class="quick-view"><i class="fa fa-eye"></i><span class="tooltipp">quick view</span></button>
												</div>
											</div>
											<div class="add-to-cart">
												<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
											</div>
										</div>
										</c:forEach>
										<!-- /product -->

									</div>
									<div id="slick-nav-4" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- /Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


		<h1></h1><br /><br /><br /><br /><br /><br /><br />


<%@ include file="../footer.jsp" %>
