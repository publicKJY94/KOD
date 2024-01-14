package model.dto;

public class CartDTO {
	private int cartID;
	private int cartProductCnt;
	private String memberID;
	private int productID;
	
	public int getCartID() {
		return cartID;
	}
	public void setCartID(int cartID) {
		this.cartID = cartID;
	}
	public int getCartProductCnt() {
		return cartProductCnt;
	}
	public void setCartProductCnt(int cartProductCnt) {
		this.cartProductCnt = cartProductCnt;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	
}
