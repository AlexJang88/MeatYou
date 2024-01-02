package com.gogi.meatyou.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.service.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberController {
	@Autowired
	private MemberService service;
	private JdbcTemplate jdbcTemplate;
	//로그인  20231225 이도준
	
	
	@RequestMapping("all")
	public String doAll(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {
		
		return "member/loginSequrity/all";
	
	}
	
	
	
	@RequestMapping("member")
	public String doMember(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {
		return "member/loginSequrity/member";
	}
	
	@RequestMapping("admin")
	public String doAdmin(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {
		
		return "member/loginSequrity/admin";
	}
	
	@RequestMapping("saller")
	public String doSaller() {
		return "member/loginSequrity/saller";
	}
	
	@RequestMapping("accessError")
	public String accessError(Authentication auth) {
		System.out.println("access Denied==>>"+auth);
		return "member/accessError";
	}
	
	//로그인 
	@RequestMapping("customLogin")
	public String doLogin(Model model, MemberDTO dto,MemStatusDTO  mdto,HttpSession  session) {
		 session.setAttribute("status",dto.getM_status());
		 session.setAttribute("level",mdto.getMstat_auth());
		  
		 Integer status = (Integer) session.getAttribute("status");
		 Integer level = (Integer) session.getAttribute("status");
		  

	//	return "member/loginSequrity/login";
			return "member/loginSequrity/login";
	}
	
	
	
	//로그아웃
	@RequestMapping("customLogout")
	public String doLogout(HttpSession session) {
			session.invalidate();
	//	return "member/loginSequrity/login";
			return "redirect:../../main/main";
	}
	//회원가입 
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) {
        return "member/inputForm";
        
    }
    	
    @RequestMapping("inputPro")
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {
        int check = service.insertMember(dto);
        model.addAttribute("check", check);
        return "member/inputPro"; // 이 부분이 정상적으로 실행되고 있는지 확인
    }
    
    
    
    
    
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {
        String m_id = authentication.getName();
        // 사용자 정보 업데이트
        dto.setM_id(m_id);

        // 회원 상태 업데이트
        Map<String, Object> statusParamMap = new HashMap<>();
        statusParamMap.put("m_id", m_id);
        service.updateMemberStatus(dto);
        service.insertIntoCusDetail(cdto);
    	return "member/saller/sallerInputPro"; // 이 부분이 정상적으로 실행되고 있는지 확인
    }
    
    
    
    
    
    //회원정보수정 
    @RequestMapping("/modify")
    public String modify(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modify";
    }

    @RequestMapping("/modifyForm")
    public String modifyForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modifyForm";
    }

    @RequestMapping("/modifyPro")
    public String modifyPro(MemberDTO dto, Authentication authentication) {
        String m_id = authentication.getName();
        dto.setM_id(m_id);
        service.userUpdate(dto);
        return "member/myPage/modifyPro";
    }
	//회원탈퇴  (사실상 status 변경)
	@RequestMapping("deleteForm")
	public String deleteForm() {
		return "member/delete/deleteForm";
	}
	
	@RequestMapping("deletePro")
	public String deletePro(Model model , String passwd , HttpSession session,MemberDTO dto) {
		String m_id =(String)session.getAttribute("m_id");
		int check = service.userDelete(dto);
		if(check == 1) {
			session.invalidate();
		}
		model.addAttribute("check",check);
		return "member/delete/deletePro";
	}
	
	
    // 장바구니 담기 
    @RequestMapping("shoppingCartForm")
    public String shoppingCartForm(Model model, Authentication authentication,MemberDTO dto,ShoppingCartDTO cdto) {
    	
        model.addAttribute("cdto", cdto);
        model.addAttribute("dto", dto);
    	
    	return "member/shoppingCart/shoppingCartForm";
    	
    }
    
    
    
    @RequestMapping("shoppingCartPro")
    public String shoppingCartPro(MemberDTO dto,ShoppingCartDTO cdto) {
        // 사용자 정보 업데이트

    	return "member/shoppingCart/shoppingCartPro"; // 
    }
    
    
    
	
    
    
    
    
    
    
}
