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

		ActionForward forward = new ActionForward();

		request.setCharacterEncoding("UTF-8");
		String year = request.getParameter("year");
		String month = request.getParameter("month");
		String day = request.getParameter("day");

		String memberBirth = year + month + day;

		
	
		 String memberEmail1 = request.getParameter("memberEmail1");  
		 String memberEmail2 = request.getParameter("memberEmail2");
		
		 String memberEmail = memberEmail1+"@"+memberEmail2;
		 
		 String PhNum1 = request.getParameter("PhNum1");
		 String PhNum2 = request.getParameter("PhNum2");
		 String PhNum3 = request.getParameter("PhNum3");
		 
		 String memberPhNum = PhNum1+PhNum2+PhNum3;
		 

		System.out.println("[로그]" + memberBirth);
		System.out.println("[로그 이메일]" + memberEmail);

		MemberDTO mDTO = new MemberDTO();
		MemberDAO mDAO = new MemberDAO();

		mDTO.setMemberID(request.getParameter("memberID"));
		mDTO.setMemberPW(request.getParameter("memberPW"));
		mDTO.setMemberName(request.getParameter("memberName"));
		mDTO.setMemberPhNum(memberPhNum);
		mDTO.setMemberEmail(memberEmail);
		mDTO.setMemberGender(request.getParameter("memberGender"));
		mDTO.setMemberBirth(memberBirth);
		System.out.println(mDTO.getMemberID());
		boolean flag = mDAO.insert(mDTO);

		if (flag) {
			forward.setPath("joinAddress.do");
			forward.setRedirect(false);
		} else {
			request.setAttribute("msg","회원가입에 실패하였습니다");

			forward.setPath("goback.do");
			forward.setRedirect(false);
		}

		return forward;
	}

}
