package controller.review;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import controller.util.ActionForward;
import model.dao.ReviewDAO;
import model.dto.MemberDTO;
import model.dto.ReviewDTO;

@WebServlet("/reviewWriteServlet")
public class ReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[로그:정현진] reviewWriteServlet 들어옴");
		ActionForward forward = new ActionForward();
		forward.setPath("myPage.do");
		forward.setRedirect(false);
		
		HttpSession session = request.getSession();
		String memberID = null;
		try {
			memberID = ((MemberDTO)session.getAttribute("memberDTO")).getMemberID();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("[로그:정현진] 로그아웃상태 : memberID is null");
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
	            // 업로드된 파일을 저장할 경로 설정/..
	            String uploadFilePath = getServletContext().getRealPath("uploads");
//	            String copyFilePath = "C:/Users/Springonward/Desktop/KOIT/KODsounds/kod/src/main/webapp/uploads/";
	            System.out.println("uploadFilePath : "+uploadFilePath);
//	            System.out.println("copyFilePath : " + copyFilePath);
	            
	            // KOD팀끼리 공유하기 위한 복사경로 만들기
	            int num = uploadFilePath.indexOf("."); // 첫번째 만나는 .의 인덱스값
	            String forwardUrlParts = uploadFilePath.substring(0, num);
	            System.out.println("forwardUrlParts : "+forwardUrlParts);
	            String middleUrlParts = request.getContextPath();
	            System.out.println("middleUrlParts : "+middleUrlParts);
	            String lastUrlParts = "/main/webapp/uploads";
	            String copyFilePathForKOD = forwardUrlParts+"kod/src"+lastUrlParts+File.separator;
	            System.out.println("copyFilePathForKOD : "+copyFilePathForKOD);
	            
	            request.getContextPath();

	            // MultipartRequest를 생성하여 파일 업로드 처리
	            MultipartRequest multipartRequest = new MultipartRequest(
	                    request,
	                    uploadFilePath,
	                    50 * 1024 * 1024, // 최대 업로드 파일 크기 제한 (5MB)
	                    "UTF-8",
	                    new DefaultFileRenamePolicy()
	            );
	            
	            
	            // 업로드된 파일 정보 가져오기
	            String fileName = multipartRequest.getFilesystemName("imageUpload");

	            ReviewDTO reviewDTO = new ReviewDTO();
				if (fileName != null) {
	                // 파일이 업로드되었을 경우에만 파일 복사 및 경로 설정
	                String filePath = uploadFilePath + File.separator + fileName;
	                System.out.println("filePath: " + filePath);

	                // 파일이 존재하는지 확인
	                boolean isFileExists = new File(filePath).exists();
	                
	                // 파일 복사
	                InputStream in = new FileInputStream(filePath);
	                OutputStream os = new FileOutputStream(copyFilePathForKOD + fileName);

	                long start = System.currentTimeMillis();
	                while (true) {
	                    int inputData = in.read();
	                    if (inputData == -1) break;
	                    os.write(inputData);
	                }
	                long end = System.currentTimeMillis();
	                System.out.println(end - start); // 파일 복사 걸린 시간
	                in.close();
	                os.close();
	                System.out.println("copy success");

	                reviewDTO.setReviewImg(isFileExists ? filePath : null);
	                
	                response.setContentType("application/json");
	                response.setCharacterEncoding("UTF-8");

	                try (PrintWriter out = response.getWriter()) {
	                    out.print("{\"isFileExists\": " + isFileExists + "}");
	                    out.flush();
	                }
	                
	                // 리뷰 작성 시 이미지 파일 경로 설정
	                reviewDTO.setReviewImg(filePath);
	            } else {
	                // 파일이 없는 경우에는 리뷰 작성 시 이미지 파일 경로를 null 또는 빈 문자열로 설정
	                reviewDTO.setReviewImg(""); // 또는 reviewDTO.setReviewImg(null);
	            }

	            // 나머지 일반 폼 데이터 가져오기
	            String title = multipartRequest.getParameter("title");
	            String content = multipartRequest.getParameter("content");
	            int reviewScore = Integer.parseInt(multipartRequest.getParameter("score"));
	            int productID = Integer.parseInt(multipartRequest.getParameter("productID"));

	            System.out.println("title: " + title);
	            System.out.println("content: " + content);
	            System.out.println("reviewScore: " + reviewScore);
	            System.out.println("productID: " + productID);

	            // reviewDTO 설정
	            ReviewDAO reviewDAO = new ReviewDAO();
	            reviewDTO = new ReviewDTO();
	            reviewDTO.setReviewTitle(title);
	            reviewDTO.setReviewContent(content);
	            reviewDTO.setReviewScore(reviewScore);
	            reviewDTO.setMemberID(memberID);
	            reviewDTO.setProductID(productID);
	            boolean flag = reviewDAO.insert(reviewDTO);
	            if(!flag) {
	            	System.out.println("리뷰작성 실패");
	            }
	            else {
	            	System.out.println("리뷰작성 성공");
	            }

	            
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
