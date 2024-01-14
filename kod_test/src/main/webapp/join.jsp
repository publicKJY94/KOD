<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  
  <form  action="join.do" method="post">

  	
    <!-- ID input -->
    <div class="first-input input__block first-input__block">
       <input type="text" placeholder="아이디를 입력해주세요." class="input" id="memberID" name="memberID"   />
    </div>
    
    <!-- password input -->
    <div class="input__block">
       <input type="password" placeholder="비밀번호를 입력해주세요." class="input" id="PW1" name="memberPW"  />
    </div>
    
    <!-- password repeat input -->
    <div class="input__block">
       <input type="password" placeholder="비밀번호를 확인." class="input" id="PW2" name="memberPW"  />
    </div>
    
 
     
    <!-- name input -->
    <div class="input__block">
       <input type="name" placeholder="ex)홍길동" class="input" id="memberName" name="memberName" minlength="1"  maxlength="10"   />
    </div>
    
       
     
     <!-- name birth input -->
      <!--   <input type="date" placeholder="생년월일을 입력해주세요" class="input" id="birth" name="birth"    />  -->
    <div class="input__block" style="display: flex; justify-content: center;">
       <input type="text" name="Year" placeholder=" ex) 1997 " style=" display: inline-block;  width: 26%; minlength="4"  maxlength="4 " required>
  
  
    	   <select class="box" id="month" style="width: 26%; display: inline-block;">
      <script>
 //선택 박스 1~12까지 
   var selectElement = document.getElementById("month");
  for (var i = 1; i <= 12; i++) {
    var option = document.createElement("option");
    option.value = option.text = ("0" + i).slice(-2);
    selectElement.add(option);
  }
</script>
       </select>
       
       <input type="text" name="date" placeholder=" ex) 11 " style=" display: inline-block; width: 26%; minlength="2"  maxlength="2" " required>
    </div>
    
    
     <!-- name phoneNumber input -->
    <div class="input__block">
       <input type="tel" placeholder="010-0000-0000" class="input" id="memberPhNum" name="memberPhNum"    />
    </div>
	<!-- name email input -->
	<div class="input__block" style="padding-left: 27px;">
	   <input type="email" placeholder="1234@5678.com" class="input" id="memberEmail" name="memberEmail" style="width: 30%; display: inline-block;"/>@
	   <input class="input" id="domain-txt" type="text" style="width: 25%; display: inline-block;"/>
	   <select class="box" id="domain-list" style="width: 25%; display: inline-block;">
			 <option value="type">직접 입력</option>
			 <option value="naver.com">naver.com</option>
			 <option value="google.com">google.com</option>
			 <option value="hanmail.net">hanmail.net</option>
			 <option value="nate.com">nate.com</option>
			 <option value="kakao.com">kakao.com</option>
		</select>
	</div>
	
	<div class="input__block">
		<input type="text" id="sample4_postcode" placeholder="우편번호" style="display: inline-block; width: 30%; margin-left: 26px;">
		<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="display: inline-block; width: 20%; height:inherit; padding:1rem 1rem;"><br><br>
		<input type="text" id="sample4_roadAddress" placeholder="도로명주소" >
		<input type="text" id="sample4_jibunAddress" placeholder="지번주소" style="margin-top: 5px;">
		<span id="guide" style="color:#999;display:none"></span>
		<input type="text" id="sample4_detailAddress" placeholder="상세주소" style="margin-top: 5px;">
		<!-- <input type="text" id="sample4_extraAddress" placeholder="참고항목" style="margin-top: 5px;"> -->
	</div>
	
  </form>
   <!-- join button -->
  	<button class="join__btn" onclick="location.href='join.do'">
      회원가입
    </button>

</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
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
                /* if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                } */

                var guideTextBox = document.getElementById("guide");
                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
<script src="js/join.js"></script>
<script src="js/login.js"></script>

</html>