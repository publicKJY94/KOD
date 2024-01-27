function handleAddressManage() {
    console.log('배송지관리');
    $.ajax({
        type: "POST",
        url: 'addressManageActionServlet',
        dataType : "json",
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
                    '<button id="modifyButton_" onclick="openModifyModal('+addressDTO.adrsID+')" style="margin-left: 85%; background-color: skyblue; color: white; border: outset;">수정</button>' +
                    '<button id="deleteButton_" onclick="openDeleteModal('+addressDTO.adrsID+')" style="background-color: lawngreen; color: white; border: outset;">삭제</button>' +
                    '</div>' +
                    '</div>' 
                    
            });
            $('#addressContainer').html(contentHtml);
            if (data.length < 5) {
                $('#addressContainer').append('<button id="insertButton_" onclick="openInsertModal()" style="margin-left:33%; background-color: deepskyblue; color: white; border: outset; padding: 20px 0px; font-size : large">배송지 추가하기</button>');
            }
            
        },
        error: function (error) {
            console.error(error);
        }
        
    });
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