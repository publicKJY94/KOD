package controller;

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
import model.dto.ProductDTO;

/**
 * Servlet implementation class TESTTEST
 */
@WebServlet("/test")
public class TESTTEST extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TESTTEST() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		final int won = 10000;
		Gson gson = new Gson();
		HttpSession session = request.getSession();
		System.out.println("\n [김진영] 서블릿에 ajax요청 들어옴");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = new ProductDTO();
		ArrayList<ProductDTO> productFilterDatas = new ArrayList<ProductDTO>();
		String categoryList = request.getParameter("categoryList");
		int max = Integer.parseInt(request.getParameter("max"));
		int min = Integer.parseInt(request.getParameter("min"));
		System.out.println(categoryList);
		categoryList = categoryList.replace("[", "");
		categoryList = categoryList.replace("]", "");
		categoryList = categoryList.replace("\"", "");
		String[] ar = categoryList.split(",");

		System.out.println(ar[0]);
		productDTO.setCategoryList(ar);
		productDTO.setMax(max*won);
		productDTO.setMin(min*won);
		// System.out.println(productDTO.getCategoryList());
		if(session.getAttribute("memberDTO")!=null) {
			productDTO.setSearchCondition("로그인상품필터");
		}else {
			productDTO.setSearchCondition("비로그인상품필터");
		}
		productFilterDatas = productDAO.selectCategory(productDTO);
		//필터검색 결과 확인용 코드
		if(productFilterDatas!=null) {
			productFilterDatas.get(0);
		}else {
			System.out.println("null임....");
		}
//		System.out.println(productFilterDatas.get(0));
		PrintWriter out = response.getWriter();
//		System.out.println("===");
//		System.out.println(productFilterDatas);
//		System.out.println("===");
//		out.print(productFilterDatas);
		if (productFilterDatas != null) {
			String transDatas = gson.toJson(productFilterDatas);
			out.println(transDatas);
		}
	}

}
