function selectcheckbox() {
	var checkboxes = document.querySelectorAll('input[type=checkbox]:checked'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMax = document.querySelector('input[name=maxPrice]'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var inputMin = document.querySelector('input[name=minPrice]'); // 카테고리명은 실제 카테고리의 이름으로 대체해야 합니다.
	var lists = [];
	checkboxes.forEach(function(checkbox) {
		lists.push(checkbox.name); // 체크박스의 name 속성 값 가져오기
		cCookie(checkbox.name);
	});
	var max = inputMax.value;
	var min = inputMin.value;
	var categoryList = JSON.stringify(lists);
	selectProduct(categoryList, max, min);
}

var product = $('div.store > div.row');
function selectProduct(categoryList, max, min) {
	console.log(categoryList);
	$.ajax({
		type: "POST",
		url: "test",
		data: { 
			"categoryList": categoryList,
			"max" : max,
			"min" : min
		},
		dataType: "json", 
		success: function(productDTO){
			console.log("확인");
			console.log(productDTO);
			var elem = "";
			$.each(productDTO, function(index, product){
				elem += `
					 <div class="col-md-4 col-xs-6">
						<div class="product">
							<div class="product-body">
								<div class="product-btns">
									<button class="add-to-wishlist">
										<i class="fa fa-heart-o"></i>
										<span class="tooltipp">위시리스트에 추가</span>
									</button>
								</div>
							</div>
							<div class="product-img">
								<img src="${product.productImg}" alt="">
								<div class="product-label">
									<span class="new">NEW</span>
								</div>
							</div>
							<div class="product-body">
								<div class="product-btns">
									<button class="add-to-wishlist">
										<i class="fa fa-heart-o"></i>
										<span class="tooltipp">위시리스트에 추가</span>
									</button>
								</div>
								<p class="product-category">${product.productCategory}</p>
								<h3 class="product-name">
									<a href="productDetail.do?productId=${product.productID}">${product.productName}</a>
								</h3>
								<h4 class="product-price">
									${product.productPrice}<del class="product-old-price"></del>
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
			product.innerHTML(elem);
		},
		error:function(err){
			console.log(err.status);
			console.log('ggg');
		}
		
	});
	
}
function cCookie(name){
	document.cookie = "name="+name;
}