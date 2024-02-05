package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class UserDetailsDTO {
	
	private String username;
	private String image;
	private String store;
	private int point;
	private Date regdate;
	private Date updatedate;
	private String address;
	private String phone;
	private int followers;
}