package com.gogi.meatyou.controller;

import java.lang.ProcessHandle.Info;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;

@Controller
@RequestMapping("/main/*")
public class MainController {
	
	  @RequestMapping("main")
	    public String test(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {
		  session.setAttribute("status",dto.getM_status());
		  session.setAttribute("level", mdto.getMstat_auth());
		  	Integer status = (Integer)session.getAttribute("status");
		  	String level= (String)session.getAttribute("mstat_auth");
		    
		  	model.addAttribute("status", status);
		  System.out.print("���� ���� �������ͽ���   "+status+"  �Դϴ�,�׸��� �����"+level+"�Դϴ�");


		    return "main/main";
	    }	

}
