package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;
import model.dto.WishListDTO;

public class WishListDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL_WISHLIST_RANK_BY_PRODUCT = // 모든상품인기순 정렬
			  "SELECT "
			+ "    RANK() OVER (ORDER BY COUNT(W.WISHLIST_ID) DESC) AS RANK, "
			+ "    P.PRODUCT_NAME, "
			+ "    COUNT(W.WISHLIST_ID) AS WISHLIST_COUNT "
			+ "FROM PRODUCT P "
			+ "JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID "
			+ "GROUP BY P.PRODUCT_NAME "
			+ "ORDER BY RANK ";
	private static final String SELECTALL_WISHLIST_RANK_BY_GENDER = // 성별 인기순 정렬
			"SELECT "
			+ "    ROW_NUMBER() OVER (ORDER BY COUNT(W.WISHLIST_ID) DESC) AS RANK, "
			+ "    COUNT(W.WISHLIST_ID) AS WISHLIST_COUNT, "
			+ "    M.MEMBER_GENDER, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_IMG "
			+ "FROM WISHLIST W "
			+ "JOIN MEMBER M ON W.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE M.MEMBER_GENDER = ? "
			+ "GROUP BY "
			+ "    M.MEMBER_GENDER, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_IMG "
			+ "ORDER BY RANK ";
	private static final String SELECTALL_WISHLIST_RANK_BY_AGE = // 연령별 인기순
			"SELECT "
			+ "  CASE "
			+ "    WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "    WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "    WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "    WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "    ELSE '기타' "
			+ "  END AS 나이대, "
			+ "  PRODUCT_NAME, "
			+ "  COUNT(WISHLIST_ID) AS 총_상품_개수 "
			+ "FROM ( "
			+ "  SELECT "
			+ "    M.MEMBER_ID, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    W.WISHLIST_ID, "
			+ "    TRUNC(MONTHS_BETWEEN(SYSDATE, M.MEMBER_BIRTH) / 12) AS AGE "
			+ "  FROM MEMBER M "
			+ "  JOIN WISHLIST W ON M.MEMBER_ID = W.MEMBER_ID "
			+ "  JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ ") Q "
			+ "WHERE AGE >= ? AND AGE < ? -- ??대인 멤버만 추출 "
			+ "GROUP BY CASE "
			+ "    WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "    WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "    WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "    WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "    ELSE '기타' "
			+ "  END, PRODUCT_NAME "
			+ "ORDER BY COUNT(WISHLIST_ID) DESC ";
	private static final String SELECTALL_WISHLIST_BY_MEMBER = 
			  "SELECT "
			+ "    M.MEMBER_NAME, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_IMG "
			+ "FROM  "
			+ "    WISHLIST W "
			+ "JOIN "
			+ "    MEMBER M ON W.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN "
			+ "    PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE M.MEMBER_ID=? ";
	
	private static final String SELECTALL_WISHLIST_BY_MEMBER_ISWISHED = 
			"SELECT "
					+ "    CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED, "
					+ "    P.PRODUCT_ID, "
					+ "    P.PRODUCT_BRAND, "
					+ "    P.PRODUCT_NAME, "
					+ "    P.PRODUCT_CATEGORY, "
					+ "    P.PRODUCT_PRICE, "
					+ "    P.PRODUCT_IMG "
					+ "FROM "
					+ "    PRODUCT P "
					+ "LEFT OUTER JOIN ( "
					+ "    SELECT PRODUCT_ID "
					+ "    FROM WISHLIST "
					+ "    WHERE MEMBER_ID=? "
					+ ") WL "
					+ "ON P.PRODUCT_ID = WL.PRODUCT_ID ";
	
	private static final String SELECTONE_WISHLIST_CNT_BY_MEMBER =
			"SELECT COUNT(WISHLIST_ID) AS WISHLIST_CNT FROM WISHLIST WHERE MEMBER_ID=? ";
	

	private static final String SELECTONE_IS_PRODUCT_IN_WISHLIST =
			"SELECT WISHLIST_ID "
			+ "FROM WISHLIST "
			+ "WHERE MEMBER_ID=? AND PRODUCT_ID=? ";
	
	
	private static final String UPDATE_ADD_PRODUCT_TO_WISHLIST =
			  "UPDATE WISHLIST "
			+ "SET IS_WISHED = 1 "
			+ "WHERE MEMBER_ID = ? AND PRODUCT_ID = ? ";
	private static final String UPDATE_REMOVE_PRODUCT_FROM_WISHLIST =
			  "UPDATE WISHLIST "
			+ "SET IS_WISHED = 0 "
			+ "WHERE MEMBER_ID = ? AND PRODUCT_ID = ? ";
	
	private static final String INSERT_WISHLIST_BY_PRODUCT = 
			"INSERT INTO WISHLIST (WISHLIST_ID,MEMBER_ID, PRODUCT_ID) "
					+ "VALUES ((SELECT NVL(MAX(WISHLIST_ID),0)+1 FROM WISHLIST),?, ?) ";

	private static final String DELETE_WISHLIST_BY_PRODUCT = 
			"DELETE FROM WISHLIST "
			+ "WHERE MEMBER_ID = ? AND PRODUCT_ID = ? ";
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
		 
		 jsp에서 비동기적으로 보여질 부분은 상품이미지 속 하트버튼
	 */
	
	public ArrayList<WishListDTO> selectAll(WishListDTO wishListDTO){
		ArrayList<WishListDTO> datas = new ArrayList<WishListDTO>();
		if(wishListDTO.getSearchCondition().equals("회원별찜목록")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISHLIST_BY_MEMBER);
				pstmt.setString(1, wishListDTO.getMemberID());
				System.out.println(wishListDTO.getMemberID()+"ㅁㄴㅇ");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
//					System.out.println("wlDAO pID : "+rs.getString("PRODUCT_ID"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
//					System.out.println(rs.getString("PRODUCT_NAME"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
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
		else if(wishListDTO.getSearchCondition().equals("찜")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISHLIST_BY_MEMBER_ISWISHED);
				System.out.println("위시DAO 회원아이디"+wishListDTO.getMemberID());
				pstmt.setString(1, wishListDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setIsWished(rs.getInt("ISWISHED"));
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
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
		else if(wishListDTO.getSearchCondition().equals("제품별찜랭킹")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISHLIST_RANK_BY_PRODUCT);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return datas;
		}
		else if(wishListDTO.getSearchCondition().equals("성별찜랭킹")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISHLIST_RANK_BY_GENDER);
				ResultSet rs = pstmt.executeQuery();
				pstmt.setString(1, wishListDTO.getMemberGender());
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return datas;
		}
		else if(wishListDTO.getSearchCondition().equals("나이별찜랭킹")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISHLIST_RANK_BY_AGE);
				ResultSet rs = pstmt.executeQuery();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return datas;
		}
		else {
			return null;
		}
		
	}
	
	public WishListDTO selectOne(WishListDTO wishListDTO) {
		conn=JDBCUtil.connect();
		WishListDTO data=null;
		if(wishListDTO.getSearchCondition().equals("위시리스트추가삭제")) {
		try {
			pstmt=conn.prepareStatement(SELECTONE_IS_PRODUCT_IN_WISHLIST);
			pstmt.setString(1, wishListDTO.getMemberID());
			pstmt.setInt(2, wishListDTO.getProductID());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data = new WishListDTO();
				data.setWishListID(rs.getInt("WISHLIST_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
		else if(wishListDTO.getSearchCondition().equals("찜수량")) {
			try {
				pstmt=conn.prepareStatement(SELECTONE_WISHLIST_CNT_BY_MEMBER);
				pstmt.setString(1, wishListDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new WishListDTO();
					data.setWishListCnt(rs.getInt("WISHLIST_CNT"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else {
			return null;
		}
	}
	
	
		
	public boolean update(WishListDTO wishListDTO) {
		conn=JDBCUtil.connect();
		if(wishListDTO.getSearchCondition().equals("위시리스트상품추가")) {
			try {
				pstmt=conn.prepareStatement(UPDATE_ADD_PRODUCT_TO_WISHLIST);
				pstmt.setString(1, wishListDTO.getMemberID());
				pstmt.setInt(2, wishListDTO.getProductID());
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
		else if(wishListDTO.getSearchCondition().equals("위시리스트상품삭제")) {
			try {
				pstmt=conn.prepareStatement(UPDATE_REMOVE_PRODUCT_FROM_WISHLIST);
				pstmt.setString(1, wishListDTO.getMemberID());
				pstmt.setInt(2, wishListDTO.getProductID());
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
		else {
			return false;
		}
	}
	public boolean insert(WishListDTO wishListDTO) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT_WISHLIST_BY_PRODUCT);
			pstmt.setString(1, wishListDTO.getMemberID());
			pstmt.setInt(2, wishListDTO.getProductID());
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

	public boolean delete(WishListDTO wishListDTO) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(DELETE_WISHLIST_BY_PRODUCT);
			System.out.println("wishListDAO들어옴");
			System.out.println("getMemberID : "+wishListDTO.getMemberID());
			System.out.println("getProductID : "+wishListDTO.getProductID());
			pstmt.setString(1, wishListDTO.getMemberID());
			pstmt.setInt(2, wishListDTO.getProductID());
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
