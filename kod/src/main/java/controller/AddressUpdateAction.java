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
		forward.setPath("myPage.do"); // 정보변경이 완료되었습니다
		forward.setRedirect(true);
		
		System.out.println("업데이트 들어옴 ");
		
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		
		String adrsIdStr = request.getParameter("adrsId");
		System.out.println(request.getParameter("adrsId"));
		
		if (adrsIdStr != null && !adrsIdStr.isEmpty()) {
		    try {
		        int adrsId = Integer.parseInt(adrsIdStr);
		        aDTO.setAdrsID(adrsId);
		        System.out.println(aDTO);
		    } catch (NumberFormatException e) {
		        e.printStackTrace(); 
		        System.out.println("addressUpdate (이유 : PK가 없는거임)");
		    }
		} 
		else {
		}
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		
		boolean flag=aDAO.update(aDTO);
		System.out.println(flag);
		
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}

		else {
			request.setAttribute("msg", "주소지를 올바르게 입력해주세요");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		
		System.out.println(aDTO);
		
		return forward;
	}

}
