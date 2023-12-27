package com.gogi.meatyou.bean;

import java.util.Date;
import java.util.List;
import com.gogi.meatyou.bean.MemStatusDTO;
import lombok.Data;

@Data
public class MemberDTO {

	private String m_Id;
	private String passwd; 
	private String m_name;
	private Date birth;
	private String m_addr1;
	private String m_addr2;
	private String email;
	private String telep;
	private String phone;
	private String m_reg_date; 
	private int m_status;
	private List<MemStatusDTO> mStatusList;//���Ѹ���Ʈ�� ���� �E�Կ� 1226 �̵���
	
}
