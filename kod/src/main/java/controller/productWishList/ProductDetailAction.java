package controller.productWishList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.ReviewDAO;
import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.ReviewDTO;
import model.dto.WishListDTO;

public class ProductDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		ActionForward forward = new ActionForward();
		forward.setPath("productDetail.jsp");
		forward.setRedirect(false);

		System.out.println("[로그 : 정현진] 프로덕트디테일액션 들어옴");
		System.out.println("[로그 : 정현진] 상품ID"+Integer.parseInt(request.getParameter("productID")));
		System.out.println("[로그 : 정현진] 상품카테고리"+(String)request.getParameter("productCategory"));
		
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("로그아웃상태 : memberID is null");
		}
		
		WishListDTO productWishDetailData = null;
		ArrayList<WishListDTO> productWishDatas = null;
		ArrayList<WishListDTO> productIsWishedDatas = new ArrayList<WishListDTO>();
		int wishListCnt=0;

		if(memberID==null) {
			WishListDAO wishListDAO = new WishListDAO();
			WishListDTO wishListDTO = new WishListDTO();
			wishListDTO.setSearchCondition("상품상세페이지LOGOUT");
			wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
			productWishDetailData = wishListDAO.selectOne(wishListDTO);
			productWishDatas = new ArrayList<WishListDTO>();
			wishListDTO.setSearchCondition("연관상품LOGOUT스텝1");
			wishListDTO = wishListDAO.selectOne(wishListDTO);
			int ageRange = wishListDTO.getMemberAge();
			System.out.println("DTO에 저장된 나이대 확인 : "+ageRange);
			wishListDTO.setSearchCondition("연관상품LOGOUT스텝2");
			wishListDTO.setMemberAge(ageRange);
			wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
			productWishDatas = wishListDAO.selectAll(wishListDTO);

		}
		else {
			WishListDAO wishListDAO = new WishListDAO();
			WishListDTO wishListDTO = new WishListDTO();
			wishListDTO.setSearchCondition("상품상세페이지LOGIN");
			wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
			wishListDTO.setMemberID(memberID);
			productWishDetailData = wishListDAO.selectOne(wishListDTO);
			wishListDTO.setSearchCondition("찜수량");
			wishListDTO.setMemberID(memberID);
			wishListDTO = wishListDAO.selectOne(wishListDTO);
			wishListCnt = wishListDTO.getWishListCnt();
			
			wishListDTO.setSearchCondition("나이");
			wishListDTO.setMemberID(memberID);
			WishListDTO memberMinMaxAge = wishListDAO.selectOne(wishListDTO);
			wishListDTO.setSearchCondition("연관상품LOGIN버전2");
			wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
			wishListDTO.setMemberMinAge(memberMinMaxAge.getMemberMinAge());
			wishListDTO.setMemberMaxAge(memberMinMaxAge.getMemberMaxAge());
			productWishDatas = wishListDAO.selectAll(wishListDTO);
			
			for (WishListDTO data : productWishDatas) {
				System.out.println("각 상품별 찜여부 확인로직");
				int dataProductID=data.getProductID();
				System.out.println("dataProductID = "+dataProductID);
				wishListDTO.setSearchCondition("찜여부");
				wishListDTO.setProductID(dataProductID);
				wishListDTO.setMemberID(memberID);
				wishListDTO = wishListDAO.selectOne(wishListDTO);
				System.out.println(wishListDTO.getIsWished());
				productIsWishedDatas.add(wishListDTO);
			}
			for (int i = 0; i < productWishDatas.size(); i++) {
				productWishDatas.get(i).setIsWished(productIsWishedDatas.get(i).getIsWished());
				System.out.println(productIsWishedDatas.get(i).getIsWished());
			}
			
		}    
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setSearchCondition("찜합계");
		wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		wishListDTO=wishListDAO.selectOne(wishListDTO);
		int wishTotalCnt = wishListDTO.getWishTotalCnt();
		
		System.out.println("[정현진LOG] 찜여부확인");
		for (WishListDTO data : productWishDatas) {
			System.out.print("data로그 : "+data.getProductName());
			System.out.println(data.getIsWished());
		}
		request.setAttribute("productWishDetailData", productWishDetailData);
		request.setAttribute("productWishDatas", productWishDatas);
		request.setAttribute("wishListCnt", wishListCnt);
		request.setAttribute("wishTotalCnt", wishTotalCnt);
		request.setAttribute("productIsWishedDatas", productIsWishedDatas);
		
		
		ArrayList<ReviewDTO> productReviewDatas = new ArrayList<ReviewDTO>();
		ReviewDAO reviewDAO = new ReviewDAO();
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setSearchCondition("상품리뷰전체조회");
		reviewDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		productReviewDatas = reviewDAO.selectAll(reviewDTO);
		double totalScore = 0.0;
		double reviewAvgScore = 0.0;
		int totalReviewCount = productReviewDatas.size(); // 리뷰 총개수

		// 리뷰데이터 유효성 검사
		if (productReviewDatas.isEmpty() || productReviewDatas==null) { // 리뷰데이터가 없다면
		    System.out.println("리뷰 데이터 없음");
		} 
		else { // 리뷰데이터가 존재한다면
				for (ReviewDTO data : productReviewDatas) {
				    // 리뷰 이미지가 존재하는지 확인
				    if (data.getReviewImg() != null && !data.getReviewImg().isEmpty()) {
				        // 이미지가 있는 경우의 처리
				        totalScore += data.getReviewScore();
				        Path path = Paths.get(data.getReviewImg());
				        System.out.println("[로그:정현진] 리뷰 이미지 경로: " + path);
	
				        // 상대 경로에서 파일명만 추출
				        String relativePath = path.getFileName().toString();
				        System.out.println("[로그:정현진] 파싱된 relativePath: " + relativePath);
	
				        // 추출한 파일명을 리뷰 데이터의 이미지 경로로 설정
				        data.setReviewImg(relativePath);
	
				        System.out.println("[로그:정현진] " + data.getMemberName() + "회원 " + data.getReviewScore() + "점");
				    } else {
				        // 이미지가 없는 경우의 처리
//				        // 기본 이미지로 설정하거나 다른 처리를 수행
				    	System.out.println("리뷰작성 시 이미지 업로드하지않음");
//				        data.setReviewImg("defaultImage.jpg"); // 기본 이미지 파일명으로 설정 (프로젝트에 실제로 존재하는 파일명으로 변경)
				        System.out.println("[로그:정현진] " + data.getMemberName() + "회원 " + data.getReviewScore() + "점 (이미지 없음)");
				    }
				}
				for (ReviewDTO data : productReviewDatas) {
				    System.out.println("[로그:정현진] 리뷰이미지"+data.getReviewImg());
				    System.out.println("[로그:정현진] 리뷰작성회원명"+data.getMemberName());
				}
				reviewAvgScore = Math.round((totalScore / totalReviewCount) * 100.0) / 100.0;
				System.out.println("총점" + totalScore);
				System.out.println("평점" + reviewAvgScore);

				request.setAttribute("productReviewDatas", productReviewDatas);
				request.setAttribute("reviewAvgScore", reviewAvgScore);

				// 리뷰데이터가 존재할 때만 필요한 변수들이라 else문 안에 선언
				int oneScoreCount = 0;
				int twoScoreCount = 0;
				int threeScoreCount = 0;
				int fourScoreCount = 0;
				int fiveScoreCount = 0;

				for (ReviewDTO data : productReviewDatas) {
				    // 각 별점 개수 카운트
				    if (data.getReviewScore() == 1) {
				        oneScoreCount++;
				    } else if (data.getReviewScore() == 2) {
				        twoScoreCount++;
				    } else if (data.getReviewScore() == 3) {
				        threeScoreCount++;
				    } else if (data.getReviewScore() == 4) {
				        fourScoreCount++;
				    } else if (data.getReviewScore() == 5) {
				        fiveScoreCount++;
				    }
				}

				// 각 별점 비율 계산
				double oneScoreRatio = (double) oneScoreCount / totalReviewCount * 100;
				double twoScoreRatio = (double) twoScoreCount / totalReviewCount * 100;
				double threeScoreRatio = (double) threeScoreCount / totalReviewCount * 100;
				double fourScoreRatio = (double) fourScoreCount / totalReviewCount * 100;
				double fiveScoreRatio = (double) fiveScoreCount / totalReviewCount * 100;

				request.setAttribute("oneScoreCount", oneScoreCount);
				request.setAttribute("twoScoreCount", twoScoreCount);
				request.setAttribute("threeScoreCount", threeScoreCount);
				request.setAttribute("fourScoreCount", fourScoreCount);
				request.setAttribute("fiveScoreCount", fiveScoreCount);
				request.setAttribute("oneScoreRatio", oneScoreRatio);
				request.setAttribute("twoScoreRatio", twoScoreRatio);
				request.setAttribute("threeScoreRatio", threeScoreRatio);
				request.setAttribute("fourScoreRatio", fourScoreRatio);
				request.setAttribute("fiveScoreRatio", fiveScoreRatio);
		    
		    /*
		     * oneScoreCount, twoScoreCount, ..., fiveScoreRatio는 
		     * 특정 별점에 대한 카운트와 비율을 계산하는 변수들로, 이들은 리뷰 데이터가 있을 때만 계산해야 합니다. 
		     * 반면에 totalScore, reviewAvgScore, totalReviewCount는 
		     * 전체 리뷰 데이터에 대한 총점, 평균점수, 총 리뷰 수 등을 나타내므로, 리뷰 데이터의 존재 여부와 관계없이 계산할 수 있기때문에 변수의 선언위치가 다른것입니다.
		     */
		
		}
		
		
		
		//===페이징 처리===
