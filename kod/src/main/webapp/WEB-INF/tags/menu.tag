<%@ tag language="java" pageEncoding="EUC-KR"%>

<%@ attribute name="apple" %>

<!-- row -->
<div class="row">
	<div class="col-md-12">
		<ul class="breadcrumb-tree">
			<li><a href="#">커스텀태그 내부의 값이 외부에 의해서 변경되어야할때</a></li>
			<li><a href="#">${apple}</a></li>
			<li><a href="#">Accessories</a></li>
			<li><a href="#">${productWishDetailData.productCategory}</a></li>
			<li class="active">Product name goes here</li>
		</ul>
	</div>
</div>
<!-- /row -->