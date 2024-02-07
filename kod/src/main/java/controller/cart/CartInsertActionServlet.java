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
		
		System.out.println("[로그 : 조형련] CartInsertActionServlet 시작");
		/*선언*/
		CartDTO cartDTO = new CartDTO();
		CartDAO cartDAO = new CartDAO();

		/*[조형련] 로그인한 회원 정보를 가져오기 위해서 세션 사용, 가져온 정보로 회원 정보 조회*/
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		/*[조형련] 장바구니에 있는 상품정보에 파라미터로 받아온 productID(PK)값을 저장*/
		cartDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		/*[조형련] 장바구니에 있는 상품정보에 파라미터로 받아온 수량을 저장*/
		cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		
		/*[조형련] 해당 회원이 가지고 있는 정보로 장바구니 조회 */
		cartDTO.setMemberID(memberID);
		CartDTO cartData = cartDAO.selectOne(cartDTO);
		
		/*[조형련] 장바구니에 조회한 상품이 존재하는 경우*/
		if(cartData!=null){
      //System.out.println("[조형련] : 장바구니에 동일 상품 존재함");
			/*[조형련] 동일한 상품이 존재하므로, 해당 장바구니에 저장된 수량만 변경 */
			cartDTO.setSearchCondition("장바구니같은상품");
			cartDAO.update(cartDTO);
		/*[조형련] 장바구니에 상품이 존재하지 않는 경우*/
		}else {
	  //System.out.println("[조형련] : 장바구니에 동일 상품 존재하지 않음");
			/*[조형련] 장바구니에 동일한 상품이 없으므로, 해당 상품 추가 */
			cartDAO.insert(cartDTO);
		}
		/*[조형련] 페이지 요청에 응답을 보내기 위해 PrintWriter 객체를 활용 데이터를 반환 */
		PrintWriter out=response.getWriter();
		out.println("1");
		
		System.out.println("[로그 : 조형련] CartInsertActionServlet 끝");
	}
}
