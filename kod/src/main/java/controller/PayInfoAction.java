package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		forward.setPath("payInfo.jsp");
		forward.setRedirect(false);
		
		
		CartDAO cartDAO = new CartDAO();
		//cartDTO.setCartProductCnt(Integer.parseInt(request.getParameter("purchaseCnt")));
		ArrayList<CartDTO> datas = new ArrayList<CartDTO>();
		HttpSession session =request.getSession();	
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		String[] selectedProducts = request.getParameterValues("selectedProducts");
		
		if (selectedProducts != null) {
		    for (String product : selectedProducts) {
		        System.out.println("체크박스 값: " + product);
		        CartDTO cartDTO = new CartDTO();
		        cartDTO.setMemberID(memberID);
		        cartDTO.setProductID(Integer.parseInt(product));
		        cartDTO = cartDAO.selectOne(cartDTO);
		        System.out.println(cartDTO);
		        datas.add(cartDTO);
		        System.out.println("선택된 장바구니 정보 : "+ datas);
		    }
		} else {
		    System.out.println("선택된 체크박스가 없습니다."); // 추후 alert창 띄우기
		}
		
		request.setAttribute("cartDTO", datas);
		System.out.println(datas);
		
		AddressDTO aDTO = new AddressDTO();
		AddressDAO aDAO = new AddressDAO();
		
		aDTO.setMemberID(memberID);
		aDTO.setSearchCondition("장바구니배송지");
		aDTO = aDAO.selectOne(aDTO);
		System.out.println("결제전 주소지 : "+aDTO);
		request.setAttribute("addressDTO", aDTO);
		
		return forward;
	}


}