//		int productPerPage = 3;
//		int currentPage = (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
//		                    ? Integer.parseInt(request.getParameter("page"))
//		                    : 1;
//		int totalPages = (int) Math.ceil((double) productReviewDatas.size() / productPerPage);
//
//		int startIndex = (currentPage - 1) * productPerPage;
//		int endIndex = Math.min(startIndex + productPerPage, productReviewDatas.size());
//
//		List<ReviewDTO> currentPageProducts = productReviewDatas.subList(startIndex, endIndex);
//		ArrayList<ReviewDTO> newArrayList = new ArrayList<ReviewDTO>(currentPageProducts);
//		
//		request.setAttribute("currentPage", currentPage);
//		request.setAttribute("totalPages", totalPages);
//		request.setAttribute("currentPageProducts", newArrayList);
//		
//		System.out.println("[정현진LOG] currentPage : "+currentPage);
//		System.out.println("[정현진LOG] totalPages : "+totalPages);
		
		
		int productPerPage = 3;
		int currentPage = (request.getParameter("page") != null && !request.getParameter("page").isEmpty())
		                    ? Integer.parseInt(request.getParameter("page"))
		                    : 1;
		int totalPages = (int) Math.ceil((double) productReviewDatas.size() / productPerPage);

		// 페이지 그룹 크기 설정
		int pagesPerGroup = 5;
		int currentGroup = (int) Math.ceil((double) currentPage / pagesPerGroup);
		int startPageOfGroup = (currentGroup - 1) * pagesPerGroup + 1;
		int endPageOfGroup = Math.min(startPageOfGroup + pagesPerGroup - 1, totalPages);

		int startIndex = (currentPage - 1) * productPerPage;
		int endIndex = Math.min(startIndex + productPerPage, productReviewDatas.size());

		List<ReviewDTO> currentPageProducts = productReviewDatas.subList(startIndex, endIndex);
		ArrayList<ReviewDTO> newArrayList = new ArrayList<>(currentPageProducts);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("totalPages", totalPages);
		request.setAttribute("currentPageProducts", newArrayList);
		request.setAttribute("startPageOfGroup", startPageOfGroup);
		request.setAttribute("endPageOfGroup", endPageOfGroup);
		
		
		
		
		
		
		
		
		return forward;
	}
}
