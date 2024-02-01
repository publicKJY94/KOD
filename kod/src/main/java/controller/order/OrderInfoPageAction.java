package controller.order;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dao.OrderListDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;
import model.dto.OrderListDTO;

public class OrderInfoPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("orderInfo.do");
		forward.setRedirect(false);
		
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session =request.getSession();
		System.out.println("멤버 세션  : "+request.getAttribute("memberDTO"));
		memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		
		String memberID = memberDTO.getMemberID();
		
		AddressDTO aDTO = new AddressDTO();
		AddressDAO aDAO = new AddressDAO();
		
		aDTO.setMemberID(memberID);
		aDTO.setSearchCondition("장바구니배송지");
		aDTO = aDAO.selectOne(aDTO);
		request.setAttribute("addressDTO", aDTO);
		
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		
		oDTO.setMemberID(memberID);
		oDTO = oDAO.selectOne(oDTO);
		System.out.println("oDTO : " + oDTO);
		
		request.setAttribute("orderDTO", oDTO);
		
//		ArrayList<OrderContentDTO> ocDatas = new ArrayList<OrderContentDTO>();
//		OrderContentDTO oContentDTO = new OrderContentDTO();
//		OrderContentDAO oContentDAO = new OrderContentDAO();
//		
//		oContentDTO.setMemberID(memberID);
//		oContentDTO.setOdListID(oDTO.getOdListID());
//		System.out.println("주문내역 상세 dao 들어가기 전 oContentDTO : " + oContentDTO);
//		ocDatas = oContentDAO.selectAll(oContentDTO);
//		System.out.println("ocDatas 로그 : " + ocDatas);
//		request.setAttribute("oContentDTO", ocDatas);
		
		return forward;
	}

	
}
