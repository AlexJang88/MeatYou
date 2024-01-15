package com.gogi.meatyou.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
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
   public int noticeMaxnum();
   public NoticeDTO getNotice();
   public int noticeReg(HttpServletRequest req, HttpServletResponse resp,Model model,NoticeDTO dto);
   public String uploadSummerImgFile(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request);
   public void noticeList(Model model,int pageNum);
   public void getNoticeContent(Model model,NoticeDTO dto,NoticeFileDTO fdto);
   public String updateSummerImgFile(@RequestParam("file") MultipartFile multipartFile,HttpServletRequest request,int n_num);
   public int noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto);
   public void noticedelete(int n_num);
   public int getNoticeNum();
   
}