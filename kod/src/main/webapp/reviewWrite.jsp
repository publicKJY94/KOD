<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 후기 작성</title>
    <link type="text/css" rel="stylesheet" href="css/reviewWrite.css">
	<!-- Custom stlylesheet -->
	<link type="text/css" rel="stylesheet" href="css/style.css"/>
	<!-- Font Awesome Icon -->
	<link rel="stylesheet" href="css/font-awesome.min.css">
	
</head>

<script>
function previewImage(event) {
    var reader = new FileReader();
    reader.onload = function() {
        var output = document.getElementById('imagePreview');
        output.src = reader.result;

        var cancelButton = document.getElementById('cancelImageButton');
        cancelButton.style.display = "inline-block";
    }

    var input = event.target;
    if (input.files.length === 0) {
        var output = document.getElementById('imagePreview');
        output.src = "";
        var cancelButton = document.getElementById('cancelImageButton');
        cancelButton.style.display = "none";
    } else {
        // 추가: 파일 크기 체크
        var fileSize = input.files[0].size; // 파일 크기 (바이트)
        var maxFileSize = 5 * 1024 * 1024; // 5MB

        if (fileSize > maxFileSize) {
            alert('파일 크기가 5MB를 초과합니다. 더 작은 파일을 선택해주세요.');
            // 선택한 파일 초기화
            input.value = '';
            return;
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function cancelImageUpload() {
    var imageUpload = document.getElementById('imageUpload');
    var imagePreview = document.getElementById('imagePreview');

    imageUpload.value = "";
    imagePreview.src = "";

    var cancelButton = document.getElementById('cancelImageButton');
    cancelButton.style.display = "none";
}

/*
 MVC2 패턴
 FC Servlet의 등장
 FC가 이 프로젝트의 유일한 서블릿 => 동기면 무조건 FC를 통해서감
 모든 요청을 Action 클래스를 통해 처리
    매핑 => HM : 싱글톤
 비동기때문에 추가 서블릿이 필요한 상황일뿐!
 */

 </script>


<body>
    <div class="container">
        <h2>상품 후기 작성</h2>
        <form action="reviewWriteServlet" method="POST" enctype="multipart/form-data">
       
       <%
		    String productIDParam = request.getParameter("productID");
		    int productID = 0; // 초기값 설정
		    if (productIDParam != null && !productIDParam.isEmpty()) {
		        productID = Integer.parseInt(productIDParam);
		    }
		    out.println("상품 ID: " + productID);
		%>
        <!-- productID hidden field -->
        <input type="hidden" name="productID" value="<%=productID %>" />
        
            <div class="FormRow">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
            </div>
            <div class="FormRow">
                <label for="content" >내용</label>
                <textarea id="content" name="content" placeholder="내용을 입력하세요" required style="height: 200px"></textarea>
            </div>
            <div class="FormRow">
                <label for="rating">별점</label>
				<div class="review-form">
				<div class="input-rating">
                <div class="stars" >
					<input id="star5" name="score" value="5" type="radio"><label for="star5"></label>
					<input id="star4" name="score" value="4" type="radio"><label for="star4"></label>
					<input id="star3" name="score" value="3" type="radio"><label for="star3"></label>
					<input id="star2" name="score" value="2" type="radio"><label for="star2"></label>
					<input id="star1" name="score" value="1" type="radio"><label for="star1"></label>
				</div>
				</div>
				</div>
            </div>
            <div class="FormRow">
			    <label for="imageUpload">이미지 업로드</label>
			    <input type="file" id="imageUpload" name="imageUpload" accept="image/*" onchange="previewImage(event)" />
			    <img id="imagePreview" src="#" alt="미리 보기 이미지" style="width: 100%" height="100%">
			    <button type="button" id="cancelImageButton" style="display: none;" onclick="cancelImageUpload()">이미지 취소</button>
			</div>
			    <div class="FormRow">
			        <button type="submit" class="SubmitBtn">작성하기</button>
			    </div>
        </form>
    </div>
</body>
</html>