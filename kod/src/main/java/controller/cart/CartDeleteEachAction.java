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

public class CartDeleteEachAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[로그 : 조형련] CartDeleteEachAction 시작");
		
		ActionForward forward = new ActionForward();
		/*선언 */
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		CartDTO cDTO= new CartDTO();
		CartDAO cDAO= new CartDAO();
		
		/*[조형련] 로그인한 회원 정보를 가져오기 위해서 세션 사용, 가져온 정보로 회원 정보 조회*/
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		/*[조형련] 페이지에서 받아온 상품의 PK값을 productID에 저장*/
		int productID=Integer.parseInt(request.getParameter("productID"));
	  //System.out.println("형련 [" + productID + "]");
		
		/*[조형련] 회원이 가지고 있는 장바구니에 해당 상품을 조회하여 개별 상품 삭제를 진행 */
		cDTO.setProductID(productID);
		cDTO.setMemberID(mDTO.getMemberID());
		cDTO.setSearchCondition("개별상품삭제");
		cDAO.delete(cDTO);
		/*[조형련] 정보 삭제 후 장바구니 페이지로 이동*/
		forward.setPath("paySelect.do");
		forward.setRedirect(true);
		
		System.out.println("[로그 : 조형련] CartDeleteEachAction 끝");
		return forward;
	}

}
