<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" type="image/x-icon" href="/img/favion.png" >
</head>
<body>
	<!-- HEADER -->
	<header>
		<!-- TOP HEADER -->
		<div id="top-header">
			<div class="container">
				<ul class="header-links pull-left">
					<li><a href="mapPage.do"><i class="fa fa-map-marker"></i>
							찾아오시는 길</a></li>
				</ul>
				<c:if test="${sessionScope.memberDTO == null}">
					<ul class="header-links pull-right">
						<li>
							<a href="loginPage.do"><i class="fa fa-user-o"></i>로그인</a>
						</li>
						<li>
							<a href="joinTermsOfUse.do"><i class="fa fa-user-o"></i>회원가입</a>
						</li>
					</ul>
				
				</c:if>
				<c:if test="${sessionScope.memberDTO != null}">
					<ul class="header-links pull-right">
						<li><a href="logout.do"><i class="fa fa-user-o"></i>
								로그아웃</a></li>
						<li><a href="myPage.do"><i class="fa fa-user-o"></i>
								마이페이지</a></li>
					</ul>
				</c:if>
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
							<a href="main.do" class="logo"> <img src="./img/logo.gif" style="width: 250px; height=65px;" alt="logo image">
							</a>
						</div>
					</div>
					<!-- /LOGO -->

					<!-- SEARCH BAR -->
					<div class="col-md-6">
						<div class="header-search" >
							<form method="POST" action="checkWished.do" style="padding-left: 10%;">
								<!-- <select class="input-select" style="">
									<option value="0">All Categories</option>
									<option value="1">Category 01</option>
									<option value="1">Category 02</option>
								</select> -->  
								<input name="searchKeyword" id="searchKeyword" class="input" placeholder="Search here" style="border-bottom-left-radius: 40px; border-top-left-radius: 40px; padding-left: 4%; width: 80%;">
								<button class="search-btn" >Search</button>
							</form>
						</div>
					</div>
					<!-- /SEARCH BAR -->

					<!-- ACCOUNT -->
					<div class="col-md-3 clearfix">
						<div class="header-ctn">
							<!-- Wishlist -->
							<div>
								<a href="wishList.do"> <!-- 위시리스트 페이지로 이동하는 링크 -->
								    <i class="fa fa-heart-o"></i> <!-- 하트 아이콘 -->
								    <span>My Wishlist</span> <!-- 위시리스트 링크의 텍스트 -->
								
								    <!-- JSTL을 사용하여 서버로부터 받은 데이터를 처리하는 부분 -->
								    <c:set var="updatedWishListCntStr" value="${requestScope.updatedWishListCnt}" /> <!-- 업데이트된 위시리스트 개수를 담은 문자열을 변수에 할당 -->
								    <c:set var="wishListCnt" value="${empty wishListCnt ? 0 : wishListCnt}" /> <!-- 위시리스트 개수를 정수로 변환하고, 값이 null이면 0으로 설정 -->
								
								    <!-- 업데이트된 위시리스트 개수를 나타내는 부분 -->													<!-- \\D는 정규 표현식에서 "숫자가 아닌 문자"를 나타내는 특수한 패턴임 -->
								    <c:set var="updatedWishListCnt" value="${fn:replace(updatedWishListCntStr, '\\\\D', '')}" /> <!-- 숫자 이외의 문자를 제거한 문자열을 변수에 할당 -->
								    <c:if test="${empty updatedWishListCnt}">     
								        <c:set var="updatedWishListCnt" value="${wishListCnt}" /> <!-- 값이 비어 있다면 0으로 설정 -->
								    </c:if>
								
								    <div class="qty wishListCnt">${updatedWishListCnt}</div> <!-- 위시리스트 개수를 출력하는 부분 -->
								</a>
							</div>
							<!-- /Wishlist -->

							<!-- Cart -->
							<c:set var="cart" value="${requestScope.cartDTO}" />
							<c:set var="cartSize" value="${fn:length(cart)}" />
							<c:set var="totalPrice" value="0" />
							<div class="dropdown">
								<a class="dropdown-toggle" data-toggle="dropdown"
									aria-expanded="true"> <i class="fa fa-shopping-cart"></i> <span>Your
										Cart</span>
									<div class="qty">${cartSize}</div>
								    <c:set var="itemPrice" value="${cdata.productPrice * cdata.cartProductCnt}" />
									<c:set var="totalPrice" value="${totalPrice + itemPrice}" />
								</a>
								<div class="cart-dropdown">
									<div class="cart-list">
									<c:forEach var="cdata" items="${cart}">
										<div class="product-widget">
											<div class="product-img">
												<img src="${cdata.productImg}" alt="">
											</div>
											<div class="product-body">
												<h3 class="product-name">
													<a href="productDetail.do?productID=${cdata.productID}">${cdata.productName}</a>
												</h3>
												<h4 class="product-price">
													<span class="qty">${cdata.cartProductCnt}</span>${cdata.productPrice}
												</h4>
											</div>
											<button class="delete">
												<i class="fa fa-close"></i>
											</button>
										</div>
									</c:forEach>
									</div>
									<div class="cart-summary">
										<small>${cartSize} Item(s) selected</small>
										<h5>SUBTOTAL: $${totalPrice}</h5>
									</div>
									<div class="cart-btns">
										<a href="paySelect.do">View Cart</a> <a href="#">Checkout <i
											class="fa fa-arrow-circle-right"></i></a>
									</div>
								</div>
							</div>
							<!-- /Cart -->

							<!-- Menu Toogle -->
							<div class="menu-toggle">
								<a href="#"> <i class="fa fa-bars"></i> <span>Menu</span>
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
</body>
</html>
