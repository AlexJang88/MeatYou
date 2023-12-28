package com.gogi.meatyou.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	
	@Autowired
	private AdminService adminServicImpl;
	
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
		System.out.println("================"+dto.getM_id());
		System.out.println("================"+dto.getM_status());
		adminServicImpl.memberList(check,model,pageNum);
		return "admin/memberList";
	}
	@RequestMapping("/test")
	public String test(Model model) {
			MemberDTO mem =	adminServicImpl.test("test");
			model.addAttribute("mem", mem);
		return "test";
	}
}
