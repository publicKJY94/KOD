package controller.crawling;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import model.dto.ProductDTO;

public class HeadPhoneCrawling {

	public ArrayList<ProductDTO> crawl() {
		
		System.out.println("[로그]HeadPhoneCrawling 들어옴");
		
		String url = "https://www.bang-olufsen.com/ko/kr/headphones"; // 뱅앤올룹슨 url -> 뱅앤올룹슨 공홈 헤드폰차트에 있는 제품들의 URL주소를 얻어오기위해 사용됨
		String url2 = "https://www.bang-olufsen.com"; // 제품상세페이지로 넘어가기위해 사용됨 -> url+제품상세주소
		Document doc = null; // 뱅앤올룹슨 헤드폰차트의 주소를 연결하는데 사용됨 -> 해당 주소에서 제품상세 주소를 얻어옴 -> 등록된 제품 개수만큼 반복진행
		Document doc2 = null; // 제품상세페이지의 모든정보를 얻어올 변수선언
		
		ArrayList<ProductDTO> productDatas = new ArrayList<ProductDTO>();
		try {
			doc = Jsoup.connect(url).get(); // 
			Elements productUrl = doc.select("div.product-card-wrap > div > a"); // 제품상세페이지 url
				
			for (int i = 0; i < productUrl.size(); i++) { // 헤드폰차트에 있는 제품개수만큼 반복문 수행
					doc2 = Jsoup.connect(url2+productUrl.get(i).select("a").attr("href")).get(); // 각각의 제품상세페이지 url 연결
					
					String productImgUrl = null; // 상품이미지 URL
					String productName = null; // 상품명
					String price_str=null; // 상품가격 -> 원화표시가 붙은 가격이 넘어오므로 String타입으로 정의
					int price=0; // 상품가격 -> 받아온 String타입의 가격을 Integer타입으로 형변환하여 데이터를 넘겨주기위해 사용
					String productInfo=null; // 제품설명(정보)
					
					productName = doc2.select("span.h5").get(0).text();
					price_str = doc2.select("span.product-price").text(); // 원화표시679,000 기존 가격
					productInfo = doc2.select("div>p").get(1).text();
					price_str = price_str.substring(1).replace(",", ""); // 첫번째문자제거 //  ','제거 => 649000 기존 가격
					if(price_str.contains(" 기존 가격")) { 
						price_str=price_str.replace(" 기존 가격", ""); // " 기존 가격" 제거 => 649000
					}
					price = Integer.parseInt(price_str); // 형변환하여 price변수에 초기화
					
					productImgUrl = doc2.select("div.slick-list img").attr("src"); // div.slick-list클래스 안에있는 img 태그의 src속성 가져오기 
					System.out.println(productName);
					System.out.println(price);
					
					
					ProductDTO productDTO = new ProductDTO();
					productDTO.setProductName(productName);
					productDTO.setProductPrice(price);
					productDTO.setProductInfo(productInfo);
					productDTO.setProductImg(productImgUrl); 
					productDTO.setProductCategory("헤드폰");
					productDatas.add(productDTO);
					
					
			} // for(i++)
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return productDatas;
		
	}
	
}
