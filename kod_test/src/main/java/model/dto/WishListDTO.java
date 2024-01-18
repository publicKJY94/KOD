package model.dto;

import java.sql.Time;

public class WishListDTO {
	private int wishListID;
	private int isWished;
	private int wishListCnt;
	private Time wishListAddTime;
	private String memberID;
	private String memberGender;
	private int productID;
	private String productBrand;
	private String productName;
	private String productCategory;
	private String productImg;
	private int productPrice;
	private String searchCondition;
	
	public int getWishListID() {
		return wishListID;
	}
	public void setWishListID(int wishListID) {
		this.wishListID = wishListID;
	}
	
	public int getIsWished() {
		return isWished;
	}
	public void setIsWished(int isWished) {
		this.isWished = isWished;
	}
	public int getWishListCnt() {
		return wishListCnt;
	}
	public void setWishListCnt(int wishListCnt) {
		this.wishListCnt = wishListCnt;
	}
	public Time getWishListAddTime() {
		return wishListAddTime;
	}
	public void setWishListAddTime(Time wishListAddTime) {
		this.wishListAddTime = wishListAddTime;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getMemberGender() {
		return memberGender;
	}
	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	
	
}
