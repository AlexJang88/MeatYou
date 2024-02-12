package com.gogi.meatyou.controller;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
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
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemberPayDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;
import com.gogi.meatyou.service.KaKaoPaymentService;
import com.gogi.meatyou.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/kakaopay/*")
@RequiredArgsConstructor
@Controller
public class KaKaoPaymentController {

	@Autowired
    private CustomersService customersService;
	
	@Autowired
	private MemberService memberService;
	

	
    private final KaKaoPaymentService kakaoPayService;
    
    private MemberPayDTO mpdto =null;
    
    //@Autowired
   // private CustomersService service;
    
    private KaKaoPayDTO dto=null;
    /**
     * 野껉퀣�젫占쎌뒄筌ｏ옙
     */
    
    
    @RequestMapping("/ready")
    public @ResponseBody KakaoReadyResponse readyToKakaoPay(Principal principal, String totalpay, String p_num, String quantity, String co_num, String co_name) {
        dto = new KaKaoPayDTO();
        
        int itotalpay = Integer.parseInt(totalpay);
        String p_id = principal.getName();
        int taxfree = (int)(itotalpay*0.9);
        int vat = (int)(itotalpay*0.1);
        String tax_free=String.valueOf(taxfree);
        String vat_p=String.valueOf(vat);
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
        dto = new KaKaoPayDTO();
        int itotalpay = Integer.parseInt(totalpay);
        String p_id = principal.getName();
        int taxfree = (int)(itotalpay*0.9);
        int vat = (int)(itotalpay*0.1);
        String tax_free=String.valueOf(taxfree);
        String vat_p=String.valueOf(vat);
        
        dto.setPartner_order_id(co_num); //  cus甕곕뜇�깈
        dto.setPartner_user_id(p_id);  //占쎈툡占쎌뵠占쎈탵
        dto.setItem_name(co_name); // 占쎄맒占쎈�뱄옙���뙴�슣�뵠�뵳占�
        dto.setQuantity(quantity);   //占쎄깻�뵳占쏙옙�땾
        dto.setTotal_amount(totalpay);  //�룯�빓�닊占쎈만
        dto.setTax_free_amount(tax_free);  //占쎄쉭疫뀐옙
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
	        
	        String p_number = dto.getP_num();  //p_num占쎌뱽 String占쎌몵嚥∽옙 獄쏆룇釉�疫꿸퀣肉� 癰귨옙野껓옙
	        int p_num = Integer.parseInt(p_number);
	        
	        String co_number = kakaoApprove.getPartner_order_id(); // 占쎈쾻嚥≪빖苡뀐옙�깈 甕곕뜃瑗� 占쎈떭占쎌쁽嚥∽옙
	        int co_num = Integer.parseInt(co_number);
	        
	        CusOrderDTO cusorderdto = new CusOrderDTO();
	        cusorderdto.setCo_num(co_num); //占쎈쾻嚥≪빖苡뀐옙�깈
	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //占쎄깻�뵳占썲첎占쏙옙�땾
	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //野껉퀣�삺 疫뀀뜆釉�
	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //野껉퀣�삺占쎌쁽 占쎌뵠�뵳占�
	        cusorderdto.setCo_p_num(p_num); //占쎄맒占쎈�배린�뜇�깈
	        
	        customersService.insert_cusorder(cusorderdto);
	        	     
		return "redirect:/customers/customer";
	}
	
    
    @RequestMapping("powerlinkpayProTwo") 
  	public String powerlinkpayProTwo(@RequestParam("pg_token") String pgToken,Model model) {
  		
  	        KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveResponse(pgToken,dto);
  	        model.addAttribute("kakaoApprove", kakaoApprove);
  	        model.addAttribute("amount", kakaoApprove.getAmount());
  	        //model.addAttribute("p_num",dto.getP_num());
  	     
  	        String co_number = kakaoApprove.getPartner_order_id(); // 占쎈쾻嚥≪빖苡뀐옙�깈 甕곕뜃瑗� 占쎈떭占쎌쁽嚥∽옙
  	        int co_num = Integer.parseInt(co_number);
  	        
  	        
  	        
  	        CusOrderDTO cusorderdto = new CusOrderDTO();
  	        cusorderdto.setCo_num(co_num); //占쎈쾻嚥≪빖苡뀐옙�깈
  	        cusorderdto.setCo_quantity(kakaoApprove.getQuantity()); //占쎄깻�뵳占썲첎占쏙옙�땾
  	        cusorderdto.setCo_pay(kakaoApprove.getAmount().getTotal()); //野껉퀣�삺 疫뀀뜆釉�
  	        cusorderdto.setCo_m_id(kakaoApprove.getPartner_user_id()); //野껉퀣�삺占쎌쁽 占쎌뵠�뵳占�
  	    
  	        
  	      customersService.insert_cusorderTwo(cusorderdto);
  	        	     
  		return "redirect:/customers/customer";
  	}
    
    @RequestMapping("/memready")
    public @ResponseBody KakaoReadyResponse MemreadyToKakaoPay(Principal principal,MemberPayDTO pdto) {
    	int num =pdto.getArr_shop_num()[0];
        System.out.println("==="+pdto.getPartner_order_id());
        System.out.println("==="+pdto.getPartner_user_id());
        System.out.println("==="+pdto.getItem_name());
        System.out.println("==="+pdto.getQuantity());
        System.out.println("==="+pdto.getTotal_amount());
        System.out.println("==="+pdto.getTax_free_amount());
        System.out.println("==="+pdto.getVat_amount());
        System.out.println("==="+pdto.getTotal_quantity());
        dto = new KaKaoPayDTO();
        mpdto = new MemberPayDTO();
        mpdto=pdto;
        int qty = Integer.parseInt(pdto.getQuantity())-1;
        
        String p_id = principal.getName();
        String txfree=pdto.getTax_free_amount().substring(0,pdto.getTax_free_amount().lastIndexOf("."));
        String vat=pdto.getVat_amount().substring(0,pdto.getVat_amount().lastIndexOf("."));
        System.out.println("==="+txfree);
        System.out.println("==="+vat);
        dto.setP_num("11");
        dto.setItem_name(pdto.getArr_p_name()[0]+"외 "+qty+"종");
        pdto.setPartner_order_id(String.valueOf(pdto.getArr_shop_num()[0]));
        dto.setPartner_order_id(String.valueOf(pdto.getArr_shop_num()[0]));
        dto.setPartner_user_id(pdto.getPartner_user_id());
        dto.setQuantity(pdto.getQuantity());
        pdto.setTax_free_amount(txfree);
        dto.setTax_free_amount(txfree);
        dto.setTotal_amount(pdto.getTotal_amount());
        pdto.setVat_amount(vat);
        dto.setVat_amount(vat);
        System.out.println("===dto.check=="+dto.getP_num());
        System.out.println("===dto.check=="+dto.getItem_name());
        System.out.println("===dto.check=="+dto.getPartner_order_id());
        System.out.println("===dto.check=="+dto.getPartner_user_id());
        System.out.println("===dto.check=="+dto.getQuantity());
        System.out.println("===dto.check=="+dto.getTax_free_amount());
        System.out.println("===dto.check=="+dto.getTotal_amount());
        System.out.println("===dto.check=="+dto.getVat_amount());
    	return kakaoPayService.kakaoPayReadymem(dto);
    }
    @GetMapping("/memsuccess")
    public String memsuccess(@RequestParam("pg_token") String pgToken,Model model) {
    	KakaoApproveResponse kakaoApprove = kakaoPayService.ApproveMemResponse(pgToken,mpdto);
        int count = mpdto.getArr_shop_num().length;
        ArrayList<MOrderDTO> list = new ArrayList<MOrderDTO>();
        
        for(int i=0; i<count; i++) {
        	MOrderDTO temp= new MOrderDTO();
        	temp.setOrder_m_id(mpdto.getArr_order_m_id()[i]);
        	temp.setOrder_cp_num(mpdto.getArr_order_cp_num()[i]);
        	temp.setOrder_p_num(mpdto.getArr_order_p_num()[i]);
        	temp.setOrder_p_price(mpdto.getArr_order_p_price()[i]);
        	temp.setOrder_dere_pay(mpdto.getArr_order_dere_pay()[i]);
        	temp.setOrder_addr(mpdto.getArr_order_addr()[i]);
        	temp.setOrder_quantity(mpdto.getArr_order_quantity()[i]);
        	temp.setOrder_discount(mpdto.getArr_order_discount()[i]);
        	temp.setOrder_totalprice(mpdto.getArr_order_totalprice()[i]);
        	temp.setOrder_memo(mpdto.getOrder_memo());
        	list.add(temp);
        	System.out.println("shounu============"+mpdto.getArr_shop_num()[i]);
        	System.out.println("shounu============"+mpdto.getArr_order_cp_num()[i]);
        }
        
        memberService.userPaycomplete(list,mpdto.getArr_shop_num(),mpdto.getArr_order_cp_num(),mpdto.getArr_order_m_id()[0]);
        
        return "kakaopay/success";
    }
    
    
    /**
     * 野껉퀣�젫 筌욊쑵六� 餓ο옙 �뿆�뫁�꺖
     */
    
    @GetMapping("/cancel")
    public void cancel() {

    }

    /**
     * 野껉퀣�젫 占쎈뼄占쎈솭
     */
    @GetMapping("/fail")
    public void fail() {

    }
}