package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AddressDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		forward.setPath("myPage.do"); 
		forward.setRedirect(false);
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		
		String adrsIdStr = request.getParameter("adrsId");
		
		if (adrsIdStr != null && !adrsIdStr.isEmpty()) {
		    try {
		        int adrsId = Integer.parseInt(adrsIdStr);
		        aDTO.setAdrsID(adrsId);
		        System.out.println(aDTO);
		    } catch (NumberFormatException e) {
		        e.printStackTrace(); 
		        System.out.println("addressDelete (이유 : PK가 없는거임)");
		    }
		} 
		else {
		}
		
		aDAO.delete(aDTO);
		boolean flag=aDAO.delete(aDTO);
		
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}

		else {
			request.setAttribute("msg", "회원가입에 실패하였습니다");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		
		
		
		
		return forward;
	}

}
