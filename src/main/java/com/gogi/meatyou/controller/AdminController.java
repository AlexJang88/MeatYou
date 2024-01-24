package com.gogi.meatyou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.bean.QnADTO;
import com.gogi.meatyou.repository.AdminMapper;
import com.gogi.meatyou.service.AdminService;
import com.gogi.meatyou.service.AdminServiceImpl;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	private AdminService adminServicImpl;

	@Autowired
	private HashMap adminMap;

	@RequestMapping("/chart")
	public String chart(Principal pc,Model model) {
		String sid = pc.getName();
		model.addAttribute("sid", sid);
		return "admin/chart";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/main")
	public String main() {
		return "admin/main";
	}

	@RequestMapping("/customLogin")
	public String login() {
		return "main/login";
	}

	@RequestMapping("/accessError")
	public String accessError(Authentication auth) {
		return "Error";
	}

	@RequestMapping("/memberlist")
	public String memberlist(int check, Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
		adminServicImpl.memberList(check, model, pageNum);
		return "admin/memberList";
	}

	@RequestMapping("/statChange")
	public String statChange(int check, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, Model model,
			MemberDTO dto) {
		adminServicImpl.statChange(dto);

		return "redirect:/admin/memberlist?check=" + check + "&pageNum=" + pageNum;
	}

//	@RequestMapping("/apiTest")
//	public String apiTest(Model model) {
//		adminServicImpl.apiTest(model);
//		return "admin/apiTest";
//	}

	@RequestMapping("/sales")
	public String sales(Model model, @RequestParam(value = "check", defaultValue = "0") int check, String daterange) {
		//현재 날짜
        Date currentDate = new Date();
        // check에 따라서 날짜를 계산
        Date targetDate = adminServicImpl.calculateTargetDate(currentDate, check);
        // SimpleDateFormat을 사용하여 원하는 형식으로 날짜를 문자열로 변환
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");
        String formattedDate = sdf.format(targetDate);
        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
        model.addAttribute("currentYear", targetDate.getYear() + 1900);
		if (check <= 0) {
			adminServicImpl.getSales(model, check);
		} else {
			String start = daterange.substring(0, 10);
			String end = daterange.substring(13, 23);
			adminServicImpl.getCheckSalse(model, check, start, end);
		}
		return "admin/sales";
	}

	@RequestMapping("/reckon")
	public String reckon(Model model, @RequestParam(value = "start", defaultValue = "2023") String start,
			@RequestParam(value = "end", defaultValue = "12") String end,
			@RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {

		adminServicImpl.getReckon(model, pageNum, start, end);

		return "admin/reckon";
	}

	@RequestMapping("/noticeList")
	public String notice(Model model, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum) {
			adminServicImpl.noticeList(model, pageNum);
		return "admin/noticeList";
	};

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping("/noticeForm")
	public String noticeForm() {
		return "admin/noticeForm";
	}

	@RequestMapping("/noticePro")
	public String noticePro(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request,
			String content) {
		String name = multipartFile.getOriginalFilename();

		return "admin/noticeForm";
	}

	@RequestMapping(value = "/uploadSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		return adminServicImpl.uploadSummerImgFile(multipartFile, request);
	}

	@RequestMapping("/reg")
	public String reg(HttpServletRequest req, HttpServletResponse resp, NoticeDTO dto, Model model) {
		adminServicImpl.noticeReg(req, resp, model, dto);
		return "redirect:/admin/noticeList";
	}
	
	@RequestMapping("/noticeContent")
	public String noticeContent(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
		adminServicImpl.getNoticeContent(model,dto);
		
		return "admin/noticeContent";
	}
	@RequestMapping("/noticeupdateForm")
	public String noticeupdateForm(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
		adminServicImpl.getNoticeContent(model,dto);
		return "admin/noticeupdateForm";
	}
	@RequestMapping("/noticeupdate")
	public String noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
		adminServicImpl.noticeupdate(req, resp, model, dto);
		return "redirect:/admin/noticeContent?n_num="+dto.getN_num();
	}
	@RequestMapping(value = "/updateSummernoteImageFile", produces = "application/json; charset=utf8")
	@ResponseBody
	public String updateSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request,int n_num) {
		System.out.println("controller update img num : "+n_num);
		return adminServicImpl.updateSummerImgFile(multipartFile, request,n_num);
	}
	@RequestMapping("/noticedelete")
	public String noticedelete(int n_num,HttpServletRequest req) {
		adminServicImpl.noticedelete(n_num,req);
		return "redirect:/admin/noticeList";
	}
	@RequestMapping(value = "/productList" ,produces = "application/json; charset=utf8")
	public String productList() {
		return "admin/productList";
	}
	@RequestMapping(value = "/serchProductList" ,produces = "application/json; charset=utf8")
	@ResponseBody
	public String serchProductList(Model model,@RequestParam(value="pageNum" ,defaultValue="1")int pageNum, String keyword,@RequestParam(value = "searchOpt", defaultValue = "1")String searchOpt,@RequestParam(value = "cate1", defaultValue = "1")int cate1,@RequestParam(value = "cate2", defaultValue = "1")int cate2,@RequestParam(value = "cate3", defaultValue = "1")int cate3,@RequestParam(value = "pstatus", defaultValue = "1")int pstatus) {
		return adminServicImpl.getSearchProductList(pageNum, keyword, searchOpt, cate1, cate2,cate3,pstatus, model);
	}
	@RequestMapping("/memo")
	public String memo(Model model,int p_num,@RequestParam(value="check" , defaultValue="1") int check) {
		model.addAttribute("p_num", p_num);
		model.addAttribute("check", check);
		return "admin/memo";
	}
	@RequestMapping("/pschange")
	public String pschange(int p_num,String memo,int check) {
		if(check==1) {
		adminServicImpl.StopProduct(p_num, memo);
		}else if(check!=1) {
		adminServicImpl.ReleaseIssue(p_num);	
		}
		return "admin/productList";
	}
	@RequestMapping("/report")
	public String report (HttpServletRequest req, HttpServletResponse resp,Model model,QnADTO dto) {
		adminServicImpl.reportReg(req, resp, model, dto);
		return "admin/productDetail";
	}
	@RequestMapping(value = "/uploadReportImageFile", produces="application/json; charset=utf8")
	@ResponseBody
	public String uploadReportImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request) {
		return adminServicImpl.uploadReportImageFile(multipartFile, request);
	}
}
