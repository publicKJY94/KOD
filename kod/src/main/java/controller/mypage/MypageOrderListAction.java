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
import model.dao.ReviewDAO;
import model.dto.MemberDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;
import model.dto.ReviewDTO;

public class MypageOrderListAction implements Action {
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ActionForward forward = new ActionForward();
		forward.setPath("myOrderList.jsp");
		forward.setRedirect(false);

		System.out.println("[형련] myOrderListAction 진입");

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
		//System.out.println("형련[로그 oDatas 결과값 : " + oDatas + "]");
		request.setAttribute("oDTO", oDatas);

		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		ArrayList<OrderContentDTO> datasTotal = new ArrayList<OrderContentDTO>();
		ArrayList<OrderContentDTO> oContentDatas = new ArrayList<OrderContentDTO> ();
		
		ReviewDAO reviewDAO=new ReviewDAO();
	
		for (OrderListDTO oData : oDatas) {
			oContentDTO.setOdListID(oData.getOdListID());
			oContentDTO.setSearchCondition("결제내역");
			oContentDatas = oContentDAO.selectAll(oContentDTO);	
				
			int odListID= oData.getOdListID();
			int cnt=0;

			for (OrderContentDTO oContentdata : oContentDatas) {
				if(oContentdata.getOdListID() == odListID) {
					oContentdata.setMemberID(mDTO.getMemberID());
					oContentdata.setProductID(oContentdata.getProductID());
					
					ReviewDTO reviewDTO=new ReviewDTO();
					
					reviewDTO.setSearchCondition("리뷰체크");
					reviewDTO.setMemberID(mDTO.getMemberID());
					reviewDTO.setOdContentID(oContentdata.getOdContentID());
					reviewDTO=reviewDAO.selectOne(reviewDTO);
					
					System.out.println("형련 [로그] mypageOrderListAction "+reviewDTO);
					
					 if (reviewDTO != null) {
			              oContentdata.setReviewButtonStatus("enabled");
			              System.out.println("[형련] oContentdata.getReviewButtonStatus 상태 : "+ oContentdata.getReviewButtonStatus());
			            } 
					 	else {
			              oContentdata.setReviewButtonStatus("disabled");
			            }
					 System.out.println("[형련] reivewDTO 상태 : "+reviewDTO);
					 	  datasTotal.add(oContentdata);
				}
				cnt++;
				//System.out.println("형련[로그 oContentDatas 결과값 : " + oContentdata + "]");
				//System.out.println("형련 [ 로그 data사이즈 : " + datasTotal.size() + "]");
				//System.out.println("주문번호 하나 당 순환하는 횟수 1 : [ " +cnt+ "]" );
				System.out.println(" 형련 [로그] oContentDatas Reivew 상태체크 : " );
			}
			oDTO.setCnt(cnt);
			System.out.println("주문번호 하나 당 순환하는 횟수 2 : [ " +oDTO.getCnt()+ "]" );
		}
		System.out.println("[형련] datasTotal 로그 :"  +datasTotal);
		request.setAttribute("oContentData", oContentDatas);
		request.setAttribute("datasTotal", datasTotal);
		return forward;
	}
}
