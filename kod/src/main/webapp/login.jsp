<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="css/login.css"/>
<link type="text/css" rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>


<meta charset="UTF-8">
<title>로그인페이지</title>

</head>
<body>

		
<div class="container">
  <!-- Heading -->
  <h1><img src="img/shopimg.png"></h1>
  
  <!-- Links -->
  <ul class="links">
    <li>
      <a href="#" id="signin"></a>
    </li>
  </ul>
  
  <!-- Form -->
  <form  action="login.do" method="post">
    <!-- email input -->
    <div class="first-input input__block first-input__block">
       <input type="text" placeholder="ID를 입력하세요." class="input" id="memberID" name="memberID"   />
    </div>
    <!-- password input -->
    <div class="input__block">
       <input type="password" placeholder="비밀번호를 입력하세요." class="input" id="memberPW" name="memberPW"  />
    </div>
    <!-- repeat password input -->
   <!--  <div class="input__block">
       <input type="password" placeholder="Repeat password" class="input repeat__password" id="repeat__password"    />
    </div> -->
    <!-- sign in button -->
    <button class="signin__btn" type="submit">
      로그인
    </button>
  </form>
  
  	<button class="join__btn" onclick="location.href='joinPage.do'">
      회원가입
    </button>
 
</div>

</body>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js" ></script>
<script src="js/login.js"></script>
</html>
