package controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	System.out.println("로그 로그인액션");
		// 새로운 ActionForward 객체생성
		ActionForward forward = new ActionForward();
		
		// 요청 문자 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// MemberDAO,MemberDTO 객체 생성
		MemberDAO mDAO = new MemberDAO();
		MemberDTO mDTO = new MemberDTO();
		
		// 로그인 페이지(login.jsp)에서 입력받은 아이디,비밀번호를 mDTO객체에 저장 
		// 검색조건(SearchCondition)"로그인" mDTO객체에 저장
		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));		
		mDTO.setSearchCondition("로그인");
		
		// 저장된 아이디, 비밀번호, 검색조건을 가지고 mDAO.selectOne 메서드 실행
		// 결과값 mDTO변수에 저장
		mDTO=mDAO.selectOne(mDTO);
		
		
		// 만약 mDTO값이 있다면 
		// session 객체를 생성
		// session에 "memberDTO"속성에 mDTO변수를 저장
		if(mDTO != null) {
			HttpSession session=request.getSession();
			session.setAttribute("memberDTO",mDTO);
			// msg 값에 안내문구를 설정
			request.setAttribute("msg", "로그인에 성공. ");
			
			// 로그인 성공 시 loginsuccess.do로 이동
			forward.setPath("loginsuccess.do");
			forward.setRedirect(false);
			
			
		}else {// 아니라면
			// msg 값에 안내문구를 설정
			// 페이지 이동
			
			// forward객체에 setPath(goback.do)를 저장 후 반환
			request.setAttribute("msg", "로그인에 실패하였습니다. ");
			
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
			
		return forward;
	}

}
