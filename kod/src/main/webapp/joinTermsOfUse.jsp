<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이용 약관 페이지</title>

<link type="text/css" rel="stylesheet" href="css/login.css" />
<link type="text/css" rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

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


function checkAllCheckboxes() {
    // 모든 체크박스에 대한 NodeList 가져오기
    var checkboxes = document.querySelectorAll('input[type="checkbox"]');

    // "약관 전체 동의하기" 체크박스의 상태 가져오기
    var checkAllCheckbox = document.getElementById('checkAll');

    // "약관 전체 동의하기" 체크박스의 상태에 따라 모든 체크박스의 상태를 설정
    checkboxes.forEach(function(checkbox) {
        checkbox.checked = checkAllCheckbox.checked;
    });
}

</script>







 <form  onsubmit="return validateForm(form);" action="joinPage.do" method="post">
 
  <div style="padding-left: 27px;">
        <input type="checkbox" id="checkAll" name="checkAll" onclick="checkAllCheckboxes()"> 
            <label for="checkAll"> 약관 전체 동의하기</label>
    </div>
   <br>
 
  
<!--   <h3> [필수] 개인정보 수집 이용 동의 </h3> -->
  <div style="padding-left: 27px;">
<input type="checkbox" class="input" id="privacyAgreeCheckbox" name="privacyAgreeCheckbox" >
    <label for="privacyAgreeCheckbox"> [필수] 개인정보 수집 및 이용 동의 </label> 

          <button type="button" onclick="javascript:openWin1()" >자세히</button>
<br>
 <textarea rows="7" cols="84">
개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.


로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 

</textarea>
<br>
  

 </div>
<br>


 
 <!--  <h3> [필수] KOD 스토어 이용 악관 동의 </h3> -->
  <div style="padding-left: 27px;">
   <input type="checkbox" class="input" id="KODAgreeCheckbox" name="KODAgreeCheckbox" >
             <label for="KODAgreeCheckbox"> [필수] KOD 스토어 이용 악관 </label>
        <button type="button" onclick="javascript:openWin2()">자세히</button>
  <br>
 <textarea rows="7" cols="84">
개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.


로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 

</textarea>
<br>
  
 </div>
<br> 
 
 


 <!-- <span > 개인정보 수집 이용 동의 </span>  -->
   
 <!-- <h3> 마케팅 활용 및 광고성 정보 수신 동의 </h3> -->
   <div style="padding-left: 27px;">
   <input type="checkbox" class="input"  id="marketingAgreeCheckbox" name="marketingAgreeCheckbox" >
             <label for="marketingAgreeCheckbox"> [선택] 마케팅 활용 및 광고성 정보 수신 동의 </label>
                <button type="button" onclick="javascript:openWin3()">자세히</button>
                <br>
 <textarea rows="7" cols="84">
개인정보보호법에 따라 네이버에 회원가입 신청하시는 분께 수집하는 개인정보의 항목, 개인정보의 수집 및 이용목적, 개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후 동의하여 주시기 바랍니다.


로렘 입숨
왜 사용하는가
문서 디자인에 의미가 있는 글을 담으면 사람들은 양식을 보지 않고 글의 내용에 집중하는 경향이 있다. 
예를 들어 "나무위키의 서버는 파라과이에 있다."라는 문장을 적으면 대부분의 사람들은 글씨체에 집중하지 않고 글의 내용에 집중하게 될 것이다. 

</textarea>
<br>
 


 </div>
<br>




 <!-- 회원가입 버튼  -->
           
             <button class="join__btn" type="button"  onclick="validateForm(this.form)">
              다음
            </button> 







<script>
function openWin1() {
    window.open("privacyAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}

function openWin2() {
    window.open("kodAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}

function openWin3() {
    window.open("marketingAgree.jsp", "약관동의", "width=600, height=800, toolbar=no, menubar=no, scrollbars=no, resizable=yes")
    return false;
}
</script>






<script>
function validateForm(form) {
    // "약관 전체 동의하기" 체크 여부 확인
  //  var checkAllCheckbox = document.getElementById('checkAll');
    
    // 필수 동의 약관 체크 여부 확인
    var privacyCheckbox = document.getElementById('privacyAgreeCheckbox');
    var KODAgreeCheckbox = document.getElementById('KODAgreeCheckbox');
    
    // 필수 동의 약관 중 하나라도 체크되지 않으면 폼 제출을 막음
    if (!privacyCheckbox.checked || !KODAgreeCheckbox.checked) {
        alert('필수 이용약관을 확인해주세요.');
        return false;
    }
    alert('필수 이용약관 동의 확인.\n회원가입페이지로 이동합니다.');

    // 모든 조건이 충족되면 폼을 제출
    
    form.submit();
}
</script>

</body>
</html>