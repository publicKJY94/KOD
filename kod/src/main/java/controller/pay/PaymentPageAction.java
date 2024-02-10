package controller.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.CartDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class PaymentPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payment.jsp");
		forward.setRedirect(false);
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session =request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		System.out.println("주문자명 : "+memberID);
		
		
		String[] payInfoProducts = request.getParameterValues("productID"); 		// 결제할 상품 번호들 받아오기
		String[] payInfoProductNames = request.getParameterValues("productName");	// 결제할 상품명들 받아오기
		System.out.println(payInfoProductNames);
//		String productName = null;
//		if(payInfoProductNames.length >1 ) {	
//			productName = payInfoProductNames[0] + "외 " + (payInfoProductNames.length-1) + "개"; // 결제할 상품이 1개 이상이면 '첫번째 상품이름+그 외 개수' 로 저장
//		}else {
//			productName = payInfoProductNames[0];	// 결제할 상품이 1개이면 해당 상품 이름으로 저장
//		}
//		System.out.println(productName);
		System.out.println(payInfoProducts);
		
		if (payInfoProducts != null) {	// 결제할 상품이 있다면 해당 상품들의 정보 cartDTO에 저장
			if(payInfoProducts.length <= 1) {
				cartDTO = new CartDTO();
	        	cartDTO.setProductID(Integer.parseInt(payInfoProducts[0]));
	        	cartDTO.setProductName(request.getParameter("productName"));
	        	cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
	        	cartDTO.setPayCk(Integer.parseInt(request.getParameter("payCk"))); // 바로 결제 확인
	        	System.out.println("바로결제 cartDTO : "+cartDTO);
	        	request.setAttribute("payNow", cartDTO);
			}else {
				for (String product : payInfoProducts) {
			        System.out.println("결제할 상품번호 : " + product);
			        cartDTO = new CartDTO();
			        cartDTO.setMemberID(memberID);
			        cartDTO.setProductID(Integer.parseInt(product));
			        cartDTO = cartDAO.selectOne(cartDTO);
			        cartDTO.setPayCk(Integer.parseInt(request.getParameter("payCk"))); // 장바구니 결제 확인
			        //cartDTO.setPg(request.getParameter("pg")); // 추후 pg로 다양한 결제방법 선택(ex : kakaopay, tosspay)
			        System.out.println(cartDTO);
			        if(cartDTO != null) {
			        	datas.add(cartDTO);
				        System.out.println("선택된 결제 정보 : "+ datas);
			        }
				}
		    }
		} else {	// 결제할 상품이 없다면 문구 출력
		    System.out.println("선택된 상품이 없습니다."); // 추후 alert창 띄우기
		}
		
		request.setAttribute("payDTO", datas);
		System.out.println("payDTO에 담긴 datas 정보 : " + datas);

		//		datas = cartDAO.selectAll(cartDTO);  // 쿼리 수정 필요 : payInfo페이지에서 넘어오는 productID값만 들고가야함
//		request.setAttribute("cData", datas);
		
		
//		Gson cartData = new Gson();
//		String cData = cartData.toJson(datas);
//		System.out.println("결제전 정보 들어오는거 확인 : " + cData);
//		
//		PrintWriter out = response.getWriter();
//		out.print(cData);
//		System.out.println("printout : " + cData);
		
		return forward;
	}

	
}
