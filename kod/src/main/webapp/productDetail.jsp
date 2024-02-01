<%@page import="model.dto.ReviewDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.WishListDTO"%>
<%@page import="model.dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>Electro - HTML Ecommerce Template</title>

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


	<script src="https://code.jquery.com/jquery-3.7.1.js"
		integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
		crossorigin="anonymous"></script>
	<script>
$(document).ready(function(){
	  $(".add-to-wishlist2").on("click", function(e){
	    e.preventDefault(); // 기본 클릭 이벤트를 중단하여 링크가 이동하는 것을 방지

	    console.log("위시리스트 버튼 클릭됨");
	    
	    var productID = $(this).find(".productID").text();
	    var heartIcon = $(this).find("#heartIcon");
	    console.log("productID", productID);
	    
	    $.ajax({
	      type: "POST",
	      url: "isWishedAction",
	      data: {"productID": productID},
	      success: function(data){
	        console.log(data);
	        heartIcon.toggleClass('fa-heart-o fa-heart');
	        
	        var updatedWishListCnt = parseInt(data);
	        $(".wishListCnt").text(updatedWishListCnt);
	        console.log("updatedWishListCnt >> " + updatedWishListCnt);
	        
		    $.ajax({
			      type: "POST",
			      url: "wishTotalCntAction",
			      data: {"productID": productID},
			      success: function(data){
			        console.log(data);

			        var updatedwishTotalCnt = parseInt(data);
			        $(".wishTotalCnt").text(updatedwishTotalCnt);
			        console.log("updatedwishTotalCnt >> " + updatedwishTotalCnt);

			      },
			      error: function(error){
			        console.log("에러: " + error);
			      } 
			    });
	        
	      },
	      error: function(error){
	        console.log("에러: " + error);
	      } 
	    });
	    
	  });
	});

</script>

	<!-- <script>
$(document).ready(function(){
	  $(".add-to-wishlist").on("click", function(e){
	    e.preventDefault(); // 기본 클릭 이벤트를 중단하여 링크가 이동하는 것을 방지

	    console.log("위시리스트 버튼 클릭됨");
	    
	    var productID = $(this).find(".productID").text();
	    var heartIcon = $(this).find("#heartIcon");
	    console.log("productID", productID);
	    
	    $.ajax({
	      type: "POST",
	      url: "isWishedAction",
	      data: {"productID": productID},
	      success: function(data){
	        console.log(data);
	        heartIcon.toggleClass('fa-heart-o fa-heart');
	        
	        var updatedWishListCnt = parseInt(data);
	        $(".wishListCnt").text(updatedWishListCnt);
	        console.log("updatedWishListCnt >> " + updatedWishListCnt);
	        
	      },
	      error: function(error){
	        console.log("에러: " + error);
	      } 
	    });
	  });
	});

