package controller.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import controller.util.Action;
import controller.util.ActionForward;

import model.dao.MemberDAO;
import model.dao.OrderContentDAO;
import model.dao.OrderListDAO;
import model.dto.MemberDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;

public class MypageOrderListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("myOrderList.jsp");
		forward.setRedirect(false);

		System.out.println("myOrderListAction 진입");

		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();

		HttpSession session = request.getSession();
		mDTO.setMemberID(((MemberDTO) session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);

		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();

		oDTO.setMemberID(mDTO.getMemberID());
		ArrayList<OrderListDTO> oDatas = oDAO.selectAll(oDTO);
		System.out.println("형련[로그 oDatas 결과값 : " + oDatas + "]");
		request.setAttribute("oDTO", oDatas);

		//request.setAttribute("oDTO", oDatas);

		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		ArrayList<OrderContentDTO> datasTotal = new ArrayList<OrderContentDTO>();
		ArrayList<OrderContentDTO> oContentDatas = new ArrayList<OrderContentDTO> ();
	
		for (OrderListDTO oData : oDatas) {
			oContentDTO.setOdListID(oData.getOdListID());
			oContentDTO.setSearchCondition("결제내역");
			oContentDatas = oContentDAO.selectAll(oContentDTO);	
				
			int odListID= oData.getOdListID();
			int cnt=0;

			for (OrderContentDTO oContentdata : oContentDatas) {
				if(oContentdata.getOdListID() == odListID) {
					datasTotal.add(oContentdata);
				}
				cnt++;
				System.out.println("형련[로그 oContentDatas 결과값 : " + oContentdata + "]");
				System.out.println("형련 [ 로그 data사이즈 : " + datasTotal.size() + "]");
				System.out.println("주문번호 하나 당 순환하는 횟수 1 : [ " +cnt+ "]" );
			}
			oDTO.setCnt(cnt);
			System.out.println("주문번호 하나 당 순환하는 횟수 2 : [ " +oDTO.getCnt()+ "]" );
		}
		request.setAttribute("oContentData", oContentDatas);
		request.setAttribute("datasTotal", datasTotal);
//		for (OrderListDTO oData : oDatas) {
//			int odListID = oData.getOdListID();
//			int cnt=0;
//			ArrayList<OrderContentDTO> matchingDatas = new ArrayList<>();
//			for (OrderContentDTO oContentData : datasTotal) {
//				
//				if (oContentData.getOdListID() == odListID) {
//					matchingDatas.add(oContentData);
//				}
//				cnt++;
//				System.out.println("형련 : matchingDataAction1 for문 내부: " + matchingDatas);
//			}
//			oData.setCnt(cnt);
//			//System.out.println("형련 : matchingDataAction2 for문 탈출: " + matchingDatas);
//			System.out.println("주문번호 하나 당 순환하는 횟수 : [ " +cnt+ "]" );
//		}
//		System.out.println("myOrderListAction 끝");
		return forward;
	}
}
