package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.MemberDTO;

public interface MemberService {
    int insertMember(MemberDTO dto);
    
    
    
    public MemberDTO member(String m_id);
    
	public void userUpdate(MemberDTO dto);
	
	public MemberDTO getUser(String m_id);
	
	public int userDelete(String m_id,String passwd);
	//ȸ�� Ż��
	public int statusChange(MemberDTO dto);
	//Ż���ϸ� ü�����Ǿ�� �ϴϱ� 
	
}
