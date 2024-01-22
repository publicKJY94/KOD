package model.dto;

public class ProductDTO {
	private int productID;
	private int productCnt;
	private int productPrice;
	private String productName;
	private String productBrand;
	private String productCategory;
	private String productInfo;
	private String productImg;
	private String searchCondition;
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getProductCnt() {
		return productCnt;
	}
	public void setProductCnt(int productCnt) {
		this.productCnt = productCnt;
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
	public String getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
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
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "ProductDTO [productID=" + productID + ", productCnt=" + productCnt + ", productPrice=" + productPrice
				+ ", productName=" + productName + ", productBrand=" + productBrand + ", productCategory="
				+ productCategory + ", productInfo=" + productInfo + ", productImg=" + productImg + "]";
	}
	
	
	
	
}
