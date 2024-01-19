package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class PurchaseMemberListDTO {
	   private int p_num;  //상품아이디
	   private String p_m_id;  //판매자아이디
	   private String p_name; //상품명
	   private int p_category; // 카테고리선택 111 삼겹살
	   private int p_s_category;  // 단품세트
	   private String thumb;  // 썸네일 사진
	   private int p_price;  // 가격
	   private int p_rcount;  // 조회수 
	   private Date p_reg_date;  // 등록일
	   private String startdate;  // 판매시작일
	   private String enddate;  // 판매종료일	 
	   
	   private int order_num;  // 등록번호
		private String order_m_id;  // 판매자
		private int order_cp_num; // 쿠폰번호
		private int order_p_num; //상품번호
		private int order_p_price;  //상품가격
		private int order_dere_num;  // 송장번호
		private int order_dere_pay;  // 배송비
		private String order_Addr;  //배송주소
		private int order_quantity;  //수량
		private String order_paytype;  // 결재방법
		private int order_status;   //구매현황
		private int order_discount;  //할인금액
		private int order_totalprice;  //총 결제금액
		private String order_memo;  // 배송 메모
		private Date order_paydate;  //결제일
		private Date order_canceldate;  //결제 취소일
		
		private int mstat_m_status;
		private String mstat_detail;
		private String mstat_auth;
		
		private String m_id;
	    private String passwd;
	    private String m_name;
	    private String birth;
	    private String m_addr1;
	    private String m_addr2;
	    private String email;
	    private String telep;
	    private String phone;
	    private String m_reg_date;
	    private int m_status;
}
