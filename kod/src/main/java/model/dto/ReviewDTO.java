package model.dto;

import java.sql.Date;

public class ReviewDTO {
	private int reviewID; // PK
	private int reviewScore; // 상품 리뷰점수
	private double reviewAvgScore; // 상품 리뷰평점
	private String reviewImg; // 상품 리뷰이미지
	private Date reviewDate; // 리뷰 작성일
	private String reviewTitle; // 리뷰 제목
	private String reviewContent; // 리뷰 내용
	private String reviewReply; // 리뷰 답글(관리자) ex)구매해주셔서 감사합니다.
	private String memberID; // 작성자ID
	private String memberName; // 작성자 이름
	private int productID; // 상품ID
	private int productName; // 상품명
	private String searchCondition; // DAO실행조건
	private int odContentID; // 주문내역ID - 주문내역별로 리뷰를 남기기 위함 - 상품 재구매 시 리뷰작성 가능하게 함
	
	public int getOdContentID() {
		return odContentID;
	}
	public void setOdContentID(int odContentID) {
		this.odContentID = odContentID;
	}
	
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
	public double getReviewAvgScore() {
		return reviewAvgScore;
	}
	public void setReviewAvgScore(double reviewAvgScore) {
		this.reviewAvgScore = reviewAvgScore;
	}
	public String getReviewImg() {
		return reviewImg;
	}
	public void setReviewImg(String reviewImg) {
		this.reviewImg = reviewImg;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
	}
	public String getReviewReply() {
		return reviewReply;
	}
	public void setReviewReply(String reviewReply) {
		this.reviewReply = reviewReply;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getProductName() {
		return productName;
	}
	public void setProductName(int productName) {
		this.productName = productName;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
}