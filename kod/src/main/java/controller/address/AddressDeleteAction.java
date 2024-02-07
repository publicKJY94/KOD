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

		System.out.println("[로그 : 조형련] AddressDeleteAction 시작");
		
		/* 선언 */
		ActionForward forward = new ActionForward();
		AddressDAO aDAO=new AddressDAO();
		AddressDTO aDTO=new AddressDTO();

		/*[조형련] 이전 페이지에서 Address 테이블의 PK인 adrsId 값을 받아옴*/
		String adrsIdStr = request.getParameter("adrsId");

		/*[조형련] 받아온 값이 null이 아니거나, 공백이아니라면 */
		if (adrsIdStr != null && !adrsIdStr.isEmpty()) {
			try {		/*[조형련] try-catch를 이용한 ID값 검증*/
				int adrsId = Integer.parseInt(adrsIdStr); /*[조형련] 문자열 타입으로 저장한 PK값을 정수형으로 변환 */
				aDTO.setAdrsID(adrsId); /*[조형련] 해당 객체에 받아온 정보를 저장*/
				//		        System.out.println(aDTO);
			} catch (NumberFormatException e) {
				e.printStackTrace(); 
				//		        System.out.println("addressDelete tryCatch 이유 : PK에러)");
			}
		} 
		else {
		}
		boolean flag=aDAO.delete(aDTO); 
		/*[조형련] 삭제에 성공했다면, 메인 페이지로 이동*/
		if (flag) { 
			forward.setPath("myPage.do");
			forward.setRedirect(true);
		}
		/*[조형련] 삭제에 실패했다면, 이전 페이지로 이동*/
		else { 
			request.setAttribute("msg", "삭제에 실패했습니다.");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		//		System.out.println(flag);
		
		System.out.println("[로그 : 조형련] AddressDeleteAction 끝");

		return forward;
	}

}
