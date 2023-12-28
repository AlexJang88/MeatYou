package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import com.gogi.meatyou.bean.MemberDTO;

public interface AdminMapper {
	public MemberDTO read(String m_id);
	public List<MemberDTO>  customList(HashMap hashmap);
	public List<MemberDTO> memberList(HashMap hashmap);
	public int cusCount();
	public int memCount();
	public List<String> goodMember();
	public List<String> bestMember();
	public void goodMemberUpdate(List<String> id);
	public void bestMemberUpdate(List<String> id);
	public MemberDTO test(String m_id);
}
