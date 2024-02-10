package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 ActionForward forward = new ActionForward();
	      
	      MemberDTO mDTO = new MemberDTO();
	      MemberDAO mDAO = new MemberDAO();
	     
	      System.out.println(request.getParameter("memberName")+"[본승] 로그 이름") ;
	      System.out.println(request.getParameter("memberPW") + "[본승] 로그 비밀번호");
	      System.out.println(request.getParameter("memberID") + "[본승] 로그 아이디");
	      System.out.println(request.getParameter("memberPhNum") + "[본승] 로그 핸드폰번호");
	      
	      mDTO.setMemberName(request.getParameter("memberName"));
	      mDTO.setMemberPW(request.getParameter("memberPW"));
	      mDTO.setMemberID(request.getParameter("memberID"));
	      mDTO.setMemberPhNum(request.getParameter("memberPhNum"));
	      mDTO.setMemberEmail(request.getParameter("memberEmail"));
	      System.out.println("[본승] 로그 memberUpdateAction -> memberEmail "+mDTO.getMemberEmail());
	      System.out.println("[본승] 로그 memberUpdateAction -> memberPhNum "+mDTO.getMemberPhNum());
	     
	      
	      boolean flag = mDAO.update(mDTO);
	      System.out.println(flag + "flag <<<");
	      if(flag) {	
	         forward.setRedirect(false);
	         forward.setPath("memberUpdateLogout.do");
	         
	      }else {
	    	  forward.setRedirect(false);
		      forward.setPath("goback.do");
	      }
	     
	  
		return forward;
	}

}
