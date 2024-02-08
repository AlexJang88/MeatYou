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

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.KaKaoPayDTO;
import com.gogi.meatyou.bean.KakaoApproveResponse;
import com.gogi.meatyou.bean.KakaoReadyResponse;
import com.gogi.meatyou.bean.MemberPayDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;
import com.gogi.meatyou.service.KaKaoPaymentService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/kakaopay/*")
@RequiredArgsConstructor
@Controller
public class KaKaoPaymentController {

	@Autowired
    private CustomersService service; 
	
    private final KaKaoPaymentService kakaoPayService;
    
    //@Autowired
   // private CustomersService service;
    
    private KaKaoPayDTO dto=null;
    /**
     * 寃곗젣�슂泥�
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
        
        dto.setP_num(p_num);
        dto.setItem_name(co_name);
        dto.setPartner_order_id(co_num);
        dto.setPartner_user_id(p_id);
        dto.setQuantity(quantity);
        dto.setTax_free_amount(tax_free);
        dto.setTotal_amount(totalpay);
        dto.setVat_amount(vat_p);
        
        
    	return kakaoPayService.kakaoPayReady(dto);
    }
    
    @RequestMapping("/memready")
    public @ResponseBody KakaoReadyResponse MemreadyToKakaoPay(Principal principal,MemberPayDTO pdto) {
        System.out.println("==="+pdto.getPartner_order_id());
        System.out.println("==="+pdto.getPartner_user_id());
        System.out.println("==="+pdto.getItem_name());
        System.out.println("==="+pdto.getQuantity());
        System.out.println("==="+pdto.getTotal_amount());
        System.out.println("==="+pdto.getTax_free_amount());
        System.out.println("==="+pdto.getVat_amount());
        System.out.println("==="+pdto.getTotal_quantity());
        
        dto = new KaKaoPayDTO();
     //  int itotalpay = Integer.parseInt(totalpay);
        String p_id = principal.getName();
     //   int taxfree = (int)(itotalpay*0.9);
      //  int vat = (int)(itotalpay*0.1);
      //  String tax_free=String.valueOf(taxfree);
       // String vat_p=String.valueOf(vat);
       // System.out.println("==="+tax_free);
       // System.out.println("==="+vat_p);
        
        dto.setP_num("11");
        dto.setItem_name(pdto.getItem_name());
        dto.setPartner_order_id(pdto.getPartner_order_id());
        dto.setPartner_user_id(pdto.getPartner_user_id());
        dto.setQuantity(pdto.getQuantity());
        dto.setTax_free_amount(pdto.getTax_free_amount());
        dto.setTotal_amount(pdto.getTotal_amount());
        dto.setVat_amount(pdto.getVat_amount());
        
        
    	return kakaoPayService.kakaoPayReady(dto);
    }
    
    @RequestMapping("/readytwo")
    public @ResponseBody KakaoReadyResponse readyToKakaoPaytwo(Principal principal, String totalpay, String quantity, String co_num, String co_name) {
        System.out.println("==="+co_num);
        System.out.println("==="+principal.getName());
        System.out.println("==="+co_name);       
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
        
        dto.setPartner_order_id(co_num); //  cus踰덊샇
        dto.setPartner_user_id(p_id);  //�븘�씠�뵒
        dto.setItem_name(co_name); // �긽�뭹�쑀猷뚯씠由�
        dto.setQuantity(quantity);   //�겢由��닔
        dto.setTotal_amount(totalpay);  //珥앷툑�븸
        dto.setTax_free_amount(tax_free);  //�꽭湲�
        dto.setVat_amount(vat_p);  // p
        
        
    	return kakaoPayService.kakaoPayReadytwo(dto);
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
        model.addAttribute("p_num",dto.getP_num());
        
        
        return "kakaopay/success";
    }
    
    @RequestMapping("powerlinkpayPro") 
	public String powerlinkpayPro(@RequestParam("pg_token") String pgToken,Model model) {
		
	        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
	        model.addAttribute("kakaoApprove", kakaoApprove);
	        model.addAttribute("amount", kakaoApprove.getAmount());
	        model.addAttribute("p_num",dto.getP_num());
	        
	        String p_number = dto.getP_num();  //p_num�쓣 String�쑝濡� 諛쏆븮湲곗뿉 蹂�寃�
	        int p_num = Integer.parseInt(p_number);
	        
	        String co_number = kakaoApprove.getPartner_order_id(); // �벑濡앸쾲�샇 踰덇꼍 �닽�옄濡�
	        int co_num = Integer.parseInt(co_number);
	        
	        CusOrderDTO cusorderdto = new CusOrderDTO();
	        cusorderdto.setCo_num(co_num); //�벑濡앸쾲�샇
	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //�겢由�媛��닔
	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //寃곗옱 湲덉븸
	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //寃곗옱�옄 �씠由�
	        cusorderdto.setCo_p_num(p_num); //�긽�뭹踰덊샇
	        
	        service.insert_cusorder(cusorderdto);
	        	     
		return "redirect:/customers/customer";
	}
	
    
    @RequestMapping("powerlinkpayProTwo") 
  	public String powerlinkpayProTwo(@RequestParam("pg_token") String pgToken,Model model) {
  		
  	        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
  	        model.addAttribute("kakaoApprove", kakaoApprove);
  	        model.addAttribute("amount", kakaoApprove.getAmount());
  	        //model.addAttribute("p_num",dto.getP_num());
  	     
  	        String co_number = kakaoApprove.getPartner_order_id(); // �벑濡앸쾲�샇 踰덇꼍 �닽�옄濡�
  	        int co_num = Integer.parseInt(co_number);
  	        
  	        
  	        
  	        CusOrderDTO cusorderdto = new CusOrderDTO();
  	        cusorderdto.setCo_num(co_num); //�벑濡앸쾲�샇
  	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //�겢由�媛��닔
  	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //寃곗옱 湲덉븸
  	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //寃곗옱�옄 �씠由�
  	    
  	        
  	        service.insert_cusorderTwo(cusorderdto);
  	        	     
  		return "redirect:/customers/customer";
  	}
    
    
    
    
    /**
     * 寃곗젣 吏꾪뻾 以� 痍⑥냼
     */
    
    @GetMapping("/cancel")
    public void cancel() {

    }

    /**
     * 寃곗젣 �떎�뙣
     */
    @GetMapping("/fail")
    public void fail() {

    }
}