package com.gogi.meatyou.bean;

import lombok.Data;
import com.gogi.meatyou.bean.MemberDTO;
@Data
public class MemStatusDTO {
	private int mstat_m_status;
	private String mstat_detail;
	private String mstat_auth;
}
