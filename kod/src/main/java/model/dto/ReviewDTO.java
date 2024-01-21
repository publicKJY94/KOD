package model.dto;

import java.sql.Time;

public class ReviewDTO {
	private int reviewID;
	private int reviewScore;
	private int reviewReply;
	private int productID;
	private Time reviewTime;
	private String reviewTitle;
	private String memberID;
	
	public int getReviewID() {
		return reviewID;
	}
	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}
	public int getReviewScore() {
		return reviewScore;
	}
	public void setReviewScore(int reviewScore) {
		this.reviewScore = reviewScore;
	}
	public int getReviewReply() {
		return reviewReply;
	}
	public void setReviewReply(int reviewReply) {
		this.reviewReply = reviewReply;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public Time getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Time reviewTime) {
		this.reviewTime = reviewTime;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	
	
	
}
