package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int r_num;
	private String r_m_id;
	private int r_p_num;
	private String r_content;
	private int star;
	private String r_img;
	private Date r_reg_date; 
}
