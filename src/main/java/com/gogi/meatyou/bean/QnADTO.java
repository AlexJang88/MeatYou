package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class QnADTO {

	private int ma_num;
	private String ma_m_id;
	private String ma_title;
	private String ma_content;
	private int ma_status;
	private Date ma_reg_date;
	private int ma_ref;
	private String readcheck;
	private String ma_reply;
	private int r;
}
