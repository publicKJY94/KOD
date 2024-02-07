package controller.address;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dto.AddressDTO;

public class AddressUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[로그 : 조형련] AddressUpdateAction 시작");
		/*선언*/
		ActionForward forward = new ActionForward();
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();
		/*[조형련] 파라미터로 받아온 adrsId의 값을 adrsIdStr에 저장*/
		String adrsIdStr = request.getParameter("adrsId");
	  //System.out.println(request.getParameter("adrsId"));
		
		/*[조형련] try-catch 구문을 이용해서 해당 변수에 들어있는 값이 존재하는 경우*/
		if (adrsIdStr != null && !adrsIdStr.isEmpty()) {
		    try {
		    	/*[조형련] 정수형타입 adrsId에 문자열로 받아온 값을 형변환 하여 해당 값을 PK에 저장*/
		        int adrsId = Integer.parseInt(adrsIdStr);
		        aDTO.setAdrsID(adrsId);
		        System.out.println(aDTO);
		    } catch (NumberFormatException e) {
		        e.printStackTrace(); 
		      //System.out.println("[로그 : 조형련] PK부재로 업데이트 실패");
		    }
		} 
		else {
		}
		/*[조형련] 받아온 PK에 페이지에서 입력받은 정보로 업데이트*/
		aDTO.setAdrsName(request.getParameter("adrsName")); // 주소지 이름
		aDTO.setAdrsStreet(request.getParameter("adrsStreet")); // 도로명 주소
		aDTO.setAdrsLotNum(request.getParameter("adrsLotNum")); // 지번 주소
		aDTO.setAdrsDetail(request.getParameter("adrsDetail")); // 상세 주소
		aDTO.setAdrsZipcode(request.getParameter("adrsZipcode")); // 우편 번호
		
		boolean flag=aDAO.update(aDTO);
	  //System.out.println(flag);
		
		/*[조형련] 수정에 성공했다면 마이페이지로 이동*/
		if (flag) {
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}
		/*[조형련] 배송지 수정에 실패했다면 안내문구와 함께 이전 페이지로 이동*/
		else {
			request.setAttribute("msg", "주소지를 올바르게 입력해주세요");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		System.out.println("[로그 : 조형련] AddressUpdateAction 끝");
		return forward;
	}

}
