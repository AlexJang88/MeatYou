package com.gogi.meatyou.repository;

import com.gogi.meatyou.bean.MemberDTO;

public interface MemberMapper {
	public MemberDTO read(String mId);
	
		//회원가입
	
	 public  int insertMember(MemberDTO dto);
}
