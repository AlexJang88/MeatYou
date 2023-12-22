package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
	private int pNum;
	private String mId;
	private String pName;
	private int pCategory;
	private int sCategory;
	private String thumb;
	private int pPrice;
	private int pRcount;
	private Date regDate;
	private Date startdate;
	private Date enddate;
	private int pStatus;
}
