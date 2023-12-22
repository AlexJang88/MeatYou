package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PreferDTO {
	
	private int preNum;
	private String mId;
	private String preResponse;
	private String pre1Response;
	private String pre2Response;
	private Date regDate;
	private int preStatus;
}
