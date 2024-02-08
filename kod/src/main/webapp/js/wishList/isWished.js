// 위시리스트 버튼이 클릭될 때 실행되는 이벤트 핸들러
$('.add-to-wishlist').on('click', function(){
    
    // 콘솔에 로그 출력
    console.log('[로그:정현진] 위시리스트 버튼 클릭됨');

    var productID = $(this).find('.productID').text(); // 클릭된 요소 안에서 'productID' 클래스를 가진 요소 찾기
    var heartIcon = $(this).find('#heartIcon'); // 클릭된 요소 안에서 'heartIcon' ID를 가진 요소 찾기
    
    // 콘솔에 로그 출력
    console.log('[로그:정현진] productID:', productID);
    
    // Ajax를 사용하여 서버에 POST 요청 보내기
    $.ajax({
        type: "POST", // HTTP 요청 메서드
        url: 'isWishedServlet', // 서버의 URL
        data: { 'productID': productID }, // 전송할 데이터 -> String 타입으로 전송됨 -> getParameter() 사용가능
        success: function(data){ // 성공 시 실행되는 콜백 함수, 변수data에는 찜한 상품 총 갯수가 들어있음
            console.log('[로그:정현진] data : '+data); // 서버로부터 받은 데이터 콘솔에 출력
            console.log('[로그:정현진] data타입 : '+typeof data);
            // 클릭 시 하트 아이콘 토글이 반응함, 토글이란 "켜고 끄다", "전환하다"라는 의미를 가지는 동사임
            heartIcon.toggleClass('fa-heart-o fa-heart');
            
            // 서버로부터 받은 업데이트된 위시리스트 개수를 정수로 변환
            var updatedWishListCnt = parseInt(data);
            
            // 위시리스트 개수 업데이트
            $('.wishListCnt').text(updatedWishListCnt);
            
            // 콘솔에 업데이트된 위시리스트 개수 출력
            console.log("[로그:정현진] updatedWishListCnt >> "+updatedWishListCnt);
        },
        error: function(error){ // 에러 시 실행되는 콜백 함수
            console.log("에러: " + error); // 에러 메시지 콘솔에 출력
        }
    });
});

/*  
	[정현진]
	.productID요소에.text() 했을 때는 요소의 String타입 텍스트값을 가져옵니다. => [로그:정현진] data : 5 , [로그:정현진] data타입 : string 
	.text()를 제거하면 Json타입의 배열을 가져옵니다.
	[로그:정현진] productID : n.fn.init {0: div.productID, length: 1, prevObject: n.fn.init, context: button.add-to-wishlist, selector: '.productID'}
	배열을 가져오게 되면 배열의 어떤 값을 사용할건지 추가적으로 알려주어야합니다.	
*/





