package com.gogi.meatyou.bean;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MemberDTO {

	private String m_id;
	private String passwd; 
	private String m_name;
	private Date birth;
	private String m_addr1;
	private String m_addr2;
	private String email;
	private String telep;
	private String phone;
	private String m_regDate; 
	private int m_status;
	private String mstat_detail;
	private String mstat_auth;
	private MemStatusDTO mstatDto;
 	private List<MemStatusDTO> mstatus_list; // 권한 list: 담았다가 가져와서 처리
	 private List<CusDetailDTO> Cusdetail_list; // 판매자 가입 list  : 담았다가 가져와서 처리
}
