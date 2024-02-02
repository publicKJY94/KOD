package controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CartDAO;
import model.dto.CartDTO;

@WebServlet("/CartUpdateActionServlet")
public class CartUpdateActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartUpdateActionServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[형련] 로그 CartUpdateActionServlet 시작");
		
		CartDTO cartDTO= new CartDTO();
		CartDAO cartDAO= new CartDAO();
		
		
	}

}
