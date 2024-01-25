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
							<h3 class="title">파워링크 목록</h3>
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
										<c:forEach var="poList" items="${poLinkList}">
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
												<a href="../main/product?p_num=${poList.p_num}&p_m_id=${poList.p_m_id}"><h3 class="product-name">${poList.p_name}</h3></a>
												<a href="../main/product?p_num=${poList.p_num}&p_m_id=${poList.p_m_id}"><h4 class="product-price">${poList.p_price}</h4></a>
											<ul class="product-links">
												<a href="../main/product?p_num=${poList.p_num}&p_m_id=${poList.p_m_id}"><li><h6>${poList.category1} / ${poList.category2} / ${poList.category3}</h6></li></a>
											</ul>
										<div class="rating-avg">${poList.star}
														<c:if test="${poList.star == 5.0 && poList.star > 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${poList.star >= 4.0 && poList.star < 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${poList.star >= 3.0 && poList.star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${poList.star >= 2.0 && poList.star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${poList.star > 1.0 && poList.star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${poList.star >= 0.0 && poList.star < 0.9}">
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
														리뷰 : ${poList.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
															<form class="product-btns" action="pickInsertMainPlink" method="post">
																<input type="hidden" name="ppic_m_id" value="${poList.ppic_m_id}">
																<input type="hidden" name="ppic_p_num" value="${poList.ppic_p_num}">
																<input type="hidden" name="desc" value="desc">
																찜하기<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
																리뷰 : ${poList.reviewAllCNT}개
															</form>
													</div>
												</c:if>
											</div>
																				<c:if test="${m_id != null}">
										<div class="add-to-cart">
										<form action="ShoppingCartInsertPowerLink" method="post">
											<input type="hidden" name="m_id" value="${m_id}">
											<input type="hidden" name="p_num" value="${p_num}">
											<input type="hidden" name="shop_quantity" value="1">>
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


		<h1></h1><br /><br /><br /><br /><br /><br /><br />


<%@ include file="../footer.jsp" %>
