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

/* MVC2 패턴
 FC Servlet의 등장
 FC가 이 프로젝트의 유일한 서블릿 => 동기면 무조건 FC를 통해서감
 모든 요청을 Action 클래스를 통해 처리
    매핑 => HM : 싱글톤
 비동기때문에 추가 서블릿이 필요한 상황일뿐!
*/ 
 
function validateForm() {
    // Check if any star is selected
    var ratingInputs = document.getElementsByName('score');
    var isRatingSelected = false;

    for (var i = 0; i < ratingInputs.length; i++) {
        if (ratingInputs[i].checked) {
            isRatingSelected = true;
            break;
        }
    }
    if (!isRatingSelected) {
        alert('별점을 선택해주세요.');
        return false; // Prevent form submission
    }

    return true; // Proceed with form submission
}
 

