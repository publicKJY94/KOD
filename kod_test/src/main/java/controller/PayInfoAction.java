package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class PayInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payInfo.jsp");
		forward.setRedirect(false);
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		//cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session =request.getSession();	
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		cartDTO.setMemberID(memberID);
		datas = cartDAO.selectAll(cartDTO);
		request.setAttribute("cartDTO", datas);
		System.out.println(datas);
		
//		ProductDTO productDTO = new ProductDTO();
//		ProductDAO productDAO = new ProductDAO();
	//	
	//	
//		productDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
//		productDTO = productDAO.selectOneChoice(productDTO);
//		productDTO.setProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
//		System.out.println(productDTO);
//		request.setAttribute("pDTO", productDTO);
		return forward;
	}


}
