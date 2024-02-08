$(document).ready(function(){
	  $(".add-to-wishlist2").on("click", function(e){
	    e.preventDefault(); // 기본 클릭 이벤트를 중단하여 링크가 이동하는 것을 방지

	    console.log("위시리스트 버튼 클릭됨");
	    
	    var productID = $(this).find(".productID").text();
	    var heartIcon = $(this).find("#heartIcon");
	    console.log("productID", productID);
	    
	    $.ajax({
	      type: "POST",
	      url: "isWishedServlet",
	      data: {"productID": productID},
	      success: function(data){
	        console.log(data);
	        heartIcon.toggleClass('fa-heart-o fa-heart');
	        
	        var updatedWishListCnt = parseInt(data);
	        $(".wishListCnt").text(updatedWishListCnt);
	        console.log("updatedWishListCnt >> " + updatedWishListCnt);
	        
		    $.ajax({
			      type: "POST",
			      url: "wishTotalCntServlet",
			      data: {"productID": productID},
			      success: function(data){
			        console.log(data);

			        var updatedwishTotalCnt = parseInt(data);
			        $(".wishTotalCnt").text(updatedwishTotalCnt); // 상품의 찜 합계수량
			        console.log("updatedwishTotalCnt >> " + updatedwishTotalCnt);

			      },
			      error: function(error){
			        console.log("에러: " + error);
			      } 
			    });
	        
	      },
	      error: function(error){
	        console.log("에러: " + error);
	      } 
	    });
	    
	  });
});