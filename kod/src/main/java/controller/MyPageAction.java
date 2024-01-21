package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AddressDAO;
import model.dao.MemberDAO;
import model.dto.AddressDTO;
import model.dto.MemberDTO;

public class MyPageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("mypageAction 시작");
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("mypage.jsp");
		forward.setRedirect(false); //
		
		MemberDTO mDTO=new MemberDTO();
		MemberDAO mDAO=new MemberDAO();
		
		HttpSession session=request.getSession();
		mDTO.setMemberID(((MemberDTO)session.getAttribute("memberDTO")).getMemberID());
		mDTO.setSearchCondition("ID체크");
		mDAO.selectOne(mDTO);
		
		System.out.println("로그: myPageAction"+mDTO);
		
		AddressDTO aDTO=new AddressDTO();
		AddressDAO aDAO=new AddressDAO();
		
		aDTO.setMemberID(mDTO.getMemberID());
		
		ArrayList<AddressDTO> aDatas = aDAO.selectAll(aDTO);
		request.setAttribute("addressDTO", aDatas);
		for (AddressDTO addressDTO : aDatas) {
		    System.out.println(addressDTO); // 주소지 정보 출력
		}
		
		System.out.println("mypageAction 끝");
		return forward;
	}

}
