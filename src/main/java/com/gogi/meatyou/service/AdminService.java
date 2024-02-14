package com.gogi.meatyou.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.AdminProductDTO;
import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.DiseaseDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.bean.ReckonDTO;

public interface AdminService {

	public void memberList(int check, Model model, int pageNum);

	public List<MemberDTO> customList(HashMap hashmap);

	public List<MemberDTO> memberList(HashMap hashmap);

	public int cusCount();

	public int memCount();

	public List<String> goodMember();

	public List<String> bestMember();

	public void goodMemberUpdate(List<String> id);

	public void bestMemberUpdate(List<String> id);
	
	

	public void test();
	
	public void statChange(MemberDTO dto);

	public void getSales(Model model, int check);

	public void getCheckSalse(Model model, int check, String start, String end);

	
	public void getReckon(Model model, int pageNum, String year, String month);

	public void autoReckonUpdate();

	public void noticeList(Model model, int pageNum);

	public int noticeReg(String realPath, NoticeDTO dto);

	public void noticeUpdate(String realPath, int num, Model model);

	public void noticeUpdateReg(String realPath, NoticeDTO dto);

	public String noticeImgUpload(MultipartFile multipartFile, String realPath);

	public void noticeImgDel(String fileName, String realPath);

	public void noticeContent(Model model, int num);

	public void noticeDelete(int num, String realPath);

	public String getSearchProductList(int pageNum, String keyword, String searchOpt, int cate1, int cate2, int cate3,
			int pstatus, Model model);

	Date calculateTargetDate(Date currentDate, int check);

	public void StopProduct(int p_num, String memo);

	public void ReleaseIssue(int p_num);

	public String uploadReportImageFile(@RequestParam("file") MultipartFile multipartFile, String realPath);

	public void reportReg(String realPath, Model model, QnADTO dto);
	
	public void reportList(int pageNum,Model model,int check);
	
	public void reportContent(Model model,int num);
	
	public void reportReply(QnADTO dto);
	
	public List<CouponDTO> getCouponMember(int m_status,int cp_price);
	
	public String getChartData(String period);
	
	public String getPriceinfo();
	
	public void updatedi(List<DiseaseDTO> dto);
	
	public void apiTest(Model model);
	
	
}