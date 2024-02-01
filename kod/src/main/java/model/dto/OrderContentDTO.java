package model.dto;

public class OrderContentDTO {

	private int odContentID;
	private int odListID;
	private int productID;
	private int odContentCnt;
	
	private int productPrice;
	private String productName;
	private String productImg;
	private String productCategory;
	private String memberID;
	private String searchCondition;
	
	
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public int getOdContentID() {
		return odContentID;
	}
	public void setOdContentID(int odContentID) {
		this.odContentID = odContentID;
	}
	public int getOdListID() {
		return odListID;
	}
	public void setOdListID(int odListID) {
		this.odListID = odListID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getOdContentCnt() {
		return odContentCnt;
	}
	public void setOdContentCnt(int odContentCnt) {
		this.odContentCnt = odContentCnt;
	}
	@Override
	public String toString() {
		return "OrderContentDTO [odContentID=" + odContentID + ", odListID=" + odListID + ", productID=" + productID
				+ ", odContentCnt=" + odContentCnt + ", productPrice=" + productPrice + ", productName=" + productName
				+ ", productImg=" + productImg + ", productCategory=" + productCategory + ", memberID=" + memberID
				+ ", searchCondition=" + searchCondition + "]";
	}
	
	
}
