<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" import = "java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>주문목록조회</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<link type="text/css" rel="stylesheet" href="css/paySelect.css"/>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<c:set var="oDatas" value="${requestScope.oDTO}" />
	<c:set var="datasTotal" value="${requestScope.datasTotal}" />
	<!-- SECTION -->
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
	<div class="section">
		<!-- container -->  
		<div class="container">
			<!-- row -->
    <div class="row">
        <div class="col-md-9" style="margin-left: 10%;">
            <!-- Billing Details -->
						<table class="cart__list">
							<tbody>
       <c:forEach var="oData" items="${oDatas}" >
		    <tr class="cart__list__detail">
		        <td colspan="4">
		            
		            <h5 style="text-align:center">
		                주문 날짜 : ${oData.odListDate}
		            </h5>
		        </td>
		    </tr>
            <tr>
                <td rowspan="${oData.cnt + 1}">
	                <h5 style="text-align:center">
	                	주문번호 : ${oData.odListID}
	            	</h5>
                </td>
                <tr>
    			<c:forEach var="data" items="${datasTotal}">
	        	<c:if test="${oData.odListID == data.odListID}">
                <td><img src="${data.productImg}" alt="product"></td>
                <td>
                    <p>${data.productName} ${data.productID}번 / 수량 : ${data.odContentCnt} 개</p>
                    <p>${data.productCategory}</p>
                </td>
                <td>
                	<span class="price">${data.productPrice * data.odContentCnt}원</span><br>
                    <form action="reviewWritePage.do" method="POST" id="form1">
                        <c:choose>
                            <c:when test="${data.reviewButtonStatus eq 'enabled'}">
                                <%-- 리뷰 작성 완료된 경우 버튼 비활성화 --%>
                                <button class="cart__list__orderbtn" disabled>리뷰작성완료</button>
                            </c:when>
                            <c:otherwise>
                                <%-- 리뷰 작성 미완료된 경우 버튼 활성화 --%>
                                <label for="${data.productID}">
                                	<img alt="" src="img/writeReview.png" style="width: 30px; height: 30px;">
                                </label>
                                <button id="${data.productID}" class="cart__list__orderbtn" onclick="openReviewWrite()" style="display: none;">리뷰작성하기</button>
                            </c:otherwise>
                        </c:choose>
                    </form>
                </td>
                <tr>
			  </c:if>
   			</c:forEach>
            </tr>
</c:forEach>
							</tbody>
						</table>
						<!-- /Billing Details -->
            <!-- Billing Details -->
            <br>
            <br>
            <!-- /Billing Details -->
        </div>
        <!-- Order Details -->
        <!-- /Order Details -->
    </div>
    <!-- /row -->
</div>
		</div>
		<!-- /container -->
	<!-- /SECTION -->
	<!-- FOOTER -->
	<footer id="footer">
		<!-- top footer -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-3 col-xs-6">
						<div class="footer">
							<h3 class="footer-title">About Us</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
								sed do eiusmod tempor incididunt ut.</p>
							<ul class="footer-links">
								<li><a href="#"><i class="fa fa-map-marker"></i>1734
										Stonecoal Road</a></li>
								<li><a href="#"><i class="fa fa-phone"></i>+021-95-51-84</a></li>
								<li><a href="#"><i class="fa fa-envelope-o"></i>email@email.com</a></li>
							</ul>
						</div>
					</div>

					<div class="col-md-3 col-xs-6">
						<div class="footer">
							<h3 class="footer-title">Categories</h3>
							<ul class="footer-links">
								<li><a href="#">Hot deals</a></li>
								<li><a href="#">Laptops</a></li>
								<li><a href="#">Smartphones</a></li>
								<li><a href="#">Cameras</a></li>
								<li><a href="#">Accessories</a></li>
							</ul>
						</div>
					</div>

					<div class="clearfix visible-xs"></div>

					<div class="col-md-3 col-xs-6">
						<div class="footer">
							<h3 class="footer-title">Information</h3>
							<ul class="footer-links">
								<li><a href="#">About Us</a></li>
								<li><a href="#">Contact Us</a></li>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="#">Orders and Returns</a></li>
								<li><a href="#">Terms & Conditions</a></li>
							</ul>
						</div>
					</div>

					<div class="col-md-3 col-xs-6">
						<div class="footer">
							<h3 class="footer-title">Service</h3>
							<ul class="footer-links">
								<li><a href="#">My Account</a></li>
								<li><a href="#">View Cart</a></li>
								<li><a href="#">Wishlist</a></li>
								<li><a href="#">Track My Order</a></li>
								<li><a href="#">Help</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /top footer -->

		<!-- bottom footer -->
		<div id="bottom-footer" class="section">
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12 text-center">
						<ul class="footer-payments">
							<li><a href="#"><i class="fa fa-cc-visa"></i></a></li>
							<li><a href="#"><i class="fa fa-credit-card"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-paypal"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-mastercard"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-discover"></i></a></li>
							<li><a href="#"><i class="fa fa-cc-amex"></i></a></li>
						</ul>
						<span class="copyright"> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;<script>
								document.write(new Date().getFullYear());
								
								function openReviewWrite() {
									document.getElementById('form1').submit();
								}
							</script>
							All rights reserved | This template is made with <i
							class="fa fa-heart-o" aria-hidden="true"></i> by <a
							href="https://colorlib.com" target="_blank">Colorlib</a> <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</span>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /bottom footer -->
	</footer>
	<!-- /FOOTER -->

	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>

</body>
</html>
