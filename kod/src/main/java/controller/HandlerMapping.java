package controller;

import java.util.HashMap;
import java.util.Map;

import controller.address.AddressDeleteAction;
import controller.address.AddressInsertAction;
import controller.address.AddressUpdateAction;
import controller.cart.CartDeleteAllAction;
import controller.cart.CartDeleteEachAction;
import controller.cart.CartInsertAction;
import controller.join.JoinAddressAction;
import controller.join.JoinMemberAction;
import controller.join.JoinPageAction;
import controller.join.JoinTermsOfUseAction;
import controller.join.JoinsuccessAction;
import controller.login.LoginAction;
import controller.login.LoginPageAction;
import controller.login.LoginsuccessAction;
import controller.login.LogoutAction;
import controller.mypage.AlertMyPageAction;
import controller.mypage.MemberNameUpdateAction;
import controller.mypage.MemberPWUpdateAction;
import controller.mypage.MemberUpdateAction;
import controller.mypage.MemberUpdateLogoutAction;
import controller.mypage.MemberUpdatePageAction;
import controller.mypage.MypageAction;
import controller.mypage.MypageCartListAction;
import controller.mypage.MypageMemberUpdateAction;
import controller.mypage.MypageMemberUpdatePWCKAction;
import controller.mypage.MypageOrderListAction;
import controller.order.OrderInfoAction;
import controller.order.OrderInfoPageAction;
import controller.order.OrderListAction;
import controller.pay.PayInfoAction;
import controller.pay.PayInfoPageAction;
import controller.pay.PaySelectAction;
import controller.pay.PaymentPageAction;
import controller.productWishList.MainAction;
import controller.productWishList.ProductAction;
import controller.productWishList.ProductActionCategory;
import controller.productWishList.ProductDetailAction;
import controller.productWishList.ProductRankAction;
import controller.productWishList.StoreAction;
import controller.productWishList.WishListAction;
import controller.review.ReviewWriteAction;
import controller.util.Action;
import controller.util.AlertAction;
import controller.util.GobackAction;
import controller.util.MapPageAction;
import memberEmailUpdateAction.MemberEmailUpdateAction;

public class HandlerMapping {
	private Map<String,Action> mappings;
	
	public HandlerMapping() {
		this.mappings=new HashMap<String,Action>();
		this.mappings.put("/myPage.do", new MypageAction());
		this.mappings.put("/login.do", new LoginAction());
		this.mappings.put("/logout.do", new LogoutAction());
		this.mappings.put("/loginPage.do", new LoginPageAction());
		this.mappings.put("/join.do", new JoinMemberAction());
		this.mappings.put("/joinAddress.do", new JoinAddressAction());
		this.mappings.put("/joinPage.do", new JoinPageAction());
		this.mappings.put("/mapPage.do", new MapPageAction());
		this.mappings.put("/addressInsert.do", new AddressInsertAction());
		this.mappings.put("/addressUpdate.do", new AddressUpdateAction());
		this.mappings.put("/addressDelete.do", new AddressDeleteAction());
		this.mappings.put("/cartInsert.do", new CartInsertAction());
		this.mappings.put("/orderList.do", new OrderListAction());
		this.mappings.put("/payInfo.do", new PayInfoAction());
		this.mappings.put("/payInfoPage.do", new PayInfoPageAction());
		this.mappings.put("/paySelect.do", new PaySelectAction());
		this.mappings.put("/paymentPage.do", new PaymentPageAction());
		this.mappings.put("/orderInfoPage.do", new OrderInfoPageAction());
		this.mappings.put("/orderInfo.do", new OrderInfoAction());
		this.mappings.put("/alert.do", new AlertAction());
		this.mappings.put("/goback.do", new GobackAction());
		this.mappings.put("/loginsuccess.do", new LoginsuccessAction());
		this.mappings.put("/joinsuccess.do", new JoinsuccessAction());
		this.mappings.put("/mypageMemberUpdate.do", new MypageMemberUpdateAction());
		this.mappings.put("/memberUpdateAction.do", new MemberUpdateAction());
		this.mappings.put("/mypageMemberUpdatePWCK.do", new MypageMemberUpdatePWCKAction());
		this.mappings.put("/memberUpdatePage.do", new MemberUpdatePageAction());
		this.mappings.put("/memberUpdateLogout.do", new MemberUpdateLogoutAction());
		this.mappings.put("/myOrderList.do", new MypageOrderListAction());
		this.mappings.put("/mypageCartList.do", new MypageCartListAction());
		this.mappings.put("/cartDeleteAll.do", new CartDeleteAllAction());
	    this.mappings.put("/cartDeleteEach.do", new CartDeleteEachAction());
	    this.mappings.put("/joinTermsOfUse.do", new JoinTermsOfUseAction());
	    this.mappings.put("/product.do", new ProductAction());
	    this.mappings.put("/main.do", new MainAction());
	    this.mappings.put("/store.do", new StoreAction());
	    this.mappings.put("/productCategory.do", new ProductActionCategory());
	    this.mappings.put("/productRank.do", new ProductRankAction());
	    this.mappings.put("/productDetail.do", new ProductDetailAction());
	    this.mappings.put("/wishList.do", new WishListAction());
	    this.mappings.put("/reviewWriteAction.do", new ReviewWriteAction());
	    this.mappings.put("/memberPWUpdateAction.do", new MemberPWUpdateAction());
	    this.mappings.put("/memberNameUpdateAction.do", new MemberNameUpdateAction());
	    this.mappings.put("/memberEmailUpdateAction.do", new MemberEmailUpdateAction());
	    this.mappings.put("/alertMyPage.do", new AlertMyPageAction());
	    
	}
	
	public Action getAction(String commed) { 
		return mappings.get(commed); // [정현진] commend 변수에는 경로가 저장되어 있음
	}
	
}


/* [정현진]
 * 핸들러 맵핑은 MVC패턴의 핵심요소로
 * 해당 클래스에서 구현된 핸들러 맵핑 로직은 Map클래스를 활용한 것으로 key와 value로 구성된 자료구조입니다.
 * put메소드는 인자로 받은 key와 value값을 Map타입의 객체에 추가하고 만약 이미 동일한 키가 존재한다면
 * 기존값은 새로운 값으로 대체하는 함수입니다.
 * 
 * 핸들러맵핑을 싱글톤 패턴으로 구현하여 리소스를 절약하고 객체의 일관성을 유지하여
 * 메모리를 효율적으로 활용하였습니다.
 * 
 */
