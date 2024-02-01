package controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.CartDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class CartInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payInfoPage.do");
		forward.setRedirect(false);
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		
		cartDTO.setProductID(Integer.parseInt(request.getParameter("productID"))); 			// productID(상품번호)
		cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));	// 구매개수
		
		HttpSession session =request.getSession();
		System.out.println(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		cartDTO.setMemberID(memberID);		// 사용자ID
		
		cartDAO.insert(cartDTO);
		return forward;
	}

	
}
