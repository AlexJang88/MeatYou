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
	private String reg_date; 
//private int MStatus;
	
	private List<StatusListDTO> m_status;//권한리스트로 따로 뺼게요 1226 이도준
	
}
