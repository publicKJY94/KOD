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

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");

		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		mDTO.setMemberID((String)request.getParameter("memberID"));
		mDTO.setSearchCondition("ID체크");
		mDTO=mDAO.selectOne(mDTO);

		AddressDAO aDAO = new AddressDAO();
		AddressDTO aDTO = new AddressDTO();

		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		aDTO.setMemberID(mDTO.getMemberID());
		System.out.println(aDTO);

		boolean flag = aDAO.insert(aDTO);

		if (flag) {
			request.setAttribute("msg","회원가입 성공!");
			forward.setPath("joinsuccess.do");
			forward.setRedirect(false);
		}

		else {
			request.setAttribute("msg", "회원가입에 실패하였습니다");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}

		return forward;
	}

}
