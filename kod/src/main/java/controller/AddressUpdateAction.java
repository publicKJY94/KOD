package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AddressUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		forward.setPath("address.do"); // 정보변경이 완료되었습니다
		forward.setRedirect(false);
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		
		aDTO.setAdrsID(1); // 사용자가 입력한 인풋안에 저장된 값
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		
		aDAO.update(aDTO);
		
		return forward;
	}

}
