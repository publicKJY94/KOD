package model.dto;

import java.util.Date;

public class OrderListDTO {
	private int odListID;
	private String memberID;
	private Date odListDate;
	
	private int cnt;
	
	
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getOdListID() {
		return odListID;
	}
	public void setOdListID(int odListID) {
		this.odListID = odListID;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public Date getOdListDate() {
		return odListDate;
	}
	public void setOdListDate(Date odListDate) {
		this.odListDate = odListDate;
	}
	@Override
	public String toString() {
		return "OrderListDTO [odListID=" + odListID + ", memberID=" + memberID + ", odListDate=" + odListDate + "]";
	}
	
}
