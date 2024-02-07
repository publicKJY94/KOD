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
		ActionForward forward = new ActionForward();
		
		
		request.setCharacterEncoding("UTF-8");
		  
		  MemberDTO mDTO=new MemberDTO(); 
		  MemberDAO mDAO=new MemberDAO();
		  
		  HttpSession session=request.getSession();
		 // ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
	
		  mDTO.setSearchCondition("로그인");
		  
		  //mDTO.setMemberID((String)session.getAttribute("memberDTO")).getMemberID());
		  mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		  System.out.println(session.getAttribute("MemberID"));
		  mDTO.setMemberPW(request.getParameter("memberPW"));
				  
		  mDTO=mDAO.selectOne(mDTO);
		  
		 
		  if(mDTO != null) {
			 // HttpSession session = request.getSession();
				session.setAttribute("memberDTO",mDTO);
				
				
				request.setAttribute("msg", "비밀번호 일치. 정보수정페이지로 이동합니다. ");
				forward.setPath("memberUpdatePWCKSuccess.jsp");
				forward.setRedirect(false);
				
				
			}else {
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