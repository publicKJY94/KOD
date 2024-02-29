package controller.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

/* [정현진]
 * Action 인터페이스는 어떤 작업을 수행하고 
 * 그 결과를 나타내는 ActionForward 객체를 반환하는 역할을 합니다.
 * execute함수는 클라이언트의 요청을 처리하는데 사용되는 메소드로
 * ActionForward타입의 객체를 반환하고
 * 반환된 객체는 다음에 수행할 작업과 관련된 정보를 담고 있습니다.
 * ActionForward클래스에 구현된 기능은 
 * 어디로 가야할지에 대한 경로(path)와 그에 따른 데이터를 준비하여 JSP 페이지로 포워딩하거나
 * 다른 서블릿으로 리다이렉트하는 등의 작업을 수행합니다.
 * 이렇게 설계된 인터페이스를 사용함으로써 코드의 유지보수성과 확장성을 높일 수 있습니다.
 * 
 */
