package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AddressDAO;
import model.dao.MemberDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

public class JoinMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward = new ActionForward();
		
		request.setCharacterEncoding("UTF-8");
		
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		System.out.println(year);
		String memberBirth = year+month+day;
		
		System.out.println("[로그]" + memberBirth);
		
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		
		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));
		mDTO.setMemberName(request.getParameter("memberName"));
		mDTO.setMemberPhNum(request.getParameter("memberPhNum"));
		mDTO.setMemberEmail(request.getParameter("memberEmail"));
		mDTO.setMemberGender(request.getParameter("memberGender"));
		mDTO.setMemberBirth(memberBirth);
		
		// 멤버와 어드레스 부분 액션을 나눠두었기 때문에 멤버만 올바르게 입력해도 DB에 정보가 저장된다.
		// 주소 부분은 마이페이지 등에서 수정하거나 추가 혹은 삭제가 가능하기 때문에 유지보수가 용이하다
		// 이는 JSP단계에서는 아직 롤백 기능 적용이 불가능한 상황이라 추후 스프링으로 바꾸는 과정에서 수정할 예정입니다~
		
		boolean flag=mDAO.insert(mDTO);
		
		if(flag) {
			  	String memberId = mDTO.getMemberID(); 
			    forward.setPath("joinAddress.do"); 
			    request.setAttribute("memberDTO", memberId);
			    forward.setRedirect(false);      
		}
		
		else{
			request.setAttribute("msg", "회원가입에 실패하였습니다");
			forward.setPath("goback.do");
			forward.setRedirect(false);
		}
		
		return forward;
	}

}
