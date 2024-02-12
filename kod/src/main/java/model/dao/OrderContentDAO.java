package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.OrderContentDTO;
import model.util.JDBCUtil;

public class OrderContentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="SELECT OC.ORDERCONTENT_ID ,OC.ORDERLIST_ID, OC.ORDERCONTENT_CNT, OC.PRODUCT_ID, P.PRODUCT_IMG, P.PRODUCT_NAME, P.PRODUCT_CATEGORY, P.PRODUCT_PRICE FROM ORDERCONTENT OC INNER JOIN PRODUCT P ON P.PRODUCT_ID = OC.PRODUCT_ID WHERE OC.ORDERLIST_ID = ?";
	private static final String SELECTTOP3="SELECT r.PRODUCT_ID , r.PRODUCT_IMG , r.PRODUCT_CATEGORY , r.PRODUCT_NAME , r.PRODUCT_PRICE, r.TOTAL "
			+ "FROM "
			+ "(SELECT p.PRODUCT_ID , p.PRODUCT_IMG , p.PRODUCT_CATEGORY , p.PRODUCT_NAME ,p.PRODUCT_PRICE, SUM(oc.ORDERCONTENT_CNT) AS TOTAL "
			+ "FROM PRODUCT p "
			+ "INNER JOIN ORDERCONTENT oc ON p.PRODUCT_ID = oc.PRODUCT_ID "
			+ "GROUP BY p.PRODUCT_ID ,p.PRODUCT_IMG , p.PRODUCT_CATEGORY , p.PRODUCT_NAME ,p.PRODUCT_PRICE "
			+ "ORDER BY TOTAL DESC) r "
			+ "WHERE ROWNUM <=3";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO ORDERCONTENT "
			+ " (ORDERCONTENT_ID, ORDERLIST_ID, PRODUCT_ID, ORDERCONTENT_CNT) "
			+ " VALUES((SELECT NVL(MAX(ORDERCONTENT_ID),0)+1 FROM ORDERCONTENT), ?, ?, ?)";
	private static final String UPDATE="";
	private static final String DELETE="";
	
	public ArrayList<OrderContentDTO> selectAll(OrderContentDTO oContentDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		OrderContentDTO data = null;
		ArrayList<OrderContentDTO> datas = new ArrayList<OrderContentDTO>();
		try {
			if(oContentDTO.getSearchCondition().equals("top3")) { // 판매량 순위 3위까지 선택
				pstmt=conn.prepareStatement(SELECTTOP3);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
				data = new OrderContentDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				datas.add(data);
				System.out.println(datas);
				}
			}
			else if(oContentDTO.getSearchCondition().equals("결제내역")) {
				pstmt=conn.prepareStatement(SELECTALL);
				pstmt.setInt(1, oContentDTO.getOdListID());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
				data = new OrderContentDTO();
				data.setProductID(rs.getInt("PRODUCT_ID"));
				data.setProductCategory(rs.getString("PRODUCT_CATEGORY"));
				data.setProductImg(rs.getString("PRODUCT_IMG"));
				data.setProductName(rs.getString("PRODUCT_NAME"));
				data.setProductPrice(rs.getInt("PRODUCT_PRICE"));
				data.setOdContentCnt(rs.getInt("ORDERCONTENT_CNT"));
				data.setOdListID(rs.getInt("ORDERLIST_ID"));
				data.setOdContentID(rs.getInt("ORDERCONTENT_ID"));
				datas.add(data);
				System.out.println(datas);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return datas;
	}
	public void selectOne() {
		
	}
	public boolean insert(OrderContentDTO oContentDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(INSERT);	// INSERT 쿼리 사용
			pstmt.setInt(1, oContentDTO.getOdListID());
			pstmt.setInt(2, oContentDTO.getProductID());
			pstmt.setInt(3, oContentDTO.getOdContentCnt());
			int result=pstmt.executeUpdate();		// executeUpdate 결과값 result에 저장 / 성공이면 1, 실패면 0
			if(result<=0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);	// JDBC 연결해제
		}
		return true;
	}
	public void update() {
		
	}
	public void delete() {
		
	}
}
