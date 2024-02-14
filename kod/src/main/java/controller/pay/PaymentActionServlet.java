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
import model.dao.ProductDAO;
import model.dto.CartDTO;
import model.dto.MemberDTO;
import model.dto.OrderContentDTO;
import model.dto.OrderListDTO;
import model.dto.ProductDTO;

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
		System.out.println("[로그 : 박현민] PaymentActionServlet 시작");
		
		String preview = (String)request.getParameter("productIDs");	// 결제할 상품 번호 받아오기
		preview = preview.replace("[", "");
		preview = preview.replace("]", "");
		preview = preview.replace("\"", "");							// ajax를 통해 넘어온 데이터를 사용하기 위해 가공하는 과정
		String[] productIDs = preview.split(",");						// 들고온 데이터를 ,(쉼표)를 기준으로 나누기
		//System.out.println(Arrays.toString(productIDs));

		String cnt = (String)request.getParameter("purchaseCnts");		// 결제할 상품 개수 받아오기
		cnt = cnt.replace("[", "");
		cnt = cnt.replace("]", "");
		cnt = cnt.replace("\"", "");
		String[] cnts = cnt.split(",");
		//System.out.println(Arrays.toString(cnts));
		
		String payCk = (String)request.getParameter("payNows");			// 결제할 방법 받아오기 (바로 구매 = 1, 선택 구매 = 2)
		payCk = payCk.replace("[", "");
		payCk = payCk.replace("]", "");
		payCk = payCk.replace("\"", "");
		String[] payCks = payCk.split(",");
		//System.out.println("결제방식 : "+Arrays.toString(payCks));
		
		OrderListDTO oDTO = new OrderListDTO();
		OrderListDAO oDAO = new OrderListDAO();
		//데이터 담기 
		HttpSession session =request.getSession();
		
		MemberDTO memberDTO = new MemberDTO();
		memberDTO = (MemberDTO)session.getAttribute("memberDTO");		// 세션에서 사용자 정보 받아오기
		String memberID = memberDTO.getMemberID();
		
		oDTO.setMemberID(memberID);
		oDAO.insert(oDTO);												// 주문내역(ORDERLIST) 테이블에 저장
		OrderContentDTO oContentDTO = new OrderContentDTO();
		OrderContentDAO oContentDAO = new OrderContentDAO();
		
		
		if(payCks[0].equals("1")) { // 바로 결제
			
			System.out.println("[로그 : 박현민] 바로 구매 실행");
			oDTO.setMemberID(memberID);
			oDTO = oDAO.selectOne(oDTO);								// 위에서 저장되며 만들어진 ORDERLIST의 ID값을 받아오기 위해 selectOne 실행
			
			oContentDTO.setOdListID(oDTO.getOdListID());				// selectOne을 통해 들고온 ORDERLIST_ID를 oContentDTO에 저장
			oContentDTO.setProductID(Integer.parseInt(productIDs[0])); 
			oContentDTO.setOdContentCnt(Integer.parseInt(cnts[0]));
//			바로 구매 데이터 확인
//			System.out.println("[서블릿] 주문 상세 내역 상품 번호 : "+productIDs[0]);
//			System.out.println("[서블릿] 주문 상세 내역 주문 개수 : "+cnts[0]);
//			System.out.println("[서블릿] 주문 상세 내역 : "+oContentDTO);
			oContentDAO.insert(oContentDTO);							// 상품의 상세 정보를 ORDERCONTENT 테이블에 저장
			
			// 구매한 상품 재고 감소
			ProductDTO pDTO = new ProductDTO();
			ProductDAO pDAO = new ProductDAO();
			
			pDTO.setProductStock(oContentDTO.getOdContentCnt()); 		// 구매개수 담기
			pDTO.setProductID(oContentDTO.getProductID()); 				// 상품번호 담기
			//System.out.println("구매 후 재고 감소를 위한 pDTO : " + pDTO);
			pDAO.update(pDTO);											// 구매한 상품을 구매 개수만큼 재고 감소
			
			System.out.println("[로그 : 박현민] 바로 구매 끝");
		} else {
			for(int i=0;i<productIDs.length;i++) {						// 사용자가 선택한 상품의 개수만큼 반복문 실행
				System.out.println("[로그 : 박현민] 선택 구매 실행");
				oDTO.setMemberID(memberID);
				oDTO = oDAO.selectOne(oDTO);							// ORDERLIST의 ID값을 받아오기 위해 selectOne 실행
				
				oContentDTO.setOdListID(oDTO.getOdListID());			// selectOne을 통해 들고온 ORDERLIST_ID를 oContentDTO에 저장
				oContentDTO.setProductID(Integer.parseInt(productIDs[i])); 
				oContentDTO.setOdContentCnt(Integer.parseInt(cnts[i]));
//				System.out.println("[서블릿] 주문 상세 내역 상품 번호 : "+productIDs[i]);
//				System.out.println("[서블릿] 주문 상세 내역 주문 개수 : "+cnts[i]);
//				System.out.println("[서블릿] 주문 상세 내역 : "+oContentDTO);
				oContentDAO.insert(oContentDTO);						// 상품의 상세 정보를 ORDERCONTENT 테이블에 저장
				
				// 구매한 상품 재고 감소
				ProductDTO pDTO = new ProductDTO();
				ProductDAO pDAO = new ProductDAO();
				
				pDTO.setProductStock(oContentDTO.getOdContentCnt()); 	// 구매개수 담기
				pDTO.setProductID(oContentDTO.getProductID()); 			// 상품번호 담기
				//System.out.println("구매 후 재고 감소를 위한 pDTO : " + pDTO);
				pDAO.update(pDTO);										// 구매한 상품을 구매 개수만큼 재고 감소
				
				// 구매한 상품 장바구니에서 비우기
				CartDTO cDTO = new CartDTO();
				CartDAO cDAO = new CartDAO();
				cDTO.setProductID(oContentDTO.getProductID());
				cDTO.setMemberID(memberID);
				cDTO.setSearchCondition("개별상품삭제");
				cDAO.delete(cDTO);										// 구매한 상품을 장바구니에서 제거
				System.out.println("[로그 : 박현민] 선택 구매 끝");
			}
		}
		System.out.println("[로그 : 박현민] paymentActionServlet 끝");
	}
	
}
