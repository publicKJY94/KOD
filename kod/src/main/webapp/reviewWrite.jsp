<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>상품 후기 작성</title>
    <link type="text/css" rel="stylesheet" href="css/reviewWrite.css">
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
    if (event.target.files.length === 0) {
        var output = document.getElementById('imagePreview');
        output.src = "";
        var cancelButton = document.getElementById('cancelImageButton');
        cancelButton.style.display = "none";
    } else {
        reader.readAsDataURL(event.target.files[0]);
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

 구조 아주 중요합니다 조금이라도 헷갈리면 질문오십셔
 */

</script>
<body>
    <div class="container">
        <h2>상품 후기 작성</h2>
        <form action="reviewWriteAction" method="POST" enctype="multipart/form-data">
       
        <!-- productID hidden field -->
        <input type="hidden" name="productID" value="1001" />
        
            <div class="FormRow">
                <label for="title">제목</label>
                <input type="text" id="title" name="title" placeholder="제목을 입력하세요" required />
            </div>
            <div class="FormRow">
                <label for="content">내용</label>
                <textarea id="content" name="content" placeholder="내용을 입력하세요" required></textarea>
            </div>
            <div class="FormRow">
                <label for="rating">별점</label>
                <select id="score" name="score">
                    <option value="1">1점</option>
                    <option value="2">2점</option>
                    <option value="3">3점</option>
                    <option value="4">4점</option>
                    <option value="5">5점</option>
                </select>
            </div>
            <div class="FormRow">
			    <label for="imageUpload">이미지 업로드</label>
			    <input type="file" id="imageUpload" name="imageUpload" accept="image/*" required onchange="previewImage(event)" />
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