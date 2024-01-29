<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>navigation</title>
</head>
<body>
<!-- NAVIGATION -->
		<nav id="navigation">
			<!-- container -->
			<div class="container">
				<!-- responsive-nav -->
				<div id="responsive-nav">
					<!-- NAV -->
					<ul class="main-nav nav navbar-nav">
						<li class="active"><a href="main.do">Home</a></li>
						<li><a href="checkWished.do">스피커</a></li>
						<li><a href="checkWished.do">헤드셋/헤드폰</a></li>
						<li><a href="checkWished.do">이어폰</a></li>
					</ul>
					<!-- /NAV -->
				</div>
				<!-- /responsive-nav -->
			</div>
			<!-- /container -->
		</nav>
		<!-- /NAVIGATION -->
</body>

<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script type="text/javascript">
	var lists = document.querySelectorAll(".nav li");
	function clickEvent(){
		for(int i = 0 ; i < lists.length; i++){
			lists[i].classList.remove("active");
		}
		this.classList.add("active");
	}
</script>
</html>
