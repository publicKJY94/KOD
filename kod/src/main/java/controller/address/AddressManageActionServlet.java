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
		
		System.out.println("[로그 : 조형련] AddressManageActionServlet 시작");
		response.setCharacterEncoding("UTF-8");
		
		/*선언*/
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		AddressDTO aDTO=new AddressDTO();
		AddressDAO aDAO=new AddressDAO();
		
		/*[조형련] 요청에 대한 정보를 가져오기 세션 사용*/
		HttpSession session=request.getSession();
		/*[조형련] 세션에 저장된 멤버 정보를 이용해서 SelectOne 수행*/
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
	  //System.out.println("[형련] 로그: myPageAction"+mDTO);
	
		aDTO.setMemberID(mDTO.getMemberID());
		/*[조형련] 해당 회원이 들고있는 모든 배송지의 정보를 aDatas에 저장*/
		ArrayList<AddressDTO> aDatas = aDAO.selectAll(aDTO);
		
      //System.out.println(aDatas);
		
		/*[조형련] Gson객체를 이용해서 배열리스트로 받아온 값을 Json타입의 문자열로 변환*/
		Gson gson=new Gson();
		String aDatasString = gson.toJson(aDatas); 
		/*[조형련] 페이지 요청에 응답을 보내기 위해 PrintWriter 객체를 활용 변환한 데이터를 반환 */
		PrintWriter out=response.getWriter();
		out.println(aDatasString);
		
	  //System.out.println(aDatasString);
		System.out.println("[로그 : 조형련] AddressManageActionServlet 끝");
		
	}

}
