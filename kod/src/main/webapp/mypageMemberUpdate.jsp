<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<title>마이 페이지</title>

<!-- Google font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">
<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />
<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<!--  my page bigbox -->
<link type="text/css" rel="stylesheet" href="css/mypage.css" />
</head>
<body>
	<jsp:include page="util/header.jsp"></jsp:include>
	<jsp:include page="util/navigation.jsp"></jsp:include>
	
	
	
	
	<div class="big-box">
		<!-- aside Widget -->
		<div class="aside">
			 <a href="mypageMemberUpdatePWCK.do">
        <h3 class="aside-title">개인정보변경</h3>
    </a><br>
		</div>
		<hr>
		<div class="aside">
			<h3 class="aside-title">주문내역조회</h3>
		</div>
		<hr>
		<div class="aside">
			<h3 class="aside-title">장바구니관리</h3>
		</div>
		<hr>
	<a href="javascript:handleAddressManage()" id="addressManage">
    <div class="aside">
        <h3 class="aside-title">배송지 관리</h3>
    </div>
</a>
</div>

<%
    MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
%>  


	<div class="container">
		<!-- Heading -->
		<h1>회원정보변경</h1>

		<form onsubmit="return formCheck(this);" action="memberUpdateAction.do" method="POST">
         
        <div class="input__block" style="padding-left: 27px; ">
            <label for="inputName" >아이디</label>
            <input type="text" class="input" id="memberID"  name="memberID" value="<%=memberDTO.getMemberID()%>" readonly>
        </div>
        <div class="input__block" style="padding-left: 27px; ">
            <label for="inputDescription">이름</label>
            <input type="text" id="memberName" class="input" name="memberName" value="<%=memberDTO.getMemberName()%>">
        </div>
        <div class="input__block" style="padding-left: 27px; ">
            <label for="inputDescription">비밀번호</label>
            <input type="password" id="memberPW" class="input" name="memberPW" value="">
        </div>
        <div class="input__block" style="padding-left: 27px; ">
            <label for="inputDescription">비밀번호 확인</label>
            <input type="password" id="memberPWCK" class="input" name="memberPWCK" value="">
            <span id="passwordMismatch" style="color: red; display: none;">비밀번호가 일치하지 않습니다.</span>
        </div>

        <script>
            // 'memberPWCK' 요소의 input 이벤트에 대한 리스너를 등록
            document.getElementById('memberPWCK').addEventListener('input', function () {
                // 비밀번호와 비밀번호 확인 값을 가져옴
                var password = document.getElementById('memberPW').value;
                var confirmPassword = this.value;

                // 비밀번호 불일치 시 메시지를 표시할 요소
                var mismatchSpan = document.getElementById('passwordMismatch');

                // 비밀번호와 비밀번호 확인 값이 일치하는지 확인
                if (password === confirmPassword) {
                    mismatchSpan.style.display = 'none';
                } else {
                    mismatchSpan.style.display = 'block';
                }
            });
        </script>

        <div class="input__block" style="padding-left: 27px; ">
            <label for="inputDescription">이메일</label>
            <input type="email" id="memberEmail" class="input" name="memberEmail" value="<%=memberDTO.getMemberEmail()%>">
        </div><br>

        <button   onclick="return formCheck(this.form)">회원정보 변경</button> 
    </form>

<script>
    // HTML 폼에서 사용자가 입력한 값들을 가져와서 유효성을 체크하는 JavaScript 함수
    function formCheck(form) {
        var memberID = document.getElementById("memberID");
        var memberPW = document.getElementById("memberPW");
        var memberName = document.getElementById("memberName");
        var memberPWCK = document.getElementById("memberPWCK");
        var memberEmail = document.getElementById("memberEmail");

        // 정규식
        var regName = /^[가-힣]{2,}$/;  // 한글만 입력 가능, 2글자 이상 입력
        var regId = /^[0-9a-z]{6,13}$/;  // 숫자, 소문자만 입력 가능, 6글자 이상 13글자 이하
        var regPw = /^(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&*]{6,13}$/; // 숫자, 대소문자, 특수문자 입력 가능(특수문자 1개 이상 반드시 포함), 6글자 이상 13글자 이하

        // 만약 이름 입력란에 값이 없다면 알람창 출력 후 이름 입력란에 마우스 커서 포커스 후 false 반환하여 form 제출을 막음    
        if (memberName.value == '') {
            alert('이름을 입력해주세요.');
            memberName.focus();
            return false;
        }
        // 만약 이름 입력값이 위에서 정의한 정규식 코드와 같지 않다면 알람창 출력 후 이름 입력란에 마우스 포커스 후 false 반환하여 form 제출을 막음
        else if (!regName.test(memberName.value)) {
            alert("2자 이상 한글만 입력 가능합니다.");
            memberName.focus();
            return false;
        }

        // 만약 memberPW 값이 없다면 알람창 출력 후 비밀번호 입력간에 마우스 포커스 후 false 반환하여 form 제출을 막음
        if (memberPW.value == '') {
            alert('비밀번호를 입력해주세요.');
            memberPW.focus();
            return false;
        }
        // 만약 비밀번호 입력값이 위에서 정의한 정규식 값이 아니라면 알람창 출력 후 비밀번호 입력창에 마우스 포커스 후 false 반환하여 form 제출을 막음
        else if (!regPw.test(memberPW.value)) {
            alert("비밀번호 6~13자 영문 대소문자, 숫자로 입력해주세요.\n특수문자 1개 이상 입력해주세요.");
            memberPW.focus();
            return false;
        }

        // 만약 비밀번호 확인 값이 없다면 알람창 실행 후 비밀번호 확인 입력창에 마우스 포커스 후 false 반환하여 form 제출을 막음    
        if (memberPWCK.value == '') {
            alert('비밀번호 확인을 입력해주세요.');
            memberPWCK.focus();
            return false;
        }

        // 만약 비밀번호 값과, 비밀번호 확인 값이 같지 않다면 알람창 출력 후 비밀번호 확인 입력란에 마우스 포커스 후 false 반환하여 form 제출을 막음    
        else if (memberPW.value != form.memberPWCK.value) {
            alert('비밀번호와 비밀번호 확인이 동일하지 않습니다.\n다시 입력해주세요.');
            memberPWCK.focus();
            return false;
        }

        // 이메일 형식 검사 정규식
        var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

        if (memberEmail.value == '') {
            alert('이메일을 입력해주세요.');
            memberPW.focus();
            return false;
        }
        // 이메일 형식이 맞지 않으면 알림을 띄우고 이메일 입력란으로 포커스 이동 후 false 반환
        if (!emailRule.test(document.getElementById("memberEmail").value)) {
            alert("이메일을 형식에 맞게 입력해주세요.\n올바른 이메일 형식 [ email@domain.com ]");
            document.getElementById("memberEmail").focus();
            return false;
        }

        form.submit();
        return false;
    }
</script>


	</div>


	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>


		
				
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/mypageAddress.js"></script>
</body>
</html>
