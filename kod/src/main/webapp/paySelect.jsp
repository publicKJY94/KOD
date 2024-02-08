<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="model.dto.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<!DOCTYPE html>
	<html lang="en">

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

		<title>장바구니 선택 페이지</title>

		<!-- Google font -->
		<link href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700" rel="stylesheet">

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

		<link type="text/css" rel="stylesheet" href="css/paySelect.css" />

		<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->

	</head>

	<body>

		<jsp:include page="util/header.jsp"></jsp:include>
		<jsp:include page="util/navigation.jsp"></jsp:include>
		<c:set var="cDatas" value="${cartDTO}" />
		<c:set var="memberDTO" value="${sessionScope.memberDTO}" />
		<section class="cart">
			<div class="cart__information">
				<ul>
					<li>장바구니 상품은 최대 30일간 저장됩니다.</li>
					<li>가격, 옵션 등 정보가 변경된 경우 주문이 불가할 수 있습니다.</li>
				</ul>
			</div>
			<form action="payInfo.do" method="POST">
				<table class="cart__list">
					<thead>
						<tr>
							<td><input type="checkbox" onclick='selectAll(this)' /> <b></b></td>
							<td colspan="2">상품정보</td>
							<td>옵션</td>
							<td>상품금액</td>
							<td>배송비</td>
							<td></td>
						</tr>
					</thead>
					<c:forEach var="cData" items="${cDatas}" varStatus="status">
						<tbody>
							<tr class="cart__list__detail">
								<td><input type="checkbox" name="selectedProducts" value="${cData.productID}"
										id="selectedCheckBox"></td>
								<td><img src="${cData.productImg}" alt="product"></td>
								<td>
									<a href="main.do">KOD스토어</a>
									<p>${cData.productName}</p>
									<span class="price" id="eachPrice">${cData.productPrice}원</span>
								</td>
								<td class="bseq_ea" style="text-align: center;">
									<p>${cData.productName}</p>
									<button type="button"
										onclick="fnCalCount('m', this, ${status.index});">-</button>
									<input type="text" id="changedCnt_${status.index}" name="pop_out"
										value="${cData.cartProductCnt}" readonly="readonly"
										style="text-align:center;" />
									<button type="button"
										onclick="fnCalCount('p', this, ${status.index});">+</button>
									<!-- <button class="cart__list__optionbtn">주문조건 추가/변경</button> -->
								</td>
								<td>
									<span class="price"
										id="totalPrice_${status.index}">${cData.sumProductPrice}원</span><br>
									<button class="cart__list__orderbtn">주문하기</button>
								</td>
								<td>무료</td>
								<td>
									<a href="cartDeleteEach.do?productID=${cData.productID}"
										class="cart__list__optionbtn">상품 삭제 </a>
								</td>
							</tr>
						</tbody>
					</c:forEach>
				</table>
				<div class="cart__mainbtns" style="float: right">
					<button class="cart__bigorderbtn right">주문하기</button>
				</div>
			</form>
				<form action="checkWished.do" method="POST" style="display: inline-block; ">
					<button class="cart__bigorderbtn left" style="width: ">쇼핑 계속하기</button>
				</form>
	
				<form action="cartDeleteAll.do" method="POST" style="display: inline-block;">
					<c:forEach var="cData" items="${cDatas}" varStatus="status">
						<input type="hidden" name="cartId" value="${cData.cartID}">
					</c:forEach>
					<div class="cart__mainbtns">
						<button class="cart__bigorderbtn right" style="background-color: ">장바구니 비우기</button>
					</div>
				</form>
		</section>
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
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
									tempor incididunt ut.</p>
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
								Copyright &copy;
								<script>document.write(new Date().getFullYear());</script> All rights reserved |
								This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by
								<a href="https://colorlib.com" target="_blank">Colorlib</a>
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
		<script>
			function submitForm() {
				var formData = new FormData(document.forms[0]);

				$.ajax({
					type: "POST",
					url: "payInfo.do",
					data: formData,
					processData: false,
					contentType: false,
					success: function (response) {
						console.log("성공");
					},
					error: function (error) {
						console.log("실패");
					}
				});
			}
		</script>
		<script>
			function updateCart(productId, productCnt, index) {
				$.ajax({
					type: 'POST',
					url: 'cartUpdateActionServlet', // 장바구니 업데이트를 처리할 서블릿 URL
					dataType: 'json',
					data: {
						productId: productId,
						productCnt: productCnt

					},
					success: function (response) {

						var changedCnt = response;
						console.log('장바구니 업데이트 성공');
						console.log(response);
						console.log('cart 변경수량 :  ' + changedCnt);

						$('#changedCnt_' + index).val(changedCnt);

						var totalPrice = changedCnt * parseInt($('#eachPrice').text().replace('원', ''));
						$('#totalPrice_' + index).text(totalPrice + '원');

					},
					error: function (xhr, status, error) {
						// 오류 발생 시 추가 로직 작성
						console.error('장바구니 업데이트 오류:', status, error);
					}
				});
			}


			function fnCalCount(type, ths, index) {
				console.log('수량변경 진입');
				// 해당 상품의 ID 가져오기
				var productId = $(ths).closest('.cart__list__detail').find('input[name="selectedProducts"]').val();

				// 해당 상품의 수량 가져오기
				var $input = $(ths).parents("td").find("input[name='pop_out']");
				var productCnt = $input.val();

				// 변경된 수량 계산
				var newProductCnt;

				if (type == 'p') {
					newProductCnt = Number(productCnt) + 1;
					if (newProductCnt > 10) {
						newProductCnt = 10;
						alert('최대수량은 10개를 넘길 수 없습니다');
					}
				}
				else {
					if (productCnt > 1) {
						newProductCnt = Number(productCnt) - 1;
					} else {
						newProductCnt = 1; // 최소 수량은 1로 설정
					}
				}
				// 비동기처리 함수
				updateCart(productId, newProductCnt, index);
			}

			function selectAll(selectAll) {
				const checkboxes
					= document.querySelectorAll('input[type="checkbox"]');

				checkboxes.forEach((checkbox) => {
					checkbox.checked = selectAll.checked
				})
			}
		</script>
	</body>

	</html>