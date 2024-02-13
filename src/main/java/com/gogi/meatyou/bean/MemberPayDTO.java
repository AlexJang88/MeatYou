package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class MemberPayDTO {

	private String partner_order_id;
	private String partner_user_id;
	private String item_name;
	private String quantity;
	private String total_amount;
	private String tax_free_amount;
	private String vat_amount;
	private String total_quantity;
	private String order_memo;
	
	private  String [] arr_order_m_id;
	private int []arr_order_cp_num;
	private int []arr_order_p_num;
	private int []arr_order_p_price;
	private int []arr_order_dere_pay;
	private String []arr_order_addr;
	private int []arr_order_quantity;
	private int []arr_order_discount;
	private int []arr_order_totalprice;
	private int []arr_shop_num;
	private String [] arr_p_name;
	
	
	
	
		 
	
	
}
