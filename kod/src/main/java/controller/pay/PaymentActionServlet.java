package controller.pay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CartDAO;
import model.dao.OrderContentDAO;
import model.dao.OrderListDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;

/**
 * Servlet implementation class PaymentActionServlet
 */
@WebServlet("/paymentActionServlet")
public class PaymentActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public PaymentActionServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("==============paymentServlet 시작==============");
		System.out.println(request.getParameter("productName"));
		System.out.println(request.getParameter("productID"));
		System.out.println(request.getParameter("purchaseCnt"));
		
		
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		//데이터 담기 
		HttpSession session =request.getSession();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		String memberID = memberDTO.getMemberID();
		
		oDTO.setMemberID(memberID);
		oDAO.insert(oDTO);
		
		
		oDTO = oDAO.selectOne(oDTO);
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		
		
		// 장바구니 결제
		CartDTO cDTO = new CartDTO();
		CartDAO cDAO = new CartDAO();
		
		cDAO.selectOne(cDTO);
		// 선택 구매된 상품의 개수만큼 추가
		
		oContentDTO.setOdListID(oDTO.getOdListID());
		System.out.println("[서블릿] 주문 상세 내역 상품 번호 : "+request.getParameter("productID"));
		oContentDTO.setProductID(Integer.parseInt(request.getParameter("productID"))); 
		oContentDTO.setOdContentCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		System.out.println("[서블릿] 주문 상세 내역 주문 개수 : "+request.getParameter("purchaseCnt"));
		System.out.println("[서블릿] 주문 상세 내역 : "+oContentDTO);
		oContentDAO.insert(oContentDTO);
		
		System.out.println("paymentServlet 끝");
		
		
//		PrintWriter out = response.getWriter();
//		out.print("사과");
	}
	
}
