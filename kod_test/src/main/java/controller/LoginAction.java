package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		
		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));
		
		mDTO=mDAO.selectOne(mDTO);
		System.out.println(mDTO);
		
		if(mDTO != null) {

			HttpSession session=request.getSession();
			session.setAttribute("member",mDTO);
			
			forward.setPath("main.do");
			forward.setRedirect(true);
			
			
		}else {
			request.setAttribute("msg", "로그인에 실패하였습니다. ");
			
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
			
		return forward;
	}

}
