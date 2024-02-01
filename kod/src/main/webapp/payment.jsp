<%@page import="model.dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String name = request.getParameter("memberName");
    int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    String productName = request.getParameter("productName").trim();
    int purchaseCnt = Integer.parseInt(request.getParameter("productCnt"));
    /* String email = request.getParameter("memberEmail");
    String phone = request.getParameter("memberPhNum");
    String address = request.getParameter("adrsStreet")+request.getParameter("adrsDetail");
    int productID = Integer.parseInt(request.getParameter("productID"));
    int productPrice = Integer.parseInt(request.getParameter("productPrice"));*/
    ArrayList<CartDTO> datas = (ArrayList<CartDTO>) request.getAttribute("payDTO"); 
    
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
</head>
<body>
	<%
	for(CartDTO cart : datas){
	%>
	<input type="hidden" name="pid" id="pid" value="<%=cart.getProductID()%>">
	<%
	}
	%>
	
    <script>
    //transPid = JSON.stringfy(pid);
    
    $(function(){
	    var pid = document.querySelectorAll('input[type=hidden]');
	    console.log(pid);
        var IMP = window.IMP; // 생략가능
        IMP.init('imp01807501'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        var everythings_fine=true;
        
        /* var list2 = [];
        var productIDs = document.querySelectorAll("input[name=productID]");
        productIDs.forEach(
           function(product){
                 list2.push(product.value);      
           }
        );
        var productIDs = JSON.stringify(list2); */
        //var product = JSON.parse(${param});
        //var product = ${cData};
       
        var productID = [];
        pid.forEach(function(cartItem){
        	productID.push(cartItem.value);
        });
        var productIDs = JSON.stringify(productID);
        console.log(productIDs);

        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '<%=productName%>',
            amount : '<%=totalPrice%>',
            productIDs : 'productIDs',
            purchaseCnt : 'purchaseCnt',
            buyer_email : 'email',
            buyer_name : 'name',
            buyer_tel : 'phone',
            buyer_addr : 'address',
            buyer_postcode : '123-456',
        }, function(rsp) {
            if ( rsp.success ) {
            	console.log('로그');
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                $.ajax({
                    url: "paymentActionServlet", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    //dataType:'json', // 상품번호를 여러개 받기 위해 사용
                    data: {
                        imp_uid : rsp.imp_uid,
                        productIDs : productIDs,
                        purchaseCnt : <%=purchaseCnt%>
                    },
                	success: function(){
                		console.log('결제 성공');
                		//성공시 이동할 페이지
                        location.href='orderInfoPage.do';
                	},
                	error : function(error){
                		console.log('에러' , error);
                	}
                    
                }).done(function(data) {
                	//[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                    if ( everythings_fine ) {
                        msg = '결제가 완료되었습니다.';
                        msg += '\n고유ID : ' + rsp.imp_uid;
                        msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                        msg += '\결제 금액 : ' + rsp.paid_amount;
                        msg += '카드 승인번호 : ' + rsp.apply_num;
                        
                        alert(msg);
                    } else {
                        //[3] 아직 제대로 결제가 되지 않았습니다.
                        //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                    } 
                });
                //location.href='orderInfoPage.do';
            } else {
                msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
                //실패시 이동할 페이지
                location.href="fail.do";
                alert(msg);
            }
        }); 
       /* function (rsp) {
        	console.log(rsp);
        	if (rsp.success) {
        		var msg = '결제가 완료되었습니다.';
        		msg += '고유ID : ' + rsp.imp_uid;
        		msg += '상점 거래ID : ' + rsp.merchant_uid;
        		msg += '결제 금액 : ' + rsp.paid_amount;
        		msg += '카테고리  : ' + rsp.category;
        		msg += '상품정보  : ' + rsp.info;
        		msg += '카드 승인번호 : ' + rsp.apply_num;
        		msg += '구매자 : ' + rsp.buyer_name;
        		
        		} else {
        			var msg = '결제에 실패하였습니다.';
        			msg += '에러내용 : ' + rsp.error_msg;
        			}
        	alert(msg);
        	}); */
    }); 
    </script>
 
</body>
</html>
