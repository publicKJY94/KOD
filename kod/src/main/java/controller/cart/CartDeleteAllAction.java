package controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.CartDAO;
import model.dao.MemberDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class CartDeleteAllAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[로그 : 조형련] CartDeleteAllAction 시작");
		
		ActionForward forward = new ActionForward();
		
		/*선언*/
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		CartDTO cDTO= new CartDTO();
		CartDAO cDAO= new CartDAO();
		
		/*[조형련] 로그인한 회원 정보를 가져오기 위해서 세션 사용, 가져온 정보로 회원 정보 조회*/
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		/*[조형련] 로그인한 회원이 가지고 있는 장바구니를 조회 후 해당 장바구니 전체 삭제*/
		cDTO.setMemberID(mDTO.getMemberID());
		cDTO.setSearchCondition("장바구니비우기");
		cDAO.delete(cDTO);
		
		/*[조형련] 정보 삭제 후 장바구니 페이지로 이동*/
		forward.setPath("paySelect.do");
		forward.setRedirect(true);
				
		System.out.println("[로그 : 조형련] CartDeleteAllAction 끝");
		return forward;
		
	}
}
