$(document).ready(function(){
    $('.add-to-wishlist').on('click', function(){
        console.log('위시리스트 버튼 클릭됨');
        
        var productID = $(this).find('.productID').text();
        var heartIcon = $(this).find('#heartIcon');
        
        console.log('productID:', productID);
        
        $.ajax({
            type: "POST",
            url: 'isWishedAction',
            data: { 'productID': productID },
            success: function(data){
                console.log(data);
                // 클릭 시 하트 아이콘 토글
                heartIcon.toggleClass('fa-heart-o fa-heart');
                
                var updatedWishListCnt = parseInt(data); // data가 업데이트된 카운트를 받아와야합니다.
                $('.wishListCnt').text(updatedWishListCnt); // 위시리스트의 개수를 업데이트해줌
                console.log("updatedWishListCnt >> "+updatedWishListCnt)
            },
            error: function(error){
                console.log("에러: " + error);
            }
        });
    });
});


