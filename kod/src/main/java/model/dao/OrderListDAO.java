package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dto.MemberDTO;
import model.dto.OrderListDTO;
import model.util.JDBCUtil;

public class OrderListDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="";
	private static final String SELECTONE="SELECT NVL(MAX(ORDERLIST_ID),1) AS MAX_ID FROM ORDERLIST";
	private static final String INSERT="INSERT INTO ORDERLIST "
			+ " (ORDERLIST_ID, MEMBER_ID, ORDERLIST_DATE) "
			+ " VALUES((SELECT NVL(MAX(ORDERLIST_ID),0)+1 FROM ORDERLIST), ?, SYSDATE)";
	private static final String UPDATE="";
	private static final String DELETE="";
	
	public void selectAll() {
		
	}
	public OrderListDTO selectOne(OrderListDTO oDTO) {
		OrderListDTO data=null;

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTONE);
			ResultSet rs=pstmt.executeQuery();

			if(rs.next()) {
				data=new OrderListDTO();
				data.setOdListID(rs.getInt("MAX_ID"));
			}

			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}

		return data;
	}
	public boolean insert(OrderListDTO oDTO) {
		conn=JDBCUtil.connect();	// JDBC 연결
		try {
			pstmt=conn.prepareStatement(INSERT);	// INSERT 쿼리 사용
			pstmt.setString(1, oDTO.getMemberID());		// 쿼리의 1번째 ?(mId)에 oDTO.getmId값 넣기 
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
