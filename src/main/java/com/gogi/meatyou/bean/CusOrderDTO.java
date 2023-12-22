package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class CusOrderDTO {
	private int coNum;
	private int coCategory;
	private int quantity;
	private int pay;
	private String mId;
	private int autoPay;
	private int pNum;
}
