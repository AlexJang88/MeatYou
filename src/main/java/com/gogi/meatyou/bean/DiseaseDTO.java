package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class DiseaseDTO {
	private int dNum;
	private String ictsdOccrrncNo;
	private String lkntsNm;
	private String farmNm;
	private String farmLocplcLegaldongCode;
	private String farmLocplc;
	private Date occrrncDe;
	private String lvstckspcCode;
	private String lvstckspcNm;
	private String occrrncLvstckcnt;
	private String dgnssEngnCode;
	private String dgnssEngnNm;
	private Date cessationDe;
	private Date regDate;
}
