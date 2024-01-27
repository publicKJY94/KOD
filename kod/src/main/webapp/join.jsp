<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/login.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>

    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
    <div class="container">
        <!-- Heading -->
        <h1>SIGN IN</h1>
  
        <!-- Links -->
  
        <!-- Form -->
        <form  onsubmit="return formCheck(this);" action="join.do" method="post">
            
            
            <!-- 아이디 입력란 -->
             <div class="input__block" style="padding-left: 27px; ">
                <input type="text" placeholder="아이디를 입력해주세요." class="input" id="memberID" name="memberID" style=" display: inline-block;  width: 58%;" minlength="6"  maxlength="13" required />
                <input type="button" id="but" value="ID중복검사" onclick="check()" style=" display: inline-block;  width: 25%;"><br>
            </div>
			<div class="first-input input__block first-input__block" style="padding-left: 27px;">			
                <span id="msg" ></span>   
			</div>
            
            
            <!-- 비밀번호 입력란 -->
             <div class="input__block" style="padding-left: 27px; ">
                <input type="password" placeholder="비밀번호 6~13자 영문 대소문자 , 숫자로 입력하세요 특수문자1개이상 입력해주세요." class="input" id="memberPW" name="memberPW" style="width: 85%; display: inline-block; minlength="6"  maxlength="13" required />
            </div>


            <!-- 비밀번호 확인입력란 -->
         <div class="input__block" style="padding-left: 27px; ">
                <input type="password" placeholder="비밀번호를 확인." class="input" id="memberPWCK" name="memberPWCK" style="width: 85%; display: inline-block; minlength="6"  maxlength="13" required />
                 <!-- 비밀번호 불일치 메시지 -->
                 <!-- memberPWCK'라는 id를 가진 입력란의 입력값이 변경될 때마다 호출되는 이벤트 리스너입니다.
                 사용자가 비밀번호확인란을 입력하면 비밀번호입력란과 값이 일치하는지확인
                 일치하면 메시지를 숨기고, 일치하지않으면 메시지를 화면에 표시합니다 -->
                <span id="passwordMismatch" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</span>
            </div>

            <script>
                // 'memberPWCK' 요소의 input 이벤트에 대한 리스너를 등록
                document.getElementById('memberPWCK').addEventListener('input', function () {
                	
                	// 비밀번호와 비밀번호 확인 값을 가져옴
                	var password = document.getElementById('memberPW').value; // 비밀번호 입력란의 값
                    var confirmPassword = this.value; // 'memberPWCK' 입력란의 값

                    // 비밀번호 불일치 시 메시지를 표시할 요소
                    var mismatchSpan = document.getElementById('passwordMismatch');

                    // 비밀번호와 비밀번호 확인 값이 일치하는지 확인
                    if (password === confirmPassword) {
                    	// 일치하면 불일치 메시지를 숨김
                        mismatchSpan.style.display = 'none';
                    } else {
                    	// 불일치하면 불일치 메시지를 표시
                        mismatchSpan.style.display = 'block';
                    }
                });
                                           
            </script>
            
            <!-- 이름 입력란 -->
             <div class="input__block" style="padding-left: 27px; ">
                <input type="name" placeholder="이름을 입력해주세요" class="input" id="memberName" name="memberName" style="width: 85%; display: inline-block; minlength="1"  maxlength="10" required />
            </div>

			 <!-- 성별 선택버튼 -->
			<div style="padding-left: 27px;">
				<input type="radio" id="memberGender" name="memberGender" value="male">남 
				<input type="radio" id="memberGender" name="memberGender" value="female">여
			</div>

            <!-- 생년월일 입력란 -->
             <div class="input__block" style="padding-left: 27px; ">
                <input type="text" id="year" name="year" placeholder=" ex) 1997 " style=" display: inline-block;  width: 27%;" minlength="4"  maxlength="4" required>
                <select class="box" id="month" name="month" style="width: 26%; display: inline-block;">
                    <script>
                     // 선택 박스에 1부터 12까지의 월 옵션 추가
                        var selectElement = document.getElementById("month");
                     // 1부터 12까지의 월에 대한 옵션을 동적으로 생성하여 추가
                        for (var i = 1; i <= 12; i++) {
                        	// option 엘리먼트를 생성
                            var option = document.createElement("option");
                         // option의 value와 text 속성에 1자리 숫자 앞에 0을 붙여 두 자리로 만든 값을 할당
                            option.value = option.text = ("0" + i).slice(-2);
                            // 생성한 option을 select 요소에 추가  
                         selectElement.add(option);
                        }
                    </script>
                </select> 
                <input type="text" name="day" placeholder=" ex) 11 " style=" display: inline-block; width: 26%;" minlength="2"  maxlength="2" required>
            </div>


            <!-- 핸드폰번호 입력란 -->
 			<div class="input__block" style="padding-left: 27px; ">
                <input type="text" id="PhNum1" name="PhNum1" placeholder=" 010 " style=" display: inline-block;  width: 23%;" minlength="3"  maxlength="3" required>
                <span style="display: inline-block; margin-left: 5px; margin-right: 5px;">-</span>
                <input type="text" id="PhNum2" name="PhNum2" placeholder=" 0000 " style=" display: inline-block;  width: 23%;" minlength="4"  maxlength="4" required>
                <span style="display: inline-block; margin-left: 5px; margin-right: 5px;">-</span>
                <input type="text" id="PhNum3" name="PhNum3" placeholder=" 0000 " style=" display: inline-block; width: 23%;" minlength="4"  maxlength="4" required>
            </div>

       
        <!-- 이메일 입력란  -->
            <div class="input__block" style="padding-left: 27px; "> 
            <!-- <div class="input__block" style="display: flex; justify-content: center;"> -->
                <input type="text" placeholder="Email1" class="input" id="memberEmail1" name="memberEmail1"  style="display: inline-block; width: 25%; " required/>
                <span style="display: inline-block; margin-left: 5px; margin-right: 5px;">@</span>
                <input class="input" id="domain-txt" type="text" placeholder="domain.com" name="memberEmail2" style="display: inline-block; width: 25%; " required />
                <select class="box" id="domain-list" name="memberEmail2" style="display: inline-block; width: 25%; " required>
                    <option value="type">직접 입력</option>
                    <option value="naver.com">naver.com</option>
                    <option value="google.com">google.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                    <option value="nate.com">nate.com</option>
                    <option value="kakao.com">kakao.com</option>
                </select>
            </div> 

            <script>
            
  // 이메일 도메인 선택 박스의 값이 변경될 때 호출되는 함수
  function updateDomainTxt() {
    var domainList = document.getElementById('domain-list');
    var domainTxt = document.getElementById('domain-txt');

    // 선택한 값이 'type'이 아닌 경우에만 값을 업데이트
    if (domainList.value !== 'type') {
      domainTxt.value = domainList.value;
    } else {
      domainTxt.value = ''; // '직접 입력'을 선택한 경우에는 입력란을 비웁니다.
    }
  }


  </script>
            
	<div class="input__block">
		<input class="input" type="text" name="adrsName" placeholder="집 / 회사 / 친구">
		<input type="text" id="sample4_postcode" name="adrsZipcode" placeholder="우편번호" style="display: inline-block; width: 30%; margin-left: 26px;">
		<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="display: inline-block; width: 20%; height:inherit; padding:1rem 1rem; "><br><br>
		<input type="text" id="sample4_roadAddress" name="adrsStreet" placeholder="도로명주소" >
		<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="adrsLotNum" style="margin-top: 5px;">
		<span id="guide" style="color:#999;display:none"></span>
		<input type="text" id="sample4_detailAddress"  name="adrsDetail" placeholder="상세주소" style="margin-top: 5px;">
	
		<!-- <input type="text" id="sample4_extraAddress" placeholder="참고항목" style="margin-top: 5px;"> -->
	</div>
	
	 <!-- 회원가입 버튼  -->
           
             <button class="join__btn" onclick="return formCheck(this.form)">
                회원가입
            </button> 
            
             <script>
	
      // HTML 폼에서 사용자가 입력한 값들을 가져와서 유효성을 체크하는 JavaScript 함수
	function formCheck(form){
		
    	// var form = document.getElementById(" 폼 아이디 ");
    	  
		var memberID = document.getElementById("memberID"); // 회원아이디를 입력하는 입력란(input text) 의 값을 memberID 저장 
		var memberPW = document.getElementById("memberPW");
		var memberPWCK = document.getElementById("memberPWCK");
		var memberName = document.getElementById("memberName");
		var memberGender = document.getElementById("memberGender");
		var year = document.getElementById("year");
		var month = document.getElementById("month");
		var day = document.getElementById("day");
		var PhNum1 = document.getElementById("PhNum1");
		var PhNum2 = document.getElementById("PhNum2");
		var PhNum3 = document.getElementById("PhNum3");
		var memberEmali1 = document.getElementById("memberEmali1");
		var memberEmali2 = "";
		
		//정규식
		var regName = /^[가-힣]{2,}$/;      // 한글만입력가능 , 2글자이상 입력	
 		var regId = /^[0-9a-z]{6,13}$/;    // 숫자 , 소문자만 입력가능 6글자이상 13글자 이하
		var regPw = /^(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]{6,13}$/; // 숫자, 대소문자, 특수문자 입력가능(특수문자 1개이상 반드시 포함) 6글자이상 13글자 이하

		
		
		/* var email_rule =  /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
  		var email_id =$("#email_id").val();
  		var email_domain =$("#email_domain").val();
  		var mail ="";
		 */
		
		 // memberID 값이 없으면 알림창 출력후 memberID 입력란에 마우스포커스 후 false를 반환해 form제출을 못하게 막는코드 
		if(memberID.value==''){
			alert("아이디를 입력해주세요.");
			memberID.focus();
			return false;
		}
		
		// 만약 memberID의 값이 위에서 정의한 정규식패턴과 맞지않는다면 알람창출력후 memberID 입력란에 포커스후 false를 반환해 form제출을 못하게 막음
 		else if(!regId.test(memberID.value)){
	            alert("아이디를 6~13글자 소문자, 숫자로 입력하세요.");
	            memberID.focus();
	            return false;
	        }
		    
		//만약 memberPW값이 없다면 알람창 출력후 비밀번호 입력간에 마우스포커스후 false반환 form제출을 막음
		 if(memberPW.value==''){
			alert('비밀번호를 입력해주세요.');
			memberPW.focus();
			return false;
		}
		// 만약 비밀번호입력값이 위에정의해둔 정규식값이 아니라면 알람창 출력후 비밀번호입력창에 마우스 포커스후 false반환 form제출을 막음
 	 	else if(!regPw.test(memberPW.value)){
			alert("비밀번호 6~13자 영문 대소문자 , 숫자로 입력해주세요.\n특수문자 1개 이상 입력해주세요. ");
			memberPW.focus();
			return false;
		}   
		
		
		   
		   /* 
		   		비밀번호와 아이디가 같으면 안내문구출력후 폼제출을 막는 유효성검사 코드
		   else if(memberPW.value == memberID.value){

	            alert("아이디와 동일한 비밀번호를 사용할 수 없습니다. 다시 입력해주세요. ")
	               setTimeout(function() {
	            	  memberPW.focus();
	            }, 0);	           
	            return false;
	        }
		    */
		
		// 만약 비밀번호확인 값이 없다면 알람창실행후 비밀번호확인 입력창에 마우스포커스후 false반환 form제출막음    
		if(memberPWCK.value==''){
			alert('비밀번호 확인을 입력해주세요.');
			memberPWCK.focus();
			return false;
		}
		   
		// 만약 비밀번호값과 , 비밀번호확인값이 같지않다면 알람창출력후 비밀번호확인 입력란에 마우스포커스후 false반환 form제출을 막음    
		else if(memberPW.value != form.memberPWCK.value){
			alert('패스워드, 패스워드 확인이 동일하지 않습니다. 다시입력해주세요.');
			 	 //setTimeout(function() {
	                memberPWCK.focus();
	         //  }, 0);
			return false;
		}
		 
		// 만약 이름입력란에 값이 없다면 알람창 출력후 이름입력란에 마우스커서포커스후 false반환후 form제출을 막음    
		if(memberName.value==''){
			alert('이름을 입력해주세요.');
			memberName.focus();
			return false;
		}
	    
		// 만약 이름입력값이 위에서정의한 정규식코드와 같지않다면 알람창출력후 이름입력란에 마우스포커스후 false반환 form제출을 막음
		else if(!regName.test(memberName.value)){
			alert("2자 이상 한글만 입력 가능 합니다. ");
			memberName.focus();
			return false;
		}
	        
	        
	        // 성별 유효성 검사
	        // 변수 gendetFlag 선언후 false값으로 초기화
	        var genderFlag = false;
	        for (var i = 0; i < form.memberGender.length; i++) { // for문을 사용하여 form객체 내의 memberGender 라는 이름을 가진 라디오버튼 그룹 각요소를 확인합니다
	            if (form.memberGender[i].checked) { //만약 현재 라디오버튼이 선택되어있다면 genderFlag변수에 true값 저장후 반복문을 탈출합니다.
	                genderFlag = true;
	                break;
	            }
	        }
			
	        // 만약 genderFlag 값이 여전히 false라면(라디오버튼이 선택되어있지 않을경우) 알람창 출력후
	        if (!genderFlag) {
	        
	            alert("성별을 체크해주세요.");
	            //setTimeout 함수를 사용하여 비동기적으로 첫 번째 라디오 버튼에 포커스를 맞춥니다.
	            setTimeout(function() {
	            	memberGender[0].focus();
	            }, 0);

	            
	            
	          
	            return false;
	        }
	        
	        
	    
	        
	      

	   // 생년월일 year 유효성검사
	   // 생년월일(year)입력란에 값이 없다면 안내문출력후 생년월일 입력란(year)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
	   	if(year.value==''){
			alert('생년월일을 입력해주세요.');
			year.focus();
			return false;		
		}
	   // 만약 숫자가 아니거나 , 1900보다작거나 , 2024보다크면 안내문 출력후 생년월일(year)입력란에 마우스커서 이동후 false반환 form 제출 막음
	   	else if (isNaN(year.value) || year.value < 1900 || year.value > 2024) {
	   	    alert("올바른 연도를 입력해주세요.");
	   	    year.focus();
	   	    return false;
	   	}
	   	
	/* 	
		if(form.month.value==''){
			alert('생년월일을 입력해주세요.');
			month.focus();
			return false;		
		} */
		
		
		// 생년월일 day 유효성검사
		// 생년월일(day)입력란에 값이 없다면 안내문출력후 생년월일 입력란(day)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
		if(form.day.value==''){
			alert('생년월일을 입력해주세요.');
			day.focus();
			return false;		
		}  
		// 만약 숫자가 아니거나 , 1보다작거나 , 31보다크면 안내문 출력후 생년월일(day)입력란에 마우스커서 이동후 false반환 form 제출 막음
	 	else if (isNaN(form.day.value) || form.day.value < 1 || form.day.value > 31) {
	   	    alert("올바른 일을 입력해주세요.");
	       
	        	form.day.focus();
          
	   	    
	   	    return false;
	   	}
	
		
        
	      
		// 휴대폰 PhNum1 유효성검사
		// 휴대폰 (PhNum1)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum1)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
		if(PhNum1.value==''){
			alert('휴대폰 번호를 입력해주세요.');
			PhNum1.focus();
			return false;		
		}
		// 만약 휴대폰입력(PhNum1)값이 /^01[016789]$/(정규식)값이 아니라면 안내문구 출력후 휴대폰 입력란(PhNum1)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
	 	// /^01[016789]$/ 다음 정규식은 3자리중 0,1로 2자리고정후 마지막자리에 0,1,6,7,8,9 만입력가능하다는 정규식이다
		else if(!/^01[016789]$/.test(PhNum1.value)){
			alert("휴대폰 앞 번호는 [ 010 , 011 , 016 , 017 , 018 , 019 ] 중 에서만 입력 가능 합니다. ");
			PhNum1.focus();
			return false;
		}    
		 
		   
		// 휴대폰 PhNum2 유효성검사
		// 휴대폰 (PhNum2)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum2)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
		if(PhNum2.value==''){
			alert('휴대폰 번호를 입력해주세요.');
			PhNum2.focus();
			return false;		
		}
		// 만약 휴대폰입력(PhNum2)값이 /^([0-9]{4})$/(정규식)값이아니라면 안내문구 출력후 휴대폰 입력란(PhNum2)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
	 	// /^([0-9]{4})$/ 다음 정규식은 0~9까지숫자만사용가능하고 4자리까지입력가능하다는 정규식이다
		else if(!/^([0-9]{4})$/.test(PhNum2.value)){
			alert("휴대폰 번호 숫자 4자리를 입력해주세요. ");
			PhNum2.focus();
			return false;
		}    
		 
		   
		// 휴대폰 PhNum3 유효성검사
		// 휴대폰 (PhNum3)입력란에 값이 없다면 안내문출력후 휴대폰 입력란(PhNum3)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
		if(PhNum3.value==''){
			alert('휴대폰 번호를 입력해주세요.');
			PhNum3.focus();
			return false;		
		}
		
		// 만약 휴대폰입력(PhNum3)값이 /^([0-9]{4})$/(정규식)값이아니라면 안내문구 출력후 휴대폰 입력란(PhNum3)에 마우스포커스 후 false반환 form에 제출을 못하게 막음
	 	// /^([0-9]{4})$/ 다음 정규식은 0~9까지숫자만사용가능하고 4자리까지입력가능하다는 정규식이다
	 	else if(!/^([0-9]{4})$/.test(PhNum3.value)){
			alert("휴대폰 번호 숫자 4자리를 입력해주세요. ");
			PhNum3.focus();
			return false;
		}    

	/* 	if(isNaN(form.PhNum1.value) || isNaN(form.PhNum2.value) || isNaN(form.PhNum3.value)){
			setTimeout(function() {
			alert("전화번호는 숫자만 입력가능합니다.");
		    if (isNaN(form.PhNum1.value)) {
		        form.PhNum1.focus();
		    } else if (isNaN(form.PhNum2.value)) {
		        form.PhNum2.focus();
		    } else {
		        form.PhNum3.focus();
		    }
			
	           }, 0);
			
			return false;
		}
	 */

	 
	 // 이메일 유효성검사 함수
	 function validateEmail() {
		    // email1 변수에 현재 문서에있는 memberEmail1값 저장 
		 	var email1 = document.getElementById("memberEmail1").value;
		   // email2 변수선언후 초기화
		    var email2 = "";
		    
		   //도메인 선택 상태에 따라 값을 설정
		       // 직접 입력일 경우
		   if (document.getElementById("domain-list").value === "type") {
		       // 도메인 텍스트입력
		        email2 = document.getElementById("domain-txt").value;
		    } else {
		        // 선택된 도메인 사용
		        email2 = document.getElementById("domain-list").value;
		    }
	
		   // 전체 이메일 주소를 조합
		    var fullEmail = email1 + "@" + email2;


		    // 이메일 주소 또는 도메인이 빈 값인 경우 알림을 띄우고 해당 입력란으로 포커스 이동 후 false 반환
		    if (!email1 || !email2) {
		        alert("이메일을 입력해주세요");
		        if (!email1) {
		            document.getElementById("memberEmail1").focus(); // 이메일 텍스트 칸으로 포커스 이동
		        } else {
		            // email2에 값이 없는 경우, domain-txt 입력란으로 포커스 이동
		            document.getElementById("domain-txt").focus();
		        }
		        return false;
		    }

		    // 이메일 형식 검사 정규식
		    var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		  
		    
		    // 이메일 형식이 맞지 않으면 알림을 띄우고 이메일 입력란으로 포커스 이동 후 false 반환
		    if (!emailRule.test(fullEmail)) {
		        alert("이메일을 형식에 맞게 입력해주세요.");
		        document.getElementById("memberEmail1").focus();
		        return false;
		    }

		    // 도메인이 .com을 포함하지 않으면 알림을 띄우고 도메인 입력란으로 포커스 이동 후 false 반환
		    if (document.getElementById("domain-txt").value.indexOf('.com') === -1) {
		        alert("도메인은 반드시 .com을 포함해야 합니다.");
		        document.getElementById("domain-txt").focus();
		        return false;
		    }

		    return true; // 유효성 검사 통과
		}	 
	 
	 
	   // 이메일 유효성 검사 함수 호출 및 결과에 따른 처리
	   if (!validateEmail()) {
	         return false; // 이메일 유효성 검사 실패 시 폼 제출 막음
	     } 
	 
	 
	 
  
	        
		  form.submit();
           // window.location.href='join.do';
            return false;
       
	}
	
