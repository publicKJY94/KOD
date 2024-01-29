<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Price Range Slider</title>
<style>
#price-slider-container {
	width: 98%;
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
<body>

	<div id="price-slider-container">
		<div class="aside">
			<h3 class="aside-title">
				PRICE: <span id="price-output">50
					- 80</span>
			</h3>
			<form method="POST" action="" style="position: absolute; top: 0.5em; right: 1em;">
				<input type="hidden" name="maxPrice" value="" /> 
				<input type="hidden" name="minPrice" value="" /> 
				<label for="searchBtn" ><img alt="search" src="img/search.png" style="position: absolute; top: 0.5em; right: 1em;" width="30px" height="30px" /></label>
				<input type="submit" name="searchBtn" id="searchBtn" value="검색" style="display: none;" onclick="selectcheckbox()">
			</form>
			<div id="price-range"></div>
		</div>
	</div>

	<script>
		$(function() {
			// jQuery UI의 slider를 초기화
			$("#price-range").slider({
				range : true, // 두 개의 핸들을 허용
				min : 0,
				max : 100,
				values : [ 50, 80 ], // 초기값 설정
				slide : function(event, ui) {
					updatePriceDisplay(ui.values[0], ui.values[1]);
				}
			});

			// 초기화 함수 호출
			initialize();

			// 초기화 함수
			function initialize() {
				var initialValues = $("#price-range")
						.slider("option", "values");
				updatePriceDisplay(initialValues[0], initialValues[1]);
			}

			// 화면에 표시된 PRICE을 업데이트하는 함수
			function updatePriceDisplay(minPrice, maxPrice) {
				$("#price-output").text(minPrice + " - " + maxPrice);
				$("input[name=maxPrice]").attr("value", maxPrice);
				$("input[name=minPrice]").attr("value", minPrice);
			}
		});
	</script>
	<script src="js/test.js"></script>
</body>
</html>
