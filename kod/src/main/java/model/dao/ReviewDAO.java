package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ReviewDTO;
import model.util.JDBCUtil;

public class ReviewDAO {
	private Connection conn; 
	private PreparedStatement pstmt;
	
	private static final String SELECTALL_PRODUCT_REVIEW=
			"SELECT  "
			+ "    R.REVIEW_ID, "
			+ "    R.REVIEW_TITLE, "
			+ "    R.REVIEW_CONTENT, "
			+ "    R.REVIEW_DATE, "
			+ "    R.REVIEW_SCORE, "
			+ "    R.REVIEW_IMG, "
			+ "    R.REVIEW_REPLY, "
			+ "    R.MEMBER_ID, "
			+ "    M.MEMBER_NAME, "
			+ "    R.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME "
			+ "FROM  "
			+ "    REVIEW R "
			+ "JOIN  "
			+ "    MEMBER M ON R.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN  "
			+ "    PRODUCT P ON R.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE  "
			+ "    R.PRODUCT_ID = ? ";
	private static final String SELECTALL_MY_REVIEW=
			  "SELECT "
			+ "    R.REVIEW_ID, "
			+ "    R.REVIEW_TITLE, "
			+ "    R.REVIEW_CONTENT, "
			+ "    R.REVIEW_DATE, "
			+ "    R.REVIEW_SCORE, "
			+ "    R.REVIEW_IMG, "
			+ "    R.REVIEW_REPLY, "
			+ "    R.MEMBER_ID, "
			+ "    R.PRODUCT_ID, "
			+ "    M.MEMBER_NAME, "
			+ "    P.PRODUCT_NAME "
			+ "FROM  "
			+ "    REVIEW R "
			+ "JOIN  "
			+ "    MEMBER M ON R.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN  "
			+ "    PRODUCT P ON R.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE  "
			+ "    M.MEMBER_ID = ? ";
	
	private static final String SELECTONE_DELTAIL_REVIEW=
			"SELECT  "
			+ "    R.REVIEW_TITLE, "
			+ "    R.REVIEW_CONTENT, "
			+ "    R.REVIEW_DATE, "
			+ "    R.REVIEW_SCORE, "
			+ "    R.REVIEW_IMG, "
			+ "	   R.REVIEW_ID, "
			+ "    R.MEMBER_ID, "
			+ "    M.MEMBER_NAME, "
			+ "    R.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    R.REVIEW_REPLY "
			+ "FROM  "
			+ "    REVIEW R "
			+ "JOIN  "
			+ "    MEMBER M ON R.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN  "
			+ "    PRODUCT P ON R.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE  "
			+ "    R.REVIEW_ID = ? ";
	
	private static final String SELECTONE_REVIEW_CHECK= "SELECT "
			+ "O.ORDERCONTENT_ID, "
			+ "O.ORDERLIST_ID, "
			+ "O.PRODUCT_ID, "
			+ "O.ORDERCONTENT_CNT, "
			+ "NVL(R.REVIEW_ID, 0) AS REVIEW_ID, "
			+ "R.MEMBER_ID, "
			+ "R.REVIEW_TITLE, "
			+ "R.REVIEW_CONTENT, "
			+ "R.REVIEW_DATE, "
			+ "R.REVIEW_SCORE, "
			+ "R.REVIEW_IMG, "
			+ "R.REVIEW_REPLY "
			+ "FROM "
			+ "ORDERCONTENT O "
			+ "LEFT OUTER JOIN "
			+ "REVIEW R ON O.ORDERCONTENT_ID = R.ORDERCONTENT_ID "
			+ "WHERE "
			+ "O.ORDERCONTENT_ID = ?";
	
	private static final String INSERT=
			"INSERT INTO REVIEW ( "
			+ "    REVIEW_ID, "
			+ "    REVIEW_TITLE, "
			+ "    REVIEW_CONTENT, "
			+ "    REVIEW_SCORE, "
			+ "    REVIEW_IMG, "
			+ "    MEMBER_ID, "
			+ "    PRODUCT_ID, "
			+ "    ORDERCONTENT_ID "
			+ ") VALUES ( "
			+ "    (SELECT NVL(MAX(REVIEW_ID),0)+1 FROM REVIEW), "
			+ "    ?, "
			+ "    ?, "
			+ "    ?, "
			+ "    ?, "
			+ "    ?, "
			+ "    ?, "
			+ "    ? "
			+ ") ";
	
	private static final String UPDATE=
			  "UPDATE REVIEW "
			  + "SET "
			  + "    REVIEW_TITLE = ?, "
			  + "    REVIEW_CONTENT = ?, "
			  + "    REVIEW_DATE = SYSTIMESTAMP, "
			  + "    REVIEW_SCORE = ?, "
			  + "    REVIEW_IMG = ? "
			  + "WHERE "
			  + "    REVIEW_ID = ? "
			  + "AND MEMBER_ID = ? ";
	
