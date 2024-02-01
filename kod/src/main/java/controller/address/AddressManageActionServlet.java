package controller.address;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.dao.AddressDAO;
import model.dao.MemberDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

@WebServlet("/addressManageActionServlet")
public class AddressManageActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddressManageActionServlet() {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("[형련] 로그 AddressManageActionServlet 시작");
		
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		System.out.println("[형련] 로그: myPageAction"+mDTO);
		
		AddressDTO aDTO=new AddressDTO();
		AddressDAO aDAO=new AddressDAO();
		
		aDTO.setMemberID(mDTO.getMemberID());
		
		ArrayList<AddressDTO> aDatas = aDAO.selectAll(aDTO);
		
		System.out.println(aDatas);

		Gson gson=new Gson();
		String aDatasString = gson.toJson(aDatas); // Gson객체 이용 데이터 변환 
		
		response.setCharacterEncoding("UTF-8");
		
		
		PrintWriter out=response.getWriter();
		out.println(aDatasString);
		
		System.out.println(aDatasString);
		System.out.println(" [형련] 로그 AddressManageActionServlet 끝");
		
	}

}
