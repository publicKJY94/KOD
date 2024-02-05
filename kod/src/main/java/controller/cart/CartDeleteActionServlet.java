package controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.dao.MemberDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

@WebServlet("/cartDeleteActionServlet")
public class CartDeleteActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CartDeleteActionServlet() {
        super();  
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[형련] 로그 CartDeleteActionServlet 시작");
		
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		CartDTO cartDTO= new CartDTO();
		CartDAO cartDAO= new CartDAO();
		
		String productId = request.getParameter("productId");
		System.out.println("형련 [ 로그 ] deleteServlet productId [  : "+productId+ " ]");
		
		cartDTO.setProductID(Integer.parseInt(productId));
		
		response.setCharacterEncoding("UTF-8");
		
		cartDAO.delete(cartDTO);
		
		System.out.println("[형련] 로그 CartDeleteActionServlet 끝");
		
	}

}
