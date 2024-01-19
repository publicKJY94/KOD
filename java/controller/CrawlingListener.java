package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CrawlingListener implements ServletContextListener {

    public CrawlingListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	// 여기에 크롤링 관련 코드를 작성
    	// 처음 컨테이너를 생성시 단 한번 실행
    	ServletContext sc = sce.getServletContext();
    	sc.setAttribute("teemo", "작은 티모");
    }
	
}
