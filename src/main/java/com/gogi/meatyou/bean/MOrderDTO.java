package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class MOrderDTO {
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
}
