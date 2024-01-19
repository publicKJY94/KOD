package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AddressInsertAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		forward.setPath("address.do"); // 정보변경이 완료되었습니다 . 메시지 출력
		forward.setRedirect(false); //반환할 데이터가 있음
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		aDTO.setMemberID("1");
	//	HttpSession session=request.getSession();
	//	aDTO.setMemberID((String)session.getAttribute("memberId")); // 회원ID - 누구의 배송목록인지 조회하기 위한 용도
		
		aDAO.insert(aDTO);
		
		return forward;
	}
	
}
