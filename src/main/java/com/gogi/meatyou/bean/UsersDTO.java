package com.gogi.meatyou.bean;



import java.util.List;

import lombok.Data;

@Data
public class UsersDTO {
	private String username;
	private String password;
	private String name;
	private List<UserGradeDTO> grade;
	private String email;
	private String birth;
	private String phone;
	private int gender;
	private int regsite;
}