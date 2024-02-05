package controller.productWishList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.dao.WishListDAO;
import model.dto.MemberDTO;
import model.dto.WishListDTO;

@WebServlet("/removeSoldOutProducts")
public class RemoveSoldOutProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveSoldOutProducts() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[로그 : 정현진] RemoveSoldOutProducts 들어옴");
		
		// JSON 데이터를 읽어옴
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        // JSON 데이터를 파싱
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(sb.toString());

        // productIDs를 활용한 품절상품 삭제 로직
        JsonNode productIDsNode = jsonNode.get("productIDs");
        if (productIDsNode != null && productIDsNode.isArray()) {
            List<String> productIDs = objectMapper.readValue(productIDsNode.traverse(), objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));

            HttpSession session = request.getSession();
            String memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();

            for (String productIDStr : productIDs) {
                // 품절 상품 삭제 로직
            	int productID = Integer.parseInt(productIDStr);
            	WishListDAO wishListDAO = new WishListDAO();
            	WishListDTO wishListDTO = new WishListDTO();
            	wishListDTO.setMemberID(memberID);
            	wishListDTO.setProductID(productID);
            	boolean flag = wishListDAO.delete(wishListDTO);
            	if(flag) {
            		System.out.println("[로그 : 정현진] "+productID+"번 품절상품 삭제 성공");
            	}
            	else {
            		System.out.println("[로그 : 정현진] "+productID+"번 품절상품 삭제 실패");
            	}
            }
        }

        // 응답을 클라이언트에게 전송
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // JSON 응답 생성
        String jsonResponse = "{\"success\": true}";
        out.print(jsonResponse);
        
        out.flush();
    }

}
