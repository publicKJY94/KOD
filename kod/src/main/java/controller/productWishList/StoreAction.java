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

public class StoreAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("productCategory.do");
		forward.setRedirect(false);
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("StoreAction들어옴");
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
		int productPerPage = 6; // 페이지당 보여줄 상품개수
		int currentPage = // 현재페이지
				(request.getParameter("page") != null && !request.getParameter("page").isEmpty())
		        ? Integer.parseInt(request.getParameter("page"))
		        : 1; 
		// "page" 는 사용자가 선택한 page를 의미함, url에 존재하여 getParameter() 사용가능
		// 처음 페이지가 로드될 때는 1 , 사용자가 페이지 번호를 눌렀을 때는 사용자가 요청한 페이지번호가 부여됨
		// 페이지는 url에서 확인가능
		

		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, isWishedDatas.size());
		/*
		currentPage: 현재 페이지 번호를 나타내는 변수입니다.
		productPerPage: 페이지당 보여줄 상품의 수를 나타내는 변수입니다.
		startIndex: 현재 페이지에서 보여줄 상품의 시작 인덱스를 계산하는 변수입니다. 현재 페이지의 이전 페이지들에서 보여진 상품의 수를 곱하면 됩니다.
		endIndex: 현재 페이지에서 보여줄 상품의 끝 인덱스를 계산하는 변수입니다. 시작 인덱스에 페이지당 보여줄 상품의 수를 더하되, 전체 상품 수를 초과하지 않도록 Math.min 함수를 사용하여 계산합니다.
		*/

		List<WishListDTO> currentPageProducts = isWishedDatas.subList(startIndex, endIndex);
		ArrayList<WishListDTO> newArrayList = new ArrayList<WishListDTO>(currentPageProducts);
		
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", (int) Math.ceil((double) isWishedDatas.size() / productPerPage));
		request.setAttribute("currentPageProducts", newArrayList);

		
		
		return forward;
	}

}