</script> -->
	<%
	WishListDTO productWishDetailData = (WishListDTO) request.getAttribute("productWishDetailData");
	%>
	<!-- SECTION -->
	<div class="section">
		<!-- container -->
		<div class="container">
			<!-- row -->
			<div class="row">
				<!-- Product main img -->
				<div class="col-md-5 col-md-push-2">
					<div id="product-main-img">
						<div class="product-preview">
							<img src="<%=productWishDetailData.getProductImg()%>" alt="">
						</div>

						<!-- <div class="product-preview">
								<img src="./img/product03.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product06.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product08.png" alt="">
							</div> -->
					</div>
				</div>
				<!-- /Product main img -->

				<!-- Product thumb imgs -->
				<div class="col-md-2  col-md-pull-5">
					<div id="product-imgs">
						<div class="product-preview">
							<img src="<%=productWishDetailData.getProductImg()%>" alt="">
						</div>

						<!-- <div class="product-preview">
								<img src="./img/product03.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product06.png" alt="">
							</div>

							<div class="product-preview">
								<img src="./img/product08.png" alt="">
							</div> -->
					</div>
				</div>
				<!-- /Product thumb imgs -->

				<!-- Product details -->
				<div class="col-md-5">
					<div class="product-details">
						<h2 class="product-name"><%=productWishDetailData.getProductName()%></h2>
						<div>
							<div class="product-rating">
								<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star"></i> <i class="fa fa-star"></i> <i
									class="fa fa-star-o"></i>
							</div>
							<a class="review-link" href="#">10 Review(s) | Add your
								review</a>
						</div>
						<div>
							<h3 class="product-price"><%=productWishDetailData.getProductPrice()%><del
									class="product-old-price"></del>
							</h3>
							<span class="product-available">In Stock</span>
						</div>
						<p><%=productWishDetailData.getProductInfo()%></p>


						<form method="POST" action="cartInsert.do">
							<div class="add-to-cart">

								<div class="qty-label">
									수량
									<div class="input-number">
										<input type="hidden" name="productID"
											value="<%=productWishDetailData.getProductID()%>"> <input
											type="hidden" name="productName"
											value="<%=productWishDetailData.getProductName()%>">
										<input type="hidden" name="productPrice"
											value="<%=productWishDetailData.getProductPrice()%>">
										<input name="purchaseCnt" type="number"> <span
											class="qty-up">+</span> <span class="qty-down">-</span>
									</div>
								</div>
								<button class="add-to-cart-btn" type="submit">
									<i class="fa fa-shopping-cart"></i>장바구니 담기
								</button>
								<button class="buy-now add-to-cart-btn" type="submit">
									<i class="fa fa-shopping-cart"></i>구매하기
								</button>
							</div>
						</form>
						<ul class="product-btns">
							<li><a href="#" class="add-to-wishlist2"> <i
									class="fa <%=productWishDetailData.getIsWished() == 1 ? "fa-heart" : "fa-heart-o"%>"
									id="heartIcon"></i> add to wishList <span class="productID"
									style="display: none;"><%=productWishDetailData.getProductID()%></span>
							</a></li>
							<%
							int wishTotalCnt = (int) request.getAttribute("wishTotalCnt");
							Integer wishTotalCntObj = (Integer) request.getAttribute("wishTotalCnt");
							wishTotalCnt = (wishTotalCntObj != null) ? wishTotalCntObj : wishTotalCnt;

							String updatedWishTotalCntStr = (String) request.getAttribute("updatedWishListCnt");
							int updatedWishTotalCnt = wishTotalCnt; // 기본값 설정

							if (updatedWishTotalCntStr != null && !updatedWishTotalCntStr.isEmpty()) {
								try {
									updatedWishTotalCnt = Integer.parseInt(updatedWishTotalCntStr);
								} catch (NumberFormatException e) {
									e.printStackTrace(); // 또는 다른 로깅 방식을 사용할 수 있습니다.
								}
							}
							%>
							<Strong><span class="wishTotalCnt"
								style="padding-left: 10px"><%=wishTotalCnt%></span></Strong>
						</ul>

						<ul class="product-links">
							<li>Category:</li>
							<li><a href="#"><%=productWishDetailData.getProductCategory()%></a></li>
						</ul>

						<ul class="product-links">
							<li>Share:</li>
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							<li><a href="#"><i class="fa fa-envelope"></i></a></li>
						</ul>

					</div>
				</div>
				<!-- /Product details -->





				<!-- Product tab -->
				<div class="col-md-12">
					<div id="product-tab">
						<!-- product tab nav -->
						<ul class="tab-nav">
							<li class="active"><a data-toggle="tab" href="#tab1">Reviews
									(${productReviewDatas.size()})</a></li>
						</ul>
						<!-- /product tab nav -->

						<!-- product tab content -->
						<div class="tab-content">
							<!-- tab1  -->
							<div id="tab1" class="tab-pane fade in active">
								<div class="row">
									<div class="col-md-12">

										<!-- tab3  -->
										<div id="tab3" class="tab-pane fade in">
											<div class="row">
												<!-- Rating -->
												<div class="col-md-3">
													<div id="rating">
														<div class="rating-avg">
															<span>리뷰평점 : ${reviewAvgScore}</span>
															<div class="rating-stars" id="averageRatingStars">
																<!-- 별의 개수를 동적으로 조절할 부분 -->
															</div>
														</div>

														<script>
    // 리뷰 평균 점수
    var avgScore = ${reviewAvgScore};

    // 별의 최대 개수
    var maxStars = 5;

    // 별의 HTML 코드 생성
    var starHtml = '';
    for (var i = 1; i <= maxStars; i++) {
        if (i <= avgScore) {
            starHtml += '<i class="fa fa-star"></i>';
        } else {
            starHtml += '<i class="fa fa-star-o"></i>';
        }
    }

    // 생성된 별의 HTML을 평점을 표시할 div에 삽입
    document.getElementById('averageRatingStars').innerHTML = starHtml;
