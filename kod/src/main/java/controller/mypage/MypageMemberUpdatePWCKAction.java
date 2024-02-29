package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

public class MypageMemberUpdatePWCKAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		  forward.setPath("mypageMemberUpdatePWCK.jsp"); 
		  forward.setRedirect(false);
		 
		// [정현진]위시리스트 상품수량 
			
			
			
			HttpSession session = request.getSession();
			String memberID = null;
			try {
				memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println("[로그:정현진] 로그아웃상태 : memberID is null");
			}
			System.out.println("[로그:정현진] memberID : "+memberID);
			WishListDTO wishListDTO = new WishListDTO();
			WishListDAO wishListDAO = new WishListDAO();
			wishListDTO.setMemberID(memberID);
			wishListDTO.setSearchCondition("위시리스트합계갯수");
			wishListDTO = wishListDAO.selectOne(wishListDTO);
			int wishListCnt = wishListDTO.getWishListCnt(); 
			request.setAttribute("wishListCnt", wishListCnt);
			System.out.println("[로그:정현진] wishListCnt : "+wishListCnt);
		
		return forward;
	}

}
