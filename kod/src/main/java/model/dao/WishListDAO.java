package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.util.JDBCUtil;
import model.dto.WishListDTO;

//관점지향적으로 횡단관리 하였음
public class WishListDAO {
	private Connection conn; // DB와의 연결을 담당
	private PreparedStatement pstmt; // CRUD 수행을 담당

	private static final String SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGIN = // 인기순 정렬 - 로그인상태
			"SELECT  "
			+ "     CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED,  "
			+ "     ROWNUM AS RANK,  "
			+ "     RANKED_PRODUCTS.PRODUCT_ID,  "
			+ "     RANKED_PRODUCTS.PRODUCT_BRAND,  "
			+ "     RANKED_PRODUCTS.PRODUCT_NAME,  "
			+ "     RANKED_PRODUCTS.PRODUCT_CATEGORY,  "
			+ "     RANKED_PRODUCTS.PRODUCT_PRICE,  "
			+ "     RANKED_PRODUCTS.PRODUCT_IMG,  "
			+ "     RANKED_PRODUCTS.WISHLIST_COUNT  "
			+ " FROM (  "
			+ "     SELECT  "
			+ "         P.PRODUCT_ID,  "
			+ "         P.PRODUCT_BRAND,  "
			+ "         P.PRODUCT_NAME,  "
			+ "         P.PRODUCT_CATEGORY,  "
			+ "         P.PRODUCT_PRICE,  "
			+ "         P.PRODUCT_IMG,  "
			+ "         COUNT(W.WISHLIST_ID) AS WISHLIST_COUNT  "
			+ "     FROM PRODUCT P  "
			+ "     LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID  "
			+ "     GROUP BY  "
			+ "         P.PRODUCT_ID,  "
			+ "         P.PRODUCT_BRAND,  "
			+ "         P.PRODUCT_NAME,  "
			+ "         P.PRODUCT_CATEGORY,  "
			+ "         P.PRODUCT_PRICE,  "
			+ "         P.PRODUCT_IMG  "
			+ "     ORDER BY COUNT(W.WISHLIST_ID) DESC "
			+ " ) RANKED_PRODUCTS  "
			+ " LEFT JOIN (  "
			+ "     SELECT PRODUCT_ID  "
			+ "     FROM WISHLIST  "
			+ "     WHERE MEMBER_ID = ? "
			+ " ) WL ON RANKED_PRODUCTS.PRODUCT_ID = WL.PRODUCT_ID "
			+ " WHERE ROWNUM <= 7";

	private static final String SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGOUT = // 인기순 정렬 - 로그아웃상태
			  "  SELECT  "
			  + "   0 AS ISWISHED,  "
			  + "   RANKED_PRODUCTS.WISHLIST_RANK,  "
			  + "   RANKED_PRODUCTS.PRODUCT_ID,  "
			  + "   RANKED_PRODUCTS.PRODUCT_NAME,  "
			  + "   RANKED_PRODUCTS.PRODUCT_BRAND,  "
			  + "   RANKED_PRODUCTS.PRODUCT_PRICE,  "
			  + "   RANKED_PRODUCTS.PRODUCT_CATEGORY,  "
			  + "   RANKED_PRODUCTS.PRODUCT_IMG "
			  + "FROM (  "
			  + "   SELECT  "
			  + "       P.PRODUCT_ID,  "
			  + "       P.PRODUCT_NAME,  "
			  + "       P.PRODUCT_BRAND,  "
			  + "       P.PRODUCT_PRICE,  "
			  + "       P.PRODUCT_CATEGORY,  "
			  + "       P.PRODUCT_IMG,  "
			  + "       ROW_NUMBER() OVER (ORDER BY COUNT(W.WISHLIST_ID) DESC) AS WISHLIST_RANK  "
			  + "   FROM PRODUCT P  "
			  + "   LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID  "
			  + "   GROUP BY  "
			  + "       P.PRODUCT_ID,  "
			  + "       P.PRODUCT_NAME,  "
			  + "       P.PRODUCT_BRAND,  "
			  + "       P.PRODUCT_PRICE,  "
			  + "       P.PRODUCT_CATEGORY,  "
			  + "       P.PRODUCT_IMG  "
			  + "   ORDER BY WISHLIST_RANK DESC NULLS LAST "
			  + ") RANKED_PRODUCTS  "
			  + "WHERE RANKED_PRODUCTS.WISHLIST_RANK <= 7 "
			  + "ORDER BY RANKED_PRODUCTS.WISHLIST_RANK ASC ";
	
	
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
	
