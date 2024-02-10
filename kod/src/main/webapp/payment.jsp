<%@page import="model.dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
    String name = request.getParameter("memberName");
    int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    //int purchaseCnt = Integer.parseInt(request.getParameter("productCnt"));
    ArrayList<CartDTO> datas = (ArrayList<CartDTO>) request.getAttribute("payDTO"); 
    //String selectPg = request.getParameter("pg");
    
    String[] payInfoProductNames = request.getParameterValues("productName");
	System.out.println(payInfoProductNames);
	String productName = null;
	String encodingName = null;
	if(payInfoProductNames.length >1 ) {
		productName = payInfoProductNames[0] + "외 " + (payInfoProductNames.length-1) + "개";
		//encodingName = new String(productName.getBytes("UTF-8"));
	}else {
		productName = payInfoProductNames[0];
	}
	System.out.println(productName);
	
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
	<%-- <%
	for(CartDTO cart : datas){
	%> --%>
	
	
	
	
	<c:set var="cDatasSize" value="${fn:length(payDTO)}" />
	<c:if test="${cDatasSize >= 1}">
		<c:forEach var="cart" items="${payDTO}">
			<input type="hidden" name="pid" id="pid" value="${cart.productID }">
			<input type="hidden" name="cnt" id="cnt" value="${cart.cartProductCnt}">
			<input type="hidden" name="payCk" id="payCk" value="${cart.payCk}">
		</c:forEach>
	</c:if>
	
	<c:if test="${cDatasSize < 1 }">
		<input type="hidden" name="pid" id="pid" value="${payNow.productID }">
		<input type="hidden" name="cnt" id="cnt" value="${payNow.cartProductCnt}">
		<input type="hidden" name="payCk" id="payCk" value="${payNow.payCk}">
	</c:if>
	
	<%-- <c:if test="${cDatasSize >= 1}">
		<c:set var="payNow" value="${payDTO[0].payCk}" />
	</c:if> --%>
	
	
	<%-- <c:if test="${cDatasSize < 1}">
		<c:set var="payNow" value="${payNow.payCk}" />
	</c:if> --%>
	<%-- <input type="hidden" name="pid" id="pid" value="<%=cart.getProductID()%>">
	<input type="hidden" name="cnt" id="cnt" value="<%=cart.getCartProductCnt()%>"> --%>
	<%-- <%
	}
	%> --%>
	
    <script>
    $(function(){
	    var pid = document.querySelectorAll('input[name=pid]');
	    var cnt = document.querySelectorAll('input[name=cnt]');
	    var payCk = document.querySelectorAll('input[name=payCk]');
	    
	    console.log(pid);
        console.log(cnt);
        console.log(payNow);
        
	    var IMP = window.IMP; // 생략가능
        IMP.init('imp01807501'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        var everythings_fine=true;
        
        
        // 결제할 상품 번호를 배열에 저장하기
        var productID = [];
        pid.forEach(function(cartItem){
        	productID.push(cartItem.value);
        });
        var productIDs = JSON.stringify(productID);
        console.log(productIDs);
		
        // 결제할 상품의 개수를 배열에 저장하기
        var purchaseCnt = [];
        cnt.forEach(function(cartItem){
        	purchaseCnt.push(cartItem.value);
        });
        var purchaseCnts = JSON.stringify(purchaseCnt);
        console.log(purchaseCnts);
        
        
        var payNow = [];
        payCk.forEach(function(cartItem){
        	payNow.push(cartItem.value);
        });
        var payNows = JSON.stringify(payNow);
        console.log("결제방식 : "+payNows);
        /* 
        	결제가 승인되었을 때 웹훅이 호출됨
        	
        */
        
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
                        purchaseCnts : purchaseCnts,
                        payNows : payNows
                    },
                	success: function(){
                		console.log('결제 성공');
                		//성공시 이동할 페이지
                        location.href='orderInfoPage.do';
                	},
                	error : function(error){
                		console.log('에러' , error);
                		//location.href='goback.do';
                	}
                })
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
