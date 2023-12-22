package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class CouponDTO {
	private int cpNum;
	private int cusNum;
	private int cpPrice;
	private String mId;
	private int cuStatus;
	private Date exDate;
	private String use;
	private int cId;
	private int cType;
	private Date useDate;
	private Date publishDate;
}
