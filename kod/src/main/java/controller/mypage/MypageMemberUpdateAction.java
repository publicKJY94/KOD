package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MypageMemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 새로운 ActionForward 객체생성
		ActionForward forward = new ActionForward();
		
		// 요청 문자 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		  
		// MemberDAO,MemberDTO 객체 생성
		  MemberDTO mDTO=new MemberDTO(); 
		  MemberDAO mDAO=new MemberDAO();
		  
		// session 객체를 생성
		  HttpSession session=request.getSession();
	
		// 검색조건(SearchCondition)"로그인"설정  mDTO객체에 저장
		  mDTO.setSearchCondition("로그인");
		  
		// 현재 session(memberDTO)에 저장되있는 아이디(.getMemberID)
		// 본인 인증페이지에서 입력받은 비밀번호(memberPW)를 mDTO객체에 담아
		// mDAO.selectOne 메서드실행
		// 실행후 반환값 mDTO에 저장
		  mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		  mDTO.setMemberPW(request.getParameter("memberPW"));
				  
		  mDTO=mDAO.selectOne(mDTO);
		  
		// 만약 mDTO 값이 null이 아니라면
		  if(mDTO != null) {	
				// session에 "memberDTO"속성에  mDTO변수를 저장
			  	session.setAttribute("memberDTO",mDTO);
				
			 // msg 값에 안내문구를 설정
			 // 페이지 이동
				request.setAttribute("msg", "비밀번호 일치. 정보수정페이지로 이동합니다. ");
				forward.setPath("memberUpdatePWCKSuccess.jsp");
				forward.setRedirect(false);
				
				
			}else {// 아니라면
				// msg 값에 안내문구를 설정
				// 페이지 이동
				request.setAttribute("msg", "비밀번호 불일치 다시입력해주세요. ");
				
				forward.setPath("goback.do");
				forward.setRedirect(false);
			}
				
			return forward;
		}

	}



/*
 * forward.setPath("mypageMemberUpdate.jsp"); forward.setRedirect(false);
 */