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
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;
import com.gogi.meatyou.service.KaKaoPaymentService;
import com.gogi.meatyou.service.TestService;
import com.gogi.meatyou.test.KakaoApproveResponse;
import com.gogi.meatyou.test.KakaoReadyResponse;

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
        
        dto.setPartner_order_id(co_num); //  cus번호
        dto.setPartner_user_id(p_id);  //아이디
        dto.setItem_name(co_name); // 상품유료이름
        dto.setQuantity(quantity);   //클릭수
        dto.setTotal_amount(totalpay);  //총금액
        dto.setTax_free_amount(tax_free);  //세금
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
	        
	        String p_number = dto.getP_num();  //p_num을 String으로 받앗기에 변경
	        int p_num = Integer.parseInt(p_number);
	        
	        String co_number = kakaoApprove.getPartner_order_id(); // 등록번호 번경 숫자로
	        int co_num = Integer.parseInt(co_number);
	        
	        CusOrderDTO cusorderdto = new CusOrderDTO();
	        cusorderdto.setCo_num(co_num); //등록번호
	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //클릭갯수
	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //결재 금액
	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //결재자 이름
	        cusorderdto.setCo_p_num(p_num); //상품번호
	        
	        service.insert_cusorder(cusorderdto);
	        	     
		return "redirect:/customers/customer";
	}
	
    
    @RequestMapping("powerlinkpayProTwo") 
  	public String powerlinkpayProTwo(@RequestParam("pg_token") String pgToken,Model model) {
  		
  	        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
  	        model.addAttribute("kakaoApprove", kakaoApprove);
  	        model.addAttribute("amount", kakaoApprove.getAmount());
  	        //model.addAttribute("p_num",dto.getP_num());
  	     
  	        String co_number = kakaoApprove.getPartner_order_id(); // 등록번호 번경 숫자로
  	        int co_num = Integer.parseInt(co_number);
  	        
  	        
  	        
  	        CusOrderDTO cusorderdto = new CusOrderDTO();
  	        cusorderdto.setCo_num(co_num); //등록번호
  	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //클릭갯수
  	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //결재 금액
  	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //결재자 이름
  	    
  	        
  	        service.insert_cusorderTwo(cusorderdto);
  	        	     
  		return "redirect:/customers/customer";
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