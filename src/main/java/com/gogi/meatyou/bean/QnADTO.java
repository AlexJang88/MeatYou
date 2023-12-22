package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class QnADTO {

	private int maNum;
	private String mId;
	private String title;
	private String content;
	private int status;
	private Date regDate;
	private int ref;
}
