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
		
		System.out.println();
		System.out.println("dd");
		
		
		HttpSession session = request.getSession();
		String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		
		int productID = Integer.parseInt(request.getParameter("productID"));
		
		
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setMemberID(memberID);
		wishListDTO.setProductID(productID);
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
			System.out.println("메롱");
			System.out.println("위시리스트 삭제성공");
		}
		else if(wishListDTO==null) {
			flag=true;
			wishListDTO = new WishListDTO();
			wishListDTO.setMemberID(memberID);
			wishListDTO.setProductID(productID);
			wishListDAO.insert(wishListDTO);
			System.out.println("위시리스트 추가성공");
		}
		
		System.out.println("위시리스트 추가or삭제 : "+flag);
		request.setAttribute("flag", flag);
	
	}

}
