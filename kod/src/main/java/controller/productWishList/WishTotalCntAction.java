package controller.productWishList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.WishListDAO;
import model.dto.WishListDTO;

@WebServlet("/wishTotalCntAction")
public class WishTotalCntAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WishTotalCntAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("wishTotalCntAction들어옴");
		
		int wishTotalCnt = 0; 
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setSearchCondition("찜합계");
		wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		wishTotalCnt=wishListDTO.getWishTotalCnt();
		System.out.println("[로그] wishTotalCnt : "+wishTotalCnt);
		
		response.getWriter().write(String.valueOf(wishTotalCnt));
		
	}

}
