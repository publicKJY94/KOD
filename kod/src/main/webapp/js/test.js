function selectcheckbox() {
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMax = document.querySelector('#maxPrice'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMin = document.querySelector('#minPrice'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var lists = [];
	checkboxes.forEach(function(checkbox) {
		lists.push(checkbox.name); // 체크박스의 name 속성 값 가져오기
	});
	var lists2 = [];
	var productIDs = document.querySelectorAll("input[name=productID]");
	productIDs.forEach(
		function(product) {
			lists2.push(product.value);
		}
	);

	var max = inputMax.value;
	var min = inputMin.value;
	var categoryList = JSON.stringify(lists);
	selectProduct(categoryList, max, min);
}
let won = Intl.NumberFormat('ko-KR', {
	style: 'currency',
	currency: 'KRW',
});
function selectProduct(categoryList, max, min) {
	console.log(categoryList + "max:" + max + "min:" + min);
	$.ajax({
		type: "POST",
		url: "test",
		data: {
			"categoryList": categoryList,
			"max": max,
			"min": min
		},
		dataType: "json",
		success: function(productDTO) {
			var product = $('div#store div.row');
			console.log("ajax요청 성공");
			console.log(productDTO);
			var elem = "";
			$.each(productDTO, function(index, product) {
				elem += `
					<div class="col-md-4 col-xs-6" style="margin-top: 30px;">
							<div class="product">
								<div class="product-body">
									<div class="product-label"
										style="display: flex; justify-content: space-between; align-items: center;">
										<span class="new" style="color: #D10024;"><strong>NEW</strong></span>
										<div class="product-btns">
											<button class="add-to-wishlist">
												<div class="productID" hidden>${product.productID}</div>`
				if (product.isWished != 0) {
					elem += '<i class="fa fa-heart" id="heartIcon" onclick="wishtoggle()"></i>'
				} else {
					elem += '<i class="fa fa-heart-o" id="heartIcon" onclick="wishtoggle()"></i>'
				}
				elem += `
												<span class="tooltipp">위시리스트에 추가</span>
											</button>
										</div>
									</div>
								</div>
								<div class="product-img">
									<img src="${product.productImg}" alt="">
								</div>
								<div class="product-body">
									<p class="product-category">${product.productCategory}</p>
									<h3 class="product-name" style="height: 31px;">
										<a
											href="productDetail.do?productID=${product.productID}&productCategory=${product.productCategory}">${product.productName}</a>
									</h3>
									<h4 class="product-price">`
				elem += won.format(product.productPrice);
				elem += `</h4>
								</div>
								<div class="add-to-cart">
									<button class="add-to-cart-btn">
										<i class="fa fa-shopping-cart"></i> add to cart
									</button>
								</div>
							</div>
						</div>`;
			});
			product.html(elem);
		},
		error: function(err) {
			console.log(err.status);
			console.log('ggg');
		}

	});
}
function wishtoggle() {
	$('.add-to-wishlist').on('click', function() {
		console.log('위시리스트 버튼 클릭됨');

		var productID = $(this).find('.productID').text();
		var heartIcon = $(this).find('#heartIcon');
		console.log('productID:', productID);

		$.ajax({
			type: "POST",
			url: 'isWishedAction',
			data: { 'productID': productID },
			success: function(data) {
				console.log(data);
				// 클릭 시 하트 아이콘 토글
				heartIcon.toggleClass('fa-heart-o fa-heart');

				var updatedWishListCnt = parseInt(data); // data가 업데이트된 카운트를 받아와야합니다.
				$('.wishListCnt').text(updatedWishListCnt); // 위시리스트의 개수를 업데이트해줌
				console.log("updatedWishListCnt >> " + updatedWishListCnt)
			},
			error: function(error) {
				console.log("에러: " + error);
			}
		});
	});
};

