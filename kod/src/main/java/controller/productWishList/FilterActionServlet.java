package controller.productWishList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.ProductDAO;
import model.dto.MemberDTO;
import model.dto.ProductDTO;

@WebServlet("/filter")
public class FilterActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FilterActionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* 응답 및 요청 인코딩 설정 */
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		/* ajax요청 로그 */
		System.out.println("\n [김진영] 서블릿에 ajax요청 들어옴");
		/* 선언 및 객체화 */
		String memberID = "";
		String transDatas ="";
		final int won = 10000;	// 가격 단위가 너무 크기때문에 일정 수량만 받고 가격을 조정해주기위한 상수 선언
		Gson gson = new Gson();	// 객체를 JSON타입으로 형변환을 진행해주기 위한 클래스를 선언
		PrintWriter out = response.getWriter();	// ajax에게 결과값을 전달하기 위한 클래스
		ProductDTO productDTO = new ProductDTO();	// 객체생성
		ProductDAO productDAO = new ProductDAO();	// 객체생성
		ArrayList<ProductDTO> productFilterDatas = new ArrayList<ProductDTO>();	// 필터검색이후 데이터를 저장하기 위한 변수 선언
		HttpSession session = request.getSession();	// 요청의 세션에 들어있는 속성을 가져오기 위한 선언
		String categoryList = request.getParameter("categoryList");	// Ajax 요청으로 판단하기 위한 문자열 카테고리
		int max = Integer.parseInt(request.getParameter("max"));	// 가격 슬라이더에서 가져온 max 값 변수
		int min = Integer.parseInt(request.getParameter("min"));	// 가격 슬라이더에서 가져온 min 값 변수
		
		/* 세션에 로그인 정보가 있는지 확인하고 결과에 따라 저장될 정보 */
		if(session.getAttribute("memberDTO")==null) {
			productDTO.setMemberID(null);
		}else {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
			productDTO.setMemberID(memberID);
		}
		/* 가공하기 전 넘어온 카테고리 문자열 확인 로그 */
		System.out.println(categoryList);
		/* 문자열로 된 카테고리 배열을 활용하기 위해 가공하는 과정 */
		categoryList = categoryList.replace("[", "");
		categoryList = categoryList.replace("]", "");
		categoryList = categoryList.replace("\"", "");
		String[] ar = categoryList.split(",");
		//
		System.out.println(ar[0]);
		System.out.println(memberID);
		productDTO.setCategoryList(ar);
		productDTO.setMax(max*won);
		productDTO.setMin(min*won);
		// System.out.println(productDTO.getCategoryList());
		productDTO.setSearchCondition("filter");
		productFilterDatas = productDAO.selectAll(productDTO);
		/* 필터검색 결과 확인용 코드 */
		if(productFilterDatas!=null) {
			System.out.println("필터검색결과 : " + productFilterDatas.get(0));
		}else {
			System.out.println("필터검색결과 없음");
		}
		/* ajax로 결과 전달 */
		if (productFilterDatas != null) {
			transDatas = gson.toJson(productFilterDatas);
			out.println(transDatas);
		}else {
			out.println(transDatas);
		}
	}

}
