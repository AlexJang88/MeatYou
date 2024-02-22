package com.gogi.meatyou.bean;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class MemberDTO {
	private String m_id;
    private String passwd;
    private String m_name;
    private String birth;
    private String m_addr1;
    private String m_addr2;
    private String email;
    private String telep;
    private String phone;
    private String m_reg_date;
    private int m_status;
    private String selectedAddress;
    
    
	private String mstat_detail;
	private String mstat_auth;
    private List<MemStatusDTO> mstatus_list; 


}