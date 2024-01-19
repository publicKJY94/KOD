package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.dao.OrderContentDAO;
import model.dao.OrderListDAO;
import model.dto.CartDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;

public class OrderListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("main.do");
		forward.setRedirect(false);
		
		System.out.println(request);
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		//데이터 담기 
		HttpSession session =request.getSession();
		
		//json 데이터 확인
		//System.out.println(session.getAttribute("member"));
		oDTO.setMemberID((String)session.getAttribute("member"));
		oDAO.insert(oDTO);
		
		
		oDTO = oDAO.selectOne(oDTO);
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		
		
		// 장바구니 결제
//		CartDTO cDTO = new CartDTO();
//		CartDAO cDAO = new CartDAO();
//		
//		cDAO.selectOne(cDTO);
		
		
		
		//System.out.println(oDTO);
		oContentDTO.setOdListID(oDTO.getOdListID());
		//System.out.println(request.getParameter("productID"));
		oContentDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		oContentDTO.setOdContentCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		//System.out.println(oContentDTO);
		oContentDAO.insert(oContentDTO);
		
		return forward;
	}

	
}
