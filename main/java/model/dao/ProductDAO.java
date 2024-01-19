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
	
	private static final String SELECTALL="SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
			+ " FROM PRODUCT";
	private static final String SELECTALLCATEGORY="SELECT PRODUCT_CATEGORY, COUNT(PRODUCT_CATEGORY) AS COUNT"
			+ " FROM PRODUCT GROUP BY PRODUCT_CATEGORY";
	private static final String SELECTALLchoice="SELECT * "
			+ " FROM PRODUCT WHERE PRODUCT_CATEGORY = ?"
			+ " OR PRODUCT_CATEGORY = ?";
	private static final String SELECTONE="SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
			+ "FROM PRODUCT WHERE PRODUCT_ID=?";
	private static final String SELECTONE_CHOICE="SELECT PRODUCT_ID, PRODUCT_NAME, PRODUCT_BRAND, PRODUCT_PRICE, PRODUCT_INFO, PRODUCT_CATEGORY, PRODUCT_CNT, PRODUCT_IMG "
			+ " FROM PRODUCT ";
	private static final String INSERT="";
	private static final String UPDATE="";
	private static final String DELETE="";
	
	public ArrayList<ProductDTO> selectAll(ProductDTO pDTO){ 
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO data=new ProductDTO();
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
	public ArrayList<ProductDTO> selectAllCategory(ProductDTO pDTO){ 
		ArrayList<ProductDTO> datas = new ArrayList<ProductDTO>();
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALLCATEGORY);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductDTO data=new ProductDTO();
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
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE);
			System.out.println(pDTO);
			pstmt.setInt(1, pDTO.getProductID());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new ProductDTO();
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
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE_CHOICE);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				data=new ProductDTO();
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
	public void insert() {
		
	}
	public void update() {
		
	}
	public void delete() {
		
	}
}
