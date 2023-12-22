package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PQuestionDTO {
	private int pqNum;
	private String mId;
	private int pNum;
	private String pqTitle;
	private String pqContent;
	private int pqStatus;
	private int pqRef;
	private Date regDate;
}
