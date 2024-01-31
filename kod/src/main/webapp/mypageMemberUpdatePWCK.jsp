<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보변경 가기전 페이지</title>


 	
</head>
<body>



<form action="mypageMemberUpdate.do" method="POST">
	비밀번호 확인	<input type="passward" name="memberPW" required>
	<!--  비밀번호 확인 <input type="password" name="memberPW" required>--> 
	<input type="submit" value="회원정보변경 입장">
</form>

<a href="main.do">메인으로 돌아가기</a>

</body>
</html>