package com.gogi.meatyou.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.MemberDTO;

public interface MemberMapper {
	public MemberDTO read(String m_id);
	
		//ȸ������
	
	 public int insertMember(MemberDTO dto);
	 

	    // Spring Security���� ����� ������ �������� ���� �޼���
	    public MemberDTO loadUserByUsername(String username);
		
	 	public List<MemberDTO> memberList();
	 
		public MemberDTO member(String m_id);
		
		
		
		public void memberUpdate(MemberDTO dto);
		
		
		public int statusAdminChange(MemberDTO dto);
		public int statusChange(MemberDTO dto);
}
