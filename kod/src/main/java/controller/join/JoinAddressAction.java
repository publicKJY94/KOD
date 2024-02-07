package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dao.MemberDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

public class JoinAddressAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*선언*/
		ActionForward forward = new ActionForward();
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		AddressDAO aDAO = new AddressDAO();
		AddressDTO aDTO = new AddressDTO();
		/*[조형련] 로그인한 회원 정보를 가져오기 위해서 세션 사용, 가져온 정보로 회원 정보 조회*/
		mDTO.setMemberID((String)request.getParameter("memberID"));
		mDTO.setSearchCondition("ID체크");
		mDTO=mDAO.selectOne(mDTO);
		/*[조형련] 회원가입 때 입력한 정보를 파라미터로 해당 변수에 저장*/
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		aDTO.setMemberID(mDTO.getMemberID());
	  //System.out.println(aDTO);
		boolean flag = aDAO.insert(aDTO);
		
		/*[조형련] 정보가 정상적으로 입력된 경우, 안내와 함께 로그인 성공*/
		if (flag) {
			request.setAttribute("msg","회원가입 성공!");
			forward.setPath("alert.do");
			forward.setRedirect(false);
		}
		/*[조형련] 입력된 정보가 정상적으로 저장되지 않는 경우, 안내와 함께 다시 회원가입 페이지로 이동*/
		else {
			request.setAttribute("msg", "회원가입에 실패하였습니다");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		return forward;
	}

}
