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

	private HandlerMapping handler; // [정현진]외부에서 객체를 생성하지 못하게 막음

	public FrontController() {
		super();
		handler = new HandlerMapping(); // 생성자가 호출되면 객체에 핸들러맵핑 생성자 호출
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
		    // [정현진]404 에러 페이지로 리다이렉트 처리 해줘
			System.out.println("액션객체 is null"); // 개발환경 콘솔에 출력됨
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "[로그 : 정현진] 액션객체 is null"); // 뷰페이지에 출력됨
			return;
		}
		
		ActionForward forward = action.execute(request, response);
		// [정현진]action에는 클라이언트에서 요청한 경로와 리다이렉트 값을 가지고 있음
		
		
		if (forward.isRedirect()) { // [정현진]리다이렉트 값이 true일 경우 경로만 전달
			System.out.println("[로그 : 정현진] forward.isRedirect() : "+forward.isRedirect());
			System.out.println("[로그 : 정현진] forward.getPath() : "+forward.getPath());
			response.sendRedirect(forward.getPath());
		} 
		else { // [정현진]리다이렉트 값이 false일 경우 경로와 데이터를 함께 전달
			System.out.println("[로그 : 정현진] forward.isRedirect() : "+forward.isRedirect());
			System.out.println("[로그 : 정현진] forward.getPath() : "+forward.getPath());
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
		}
	}

}
