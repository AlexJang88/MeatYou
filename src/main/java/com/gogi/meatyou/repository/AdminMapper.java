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
	public int cusWaitCount();
	public List<MemberDTO> cusWaitList(HashMap hashmap);
	public void statChange(MemberDTO dto);
	public List<MemberDTO> cusPaidList(HashMap hashmap);
	public int cusPaidCount();
	public int getProductSalse(int check);
	public int getProductComm(int check);
	public int getPaidItem(int check);
	public int getPaidAdv(int check);
	public int getUsedCoupon(int check);
	public int getLastProductSalse(String start,String end);
	public int getLastProductComm(String start,String end);
	public int getLastPaidItem(String start,String end);
	public int getLastPaidAdv(String start,String end);
	public int getLastUsedCoupon(String start,String end);
}
