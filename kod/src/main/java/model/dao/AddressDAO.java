package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.AddressDTO;
import model.util.JDBCUtil;

public class AddressDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private static final String SELECTALL = "SELECT * FROM ADDRESS WHERE MEMBER_ID=?";
	private static final String SELECTONE = "SELECT * FROM ADDRESS WHERE ADDRESS_ID=? ";
	private static final String SELECTONE_CARTINFO="SELECT * "
			+ " FROM ( "
			+ "    SELECT * "
			+ "    FROM ADDRESS "
			+ "    WHERE MEMBER_ID = ? "
			+ "    ORDER BY ADDRESS_ID "
			+ " ) "
			+ " WHERE ROWNUM = 1 ";
	private static final String INSERT = "INSERT INTO ADDRESS(ADDRESS_ID, ADDRESS_NAME, ADDRESS_STREET, ADDRESS_LOTNUM, ADDRESS_DETAIL, ADDRESS_ZIPCODE, MEMBER_ID) VALUES((SELECT NVL(MAX(ADDRESS_ID),0)+1 FROM ADDRESS),?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE ADDRESS SET ADDRESS_NAME=?, ADDRESS_STREET=?, ADDRESS_LOTNUM=?, ADDRESS_DETAIL=?, ADDRESS_ZIPCODE=? WHERE ADDRESS_ID=?";
	private static final String DELETE = "DELETE FROM ADDRESS WHERE ADDRESS_ID=?";

	public ArrayList<AddressDTO> selectAll(AddressDTO aDTO) {
		ArrayList<AddressDTO> datas = new ArrayList<AddressDTO>();	

		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(SELECTALL);
			pstmt.setString(1, aDTO.getMemberID());			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				AddressDTO data = new AddressDTO();
				data.setAdrsID(rs.getInt("ADDRESS_ID"));
				data.setAdrsName(rs.getString("ADDRESS_NAME"));
				data.setAdrsStreet(rs.getString("ADDRESS_STREET"));
				data.setAdrsLotNum(rs.getString("ADDRESS_LOTNUM"));
				data.setAdrsDetail(rs.getString("ADDRESS_DETAIL"));
				data.setAdrsZipcode(rs.getString("ADDRESS_ZIPCODE"));
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

	public AddressDTO selectOne(AddressDTO aDTO) {
		AddressDTO data = null;

		conn = JDBCUtil.connect();
		try {
			if(aDTO.getSearchCondition().equals("배송지관리")) {
			pstmt = conn.prepareStatement(SELECTONE);
			pstmt.setInt(1, aDTO.getAdrsID());
			}
			else if(aDTO.getSearchCondition().equals("장바구니배송지")) {
				pstmt = conn.prepareStatement(SELECTONE_CARTINFO);
	            pstmt.setString(1, aDTO.getMemberID());
			
			}
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				data = new AddressDTO();
				data.setAdrsID(rs.getInt("ADDRESS_ID"));
				data.setAdrsStreet(rs.getString("ADDRESS_STREET"));
				data.setAdrsLotNum(rs.getString("ADDRESS_LOTNUM"));
				data.setAdrsZipcode(rs.getString("ADDRESS_ZIPCODE"));
				data.setAdrsDetail(rs.getString("ADDRESS_DETAIL"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(pstmt, conn);
		}
		return data;
	}
	
	public boolean insert(AddressDTO aDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, aDTO.getAdrsName());
			pstmt.setString(2, aDTO.getAdrsStreet());
			pstmt.setString(3, aDTO.getAdrsLotNum());
			pstmt.setString(4, aDTO.getAdrsDetail());
			pstmt.setString(5, aDTO.getAdrsZipcode());
			pstmt.setString(6, aDTO.getMemberID());

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

	public boolean update(AddressDTO aDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, aDTO.getAdrsName());
			pstmt.setString(2, aDTO.getAdrsStreet());
			pstmt.setString(3, aDTO.getAdrsLotNum());
			pstmt.setString(4, aDTO.getAdrsDetail());
			pstmt.setString(5, aDTO.getAdrsZipcode());
			pstmt.setInt(6, aDTO.getAdrsID()); 
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

	public boolean delete(AddressDTO aDTO) {
		conn = JDBCUtil.connect();
		try {
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, aDTO.getAdrsID());
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
