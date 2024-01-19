package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.dto.OrderContentDTO;
import model.util.JDBCUtil;

public class OrderContentDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	private static final String SELECTONE="";
	private static final String INSERT="INSERT INTO ORDERCONTENT "
			+ " (ORDERCONTENT_ID, ORDERLIST_ID, PRODUCT_ID, ORDERCONTENT_CNT) "
			+ " VALUES((SELECT NVL(MAX(ORDERCONTENT_ID),0)+1 FROM ORDERCONTENT), ?, ?, ?)";
	private static final String UPDATE="";
	private static final String DELETE="";
	
	public void selectAll() {
		
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
