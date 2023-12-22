package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class OrderDTO {
	private int orderNum;
	private String mId;
	private int cpNum;
	private int pNum;
	private int pPrice;
	private int dereNum;
	private int derPay;
	private String orderAddr;
	private int quantity;
	private String paytype;
	private int status;
	private int discount;
	private int totalprice;
	private String memo;
}
