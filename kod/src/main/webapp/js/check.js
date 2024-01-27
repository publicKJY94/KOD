function check(){
	var memberID=$('#memberID').val();
	
  if (memberID.trim() === '') {
        $('#msg').css('color', '#FF0000').text("아이디를 입력해주세요.");
        return; // 함수 종료
    }	

	$.ajax({
		type:"POST",
		url : "check.do",
		data : {'memberID' : memberID },
		dataType :  'text' ,
		success : function(data){
			
		
			if(data=='true'){
				$('#msg').css('color', '#00FF00').text("사용가능한 아이디 입니다. ");
				
			}
			
			else{
				$('#msg').css('color', '#FF0000').text("중복 아이디 입니다. ");
				
			}
		},
		error : function(error){
			console.log('에러발생!');
			console.log('에러종류 : '+error);
		}
		
	});
}