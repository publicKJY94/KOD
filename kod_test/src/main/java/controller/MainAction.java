package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		System.out.println("MainAction들어옴");
		forward.setPath("main.jsp");
		
		forward.setRedirect(false);
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("memberID : "+memberID);
		if(memberID==null) {
			
		}
		else {
		WishListDTO wishListDTO = new WishListDTO();
		WishListDAO wishListDAO = new WishListDAO();
		
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("찜수량");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		int wishListCnt = wishListDTO.getWishListCnt();
		request.setAttribute("wishListCnt", wishListCnt);
		System.out.println("wishListCnt : "+wishListCnt);
		}
		
		
		
		return forward;
	}

	
}
