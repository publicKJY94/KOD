package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("alert.do");
		forward.setRedirect(false); 
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session=request.getSession();
		session.removeAttribute("memberDTO");
		request.setAttribute("msg", "로그아웃 성공!");
		
		return forward;
		
	}
}
