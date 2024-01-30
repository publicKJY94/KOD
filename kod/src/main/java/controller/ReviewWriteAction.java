package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.dao.ReviewDAO;
import model.dto.MemberDTO;
import model.dto.ReviewDTO;

@WebServlet("/reviewWriteAction")
public class ReviewWriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewWriteAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("reviewWriteAction 들어옴");
		ActionForward forward = new ActionForward();
		forward.setPath("myPage.do");
		forward.setRedirect(false);
		
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("로그아웃상태 : memberID is null");
		}
		
		
		
		/* 리뷰 작성로직
		 * 
		 * 리뷰를 작성하려면 아래와 같이 접근가능
		 * teemo회원 주문목록 조회 -> 주문내역(1001,1002,1003)존재 -> 1001번 리뷰작성
		 * 
		 * 리뷰를 작성하려면 주문내역에 해당 상품이 존재해야하는데
		 * 리뷰작성로직에서
		 * teemo회원의 주문목록에 102번 상품이 존재한다면 이라는 로직이 필요한가요 ?
		 * 
		 * 아니면 
		 * 1001
		 * 		101
		 * 1002
		 * 		102
		 * 		103
		 * 1003
		 * 		104
		 * [필수아님]
		 * [SELECTONE TEEMO 102? null아님]
		 * 					null 이상한 접근
		 * 
		 * 주문목록페이지		작성완료페이지
		 * V->[C]->V->[C]->C->V
		 * 		리뷰작성페이지
		 * 
		 */
		
		 try {
	            // 업로드된 파일을 저장할 경로 설정
	            String savePath = getServletContext().getRealPath("uploads");
//	            String contextPath = getServletContext().getContextPath();
	            
	            System.out.println("savePath : "+savePath);
	            String uploadFilePath = savePath + File.separator + "uploads";
//	            String uploadFilePath2 = "C:/Users/Springonward/Desktop/KOIT/KODsounds"+ contextPath + "/src/main/webapp/uploads/";
	            String uploadFilePath2 = "C:/Users/Springonward/Desktop/KOIT/KODsounds/kod/src/main/webapp/uploads/";
	            System.out.println("uploadFilePath2 : " + uploadFilePath2);
	            // MultipartRequest를 생성하여 파일 업로드 처리
	            MultipartRequest multipartRequest = new MultipartRequest(
	                    request,
	                    uploadFilePath,
	                    5 * 1024 * 1024, // 최대 업로드 파일 크기 제한 (5MB)
	                    "UTF-8",
	                    new DefaultFileRenamePolicy()
	            );
	            


	            // 업로드된 파일 정보 가져오기
	            String fileName = multipartRequest.getFilesystemName("imageUpload");
	            String filePath = uploadFilePath + File.separator + fileName;
//	            String filePath = uploadFilePath2 + fileName;

	            // 나머지 일반 폼 데이터 가져오기
	            String title = multipartRequest.getParameter("title");
	            String content = multipartRequest.getParameter("content");
	            int reviewScore = Integer.parseInt(multipartRequest.getParameter("score"));
	            int productID = Integer.parseInt(multipartRequest.getParameter("productID"));
	            System.out.println("title : "+title);
	            System.out.println("content : "+content);
	            System.out.println("reviewScore : "+reviewScore);
	            System.out.println("filePath : "+filePath);
	            System.out.println("productID : "+productID);
	            

	            // reviewDTO 설정
	            ReviewDTO reviewDTO = new ReviewDTO();
	    		ReviewDAO reviewDAO = new ReviewDAO();
	            reviewDTO.setReviewTitle(title);
	            reviewDTO.setReviewContent(content);
	            reviewDTO.setReviewScore(reviewScore);
	            reviewDTO.setReviewImg(filePath);
	            reviewDTO.setMemberID(memberID);
	            reviewDTO.setProductID(productID);
	            boolean flag = reviewDAO.insert(reviewDTO);
	            if(!flag) {
	            	System.out.println("리뷰작성 실패");
	            }
	            else {
	            	System.out.println("리뷰작성 성공");
	            }

	            
	            // 
	            InputStream in = new FileInputStream(filePath); // 파일 읽기 그릇 생성
	            OutputStream os = new FileOutputStream(uploadFilePath2+fileName);
	            System.out.println("OutputStream 경로"+uploadFilePath2+fileName);
	    		
	    		long start = System.currentTimeMillis();
	    		while(true){
	    			int inputData = in.read();
	    			if(inputData==-1) break;
	    			os.write(inputData);
	    		}// end while
	    		long end = System.currentTimeMillis();
	    		System.out.println(end-start); // 파일복사 걸린 시간
	    		in.close();
	    		os.close();
	    		System.out.println("copy success");
	            
	            
	            
	            
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            // 예외 처리
	        }
		
		
		
		 
		 
	}

}



















//File src = new File(uploadFilePath);//절대경로 - 복사원본
//File dist = new File("C:/Users/Springonward/Desktop/KOIT/KODsounds/src/main/webapp/uploads");//절대경로 - 복제될 위치
//int count;
//FileInputStream fis = null;
//FileOutputStream fos = null;
//BufferedInputStream bis = null;
//BufferedOutputStream bos = null;
//
//try {
//	fis = new FileInputStream(src); // 파일 입력 바이트 스트림 연결
//	fos = new FileOutputStream(dist);// 파일 출력 바이트 스트림 연결
//	bis = new BufferedInputStream(fis);//버퍼 입력스트림 연결
//	bos = new BufferedOutputStream(fos); // 버퍼출력스트림 연결
//	
//	while((count = bis.read())!=1){
//		bos.write((char)count);
//	}//end while
//	
//	System.out.println("파일 복사 성공");
//	bis.close(); 
//	bos.close();
//	fis.close();
//	fos.close();
//} catch (Exception e) {
//	System.out.println("파일 복사 오류 발생");
//}
