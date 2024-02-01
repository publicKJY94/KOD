package controller.pay;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		cartDTO.setMemberID(memberID);
		datas = cartDAO.selectAll(cartDTO);
		request.setAttribute("cartDTO", datas);
		
//		Gson cartData = new Gson();
//		String cData = cartData.toJson(datas);
//		
//		PrintWriter out = response.getWriter();
//		out.print(cData);
		
		return forward;
	}

	
}
