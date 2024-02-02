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

		<title>장바구니 선택 페이지</title>

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
 		
 		<link type="text/css" rel="stylesheet" href="css/paySelect.css"/>

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
                <li>오늘출발 상품은 판매자 설정 시점에 따라 오늘출발 여부가 변경될 수 있으니 주문 시 꼭 다시 확인해 주시기 바랍니다.</li>
            </ul>
        </div>
        <form action="payInfo.do" method="POST" >
	        <table class="cart__list">
	                <thead>
	                    <tr> 
	                        <td><input type="checkbox" onclick='selectAll(this)'/ name = "" value=""> <b></b></td>
	                        <td colspan="2">상품정보</td>
	                        <td>옵션</td>
	                        <td>상품금액</td>
	                        <td>배송비</td>
	                    </tr>
	                </thead>
	                  <c:forEach var="cData" items="${cDatas}">
	                <tbody>
	                    <tr class="cart__list__detail">
	                        <td><input type="checkbox" name="selectedProducts" value="${cData.productID}"></td>
	                        <td><img src="${cData.productImg}" alt="product"></td>
	                        <td><a href="main.do">KOD스토어</a>
	                            <p>${cData.productName}</p>
	                            <span class="price">${cData.productPrice}원</span>
	                        </td>
	                        <td class="bseq_ea">
	                            <p>${cData.productName} / ${cData.cartProductCnt}개</p>
	                             	<button type ="button" onclick="fnCalCount('m',this);">-</button>
							        <input type="text" name="pop_out" value="${cData.cartProductCnt}" readonly="readonly" style="text-align:center;"/>
							        <button type="button" onclick="fnCalCount('p', this);">+</button>
	                            <!-- <button class="cart__list__optionbtn">주문조건 추가/변경</button> -->
	                        </td>
	                        <td><span class="price">${cData.productPrice*cData.cartProductCnt}원</span><br>
	                            <button class="cart__list__orderbtn">주문하기</button>
	                        </td>
	                        <td>무료</td>
	                    </tr>
	                </tbody>
	                </c:forEach>
	                <tfoot>
	                    <tr>
	                        <td colspan="3">
	                        	<button type="button" class="cart__list__optionbtn" onclick ="">선택상품 삭제</button>
	                       <!--      <button type="button" class="cart__list__optionbtn">선택상품 찜</button> --> 
	                        </td>
	                        <td></td>
	                        <td></td>
	                        <td></td>
	                    </tr>
	                </tfoot>
	            
	        </table>
	        <div class="cart__mainbtns">
	            <button class="cart__bigorderbtn left">쇼핑 계속하기</button>
	            <button class="cart__bigorderbtn right" onclick="payInfo.do">주문하기</button>
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
		<script>
			function submitForm(){
				var formData = new FormData(document.forms[0]);
				
				$.ajax({
					type : "POST",
					url : "payInfo.do",
					data : formData,
					processData : false,
					contentType : false,
					success : function(response){
						console.log("성공");
					},
					error : function(error){
						console.log("실패");
					}
				});
			}
		</script>
		<script>
		function fnCalCount(type, ths){
	    var $input = $(ths).parents("td").find("input[name='pop_out']");
	    var tCount = Number($input.val());
	    var tEqCount = Number($(ths).parents("tr").find("td.bseq_ea").html());
	    
	    if(type=='p'){
	        $input.val(Number(tCount)+1);
	        
	    }else{
	        if(tCount > 1) $input.val(Number(tCount)-1);    
	        }
		}
		
		function selectAll(selectAll)  {
			  const checkboxes 
			     = document.querySelectorAll('input[type="checkbox"]');
			  
			  checkboxes.forEach((checkbox) => {
			    checkbox.checked = selectAll.checked
			  })
			}
	</script>
	</body>
</html>
