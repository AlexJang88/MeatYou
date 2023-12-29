package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.MemberDTO;

public interface MemberService {
    int insertMember(MemberDTO dto);
    
    
    
    public MemberDTO member(String m_id);
    
	public void userUpdate(MemberDTO dto);
	
	public MemberDTO getUser(String m_id);
	
	public int userDelete(String m_id,String passwd);
	//È¸¿ø Å»Åð
	public int statusChange(MemberDTO dto);
	//Å»ÅðÇÏ¸é Ã¼ÀÎÁöµÇ¾î¾ß ÇÏ´Ï±î 
	
}