	private static final String SELECTALL_WISH_RANKING_BY_AGE = // 연령별 인기순
			"SELECT "
			+ "  AGE_RANGE, "
			+ "  PRODUCT_ID, "
			+ "  PRODUCT_IMG, "
			+ "  PRODUCT_CATEGORY, "
			+ "  PRODUCT_NAME, "
			+ "  PRODUCT_PRICE, "
			+ "  WISH_TOTAL_COUNT "
			+ "FROM ( "
			+ "  SELECT "
			+ "    CASE "
			+ "      WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "      WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "      WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "      WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "      ELSE '50대 이상' "
			+ "    END AS AGE_RANGE, "
			+ "    PRODUCT_ID, "
			+ "    PRODUCT_IMG, "
			+ "    PRODUCT_CATEGORY, "
			+ "    PRODUCT_NAME, "
			+ "    PRODUCT_PRICE, "
			+ "    COUNT(WISHLIST_ID) AS WISH_TOTAL_COUNT "
			+ "  FROM ( "
			+ "    SELECT "
			+ "      M.MEMBER_ID, "
			+ "      P.PRODUCT_ID, "
			+ "      P.PRODUCT_NAME, "
			+ "      P.PRODUCT_CATEGORY, "
			+ "      P.PRODUCT_IMG, "
			+ "      P.PRODUCT_PRICE, "
			+ "      W.WISHLIST_ID, "
			+ "      TRUNC(MONTHS_BETWEEN(SYSDATE, M.MEMBER_BIRTH) / 12) AS AGE "
			+ "    FROM MEMBER M "
			+ "    JOIN WISHLIST W ON M.MEMBER_ID = W.MEMBER_ID "
			+ "    JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "  ) Q "
			+ "  WHERE AGE >= ? AND AGE < ? "
			+ "  GROUP BY "
			+ "    CASE "
			+ "      WHEN AGE >= 10 AND AGE < 20 THEN '10대' "
			+ "      WHEN AGE >= 20 AND AGE < 30 THEN '20대' "
			+ "      WHEN AGE >= 30 AND AGE < 40 THEN '30대' "
			+ "      WHEN AGE >= 40 AND AGE < 50 THEN '40대' "
			+ "      ELSE '50대 이상' "
			+ "    END, PRODUCT_ID, PRODUCT_NAME, PRODUCT_IMG, PRODUCT_CATEGORY, PRODUCT_PRICE "
			+ "  ORDER BY COUNT(WISHLIST_ID) DESC "
			+ ") "
			+ "WHERE ROWNUM <= 6 ";
	
	// 상품별 재고를 가져와서 해당 상품 재고가 0일 경우 상품정보를 흐려지게하는 작업 후 버튼 작동불가하게 함
	// selectOne이 아닌 selectAll에서 재고를 가져와서 
	// 재고가 0인 상품 반복문을 통해 확인해주고 delete기능 수행
	private static final String SELECTALL_WISHLIST_BY_MEMBER = // 회원별 찜 목록 - 위시리스트
			  "SELECT "
			+ "    M.MEMBER_NAME, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_IMG, "
			+ "    P.PRODUCT_STOCK " // 상품재고
			+ "FROM  "
			+ "    WISHLIST W "
			+ "JOIN "
			+ "    MEMBER M ON W.MEMBER_ID = M.MEMBER_ID "
			+ "JOIN "
			+ "    PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE M.MEMBER_ID=? ";
	
	// 회원별 상품찜여부 확인 - 모든상품 로드 (상품목록페이지에서 사용됨)
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
	// 상품 검색에 따른 페이지
	private static final String SELECTALL_WISH_SEARCH = 
			"SELECT "
			+ " p.PRODUCT_ID, "
			+ " PRODUCT_NAME, "
			+ " PRODUCT_BRAND, "
			+ " PRODUCT_PRICE, "
			+ " PRODUCT_INFO, "
			+ " PRODUCT_CATEGORY, "
			+ " PRODUCT_IMG, "
			+ " PRODUCT_STOCK, "
			+ " w.MEMBER_ID, "
			+ " w.WISHLIST_ID "
			+ " FROM PRODUCT p "
			+ " LEFT OUTER JOIN WISHLIST w "
			+ "	ON P.PRODUCT_ID = W.PRODUCT_ID "
			+ "	AND W.MEMBER_ID = ? "
			+ " WHERE UPPER(p.PRODUCT_CATEGORY)"
			+ " LIKE UPPER('%'||?||'%') "
			+ " OR UPPER(p.PRODUCT_NAME) "
			+ " LIKE UPPER('%'||?||'%') ";
	// 카테고리별 상품추천 - 로그아웃상태
