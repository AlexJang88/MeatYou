package com.gogi.meatyou.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {
	@RequestMapping("main")
	public String main() {
		return "admin/main";
	}
	@RequestMapping("customLogin")
	public String login() {
		return "main/login";
	}
	@RequestMapping("/accessError")
	public String accessError(Authentication auth) {
		System.out.println("access Denied ==>"+auth);
		return "Error";
	}
}
