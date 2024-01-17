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
		
		request.setCharacterEncoding("UTF-8");
		
		/*
		 * 세션으로 부터 로그인 정보를 받아온다.
		 * 로그인 하지 않았다면
		 * 위시리스트 정보 없음
		 * 로그인을 했다면
		 * 위시리스트 정보 반환하기
		 */
		
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		
		System.out.println("wishListAction들어옴");
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("member")).getMemberID();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(memberID==null) {
			request.setAttribute("msg", "로그인 후 이용해주세요.");
			forward.setPath("alert.do");
			forward.setRedirect(false);
			
		}
		else {
			System.out.println(memberID);
			wishListDTO.setMemberID(memberID);
			wishListDTO.setSearchCondition("회원별찜목록");
			ArrayList<WishListDTO> wishListDatas = wishListDAO.selectAll(wishListDTO);
			request.setAttribute("wishListDatas", wishListDatas);
			forward.setPath("wishList.jsp");
			forward.setRedirect(false);
		}
		
		
		
		return forward;
	}
}
