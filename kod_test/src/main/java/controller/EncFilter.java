package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter({ "*.do", "*.jsp" })
// 중괄호는 매핑이 여러개 발생했기 때문이다
// 컨테이너의 역할 >> 객체를 프로그램에서 생성(new) 및 관리(싱글톤 패턴) 수행(메서드 사용가능)[사용자가 생성 x]
public class EncFilter extends HttpFilter implements Filter {
	private String encoding;

	public EncFilter() {
		super();
	}
	// 마지막에 단 한번만 실행되는 메서드
	public void destroy() {
	}

	// 인코딩 설정을 분리한 이유
	// 1. 결합도를 낮추고
	// 2. 응집도를 높일 수 있음
	// => 유지보수 용이해짐
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(encoding);
		}
		// 다음필터가 존재한다면 그곳으로 이동
		// 더이상 수행할 필터가 없다면, 원래 수행하던 요청으로 돌아간다
		chain.doFilter(request, response);
	}
	// 자바는 멤버변수를 생성자에서 초기화 하지만, filter는 그 행동을 인식하고 있기 때문에 init에서 실행 >> 유사 생성자
	// 최초에 단 한번만 실행되는 메서드
	public void init(FilterConfig fConfig) throws ServletException {
		this.encoding=fConfig.getServletContext().getInitParameter("encoding");
	}

}
