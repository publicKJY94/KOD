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
import controller.productWishList.CheckWishedAction;
import controller.productWishList.MainAction;
import controller.productWishList.ProductAction;
import controller.productWishList.ProductActionCategory;
import controller.productWishList.ProductDetailAction;
import controller.productWishList.ProductRankAction;
import controller.productWishList.WishListAction;
import controller.review.ReviewWriteAction;
import controller.util.Action;
import controller.util.AlertAction;
import controller.util.GobackAction;
import controller.util.MapPageAction;

public class HandlerMapping {
	private Map<String,Action> mappings;
	
	public HandlerMapping() {
		this.mappings=new HashMap<String,Action>();
		this.mappings.put("/main.do", new MainAction());
		this.mappings.put("/myPage.do", new MypageAction());
		this.mappings.put("/login.do", new LoginAction());
		this.mappings.put("/logout.do", new LogoutAction());
		this.mappings.put("/loginPage.do", new LoginPageAction());
		this.mappings.put("/join.do", new JoinMemberAction());
		this.mappings.put("/joinAddress.do", new JoinAddressAction());
		this.mappings.put("/joinPage.do", new JoinPageAction());
		this.mappings.put("/productDetail.do", new ProductDetailAction());
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
		this.mappings.put("/wishList.do", new WishListAction());
		this.mappings.put("/checkWished.do", new CheckWishedAction());
		this.mappings.put("/alert.do", new AlertAction());
		this.mappings.put("/product.do", new ProductAction());
		this.mappings.put("/productRank.do", new ProductRankAction());
		this.mappings.put("/productCategory.do", new ProductActionCategory());
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
		this.mappings.put("/reviewWritePage.do", new ReviewWritePageAction());
		this.mappings.put("/reviewWriteAction.do", new ReviewWriteAction());
		this.mappings.put("/cartDeleteAll.do", new CartDeleteAllAction());
	    this.mappings.put("/cartDeleteEach.do", new CartDeleteEachAction());
	    this.mappings.put("/joinTermsOfUse.do", new JoinTermsOfUseAction());
	    
	}
	
	public Action getAction(String commed) {
		return mappings.get(commed);
	}
	
}
