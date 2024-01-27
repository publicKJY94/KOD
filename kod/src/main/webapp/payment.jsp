<%@page import="model.dto.ProductDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.dto.*, java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	ArrayList<CartDTO> cDatas = (ArrayList<CartDTO>)request.getAttribute("cartDTO");
	System.out.print(cDatas);
    String name = request.getParameter("memberName");
    String email = request.getParameter("memberEmail");
    String phone = request.getParameter("memberPhNum");
    String address = "서울"; //request.getParameter("adrsStreet")+request.getParameter("adrsDetail");
    int totalPrice = Integer.parseInt(request.getParameter("totalPrice"));
    int productID = Integer.parseInt(request.getParameter("productID"));
    int purchaseCnt = Integer.parseInt(request.getParameter("productCnt"));
    String productName = request.getParameter("productName");
    int productPrice = Integer.parseInt(request.getParameter("productPrice"));
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
    <script>
    $(function(){
        var IMP = window.IMP; // 생략가능
        IMP.init('imp01807501'); // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
        var msg;
        
        <%-- <%for(CartDTO cData:cDatas){%>
        var cartItems = [
            {
                productName: 'Item 1',
                productPrice: 1000,
                email: 'user1@example.com',
                name: 'User 1',
                phone: '123456789',
                address: '123 Main St'
            },
            // Add more items as needed
        ];
        <%}%> --%>
        
        
        IMP.request_pay({
            pg : 'kakaopay',
            pay_method : 'card',
            merchant_uid : 'merchant_' + new Date().getTime(),
            name : '<%=productName%>',
            amount : '<%=productPrice%>',
            buyer_email : '<%=email%>',
            buyer_name : '<%=name%>',
            buyer_tel : '<%=phone%>',
            buyer_addr : '<%=address%>',
            buyer_postcode : '123-456',
            //m_redirect_url : 'http://www.naver.com'
        }, function(rsp) {
            if ( rsp.success ) {
            	console.log('로그');
                //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
                jQuery.ajax({
                    url: "orderList.do?productID=<%=productID%>&purchaseCnt=<%=purchaseCnt%>", //cross-domain error가 발생하지 않도록 주의해주세요
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        imp_uid : rsp.imp_uid,
                        productID : rsp.productID
                        //기타 필요한 데이터가 있으면 추가 전달
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
                //성공시 이동할 페이지
                location.href='orderInfo.jsp';
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
