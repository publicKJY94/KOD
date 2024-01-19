package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

public class PaymentPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("payment.jsp");
		forward.setRedirect(false);
		System.out.println(request.getParameter("productID")+"ㅎㅇ");
		ProductDTO productDTO = new ProductDTO();
		ProductDAO productDAO = new ProductDAO();
		productDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		productDTO = productDAO.selectOne(productDTO);
		productDTO.setProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		System.out.println(productDTO);
		request.setAttribute("pDTO", productDTO);
		return forward;
	}

	
}
