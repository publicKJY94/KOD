<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style>
#price-slider-container {
	width: 100%;
	margin: 2px;
}

#price-range {
	width: 100%;
}

#price-output {
	font-size: 18px;
	margin-top: 10px;
}
label:hover{
	cursor: pointer;
}
</style>

</head>
	<!-- jQuery Plugins -->
	<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- <script src="js/jquery.min.js"></script> -->
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/test.js"></script>
<body>
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
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
						<c:forEach var="productCategoryData" items="${productCategoryDatas}" varStatus="i">
						
							<div class="input-checkbox">
								<input type="checkbox" id="category-${i.index+1}"
									name="${productCategoryData.productCategory}"
									> <label
									for="category-${i.index+1}"> <span></span>${productCategoryData.productCategory}
									<small>
									(<fmt:formatNumber value="${productCategoryData.productCnt}" type="currency" pattern="#,###" />)
									<fmt:setLocale value="ko_KR"/>
								</small>
								</label>
							</div>
						</c:forEach>
						</div>
					</div>
					<!-- /aside Widget -->
					<div class="aside">
						<jsp:include page="slider.jsp"></jsp:include>
					</div>
					<!-- aside Widget -->
					<!-- <div class="aside">
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
					</div> -->


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
						<c:forEach var="productRankData" items="${orderRankDatas}">
						<div class="product-widget">
							<div class="product-img">
								<img src="${productRankData.productImg}" alt="">
							</div>
							<div class="product-body">
								<p class="product-category">${productRankData.productCategory}</p>
								<h3 class="product-name">
									<a href="productDetail.do?productID=${productRankData.productID}">${productRankData.productName}</a>
								</h3>
								<h4 class="product-price">
									<fmt:setLocale value="ko_KR"/>
									<fmt:formatNumber value="${productRankData.productPrice}" type="currency"/>
									<!-- <del class="product-old-price">$990.00</del> -->
								</h4>
							</div>
						</div>
						</c:forEach>
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
                
                var updatedWishListCnt = parseInt(data); // data가 업데이트된 카운트를 받아와야합니다.
                $('.wishListCnt').text(updatedWishListCnt); // 위시리스트의 개수를 업데이트해줌
                console.log("updatedWishListCnt >> "+updatedWishListCnt)
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
    ArrayList<WishListDTO> currentPageProducts = (ArrayList<WishListDTO>) request.getAttribute("currentPageProducts");
	int startIndex = 0;
	int endIndex = currentPageProducts.size();
	for (WishListDTO isWishedData : currentPageProducts) {
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
                    <a href="productDetail.do?productID=<%=isWishedData.getProductID()%>&productCategory=<%=isWishedData.getProductCategory()%>"><%=isWishedData.getProductName()%></a>
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
    <span class="store-qty">Showing <%=startIndex + 1%> - <%=endIndex%> products</span>
    <ul class="store-pagination">
        <%-- 이전 페이지 링크 --%>
        <% if ((int)request.getAttribute("currentPage") > 1) { %>
            <li><a href="checkWished.do?page=<%=(int)request.getAttribute("currentPage") - 1 %>"><i class="fa fa-angle-left"></i></a></li>
        <% } %>

        <%-- 페이지 번호 출력 --%>
        <% for (int i = 1; i <= (int)request.getAttribute("totalPages"); i++) { %>
            <li class="<%= (i == (int)request.getAttribute("currentPage")) ? "active" : "" %>">
                <a href="checkWished.do?page=<%=i%>"><%=i%></a>
            </li>
        <% } %>

        <%-- 다음 페이지 링크 --%>
        <% if ((int)request.getAttribute("currentPage") < (int)request.getAttribute("totalPages")) { %>
            <li><a href="checkWished.do?page=<%=(int)request.getAttribute("currentPage") + 1 %>"><i class="fa fa-angle-right"></i></a></li>
        <% } %>
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

	<jsp:include page="util/footer.jsp"></jsp:include>



</body>
</html>
