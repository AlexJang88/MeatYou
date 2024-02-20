<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../header.jsp" %> 
<script>
window.onload = function () {
    // JSP 페이지에서 설정한 statusFromJSP 값을 읽어옴
    var statusFromJSP = ${status};
    var testPageUrl = '';
    var newWindow = '';
    var id= '${id}';
    // status 값이 0인 경우에만 동작
    if(id != ''){
  
    if (statusFromJSP === 0) {
        testPageUrl = '/customers/test';
        newWindow = window.open(testPageUrl, 'TestPage', 'width=800,height=600');
        if (window.focus) {
            newWindow.focus();
        }
    }else if(statusFromJSP === 1) {
    	testPageUrl = '/customers/select';
        newWindow = window.open(testPageUrl, 'TestPage', 'width=800,height=600');
        if (window.focus) {
            newWindow.focus();
        }
    }
}
}

</script>
<script>
	function cart() {
	alert("장바구니에 추가되었습니다.");
	}
</script>
<script>
	function pick() {
	alert("찜목록에 추가되었습니다.");
	}
</script>
<div style="text-align : center;">
	<%@ include file="popup2.jsp" %> 
</div>
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
									<li class="active"><a href="../main/powerLink?review=review">파워링크 상품 더보기</a></li>
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
												<img src="../resources/file/product/${cus.p_num}/${cus.thumb}" alt=""style="width:260px; height:260px;  ">
												<div class="product-label">
													<div style="text-align : center;">
														<form>
															<input class="viewClass" type="button" value="미리 보기" onclick="openPopUp('${cus.p_num}','${cus.p_m_id}')" color="red"><br>
														</form>
													</div>
												</div>
											</div>
											<div class="product-body">
												<h3 class="product-name"><a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}">${cus.p_name}</a></h3>
												<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}"><h4 class="product-price">${cus.p_price}원</h4></a>
												<ul class="product-links">
													<a href="../main/product?p_num=${cus.p_num}&p_m_id=${cus.p_m_id}"><li><h6>${cus.category1} / ${cus.category2} / ${cus.category3}</h6></li></a>
												</ul>
													<div class="rating-avg">${cus.star}
														<c:if test="${cus.star <= 5.0 && cus.star >= 4.7}">
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
														<c:if test="${cus.star >= 4.0 && cus.star <= 4.6}">
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
											<form action="ShoppingCartInsert2" method="post" onsubmit="cart()">
												<input type="hidden" name="m_id" value="${m_id}">
												<input type="hidden" name="p_num" value="${cus.p_num}">
												<input type="hidden" name="shop_quantity" value="1">
												<button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
											</form>
											</div>
										</c:if>

										<c:if test="${m_id == null}">
											<div class="add-to-cart">
												<button class="add-to-cart-btn"  onclick="location.href='/member/customLogin'"><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
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
							<h3 class="title">${memdto.m_name}님 맞춤 추천</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
								<li class="active"><a href="customOrder?pre_m_id=${m_id}">${memdto.m_name}님 맞춤 추천 상품 더보기</a></li>
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
										<c:forEach var="meat" items="${customOrderBest}">
										<div class="product">
											<div class="product-img">
												<img src="../resources/file/product/${meat.p_num}/${meat.thumb}" alt="">
												<div class="product-label">
													<div style="text-align : center;">
														<form>
															<input class="viewClass" type="button" value="미리 보기" onclick="openPopUp('${meat.p_num}','${meat.p_m_id}')"><br>
														</form>
													</div>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h3 class="product-name">${meat.p_name}</h3></a>
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h4 class="product-price">${meat.p_price}원</h4></a>
												<ul class="product-links">
													<li><h6><a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">${meat.category1} /${meat.category2}/${meat.category3}</a></h6></li>
												</ul>
													<div class="rating-avg">${meat.star}
														<c:if test="${meat.star <= 5.0 && meat.star >= 4.7}">
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
														<c:if test="${meat.star >= 4.0 && meat.star <= 4.6}">
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
												<form action="ShoppingCartInsert2" method="post" onsubmit="cart()">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${meat.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
													<button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
												</form>
												</div>
												<script>
													function cart() {
													alert("장바구니에 추가되었습니다.");
													}
												</script>
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
												<img src="../resources/file/product/${meat.p_num}/${meat.thumb}" alt="">
												<div class="product-label">
													<div style="text-align : center;">
														<form>
															<input class="viewClass" type="button" value="미리 보기" onclick="openPopUp('${meat.p_num}','${meat.p_m_id}')"><br>
														</form>
													</div>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h3 class="product-name">${meat.p_name}</h3></a>
												<a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}"><h4 class="product-price">${meat.p_price}원</h4></a>
												<ul class="product-links">
													<li><h6><a href="../main/product?p_num=${meat.p_num}&p_m_id=${meat.p_m_id}">${meat.category1} /${meat.category2}/${meat.category3}</a></h6></li>
												</ul>
													<div class="rating-avg">${meat.star}
														<c:if test="${meat.star <= 5.0 && meat.star >= 4.7}">
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
														<c:if test="${meat.star >= 4.0 && meat.star <= 4.6}">
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
												<form action="ShoppingCartInsert2" method="post" onsubmit="cart()">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${meat.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
													<button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
												</form>
												</div>
												<script>
													function cart() {
													alert("장바구니에 추가되었습니다.");
													}
												</script>
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
												<img src="../resources/file/product/${fork.p_num}/${fork.thumb}" alt="">
												<div class="product-label">
													<div style="text-align : center;">
														<form>
															<input class="viewClass" type="button" value="미리 보기" onclick="openPopUp('${fork.p_num}','${fork.p_m_id}')"><br>
														</form>
													</div>
												</div>
											</div>
											<div class="product-body">
												<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}"><h3 class="product-name">${fork.p_name}</h3></a>
												<a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}"><h4 class="product-price">${fork.p_price}원</h4></a>
													<div class="rating-avg">${fork.star}
													<ul class="product-links">
														<li><h6><a href="../main/product?p_num=${fork.p_num}&p_m_id=${fork.p_m_id}">${fork.category1} /${fork.category2}/${fork.category3}</a></h6></li>
													</ul>
														<c:if test="${fork.star >= 4.7 && fork.star <= 5.0}">
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
														<c:if test="${fork.star >= 4.0 && fork.star <= 4.6}">
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
												<form action="ShoppingCartInsert2" method="post" onsubmit="cart()">
													<input type="hidden" name="m_id" value="${m_id}">
													<input type="hidden" name="p_num" value="${fork.p_num}">
													<input type="hidden" name="shop_quantity" value="1">
													<button class="add-to-cart-btn" ><i class="fa fa-shopping-cart"></i>장바구니 담기</button>
												</form>
												</div>
												<script>
													function cart() {
													alert("장바구니에 추가되었습니다.");
													}
												</script>
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
												<img src="../resources/file/product/${newList.p_num}/${newList.thumb}" alt="">
												<div class="product-label">
													<div style="text-align : center;">
														<form>
															<input class="viewClass" type="button" value="미리 보기" onclick="openPopUp('${newList.p_num}','${newList.p_m_id}')"><br>
														</form>
													</div>
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
														<c:if test="${newList.star <= 5.0 && newList.star >= 4.7}">
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
														<c:if test="${newList.star >= 4.0 && newList.star <= 4.6}">
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
												<form action="ShoppingCartInsert2" method="post" onsubmit="cart()">
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
