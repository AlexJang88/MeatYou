package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PreferDTO {
	
	private int pre_num;
	private String pre_m_id;
	private int pre0_response;
	private int pre1_response;
	private String pre2_response;
	private Date pre_reg_date;
	private int pre_status;
}
