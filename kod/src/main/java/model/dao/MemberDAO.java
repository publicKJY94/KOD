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
	
	private static final String SELECTALL="SELECT "
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH "
			+ "FROM MEMBER ";
	
	
	private static final String SELECTONE="SELECT "
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH "
			+ "FROM MEMBER "
			+ "WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	
	// 테이블에 특정 회원 정보조회를 하기위한 쿼리
	private static final String SELECTONE_CHECK="SELECT "
			// 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH "
			// 회원 테이블에서 데이터를 가져옴
			+ "FROM MEMBER "
			// WHERE절을 사용하여 조회할 ID지정
			+ "WHERE MEMBER_ID=?";

	
	private static final String INSERT="INSERT INTO MEMBER VALUES(?,?,?,?,?,'user',?,?)";
	
	
	private static final String UPDATE="UPDATE "
			+ "MEMBER SET MEMBER_NAME=?, MEMBER_PW=?, MEMBER_EMAIL=?, MEMBER_PHONE=? WHERE MEMBER_ID=?"; 
	
	
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
		else {// 검색조건(SearchCondition)이 로그인이 아니라면
			// 데이터베이스 준비문 prepareStatement에 위에서 정의된 SELECTONE_CHECK퀴리문을 인자로받아 실행대기시키고 pstmt변수에 저장함
			pstmt=conn.prepareStatement(SELECTONE_CHECK);
			   
			// pstmt의 첫 번째 매개변수(위치)에 check.java에서 받아온 데이터(mDTO) 값을 문자열로 설정합니다.
			pstmt.setString(1,mDTO.getMemberID());
		} 
			// SELECTONE_CHECK 쿼리문을 실행시키고 실행결과를 담을 ResultSet rs변수를 생성하고 실행결과를 저장합니다 
			ResultSet rs=pstmt.executeQuery();
			// rs변수에 데이터가 있는지 확인합니다
			// 데이터가 있다면 해당 데이터를 추출합니다
			if(rs.next()) {
				// scope 이슈로 조건문안에 data변수 생성 
				// SELECTONE_CHECK 쿼리문 실생결과를 담은rs변수의 회원 아이디, 비밀번호, 이메일, 핸드폰번호를 data변수에 저장
				data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				data.setMemberPhNum(rs.getString("MEMBER_PHONE"));
				
			}
			// rs 객체 닫기
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 데이터베이스 연결해제
			JDBCUtil.disconnect(pstmt, conn);
		}
		// 조회된 회원 정보를 담은 MemberDTO 객체(data) 반환
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
		
		
	
	
	public boolean update(MemberDTO mDTO) {
		System.out.println(mDTO + "dao <<<");
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(UPDATE);
			pstmt.setString(1,mDTO.getMemberName());
			pstmt.setString(2,mDTO.getMemberPW());
			pstmt.setString(3,mDTO.getMemberEmail());
			pstmt.setString(4,mDTO.getMemberPhNum());			
			pstmt.setString(5,mDTO.getMemberID());
			System.out.println("[로그] memberDAO update -> memberEmail"+mDTO.getMemberEmail());
			System.out.println("[로그] memberDAO update -> memberPhNum"+mDTO.getMemberPhNum());
			int rs=pstmt.executeUpdate();
			System.out.println("[로그] memberDAO update -> memberPhNum"+mDTO.getMemberPhNum());
			if(rs <= 0) {
				return false;
			}
			System.out.println(rs+" rs ");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			JDBCUtil.disconnect(pstmt, conn);
			
		}
		
		return true;
		
	}
	
	
	public void delete() {
		
	}
}
