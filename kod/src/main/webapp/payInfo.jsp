<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		 <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>결제 전 정보확인</title>

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

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->

    </head>
	<body>
		<!-- HEADER, NAVIGATION -->
		<jsp:include page="util/header.jsp"></jsp:include>
        <jsp:include page="util/navigation.jsp"></jsp:include>
		<!-- /HEADER, NAVIGATION -->
	<%
	
		System.out.println("[payInfo]");
		ArrayList<CartDTO> cDatas =(ArrayList<CartDTO>)request.getAttribute("cartDTO");
		//CartDTO cartDTO = cDatas.get(1);
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		System.out.println(memberDTO);
		AddressDTO addressDTO = (AddressDTO)request.getAttribute("addressDTO");
		System.out.println("payInfo 주소지 : "+addressDTO);
		
	%>
		<!-- BREADCRUMB -->
		<div id="breadcrumb" class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">
					<div class="col-md-12">
						<h3 class="breadcrumb-header">결제</h3>
					</div>
				</div>
				<!-- /row -->
			</div>
			<!-- /container -->
		</div>
		<!-- /BREADCRUMB -->

		<!-- SECTION -->
		<div class="section" >
			<!-- container -->
			<div class="container" >
				<!-- row -->
				<div class="row">
					<form action="paymentPage.do" method="POST">
						
					<div class="col-md-9" style="margin-left: 10%;">
						<!-- Billing Details -->
						
						<div class="billing-details"  >
							<div class="section-title">
								<h3 class="title">구매자 정보</h3>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="memberName" placeholder="이름" value="<%=memberDTO.getMemberName()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="email" name="memberEmail" placeholder="Email" value="<%=memberDTO.getMemberEmail()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="tel" name="memberPhNum" placeholder="전화번호" value="<%=memberDTO.getMemberPhNum()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsZipcode" placeholder="우편번호" value="<%=addressDTO.getAdrsZipcode()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsStreet" placeholder="도로명주소" value="<%=addressDTO.getAdrsStreet()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsLotNum" placeholder="지번주소" value="<%=addressDTO.getAdrsLotNum()%>" disabled>
							</div>
							<div class="form-group">
								<input class="input" type="text" name="adrsDetail" placeholder="상세주소" value="<%=addressDTO.getAdrsDetail()%>" disabled>
							</div>
						</div>
						<!-- /Billing Details -->

						<!-- Billing Details -->
						<br><br>
						<div class="billing-details">
						<table style="width: 100%; text-align: center;">
					        <thead>
					            <tr>
					                <th style="text-align: center;">상품</th>
					                <th style="text-align: center;">상품이름</th>
					                <th style="text-align: center;">구매개수</th>
					                <th style="text-align: center;">가격</th>
					            </tr>
					        </thead>
					        <%for(CartDTO cData:cDatas){ %>
					        
					        <tbody>
					            <tr>
					                <td><img src="<%=cData.getProductImg()%>" alt="img" style="width: 200px; height: 200px;"></td>
					                <td><%=cData.getProductName()%></td>
					                <td><%=cData.getCartProductCnt()%></td>
					                <td><%=cData.getProductPrice()%></td>
					            </tr>
					        </tbody>
					        
					        <%} %>
					        
					    </table>
					    </div>
						<!-- /Billing Details -->
						<div class="order-details">
							<div class="section-title text-center">
								<h3 class="title">Your Order</h3>
							</div>
							<div class="order-summary">
								<div class="order-col">
									<div><strong>PRODUCT</strong></div>
									<div><strong>TOTAL</strong></div>
								</div>
								<div class="order-products">
								<%for(int i=0; i<cDatas.size(); i++){ %>
									<div class="order-col">
										<div><%=cDatas.get(i).getProductName()%></div>
										<div style="text-align: right;"><%=cDatas.get(i).getProductPrice()%>원</div>
										<input type="hidden" name="productID" value="<%=cDatas.get(i).getProductID()%>">
										<input type="hidden" name="productName" value="<%=cDatas.get(i).getProductName()%>">
										<input type="hidden" name="productCnt" value="<%=cDatas.get(i).getCartProductCnt()%>">
										<input type="hidden" name="productPrice" value="<%=cDatas.get(i).getProductPrice()%>">
									</div>
									<%} %>
									<!-- <div class="order-col">
										<div>2x Product Name Goes Here</div>
										<div>$980.00</div>
									</div> -->
								</div>
								<div class="order-col">
									<div>배송비</div>
									<div><strong>FREE</strong></div>
								</div>
								<div class="order-col">
									<div><strong>TOTAL</strong></div>
									<%
									int total=0;
									for(CartDTO cData : cDatas){
										total += cData.getProductPrice()*cData.getCartProductCnt(); 
									
									%>
									<div style="text-align: right;"><strong class="order-total" ><%=cData.getProductPrice()%>원</strong></div>
									<input type="hidden" name="totalPrice" value="<%=total%>">
									<%} %>
								</div>
							</div>
							<div class="payment-method" style="align-items: center; display: flex;">
								<div style="display: inline-block; "><strong>결제수단</strong></div>
							   	<select class="input-select" id="pgSelect" name="pg" style="display: inline-block; position: absolute; right: 4%; ">
								 	<option value="kakaopay">카카오페이</option>
								 	<option value="tosspay">토스페이</option>
								 	<option value="html5_inicis">이니시스</option>
								</select>
							</div>
							
							
							<button type="submit" class="primary-btn order-submit" style="width: 50%; margin-left: 25%">결제하기</button>
						</div>
					</div>
					</form>
					<!-- Order Details -->
					
					<!-- /Order Details -->
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
