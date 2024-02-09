<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %> 
 <script>
	function cart() {
	alert("장바구니에 추가되었습니다.");
	}
</script>
		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">돼지고기 목록</h3>
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
										<c:forEach var="fork" items="${mainFork}">
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
												<h3 class="product-name"><a href="#">${fork.p_name}</a></h3>
												<h4 class="product-price">${fork.p_price}</h4>
												<div class="product-rating">
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
													<i class="fa fa-star"></i>
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
											<button class="add-to-cart-btn"  onsubmit="cart()" onclick="location.href='ShoppingCartInsert2?p_num=${p_num}&m_id=${m_id}&shop_quantity=1'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
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
