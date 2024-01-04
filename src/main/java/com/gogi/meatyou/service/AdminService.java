package com.gogi.meatyou.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ReckonDTO;

public interface AdminService {
   public void memberList(int check,Model model,int pageNum);
   public List<MemberDTO>  customList(HashMap hashmap);
   public List<MemberDTO> memberList(HashMap hashmap);
   public int cusCount();
   public int memCount();
   public List<String> goodMember();
   public List<String> bestMember();
   public void goodMemberUpdate(List<String> id);
   public void bestMemberUpdate(List<String> id);
   public MemberDTO test(String m_id);
   public void apiTest(Model model);
   public void statChange(MemberDTO dto);
   public void getSales(Model model,int check);
   public void getCheckSalse(Model model,int check,String start,String end);
   public void getReckon(Model model,int pageNum,String year,String month);
   public void noticeReg(MultipartFile multipartFile, HttpServletRequest request);
}