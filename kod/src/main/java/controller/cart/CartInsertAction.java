package controller.cart;

import java.io.IOException;
import java.util.ArrayList;

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
//		forward.setPath("payInfoPage.do");
//		forward.setRedirect(false);
//		
//		CartDTO cartDTO = new CartDTO();
//		CartDAO cartDAO = new CartDAO();
//		
//		cartDTO.setProductID(Integer.parseInt(request.getParameter("productID"))); 			// productID(상품번호)
//		cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));	// 구매개수
//		
//		HttpSession session =request.getSession();
//		System.out.println(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
//		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
//		cartDTO.setMemberID(memberID);		// 사용자ID
//		
//		cartDAO.insert(cartDTO);
		
		
		// VER2. 장바구니 담기 -> 장바구니에 담는 로직만 구현(장바구니에 같은 상품이 있다면 UPDATE, 없다면 INSERT)
		
		forward.setPath("productDetail.do");
		forward.setRedirect(false);
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		
		cartDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		int productID = Integer.parseInt(request.getParameter("productID"));
		cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		cartDTO.setMemberID(memberID);
		
		// 장바구니의 모든 데이터를 가져와서 상품번호 비교
		ArrayList<CartDTO> datas =cartDAO.selectAll(cartDTO);
		System.out.println("장바구니 전체 데이터 : " + datas);
		
		boolean flag = false;
		for(CartDTO data : datas) {
			if(data.getProductID()==productID) {
				flag = true;
				System.out.println(flag);
				if(flag) {
					cartDTO.setSearchCondition("장바구니같은상품");
					System.out.println("이미 장바구니에 같은 상품이 있을 때 : "+cartDTO);
					cartDAO.update(cartDTO);
					System.out.println("장바구니 업데이트 완료");
				}
			}else {
				System.out.println("장바구니 새상품 추가 flag" + flag);
				cartDAO.insert(cartDTO);
				System.out.println("장바구니 새상품 추가 완료");
			}
		}
		
		
//		boolean flag=false;
//		for( DTO data: 장바구니)
//		   if(내가 넣으려는애==data)
//		      flag=true;
//		if (flag) {
//		   cDAO.update(cDTO);
//		else {
//		   cDAO.insert(cDTO)
//		} 
		
		
		return forward;
	}

	
}
