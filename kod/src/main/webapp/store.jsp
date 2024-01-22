<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*, java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>너의 목소리가 보여</title>

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
	<%
	
	ArrayList<ProductDTO> productCategoryDatas = (ArrayList<ProductDTO>) request.getAttribute("productCategoryDatas");
	System.out.print(productCategoryDatas);
	%>
	<!-- BREADCRUMB -->
	<div id="breadcrumb" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<ul class="breadcrumb-tree">
						<li><a href="main.do">Home</a></li>
						<li><a href="#">All Categories</a></li>
						<li><a href="#">Accessories</a></li>
						<li class="active">Headphones (227,490 Results)</li>
					</ul>
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
				<!-- ASIDE -->
				<div id="aside" class="col-md-3">
					<!-- aside Widget -->
					<div class="aside">
						<h3 class="aside-title">Categories</h3>
						<div class="checkbox-filter">
							<%
							for(int i=0; i<productCategoryDatas.size(); i++){
							%>
							<div class="input-checkbox">
								<input type="checkbox" id="category-<%=i+1%>" name="<%=productCategoryDatas.get(i).getProductCategory() %>" onclick="selectcheckbox()"> <label
									for="category-<%=i+1%>"> <span></span><%=productCategoryDatas.get(i).getProductCategory()%> <small>(<%=productCategoryDatas.get(i).getProductCnt() %>) </small>
								</label>
							</div>
							<%
							}
							%>
						</div>
					</div>
					<!-- /aside Widget -->

					<!-- aside Widget -->
					<div class="aside">
						<h3 class="aside-title">Price</h3>
						<div class="price-filter">
							<div id="price-slider"></div>
							<div class="input-number price-min">
								<input name="min" id="price-min" type="number"> <span
									class="qty-up">+</span> <span class="qty-down">-</span>
							</div>
							<span>-</span>
							<div class="input-number price-max">
								<input name="max" id="price-max" type="number"> <span
									class="qty-up">+</span> <span class="qty-down">-</span>
							</div>
						</div>
					</div>
					<!-- /aside Widget -->

					<!-- aside Widget -->
					<!-- <div class="aside">
						<h3 class="aside-title">Brand</h3>
						<div class="checkbox-filter">
							<div class="input-checkbox">
								<input type="checkbox" id="brand-1"> <label
									for="brand-1"> <span></span> SAMSUNG <small>(578)</small>
								</label>
							</div>
							<div class="input-checkbox">
								<input type="checkbox" id="brand-2"> <label
									for="brand-2"> <span></span> LG <small>(125)</small>
								</label>
							</div>
							<div class="input-checkbox">
								<input type="checkbox" id="brand-3"> <label
									for="brand-3"> <span></span> SONY <small>(755)</small>
								</label>
							</div>
							<div class="input-checkbox">
								<input type="checkbox" id="brand-4"> <label
									for="brand-4"> <span></span> SAMSUNG <small>(578)</small>
								</label>
							</div>
							<div class="input-checkbox">
								<input type="checkbox" id="brand-5"> <label
									for="brand-5"> <span></span> LG <small>(125)</small>
								</label>
							</div>
							<div class="input-checkbox">
								<input type="checkbox" id="brand-6"> <label
									for="brand-6"> <span></span> SONY <small>(755)</small>
								</label>
							</div>
						</div>
					</div> -->
					<!-- /aside Widget -->

					<!-- aside Widget -->
					<div class="aside">
						<h3 class="aside-title">Top selling</h3>
						<div class="product-widget">
							<div class="product-img">
								<img src="./img/product01.png" alt="">
							</div>
							<div class="product-body">
								<p class="product-category">Category</p>
								<h3 class="product-name">
									<a href="#">product name goes here</a>
								</h3>
								<h4 class="product-price">
									$980.00
									<del class="product-old-price">$990.00</del>
								</h4>
							</div>
						</div>

						<div class="product-widget">
							<div class="product-img">
								<img src="./img/product02.png" alt="">
							</div>
							<div class="product-body">
								<p class="product-category">Category</p>
								<h3 class="product-name">
									<a href="#">product name goes here</a>
								</h3>
								<h4 class="product-price">
									$980.00
									<del class="product-old-price">$990.00</del>
								</h4>
							</div>
						</div>

						<div class="product-widget">
							<div class="product-img">
								<img src="./img/product03.png" alt="">
							</div>
							<div class="product-body">
								<p class="product-category">Category</p>
								<h3 class="product-name">
									<a href="#">product name goes here</a>
								</h3>
								<h4 class="product-price">
									$980.00
									<del class="product-old-price">$990.00</del>
								</h4>
							</div>
						</div>
					</div>
					<!-- /aside Widget -->
				</div>
				<!-- /ASIDE -->

				<!-- STORE -->
				<div id="store" class="col-md-9">
					<!-- store top filter -->
					<div class="store-filter clearfix">
						<div class="store-sort">
							<label> Sort By: <select class="input-select">
									<option value="0">Popular</option>
									<option value="1">Position</option>
							</select>
							</label> <label> Show: <select class="input-select">
									<option value="0">20</option>
									<option value="1">50</option>
							</select>
							</label>
						</div>
						<ul class="store-grid">
							<li class="active"><i class="fa fa-th"></i></li>
							<li><a href="#"><i class="fa fa-th-list"></i></a></li>
						</ul>
					</div>
					<!-- /store top filter -->

					<!-- store products -->
					<div class="row">
					
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script>
$(document).ready(function(){
    $('.add-to-wishlist').on('click', function(){
        console.log('위시리스트 버튼 클릭됨');
        
        var productID = $(this).find('.productID').text();
        var heartIcon = $(this).find('#heartIcon');
        
        console.log('productID:', productID);
        
        $.ajax({
            type: "POST",
            url: 'IsWishedAction',
            data: { 'productID': productID },
            success: function(data){
                console.log(data);
                // 클릭 시 하트 아이콘 토글
                heartIcon.toggleClass('fa-heart-o fa-heart');
            },
            error: function(error){
                console.log("에러: " + error);
            }
        });
    });
});

