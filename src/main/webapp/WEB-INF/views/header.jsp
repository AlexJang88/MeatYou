<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
    // 로그아웃 시도
    Kakao.Auth.logout(function() {
        console.log("Kakao.Auth.logout: Logout successful.");

        // 로그아웃 후 현재 액세스 토큰 확인
        console.log("Kakao.Auth.getAccessToken():", Kakao.Auth.getAccessToken());

        // 로그아웃 후 추가적인 작업을 여기에 추가할 수 있습니다.
    });

    // 추가적인 로그아웃 시도 (Promise를 이용한 방식)
    Kakao.Auth.logout()
        .then(function(response) {
            console.log("Kakao.Auth.logout Promise: Logout successful.");
            // Promise 방식으로도 로그아웃 후 액세스 토큰 확인
            console.log("Kakao.Auth.getAccessToken():", Kakao.Auth.getAccessToken());
        })
        .catch(function(error) {
            console.log('Kakao.Auth.logout Promise: Logout failed.', error);
        });
</script>

<!DOCTYPE html>
<html lang="en"> 
   <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
       <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
 
      <title>MeetYou</title>
<!--       <link href="/resources/img/gogi.jpg" rel="shortcut icon" type="image/x-icon"> -->
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
                              <a href="/member/tokenLogout">카카오톡로그아웃</a>
                     </sec:authorize>
                     
            
				  <sec:authorize access="isAuthenticated() and (hasAuthority('ROLE_KAKAO_BESTMEMBER') or hasAuthority('ROLE_KAKAO_MEMBER') or hasAuthority('ROLE_KAKAO_RESIGN') or hasAuthority('ROLE_KAKAO_READYSALLER') or hasAuthority('ROLE_KAKAO_RESIGNSALLER') or hasAuthority('ROLE_KAKAO_SALLER') or hasAuthority('ROLE_KAKAO_TOPPAYSALLER') or hasAuthority('ROLE_KAKAO_CONTENTPAYSALLER') or hasAuthority('ROLE_KAKAO_ALLPAYSALLER') or hasAuthority('ROLE_KAKAO_ADMIN'))">
				    <!-- 카카오 계정으로 로그인한 사용자에게 보여질 내용 -->
				    <div>
				      <li><a>보이려나</a></li>
				    </div>
				</sec:authorize>
       
  			
                  <li><a href="/board/consumerQna"><i class="fa"></i> 문의하기</a></li>
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
<!--                         <img src="../resources/img/MeetYou1.gif" style="width: 200px"> -->
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
                          <div class="dropdown"  style="position:absolute; right:-80px; margin-top:0; float: left;   height: 150px; top:-2px;">
                       <a href="/member/customLogin" class="login-required" onclick="checkLogin()" style=" height: 130px;" >
                       <img src="/resources/member/img/customer.png"  style="width:40%; margin-top:0; ">
                         <span style="position:absolute; top:39px; right: 28px;">내가 찜</span>
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
                     
                                        
                   <div class="dropdown"  style="position:absolute; right=0; margin-top:0; float: left;   height: 150px; top:-1.2px;">
                       <a href="/member/pickMe" class="login-required" onclick="checkLogin()" style=" height: 130px;" >
                       <img src="/resources/member/img/customer.png"  style="width:40%; margin-top:0; ">
                         <div class="qty">${pickmecount}</div>                        
                         <span style="position:absolute; top:39px; right: 28px;">찜업체</span>
                         <div class="qty">${pick_P_CNT}</div>
                         </a>
                     </div>
                           </sec:authorize>
                 <sec:authorize access="isAnonymous() == false and (hasAuthority('ROLE_READYSALLER') or hasAuthority('ROLE_RESIGNSALLER') or hasAuthority('ROLE_SALLER') or hasAuthority('ROLE_TOPPAYSALLER') or hasAuthority('ROLE_CONTENTPAYSALLER') or hasAuthority('ROLE_ALLPAYSALLER')   or hasAuthority('ROLE_KAKAO_RESIGNSALLER') or hasAuthority('ROLE_KAKAO_SALLER') or hasAuthority('ROLE_KAKAO_TOPPAYSALLER') or hasAuthority('ROLE_KAKAO_CONTENTPAYSALLER') 
                 or hasAuthority('ROLE_KAKAO_ALLPAYSALLER'))">
                <!-- 인증된 사용자 중 ROLE_MEMBER와 ROLE_ADMIN을 제외한 다른 모든 권한을 가진 사용자에게만 보여집니다 -->
                <div class="dropdown" style="position:absolute; right:-170px; margin-top:0; float: left; height: 150px; top:-2px;">
                    <a href="/member/SallerPickMe" class="login-required" style="height: 130px;">
                        <img src="/resources/member/img/guest.png" style="width:40%; margin-top:0;">
                        <div class="qty">${pickmecount}</div>          
                        <span style="position:absolute; top:39px; right: 28px;">나를 찜</span>
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
                  <li><a href="/main/setMenu?p_s_category=1">세트</a></li>
                  <li><a href="/main/giftSet">선물세트</a></li>
               </ul>
               <!-- /NAV -->
            </div>
            <!-- /responsive-nav -->
         </div>
         <!-- /container -->
      </nav>
      <!-- /NAVIGATION -->