//	private static final String SELECTALL_PRODUCT_CATEGORY_WISH_LOGOUT =
//			"SELECT "
//			+ "	0 AS ISWISHED, "
//			+ "	PRODUCT_ID, "
//			+ "	PRODUCT_NAME, "
//			+ "	PRODUCT_CATEGORY, "
//			+ "	PRODUCT_PRICE, "
//			+ "	PRODUCT_IMG "
//			+ "FROM PRODUCT "
//			+ "WHERE PRODUCT_CATEGORY=? "
//			+ "AND ROWNUM <= 4 ";
	
	//카테고리별 상품추천 - 로그인 상태
//	private static final String SELECTALL_PRODUCT_CATEGORY_WISH_LOGIN =
//			"SELECT "
//			+ "    CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END AS ISWISHED, "
//			+ "    P.PRODUCT_ID, "
//			+ "    P.PRODUCT_NAME, "
//			+ "    P.PRODUCT_BRAND, "
//			+ "    P.PRODUCT_CATEGORY, "
//			+ "    P.PRODUCT_PRICE, "
//			+ "    P.PRODUCT_IMG "
//			+ "FROM PRODUCT P "
//			+ "LEFT JOIN WISHLIST W ON P.PRODUCT_ID = W.PRODUCT_ID "
//			+ "AND W.MEMBER_ID = ? "
//			+ "WHERE P.PRODUCT_CATEGORY = ? "
//			+ "AND ROWNUM <= 4";
	
	// 상품상세페이지 - 로그인 상태
	private static final String SELECTONE_PRODUCT_DETAIL_LOGIN = 
			"SELECT "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_STOCK, "
			+ "    P.PRODUCT_IMG, "
			+ "    ( "
			+ "        SELECT COUNT(W.WISHLIST_ID) "
			+ "        FROM WISHLIST W "
			+ "        WHERE W.PRODUCT_ID = P.PRODUCT_ID "
			+ "    ) AS WISH_TOTAL_CNT, " // 상품별 찜 수량확인
			+ "    MAX(CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END) AS ISWISHED "
			+ "FROM "
			+ "    MEMBER M "
			+ "JOIN "
			+ "    PRODUCT P ON P.PRODUCT_ID = ? "
			+ "LEFT JOIN "
			+ "    WISHLIST W ON W.MEMBER_ID = M.MEMBER_ID AND W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE "
			+ "    M.MEMBER_ID = ? "
			+ "GROUP BY "
			+ "    P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_BRAND, P.PRODUCT_PRICE, P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, P.PRODUCT_STOCK, P.PRODUCT_IMG ";
	
	// 상품상세페이지 - 로그아웃 상태
	private static final String SELECTONE_PRODUCT_DETAIL_LOGOUT = 
			"SELECT "
			+ "	0 AS ISWISHED, "
			+ "    P.PRODUCT_ID, "
			+ "    P.PRODUCT_NAME, "
			+ "    P.PRODUCT_BRAND, "
			+ "    P.PRODUCT_PRICE, "
			+ "    P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, "
			+ "    P.PRODUCT_STOCK, "
			+ "    P.PRODUCT_IMG, "
			+ "    ( "
			+ "        SELECT COUNT(W.WISHLIST_ID) "
			+ "        FROM WISHLIST W "
			+ "        WHERE W.PRODUCT_ID = P.PRODUCT_ID "
			+ "    ) AS WISH_TOTAL_CNT, "
			+ "    MAX(CASE WHEN W.PRODUCT_ID IS NOT NULL THEN 1 ELSE 0 END) AS ISWISHED "
			+ "FROM "
			+ "    PRODUCT P "
			+ "LEFT JOIN "
			+ "    WISHLIST W ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE "
			+ "    P.PRODUCT_ID = ? "
			+ "GROUP BY "
			+ "    P.PRODUCT_ID, P.PRODUCT_NAME, P.PRODUCT_BRAND, P.PRODUCT_PRICE, P.PRODUCT_INFO, "
			+ "    P.PRODUCT_CATEGORY, P.PRODUCT_STOCK, P.PRODUCT_IMG ";
	
	// 회원별 위시리스트 수량
	private static final String SELECTONE_WISHLIST_CNT_BY_MEMBER =
			"SELECT COUNT(WISHLIST_ID) AS WISHLIST_CNT FROM WISHLIST WHERE MEMBER_ID=? ";
	
	// 해당상품 위시리스트 존재여부 확인
	private static final String SELECTONE_IS_PRODUCT_IN_WISHLIST =
			"SELECT WISHLIST_ID "
			+ "FROM WISHLIST "
			+ "WHERE MEMBER_ID=? AND PRODUCT_ID=? ";
	
	// 상품별 찜 총 수량
	private static final String SELECTONE_WISH_TOTAL_CNT =
			"SELECT "
			+ "	COUNT(W.WISHLIST_ID) AS WISH_TOTAL_CNT  "
			+ "FROM WISHLIST W "
			+ "JOIN PRODUCT P ON W.PRODUCT_ID = P.PRODUCT_ID "
			+ "WHERE P.PRODUCT_ID=? ";
	
	// 회원 나이 계산 - 연령별 상품추천 로직 밑작업
	private static final String SELECTONE_MEMBER_AGE=
			"SELECT "
			+ "	M.MEMBER_ID, "
			+ "	TRUNC(MONTHS_BETWEEN(SYSDATE, M.MEMBER_BIrTH)/12) AS AGE "
			+ "FROM MEMBER M "
			+ "WHERE M.MEMBER_ID= ? ";
	
	// 상품상세페이지 찜 여부 확인
	private static final String SELECTONE_CHECK_ISWISHED=
			  "SELECT "
			+ "    CASE WHEN WL.PRODUCT_ID IS NULL THEN 0 ELSE 1 END AS ISWISHED "
			+ "FROM "
			+ "    MEMBER M "
			+ "LEFT JOIN "
			+ "    WISHLIST WL ON M.MEMBER_ID = WL.MEMBER_ID AND WL.PRODUCT_ID = ? "
			+ "WHERE "
			+ "    M.MEMBER_ID = ? ";
	
	// KOD사이트에 가입한 회원들 중 가장 많은 나이대가 속한 연령대 상품추천로직 밑작업 - 상품상세페이지 하단에서 구현, 로그아웃상태
	private static final String SELECTONE_MOST_AGE_RANGE=
			"SELECT "
			+ "    AGE_RANGE, "
			+ "    MEMBER_COUNT "
			+ "FROM ( "
			+ "    SELECT "
			+ "        AGE_RANGE, "
			+ "        MEMBER_COUNT, "
			+ "        RANK() OVER (ORDER BY MIN_AGE ASC) AS RANK_ORDER " // 연령별 회원 수가 같은 경우, 나이가 적은 연령이 상위에 랭크될 수 있게 하기 위함
			+ "    FROM ( "
			+ "        SELECT "
			+ "            CASE "
			+ "                WHEN AGE >= 10 AND AGE < 20 THEN 10 "
			+ "                WHEN AGE >= 20 AND AGE < 30 THEN 20 "
			+ "                WHEN AGE >= 30 AND AGE < 40 THEN 30 "
			+ "                WHEN AGE >= 40 AND AGE < 50 THEN 40 "
			+ "                ELSE 50 "
			+ "            END AS AGE_RANGE, "
			+ "            COUNT(*) AS MEMBER_COUNT, "  // 연령별 회원 수
			+ "            MIN(AGE) AS MIN_AGE " // 나이가 적은 순으로 정렬하기 위한 변수
			+ "        FROM ( "
			+ "            SELECT "
			+ "                MEMBER_ID, "
			+ "                TRUNC(MONTHS_BETWEEN(SYSDATE, MEMBER_BIRTH) / 12) AS AGE "
			+ "            FROM "
			+ "                MEMBER "
			+ "        ) AGE_DATA "
			+ "        GROUP BY "
			+ "            CASE "
			+ "                WHEN AGE >= 10 AND AGE < 20 THEN 10 "
			+ "                WHEN AGE >= 20 AND AGE < 30 THEN 20 "
			+ "                WHEN AGE >= 30 AND AGE < 40 THEN 30 "
			+ "                WHEN AGE >= 40 AND AGE < 50 THEN 40 "
			+ "                ELSE 50 "
			+ "            END "
			+ "    ) "
			+ ") "
			+ "WHERE RANK_ORDER = 1 ";
	
	// 찜 하기
	private static final String INSERT_WISHLIST_BY_PRODUCT = 
			"INSERT INTO WISHLIST (WISHLIST_ID,MEMBER_ID, PRODUCT_ID) "
					+ "VALUES ((SELECT NVL(MAX(WISHLIST_ID),0)+1 FROM WISHLIST),?, ?) ";
	// 찜 삭제
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
				System.out.println("[로그 : 정현진] DAO, 회원ID : "+wishListDTO.getMemberID());
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
					data.setProductStock(rs.getInt("PRODUCT_STOCK"));
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
		else if(wishListDTO.getSearchCondition().equals("인기상품LOGIN")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGIN);
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
		else if(wishListDTO.getSearchCondition().equals("인기상품LOGOUT")) {
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_RANKING_BY_PRODUCTS_LOGOUT);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setIsWished(rs.getInt("ISWISHED"));
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
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_RANKING_BY_AGE);
				pstmt.setInt(1, wishListDTO.getMemberMinAge());
				pstmt.setInt(2, wishListDTO.getMemberMaxAge());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
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
//		else if(wishListDTO.getSearchCondition().equals("연관상품LOGIN")) {
//			System.out.println("DAO,연관상품LOGIN 들어옴");
//			conn=JDBCUtil.connect();
//			try {
//				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_PRODUCT_CATEGORY_WISH_LOGIN);
//				System.out.println("쿼리실행됨?");
//				pstmt.setString(1, wishListDTO.getMemberID());
//				pstmt.setString(2, wishListDTO.getProductCategory());
//				ResultSet rs = pstmt.executeQuery();
//				System.out.println("됨");
//				while(rs.next()) {
//					System.out.println("rs.next()들어옴");
//					WishListDTO data = new WishListDTO();
//					data.setIsWished(rs.getInt("ISWISHED"));
//					data.setProductID(rs.getInt("PRODUCT_ID"));
//					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
//					data.setProductName(rs.getString("PRODUCT_NAME"));
//					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
//					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
//					data.setProductImg(rs.getString("PRODUCT_IMG"));
//					datas.add(data);
//				}
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				JDBCUtil.disconnect(pstmt, conn);
//			}
//			return datas;
//		}
		else if(wishListDTO.getSearchCondition().equals("연관상품LOGIN버전2")) {
			System.out.println("연관상품LOGIN버전2 들어옴 @@@@");
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_RANKING_BY_AGE);
				pstmt.setInt(1, wishListDTO.getMemberMinAge());
				pstmt.setInt(2, wishListDTO.getMemberMaxAge());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					datas.add(data);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			System.out.println("[로그]datas.size()"+datas.size());
			int productID=wishListDTO.getProductID();
			for (int i = 0; i < datas.size(); i++) {
				System.out.println("위시DTO가 받아온 ProductID"+productID);
				if(datas.get(i).getProductID()==productID) {
					System.out.println("datas"+i+"번째 인덱스 삭제됨");
					datas.remove(i);
					break; 
				}
				System.out.println("몇번째"+i);
			}
