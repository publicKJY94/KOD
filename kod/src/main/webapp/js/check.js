var idCheckStatus = 0;
var regId = /^[0-9a-z]{6,13}$/;

function check(){
	var memberID=$('#memberID').val();
	
  if (memberID.trim() === '') {
        $('#msg').css('color', '#FF0000').text("아이디를 입력해주세요.");
        return; // 함수 종료
    }	

    // 정규식 패턴에 맞지 않으면 메시지를 출력하고 함수 종료
    if (!regId.test(memberID)) {
        $('#msg').css('color', '#FF0000').text("아이디는 6~13자의 영문 소문자, 숫자로 입력해주세요.");
        idCheckStatus = 0;
        return;
    }

	$.ajax({
		type:"POST",
		url : "check.do",
		data : {'memberID' : memberID },
		dataType :  'text' ,
		success : function(data){
			
		
		
			if(data=='true'){
				$('#msg').css('color', '#00FF00').text("사용가능한 아이디 입니다. ");
				idCheckStatus = 1;
			}
			
			else{
				$('#msg').css('color', '#FF0000').text("중복 아이디 입니다. ");
				idCheckStatus = 0;
				
			}
		},
		error : function(error){
			console.log('에러발생!');
			console.log('에러종류 : '+error);
		}
		
	});
}