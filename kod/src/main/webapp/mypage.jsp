<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" %>
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
			<h3 class="aside-title">개인정보변경</h3><br>
		</div>
		<hr>
		<div class="aside">
			<h3 class="aside-title">주문내역조회</h3>
		</div>
		<hr>

		<div class="aside">
			<h3 class="aside-title">장바구니관리</h3>
		</div>

		<hr>
		<a href="address.do">
			<div class="aside">
				<h3 class="aside-title">배송지 관리</h3>
			</div>
		</a>
		<hr>
		  
	</div>
	
			<div class="content">
<%
	ArrayList<AddressDTO> aDatas = (ArrayList<AddressDTO>) request.getAttribute("addressDTO");
	if (aDatas != null) {
		for (AddressDTO addressDTO : aDatas) {
%>
			<div class="content box" style="display: flex; flex-direction: column;">
					<div style="margin-bottom: 5px;">
						<th style="text-align: center;"> 주소지 이름 :</th>
						<td><%= addressDTO.getAdrsName() %></td>
					</div>
					<div style="margin-bottom: 5px;">
						<th style="text-align: center;"> 우편 번호 :</th>
						<td><%= addressDTO.getAdrsZipcode() %></td>
					</div>
					<div style="margin-bottom: 5px;">
						<th style="text-align: center;"> 도로명 주소 :</th>
						<td><%= addressDTO.getAdrsStreet() %></td>
					</div>
					<div style="margin-bottom: 5px;">
						<th style="text-align: center;"> 상세 주소 :</th>
						<td><%= addressDTO.getAdrsDetail() %></td>
					</div>
					<div style="margin-bottom:5px;">
						<button style="margin-left: 85%">수정</button>
						<button>삭제</button>
					</div>
			</div>
		<%
		}
		%>
		<% if(aDatas.size() < 5) { %>
    	<button style="margin-left: 20%; margin-bottom: 20px;">배송지 추가하기</button>
		<% } else { %>
    	<!-- 조건이 충족되지 않을 때의 처리 부분 -->
		<% } %>
		
		<% 
	}
%>
	<div class="billing-details">
						<table style="width: 100%; text-align: center;">
					      
					        
					    </table>
					    </div>
	</div>
	
	<!-- End of big-box -->
	
</body>
</html>