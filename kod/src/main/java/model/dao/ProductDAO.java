package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.ProductDTO;
import model.util.JDBCUtil;

public class ProductDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SELECTALL = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_STOCK, PRODUCT_IMG "
			+ " FROM PRODUCT";
	private static final String SELECTALL_CATEGORY = "SELECT PRODUCT_CATEGORY, COUNT(PRODUCT_CATEGORY) AS COUNT"
			+ " FROM PRODUCT GROUP BY PRODUCT_CATEGORY";
	private static final String SELECTALL_CHIOCE = 
			" SELECT p.PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, "
			+ " PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, "
			+ " PRODUCT_STOCK, PRODUCT_IMG, NVL(w.WISHLIST_ID,0) AS ISWISHED "
			+ " FROM PRODUCT p LEFT OUTER JOIN WISHLIST w ON p.PRODUCT_ID = w.PRODUCT_ID AND MEMBER_ID = ";
	private static final String SELECTONE = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_STOCK, PRODUCT_IMG "
			+ "FROM PRODUCT WHERE PRODUCT_ID=?";
	private static final String SELECTONE_CHOICE = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_STOCK, PRODUCT_IMG "
			+ " FROM PRODUCT ";
	private static final String INSERT_CRAWLING = 
			  "INSERT "
			+ "INTO PRODUCT( "
			+ "  PRODUCT_ID, "
			+ "  PRODUCT_NAME, "
			+ "  PRODUCT_BRAND, "
			+ "  PRODUCT_PRICE, "
			+ "  PRODUCT_INFO, "
			+ "  PRODUCT_CATEGORY,  "
			+ "  PRODUCT_STOCK,  "
			+ "  PRODUCT_IMG) "
			+ "VALUES(" // 이름 가격 정보 카테고리 수량 이미지
			+ "  (SELECT NVL(MAX(PRODUCT_ID),1000)+1 FROM PRODUCT), "
			+ "?,'Bang&Olufsen',?,?,?,5,?)"; // 상품 크롤링
			
	private static final String UPDATE = "";
	private static final String DELETE = "";

	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO data = new ProductDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductCnt(rs.getInt("PRODUCT_STOCK"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductInfo(rs.getString("PRODUCT_INFO"));
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
	
	public ArrayList<ProductDTO> selectCategory(ProductDTO pDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn = JDBCUtil.connect();
		try {
			// [김진영] 필터검색 조건에 맞게 쿼리를 변경하기 위한 작업
			String result = "";
			// [김진영]로그인 유무에 따른 조건식
			if(pDTO.getMemberID()!=null) {
				result += " '"+pDTO.getMemberID()+ "' ";
			}else {
				result += " 'NULL' ";
			}
			// [김진영] 요청에서 가격은 항상 받기에 if조건문 미사용 
			System.out.println("리스트 길이 : "+pDTO.getCategoryList().length);
			System.out.println("배열 내용 : " + pDTO.getCategoryList().toString());
			result += " WHERE ( PRODUCT_PRICE BETWEEN " + pDTO.getMin() + " AND " + pDTO.getMax() + " ) ";
			if(!pDTO.getCategoryList()[0].equals("")) {
				result += " AND ( PRODUCT_CATEGORY = " + " '"+ pDTO.getCategoryList()[0] + "' ";
				for (int i = 1; i < pDTO.getCategoryList().length; i++) {
					result += " OR PRODUCT_CATEGORY = " + " '" + pDTO.getCategoryList()[i] + "' " ;
				}
				result += " ) ";
			}
			result = SELECTALL_CHIOCE+result;
			// [김진영] 완성된 쿼리를 확인
			System.out.println(result);
			pstmt = conn.prepareStatement(result);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				ProductDTO data = new ProductDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductStock(rs.getInt("PRODUCT_STOCK"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductInfo(rs.getString("PRODUCT_INFO"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
				data.setIsWished(rs.getInt("ISWISHED"));
				System.out.println(data);
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

	public ArrayList<ProductDTO> selectAllCategory(ProductDTO pDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL_CATEGORY);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO data = new ProductDTO();
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductCnt(rs.getInt("COUNT"));
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

	public ProductDTO selectOne(ProductDTO pDTO) {
		ProductDTO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE);
			System.out.println(pDTO);
			pstmt.setInt(1, pDTO.getProductID());
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new ProductDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductCnt(rs.getInt("PRODUCT_STOCK"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductInfo(rs.getString("PRODUCT_INFO"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
				System.out.println(data);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public ProductDTO selectOneChoice(ProductDTO pDTO) {
		ProductDTO data = null;
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTONE_CHOICE);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new ProductDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductCnt(rs.getInt("PRODUCT_STOCK"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductInfo(rs.getString("PRODUCT_INFO"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}

	public boolean insert(ProductDTO productDTO) {
		if(productDTO.getSearchCondition().equals("크롤링")) {
			conn=JDBCUtil.connect();
			try {
				pstmt = conn.prepareStatement(INSERT_CRAWLING);
				pstmt.setString(1, productDTO.getProductName());
				pstmt.setInt(2, productDTO.getProductPrice());
				pstmt.setString(3, productDTO.getProductInfo());
				pstmt.setString(4, productDTO.getProductCategory());
				pstmt.setString(5, productDTO.getProductImg());
				int result = pstmt.executeUpdate();
				if(result <= 0) {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCUtil.disconnect(pstmt, conn);
			}
		}
		else {
			return false;
		}
		return true;
	}

	public void update() {

	}

	public void delete() {

	}
}
