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
	
	// MEMBER 테이블에 특정 회원정보를 조회하기 위한 쿼리문
	private static final String SELECTONE="SELECT "
			// 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH "
			// 회원 테이블에서 데이터를 가져옴
			+ "FROM MEMBER "
			// WHERE절을 사용하여 조회할 아이디(MEMBER_ID), 비밀번호(MEMBER_PW)지정
			+ "WHERE MEMBER_ID=? AND MEMBER_PW=? ";
	

	// 테이블에 특정 회원 정보조회를 하기위한 쿼리문
	private static final String SELECTONE_CHECK="SELECT "
			// 회원 아이디, 비밀번호, 이름, 핸드폰 번호, 이메일, 등급, 성별, 생년월일 정보를 선택
			+ "MEMBER_ID, MEMBER_PW, MEMBER_NAME, MEMBER_PHONE, MEMBER_EMAIL, MEMBER_GRADE, MEMBER_GENDER, MEMBER_BIRTH "
			// 회원 테이블에서 데이터를 가져옴
			+ "FROM MEMBER "
			// WHERE절을 사용하여 조회할 아이디(MEMBER_ID)지정
			+ "WHERE MEMBER_ID=?";

	
	// 회원정보를 추가하기위한 쿼리문
	// MEMBER 테이블에 새로운 행(회원정보)을 저장 
	// 저장할값들은 VALUES(?,?,?,?,?,'user',?,?)
	// 이부분의 ?들은 실제 데이터베이스에 저장될 값이 위치
	private static final String INSERT="INSERT INTO MEMBER VALUES(?,?,?,?,?,'user',?,?)";
	
	
	// 회원 정보를 변경 하기위한 쿼리문
	// MEMBER의 각 열을 업데이트할 값 MEMBER_NAME=?, MEMBER_PW=?, MEMBER_EMAIL=?, MEMBER_PHONE=?
	// WHERE MEMBER_ID=? 어떠한 회원 정보를 업데이트할지 결정하는 조건
 	private static final String UPDATE="UPDATE "
			+ "MEMBER SET MEMBER_NAME=?, MEMBER_PW=?, MEMBER_EMAIL=?, MEMBER_PHONE=? WHERE MEMBER_ID=?"; 

 	
 	private static final String UPDATE_PW="UPDATE "
 			+ "MEMBER SET MEMBER_PW=? WHERE MEMBER_ID=?"; 

 	private static final String UPDATE_NAME="UPDATE "
 			+ "MEMBER SET MEMBER_NAME=? WHERE MEMBER_ID=?"; 
 	
 	private static final String UPDATE_EMAIL="UPDATE "
 			+ "MEMBER SET MEMBER_EMAIL=? WHERE MEMBER_ID=?"; 
	
	
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
		// 데이터 베이스 연결
		conn=JDBCUtil.connect();
		try {
			// 만약 검색조건이 로그인이라면 조건문 실행
		if(mDTO.getSearchCondition().equals("로그인")) {
			
			// 로그인을 위한 쿼리문 실행준비
			// 첫 번째 물음표에 아이디 저장
			// 두 번째 물음표에 비밀번호 저장
			pstmt=conn.prepareStatement(SELECTONE);
			pstmt.setString(1,mDTO.getMemberID());
			pstmt.setString(2,mDTO.getMemberPW());
		}
		else {// 검색조건(SearchCondition)이 로그인이 아니라면
			
			// 특정 회원정보를 조회하기위한 쿼리문(SELECTONE_CHECK) 실행준비
			pstmt=conn.prepareStatement(SELECTONE_CHECK);
			   
			// 쿼리문에 데이터 저장
			// JoinAddressAction 에서 받아온값을 쿼리문에 저장 
			// check.java 에서 받아온값을 쿼리문에 저장 
			pstmt.setString(1,mDTO.getMemberID());
		} 
			// 쿼리문을 실행시키고 실행결과를 담을 ResultSet 변수(rs)를 생성하고 실행결과를 저장함
			ResultSet rs=pstmt.executeQuery();
			
			// 변수(rs)에 데이터가 있는지 확인함 데이터가 존재한다면
			// 데이터가 있다면 해당 데이터를 추출함
			if(rs.next()) {
			
				// scope 이슈로 조건문안에 data변수 생성 
				// SELECTONE_CHECK 쿼리문 실생결과를 담은rs변수의 회원 아이디, 비밀번호, 이메일, 핸드폰번호를 data변수에 저장
				data=new MemberDTO();
				data.setMemberID(rs.getString("MEMBER_ID"));
				data.setMemberName(rs.getString("MEMBER_NAME"));
				data.setMemberEmail(rs.getString("MEMBER_EMAIL"));
				data.setMemberPhNum(rs.getString("MEMBER_PHONE"));
				data.setMemberGrade(rs.getString("MEMBER_GRADE"));
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
	
	
	
	
	// 회원 정보 추가 메서드
	public boolean insert(MemberDTO mDTO) {
		// 데이터 베이스 연결
		conn=JDBCUtil.connect();
			
		try {
			// 회원가입을 위한 쿼리문 실행준비
			// 쿼리문을 실행하기 위한 객체 생성 
			pstmt=conn.prepareStatement(INSERT);
			System.out.println(mDTO.getMemberID());
			
			// 쿼리문에 데이터 저장
			// joinMemberAction.java에서 받아온값을 쿼리문에 저장 
			pstmt.setString(1,mDTO.getMemberID());
			pstmt.setString(2,mDTO.getMemberPW());
			pstmt.setString(3,mDTO.getMemberName());
			pstmt.setString(4,mDTO.getMemberPhNum());
			pstmt.setString(5,mDTO.getMemberEmail());
			pstmt.setString(6,mDTO.getMemberGender());
			pstmt.setString(7,mDTO.getMemberBirth());
			
			// 퀴리 실행후 결과값 변수(rs)에 저장 
			int rs=pstmt.executeUpdate();
			// 삽입된 데이터가 없으면 false 
			if(rs<=0) {
				return false;
				
			}
		
		} catch (SQLException e) {
			// 예외 발생시 예외 내용 출력
			e.printStackTrace();
			return false;
		} finally {
			// 데이터 베이스 연결 해제
			JDBCUtil.disconnect(pstmt, conn);
			
		}
		// 정상적으로 데이터를 저장했을경우 true 반환
			return true;
	}
		
		
	
	// 회원 정보 변경 메서드
	public boolean update(MemberDTO mDTO) {
		System.out.println(mDTO + "[본승] 로그 mDTO <<<");
		// 데이터 베이스 연결
		conn=JDBCUtil.connect();
		try {
			
			if(mDTO.getSearchCondition().equals("비밀번호변경")) {
				pstmt=conn.prepareStatement(UPDATE_PW);
				pstmt.setString(1,mDTO.getMemberPW());
				pstmt.setString(2,mDTO.getMemberID());
				System.out.println("[본승] 로그 memberDAO update -> memberPW"+mDTO.getMemberPW());
				System.out.println("[본승] 로그  검색조건 비밀번호변경 들어옴");
			
			}
			else if(mDTO.getSearchCondition().equals("이름변경")) {
				pstmt=conn.prepareStatement(UPDATE_NAME);
				pstmt.setString(1,mDTO.getMemberName());
				pstmt.setString(2,mDTO.getMemberID());
				System.out.println("[본승] 로그  검색조건 이름변경 들어옴");
				System.out.println("[본승] 로그 memberDAO update -> memberName"+mDTO.getMemberName());
				
			}
			else if(mDTO.getSearchCondition().equals("이메일변경")) {
				pstmt=conn.prepareStatement(UPDATE_EMAIL);
				pstmt.setString(1,mDTO.getMemberEmail());
				pstmt.setString(2,mDTO.getMemberID());
				System.out.println("[본승] 로그 memberDAO update -> memberEmail"+mDTO.getMemberEmail());
				
			}
		
			
			// 퀴리 실행후 결과값 변수(rs)에 저장 
			int rs=pstmt.executeUpdate();
			System.out.println("[본승] 로그 rs"+rs);
			System.out.println("[본승] 로그memberDAO update -> memberPW"+mDTO.getMemberPW());
			
			// 삽입된 데이터가 없으면 false 
			if(rs <= 0) {
				return false;
			}
		} catch (SQLException e) {
			// 예외 발생시 예외 내용 출력
			e.printStackTrace();
			return false;
		}finally {// 데이터 베이스 연결 해제
			JDBCUtil.disconnect(pstmt, conn);
		}
		// 정상적으로 데이터를 저장했을경우 true 반환
		return true;
		
	}
	
	
	public void delete() {
		
	}
}
