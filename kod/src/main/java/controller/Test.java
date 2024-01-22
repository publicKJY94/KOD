package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

@WebServlet("/test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Test() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		ProductDAO productDAO = new ProductDAO();
		ProductDTO productDTO = new ProductDTO();
		ArrayList<ProductDTO> productDatas = new ArrayList<ProductDTO>();
		String categoryList = request.getParameter("categoryList");
		categoryList = categoryList.replace("[", "");
		categoryList = categoryList.replace("]", "");
		categoryList = categoryList.replace("\"", "");
		String[] ar = categoryList.split(",");
		//System.out.println(ar[0]);
		productDTO.setCategoryList(ar);
		//System.out.println(productDTO.getCategoryList());
		productDatas = productDAO.selectAllCategory(productDTO);
		
		PrintWriter out = response.getWriter();
		out.print(productDatas);
	}
}
