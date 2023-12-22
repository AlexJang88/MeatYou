package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class ShoppingCartDTO {
	private int shopNum;
	private String mId;
	private int pNum;
	private int quantity; 
}
