package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.ProductDAO;
import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;
import model.dto.WishListDTO;

public class CheckWishedAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("store.jsp");
		forward.setRedirect(false);
		
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("CheckWishedAction들어옴");
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("찜");
		ArrayList<WishListDTO> isWishedDatas = wishListDAO.selectAll(wishListDTO);
		
		ProductDTO pDTO = new ProductDTO();
		ProductDAO pDAO = new ProductDAO();
//
//		ArrayList<ProductDTO> productDatas = new ArrayList<ProductDTO>();
//		productDatas = pDAO.selectAll(pDTO);
//		request.setAttribute("productDatas", productDatas);
		ArrayList<ProductDTO> productCategoryDatas = new ArrayList<ProductDTO>();
		productCategoryDatas = pDAO.selectAllCategory(pDTO);
		request.setAttribute("productCategoryDatas", productCategoryDatas);
		System.out.println(productCategoryDatas);
		
		request.setAttribute("isWishedDatas", isWishedDatas);
		
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("찜수량");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		int wishListCnt = wishListDTO.getWishListCnt();
		request.setAttribute("wishListCnt", wishListCnt);
		System.out.println("wishListCnt : "+wishListCnt);
		
		
		
		
		
		return forward;
	}

}
