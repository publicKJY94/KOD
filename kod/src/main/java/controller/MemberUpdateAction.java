package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("집에좀 가자 ");
		
		 ActionForward forward = new ActionForward();
	      
	      MemberDTO mDTO = new MemberDTO();
	      MemberDAO mDAO = new MemberDAO();
	      //mDTO.setmemberID(Integer.parseInt(request.getParameter("memberID"));
	     // mDTO.setmemberID(request.getParameter("memberID"));
	      System.out.println(request.getParameter("memberName") + "test1111") ;
	      System.out.println(request.getParameter("memberPW") + "test2222");
	      System.out.println(request.getParameter("memberID") + " <<멤버아이디 <<<");
	      mDTO.setMemberName(request.getParameter("memberName"));
	      mDTO.setMemberPW(request.getParameter("memberPW"));
	      mDTO.setMemberID(request.getParameter("memberID"));
	      mDTO.setMemberEmail(request.getParameter("memberEmail"));
	      System.out.println("[로그 memberUpdateAction -> memberEmail]"+mDTO.getMemberEmail());
	      boolean flag = mDAO.update(mDTO);
	      System.out.println(flag + "flag <<<");
	      if(flag) {	
	         request.setAttribute("msg", "정보변경에 성공하였습니다! 로그인후 이용해주세여");
	         forward.setRedirect(false);
	         forward.setPath("logout.do");
	         return forward;
	      }
	 
	      forward.setRedirect(false);
	      forward.setPath("goback.do");
	     // request.setAttribute("status", "success");
	      //request.setAttribute("msg", "수정되었습니다");
	    //  request.setAttribute("redirect", "adminTitleManagementPage.do");

		
		return forward;
	}

}
