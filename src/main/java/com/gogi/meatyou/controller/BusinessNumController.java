package com.gogi.meatyou.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gogi.meatyou.bean.BusinessNumDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.service.testService;

@Controller
public class BusinessNumController {
	
	@Autowired
	private testService service;
	
	@RequestMapping("/check")
	public String check(Model model) {
		model.addAttribute("apiKey","wBStzrx7b1p8B9XqfLWLBMa0q7HCWqRMC7%2F2o%2BG1CWfp2gW%2FffWQ8H81TDthbbN%2FU%2FqtGmiOtMUvFtzKeHPiuQ%3D%3D");
		return "admin/businessNum";
	}
	
	
	
	@RequestMapping(value="/api",produces = "application/text; charset=UTF-8")
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	public @ResponseBody String itemCategoryAPI(Model model,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return service.getPriceinfo();
	}
	@RequestMapping("item")
	public String item() {
		return "admin/itemCategoryAPI";
	}
	
	/*
	@RequestMapping("/test/login")
	public String login(Model model) {
		model.addAttribute("key", "fcaac1b29853acd91d3df7f95bfa316f");
		model.addAttribute("uri", "http://localhost:8080/test/loginpro");
		//model.addAttribute("uri", "http://localhost:8080/test/loginpro");
		return "test/login";
	}
	
	
	@RequestMapping("/test/loginpro")
	public @ResponseBody String loginPro(@RequestParam(value = "code", required = false) String code,MemberDTO dto) throws Throwable {
		String token = service.getAccessToken(code);
		HashMap<String, Object> userInfo = service.getUserInfo(token);
		System.out.println("###nickname#### : " + userInfo.get("nickname"));
		System.out.println("###id#### : " + userInfo.get("id"));
		System.out.println("###email#### : " + userInfo.get("email"));
		System.out.println("###name#### : " + userInfo.get("name"));
		System.out.println("###birthyear#### : " + userInfo.get("birthyear"));
		System.out.println("###birthday#### : " + userInfo.get("birthday"));
		System.out.println("###phone_number#### : " + userInfo.get("phone_number"));
		return code;
	}
	*/
	@RequestMapping("diapi")
	public String diapi(Model model) {
		model.addAttribute("key", "1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94");
		return "test/disease";
	}
	
	@RequestMapping(value="/dapi",produces = "application/text; charset=UTF-8")
	@CrossOrigin(origins = "*", methods = RequestMethod.GET)
	public @ResponseBody String dapi(Model model,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		return service.getdi();
	}

	
}
