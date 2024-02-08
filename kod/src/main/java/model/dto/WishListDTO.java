package model.dto;

import java.sql.Time;

// [정현진] wishlist , product , review 기능을 구현함
// 관점지향적으로 횡단관리 하였기에
// 위시리스트DTO에 회원의 정보와 상품의 정보가 포함되었음

public class WishListDTO {
	private int wishListID; // PK
	private int isWished; // 찜여부
	private int wishListCnt; // 위시리스트 합계갯수(회원별 위시리스트에 담긴 상품갯수)
	private int wishTotalCnt; // 상품의 찜 합계수량(상품에 찜이 되어있는 갯수)
	private Time wishListAddTime; // 찜을 추가한 시간
	private String memberID; // 회원ID
	private String memberGender; // 회원성별
	private int memberAge; // 회원나이
	private int memberMinAge; // 회원최소 나이값(연령별 추천상품 로직에서 사용됨)
	private int memberMaxAge; // 회원최대 나이값
	private int productID; // 상품ID
	private String productBrand; // 상품브랜드
	private String productName; // 상품이름
	private String productCategory; // 상품카테고리
	private String productInfo; // 상품정보
	private String productImg; // 상품이미지
	private int productPrice; // 상품가격
	private int productStock; // 상품재고
	private String searchCondition; // DAO 실행조건
	private String SearchKeyword; // 검색어
	
	public String getSearchKeyword() {
		return SearchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		SearchKeyword = searchKeyword;
	}
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
	public int getWishTotalCnt() {
		return wishTotalCnt;
	}
	public void setWishTotalCnt(int wishTotalCnt) {
		this.wishTotalCnt = wishTotalCnt;
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
	public int getMemberAge() {
		return memberAge;
	}
	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}
	public int getMemberMinAge() {
		return memberMinAge;
	}
	public void setMemberMinAge(int memberMinAge) {
		this.memberMinAge = memberMinAge;
	}
	public int getMemberMaxAge() {
		return memberMaxAge;
	}
	public void setMemberMaxAge(int memberMaxAge) {
		this.memberMaxAge = memberMaxAge;
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
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
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
	public int getProductStock() {
		return productStock;
	}
	public void setProductStock(int productStock) {
		this.productStock = productStock;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	
	
}
