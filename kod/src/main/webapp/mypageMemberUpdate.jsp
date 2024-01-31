<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="model.dto.*" %>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<link type="text/css" rel="stylesheet" href="css/login.css"/>
    <link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>


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


  	<%
	
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("memberDTO");
		//String memberPhNum = (String) session.getAttribute("memberPhNum");
		//System.out.println(memberID);
	%>  



<%-- 	<%
	

		String memberID = ((MemberDTO) session.getAttribute("memberDTO")).getMemberID();
		System.out.println(memberID);
		String MID=(String)session.getAttribute("memberID");
	%>
 --%>
 

<!-- HEADER, NAVIGATION -->
		<jsp:include page="util/header.jsp"></jsp:include>
        <jsp:include page="util/navigation.jsp"></jsp:include>
		<!-- /HEADER, NAVIGATION -->



<!-- </form> -->
<form onsubmit="return formCheck(this);" action="memberUpdateAction.do" method="POST">
                           <!-- <div class="card-body"> -->
                              <div class="form-group">
                                 <label
                                    for="inputName">아이디</label> 
                                 
                                    <input type="text" id="memberID"
                                    class="form-control" name="memberID" value="<%=memberDTO.getMemberID()%>"readonly>
                              </div>
                              <div class="form-group">
                                 <label for="inputDescription">이름</label><input type="text"
                                    id="memberName" class="form-control" name="memberName"
                                    value="<%=memberDTO.getMemberName()%>">
                              </div>
                              <div class="form-group">
                                 <label for="inputDescription">비밀번호 변경</label><input type="text"
                                    id="memberPW" class="form-control" name="memberPW"
                                    value="">
                              </div>
                              <div class="form-group">
                                 <label for="inputDescription">이메일 변경</label><input type="text"
                                    id="memberEmail" class="form-control" name="memberEmail"
                                    value="<%=memberDTO.getMemberEmail()%>">
                              </div>
                        <button class="memberUpdate__btn" onclick="return formCheck(this.form)">
                							회원정보 변경
            </button> 
                        
                        </form>

        <script>
	
      // HTML 폼에서 사용자가 입력한 값들을 가져와서 유효성을 체크하는 JavaScript 함수
	function formCheck(form){
		
    	// var form = document.getElementById(" 폼 아이디 ");
    	  
		var memberID = document.getElementById("memberID"); // 회원아이디를 입력하는 입력란(input text) 의 값을 memberID 저장 
		var memberPW = document.getElementById("memberPW");
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
		
	
		 
	        
		  form.submit();
           return false;
      
	}
		   </script>
 <!-- <button type="submit" class="btn btn-block btn-primary"
                                 style="margin-bottom: 10px; margin-top: 10px;">개인정보 변경</button> -->



	
				
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/mypageAddress.js"></script>
</body>
</html>