	private static final String DELETE=
			  "DELETE FROM REVIEW "
			+ "WHERE "
			+ "    REVIEW_ID = ? "
			+ "AND MEMBER_ID = ? ";
	

	public ArrayList<ReviewDTO> selectAll(ReviewDTO reviewDTO) {
		ArrayList<ReviewDTO> datas = new ArrayList<ReviewDTO>();
		ReviewDTO data = null;
		PreparedStatement pstmt = null;
		conn=JDBCUtil.connect();
		try {
			if(reviewDTO.getSearchCondition().equals("상품리뷰전체조회")) {
				System.out.println("DAO, 상품리뷰전체조회 들어옴");
					pstmt = conn.prepareStatement(SELECTALL_PRODUCT_REVIEW);
					pstmt.setInt(1, reviewDTO.getProductID());
					
			}
			else if(reviewDTO.getSearchCondition().equals("회원리뷰전체조회")) {
					System.out.println("DAO, 회원리뷰전체조회 들어옴");
					pstmt = conn.prepareStatement(SELECTALL_MY_REVIEW);
					pstmt.setString(1, reviewDTO.getMemberID());
			}
			else {
				return null;
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				data=new ReviewDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setReviewID(rs.getInt("REVIEW_ID"));
				data.setReviewTitle(rs.getString("REVIEW_TITLE"));
				System.out.println("리뷰제목 : "+rs.getString("REVIEW_TITLE"));
				data.setReviewContent(rs.getString("REVIEW_CONTENT"));
				data.setReviewScore(rs.getInt("REVIEW_SCORE"));
				data.setReviewDate(rs.getDate("REVIEW_DATE"));
				data.setReviewImg(rs.getString("REVIEW_IMG"));
				data.setReviewReply(rs.getString("REVIEW_REPLY"));
				
				datas.add(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return datas;
	}
	public ReviewDTO selectOne(ReviewDTO reviewDTO) {
		ReviewDTO data = null;
		conn=JDBCUtil.connect();
		try {
			if(reviewDTO.getSearchCondition().equals("내리뷰상세조회")) {
					pstmt = conn.prepareStatement(SELECTONE_DELTAIL_REVIEW);
					pstmt.setInt(1, reviewDTO.getReviewID());
			}
			else if(reviewDTO.getSearchCondition().equals("리뷰체크")) {
					pstmt= conn.prepareStatement(SELECTONE_REVIEW_CHECK);
					pstmt.setInt(1, reviewDTO.getOdContentID());
					
			}
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new ReviewDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setReviewID(rs.getInt("REVIEW_ID"));
				data.setReviewTitle(rs.getString("REVIEW_TITLE"));
				data.setReviewContent(rs.getString("REVIEW_CONTENT"));
				data.setReviewScore(rs.getInt("REVIEW_SCORE"));
				data.setReviewDate(rs.getDate("REVIEW_DATE"));
				data.setReviewImg(rs.getString("REVIEW_IMG"));
				data.setReviewReply(rs.getString("REVIEW_REPLY"));
				data.setOdContentID(rs.getInt("ORDERCONTENT_ID"));
				System.out.println(data);
			}
		} catch (SQLException e) {
				e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
	public boolean insert(ReviewDTO reviewDTO) {
		System.out.println("DAO, INSERT쿼리 들어옴");
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT);
			pstmt.setString(1, reviewDTO.getReviewTitle());
			pstmt.setString(2, reviewDTO.getReviewContent());
			pstmt.setDouble(3, reviewDTO.getReviewScore());
			pstmt.setString(4, reviewDTO.getReviewImg());
			pstmt.setString(5, reviewDTO.getMemberID());
			pstmt.setInt(6, reviewDTO.getProductID());
			pstmt.setInt(7, reviewDTO.getOdContentID());
			System.out.println("title : "+reviewDTO.getReviewTitle());
            System.out.println("content : "+reviewDTO.getReviewContent());
            System.out.println("reviewScore : "+reviewDTO.getReviewScore());
            System.out.println("productID : "+reviewDTO.getProductID());
            System.out.println("filePath : "+reviewDTO.getReviewImg());
			int result = pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return true;
	}
	public boolean update(ReviewDTO reviewDTO) {
		try {
			pstmt=conn.prepareStatement(UPDATE);
			pstmt.setString(1, reviewDTO.getReviewTitle());
			pstmt.setString(2, reviewDTO.getReviewContent());
			pstmt.setDouble(3, reviewDTO.getReviewScore());
			pstmt.setString(4, reviewDTO.getReviewImg());
			pstmt.setInt(5, reviewDTO.getReviewID());
			pstmt.setString(6, reviewDTO.getMemberID());
			int result = pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public boolean delete(ReviewDTO reviewDTO) {
		try {
			pstmt=conn.prepareStatement(DELETE);
			pstmt.setInt(1, reviewDTO.getReviewID());
			pstmt.setString(2, reviewDTO.getMemberID());
			int result = pstmt.executeUpdate();
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
}
