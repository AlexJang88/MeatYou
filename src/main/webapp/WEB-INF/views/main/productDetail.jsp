<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container"> 
				<!-- row -->
				<div class="row">
					<!-- Product main img -->
					<div class="col-md-5 col-md-push-2">
						<div id="product-main-img">
							<div class="product-preview">
							<img src="../resources/file/product/${dto.p_num}/${dto.thumb}" >
							</div>
						</div>
					</div>
					<!-- /Product main img -->

					<!-- Product thumb imgs -->
					<div class="col-md-2  col-md-pull-5">
						<div id="product-imgs">
						</div>
					</div>
					<!-- /Product thumb imgs -->

					<!-- Product details -->
					<div class="col-md-5">
						<div class="product-details">
							<h2 class="product-name">${dto.p_name}</h2>
							<div>
								<c:if test="${star == 5.0 && star >= 4.6}">
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
									</div>
								</c:if>
								<c:if test="${star >= 4.0 && star < 4.6}">
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o"></i>
									</div>
								</c:if>
								<c:if test="${star >= 3.0 && star < 3.9}">
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
									</div>
								</c:if>
								<c:if test="${star >= 2.0 && star < 2.9}">
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
									</div>
								</c:if>
								<c:if test="${star > 1.0 && star < 1.9}">
									<div class="product-rating">
										<i class="fa fa-star"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
									</div>
								</c:if>
								<c:if test="${star >= 0.0 && star < 0.9}">
									<div class="product-rating">
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
										<i class="fa fa-star-o"></i>
									</div>
								</c:if>
								<a class="review-link" href=""> 리뷰(${reviewAllCNT}개)</a>
							</div>
							<div>
								<h3 class="product-price">${dto.p_price}원</h3>
								<span class="product-available">${dto.stock}개 남음</span>
							</div>
<%-- 							<%@ include file="../comment.jsp" %> --%>

<!-- 							<div class="product-options">
								<label>
									Size
									<select class="input-select">
										<option value="0">X</option>
									</select>
								</label>
								<label>
									Color
									<select class="input-select">
										<option value="0">Red</option>
									</select>
								</label>
							</div> -->
							<c:if test="${mid != null}">
							<form action="/main/ShoppingCartInsert">
								<div class="add-to-cart">
									<div class="qty-label">
										재고 수량
										<div class="input-select">
											<input type="hidden" name="shop_m_id" value="${mid}">
											<input type="hidden" name="shop_p_num" value="${p_num}">
											<input type="number" name="shop_quantity" value=1 >
										</div>
									</div><br/>
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 장바구니 담기</button>
								</div>
							</form>
							</c:if>
							
							<c:if test="${mid == null}">
							<form action="/member/customLogin">
								<div class="add-to-cart">
									<div class="qty-label">
										Qty
										<div class="input-select">
											<input type="hidden" name="shop_m_id" value="${mid}">
											<input type="hidden" name="shop_p_num" value="${p_num}">
											<input type="number" name="quantity" value=1>
										</div>
									</div><br/>
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 장바구니 담기</button>
								</div>
							</form>
							</c:if>

							<c:if test="${mid != null}">
								<form action="/main/pickInsert">
									<div class="add-to-cart">
										<input type="hidden" name="ppic_m_id" value="${mid}">
										<input type="hidden" name="ppic_p_num" value="${p_num}">
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 찜하기</button>
									</div>
								</form>
							</c:if>


							<c:if test="${mid == null}">
								<form action="/member/customLogin">
									<div class="add-to-cart">
										<input type="hidden" name="ppic_m_id" value="${mid}">
										<input type="hidden" name="ppic_p_num" value="${p_num}">
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 찜하기</button>
									</div>
								</form>
							</c:if>
							
							<c:if test="${mid != null}">
								<a href="/board/userQna?p_num=${p_num}" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>판매자 문의하기</a>
								<a href="/admin/reportForm?p_num=${p_num}" class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i>신고하기</a>
							</c:if>
							
							<c:if test="${mid == null}">
								<form action="/member/customLogin">
									<div class="add-to-cart">
										<input type="hidden" name="ppic_m_id" value="${mid}">
										<input type="hidden" name="ppic_p_num" value="${p_num}">
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 판매자 문의하기</button>
										<button class="add-to-cart-btn" type="submit"><i class="fa fa-shopping-cart"></i> 신고하기</button>
									</div>
								</form>
							</c:if>
							
							
							
