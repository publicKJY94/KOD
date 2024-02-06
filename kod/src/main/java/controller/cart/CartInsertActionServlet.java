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
import model.dto.CartDTO;
import model.dto.MemberDTO;

@WebServlet("/cartInsertActionServlet")
public class CartInsertActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public CartInsertActionServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("====================================================");
		System.out.println("[형련] 로그 cartInsertActionServlet 시작");
		
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();
		
		cartDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		cartDTO.setMemberID(memberID);
		
		CartDTO cartData = cartDAO.selectOne(cartDTO);
		
		if(cartData!=null){
			System.out.println("장바구니에 같은 상품 있음");
			cartDTO.setSearchCondition("장바구니같은상품");
			cartDAO.update(cartDTO);
		}else {
			System.out.println("장바구니에 같은 상품 없음");
			cartDAO.insert(cartDTO);
		}
		
		PrintWriter out=response.getWriter();
		out.println("1");
	
		System.out.println("[형련] 로그 cartInsertActionServlet 끝");
		System.out.println("====================================================");
	}
}
