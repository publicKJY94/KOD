package controller.join;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class JoinMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 새로운 ActionForward 객체생성	
		ActionForward forward = new ActionForward();
		
		// 요청 문자 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 회원가입페이지(join.jsp)에서 입력받은 년(year),월(month),일(day)값을 받아와 
		// 문자형 변수 년(year),월(month),일(day)변수생성후 저장
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		
		// 문자열 타입 변수 memberBirth생성후 년(year),월(month),일(day)값 저장
		String memberBirth = year + month + day;

		
		// 회원가입페이지(join.jsp)에서 입력받은 이메일아이디(memberEmail1)와 도메인(memberEmail2)
		// 문자열 타입 변수 이메일아이디(memberEmail1)와 도메인(memberEmail2)생성후 저장
		 String memberEmail1 = request.getParameter("memberEmail1");  
		 String memberEmail2 = request.getParameter("memberEmail2");
		
		// 문자열 타입 변수 memberEmail 생성후 생성한 변수에 memberEmail1+"@"+memberEmail2값 저장
		 String memberEmail = memberEmail1+"@"+memberEmail2;
		 
		 
		// 회원가입페이지(join.jsp)에서 입력받은 폰번호 1,2,3 을
		// 문자열 타입 변수 PhNum1,PhNum2,PhNum3 생성후 회원가입 페이지에서 받아온값 저장
		 String PhNum1 = request.getParameter("PhNum1");
		 String PhNum2 = request.getParameter("PhNum2");
		 String PhNum3 = request.getParameter("PhNum3");
		 
		// 문자열 타입 변수 memberPhNum생성후 PhNum1+PhNum2+PhNum3 값은 저장
		 String memberPhNum = PhNum1+PhNum2+PhNum3;
		 

		System.out.println("[로그]" + memberBirth);
		System.out.println("[로그 이메일]" + memberEmail);

		
		// MemberDTO속성 mDTO객체 생성
		// MemberDAO속성 mDAO객체 생성
		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();

		// 회원가입페이지(join.jsp)에서 입력받은 memberID를 객체 mDTO.setMemberID에 저장
		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));
		mDTO.setMemberName(request.getParameter("memberName"));
		mDTO.setMemberPhNum(memberPhNum);
		mDTO.setMemberEmail(memberEmail);
		mDTO.setMemberGender(request.getParameter("memberGender"));
		mDTO.setMemberBirth(memberBirth);
		System.out.println(mDTO.getMemberID());
		
		// mDAO.insert메서드 실행 인자값으로(mDTO) 가지고감
		// 실행결과를 논리형 변수 flag에 저장 
		boolean flag = mDAO.insert(mDTO);

		// 만약 논리형변수 flag가 true면
		if (flag) {
			forward.setPath("joinAddress.do");
			forward.setRedirect(false);
		} else { // 아니라면
			 // msg 값에 안내문구를 설정
			request.setAttribute("msg","회원가입에 실패하였습니다");

			forward.setPath("goback.do");
			forward.setRedirect(false);
		}

		return forward;
	}

}
