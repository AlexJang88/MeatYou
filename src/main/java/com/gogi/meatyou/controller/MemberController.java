package com.gogi.meatyou.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.service.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService service;
	//로그인  20231225 이도준
	@RequestMapping("/all")
		public String doAll() {
			return "member/all";
		}
	
	
	@RequestMapping("/member")
	public String doMember() {
		return "member/member";
	}
	
	@RequestMapping("/admin")
	public String doAdmin() {
		return "member/admin";
	}
	
	@RequestMapping("/saller")
	public String doSaller() {
		return "member/saller";
	}
	
	@RequestMapping("/accessError")
	public String accessError(Authentication auth) {
		System.out.println("access Denied==>>"+auth);
		return "member/accessError";
	}
	

	@RequestMapping("/customLogin")
	public String doLogin() {
		return "member/login";
	}
	@RequestMapping("/customLogout")
	public void doLogout() {
		
	}
	
	
	@RequestMapping("/inputForm")
	    public String inputForm() {
	        return "member/inputForm";
	    }
	


	@RequestMapping("/inputPro")
	public String inputPro(Model model ,MemberDTO dto, String passwd , HttpSession session) {
		String id =(String)session.getAttribute("memId");
		int check = service.insertMember(dto, id,passwd);
		model.addAttribute("check",check);
		return "member/inputPro";
	}
	
	
	
	
	
	
	
}
