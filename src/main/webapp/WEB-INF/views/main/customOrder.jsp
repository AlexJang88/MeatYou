<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %>
<%@ include file="../newSort.jsp" %> 
<%@ include file="popup2.jsp" %> 

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
						<h3 class="title">맞춤품 목록</h3>
						
						<!-- store products -->
						<div class="row">
							<!-- product -->
							<c:forEach var="newp" items="${customOrder}">
							<div class="col-md-4 col-xs-6">
								<div class="product">
									<div class="product-img">
										<img src="/resources/img/product01.png" alt="">
										<div class="product-label">
											<div style="text-align : center;">
												<form>
													<input  class="viewClass"  type="button" value="미리 보기" onclick="openPopUp('${newp.p_num}','${newp.p_m_id}')"><br>
												</form>
											</div>

										</div>
									</div>
									<div class="product-body">
										<a href="../main/product?p_num=${newp.p_num}&p_m_id=${newp.p_m_id}"><h3 class="product-name">${newp.p_name}</a></h3></a>
										<a href="../main/product?p_num=${newp.p_num}&p_m_id=${newp.p_m_id}"><h4 class="product-price">${newp.p_price}원</h4></a>
										<ul class="product-links">
												<a href="../main/product?p_num=${newp.p_num}&p_m_id=${newp.p_m_id}"><li><h6>${newp.category1} / ${newp.category2} / ${newp.category3}</h6></li></a>
											</ul>
										<div class="rating-avg">${newp.star}
														<c:if test="${newp.star == 5.0 && newp.star >= 4.7}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
															</div>
														</c:if>
														<c:if test="${newp.star >= 4.0 && newp.star <= 4.6}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${newp.star >= 3.0 && newp.star < 3.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${newp.star >= 2.0 && newp.star < 2.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${newp.star > 1.0 && newp.star < 1.9}">
															<div class="rating-stars">
																<i class="fa fa-star"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
																<i class="fa fa-star-o"></i>
															</div>
														</c:if>
														<c:if test="${newp.star >= 0.0 && newp.star < 0.9}">
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
														리뷰 : ${newp.reviewAllCNT}개
													</div>
												</c:if>

												<c:if test="${m_id != null}">
													<div class="product-btns">
															<form class="product-btns" action="pickInsertMainNewProduct" method="post">
																<input type="hidden" name="ppic_m_id" value="${newp.ppic_m_id}">
																<input type="hidden" name="ppic_p_num" value="${newp.ppic_p_num}">
																<input type="hidden" name="desc" value="desc">
																찜하기<button class="add-to-wishlist"><i class="fa fa-heart-o"></i><span class="tooltipp">찜하기</span></button>
																리뷰 : ${newp.reviewAllCNT}개
															</form>
													</div>
												</c:if>
									</div>
									<c:if test="${m_id != null}">
										<div class="add-to-cart">
										<form action="ShoppingCartInsertnewProduct" method="post">
											<input type="hidden" name="m_id" value="${m_id}">
											<input type="hidden" name="p_num" value="${p_num}">
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
										<li><a href="/main/newProduct?&pageNum${startPage-10}">[이전]</a></li>
									</c:if>
									<c:forEach var="i" begin="${startPage}" end="${endPage}">
										<li><a href="/main/newProduct?&pageNum=${i}">${i}</a></li>
									</c:forEach>
									<c:if test="${endPage < pageCount}">
										<li><a href="/main/newProduct?&pageNum${startPage+10}">[다음]</a></li>
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
