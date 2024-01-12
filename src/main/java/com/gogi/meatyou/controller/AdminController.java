package com.gogi.meatyou.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.NoticeDTO;
import com.gogi.meatyou.bean.NoticeFileDTO;
import com.gogi.meatyou.repository.AdminMapper;
import com.gogi.meatyou.service.AdminService;
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

	@RequestMapping("/test")
	public String test(Model model) {
		MemberDTO mem = adminServicImpl.test("test");
		model.addAttribute("mem", mem);
		return "test";
	}

	@RequestMapping("/apiTest")
	public String apiTest(Model model) {
		adminServicImpl.apiTest(model);
		return "admin/apiTest";
	}

	@RequestMapping("/sales")
	public String sales(Model model, @RequestParam(value = "check", defaultValue = "0") int check, String daterange) {
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
		adminServicImpl.getNoticeContent(model,dto,fdto);
		
		return "admin/noticeContent";
	}
	@RequestMapping("/noticeupdateForm")
	public String noticeupdateForm(Model model,NoticeDTO dto,NoticeFileDTO fdto) {
		adminServicImpl.getNoticeContent(model,dto,fdto);
		return "admin/noticeupdateForm";
	}
	@RequestMapping("/noticeupdate")
	public String noticeupdate(HttpServletRequest req, HttpServletResponse resp, Model model, NoticeDTO dto) {
		adminServicImpl.noticeupdate(req, resp, model, dto);
		return "admin/noticeupdateForm";
	}
	@RequestMapping("/updateSummernoteImageFile")
	public String updateSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile,
			HttpServletRequest request,int n_num) {
		return adminServicImpl.updateSummerImgFile(multipartFile, request,n_num);
	}
	@RequestMapping("/noticedelete")
	public String noticedelete(int n_num) {
		
		return "redirect:/admin/noticeList";
	}
}
