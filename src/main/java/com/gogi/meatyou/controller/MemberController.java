package com.gogi.meatyou.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member/*")
public class MemberController {

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
	
	
}
