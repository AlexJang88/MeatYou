package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ReckonDTO {
	private int rk_num;
	private String rk_m_id;
	private int rk_status;
	private Date rk_deposit_date;
	private int totalprice;
	private int p_price;
	private int dere_pay;
	private String p_m_id;
	private int cp_price;
	private int order_dere_pay;
	private int deposit;
	
	
}
