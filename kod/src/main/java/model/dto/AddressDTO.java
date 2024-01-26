package model.dto;

public class AddressDTO {
	private int adrsID; //PK
	private String adrsName;  // 주소 저장할 이름 (회사 , 집) 등
	private String adrsStreet; //도로명주소
	private String adrsLotNum; // 지번주소
	private String adrsDetail; //상세주소
	private String adrsZipcode; //우편번호
	private String memberID; // MID
	
	private String searchCondition;
	
	public int getAdrsID() {
		return adrsID;
	}
	public void setAdrsID(int adrsID) {
		this.adrsID = adrsID;
	}
	public String getAdrsName() {
		return adrsName;
	}
	public void setAdrsName(String adrsName) {
		this.adrsName = adrsName;
	}
	public String getAdrsStreet() {
		return adrsStreet;
	}
	public void setAdrsStreet(String adrsStreet) {
		this.adrsStreet = adrsStreet;
	}
	public String getAdrsLotNum() {
		return adrsLotNum;
	}
	public void setAdrsLotNum(String adrsLotNum) {
		this.adrsLotNum = adrsLotNum;
	}
	public String getAdrsDetail() {
		return adrsDetail;
	}
	public void setAdrsDetail(String adrsDetail) {
		this.adrsDetail = adrsDetail;
	}
	public String getAdrsZipcode() {
		return adrsZipcode;
	}
	public void setAdrsZipcode(String adrsZipcode) {
		this.adrsZipcode = adrsZipcode;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	@Override
	public String toString() {
		return "AddressDTO [adrsID=" + adrsID + ", adrsName=" + adrsName + ", adrsStreet=" + adrsStreet
				+ ", adrsLotNum=" + adrsLotNum + ", adrsDetail=" + adrsDetail + ", adrsZipcode=" + adrsZipcode
				+ ", memberID=" + memberID + "]";
	}
	
	
	
}
