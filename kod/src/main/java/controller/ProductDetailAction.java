package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class ProductDetailAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("productDetail.jsp");
		forward.setRedirect(false);

		request.setCharacterEncoding("UTF-8");

		ProductDAO productDAO = new ProductDAO();
		ProductDTO productData = new ProductDTO();
		System.out.println(Integer.parseInt(request.getParameter("productId")));
		productData.setProductID(Integer.parseInt(request.getParameter("productId")));
		productData = productDAO.selectOne(productData);
		System.out.println(productData);
		request.setAttribute("productData", productData);

		return forward;
	}
}
