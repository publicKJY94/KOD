package controller.join;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.MemberDAO;
import model.dto.MemberDTO;

@WebServlet("/check.do")
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// MemberDAO 속성의 mDAO 객체 생성
		MemberDAO mDAO=new MemberDAO();
		// MemberDTO 속성의 mDTO 객체 생성
		MemberDTO mDTO=new MemberDTO();
		mDTO.setSearchCondition("ID중복검사");
		// check.js에서 받아온 memberID mDTO에 저장
		mDTO.setMemberID(request.getParameter("memberID"));
		// mDTO의 데이터(memberID,ID중복검사)값을 가지고 mDAO.selectOne 으로 이동 반환받은 결과값(data)를 mDTO변수에 저장
		mDTO=mDAO.selectOne(mDTO);
		
		// 논리형 변수 flag에 false저장
		boolean flag=false;
		
		// 만약 MemberDAO 에서 반환받은mDTO가 값이null 이면 flag변수에 true 저장 
		if(mDTO==null) {
			flag=true;
		}
		// 서블릿에서 클라이언트로 데이터를 출력하는 부분
		//response.getWriter()함수를 사용하여 클라이언트와의 통신을 위한 PrintWriter객체를 가져옵니다
		PrintWriter out=response.getWriter();
		// PrintWriter객체를 통해 flag변수의 값을 클라이언트로 출력합니다
		out.print(flag);
		System.out.println("[본승]로그 check.java ID중복체크 "+flag);
	}

}