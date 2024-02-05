package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AddressDTO;
import model.dto.CartDTO;
import model.dto.MemberDTO;
import model.util.JDBCUtil;

public class CartDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="SELECT C.CART_ID, C.PRODUCT_ID, P.PRODUCT_IMG, P.PRODUCT_NAME, P.PRODUCT_PRICE ,SUM(P.PRODUCT_PRICE*CART_PRODUCT_CNT) AS SUM_PRODUCT_PRICE, C.MEMBER_ID , SUM(C.CART_PRODUCT_CNT) AS CART_PRODUCT_CNT FROM CART C "
			+ "	INNER JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID "
			+ "	WHERE C.MEMBER_ID = ? "
			+ "	GROUP BY C.CART_ID,C.PRODUCT_ID,P.PRODUCT_IMG, P.PRODUCT_NAME, P.PRODUCT_PRICE, C.MEMBER_ID";
	private static final String SELECTONE="SELECT C.PRODUCT_ID , P.PRODUCT_NAME , P.PRODUCT_IMG, C.MEMBER_ID ,SUM(C.CART_PRODUCT_CNT) AS CART_PRODUCT_CNT  ,SUM(P.PRODUCT_PRICE*CART_PRODUCT_CNT) AS PRODUCT_PRICE  FROM CART C "
			+ "	INNER JOIN PRODUCT P ON P.PRODUCT_ID = C.PRODUCT_ID "
			+ "	WHERE C.MEMBER_ID = ? AND C.PRODUCT_ID = ? "
			+ " GROUP BY C.PRODUCT_ID ,P.PRODUCT_NAME , P.PRODUCT_IMG, C.MEMBER_ID ,P.PRODUCT_PRICE, C.CART_PRODUCT_CNT";
	private static final String INSERT="INSERT INTO CART VALUES ((SELECT NVL(MAX(CART_ID),0)+1 FROM CART), ?, ?, ?)";
	private static final String UPDATE="UPDATE CART SET CART_PRODUCT_CNT = ? WHERE PRODUCT_ID = ? AND MEMBER_ID = ?";
	private static final String DELETE="DELETE FROM CART WHERE CART_ID = ?";
	private static final String DELETE_ALL="DELETE FROM CART WHERE MEMBER_ID = ?";
	
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
				data.setSumProductPrice(rs.getInt("SUM_PRODUCT_PRICE"));
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
	public CartDTO selectOne(CartDTO cDTO) {
		System.out.println("멤버아이디 : "+cDTO.getMemberID());
		System.out.println("상품번호 : "+cDTO.getProductID());
		CartDTO data=null;

        conn=JDBCUtil.connect();
        try {
        	pstmt=conn.prepareStatement(SELECTONE);
        	pstmt.setString(1, cDTO.getMemberID());
        	pstmt.setInt(2, cDTO.getProductID());
            ResultSet rs=pstmt.executeQuery();

            if(rs.next()) {
            	System.out.println("rs로그");
                data=new CartDTO();
                //data.setCartID(rs.getInt("CART_ID"));
				data.setCartProductCnt(rs.getInt("CART_PRODUCT_CNT"));
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
            }
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.disconnect(pstmt, conn);
        }
        return data;
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
	public boolean update(CartDTO cDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setInt(1, cDTO.getCartProductCnt());
			pstmt.setInt(2, cDTO.getProductID());
			pstmt.setString(3, cDTO.getMemberID());
			
			int rs = pstmt.executeUpdate();

			if (rs <= 0) {
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
	
	public boolean delete(CartDTO cDTO) {
		conn = JDBCUtil.connect();
		try {
			if(cDTO.getSearchCondition().equals("장바구니비우기")) {
			pstmt = conn.prepareStatement(DELETE_ALL);
			pstmt.setString(1, cDTO.getMemberID());
			}
			else if(cDTO.getSearchCondition().equals("개별상품삭제")) {
				pstmt=conn.prepareStatement(DELETE);
				pstmt.setInt(1, cDTO.getCartID());
				
			}
			int rs = pstmt.executeUpdate();
			if (rs <= 0) {
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
}
