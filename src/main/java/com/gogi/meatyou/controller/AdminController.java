package com.gogi.meatyou.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@Autowired
	private AdminService adminServicImpl;
	
	@RequestMapping("/chart")
	public String chart() {
		return "admin/chart";
	}
	
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
		System.out.println("access Denied ==>"+auth);
		return "Error";
	}
	@RequestMapping("/memberlist")
	public String memberlist(int check,Model model,@RequestParam(value="pageNum",defaultValue="1")int pageNum) {
		adminServicImpl.memberList(check, model,pageNum);
		return "admin/memberList";
	}
	@RequestMapping("/statChange")
	public String statChange(int check,@RequestParam(value="pageNum",defaultValue="1")int pageNum,Model model,MemberDTO dto) {
		adminServicImpl.statChange(dto);
		
		return "redirect:/admin/memberlist?check="+check+"&pageNum="+pageNum;
	}
	@RequestMapping("/test")
	public String test(Model model) {
			MemberDTO mem =	adminServicImpl.test("test");
			model.addAttribute("mem", mem);
		return "test";
	}
	@RequestMapping("/apiTest")
	public String apiTest(Model model) {
			adminServicImpl.apiTest(model);
		return "admin/apiTest";
	}
	@RequestMapping("/sales")
	public String sales(Model model,@RequestParam(value="check",defaultValue="0")int check,String daterange) {
		System.out.println("check==========="+check);
		if(check<=0) {	
			adminServicImpl.getSales(model,check);
		}else {
			String start = daterange.substring(0,10);
			String end = daterange.substring(13, 23);
			adminServicImpl.getCheckSalse(model,check,start,end);
		}
		return "admin/sales";
	}
	@RequestMapping("reckon")
	public String reckon(Model model,@RequestParam(value="start",defaultValue="2023") String start,@RequestParam(value="end",defaultValue="12")String end,@RequestParam(value="pageNum",defaultValue="1")int pageNum) {
		
		adminServicImpl.getReckon(model,pageNum , start, end);
		
		return "admin/reckon";
	}
	
}
