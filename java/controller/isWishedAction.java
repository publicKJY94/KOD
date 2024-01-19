package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * 컨트롤러는 인풋, 아웃풋, 기능 에 대한 한글코딩이 필요합니다.
 */
public class isWishedAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		forward.setPath("addToWishList.jsp");
		forward.setRedirect(false);
		
		/*
		 isWished 클래스를 가진 버튼이 클릭되면 
		 비동기적으로 상품을 추가하는 기능을 수행하고 싶어
		 isWished클래스를 가진 버튼이 클릭될때
		 해당 제품이 가진 isWished 변수의 값이 false라면 
		 데이터베이스에 있는 해당멤버의 위시리스트목록에 해당제품을 추가하고
		 true로 변경해준 뒤 하트에 빨강색을 칠해준다
		 만약 
		 회원이 iswished버튼을 클릭할 때
		 iswished의 값이 true라면
		 데이터베이스에 있는 해당멤버의 위시리스트목록에 있는 해당제품을 삭제하고
		 iswished의 값을 false로 변경해준뒤 색깔을 비워준다.
		 이 boolean타입의 iswished
		 
		 jsp에서 비동기적으로 보여질 부분은 상품이미지 속 하트버튼
		 
		 회원에 따라 상품의 iswished 값이 달라야하는데
		 어떻게 구분해줄수 있을까?
		 
		 
		 회원아이디
		 제품아이디
		 제품 iswished변수값
		 
		 
		 
		 if(iswished가 1)
		 	좋아요한상태
		 	delete호출
		 else
		 	안좋아요상태
		 	insert호출
		 
		 
		 인풋
		 아웃풋
		 기능
		 
		 
		 
		 
		 
		 
		 */
		
		
		
		return forward;
	}
}
