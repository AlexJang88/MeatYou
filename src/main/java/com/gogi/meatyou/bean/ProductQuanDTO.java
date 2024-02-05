package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductQuanDTO {
 	private int p_num;
	  private String p_m_id;
	 	private String p_name;
		private int p_category;
		private int p_s_category;
		private String thumb;  
		private int p_price;
		private int p_rcount;
		private Date p_reg_date;
		private String startdate;
		private String enddate;
		private String co_name;
		private int co_num;
		private int co_p_num;
		private int clickpay;
		private int co_quantity;
	   	
		private int p_status;
	  	private int pd_p_num;
		private String pd_p_desc;
		private String origin;
		private String local;
		private int weight;
		private String butchery;
		private int serialNum;
		private String retain;
		private int stock;
		private String pd_duedate;
		private int pd_p_status;
		private PDetailDTO pdetaildto;
		private double star;
		private int count;
		private int r_cnt;
		private String category1;
		private String category2;
		private String category3;
		private String ppic_m_id;
		private int ppic_p_num;
		private int reviewAllCNT;
		private int r_p_num;
		private int add_num;
		private String add_m_id;
		private String add_mem_address1;
		private String add_mem_address2;
		private int add_type;
		private String sale;
		   private int co_category;
		   private int co_pay;
		   private String co_m_id;
		   private int autoPay;
		   private Date co_paydate;
		   private Date co_payenddate;
		
		
   }