//			int i=0;
//			while(true) {
//				if(datas.get(i).getProductID()==productID) {
//					datas.remove(i);
//					break;
//				}
//				i++;
//			}
			return datas;
			/*
			 * 해당 코드의 특징
			 * SELECTALL_WISH_RANKING_BY_AGE 쿼리는 연령별 추천상품을 조회하는 쿼리로서
			 * 상품을 선택하면 이동되는 상품상세페이지에서 로그인한 회원의 나이대에 해당하는
			 * 회원들이 찜을 많이 한 순서로 상품을 조회하는 로직으로 구현되어 있습니다.
			 * 상품상세페이지에서 보여질 추천상품 리스트들은 현재 보고있는 상품의 정보를 제외하고 
			 * 보여주기위해 for안에 if문을 사용하여 데이터를 반환하도록 구현하였습니다.
			 * 여기서 주의 할 점은 if문이 참이었을 때, remove 함수를 실행하게 되면
			 * i번째 인덱스가 삭제되면서 for문의 반복조건이 변경되어 삭제된 횟수만큼 
			 * 반복을 수행하지 못하는 오류가 발생하게됩니다.
			 * 해당 오류를 해결하기 위해서는 
			 * 리스트 내에 중복상품이 여러개일 경우 while문을 사용하거나
			 * 중복된 상품이 없을 경우엔 for문을 사용하여 if문인 참인 조건을 실행하였다면
			 * break 예약어를 사용하여 불필요한 반복을 피하고 코드실행을 효율적으로 관리하기 위함입니다. 
			 */
			/*
			 * 쿼리를 조회한 후 조건문을 사용한 이유는 현재 우리의 프로그램이 데이터의 양이 많지 않아 
			 * 데이터베이스의 부하가 크지 않고 다양한 조건이 필요한 경우가 아니라서 구현된 로직입니다.
			 * 만약 데이터의 양이 많아서 데이터베이스의 과부하가 우려되는 상황이나
			 * 여러가지 조건이 유동적으로 자주 변화해야 되는 로직이라면
			 * 조건에 맞게 쿼리를 변경하여 필요한 데이터만 가져오게하여 데이터베이스의 액세스 시간을 단축시켜
			 * 과부하를 줄일 수 있습니다. 
			 */
		}
		else if(wishListDTO.getSearchCondition().equals("연관상품LOGOUT스텝2")) {
			if(wishListDTO.getMemberAge()==10) {
				wishListDTO.setMemberMinAge(10);
				wishListDTO.setMemberMaxAge(20);
			}
			else if(wishListDTO.getMemberAge()==20) {
				wishListDTO.setMemberMinAge(20);
				wishListDTO.setMemberMaxAge(30);
			}
			else if(wishListDTO.getMemberAge()==30) {
				wishListDTO.setMemberMinAge(30);
				wishListDTO.setMemberMaxAge(40);
			}
			else if(wishListDTO.getMemberAge()==40) {
				wishListDTO.setMemberMinAge(40);
				wishListDTO.setMemberMaxAge(50);
			}
			else {
				wishListDTO.setMemberMinAge(50);
				wishListDTO.setMemberMaxAge(60);
			}
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_RANKING_BY_AGE);
				pstmt.setInt(1, wishListDTO.getMemberMinAge());
				pstmt.setInt(2, wishListDTO.getMemberMaxAge());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					System.out.println("로그 pName : "+rs.getString("PRODUCT_NAME"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					datas.add(data);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			System.out.println("로그datas.size()"+datas.size());
			int productID=wishListDTO.getProductID();
			for (int i = 0; i < datas.size(); i++) {
				System.out.println("위시DTO가 받아온 ProductID"+productID);
				if(datas.get(i).getProductID()==productID) {
					System.out.println("datas"+i+"번째 인덱스 삭제됨");
					datas.remove(i);
					break; 
				}
				System.out.println("몇번째"+i);
			}
			return datas;
		}
		else if(wishListDTO.getSearchCondition().equals("search")) { // 김진영
			System.out.println("검색 DAO들어옴");
			conn=JDBCUtil.connect();
			try {
				PreparedStatement pstmt = conn.prepareStatement(SELECTALL_WISH_SEARCH);
				pstmt.setString(1, wishListDTO.getMemberID());
				pstmt.setString(2, wishListDTO.getSearchKeyword());
				pstmt.setString(3, wishListDTO.getSearchKeyword());
				System.out.println(wishListDTO.getSearchKeyword());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					WishListDTO data = new WishListDTO();
					data.setIsWished(rs.getInt("WISHLIST_ID"));
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					datas.add(data);
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			System.out.println("로그datas.size()"+datas.size());
			int productID=wishListDTO.getProductID();
			for (int i = 0; i < datas.size(); i++) {
				System.out.println("위시DTO가 받아온 ProductID"+productID);
				if(datas.get(i).getProductID()==productID) {
					System.out.println("datas"+i+"번째 인덱스 삭제됨");
					datas.remove(i);
					break; 
				}
				System.out.println("몇번째"+i);
			}
//			int i=0;
//			while(true) {
//				if(datas.get(i).getProductID()==productID) {
//					datas.remove(i);
//					break;
//				}
//				i++;
//			}
			return datas;
		}else {
			return null;
		}
	}
	
	public WishListDTO selectOne(WishListDTO wishListDTO) {
		conn=JDBCUtil.connect();
		WishListDTO data=null;
		if(wishListDTO.getSearchCondition().equals("위시리스트추가삭제")) {
		try {
			pstmt=conn.prepareStatement(SELECTONE_IS_PRODUCT_IN_WISHLIST);
			System.out.println("[로그 : 정현진] DAO, 위시리스트 추가삭제");
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
					System.out.println("DAO찜수량들어옴\n위시리스트수량 >> "+rs.getInt("WISHLIST_CNT"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("찜합계")) {
			try {
				pstmt=conn.prepareStatement(SELECTONE_WISH_TOTAL_CNT);
				pstmt.setInt(1, wishListDTO.getProductID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data=new WishListDTO();
					data.setWishTotalCnt(rs.getInt("WISH_TOTAL_CNT"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("상품상세페이지LOGIN")) {
			try {
				pstmt=conn.prepareStatement(SELECTONE_PRODUCT_DETAIL_LOGIN);
				pstmt.setInt(1, wishListDTO.getProductID());
				pstmt.setString(2, wishListDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductInfo(rs.getString("PRODUCT_INFO"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setIsWished(rs.getInt("ISWISHED"));
					data.setProductStock(rs.getInt("PRODUCT_STOCK"));
					data.setWishTotalCnt(rs.getInt("WISH_TOTAL_CNT"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("상품상세페이지LOGOUT")) {
			System.out.println("DAO, 상품상세페이지LOGOUT 들어옴");
			try {	
				pstmt=conn.prepareStatement(SELECTONE_PRODUCT_DETAIL_LOGOUT);
				pstmt.setInt(1, wishListDTO.getProductID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new WishListDTO();
					data.setProductID(rs.getInt("PRODUCT_ID"));
					data.setProductBrand(rs.getString("PRODUCT_BRAND"));
					data.setProductName(rs.getString("PRODUCT_NAME"));
					data.setProductInfo(rs.getString("PRODUCT_INFO"));
					data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
					data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
					data.setProductImg(rs.getString("PRODUCT_IMG"));
					data.setProductStock(rs.getInt("PRODUCT_STOCK"));
					data.setIsWished(rs.getInt("ISWISHED"));
					data.setWishTotalCnt(rs.getInt("WISH_TOTAL_CNT"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("나이")) {
			try {
				pstmt = conn.prepareStatement(SELECTONE_MEMBER_AGE);
				pstmt.setString(1, wishListDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					System.out.println("wishDAO 나이 들어옴");
					int age = rs.getInt("AGE");
					System.out.println("회원나이 : "+age);
					data = new WishListDTO();
					if(10<=age&&age<20) {
						data.setMemberMinAge(10);
						data.setMemberMaxAge(20);
					}
					else if(20<=age&&age<20) {
						data.setMemberMinAge(20);
						data.setMemberMaxAge(30);
					}
					else if(30<=age&&age<40) {
						data.setMemberMinAge(30);
						data.setMemberMaxAge(40);
					}
					else {
						data.setMemberMinAge(40);
						data.setMemberMaxAge(50);
					}
					System.out.println("minAge : "+data.getMemberMinAge());
					System.out.println("maxAge : "+data.getMemberMaxAge());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("찜여부")) {
			try {
				pstmt=conn.prepareStatement(SELECTONE_CHECK_ISWISHED);
				pstmt.setInt(1, wishListDTO.getProductID());
				pstmt.setString(2, wishListDTO.getMemberID());
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data = new WishListDTO();
					data.setIsWished(rs.getInt("ISWISHED"));
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
			return data;
		}
		else if(wishListDTO.getSearchCondition().equals("연관상품LOGOUT스텝1")) {
			System.out.println("DAO, 연관상품LOGOUT스텝1 들어옴");
			try {
				pstmt=conn.prepareStatement(SELECTONE_MOST_AGE_RANGE);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					data=new WishListDTO();
					data.setMemberAge(rs.getInt("AGE_RANGE"));
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
