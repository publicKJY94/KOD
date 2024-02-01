package controller.productWishList;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
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
//			e.printStackTrace();
			System.out.println("로그아웃상태 : memberID is null");
		}
		System.out.println("memberID : "+memberID);
		if(memberID==null) {
			WishListDAO wishListDAO = new WishListDAO();
			WishListDTO wishListDTO = new WishListDTO();
			wishListDTO.setSearchCondition("인기상품LOGOUT");
			ArrayList<WishListDTO> popularAllItems = wishListDAO.selectAll(wishListDTO);
			request.setAttribute("popularAllItems", popularAllItems);
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
		
		wishListDAO = new WishListDAO();
		wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("인기상품LOGIN");
		ArrayList<WishListDTO> popularAllItems = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("popularAllItems", popularAllItems);
		
		}
		
		
		WishListDAO	wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberMinAge(10);
		wishListDTO.setMemberMaxAge(20);
		wishListDTO.setSearchCondition("나이별찜랭킹");
		ArrayList<WishListDTO> teenagerRanking = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("teenagerRanking", teenagerRanking);
		
		wishListDTO.setMemberMinAge(20);
		wishListDTO.setMemberMaxAge(30);
		wishListDTO.setSearchCondition("나이별찜랭킹");
		ArrayList<WishListDTO> twentyRanking = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("twentyRanking", twentyRanking);

		wishListDTO.setMemberMinAge(30);
		wishListDTO.setMemberMaxAge(40);
		wishListDTO.setSearchCondition("나이별찜랭킹");
		ArrayList<WishListDTO> thirtyRanking = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("thirtyRanking", thirtyRanking);
		
		
		
		
		
		
		//=== 인기상품 ===
//		WishListDAO wishListDAO = new WishListDAO();
//		WishListDTO wishListDTO = new WishListDTO();
//		wishListDTO.setSearchCondition("인기상품");
//		ArrayList<WishListDTO> popularAllItems = wishListDAO.selectAll(wishListDTO);
//		request.setAttribute("popularAllItems", popularAllItems);
		
		
		
		
		
		
		
		return forward;
	}

	
}
