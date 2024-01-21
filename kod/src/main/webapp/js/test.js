function selectcheckbox() {
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var lists = [];
	checkboxes.forEach(function(checkbox) {
		lists.push(checkbox.name); // 체크박스의 name 속성 값 가져오기
	});
	var categoryList = JSON.stringify(lists);
	console.log(categoryList);
	selectProduct(categoryList);
}


var product = $('div.row');
function selectProduct(categoryList) {
	console.log(categoryList);
	$.ajax({
		type: "POST",
		url: "Test",
		data: { "categoryList": categoryList},
		dataType: "json", 
		success: function(data) {
			var text = "";
			$.each(data, function(data) {
				text += `
					<div class="col-md-4 col-xs-6">
						<div class="product">
							<div class="product-body">
								<div class="product-btns">
									<button class="add-to-wishlist">
										<i class="fa fa-heart-o"></i><span class="tooltipp">위시리스트에
											추가</span>
									</button>
								</div>
							</div>
							<div class="product-img">
								<img src="${data.getProductImg()}" alt="">
								<div class="product-label">
									<span class="new">NEW</span>
								</div>
							</div>
							<div class="product-body">
								<div class="product-btns">
									<button class="add-to-wishlist">
										<i class="fa fa-heart-o"></i><span class="tooltipp">위시리스트에
											추가</span>
									</button>
								</div>
								<p class="product-category">${data.getProductCategory()}</p>
								<h3 class="product-name">
									<a
										href="productDetail.do?productId=${data.getProductID()}">${data.getProductName()}</a>
								</h3>
								<h4 class="product-price">${data.getProductPrice()}<del
										class="product-old-price"></del>
								</h4>
								<div class="product-rating">
								</div>
	
							</div>
							<div class="add-to-cart">
								<button class="add-to-cart-btn">
									<i class="fa fa-shopping-cart"></i> add to cart
								</button>
							</div>
						</div>
					</div>`;
			});
			product.innerHTML(text);
		}
	});
}