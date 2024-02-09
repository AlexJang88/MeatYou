package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PQuestionDTO {
	private int pq_num;
	private String pq_m_id;
	private int pq_p_num;
	private String pq_title;
	private String pq_content;
	private int pq_status;
	private int pq_ref;
	private Date pq_reg_date;
	private int r;
}
