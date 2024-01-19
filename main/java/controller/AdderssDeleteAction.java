package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AdderssDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		forward.setPath("address.do"); // 정보변경이 완료되었습니다
		forward.setRedirect(false);
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		
		aDTO.setAdrsID(1);
		
		aDAO.delete(aDTO);
		
		
		
		return forward;
	}

}
