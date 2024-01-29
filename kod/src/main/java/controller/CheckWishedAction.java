package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		forward.setPath("product.do");
		forward.setRedirect(false);
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("CheckWishedAction들어옴");
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("로그아웃상태 : memberID is null");
		}
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		wishListDTO.setSearchCondition("찜");
		ArrayList<WishListDTO> isWishedDatas = wishListDAO.selectAll(wishListDTO);
		
		request.setAttribute("isWishedDatas", isWishedDatas);
		
		wishListDTO.setMemberID(memberID);
		System.out.println("memberID >> "+memberID);
		wishListDTO.setSearchCondition("찜수량");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		int updatedWishListCnt = wishListDTO.getWishListCnt();
		System.out.println("updatedWishListCnt >> "+updatedWishListCnt);
		response.getWriter().write(String.valueOf(updatedWishListCnt));
		request.setAttribute("wishListCnt", updatedWishListCnt);
		
		//===페이징처리===
		int productPerPage = 6;
		int currentPage = (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
		                    ? Integer.parseInt(request.getParameter("page"))
		                    : 1;

		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, isWishedDatas.size());

		List<WishListDTO> currentPageProducts = isWishedDatas.subList(startIndex, endIndex);
		ArrayList<WishListDTO> newArrayList = new ArrayList<WishListDTO>(currentPageProducts);
		
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", (int) Math.ceil((double) isWishedDatas.size() / productPerPage));
		request.setAttribute("currentPageProducts", newArrayList);

		
		
		return forward;
	}

}
