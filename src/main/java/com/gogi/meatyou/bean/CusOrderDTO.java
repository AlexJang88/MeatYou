package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CusOrderDTO {
	private int co_num;  //식별번호
	private int co_category;  // 카테고리 2002파워링크
	private int co_quantity; // 클릭갯수
	private int co_pay;  // 금액
	private String co_m_id;  // 판매자 아이디
	private int autoPay;  // 자동결제
	private int co_p_num; // 상품 아이디
	private Date co_paydate;  // 결제일
	private Date co_payenddate; //품목확장 종료일
}
