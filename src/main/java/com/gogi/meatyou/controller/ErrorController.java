package com.gogi.meatyou.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/error404")
	public String error404(HttpServletResponse response) {
		 response.setStatus(response.SC_NOT_FOUND);
		return "error/404";
	}
	@RequestMapping("/error500")
	public String error500(HttpServletResponse response) {
		 response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return "error/500";
	}
}
