package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

@WebServlet("/IsWishedAction")
public class IsWishedAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public IsWishedAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		wishListDTO.setProductID(productID);
		wishListDTO.setSearchCondition("위시리스트추가삭제");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		
		System.out.println(memberID);
		System.out.println(productID);
		System.out.println("isWishedAction들어옴");
		
		boolean flag = false;
		if(wishListDTO!=null) {
			flag=false;
			wishListDTO.setMemberID(memberID);
			wishListDTO.setProductID(productID);
			wishListDAO.delete(wishListDTO);
			System.out.println("위시리스트 삭제성공");
		}
		else if(wishListDTO==null) {
			flag=true;
			wishListDTO = new WishListDTO();
			wishListDTO.setProductID(productID);
			wishListDTO.setMemberID(memberID);
			wishListDAO.insert(wishListDTO);
			System.out.println("위시리스트 추가성공");
		}

		
		System.out.println("위시리스트 추가or삭제 : "+flag);
		request.setAttribute("flag", flag);
		
		wishListDTO.setMemberID(memberID);
		System.out.println("memberID >> "+memberID);
		wishListDTO.setSearchCondition("찜수량");
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		int updatedWishListCnt = wishListDTO.getWishListCnt();
		System.out.println("updatedWishListCnt >> "+updatedWishListCnt);
		response.getWriter().write(String.valueOf(updatedWishListCnt));
	}
}