<!-- 							<ul class="product-btns">
								<li><a href="#"><i class="fa fa-heart-o"></i> add to wishlist</a></li>
								<li><a href="#"><i class="fa fa-exchange"></i> add to compare</a></li>
							</ul> --> 
 
							<ul class="product-links">
								<li><h5>고기 정보 : </h5></li>
								<li><h5>${PDcategory1}/</h5></li>
								<li><h5>${PDcategory2}/</h5></li>
								<li><h5>${PDcategory3}</h5></li>
							</ul>

							<ul class="product-links">
								<li>Share:</li>
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
								<li><a href="#"><i class="fa fa-envelope"></i></a></li>
							</ul>

						</div>
					</div>
					<!-- /Product details -->

					<!-- Product tab -->
					<div class="col-md-12">
						<div id="product-tab">
							<!-- product tab nav -->
							<ul class="tab-nav">
								<li class="active"><a data-toggle="tab" href="#tab1">상세정보</a></li>
								<!-- <li><a data-toggle="tab" href="#tab2">사진</a></li> -->
								<li><a data-toggle="tab" href="#tab3">리뷰/평점</a></li>
							</ul>
							<!-- /product tab nav -->

							<!-- product tab content -->
							<div class="tab-content">
								<!-- tab1  -->
								<div id="tab1" class="tab-pane fade in active">
									<div class="row">
										<div class="col-md-12">
											${dto.pd_p_desc}
										</div>
									</div>
								</div>
								<!-- /tab1  -->

								<!-- tab2  -->
								<div id="tab2" class="tab-pane fade in">
									<div class="row">
										<div class="col-md-12">
											<img src="/resources/img/product01.png" alt="">
										</div>
									</div>
								</div>
								<!-- /tab2  -->

								<!-- tab3  -->
								<div id="tab3" class="tab-pane fade in">
									<div class="row">
										<!-- Rating -->
										<div class="col-md-3">
											<div id="rating">
												<div class="rating-avg">${star}
													<c:if test="${star == 5.0 && star >= 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${star >= 4.0 && star < 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${star >= 3.0 && star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${star >= 2.0 && star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${star > 1.0 && star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${star >= 0.0 && star < 0.9}">
															<div class="rating-stars">
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
												</div>
												<ul class="rating">
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
														</div>
														<div class="rating-progress">
															<div style="width: ${star5Per}%;"></div>
														</div>
														<span class="sum">${star5}</span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: ${star4Per}%;"></div>
														</div>
														<span class="sum">${star4}</span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: ${star3Per}%;"></div>
														</div>
														<span class="sum">${star3}</span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: ${star2Per}%;"></div>
														</div>
														<span class="sum">${star2}</span>
													</li>
													<li>
														<div class="rating-stars">
															<i class="fa fa-star"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
															<i class="fa fa-star-o"></i>
														</div>
														<div class="rating-progress">
															<div style="width: ${star1Per}%;"></div>
														</div>
														<span class="sum">${star1}</span>
													</li>
												</ul>
											</div>
										</div>
										<!-- /Rating -->

										<!-- Reviews -->
										
										<div class="col-md-6">
											<div id="reviews">
												<ul class="reviews">
												<c:forEach var="rdto" items="${reviewAllPaging}">
													<li>
														<div class="review-heading">
															<h5 class="name">${rdto.r_m_id}</h5>
															<p class="date">${rdto.r_reg_date}</p>
															<c:if test="${rdto.star == 5}">
																<div class="review-rating">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																</div>
															</c:if>
															<c:if test="${rdto.star == 4}">
																<div class="review-rating">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</c:if>
															<c:if test="${rdto.star == 3}">
																<div class="review-rating">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</c:if>
															<c:if test="${rdto.star == 2}">
																<div class="review-rating">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</c:if>
															<c:if test="${rdto.star == 1}">
																<div class="review-rating">
																	<i class="fa fa-star"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</c:if>
															<c:if test="${rdto.star == 0}">
																<div class="review-rating">
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
															</c:if>
														</div>
														<div class="review-body">
															<p>${rdto.r_content}</p>
														</div>
													</li>
												</c:forEach>
												</ul>
												<ul class="reviews-pagination">
												<c:if test="${rcount > 0}">
													<c:if test="${startPage > 10}">
														<li><a href="/main/product?p_num=${p_num}&p_m_id=${op.p_m_id}&pageNum=${startPage-10}">[이전]</a></li>
													</c:if>	
													
													<c:forEach var="i" begin="${startPage}" end="${endPage}">	
														<li><a href="/main/product?p_num=${p_num}&p_m_id=${op.p_m_id}&pageNum=${i}">${i}</a></li>
													</c:forEach>	
													
													<c:if test="${endPage > pageCount}">
														<li><a href="/main/product?p_num=${p_num}&p_m_id=${op.p_m_id}&pageNum=${startPage+10}">[다음]</a></li>
													</c:if>		
												</c:if>		
												</ul>
											</div>
										</div>
										<!-- /Reviews -->

										<!-- Review Form -->
										<c:if test="${mid != null && result > 0}">
										<div class="col-md-3">
											<div id="review-form">
												<form class="review-form" method="post" action="../main/review">
													<textarea class="input" name="r_content" placeholder="리뷰를 작성해주세요"></textarea>
													<div class="input-rating">
														<span>별점 작성 : </span>
														<div class="stars">
															<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
															<input type="hidden" name="r_m_id" value="${mid}">
															<input type="hidden" name="r_p_num" value="${dto.p_num}">
															<input type="hidden" name="r_img" value="file">
															<input id="star5" name="star" value="5" type="radio"><label for="star5"></label>
															<input id="star4" name="star" value="4" type="radio"><label for="star4"></label>
															<input id="star3" name="star" value="3" type="radio"><label for="star3"></label>
															<input id="star2" name="star" value="2" type="radio"><label for="star2"></label>
															<input id="star1" name="star" value="1" type="radio"><label for="star1"></label>
														</div>
													</div>
													<button class="primary-btn">리뷰작성</button>
												</form>
											</div>
										</div>
										</c:if>
										<!-- /Review Form -->
									</div>
								</div>
								<!-- /tab3  -->
							</div>
							<!-- /product tab content  -->
						</div>
					</div>
					<!-- /product tab -->
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
							<h3 class="title">판매자의 다른상품</h3>
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
										<c:forEach var="op" items="${opList}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/file/product/${dto.p_num}/${dto.thumb}" >
												<div class="product-label">
													<span class="sale" ><a>목록확인</a></span>
													<span class="sale" ><a>상품설명</a></span>
													<span class="sale" ><a>상품평/평점</a></span>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${op.p_num}&p_m_id=${op.p_m_id}"><h3 class="product-name">${op.p_name}</h3></a>
												<a href="../main/product?p_num=${op.p_num}&p_m_id=${op.p_m_id}"><h4 class="product-price">${op.p_price}원</h4></a>
												<ul class="product-links">
													<a href="../main/product?p_num=${op.p_num}&p_m_id=${op.p_m_id}"><li><h6>${op.category1} / ${op.category2} / ${op.category3}</h6></li></a>
												</ul>
													<div class="rating-avg">${op.star}
														<c:if test="${op.star == 5.0 && op.star > 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${op.star >= 4.0 && op.star < 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${op.star >= 3.0 && op.star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${op.star >= 2.0 && op.star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${op.star >= 1.0 && op.star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${op.star >= 0.0 && op.star < 0.9}">
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
													<button class="add-to-cart-btn" onclick="location.href='ShoppingCartInsert2?p_num=${p_num}&m_id=${m_id}&shop_quantity=1'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
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



<%@ include file="../footer.jsp" %>
	</body>
</html>
