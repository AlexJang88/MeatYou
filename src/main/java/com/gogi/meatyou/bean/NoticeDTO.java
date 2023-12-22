package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class NoticeDTO {
	private int num;
	private String title;
	private String content;
	private Date regDate;
	
}
