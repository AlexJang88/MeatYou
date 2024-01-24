package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class AdminProductDTO {
	private int p_num;
	private String thumb;
	private String p_name;
	private String p_m_id;
	private int totalsales;
	private int p_price;
	private int report;
	private int p_rcount;
	private int p_status;
	private int pd_p_status;
	private Date p_reg_date;
	
}
