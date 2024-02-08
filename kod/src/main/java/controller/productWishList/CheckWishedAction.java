package controller.productWishList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.WishListDAO;
import model.dto.MemberDTO;
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
			System.out.println("[로그:정현진] 로그아웃상태 : memberID is null");
		}
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		if(request.getParameter("searchKeyword")!=null) {
			System.out.println("[로그] 검색 값 들어옴");
			wishListDTO.setSearchCondition("search");
			wishListDTO.setSearchKeyword(request.getParameter("searchKeyword"));
		}else {
			System.out.println("[로그] 검색 null 들어옴");
			wishListDTO.setSearchCondition("찜");
		}
		
		ArrayList<WishListDTO> isWishedDatas = wishListDAO.selectAll(wishListDTO);
		request.setAttribute("isWishedDatas", isWishedDatas);
		
		wishListDTO.setMemberID(memberID);
		System.out.println("memberID >> "+memberID);
		wishListDTO.setSearchCondition("위시리스트합계갯수");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		int wishListCnt = wishListDTO.getWishListCnt();
		request.setAttribute("wishListCnt", wishListCnt);
		
		//===페이징처리===
		int productPerPage = 6; // 페이지당 6개씩 로드
		int currentPage = (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
		                    ? Integer.parseInt(request.getParameter("page"))
		                    : 1; 
		// 처음 페이지가 로드될 때는 1 , 사용자가 페이지 번호를 눌렀을 때는 사용자가 요청한 페이지번호가 부여됨
		// 페이지는 url에서 확인가능
		

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
