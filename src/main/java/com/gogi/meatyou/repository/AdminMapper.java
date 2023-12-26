package com.gogi.meatyou.repository;

import com.gogi.meatyou.bean.MemberDTO;

public interface AdminMapper {
	public MemberDTO read(String mId);
}
