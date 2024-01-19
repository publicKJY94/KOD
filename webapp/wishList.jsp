<%@page import="model.dto.MemberDTO"%>
<%@page import="model.dto.WishListDTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
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

<title>위시리스트</title>
</head>
<body>
<jsp:include page="util/header.jsp"></jsp:include>
		
		<div class="section">
			<!-- container -->
			<div class="container">
				<!-- row -->
				<div class="row">

					<!-- section title -->
					<div class="col-md-12">
						<div class="section-title">
							<h3 class="title">
								<div class="box-page-count" style="display: flex; align-items: center; ">
									<div class="l-cont">
									<%String memberID=((MemberDTO)session.getAttribute("memberDTO")).getMemberID(); %>
									<div><%=memberID %></div>
			                      		<%
			                      		ArrayList<WishListDTO> wishListDatas = (ArrayList<WishListDTO>) request.getAttribute("wishListDatas");
			                      		int wishListCnt = wishListDatas.size();
			                      		%>
										<storng class="tit">총 <span><%=wishListCnt %></span>개 상품</storng>
									</div>
								</div>
							</h3>
							<div class="section-nav">
								<ul class="section-tab-nav tab-nav">
									<li class="active">
										<div class="r-cont">
											<button class="btn-underline" id="soldOutDelBtn" data-di-id="#soldOutDelBtn">
												<span>품절상품 삭제</span>
											</button>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
					<!-- /section title -->
					
					
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




					<!-- Products tab & slick -->
<div class="col-md-12">
    <div class="row">
        <div class="products-tabs">
            <!-- tab -->
            <div id="tab1" class="tab-pane active">
                <div class="products-slick slick-initialized slick-slider" data-nav="#slick-nav-1" style="display: flex; flex-wrap: wrap;">
                    <!-- /product -->
                    <%
                        if (wishListDatas == null || wishListDatas.isEmpty()) {
                            out.println("위시리스트 목록이 없습니다.");
                        } else {
                            for (WishListDTO wishList : wishListDatas) {
                                if (wishList != null) {
                    %>
                                    <!-- product -->
                                    <div class="product" style="flex: 0 0 calc(33.33% - 20px); margin: 10px;">
	                                    <div class="product-body">
										    <div class="product-label" style="display: flex; justify-content: space-between; align-items: center;">
										        <span class="new" style="color: #D10024;"><strong>NEW</strong></span>
										        <div class="product-btns">
										             <button class="add-to-wishlist">
										                <div class="productID" hidden><%=wishList.getProductID()%></div>
										                <i class="fa fa-heart" id="heartIcon"></i><span class="tooltipp">위시리스트에 추가</span>
										            </button>
										        </div>
										    </div>
										</div>
                                        <div class="product-img">
                                            <img src="<%=wishList.getProductImg()%>" alt="Product Image" />
                                        </div>
                                        <div class="product-body">
                                            <p class="product-category"><%=wishList.getProductCategory()%></p>
                                            <h3 class="product-name"><a href="#" tabindex="-1"><%=wishList.getProductName()%></a></h3>
                                            <h4 class="product-price"><%=wishList.getProductPrice() %></h4>
                                        </div>
                                    </div>
                    <%
                                } else {
                    %>
                                    <div class="product" style="flex: 0 0 calc(33.33% - 20px); margin: 10px;">
                                        <input class="info" type="text" name="content" disabled value="하트를 눌러 위시리스트를 추가해보세요." /> <br>
                                    </div>
                    <%
                                }
                            }
                        }
                    %>
                </div>
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
		
		




<jsp:include page="util/footer.jsp"></jsp:include>

</body>
</html>