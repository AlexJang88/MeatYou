package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CusOrderDTO {
	 private int co_num;  //번호
	   private int co_category;  // 종류
	   private int co_quantity; // 클릭 또는 결재 갯수
	   private int co_pay;  // 결재 금액
	   private String co_m_id;  //결재자 아이디
	   private int autoPay;  // 자동결제 0, 1
	   private int co_p_num; //상품 번호
	   private Date co_paydate;  // 결재 날짜
	   private Date co_payenddate; //결제 종료날짜
	}