package controller.address;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

public class AddressInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[로그 : 조형련] AddressInsertAction 시작");
		
		ActionForward forward = new ActionForward();
		/*[조형련] 요청에 대한 정보를 가져오기 위해서 세션 사용*/
		HttpSession session=request.getSession();
		session.getAttribute("memberDTO");
	  //System.out.println("로그 : 멤버아이디 in addressInsert"+session.getAttribute("memberDTO"));
		
		/*[조형련] 멤버 객체 선언 후 세션에서 가져온 정보에 있는 해당 멤버의 ID를 받아옴 */
		MemberDTO mDTO = new MemberDTO();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		
		AddressDAO aDAO = new AddressDAO();
		AddressDTO aDTO = new AddressDTO();
		
		/*[조형련] 주소지 추가에 필요한 정보를 파라미터 값으로 전달 */
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		aDTO.setMemberID(mDTO.getMemberID()); 
		/*[조형련] 해당 멤버회원에 배송지 추가완료 */
		boolean flag=aDAO.insert(aDTO);
		/*[조형련] 배송지 추가에 성공했다면 마이페이지로 이동*/
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}
		/*[조형련] 배송지 추가에 실패했다면 안내문구와 함께 이전 페이지로 이동*/
		else {
			request.setAttribute("msg", "주소지를 올바르게 입력해주세요");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		System.out.println("[로그 : 조형련] AddressInsertAction 끝");
		return forward;
	}

}
