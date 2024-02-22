package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class ChartDTO {
	
	private int mon_sal; //상품 판매금
	private int net_profit; // 상품 판매금 정산후 금액
	private int cp_price; // 쿠폰 금액
	private int total_profit; //상품판매 정산후 금액 + 유료결제
	private int co_pay; //유료결제
}
