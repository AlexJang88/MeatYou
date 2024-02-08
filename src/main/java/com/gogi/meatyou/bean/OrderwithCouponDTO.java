package com.gogi.meatyou.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderwithCouponDTO {
	
	private int [] arr_shop_num;
	private int [] arr_shop_p_num;
	private int [] arr_p_num;
	private int [] arr_pd_p_num;
	private int [] arr_shop_quantity;
	private int [] arr_cp_num;
	private String [] arr_p_name;
	private String [] arr_p_m_id;
	private String [] arr_thumb;
	private int [] arr_p_price;
	private int [] arr_cp_price;
	
	private List<CouponDTO> coupons;
	private List<String> addressList;
	
	private int tot_price;
	private int shop_num;
	private int total_quantity;
	private int order_dere_pay;
	private int  shop_p_num;
	private int  p_num;
	private int  pd_p_num;
	private int  shop_quantity;
	private String  p_name;
	private String  p_m_id;
	private String  thumb;
	private int  p_price;
	private int  selectedShopNums;
	private String shop_m_id;
	
	private int  cp_num;
	private int  cp_price;
	private String selectedAddress;
	
	
	
	private int total_amount;
	private String combined_address;
	private String add_m_id;
	
}
