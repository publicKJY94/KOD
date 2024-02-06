package controller.cart;

import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet("/cartUpdateActionServlet")
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
		
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		CartDTO cartDTO= new CartDTO();
		CartDAO cartDAO= new CartDAO();
		
		String productId = request.getParameter("productId");
	    String productCnt = request.getParameter("productCnt");
	 
	    cartDTO.setProductID(Integer.parseInt(productId));
	    cartDTO.setCartProductCnt(Integer.parseInt(productCnt));
		cartDTO.setMemberID(mDTO.getMemberID());
		
		response.setCharacterEncoding("UTF-8");
		cartDTO.setSearchCondition("장바구니수량변경");
		cartDAO.update(cartDTO);
		
		int changedCnt=cartDTO.getCartProductCnt();
		

		PrintWriter out=response.getWriter();
		out.println(changedCnt);
		
		System.out.println("[형련] 로그 actionServlet cartDTO 결과 값 : " +changedCnt);
		

		System.out.println("[형련] 로그 CartUpdateActionServlet 끝");
		
	}

}
