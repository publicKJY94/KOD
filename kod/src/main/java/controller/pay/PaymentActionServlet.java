package controller.pay;

import java.io.IOException;
import java.util.Arrays;

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
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		System.out.println("\n==============paymentServlet 시작==============");
		System.out.println(request.getParameter("productName"));
		String preview = (String)request.getParameter("productIDs");
		preview = preview.replace("[", "");
		preview = preview.replace("]", "");
		preview = preview.replace("\"", "");
		String[] productIDs = preview.split(",");
		System.out.println(Arrays.toString(productIDs));
		System.out.println(productIDs[0]);
		System.out.println(productIDs.length);

		String cnt = (String)request.getParameter("purchaseCnts");
		cnt = cnt.replace("[", "");
		cnt = cnt.replace("]", "");
		cnt = cnt.replace("\"", "");
		String[] cnts = cnt.split(",");
		System.out.println(Arrays.toString(cnts));
		System.out.println(cnts[0]);
		System.out.println(cnts.length);
		
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		//데이터 담기 
		HttpSession session =request.getSession();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = (MemberDTO)session.getAttribute("memberDTO");
		String memberID = memberDTO.getMemberID();
		
		oDTO.setMemberID(memberID);
		oDAO.insert(oDTO);
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		
		for(int i=0;i<productIDs.length;i++) {
			oDTO.setMemberID(memberID);
			oDTO = oDAO.selectOne(oDTO);
			
			oContentDTO.setOdListID(oDTO.getOdListID());
			System.out.println("[서블릿] 주문 상세 내역 상품 번호 : "+productIDs[i]);
			oContentDTO.setProductID(Integer.parseInt(productIDs[i])); 
			oContentDTO.setOdContentCnt(Integer.parseInt(cnts[i]));
			System.out.println("[서블릿] 주문 상세 내역 주문 개수 : "+cnts[i]);
			System.out.println("[서블릿] 주문 상세 내역 : "+oContentDTO);
			oContentDAO.insert(oContentDTO);
			
			
			// 구매한 상품 재고 감소
			
			// 구매한 상품 장바구니에서 비우기
			CartDTO cDTO = new CartDTO();
			CartDAO cDAO = new CartDAO();
			cDTO.setProductID(oContentDTO.getProductID());
			cDTO.setMemberID(memberID);
			cDTO.setSearchCondition("개별상품삭제");
			cDAO.delete(cDTO);
			System.out.println("장바구니 삭제 완료");
		}
		
		
		
		
		System.out.println("paymentServlet 끝");
	}
	
}
