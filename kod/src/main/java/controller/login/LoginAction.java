package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	System.out.println("로그 로그인액션");
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		
		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));
		
		mDTO.setSearchCondition("로그인");
		mDTO=mDAO.selectOne(mDTO);
		
		
		
		if(mDTO != null) {
			HttpSession session=request.getSession();
			session.setAttribute("memberDTO",mDTO);
			request.setAttribute("msg", "로그인에 성공. ");
			
			
			forward.setPath("loginsuccess.do");
			forward.setRedirect(false);
			
			
		}else {
			request.setAttribute("msg", "로그인에 실패하였습니다. ");
			
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
			
		return forward;
	}

}
