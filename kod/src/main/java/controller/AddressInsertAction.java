package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AddressDAO;
import model.dao.MemberDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

public class AddressInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		session.getAttribute("memberDTO");
		System.out.println("로그 : 멤버아이디"+session.getAttribute("memberDTO"));
		
		MemberDTO mDTO = new MemberDTO();
	
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		
		System.out.println("로그1 주소추가"+mDTO);
		
		AddressDAO aDAO = new AddressDAO();
		AddressDTO aDTO = new AddressDTO();

		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		aDTO.setMemberID(mDTO.getMemberID());
		
		boolean flag=aDAO.insert(aDTO);
		
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}

		else {
			request.setAttribute("msg", "주소지를 올바르게 입력해주세요");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
