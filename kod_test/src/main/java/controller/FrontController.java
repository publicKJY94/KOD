package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}
	public void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String cp = request.getContextPath();
		String action = uri.substring(cp.length());
		ActionForward forward = null;
		if(action.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
		}
		else if(action.equals("/store.do")) {
			forward = new ProductAction().execute(request, response);
		}
		else if(action.equals("/productDetail.do")) {
			forward = new ProductDetailAction().execute(request, response);
		}
		else if(action.equals("/mapPage.do")) {
			forward = new MapPageAction().execute(request, response);
		}
		else if(action.equals("/joinPage.do")) {
			forward = new JoinPageAction().execute(request, response);
		}
		else if(action.equals("/loginPage.do")) {
			forward = new LoginPageAction().execute(request, response);
		}
		else if(action.equals("/login.do")) {
			forward = new LoginAction().execute(request, response);
		}
		else if(action.equals("/logout.do")) {
			forward = new LogoutAction().execute(request, response);
		}
		else if(action.equals("/myPage.do")) {
			forward = new myPageAction().execute(request, response);
		}
		else if(action.equals("/address.do")) {
			forward = new AddressAction().execute(request, response);
		}else if(action.equals("/wishList.do")) {
			System.out.println("들어옴1");
			forward= new WishListAction().execute(request, response);
		}else if(action.equals("/alert.do")) {
			forward= new AlertAction().execute(request, response);
			
		}else if(action.equals("/payInfoPage.do")) {
			System.out.println("[로그]");
			System.out.println(request.getParameter("productID"));
			forward = new PayInfoPage().execute(request, response);
		}
		else if(action.equals("/paymentPage.do")) {
			System.out.println("[로그]");
			System.out.println(request.getParameter("productID"));
			forward = new PaymentPageAction().execute(request, response);
		}
		else if(action.equals("/orderList.do")) {
			System.out.println("[로그2]");
			System.out.println("orderList pid : "+request.getParameter("productID"));
			forward = new OrderListAction().execute(request, response);
		}
		
		if(forward == null) {
			// 에러 상황
		}
		
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath());
		}
		else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response);
			// pageContext.forward(forward.getPath());
		}
	}

}
