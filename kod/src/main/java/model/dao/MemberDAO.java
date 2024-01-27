package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dto.MemberDTO;
import model.util.JDBCUtil;

public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;
	
	private static final String SELECTALL="SELECT * FROM MEMBER";
	private static final String SELECTONE="SELECT * FROM MEMBER WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	private static final String SELECTONE_CHECK="SELECT * FROM MEMBER WHERE MEMBER_ID=?";

	private static final String INSERT="INSERT INTO MEMBER VALUES(?,?,?,?,?,'user',?,?)";
	private static final String UPDATE="UPDATE MEMBER SET NAME=? WHERE MEMBER_ID=?";
	private static final String DELETE="DELETE FROM MEMBER WHERE MID=?";
	
	
	
	public ArrayList<MemberDTO> selectAll(MemberDTO mDTO) {
		ArrayList<MemberDTO> datas=new ArrayList<MemberDTO>();

		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SELECTALL);

			ResultSet rs=pstmt.executeQuery();

			while(rs.next()) {
				MemberDTO data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setMemberPhNum(rs.getString("MEMBER_PHNUM"));
				data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				data.setMemberGrade(rs.getString("MEMBER_GRADE"));
				data.setMemberGender(rs.getString("MEMBER_GENDER"));
				data.setMemberBirth(rs.getString("MEMBER_BIRTH"));
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
	
	
	public MemberDTO selectOne(MemberDTO mDTO) {
		MemberDTO data=null;
		
		conn=JDBCUtil.connect();
		try {
		if(mDTO.getSearchCondition().equals("로그인")) {
			
			pstmt=conn.prepareStatement(SELECTONE);
			pstmt.setString(1,mDTO.getMemberID());
			pstmt.setString(2,mDTO.getMemberPW());
		}
		else {
			pstmt=conn.prepareStatement(SELECTONE_CHECK);
			pstmt.setString(1,mDTO.getMemberID());
		} 
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberPW(rs.getString("MEMBER_PW"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			System.out.println("연결해제");
			JDBCUtil.disconnect(pstmt, conn);
		}
		
		return data;
	}
	public boolean insert(MemberDTO mDTO) {
		conn=JDBCUtil.connect();
		
		
		try {
			pstmt=conn.prepareStatement(INSERT);
			System.out.println(mDTO.getMemberID());
			pstmt.setString(1,mDTO.getMemberID());
			pstmt.setString(2,mDTO.getMemberPW());
			pstmt.setString(3,mDTO.getMemberName());
			pstmt.setString(4,mDTO.getMemberPhNum());
			pstmt.setString(5,mDTO.getMemberEmail());
			pstmt.setString(6,mDTO.getMemberGender());
			pstmt.setString(7,mDTO.getMemberBirth());
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
