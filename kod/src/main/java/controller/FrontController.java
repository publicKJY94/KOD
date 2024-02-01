package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private HandlerMapping handler;

	public FrontController() {
		super();
		handler = new HandlerMapping();
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doAction(request, response);
	}

	public void doAction(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String commend = uri.substring(cp.length());
		System.out.println("FC : "+commend);
		
		Action action = handler.getAction(commend);
		if(action==null) {
		    // 404 에러 페이지로 리다이렉트 처리 해줘
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "액션객체가 null입니다.");
			return;
		}
		
		ActionForward forward = action.execute(request, response);
//		ActionForward forward = main.do.execute(request, response);
		
		
		if (forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		} 
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

}
