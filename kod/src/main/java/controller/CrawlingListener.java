package controller;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import model.dao.ProductDAO;
import model.dto.ProductDTO;

@WebListener
public class CrawlingListener implements ServletContextListener {

    public CrawlingListener() {
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    }

    public void contextInitialized(ServletContextEvent sce)  { 
    	
//    	ServletContext sc = sce.getServletContext();
//    	sc.setAttribute("teemo", "작은 티모");
    	// sc == application
    
    	System.out.println("[로그] 리스너 서블릿 클래스 들어옴");
    	
//    	HeadPhoneCrawling headPhoneCrawling = new HeadPhoneCrawling();
//    	ArrayList<ProductDTO> headPhoneDatas = headPhoneCrawling.crawl();
//    	ProductDAO productDAO = new ProductDAO();
//    	for (ProductDTO headPhoneData : headPhoneDatas) {
//			headPhoneData.setSearchCondition("크롤링");
//			productDAO.insert(headPhoneData);
//		}
    	
//    	SpeakerCrawling speakerCrawling = new SpeakerCrawling();
//    	ArrayList<ProductDTO> speakerDatas = speakerCrawling.crawl();
//    	ProductDAO productDAO = new ProductDAO();
//    	for (ProductDTO speakerData : speakerDatas) {
//			speakerData.setSearchCondition("크롤링");
//			productDAO.insert(speakerData);
//		}
    	
    }
	
}
