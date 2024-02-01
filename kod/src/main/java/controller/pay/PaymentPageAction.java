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
		
		
		String[] payInfoProducts = request.getParameterValues("productID");
		System.out.println(payInfoProducts);
		
		if (payInfoProducts != null) {
		    for (String product : payInfoProducts) {
		        System.out.println("결제할 상품번호 : " + product);
		        cartDTO = new CartDTO();
		        cartDTO.setMemberID(memberID);
		        cartDTO.setProductID(Integer.parseInt(product));
		        cartDTO = cartDAO.selectOne(cartDTO);
		        System.out.println(cartDTO);
		        datas.add(cartDTO);
		        System.out.println("선택된 결제 정보 : "+ datas);
		    }
		} else {
		    System.out.println("선택된 상품이 없습니다."); // 추후 alert창 띄우기
		}
		
		request.setAttribute("payDTO", datas);
		System.out.println("payDTO에 담긴 datas 정보 : " + datas);

		//		datas = cartDAO.selectAll(cartDTO);  // 쿼리 수정 필요 : payInfo페이지에서 넘어오는 productID값만 들고가야함
//		request.setAttribute("cData", datas);
		
		
		Gson cartData = new Gson();
		String cData = cartData.toJson(datas);
		System.out.println("결제전 정보 들어오는거 확인 : " + cData);
		
		PrintWriter out = response.getWriter();
		out.print(cData);
		System.out.println("printout : " + cData);
		
		return forward;
	}

	
}
