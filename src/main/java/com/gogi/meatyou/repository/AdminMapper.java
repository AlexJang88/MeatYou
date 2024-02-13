package com.gogi.meatyou.repository;



import java.util.HashMap;

import java.util.List;



import org.apache.ibatis.annotations.Param;



import com.gogi.meatyou.bean.AdminProductDTO;
import com.gogi.meatyou.bean.ChartDTO;
import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.DiseaseDTO;
import com.gogi.meatyou.bean.MemberDTO;

import com.gogi.meatyou.bean.NoticeDTO;

import com.gogi.meatyou.bean.NoticeFileDTO;

import com.gogi.meatyou.bean.QnADTO;

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
   
   public void autoCoupon(List<CouponDTO> list);
   
   public List<CouponDTO> getCouponMember(HashMap hashmap);

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
   
   public List<Integer> getDiseaseIssueNum();
   
   public List<Integer> getCancleDIsuueNum();
   
   public void DiseaseCheck(HashMap hashmap);

   public List<ReckonDTO> getReckon(HashMap hashmap);

   public int getReckCount(HashMap hashmap);

   public void insertTest(String id);

   public List<NoticeDTO> noticeList(HashMap hashmap);

   public int getNoticeCount();

   public void noticeReg(NoticeDTO dto);

   public void noticeFileUpload(NoticeFileDTO dto);

   public void noticeUnitFileDelete(String filename);

   public NoticeDTO noticeContent(int num);

   public List<NoticeFileDTO> getNoticeFiles(int nf_n_num);

   public void noticeFileDelete(int nf_n_num);

   public void noticedelete(int n_num);

   public int getNoticeNum();

   public void noticeUpdate(NoticeDTO dto);

   public List<AdminProductDTO> adminProductList(HashMap hashmap);

   public int adminProductCount(HashMap hashmap);
   

   public void pdstatChange(HashMap hashmap);

   public void releaseIssue(int pd_p_num);

   public int getQnAnumber();

   public int QnAnextval();

   public void ReportFileUpload(NoticeFileDTO dto);

   public void reportReg(QnADTO dto);
   
   public List<QnADTO> reportList(HashMap hashmap);
   
   public int reportCount(int check);
   
   public List<QnADTO> reportContent(int ma_num);
   
   public void reportReply(QnADTO dto);
   
   public void maStatChange(int ma_num);
   
   public List<ChartDTO> getChartData(String period);
   
   public void diseaseAutoUpdate(DiseaseDTO dto);
   
   
}