</script>
														<ul class="rating">
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${fiveScoreRatio}%;"></div>
																</div> <span class="sum">${fiveScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${fourScoreRatio}%;%;"></div>
																</div> <span class="sum">${fourScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star"></i> <i class="fa fa-star-o"></i> <i
																		class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${threeScoreRatio}%;%;"></div>
																</div> <span class="sum">${threeScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star"></i> <i
																		class="fa fa-star-o"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${twoScoreRatio}%;%;"></div>
																</div> <span class="sum">${twoScoreCount }</span>
															</li>
															<li>
																<div class="rating-stars">
																	<i class="fa fa-star"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i> <i class="fa fa-star-o"></i>
																	<i class="fa fa-star-o"></i>
																</div>
																<div class="rating-progress">
																	<div style="width: ${oneScoreRatio}%;%;"></div>
																</div> <span class="sum">${oneScoreCount }</span>
															</li>
														</ul>
													</div>
												</div>
												<!-- /Rating -->

												<!-- Reviews -->
												<div class="col-md-6">
													<div id="reviews">
														<ul class="reviews">
															<c:forEach var="review" items="${currentPageProducts}">
																<li>
																	<div class="review-heading">
																		<h5 class="name">${review.memberName}</h5>
																		<p class="date">${review.reviewDate}</p>
																		<div class="review-rating"
																			id="ratingContainer_${review.reviewID}">
																			<!-- Default: 5 empty stars -->
																			<i class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i> <i
																				class="fa fa-star-o empty"></i>
																		</div>

<script>
    // 리뷰 레이팅 값 (1부터 5까지의 정수)
    var reviewRating_${review.reviewID} = ${review.reviewScore};

    // 별 아이콘의 상태를 업데이트하는 함수
    function updateRating_${review.reviewID}(rating) {
        var ratingContainer = document.getElementById("ratingContainer_${review.reviewID}");
        var stars = ratingContainer.children;

        for (var i = 0; i < stars.length; i++) {
            if (i < rating) {
                stars[i].classList.remove("fa-star-o", "empty");
                stars[i].classList.add("fa-star");
            } else {
                stars[i].classList.remove("fa-star");
                stars[i].classList.add("fa-star-o", "empty");
            }
        }
    }

    // 리뷰 레이팅 값으로 별 아이콘 업데이트
    updateRating_${review.reviewID}(reviewRating_${review.reviewID});
</script>
																	</div>
																	<div class="review-body">
																		<h5>${review.reviewTitle}</h5>
																		<p>${review.reviewContent}</p>
																	</div>
																</li>
															</c:forEach>
														</ul>


													</div>
												</div>
												<!-- /Reviews -->
												
												
<!-- 부트스트랩 CSS 파일 -->

<!-- jQuery 파일 -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>

<!-- 부트스트랩 JavaScript 파일 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
												
												
												<!-- Review Form -->
												<div class="col-md-3">
												    <c:forEach var="review" items="${currentPageProducts}">
												        <div class="image-container">
												            <img alt=""
												                src='<c:url value="uploads/${review.reviewImg}" />'
												                style="height: 70px; width: 70px;"
												                class="img-thumbnail" data-toggle="modal" data-target="#myModal">
												            <div style="height: 30px"></div>
												        </div>
												    </c:forEach>
												</div>
												
												<!-- 모달 창 -->
												<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
												    <div class="modal-dialog" role="document">
												        <div class="modal-content">
												            <div class="modal-header">
												                <h5 class="modal-title" id="exampleModalLabel">이미지 미리보기</h5>
												                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
												                    <span aria-hidden="true">&times;</span>
												                </button>
												            </div>
												            <div class="modal-body">
												                <img id="previewImage" alt="" style="width: 100%;">
												            </div>
												        </div>
												    </div>
												</div>
												
<script>
    $(document).ready(function () {
        // 이미지를 클릭했을 때 모달에 이미지 로드
        $('.img-thumbnail').click(function () {
            var imgSrc = $(this).attr('src');
            $('#previewImage').attr('src', imgSrc);
        });
    });
</script>
												<!-- /Review Form -->

											</div>
										</div>
										<!-- /tab3  -->
										<!-- 정적인 페이징 -->
										<ul class="reviews-pagination">
											<c:forEach begin="1" end="${totalPages}" varStatus="loop">
												<li
													<c:if test="${loop.index == currentPage}">class="active"</c:if>>
													<a href="productDetail.do?productID=${param.productID}&productCategory=${param.productCategory}&page=${loop.index}">${loop.index}</a>
												</li>
											</c:forEach>
										</ul>
									</div>
									<!-- /product tab content  -->
								</div>
							</div>
							<!-- /product tab -->

						</div>
						<!-- /row -->
					</div>
					<!-- /container -->
				</div>
				<!-- /SECTION -->

				<!-- Section -->
				<div class="section">
					<!-- container -->
					<div class="container">
						<!-- row -->
						<div class="row">


							<div class="col-md-12">
								<div class="section-title text-center">
									<h3 class="title">(로그인)한회원 연령에 따라 상품추천 && (비로그인)KOD사이트에 가장
										많이 가입한 나이대의 인기상품 추천</h3>
								</div>
							</div>

							<%
							ArrayList<WishListDTO> productWishDatas = (ArrayList<WishListDTO>) request.getAttribute("productWishDatas");
							%>

							<!-- Products tab & slick -->
							<div class="col-md-12">
								<div class="row">
									<div class="products-tabs">
										<!-- tab -->
										<div id="tab1" class="tab-pane active">
											<div class="products-slick" data-nav="#slick-nav-1">
												<%
												for (WishListDTO data : productWishDatas) {
												%>
												<!-- product -->
												<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
													<div class="product">
														<div class="product-body">
															<div class="product-label"
																style="display: flex; justify-content: space-between; align-items: center;">
																<span class="new" style="color: #D10024;"><strong>NEW</strong></span>
																<div class="product-btns">
																	<button class="add-to-wishlist">
																		<div class="productID" hidden><%=data.getProductID()%></div>
																		<i
																			class="fa <%=data.getIsWished() == 1 ? "fa-heart" : "fa-heart-o"%>"
																			id="heartIcon"></i><span class="tooltipp">위시리스트에
																			추가</span>
																	</button>
																</div>
															</div>
														</div>
														<div class="product-img">
															<img src="<%=data.getProductImg()%>" alt="">
														</div>
														<div class="product-body">
															<p class="product-category"><%=data.getProductCategory()%></p>
															<h3 class="product-name" style="height: 31px;">
																<a
																	href="productDetail.do?productID=<%=data.getProductID()%>"><%=data.getProductName()%></a>
															</h3>
															<h4 class="product-price"><%=data.getProductPrice()%><del
																	class="product-old-price"></del>
															</h4>
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


											</div>
											<div id="slick-nav-1" class="products-slick-nav"></div>
										</div>
										<!-- /tab -->
									</div>
								</div>
							</div>
							<!-- Products tab & slick -->

						</div>
						<!-- /row -->
					</div>
					<!-- /container -->
				</div>
				<!-- /Section -->


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
										<input class="input" type="email"
											placeholder="Enter Your Email">
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
				<script src="js/jquery.min.js"></script>
				<script src="js/bootstrap.min.js"></script>
				<script src="js/slick.min.js"></script>
				<script src="js/nouislider.min.js"></script>
				<script src="js/jquery.zoom.min.js"></script>
				<script src="js/main.js"></script>
</body>
</html>
