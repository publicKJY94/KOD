package controller.address;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AddressDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		forward.setPath("myPage.do"); 
		forward.setRedirect(true);
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		System.out.println("삭제 로그 1");
		String adrsIdStr = request.getParameter("adrsId");
		System.out.println(request.getParameter("adrsId"));
		
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
		System.out.println("삭제 로그 2");
		System.out.println("삭제 로그 3");
		System.out.println(aDTO);
		boolean flag=aDAO.delete(aDTO);
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}
		else {
			request.setAttribute("msg", "ㄴㄴㄴㄴ안됨");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		System.out.println(flag);
		return forward;
	}

}
