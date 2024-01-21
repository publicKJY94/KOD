package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class ProductAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("store.jsp");
		forward.setRedirect(false);

		request.setCharacterEncoding("UTF-8");

		ProductDTO pDTO = new ProductDTO();
		ProductDAO pDAO = new ProductDAO();

		ArrayList<ProductDTO> productDatas = new ArrayList<ProductDTO>();
		productDatas = pDAO.selectAll(pDTO);
		request.setAttribute("productDatas", productDatas);
		ArrayList<ProductDTO> productCategoryDatas = new ArrayList<ProductDTO>();
		productCategoryDatas = pDAO.selectAllCategory(pDTO);
		request.setAttribute("productCategoryDatas", productCategoryDatas);
		System.out.println(productCategoryDatas);
		return forward;
	}
}
