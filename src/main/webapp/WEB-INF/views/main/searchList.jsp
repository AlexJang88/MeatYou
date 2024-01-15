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
							<c:forEach var="sear" items="${searchList}">
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
										<h3 class="product-name"><a href="../main/product?p_num=${sear.p_num}">${sear.p_name}</a></h3>
										<h4 class="product-price">${sear.p_price}</h4>
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
									<div class="add-to-cart">
										<button class="add-to-cart-btn"><i class="fa fa-shopping-cart"></i> add to cart</button>
									</div>
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
										<li><a href="/main/search?searchOption=total&search=${search}&pageNum${startPage-10}">[이전]</a></li>
								</c:if>
								<c:forEach var="i" begin="${startPage}" end="${endPage}">
									<li><a href="/main/search?searchOption=total&search=${search}&pageNum${i}">${i}</a></li>
								</c:forEach>
								<c:if test="${endPage > pageCount}">
									<li><a href="/main/search?searchOption=total&search=${search}&pageNum${startPage+10}">[다음]</a></li>
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
