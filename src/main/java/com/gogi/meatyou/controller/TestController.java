package com.gogi.meatyou.controller;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.TestService;
import com.gogi.meatyou.test.KakaoApproveResponse;
import com.gogi.meatyou.test.KakaoReadyResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/test/*")
@RequiredArgsConstructor
@Controller
public class TestController {
	@Autowired
    private final TestService kakaoPayService;
    private ProductDTO dto;
    /**
     * 결제요청
     */
    @PostMapping("/ready")
    public @ResponseBody KakaoReadyResponse readyToKakaoPay(Principal principal, int totalpay, String p_num, int quantity, int co_num, String co_name) {
   	
    	
    	dto = new ProductDTO();
    	 System.out.println(co_num);
         System.out.println(principal.getName());
         System.out.println(co_name);
         System.out.println(quantity);
         System.out.println(totalpay);
        dto.setCo_num(co_num);
        dto.setP_m_id(principal.getName());
        dto.setCo_name(co_name);
        dto.setCo_quantity(quantity);
        dto.setClickpay(totalpay);
       
        
        
        return kakaoPayService.kakaoPayReady(dto);
    }
    @RequestMapping("/main")
    public String main() {
    	return "test/main";
    }
    @GetMapping("/success")
    public String afterPayRequest(@RequestParam("pg_token") String pgToken,Model model) {
        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
        model.addAttribute("kakaoApprove", kakaoApprove);
        model.addAttribute("amount", kakaoApprove.getAmount());
        return "test/success";
    }
    
    
    /**
     * 결제 진행 중 취소
     */
    
    @GetMapping("/cancel")
    public void cancel() {

    }

    /**
     * 결제 실패
     */
    @GetMapping("/fail")
    public void fail() {

    }
}