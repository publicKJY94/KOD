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
			<h3 class="aside-title">개인정보변경</h3><br>
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

<!-- 배송지 수정 모달창 -->
<div class="content">
<div style="margin-bottom: 5px;">
						
						<div class="modal hidden">
						  <div class="bg"></div>
						  <div class="modalBox">
						    	<form  action="addressUpdate.do" method="post">
								<div class="form-group">
									<input class="input" type="text" name="adrsName" placeholder="주소지 이름">
								</div>		
								<input type="hidden" name="adrsId" value="" id="ADRSID">	
								<input type="text" id="sample4_postcode" name="adrsZipcode" placeholder="우편번호" style="display: inline-block; width: 30%; margin-left: 26px;">
								<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기" style="display: inline-block; width: 20%; height:inherit; padding:1rem 1rem; "><br><br>
								<input type="text" id="sample4_roadAddress" name="adrsStreet" placeholder="도로명주소" >
								<input type="text" id="sample4_jibunAddress" placeholder="지번주소" name="adrsLotNum" style="margin-top: 5px;">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="sample4_detailAddress"  name="adrsDetail" placeholder="상세주소" style="margin-top: 5px;">
								<input type="submit" value="변경하기">
								</form>
						    <button onclick="closeModal()">✖</button>
						  </div>
						</div>
					</div>
					<div id="addressContainer">
					    </div>
	<!-- 배송지 삭제 모달창 -->
				<form action="addressDelete.do" method="POST">
   			 <div style="margin-bottom: 5px;">
       		 <div class="modalDelete hidden">
            <div class="bg2"></div>
            <div class="modalBox">
            <input type="hidden" name="adrsId" value="" id="ADRSID2">	
                <p>정말로 삭제하시겠습니까?</p>
                <button type="submit">삭제</button>
                <button type="button" onclick="closeModal()">취소</button>
                <button type="button" onclick="closeModal()">✖</button>
            </div>
        </div>
    </div>
</form>

							<div class="modalInsert hidden">
						  <div class="bg3"></div>
						  <div class="modalBox">
						    	<form  action="addressInsert.do" method="post">
								<div class="form-group">
									<input class="input" type="text" name="adrsName" placeholder="주소지 이름">
								</div>		
								<input type="text" id="sample4_postcode2" name="adrsZipcode" placeholder="우편번호" style="display: inline-block; width: 30%; margin-left: 26px;">
								<input type="button" onclick="sample4_execDaumPostcode2()" value="우편번호 찾기" style="display: inline-block; width: 20%; height:inherit; padding:1rem 1rem; "><br><br>
								<input type="text" id="sample4_roadAddress2" name="adrsStreet" placeholder="도로명주소" >
								<input type="text" id="sample4_jibunAddress2" placeholder="지번주소" name="adrsLotNum" style="margin-top: 5px;">
								<span id="guide" style="color:#999;display:none"></span>
								<input type="text" id="sample4_detailAddress"  name="adrsDetail" placeholder="상세주소" style="margin-top: 5px;">
								<input type="submit" value="주소지 추가하기">
								</form>
						    <button onclick="closeModal()">✖</button>
						  </div>
						</div>
					</div>

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
    
    <script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode2() {
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
                document.getElementById('sample4_postcode2').value = data.zonecode;
                document.getElementById("sample4_roadAddress2").value = roadAddr;
                document.getElementById("sample4_jibunAddress2").value = data.jibunAddress;
                
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


</div>
	<div style="margin-bottom: 5px;">
					</div>
			</div>
				<script>
				// 모달창을 열기 위한 함수
				function openModifyModal(adrsID) {
				    document.querySelector(".modal").classList.remove("hidden");
				    console.log('[adrsID로그] ['+adrsID+']');
				    document.getElementById('ADRSID').value=adrsID;
				}
				function openDeleteModal(adrsID) {
				    // 삭제 모달 창을 나타내는 클래스를 추가
				    document.querySelector(".modalDelete").classList.remove("hidden");
				    // adrsID 값 로그에 출력
				    console.log('[adrsID로그] ['+adrsID+']');
				    console.log('삭제 들어옴'); 
				    // adrsID 값을 ADRSID 필드에 설정
				    document.getElementById('ADRSID2').value=adrsID;
				}
				function openInsertModal (){
					document.querySelector(".modalInsert").classList.remove("hidden");
					console.log('[배송지 추가 들어옴]');
				}
				// 모달창을 닫기 위한 함수
				function closeModal() {
				    document.querySelector(".modal").classList.add("hidden");
				    document.querySelector(".modalDelete").classList.add("hidden");
				    document.querySelector(".modalInsert").classList.add("hidden");
				    document.querySelector(".bg").addEventListener("click", closeModal);
					document.querySelector(".bg2").addEventListener("click", closeModal);
				    document.querySelector(".bg3").addEventListener("click", closeModal);
				    
				}
				
				</script>
				
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js" integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4=" crossorigin="anonymous"></script>
<script src="js/mypageAddress.js"></script>
</body>
</html>