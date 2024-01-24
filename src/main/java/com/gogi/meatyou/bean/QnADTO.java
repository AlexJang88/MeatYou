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
	private Date ma_regdate;
	private int ma_ref;
}
