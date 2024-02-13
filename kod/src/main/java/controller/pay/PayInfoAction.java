package controller.pay;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.AddressDAO;
import model.dao.CartDAO;
import model.dto.AddressDTO;
import model.dto.CartDTO;
import model.dto.MemberDTO;

public class PayInfoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		System.out.println("[로그 : 박현민] PayInfoAction 시작");
		CartDAO cartDAO = new CartDAO();
		//cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session =request.getSession();	
		
		if(session.getAttribute("memberDTO")== null) { // 로그인을 하지 않은 경우 로그인 페이지로 이동
			forward.setPath("loginPage.do");
			forward.setRedirect(true);
		}
		else { // 로그인했을 경우 결제 전 페이지로 이동
			String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
			String[] selectedProducts = request.getParameterValues("selectedProducts");		// 장바구니에서 선택한 상품번호 받아오기
			
			if (selectedProducts != null) {	
			    for (String product : selectedProducts) {
			        System.out.println("장바구니에서 선택한 상품번호 : " + product);
			        CartDTO cartDTO = new CartDTO();
			        cartDTO.setMemberID(memberID);
			        cartDTO.setProductID(Integer.parseInt(product));
			        cartDTO = cartDAO.selectOne(cartDTO);	// 장바구니에서 선택한 상품의 정보만 받아오기
			        //System.out.println(cartDTO);
			        datas.add(cartDTO);						// 선택한 상품의 정보를 리스트에 저장
			        System.out.println("선택된 장바구니 정보 : "+ datas);
			    }
			} else {
			    System.out.println("선택된 체크박스가 없습니다."); // 선택한 상품이 없는 경우
			}
			
			request.setAttribute("cartDTO", datas);		// 'cartDTO'라는 변수에 선택된 상품의 리스트 정보를 저장
			//System.out.println("cartDTO 변수에 담긴 상품 리스트 : "+datas);
			
			AddressDTO aDTO = new AddressDTO();
			AddressDAO aDAO = new AddressDAO();
			
			aDTO.setMemberID(memberID);
			aDTO.setSearchCondition("장바구니배송지");
			aDTO = aDAO.selectOne(aDTO); 				// 해당 사용자의 가장 최근에 생성된 주소지를 가져옴
			System.out.println("결제 전 주소지 정보확인 : "+aDTO);
			request.setAttribute("addressDTO", aDTO);	// 'addressDTO'라는 변수에 해당 사용자 주소지 정보를 저장
			
			forward.setPath("payInfo.jsp");				// payInfo.jsp로 이동
			forward.setRedirect(false);
		}
		
		return forward;
	}


}
