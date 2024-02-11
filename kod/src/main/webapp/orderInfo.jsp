<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>결제내역</title>

 		<!-- Google font -->
 		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

 		<!-- Bootstrap -->
 		<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css"/>

 		<!-- Slick -->
 		<link type="text/css" rel="stylesheet" href="css/slick.css"/>
 		<link type="text/css" rel="stylesheet" href="css/slick-theme.css"/>

 		<!-- nouislider -->
 		<link type="text/css" rel="stylesheet" href="css/nouislider.min.css"/>

 		<!-- Font Awesome Icon -->
 		<link rel="stylesheet" href="css/font-awesome.min.css">

 		<!-- Custom stlylesheet -->
 		<link type="text/css" rel="stylesheet" href="css/style.css"/>
 		<link type="text/css" rel="stylesheet" href="css/payInfo.css"/>
 		<link type="text/css" rel="stylesheet" href="css/paySelect.css"/>

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
	<%
	
		System.out.println("[orderInfo jsp]");
		//ArrayList<CartDTO> cDatas =(ArrayList<CartDTO>)request.getAttribute("cartDTO");
		
		
		//ArrayList<OrderContentDTO> ocDatas = (ArrayList<OrderContentDTO>)request.getAttribute("oContentDTO");
		//System.out.println("ocDatas : "+ocDatas);
		
		//MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		//System.out.println(memberDTO);
		//AddressDTO addressDTO = (AddressDTO)request.getAttribute("addressDTO");
		//System.out.println("orderInfo 주소지 : "+addressDTO);
		
	%>
		<!-- HEADER, NAVIGATION -->
		<jsp:include page="util/header.jsp"></jsp:include>
        <jsp:include page="util/navigation.jsp"></jsp:include>
		<!-- /HEADER, NAVIGATION -->
		
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">결제내역</h3>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<div class="col-md-9" style="margin-left: 10%;">
						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">구매자 정보</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="memberName" placeholder="이름" value="${memberDTO.memberName}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="memberEmail" placeholder="Email" value="${memberDTO.memberEmail}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="memberPhNum" placeholder="전화번호" value="${memberDTO.memberPhNum}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsStreet" placeholder="도로명주소" value="${addressDTO.adrsStreet}" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsDetail" placeholder="상세주소" value="${addressDTO.adrsDetail}" disabled>
							</div>
						</div>
						<!-- /Billing Details -->

						<!-- Billing Details -->
						<div class="billing-details">
							<div class="section-title">
								<h3 class="title">주문 내역</h3>
							</div>
							
							<table style="width: 100%; text-align: center;">
					        <thead>
					            <tr>
					                <th style="text-align: center;">상품</th>
					                <th style="text-align: center;">상품이름</th>
					                <th style="text-align: center;">구매개수</th>
					                <th style="text-align: center;">가격</th>
					            </tr>
					        </thead>
					        
					        <c:forEach var="ocdata" items="${oContentDTO}">
					        	<tbody>
					            <tr>
					                <td><img src="${ocdata.productImg}" alt="img" style="width: 200px; height: 200px;"></td>
					                <td>${ocdata.productName}</td>
					                <td>${ocdata.odContentCnt}개</td>
					                <td>${ocdata.productPrice*ocdata.odContentCnt}원</td>
					            </tr>
					       		</tbody>
					        </c:forEach>
					        
					        
					        
					        
					        <%-- <%for(OrderContentDTO ocdata : ocDatas){%>
					        <tbody>
					            <tr>
					                <td><img src="<%=ocdata.getProductImg()%>" alt="img" style="width: 200px; height: 200px;"></td>
					                <td><%=ocdata.getProductName()%></td>
					                <td><%=ocdata.getOdContentCnt()%>개</td>
					                <td><%=ocdata.getProductPrice()*ocdata.getOdContentCnt()%>원</td>
					            </tr>
					        </tbody>
					        <%} %> --%>
					    </table>
					    <div class="cart__mainbtns" style="margin-left: 40%;">
						    <form action="main.do" method="POST">
						    	<button class="cart__bigorderbtn left" type="submit">메인으로</button>
							</form>
						</div>
						
						</div>
						<!-- /Billing Details -->
							
					</div>

				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /SECTION -->

		<!-- NEWSLETTER -->
		<div id="newsletter" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<div class="newsletter">
							<p>Sign Up for the <strong>NEWSLETTER</strong></p>
							<form>
								<input class="input" type="email" placeholder="Enter Your Email">
								<button class="newsletter-btn"><i class="fa fa-envelope"></i> Subscribe</button>
							</form>
							<ul class="newsletter-follow">
								<li>
									<a href="#"><i class="fa fa-facebook"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-twitter"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-instagram"></i></a>
								</li>
								<li>
									<a href="#"><i class="fa fa-pinterest"></i></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /NEWSLETTER -->

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
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</p>
								<ul class="footer-links">
									<li><a href="#"><i class="fa fa-map-marker"></i>1734 Stonecoal Road</a></li>
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
							<span class="copyright">
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
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
