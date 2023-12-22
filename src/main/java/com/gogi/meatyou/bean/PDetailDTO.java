package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PDetailDTO {
	private int pdeNum;
	private int pNum;
	private String pDesc;
	private int pStatus;
	private String origin;
	private String local;
	private int weight;
	private String butchery;
	private int serialNum;
	private String retain;
	private int stock;
	private Date duedate;
}
