package model.dto;

import java.sql.Date;

public class MemberDTO {
	private String memberID;
	private String memberPW;
	private String memberPWCK;
	private String memberName;
	private String memberPhNum;
	private String memberEmail;
	private String memberGrade;
	private String memberGender;
	private String memberBirth;
	private String searchCondition;
	
	public String getMemberPWCK() {
		return memberPWCK;
	}
	public void setMemberPWCK(String memberPWCK) {
		this.memberPWCK = memberPWCK;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberPW() {
		return memberPW;
	}
	public void setMemberPW(String memberPW) {
		this.memberPW = memberPW;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberPhNum() {
		return memberPhNum;
	}
	public void setMemberPhNum(String memberPhNum) {
		this.memberPhNum = memberPhNum;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public String getMemberGrade() {
		return memberGrade;
	}
	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public String getMemberBirth() {
		return memberBirth;
	}
	public void setMemberBirth(String memberBirth) {
		this.memberBirth = memberBirth;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "MemberDTO [memberID=" + memberID + ", memberPW=" + memberPW + ", memberPWCK=" + memberPWCK
				+ ", memberName=" + memberName + ", memberPhNum=" + memberPhNum + ", memberEmail=" + memberEmail
				+ ", memberGrade=" + memberGrade + ", memberGender=" + memberGender + ", memberBirth=" + memberBirth
				+ ", searchCondition=" + searchCondition + "]";
	}
	
	
	

}	
