<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>


		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">파워링크</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active"><a href="../main/powerLink">파워링크 상품 더보기</a></li>
								</ul>
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
										<c:forEach var="cus" items="${cusList}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/img/product01.png" alt="">
												
												<div class="modal-dialog modal-dialog-scrollable">
  ...
</div>
												
												<div class="product-label">
													<span class="sale" ><a href="boot">목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">${cus.p_name}</a></h3>
												<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}"><h4 class="product-price">${cus.p_price}원</h4></a>
												<ul class="product-links">
													<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}"><li><h6>${cus.category1} / ${cus.category2} / ${cus.category3}</h6></li></a>
												</ul>
													<div class="rating-avg">${cus.star}
														<c:if test="${cus.star == 5.0 && cus.star > 4.6}">
														<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</a>
														</c:if>
														<c:if test="${cus.star >= 4.0 && cus.star < 4.6}">
															<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${cus.star >= 3.0 && cus.star < 3.9}">
															<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${cus.star >= 2.0 && cus.star < 2.9}">
															<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${cus.star >= 1.0 && cus.star < 1.9}">
															<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${cus.star >= 0.0 && cus.star < 0.9}">
															<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
													</div>
													
												<c:if test="${m_id == null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${cus.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='pickInsertMain?ppic_m_id=${cus.ppic_m_id}&ppic_p_num=${cus.ppic_p_num}'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${cus.reviewAllCNT}개
													</div>
												</c:if>
											</div>
										<c:if test="${m_id != null}">
											<div class="add-to-cart">
											<form action="ShoppingCartInsert2" method="post">
												<input type="hidden" name="m_id" value="${m_id}">
												<input type="hidden" name="p_num" value="${cus.p_num}">
												<input type="hidden" name="shop_quantity" value="1">
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
										</c:forEach>
										<!-- /product -->
									</div>
									<div id="slick-nav-1" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->




		<c:if test="${m_id != null}">
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">XX님 맞춤 추천</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">XX님 맞춤 추천 상품 더보기</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab2" class="tab-pane fade in active">
									<div class="products-slick" data-nav="#slick-nav-2">
										<!-- product -->
										<div class="product">
											<div class="product-img">
												<img src="../resources/img/product06.png" alt="">
												
												<a>
												<div class="modal-dialog modal-dialog-scrollable">
												  ...
												</div></a>
												
												<div class="product-label">
													<span class="sale" ><a>목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<p class="product-category">Category</p>
												<h3 class="product-name"><a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">제목 : product name goes here</a></h3>
												<h4 class="product-price">$980.00 <del class="product-old-price">$990.00</del></h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
												</div>
												<c:if test="${m_id == null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${cus.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='pickInsertMain?ppic_m_id=${cus.ppic_m_id}&ppic_p_num=${cus.ppic_p_num}'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${cus.reviewAllCNT}개
													</div>
												</c:if>
											</div>
											<c:if test="${m_id != null}">
													<div class="add-to-cart">
														<input type="hidden" name="shop_m_id" value="${m_id}">
														<input type="hidden" name="shop_p_num" value="${p_num}">
														<input type="hidden" name="shop_quantity" value="1">
														<button class="add-to-cart-btn" onclick="location.href='ShoppingCartInsert2?p_num=${p_num}&m_id=${m_id}&shop_quantity=1'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
													</div>
												</c:if>
												<c:if test="${m_id == null}">
													<div class="add-to-cart">
														<button class="add-to-cart-btn" onclick="location.href='/member/customLogin'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
													</div>
												</c:if>
										</div>
										<!-- /product -->
									</div>
									<div id="slick-nav-2" class="products-slick-nav"></div>
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
		</c:if>




		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">소고기 Best 상품</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
								<li class="active"><a href="/main/mainMeat?category=2&sale=desc">소고기 부위별 Best 상품 더보기</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab2" class="tab-pane fade in active">
									<div class="products-slick" data-nav="#slick-nav-3">
										<!-- product -->
										<c:forEach var="meat" items="${meatList}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/img/product06.png" alt="">
												<div class="product-label">
													<span class="sale" ><a>목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h3 class="product-name">${meat.p_name}</h3></a>
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h4 class="product-price">${meat.p_price}원</h4></a>
												<ul class="product-links">
													<li><h6><a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">${meat.category1} /${meat.category2}/${meat.category3}</a></h6></li>
												</ul>
													<div class="rating-avg">${meat.star}
														<c:if test="${meat.star == 5.0 && meat.star > 4.6}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${meat.star >= 4.0 && meat.star < 4.6}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${meat.star >= 3.0 && meat.star < 3.9}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${meat.star >= 2.0 && meat.star < 2.9}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${meat.star >= 1.0 && meat.star < 1.9}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${meat.star >= 0.0 && meat.star < 0.9}">
															<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
													</div>
												<c:if test="${m_id == null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${meat.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='pickInsertMain?ppic_m_id=${meat.ppic_m_id}&ppic_p_num=${meat.ppic_p_num}'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${meat.reviewAllCNT}개
													</div>
												</c:if>
											</div>
											<c:if test="${m_id != null}">
												<div class="add-to-cart">
												<form action="ShoppingCartInsert2" method="post">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${meat.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
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
										</c:forEach>
										<!-- /product -->
									</div>
									<div id="slick-nav-3" class="products-slick-nav"></div>
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





		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">돼지고기 Best 상품</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
								<li class="active"><a href="/main/mainMeat?category=1&sale=desc">돼지고기 부위별 Best 상품 더보기</a></li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->

					<!-- Products tab & slick -->
					<div class="col-md-12">
						<div class="row">
							<div class="products-tabs">
								<!-- tab -->
								<div id="tab2" class="tab-pane fade in active">
									<div class="products-slick" data-nav="#slick-nav-4">
										<!-- product -->
										<c:forEach var="fork" items="${forkList}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/img/product02.png" alt="">
												<div class="product-label">
													<span class="sale" ><a>목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}"><h3 class="product-name">${fork.p_name}</h3></a>
												<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}"><h4 class="product-price">${fork.p_price}원</h4></a>
													<div class="rating-avg">${fork.star}
													<ul class="product-links">
														<li><h6><a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">${fork.category1} /${fork.category2}/${fork.category3}</a></h6></li>
													</ul>
														<c:if test="${fork.star == 5.0 && fork.star > 4.6}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${fork.star >= 4.0 && fork.star < 4.6}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${fork.star >= 3.0 && fork.star < 3.9}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${fork.star >= 2.0 && fork.star < 2.9}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${fork.star > 1.0 && fork.star < 1.9}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${fork.star >= 0.0 && fork.star < 0.9}">
															<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
													</div>
												<c:if test="${m_id == null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${fork.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='pickInsertMain?ppic_m_id=${m_id}&ppic_p_num=${p_num}'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${fork.reviewAllCNT}개
													</div>
												</c:if>
											</div>
											<c:if test="${m_id != null}">
												<div class="add-to-cart">
												<form action="ShoppingCartInsert2" method="post">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${fork.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
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







<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">신상품 Best</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active"><a href="../main/newProduct">신상품 Best 더보기</a></li>
								</ul>
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
									<div class="products-slick" data-nav="#slick-nav-5">
										<!-- product -->
										<c:forEach var="newList" items="${newProduct}">
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
												<p class="product-category">Category</p>
												<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}"><h3 class="product-name">${newList.p_name}</h3></a>
												<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}"><h4 class="product-price">${newList.p_price}원</h4></a>
													<ul class="product-links">
														<li><h6><a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">${newList.category1} /${newList.category2}/${newList.category3}</a></h6></li>
													</ul>
													<div class="rating-avg">${newList.star}
														<c:if test="${newList.star == 5.0 && newList.star > 4.6}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${newList.star >= 4.0 && newList.star < 4.6}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${newList.star >= 3.0 && newList.star < 3.9}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${newList.star >= 2.0 && newList.star < 2.9}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${newList.star > 1.0 && newList.star < 1.9}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
														<c:if test="${newList.star >= 0.0 && newList.star < 0.9}">
															<a href="../main/product?p_num=${newList.p_num}&p_m_id=${newList.p_m_id}">
																<div class="rating-stars">
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</a>
														</c:if>
													</div>
												<c:if test="${m_id == null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='/member/customLogin'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${newList.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
														찜하기<button class="add-to-wishlist" onclick="location.href='pickInsertMain?ppic_m_id=${m_id}&ppic_p_num=${p_num}'"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
														리뷰 : ${newList.reviewAllCNT}개
													</div>
												</c:if>
											</div>
											<c:if test="${m_id != null}">
												<div class="add-to-cart">
												<form action="ShoppingCartInsert2" method="post">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${newList.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
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
										</c:forEach>
										<!-- /product -->
									</div>
									<div id="slick-nav-5" class="products-slick-nav"></div>
								</div>
								<!-- /tab -->
							</div>
						</div>
					</div>
					<!-- Products tab & slick -->
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->


		<h1></h1><br /><br /><br /><br /><br /><br /><br />


<%@ include file="../footer.jsp" %>
