<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
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
<link type="text/css" rel="stylesheet" href="css/mypage.css" />
</head>
<body>
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
	
	
	
	
	<div class="big-box">
		<!-- aside Widget -->
		<div class="aside">
  		<h3 class="aside-title">
   		 <a href="mypageMemberUpdatePWCK.do">개인정보변경</a>
  		</h3>
		</div>
		<hr>
		<div class="aside">
  		<h3 class="aside-title">
   		 <a href="">주문내역조회</a>
  		</h3>
		</div>
		<hr>
		<div class="aside">
  		<h3 class="aside-title">
   		 <a href="">장바구니관리</a>
  		</h3>
		</div>
		<hr>
	<div class="aside">
  		<h3 class="aside-title">
   		 <a href="javascript:handleAddressManage()" id="addressManage">배송지관리</a>
  		</h3>
		</div>
</div>




	<div class="container">
		<!-- Heading -->
		<h1>본인 인증</h1>

		<form action="mypageMemberUpdate.do" method="POST"">
			
			<div class="input__block" style="padding-left: 27px; ">
				<label for="inputName">비밀번호 확인</label> 
				<input type="password" class="input"  name="memberPW" style=" display: inline-block;  width: 58%;" required>
				 <input type="submit" class="input" value="회원정보변경 입장" style="display: inline-block; width: 25%;"><br>
					
			</div>
		</form>


	</div>


	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


		
				
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/mypageAddress.js"></script>
</body>
</html>
