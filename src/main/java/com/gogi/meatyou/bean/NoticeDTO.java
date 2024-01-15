package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	private int n_num;
	private String n_title;
	private String n_content;
	private Date n_reg_date;
	
}
