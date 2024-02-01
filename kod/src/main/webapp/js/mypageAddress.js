function handleAddressManage() {
	console.log('배송지관리');
	$.ajax({
		type: "POST",
		url: 'addressManageActionServlet',
		dataType: "json",
		success: function(data) {
			var contentHtml = "";

			$.each(data, function(index, addressDTO) {
				contentHtml += '<div class="content box" style="display: flex; flex-direction: column;">' +
					'<input type="hidden" name="adrsID" value="' + addressDTO.adrsID + '">' +
					'<div style="margin-bottom: 5px;">' +
					'<th style="text-align: center;"> 주소지 이름 :</th>' +
					'<td>' + addressDTO.adrsName + '</td>' +
					'</div>' +
					'<div style="margin-bottom: 5px;">' +
					'<th style="text-align: center;"> 우편 번호 :</th>' +
					'<td>' + addressDTO.adrsZipcode + '</td>' +
					'</div>' +
					'<div style="margin-bottom: 5px;">' +
					'<th style="text-align: center;"> 도로명 주소 :</th>' +
					'<td>' + addressDTO.adrsStreet + '</td>' +
					'</div>' +
					'<div style="margin-bottom: 5px;">' +
					'<th style="text-align: center;"> 상세 주소 :</th>' +
					'<td>' + addressDTO.adrsDetail + '</td>' +
					'</div>' +
					'<div style="margin-bottom:5px;">' +
					'<button id="modifyButton_" onclick="openModifyModal(' + addressDTO.adrsID + ')" style="margin-left: 85%; background-color: skyblue; color: white; border: outset;">수정</button>' +
					'<button id="deleteButton_" onclick="openDeleteModal(' + addressDTO.adrsID + ')" style="background-color: lawngreen; color: white; border: outset;">삭제</button>' +
					'</div>' +
					'</div>'

			});
			$('#addressContainer').html(contentHtml);
			if (data.length < 5) {
				$('#addressContainer').append('<button id="insertButton_" onclick="openInsertModal()" style="margin-left:33%; background-color: deepskyblue; color: white; border: outset; padding: 20px 0px; font-size : large">배송지 추가하기</button>');    
			}
					
		},
		error: function(error) {
			console.error(error);
		}
	});
	deleteCookie("addressManageClicked");
}
	

window.onload = function() {
	// 쿠키 가져오기
	var addressManageCookie = getCookie("addressManageClicked");
	// 쿠키가 존재하는 경우에만 해당 태그의 클릭 이벤트를 실행
	if (addressManageCookie) {
		handleAddressManage();
	}
}
// 쿠키 가져오기 함수
function getCookie(name) {
	var nameEQ = name + "=";
	var cookies = document.cookie.split(';');
	for (var i = 0; i < cookies.length; i++) {
		var cookie = cookies[i];
		while (cookie.charAt(0) === ' ') {
			cookie = cookie.substring(1, cookie.length);
		}
		if (cookie.indexOf(nameEQ) === 0) {
			return cookie.substring(nameEQ.length, cookie.length);
		}
	}
	return null;
}
// 쿠키 설정 함수

function setCookie(name, value) {
					    var expires = "";
					    var date = new Date();
					    date.setTime(date.getTime() + (60 * 1000)); // 5초 후의 시간을 설정합니다.
					    expires = "; expires=" + date.toUTCString();
					    document.cookie = name + "=" + value + expires + "; path=/";
					}
function deleteCookie(name) {
	this.setCookie(name, "", -1);
}


document.getElementById("modifyButton_").addEventListener("click", function() {
	openModal(addressDTO.adrsID);
});
document.getElementById("deleteButton_").addEventListener("click", function() {
	openDeleteModal(addressDTO.adrsID);
});

document.getElementById("insertButton_").addEventListener("click", function() {
	openInsertModal();
});

window.onload = function() {
						// 쿠키 가져오기
						var addressManageCookie = getCookie("addressManageClicked");
						// 쿠키가 존재하는 경우에만 해당 태그의 클릭 이벤트를 실행
						if (addressManageCookie) {
							handleAddressManage();
						}
					}