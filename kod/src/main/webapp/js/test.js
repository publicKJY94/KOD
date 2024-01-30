function selectcheckbox() {
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMax = document.querySelector('#maxPrice'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMin = document.querySelector('#minPrice'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var lists = [];
	checkboxes.forEach(function(checkbox) {
		lists.push(checkbox.name); // 체크박스의 name 속성 값 가져오기
	});
	var max = inputMax.value;
	var min = inputMin.value;
	var categoryList = JSON.stringify(lists);
	selectProduct(categoryList, max, min);
}

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
												<div class="productID" hidden><${product.productID}</div>
												<c:if test="${product.isWished == 1}">
													<i class="fa fa-heart" id="heartIcon"></i>
												</c:if>
												<c:if test="${product.isWished != 1}">
													<i class="fa fa-heart-o" id="heartIcon"></i>
												</c:if>
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
									<h4 class="product-price">${product.productPrice}<del
											class="product-old-price"></del>
									</h4>
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
