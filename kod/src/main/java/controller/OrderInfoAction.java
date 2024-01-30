package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.OrderContentDAO;
import model.dto.MemberDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;

public class OrderInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("/orderInfo.jsp");
		forward.setRedirect(false);
		
		ArrayList<OrderContentDTO> ocDatas = new ArrayList<OrderContentDTO>();
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		OrderListDTO oDTO = new OrderListDTO();
		MemberDTO mDTO = new MemberDTO();
		
		
		HttpSession session = request.getSession();
		System.out.println(session.getAttribute("memberDTO"));
		mDTO = (MemberDTO)session.getAttribute("memberDTO");
		String memberID = mDTO.getMemberID();
		
		oDTO = (OrderListDTO)request.getAttribute("orderDTO");
		oContentDTO.setSearchCondition("결제내역");
		oContentDTO.setMemberID(memberID);
		oContentDTO.setOdListID(oDTO.getOdListID());
		System.out.println("주문내역 상세 dao 들어가기 전 oContentDTO : " + oContentDTO);
		ocDatas = oContentDAO.selectAll(oContentDTO);
		System.out.println("ocDatas 로그 : " + ocDatas);
		request.setAttribute("oContentDTO", ocDatas);
		
		return forward;
	}

	
}
