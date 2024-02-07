package controller.pay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dao.ProductDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

public class PayNowInfoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payInfo.jsp");
		forward.setRedirect(false);
		
		HttpSession session =request.getSession();	
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		AddressDTO aDTO = new AddressDTO();
		AddressDAO aDAO = new AddressDAO();
		
		aDTO.setMemberID(memberID);
		aDTO.setSearchCondition("장바구니배송지");
		aDTO = aDAO.selectOne(aDTO);
		System.out.println("결제전 주소지 : "+aDTO);
		request.setAttribute("addressDTO", aDTO);
		
		return forward;
	}

}
