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

public class PaymentPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payment.jsp");
		forward.setRedirect(false);
		
		System.out.println("[로그 : 박현민] PaymentPageAction 시작");
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();

		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO) session.getAttribute("memberDTO")).getMemberID(); // 세션에 저장된 사용자의 아이디를 저장
		System.out.println("주문자명 : " + memberID);

		String[] payInfoProducts = request.getParameterValues("productID"); // 결제할 상품 번호들 받아오기
		int payCk = Integer.parseInt(request.getParameter("payCk"));
		System.out.println("payCk : " + payCk);
		if (payInfoProducts != null) { // 결제할 상품이 있다면 해당 상품들의 정보 cartDTO에 저장
			if (payInfoProducts.length <= 1  && payCk == 1) {
				cartDTO = new CartDTO();
				cartDTO.setProductID(Integer.parseInt(payInfoProducts[0]));
				cartDTO.setProductName(request.getParameter("productName"));
				cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
				cartDTO.setPayCk(Integer.parseInt(request.getParameter("payCk"))); // 바로 결제 확인
				System.out.println("바로결제 cartDTO : "+cartDTO);
				request.setAttribute("payNow", cartDTO); // 'payNow'변수에 바로 결제할 상품 정보 저장
			} else {
				for (String product : payInfoProducts) {
					System.out.println("결제할 상품번호 : " + product);
					cartDTO = new CartDTO();
					cartDTO.setMemberID(memberID);
					cartDTO.setProductID(Integer.parseInt(product));
					cartDTO = cartDAO.selectOne(cartDTO);
					cartDTO.setPayCk(Integer.parseInt(request.getParameter("payCk"))); // 장바구니 결제 확인
					// cartDTO.setPg(request.getParameter("pg")); // 추후 pg로 다양한 결제방법 선택(ex :
					// kakaopay, tosspay)
					System.out.println(cartDTO);
					if (cartDTO != null) {
						datas.add(cartDTO); // 리스트에 장바구니를 통해 구매할 상품 정보 저장
						System.out.println("선택된 결제 정보 : " + datas);
					}
				}
			}
		} else { // 결제할 상품이 없다면 문구 출력
			System.out.println("선택된 상품이 없습니다."); // 추후 alert창 띄우기
		}

		request.setAttribute("payDTO", datas); // 'payDTO'변수에 장바구니를 통해 구매할 상품 정보 저장
		System.out.println("payDTO에 담긴 상품 정보 : " + datas);

		return forward;
	}

}
