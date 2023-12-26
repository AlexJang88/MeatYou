package com.gogi.meatyou.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {

	private String mId;
	private String passwd; 
	private String mName;
	private Date birth;
	private String addr1;
	private String addr2;
	private String email;
	private String telep;
	private String phone;
	private String regDate; 
	private int mStatus;
	private String mstatDetail;
	private String mstatAuth; 
}
