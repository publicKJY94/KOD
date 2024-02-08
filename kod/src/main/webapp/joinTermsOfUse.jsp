<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용 약관 페이지</title>

<link type="text/css" rel="stylesheet" href="css/login.css" />
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link rel="icon" type="image/x-icon" href="/img/favion.png" >

<style>
  .header-logo {
    background-color: #000; /* 검정색 배경으로 설정 */
  } 
</style>

</head>
<body>

<header>
	<!-- LOGO -->
					<div class="col-md-3">
						<div class="header-logo">
							<a href="main.do" class="logo"> <img src="./img/logo.gif"  style="width: 250px" height="65px" alt="">
							</a>
						</div>
					</div>
					<!-- /LOGO -->
</header>





<script>

//JavaScript 함수: 모든 체크박스를 선택 상태로 변경하는 기능을 수행함
function checkAllCheckboxes() {
    // 모든 체크박스에 대한 NodeList(체크박스 목력) 가져오기 
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // "약관 전체 동의하기" 체크박스의 상태 가져오기
    var checkAllCheckbox = document.getElementById('checkAll');

    // "약관 전체 동의하기" 체크박스의 상태에 따라 모든 체크박스의 상태를 설정
    // 모든 체크박스를 순회하면서 각 체크박스의 상태를 "약관 전체 동의하기" 체크박스의 상태로 설정
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = checkAllCheckbox.checked;
    });
}

</script>






<!-- 폼 제출 버튼을 누르면 유효성을 검사하는 함수실행후 회원가입 페이지.do로 이동 post 타입 요청으로 데이터전송-->
<!-- post 타입 요청은 get타입 요청과 달리 URL에 데이터를 노출시키지않아 보안적으로 더안전 데이터 크기제한이없어 대량의 데이터를 보낼수있음-->
 <form onsubmit="validateForm(form);" action="joinPage.do" method="post">
  
<!-- 약관 전체 동의 체크박스 -->
  <div style="padding-left: 27px;">
<!-- 약관 전체 동의 체크박스를 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
    <input type="checkbox" id="checkAll" name="checkAll" onclick="checkAllCheckboxes()"> 
    <label for="checkAll"> 약관 전체 동의하기</label>
  </div>
  <br>

<!-- 개인정보 수집동의 체크박스 -->
  <div style="padding-left: 27px;">
    <input type="checkbox" class="input" id="privacyAgreeCheckbox" name="privacyAgreeCheckbox">
    <label for="privacyAgreeCheckbox"> [필수] 개인정보 수집 및 이용 동의 </label> 
<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->
    <button type="button" onclick="javascript:openWin1()">자세히</button>
    <br>
    <textarea rows="7" cols="84">
로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 
이런 일을 막고 디자인을 보여주는 데 집중하고자 어딘가 라틴어처럼 보이지만 실질적인 의미가 없는 단어를 조합해서 만든 글이다.
물론 의미 없이 아무런 글자를 무작위로 입력할 수도 있지만 그런 텍스트는 아름답지도 않고 폰트나 레이아웃은 글 내용의 무질서함에 가려져 보이지 않을 것이다. 
를 들어 "askfiofsikf" 같이 아무렇게나 입력한 문자열로 폰트를 평가한다면 폰트, 레이아웃은 보이지 않고 애들 장난 같이 보일 것이다.
그렇기 때문에 로렘 입숨처럼 적당히 정갈하면서도 전 세계 어떤 모국어 화자도 무슨 내용인지 이해할 수 없는(달리 말해 주의를 빼앗기지 않을) 문장을 사용해야 한다.
    </textarea>
    <br>
  </div>
  <br>

<!-- KOD 이용약관 동의 체크박스 -->
  <div style="padding-left: 27px;">
    <input type="checkbox" class="input" id="KODAgreeCheckbox" name="KODAgreeCheckbox">
    <label for="KODAgreeCheckbox"> [필수] KOD 스토어 이용 악관 </label>
<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->    
    <button type="button" onclick="javascript:openWin2()">자세히</button>
    <br>
    <textarea rows="7" cols="84">
