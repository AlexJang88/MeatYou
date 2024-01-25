<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<!DOCTYPE html>
<html lang="en"> 
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 
		<title>MeetYou</title>
<!-- 		<link href="/resources/img/gogi.jpg" rel="shortcut icon" type="image/x-icon"> -->
 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap --> 
 		<link type="text/css" rel="stylesheet" href="/resources/css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="/resources/css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="/resources/css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="/resources/css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="/resources/css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="/resources/css/style.css"/>

 		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
 		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
 		<!--[if lt IE 9]>
 		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
 		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
 		<![endif]-->

    </head>
	<body>
		<!-- HEADER -->
		<header>
			<!-- TOP HEADER -->
			<div id="top-header">
				<div class="container">
				
					<ul class="header-links pull-right">
						                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />      
                  <sec:authorize access="isAnonymous()">
                      <li><a href="/member/inputForm"><i class="fa"></i>회원가입 </a></li>
                      <li><a href="/member/customLogin"><i class="fa"></i> 로그인</a></li>
                      
                  </sec:authorize>

                     <sec:authorize access="isAuthenticated()">
                         <li><a href="/member/modify"><i class="fa"></i> 마이페이지</a></li>
                         <li><a href="/member/customLogout"><i class="fa"></i> 로그아웃</a></li>
                     </sec:authorize>
						<li><a href="#"><i class="fa"></i> 고객센터</a></li>
					</ul>
				</div>
			</div>
			<!-- /TOP HEADER -->

			<!-- MAIN HEADER -->
			<div id="header">
				<!-- container -->
				<div class="container">
					<!-- row -->
					<div class="row">
						<!-- LOGO -->
						<div class="col-md-3">
							<div class="header-logo">
								<a href="#" class="logo">
								<a href="/main/main"><h1 style="color:white;">I Meet You</h1></a>
									<!-- <img src="../resources/img/logo.png" alt=""> -->
								</a>
							</div>
						</div>
						<!-- /LOGO -->

						<!-- SEARCH BAR -->
						<div class="col-md-6">
							<div class="header-search">
								<form action="/main/search">
<!-- 								<img src="../resources/img/MeetYou1.gif" style="width: 200px"> -->
									<select class="input-select" name="searchOption">
										<option value="total">검색하기</option>
										<option value="title">제목 검색</option>
										<option value="total">제목+본문 검색</option>
									</select>
									<input class="input" name="search" placeholder="여기서 검색하세요..!">
									<button class="search-btn">검색</button>
								</form>
							</div>
						</div>
						<!-- /SEARCH BAR -->

						        <!-- ACCOUNT -->
                  <div class="col-md-3 clearfix">
                     <div class="header-ctn">   
                      <sec:authorize access="isAnonymous()">
                              <div>
                   <a href="/member/customLogin" class="login-required" onclick="checkLogin()">
                         <i class="fa fa-heart-o"></i>
                         <span>찜목록</span>
                         <div class="qty">${pickCNT}</div>
                       </a>
                     </div>
                     
                     <div class="dropdown">
                       <a href="/member/customLogin" class="login-required" onclick="checkLogin()">
                         <i class="fa fa-shopping-cart"></i>
                         <span>장바구니</span>
                         <div class="qty">${CartCNT}</div>
                       </a>
                     </div>
                        </sec:authorize>

                     <sec:authorize access="isAuthenticated()">
                           <div>
                           <a href="/member/pPickList">
                              <i class="fa fa-heart-o"></i>
                              <span>찜목록</span>
                              <div class="qty">${pickCNT}</div>
                           </a>
                        </div>
                        <!-- /Wishlist -->
                        <!-- Cart -->
                        <div class="dropdown">
                           <a href="/member/shoppingCartForm">
                                 <i class="fa fa-shopping-cart"></i>
                                 <span>장바구니</span>
                                 <div class="qty">${CartCNT}</div>
                           </a>
                        </div>
                     </sec:authorize>

                        <!-- Wishlist -->
                      
                        <!-- /Cart -->

                        <!-- Menu Toogle -->
                        <div class="menu-toggle">
                           <a href="#">
                              <i class="fa fa-bars"></i>
                              <span>Menu</span>
                           </a>
                        </div>
                        <!-- /Menu Toogle -->
                     </div>
                  </div>
                  <!-- /ACCOUNT --> 
					</div>
					<!-- row -->
				</div>
				<!-- container -->
			</div>
			<!-- /MAIN HEADER -->
		</header>
		<!-- /HEADER -->

		<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container" >
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="/main/main">홈</a></li>
						<li><a href="/main/mainMeat?category=2&price=desc">소고기</a></li>
						<li><a href="/main/mainMeat?category=1&price=desc">돼지고기</a></li>
						<li><a href="#">세트</a></li>
						<li><a href="#">선물세트</a></li>
						<li><a href="#">특수부위</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->