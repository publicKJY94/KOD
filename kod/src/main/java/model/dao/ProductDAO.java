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

	private static final String SELECTALL = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
			+ " FROM PRODUCT";
	private static final String SELECTALLCATEGORY = "SELECT PRODUCT_CATEGORY, COUNT(PRODUCT_CATEGORY) AS COUNT"
			+ " FROM PRODUCT GROUP BY PRODUCT_CATEGORY";
	private static final String SELECTALLCHIOCE = "SELECT * " + " FROM PRODUCT WHERE PRODUCT_CATEGORY = ? ?";
	private static final String SELECTONE = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
			+ "FROM PRODUCT WHERE PRODUCT_ID=?";
	private static final String SELECTONE_CHOICE = "SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
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
			+ "  PRODUCT_CNT,  "
			+ "  PRODUCT_IMG) "
			+ "VALUES(" // 이름 가격 정보 카테고리 수량 이미지
			+ "  (SELECT NVL(MAX(PRODUCT_ID),1000)+1 FROM PRODUCT), "
			+ "?,'Bang&Olufsen',?,?,'헤드폰',5,?)"; // 상품 크롤링
			
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
				data.setProductCnt(rs.getInt("PRODUCT_CNT"));
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
			pstmt = conn.prepareStatement(SELECTALLCHIOCE);
			pstmt.setString(1, pDTO.getCategoryList()[0]);
			String result = "";
			if (pDTO.getCategoryList().length <= 1) {
				for (int i = 1; i < pDTO.getCategoryList().length; i++) {
					result += " OR PRODUCT_CATEGORY =" + pDTO.getCategoryList()[i];
				}
			}
			pstmt.setString(2, result);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ProductDTO data = new ProductDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductBrand(rs.getString("PRODUCT_BRAND"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setProductCnt(rs.getInt("PRODUCT_CNT"));
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

	public ArrayList<ProductDTO> selectAllCategory(ProductDTO pDTO) {
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALLCATEGORY);
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
				data.setProductCnt(rs.getInt("PRODUCT_CNT"));
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
				data.setProductCnt(rs.getInt("PRODUCT_CNT"));
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
				pstmt.setString(4, productDTO.getProductImg());
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
