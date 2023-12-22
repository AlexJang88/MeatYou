package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewDTO {
	private int rNum;
	private String mId;
	private int pNum;
	private String content;
	private int star;
	private String img;
	private Date regDate;
}