로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 
이런 일을 막고 디자인을 보여주는 데 집중하고자 어딘가 라틴어처럼 보이지만 실질적인 의미가 없는 단어를 조합해서 만든 글이다.
물론 의미 없이 아무런 글자를 무작위로 입력할 수도 있지만 그런 텍스트는 아름답지도 않고 폰트나 레이아웃은 글 내용의 무질서함에 가려져 보이지 않을 것이다. 
를 들어 "askfiofsikf" 같이 아무렇게나 입력한 문자열로 폰트를 평가한다면 폰트, 레이아웃은 보이지 않고 애들 장난 같이 보일 것이다.
그렇기 때문에 로렘 입숨처럼 적당히 정갈하면서도 전 세계 어떤 모국어 화자도 무슨 내용인지 이해할 수 없는(달리 말해 주의를 빼앗기지 않을) 문장을 사용해야 한다.
    </textarea>
    <br>
  </div>
  <br>

<!-- 마케팅 활용 및 광고성 정보 수신 동의 체크박스 -->
  <div style="padding-left: 27px;">
    <input type="checkbox" class="input" id="marketingAgreeCheckbox" name="marketingAgreeCheckbox">
    <label for="marketingAgreeCheckbox"> [선택] 마케팅 활용 및 광고성 정보 수신 동의 </label>
<!-- 자세히 버튼을 클릭하면 아래 자바스크립트에서 생성한 함수실행 -->    
    <button type="button" onclick="javascript:openWin3()">자세히</button>
    <br>
    <textarea rows="7" cols="84">
로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 
이런 일을 막고 디자인을 보여주는 데 집중하고자 어딘가 라틴어처럼 보이지만 실질적인 의미가 없는 단어를 조합해서 만든 글이다.
물론 의미 없이 아무런 글자를 무작위로 입력할 수도 있지만 그런 텍스트는 아름답지도 않고 폰트나 레이아웃은 글 내용의 무질서함에 가려져 보이지 않을 것이다. 
를 들어 "askfiofsikf" 같이 아무렇게나 입력한 문자열로 폰트를 평가한다면 폰트, 레이아웃은 보이지 않고 애들 장난 같이 보일 것이다.
그렇기 때문에 로렘 입숨처럼 적당히 정갈하면서도 전 세계 어떤 모국어 화자도 무슨 내용인지 이해할 수 없는(달리 말해 주의를 빼앗기지 않을) 문장을 사용해야 한다.
    </textarea>
    <br>
  </div>
  <br>
<!-- 다음 버튼을 클릭하면 자바스크립트에서 생성한 유효성검사 함수실행 -->
  <button class="join__btn" type="button" onclick="validateForm(this.form)">다음</button>
</form>



<script>
// 개인정보 처리방침 자세히 버튼을 누르면 실행되는 함수
function openWin1() {
	// window.open이라는 메서드를 사용하여 개인정보 처리방침 안내문이 적혀있는 페이지 열기 
	// 페이지의 이름은 "약관동의"로 설정되며 크기는 600*800 , 툴바, 메뉴바, 스크롤바 표시되지 않음
	// 페이지의 사이즈는 조절가능
	window.open("privacyAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    // 폼 제출을 막기위해 false반환
	return false;
}
// KOD 이용약관 동의 자세히 버튼을 누르면 실행되는 함수
function openWin2() {
    window.open("kodAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}
// 마케팅 정보 수신동의 자세히 버튼을 누르면 실행되는 함수
function openWin3() {
    window.open("marketingAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}
</script>





<script>
// 유효성검사 함수
function validateForm(form) {
         
    // 문서에 개인정보처리방침 동의 박스와 KOD이용약관 동의 박스체크박스를 변수에 저장함
    var privacyCheckbox = document.getElementById('privacyAgreeCheckbox');
    var KODAgreeCheckbox = document.getElementById('KODAgreeCheckbox');
    
    // 필수 동의 약관 중 하나라도 체크되지 않으면 폼 제출을 막음
    // 개인정보처리방침 동의 박스가 체크되지 않았거나 KOD이용약관 동의 박스가 체크되지않았으면
    // 안내문구출력후 false를 반환해 함수를 종료하고 폼제출을 막음
    if (!privacyCheckbox.checked || !KODAgreeCheckbox.checked) {
        alert('필수 이용약관을 확인해주세요.');
        return false;
    }
    
    // 위에 조건문에 해당하지않는다면 안내문구 출력
    alert('필수 이용약관 동의 확인.\n회원가입페이지로 이동합니다.');

    
    // 모든 조건이 충족되면 폼을 제출
    form.submit();
}
</script>

</body>
</html>