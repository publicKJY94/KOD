package controller.productWishList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.WishListDAO;
import model.dto.WishListDTO;

@WebServlet("/wishTotalCntServlet") // 상품의 찜 합계수량 비동기반응 - 상품상세페이지
public class WishTotalCntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WishTotalCntServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[로그:정현진] wishTotalCntServlet들어옴");
		
		int wishTotalCnt = 0; 
		WishListDAO wishListDAO = new WishListDAO();
		WishListDTO wishListDTO = new WishListDTO();
		wishListDTO.setSearchCondition("상품찜합계");
		wishListDTO.setProductID(Integer.parseInt(request.getParameter("productID")));
		wishListDTO = wishListDAO.selectOne(wishListDTO);
		wishTotalCnt=wishListDTO.getWishTotalCnt(); // 상품찜합계
		System.out.println("[로그] wishTotalCnt : "+wishTotalCnt);
		
		response.getWriter().write(String.valueOf(wishTotalCnt));
		
	}

}