</script>
            		
            
        </form>
    </div>

	<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
		<script src="js/check.js"></script>
    
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
        // 본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
        function sample4_execDaumPostcode() {
            new daum.Postcode({
                oncomplete: function(data) {
                    // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                    // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
                    // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                    var roadAddr = data.roadAddress; // 도로명 주소 변수
                    var extraRoadAddr = ''; // 참고 항목 변수

                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraRoadAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                       extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraRoadAddr !== ''){
                        extraRoadAddr = ' (' + extraRoadAddr + ')';
                    }

                    // 우편번호와 주소 정보를 해당 필드에 넣는다.
                    document.getElementById('sample4_postcode').value = data.zonecode;
                    document.getElementById("sample4_roadAddress").value = roadAddr;
                    document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
                    
                    // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
                    if(roadAddr !== ''){
                        document.getElementById("sample4_detailAddress").value = extraRoadAddr;
                    } else {
                        document.getElementById("sample4_detailAddress").value = '';
                    }

                    var guideTextBox = document.getElementById("guide");
                    // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                    if(data.autoRoadAddress) {
                        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                        guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expAddr + ')';
                        guideTextBox.style.display = 'block';

                    } else if(data.autoJibunAddress) {
                        var expJibunAddr = data.autoJibunAddress;
                        guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
                        guideTextBox.style.display = 'block';
                    } else {
                        guideTextBox.innerHTML = '';
                        guideTextBox.style.display = 'none';
                    }
                }
            }).open();
        }
    </script>
      

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/join.js"></script>
    <script src="js/login.js"></script>
</body>
</html>
