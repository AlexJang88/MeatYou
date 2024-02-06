package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class OrderwithCouponDTO {
	
	private int [] shop_p_num;
	private int [] p_num;
	private int [] pd_p_num;
	private int [] shop_quantity;
	private int [] cp_num;
	private String [] p_name;
	private String [] p_m_id;
	private String [] thumb;
	private int [] p_price;

	private int totalAmount;
	private int   order_num; 
	private int order_m_num;
	private String order_m_id;
	private int order_discount; 
	private int  order_cp_num;
	private int  order_p_num;
	private int order_p_price;
	private int  order_dere_num;  
	private int order_dere_pay;  
	private String order_addr;   
	private int order_quantity;   
	private String order_paytype;  
	private int order_status;    
	private int order_totalprice; 
	private String order_memo;  
	private Date order_paydate;  
	private Date order_canceldate;  
	private Date order_dere_start;  
	private Date ordere_end;
	private String combined_address;
	private String add_m_id;
	
}