</script>

<!--
V는 C한테
   ♥♡를 구분해야하니까
   1,0 등의 신호를 주세요.
C는 V한테 줘야되니까
   SELECTALL 해올적에
   SELECT ??? FROM
      ???에 1,0 등의 값을
      받아올수있도록 해달라고
      M한테 요청
M은 C한테 1,0 등의 값을 줘야하니까
   SQL문을 수정해야됨
   SELECTALL이 되는상황
  -->
					<!-- product -->
					<%
					ArrayList<WishListDTO> isWishedDatas = (ArrayList<WishListDTO>) request.getAttribute("isWishedDatas");
					for (WishListDTO isWishedData : isWishedDatas) {
					%>
					<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
						<div class="product">
					    <div class="product-body">
						    <div class="product-label" style="display: flex; justify-content: space-between; align-items: center;">
						        <span class="new" style="color: #D10024;"><strong>NEW</strong></span>
						        <div class="product-btns">
						            <button class="add-to-wishlist">
				                        <div class="productID" hidden><%=isWishedData.getProductID()%></div>
				                        <i class="fa <%= isWishedData.getIsWished() == 1 ? "fa-heart" : "fa-heart-o" %>" id="heartIcon"></i><span class="tooltipp">위시리스트에 추가</span>
				                    </button>
						        </div>
						    </div>
						</div>
					    <div class="product-img">
					        <img src="<%=isWishedData.getProductImg()%>" alt="">
					    </div>
					    <div class="product-body">
					        <p class="product-category"><%=isWishedData.getProductCategory()%></p>
					        <h3 class="product-name" style="height: 31px;">
					            <a href="productDetail.do?productId=<%=isWishedData.getProductID()%>"><%=isWishedData.getProductName()%></a>
					        </h3>
					        <h4 class="product-price"><%=isWishedData.getProductPrice()%><del class="product-old-price"></del></h4>
					        <div class="product-rating">
					            <%--평점 들어가는 라인 --%>
					        </div>
					    </div>
					    <div class="add-to-cart">
					        <button class="add-to-cart-btn">
					            <i class="fa fa-shopping-cart"></i> add to cart
					        </button>
					    </div>
					</div>
					</div>
					<%
					}
					%>
					<!-- /product -->
					<!-- /store products -->

					<!-- store bottom filter -->
					<div class="store-filter clearfix">
						<span class="store-qty">Showing 20-100 products</span>
						<ul class="store-pagination">
							<li class="active">1</li>
							<li><a href="#">2</a></li>
							<li><a href="#">3</a></li>
							<li><a href="#">4</a></li>
							<li><a href="#"><i class="fa fa-angle-right"></i></a></li>
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
	</div>

	<!-- NEWSLETTER -->
	<div id="newsletter" class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<div class="col-md-12">
					<div class="newsletter">
						<p>
							Sign Up for the <strong>NEWSLETTER</strong>
						</p>
						<form>
							<input class="input" type="email" placeholder="Enter Your Email">
							<button class="newsletter-btn">
								<i class="fa fa-envelope"></i> Subscribe
							</button>
						</form>
						<ul class="newsletter-follow">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- /row -->
		</div>
		<!-- /container -->
	</div>
	<!-- /NEWSLETTER -->

	<jsp:include page="util/footer.jsp"></jsp:include>

	<!-- jQuery Plugins -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/test.js"></script>

</body>
</html>
