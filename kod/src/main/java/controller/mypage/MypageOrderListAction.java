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
		
		System.out.println("[로그 : 조형련] myOrderListAction 시작");
		
		/*선언*/
		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		ArrayList<OrderContentDTO> datasTotal = new ArrayList<OrderContentDTO>();
		ArrayList<OrderContentDTO> oContentDatas = new ArrayList<OrderContentDTO> ();
		ReviewDAO reviewDAO=new ReviewDAO();
		
		/*[조형련] 요청에 대한 정보를 가져오기 위해서 세션 사용*/
		HttpSession session = request.getSession();
		mDTO.setMemberID(((MemberDTO) session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		/*[조형련] 로그인한 회원이 가지고 있는 주문내역 정보 확인*/
		oDTO.setMemberID(mDTO.getMemberID());
		ArrayList<OrderListDTO> oDatas = oDAO.selectAll(oDTO);
		/*[조형련] oDatas에 있는 데이터를 oDTO라는 이름으로 JSP로 전달*/
		request.setAttribute("oDTO", oDatas);
		
		/*[조형련] 주문번호에 있는 정보와 주문상세내역을 조회하여 주문번호가 같은 상품을 배열로 생성*/
		for (OrderListDTO oData : oDatas) {
			oContentDTO.setOdListID(oData.getOdListID());
			oContentDTO.setSearchCondition("결제내역");
			oContentDatas = oContentDAO.selectAll(oContentDTO);	
			/*[조형련] 주문번호를 odListID에 저장하고,각 주문번호안에 내역 수량을 확인하기 위해 변수 cnt 선언*/
			int odListID= oData.getOdListID();
			int cnt=0;
				
			/*[조형련] 주문상세 배열리스트에서 주문번호가 같은 데이터가 있는지 확인*/
			for (OrderContentDTO oContentdata : oContentDatas) {
				if(oContentdata.getOdListID() == odListID) {
				    /*[조형련] 해당 주문내역에 작성된 리뷰가 있었는지 확인*/
				    ReviewDTO reviewDTO=new ReviewDTO();
					reviewDTO.setSearchCondition("리뷰체크");
					reviewDTO.setMemberID(mDTO.getMemberID());
					reviewDTO.setOdContentID(oContentdata.getOdContentID()); 
					reviewDTO=reviewDAO.selectOne(reviewDTO);
					System.out.println("로그1: "+reviewDTO);
					//System.out.println("형련 [로그] mypageOrderListAction "+reviewDTO);
					/*[조형련] 리뷰정보가 존재하는 경우*/
					if (reviewDTO.getReviewID() !=0 ) {
						/*[조형련] 리뷰작성 버튼을 enabled로 설정*/
						oContentdata.setReviewButtonStatus("enabled");
						System.out.println("if문 안에서"+reviewDTO);
						//System.out.println("[형련] oContentdata.getReviewButtonStatus 상태 : "+ oContentdata.getReviewButtonStatus());
					} 
					/*[조형련] 리뷰정보가 존재하지 않는경우*/
					else {
						System.out.println("리뷰가 없으면"+reviewDTO);
						/*[조형련] 리뷰작성 버튼을 disabled로 설정*/
						oContentdata.setReviewButtonStatus("disabled");
					}
					//System.out.println("[형련] reivewDTO 상태 : "+reviewDTO);
					/*[조형련] 주문번호가 같고, 리뷰작성 여부가 포함된 데이터를 datasTotal 배열리스트에 담아줌*/
					datasTotal.add(oContentdata);
				}
				/*[조형련] 주문번호를 한번 돌아서 나갈 때마다 순환횟수 1씩 증가*/
				cnt++;
				//System.out.println("형련[로그 oContentDatas 결과값 : " + oContentdata + "]");
				//System.out.println("형련[로그 data사이즈 : " + datasTotal.size() + "]");
				//System.out.println("주문번호 하나 당 순환하는 횟수 1 : [ " +cnt+ "]" );
				//System.out.println("형련[로그] oContentDatas Reivew 상태체크 : " );
			}
			/*[조형련] 선언해둔 cnt에 각 주문번호가 몇개의 내역을 포함하고 있는지 담아서 전달*/
			oData.setCnt(cnt);
		  //System.out.println("주문번호 하나 당 순환하는 횟수 2 : [ " +oData.getCnt()+ "]" );
		}
	  //System.out.println("[형련] datasTotal 로그 :"  +datasTotal);
		/*[조형련] 가공한 정보들을 배열에 담아서 전달*/
		request.setAttribute("oContentData", oContentDatas);
		request.setAttribute("datasTotal", datasTotal);
		
		forward.setPath("myOrderList.jsp");
		forward.setRedirect(false);
		
		System.out.println("[로그 : 조형련] myOrderListAction 끝");
		return forward;
	}
}
