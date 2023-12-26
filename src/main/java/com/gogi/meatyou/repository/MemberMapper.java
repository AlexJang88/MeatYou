package com.gogi.meatyou.repository;

import com.gogi.meatyou.bean.MemberDTO;

public interface MemberMapper {
	public MemberDTO read(String mId);
}
