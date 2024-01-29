package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.OrderContentDAO;
import model.dao.ProductDAO;
import model.dto.OrderContentDTO;
import model.dto.ProductDTO;

public class ProductRankAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		forward.setPath("store.jsp");
		forward.setRedirect(false);

		OrderContentDTO orderContentDTO = new OrderContentDTO();
		OrderContentDAO orderContentDAO = new OrderContentDAO();
		
		ArrayList<OrderContentDTO> orderRankDatas = new ArrayList<OrderContentDTO>();
		orderContentDTO.setSearchCondition("top3");
		orderRankDatas = orderContentDAO.selectAll(orderContentDTO);
		request.setAttribute("orderRankDatas", orderRankDatas);
//		ArrayList<ProductDTO> productCategoryDatas = new ArrayList<ProductDTO>();
//		productCategoryDatas = pDAO.selectAllCategory(pDTO);
//		request.setAttribute("productCategoryDatas", productCategoryDatas);
//		System.out.println(productCategoryDatas);
		return forward;
	}
}
