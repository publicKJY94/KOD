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
import model.dao.WishListDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

public class PaySelectAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("형련[로그] payselectAction 진입 ");
		ActionForward forward = new ActionForward();
		forward.setPath("paySelect.jsp");
		forward.setRedirect(false);
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		//cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session =request.getSession();	
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		cartDTO.setMemberID(memberID);
		datas = cartDAO.selectAll(cartDTO);
		
		//System.out.println("최대 개수 로직 실행 전 데이터 : " + datas);
		for(CartDTO cData : datas) {
			if(cData.getCartProductCnt()>10) {
				System.out.println("최대 개수는 10개입니다.");
				cData.setCartProductCnt(10);
			}
		}
		//System.out.println("최대 개수 로직 실행 후 데이터 : " + datas);
		
		request.setAttribute("cartDTO", datas);
		
		System.out.println("형련 [로그] 장바구니데이터: "+datas);
		
		System.out.println("형련[로그] payselectAction 끝 ");
		
		// [정현진]위시리스트 상품수량 
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
