package model.dto;

import java.util.Date;

public class OrderListDTO {
	private int odListID;
	private String memberID;
	private Date odListDate;
	
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
	
}
