package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

public class WishListAction implements Action{
	
	
	

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("wishList.jsp");
		forward.setRedirect(false);
		
		request.setCharacterEncoding("UTF-8");
		System.out.println("들어옴 2");
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		
		System.out.println("wishListAction들어옴");
		HttpSession session = request.getSession();
		String memberID = (String)session.getAttribute("member");
		
		System.out.println(memberID);
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("회원별찜목록");
		ArrayList<WishListDTO> wishListDatas = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("wishListDatas", wishListDatas);
		
		System.out.println("들어옴3");
		return forward;
	}
}
