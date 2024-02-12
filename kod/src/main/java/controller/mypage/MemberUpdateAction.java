package controller.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.util.Action;
import controller.util.ActionForward;
import model.dao.MemberDAO;
import model.dto.MemberDTO;

public class MemberUpdateAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 새로운 ActionForward 객체생성
		 ActionForward forward = new ActionForward();
	      
		// MemberDAO,MemberDTO 객체 생성
	      MemberDTO mDTO = new MemberDTO();
	      MemberDAO mDAO = new MemberDAO();
	     
	      System.out.println(request.getParameter("memberName")+"[본승] 로그 이름") ;
	      System.out.println(request.getParameter("memberPW") + "[본승] 로그 비밀번호");
	      System.out.println(request.getParameter("memberID") + "[본승] 로그 아이디");
	      System.out.println(request.getParameter("memberPhNum") + "[본승] 로그 핸드폰번호");
	      
	      // 회원정보 변경페이지에서 받아온 회원이름, 비밀번호, 아이디, 핸드폰 번호, 이메일 값 들을 mDTO객체에 저장 
	      mDTO.setMemberName(request.getParameter("memberName"));
	      mDTO.setMemberPW(request.getParameter("memberPW"));
	      mDTO.setMemberID(request.getParameter("memberID"));
	      mDTO.setMemberPhNum(request.getParameter("memberPhNum"));
	      mDTO.setMemberEmail(request.getParameter("memberEmail"));
	      System.out.println("[본승] 로그 memberUpdateAction -> memberEmail "+mDTO.getMemberEmail());
	      System.out.println("[본승] 로그 memberUpdateAction -> memberPhNum "+mDTO.getMemberPhNum());
	     
	      // mDAO.update 메서드 실행 인자로(mDTO)를 가져감
	      // 실행후 결과값 논리형 변수 falg에 저장
	      boolean flag = mDAO.update(mDTO);
	      System.out.println(flag + "flag <<<");
	     
	      // flag값이 true라면
	      if(flag) {	// 로그아웃 
	         forward.setRedirect(false);
	         forward.setPath("memberUpdateLogout.do");
	         
	      }else { // 아니라면 이전페이지로 이동
	    	  forward.setRedirect(false);
		      forward.setPath("goback.do");
	      }
	     
	  
		return forward;
	}

}
