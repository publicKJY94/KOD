package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.CartDTO;
import model.util.JDBCUtil;

public class CartDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="SELECT * FROM CART C INNER JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID WHERE MEMBER_ID=?";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO CART VALUES ((SELECT NVL(MAX(CART_ID),1)+1 FROM CART), ?, ?, ?)";
	private static final String UPDATE="";
	private static final String DELETE="";
	
	public ArrayList<CartDTO> selectAll(CartDTO cDTO) {
		ArrayList<CartDTO> datas=new ArrayList<CartDTO>();

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);
			pstmt.setString(1, cDTO.getMemberID());
			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				CartDTO data=new CartDTO();
				data.setCartID(rs.getInt("CART_ID"));
				data.setCartProductCnt(rs.getInt("CART_PRODUCT_CNT"));
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
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
	public void selectOne() {
		
	}
	public boolean insert(CartDTO cDTO) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(INSERT);
			pstmt.setInt(1, cDTO.getCartProductCnt());
			pstmt.setString(2, cDTO.getMemberID());
			pstmt.setInt(3, cDTO.getProductID());
			int rs=pstmt.executeUpdate();
			if(rs<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return true;
	}
	public void update() {
		
	}
	public void delete() {
		
	}
}
