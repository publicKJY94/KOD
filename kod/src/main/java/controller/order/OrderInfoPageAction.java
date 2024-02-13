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
		
		System.out.println("[로그 : 박현민] OrderInfoPageAction 시작");
		
		MemberDTO memberDTO = new MemberDTO();
		HttpSession session =request.getSession();
		//System.out.println("멤버 세션  : "+request.getAttribute("memberDTO"));
		memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		
		String memberID = memberDTO.getMemberID();	// 세선에서 받아온 사용자의 ID를 memberID 변수에 저장
		
		AddressDTO aDTO = new AddressDTO();
		AddressDAO aDAO = new AddressDAO();
		
		aDTO.setMemberID(memberID);
		aDTO.setSearchCondition("장바구니배송지");
		aDTO = aDAO.selectOne(aDTO);				// 사용자의 주소지 중 가장 최근에 생성된 주소지 정보를 가져옴
		request.setAttribute("addressDTO", aDTO);	// 'addressDTO'에 가져온 사용자의 주소지 정보를 저장
		
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		
		oDTO.setMemberID(memberID);
		oDTO = oDAO.selectOne(oDTO);				// 주문내역 중 가장 최근 번호(방금 구매한 내역)을 가져옴
		//System.out.println("oDTO : " + oDTO);
			
		request.setAttribute("orderDTO", oDTO);		// 'orderDTO'에 주문 내역 번호를 저장
		
		return forward;
	}

	
}
