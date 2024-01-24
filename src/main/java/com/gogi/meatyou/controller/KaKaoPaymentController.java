package com.gogi.meatyou.controller;
import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.KaKaoPayDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.KaKaoPaymentService;
import com.gogi.meatyou.service.TestService;
import com.gogi.meatyou.test.KakaoApproveResponse;
import com.gogi.meatyou.test.KakaoReadyResponse;

import lombok.RequiredArgsConstructor;

@RequestMapping("/kakaopay/*")
@RequiredArgsConstructor
@Controller
public class KaKaoPaymentController {

    private final KaKaoPaymentService kakaoPayService;
    private KaKaoPayDTO dto=null;
    /**
     * 결제요청
     */
    @RequestMapping("/ready")
    public @ResponseBody KakaoReadyResponse readyToKakaoPay(Principal principal, String totalpay, String p_num, String quantity, String co_num, String co_name) {
        System.out.println("==="+co_num);
        System.out.println("==="+principal.getName());
        System.out.println("==="+co_name);
        System.out.println("==="+p_num);
        System.out.println("==="+totalpay);
        System.out.println("==="+quantity);
        dto = new KaKaoPayDTO();
        int itotalpay = Integer.parseInt(totalpay);
        String p_id = principal.getName();
        int taxfree = (int)(itotalpay*0.9);
        int vat = (int)(itotalpay*0.1);
        String tax_free=String.valueOf(taxfree);
        String vat_p=String.valueOf(vat);
        System.out.println("==="+tax_free);
        System.out.println("==="+vat_p);
        
        
        dto.setItem_name(co_name);
        dto.setPartner_order_id(co_num);
        dto.setPartner_user_id(p_id);
        dto.setQuantity(quantity);
        dto.setTax_free_amount(tax_free);
        dto.setTotal_amount(totalpay);
        dto.setVat_amount(vat_p);
        
        
    	return kakaoPayService.kakaoPayReady(dto);
    }
    @RequestMapping("/main")
    public String main() {
    	return "kakaopay/main";
    }
    @GetMapping("/success")
    public String afterPayRequest(@RequestParam("pg_token") String pgToken,Model model) {
        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
        model.addAttribute("kakaoApprove", kakaoApprove);
        model.addAttribute("amount", kakaoApprove.getAmount());
        
        return "kakaopay/success";
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