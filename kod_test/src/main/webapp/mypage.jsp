<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>마이 페이지</title>

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

<!--  my page bigbox -->
<link type="text/css" rel="stylesheet" href="css/mypage.css"/>

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


	<div class="big-box">
        <!-- aside Widget -->
        <div class="aside">
            <h3 class="aside-title">개인정보변경</h3>
            <div class="price-filter">
                <div id="price-slider"></div>
            </div>
        </div>
        <div class="aside">
            <h3 class="aside-title">주문내역조회</h3>
            <div class="price-filter">
                <div id="price-slider"></div>
            </div>
        </div>
        <!-- /aside Widget -->

        <div class="aside">
            <h3 class="aside-title">장바구니관리</h3>
            <div class="product-widget"></div>
        </div>

        <div class="aside">
            <h3 class="aside-title">
                <a href="addressPage.do">배송지 관리</a>
            </h3>
            <div class="price-filter">
                <div id="price-slider"></div>
            </div>
        </div>
    </div> <!-- End of big-box -->
</body>
</html>