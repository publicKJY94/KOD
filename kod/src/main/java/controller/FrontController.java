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
		System.out.println("[로그:정현진] 프론트컨트롤러 들어옴");

		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String commend = uri.substring(cp.length());
//		System.out.println("[로그:정현진] uri : "+uri); // => /kod/main.do
//		System.out.println("[로그:정현진] cp : "+cp); // => /kod
//		System.out.println("[로그:정현진] commend : "+commend); // => /main.do
		
		Action action = handler.getAction(commend);
		if(action==null) {
		    // 404 에러 페이지로 리다이렉트 처리 해줘
			System.out.println("액션객체 is null"); // 개발환경 콘솔에 출력됨
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "[로그 : 정현진] 액션객체 is null"); // 뷰페이지에 출력됨
			return;
		}
		
		ActionForward forward = action.execute(request, response);
		
		
		if (forward.isRedirect()) {
			System.out.println("[로그 : 정현진] forward.isRedirect() : "+forward.isRedirect());
			System.out.println("[로그 : 정현진] forward.getPath() : "+forward.getPath());
			response.sendRedirect(forward.getPath());
		} 
		else {
			System.out.println("[로그 : 정현진] forward.isRedirect() : "+forward.isRedirect());
			System.out.println("[로그 : 정현진] forward.getPath() : "+forward.getPath());
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

}
