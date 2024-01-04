package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ReckonDTO;

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
   public int getCheckProductSalse(HashMap hashmap);
   public int getCheckProductComm(HashMap hashmap);
   public int getCheckPaidItem(HashMap hashmap);
   public int getCheckPaidAdv(HashMap hashmap);
   public int getCheckUsedCoupon(HashMap hashmap);
   public List<ReckonDTO> getReckon(HashMap hashmap);
   public int getReckCount(HashMap hashmap);
   public void insertTest(String id);
}