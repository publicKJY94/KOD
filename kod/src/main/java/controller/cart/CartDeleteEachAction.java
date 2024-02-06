package controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.CartDAO;
import model.dao.MemberDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class CartDeleteEachAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		forward.setPath("paySelect.do");
		forward.setRedirect(false);
		
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
	
		int productID=Integer.parseInt(request.getParameter("productID"));
		System.out.println("형련 [" + productID + "]");
		
		CartDTO cDTO= new CartDTO();
		CartDAO cDAO= new CartDAO();
		
		
		//cDTO.setCartID(Integer.parseInt(cartId));
		cDTO.setProductID(productID);
		cDTO.setMemberID(mDTO.getMemberID());
		cDTO.setSearchCondition("개별상품삭제");
		
		cDAO.delete(cDTO);
		
		return forward;
	}

}
