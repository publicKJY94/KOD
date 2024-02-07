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
		
		System.out.println("[로그 : 조형련] CartUpdateActionServlet 시작");
		response.setCharacterEncoding("UTF-8");
		/*선언*/
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();
		CartDTO cartDTO= new CartDTO();
		CartDAO cartDAO= new CartDAO();
		/*[조형련] 로그인한 회원 정보를 가져오기 위해서 세션 사용, 가져온 정보로 회원 정보 조회*/
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		/*[조형련] 파라미터로 받아온 productID(PK)값을 저장*/
		String productId = request.getParameter("productId");
		/*[조형련] 파라미터로 받아온 productCnt(상품 수량)을 저장*/
	    String productCnt = request.getParameter("productCnt");
	    
	    /*[조형련] 장바구니에 있는 상품정보에 productID(PK)값을 저장*/
	    cartDTO.setProductID(Integer.parseInt(productId));
	    /*[조형련] 장바구니에 있는 상품정보에 productCnt(수량)을 저장*/
	    cartDTO.setCartProductCnt(Integer.parseInt(productCnt));
		cartDTO.setMemberID(mDTO.getMemberID());
		/*[조형련] 받아온 정보로 장바구니에 있는 상품 수량을 변경*/
		cartDTO.setSearchCondition("장바구니수량변경");
		cartDAO.update(cartDTO);
		/*[조형련] 변경된 수량을 반환하기 위해 changedCnt에 저장*/
		int changedCnt=cartDTO.getCartProductCnt();
		/*[조형련] 페이지 요청에 응답을 보내기 위해 PrintWriter 객체를 활용 데이터를 반환 */
		PrintWriter out=response.getWriter();
		out.println(changedCnt);
		
	  //System.out.println("[형련] 로그 actionServlet cartDTO 결과 값 : " +changedCnt);
		System.out.println("[로그 : 조형련] CartUpdateActionServlet 끝");
		
	}

}
