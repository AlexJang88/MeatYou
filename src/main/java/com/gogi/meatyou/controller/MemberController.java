package com.gogi.meatyou.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.MemberPayDTO;
import com.gogi.meatyou.bean.OrderwithCouponDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.bean.UserPayDTO;
import com.gogi.meatyou.service.MemberService;




@Configuration
@EnableWebSecurity
@Controller
@RequestMapping("/member/*")
public class MemberController {
   @Autowired
   private MemberService service;
   
   @Autowired
   private ArrayList<Integer> numbers;
   
   @Autowired
   private HashMap memberMap;
   
   //@Autowired
   @RequestMapping("all")
   public String doAll(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {return "member/loginSequrity/all"; }
   @RequestMapping("member")
   public String doMember(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {return "member/loginSequrity/member"; }
   
   @RequestMapping("admin")
   public String doAdmin(Model model, MemberDTO dto,HttpSession  session,MemStatusDTO mdto) {return "member/loginSequrity/admin"; }
   
   @RequestMapping("saller")
   public String doSaller() {   return "member/loginSequrity/saller"; }
   
   @RequestMapping("accessError")
   public String accessError(Authentication auth) {return "member/loginSequrity/accessError";}
   
   @RequestMapping("customLogin")
   public String doLogin(Model model, MemberDTO dto,MemStatusDTO  mdto,HttpSession  session) {  session.setAttribute("status",dto.getM_status()); session.setAttribute("level",mdto.getMstat_auth());  
       Integer status = (Integer) session.getAttribute("status");
       Integer level = (Integer) session.getAttribute("status");
   //   return "member/loginSequrity/login";
         return "member/loginSequrity/login";
   }
   

   @RequestMapping("customLogout")
   public String doLogout(HttpSession session) {session.invalidate();  return "redirect:../../main/main";  }
   
   
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) { return "member/inputForm";  }
    
    
    @RequestMapping("inputPro")
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {  
    	int check = service.insertMember(dto); if (check > 0) {
    		service.shoppingCart(dto.getM_id()); 
    		service.shoppingCart_seq(dto.getM_id()); 
    		service.pick_me_seq(dto.getM_id());
    		service.pick_me(dto.getM_id());     
    		service.p_pick_seq(dto.getM_id()); 
    		service.prefer(dto.getM_id());    
    		service.p_pick(dto.getM_id());    
   model.addAttribute("check", check);
            return "member/inputPro";
        } else {
            return "errorPage";  
        }    }
   /* @RequestMapping("sallerInputForm")
    public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {  String username = authentication.getName(); MemberDTO dto = service.getUser(username); model.addAttribute("dto", dto);
       return "member/saller/sallerInputForm";
    }*/
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {  String m_id = authentication.getName(); dto.setM_id(m_id); Map<String, Object> statusParamMap = new HashMap<>();  statusParamMap.put("m_id", m_id);  service.updateMemberStatus(dto);  service.insertIntoCusDetail(cdto);
       return "member/saller/sallerInputPro"; 
    }
    
    
    
    @RequestMapping("/sallerInputForm")
	public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {
		
    	String username = authentication.getName(); MemberDTO dto = service.getUser(username); model.addAttribute("dto", dto);
        
    	model.addAttribute("apiKey","wBStzrx7b1p8B9XqfLWLBMa0q7HCWqRMC7%2F2o%2BG1CWfp2gW%2FffWQ8H81TDthbbN%2FU%2FqtGmiOtMUvFtzKeHPiuQ%3D%3D");
	     return "member/saller/sallerInputForm";
	}
    
    
    
    @RequestMapping("/modify")
    public String modify(Model model, Authentication authentication, CouponDTO cdto ) {
        String cp_m_id = authentication.getName();
        
        MemberDTO dto = service.getUser(cp_m_id);
        
        List<CouponDTO> cList = service.howmuchCoupon(cp_m_id);
        int count = service.couponCount(cp_m_id);
        
        model.addAttribute("cList", cList);
        model.addAttribute("count", count);
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
      return "redirect:/member/customLogout";
   } 
   
   @RequestMapping("sallerDelete")
   public String sallerDelete() {
      return "member/deleteSaller/sallerDelete";
   }
   
   @RequestMapping("sallerDeletePro")
   public String sallerDeletePro(Principal seid,Model model , String passwd , HttpSession session,MemberDTO dto,CusDetailDTO cdto) {
      String m_id =(String) seid.getName();
      int check = service.sallerDelete(dto);
          service.cusDelete(cdto,dto,m_id);
      if(check == 1) {
         session.invalidate();
      }
      model.addAttribute("check",check);
      model.addAttribute("m_id",m_id);
      return "redirect:/member/customLogout";
   } 
   
 
   

   
   
   
   
   
      
      @RequestMapping("updateQuantity")
      public @ResponseBody String updateQuantity(Principal seid,int shop_p_num,int shop_quantity) {
           String shop_m_id = (String) seid.getName();
          service.updateQuantity(shop_p_num, shop_quantity, shop_m_id);
             
          return "success";  
      }   
      
      
      @PostMapping("delete")
      public String deleteItems(Principal seid,ShoppingCartDTO sdto) {

                  String shop_m_id = (String) seid.getName();

                int check = service.deleteCart(sdto.getShop_p_num(), shop_m_id);
                if (check == 1) {
                      
                     return "redirect:/member/shoppingCartForm";
                } else {
                   return "error";
                }   
             }
      
      @PostMapping("/deleteSelectedItems")
      @ResponseBody
      public ResponseEntity<String> deleteSelectedItems(Principal seid, @RequestParam("selectedShopNums") List<Long> selectedShopNums ) {
          String shop_m_id = (String) seid.getName();

          try {
              service.deleteSelectedItems(selectedShopNums , shop_m_id);
              return ResponseEntity.ok("Selected items deleted successfully");
          } catch (Exception e) {
              e.printStackTrace();
              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting selected items");
          }
      }

      @PostMapping("/orderSelectedItems")
      public void orderSelectedItems(@RequestBody List<String> selectedShopNums, HttpSession session) {
          session.setAttribute("selectedShopNums",selectedShopNums);
          System.out.println("selectedShopNums================================"+selectedShopNums);
      }
         
      
      @RequestMapping("pickMe")
      public String pickMekList(
              Principal seid,
              Model model,
              @RequestParam(defaultValue = "1") int page,  
              @RequestParam(defaultValue = "7") int pageSize,  
              PickMeDTO pdto, CusDetailDTO cdto   
              ,@Param("p_m_id")String p_m_id
            ) {

         String pm_m_id = (String) seid.getName();

          List<PickMeDTO> pickMekList = service.pickMeCountPage(pm_m_id, page, pageSize, pdto, cdto);
          int totalItemCount = service.pickMeCount(pm_m_id,p_m_id );
          int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
          model.addAttribute("pickMekList", pickMekList);
          model.addAttribute("page", page);
          model.addAttribute("pageSize", pageSize);
          model.addAttribute("totalPage", totalPage);

          return "member/pickMe/pickMe";
      }
      
      public String mainheader( Principal seid,Model model ,@Param("p_m_id")String p_m_id,MemberDTO dto,String ppic_m_id, int ppic_num) {
 
         String pm_m_id = (String) seid.getName();
         int pickmecount=service.pickMeCount(pm_m_id,p_m_id );
         int ppickcount=service.pPickCount(ppic_m_id,ppic_num);
         model.addAttribute("pickmecount",pickmecount);
         model.addAttribute("dto",dto);
         return "/WEB-INF/views/header";
      }
      
      @RequestMapping("SallerPickMe")
      public String SallerPickMeList(
            Principal seid,
            Model model,
            @RequestParam(defaultValue = "1") int page,   
            @RequestParam(defaultValue = "7") int pageSize,  
            PickMeDTO pdto
            ) {
         
         String pm_c_id = (String) seid.getName();
         String pm_m_id=pdto.getPm_m_id();
         //  int totalPrice = sdto.getQuantity() * sdto.getP_price();
       
         List<PickMeDTO> SallerPickMeList = service.SallerpickMeCountPage(pm_m_id,pm_c_id, page, pageSize, pdto );
         int totalItemCount = service.SallerpickMeCount(pm_m_id,pm_c_id);
         int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
         model.addAttribute("SallerPickMeList", SallerPickMeList);
         //model.addAttribute("totalPrice", totalPrice);
         model.addAttribute("page", page);
         model.addAttribute("pageSize", pageSize);
         model.addAttribute("totalPage", totalPage);
         
         return "member/SallerPickMe/SallerPickMe";
      }
      
      

      @PostMapping("deleteHim")  
      public String deleteHim(Principal seid, PickMeDTO pdto) {

         String pm_m_id = (String) seid.getName();

         int check = service.deleteHim(pdto.getPm_num(), pm_m_id);
         if (check == 1) {
             return "redirect:/member/pickMe"; 
         } else {
            return "error";
         }   
      }


   
      @RequestMapping("pPickList")
      public String pPickList(
            Principal seid,
            Model model,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "1") Integer ppic_num,
            @RequestParam(defaultValue = "10") int pageSize, 
            PPicDTO ppdto, 
            ProductDTO pdto,      
            Map<String, Object> params, 
            MemberDTO mdto,PickMeDTO pppdto,
               CusDetailDTO cdto 
           
      ) {
         int p_num=pdto.getP_num();
         String ppic_m_id = (String) seid.getName();
         pppdto.setPm_m_id(ppic_m_id);
         List<PPicDTO> pPickList = service.pPickCountPages(ppic_m_id, params, page, pageSize, ppdto, pdto,mdto, ppic_num,cdto  );
         
         int totalItemCount = service.pPickCount(ppic_m_id, ppic_num );

         int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
         model.addAttribute("pPickList", pPickList);
         model.addAttribute("page", page);
         model.addAttribute("pageSize", pageSize);
         model.addAttribute("totalPage", totalPage);
         model.addAttribute("ppic_num", ppic_num);
         model.addAttribute("ppic_m_id", ppic_m_id);

         return "member/ppick/pPickList";
      }

   
    @PostMapping("deletePick")  
    public String deletePick(Principal seid, PPicDTO ppdto) {
       String ppic_m_id = (String) seid.getName();

       int check = service.deleteP_item(ppdto.getPpic_num(), ppic_m_id);
       if (check == 1) {
          return "redirect:/member/pPickList";
       } else {
          return "error";
       }   
    }

 
    @RequestMapping("pickInsert")
    public String pickInsert(Principal seid, RedirectAttributes redirectAttributes, PickMeDTO pdto,
          ProductDTO ppdto, Model model) {
        int pm_num = pdto.getPm_num();
        String pm_c_id = pdto.getPm_c_id();
        String pm_m_id = (String) seid.getName();

        service.pickMeInsert(model, pdto, ppdto, pm_m_id, pm_c_id, pm_num);
        service.pickMeInsert2(model, pdto, ppdto, pm_m_id, pm_c_id, pm_num);

        return "redirect:/member/pPickList";
    }
    @RequestMapping("memberForm")
    public String memberForm() {
       return "member/memberForm";
    }
    
    
    
    
    
    
    @RequestMapping("addressForm")
    public String addressForm(
          Principal seid,
          Model model,
          MemberDTO mdto,
        MemAddressDTO adto
          ) {
          int add_num=adto.getAdd_num();
       String add_m_id = (String) seid.getName();
       int count=service.addressCount(add_m_id, add_num);
       System.out.print("add_m_id  ======================================================"+add_m_id);
             
       List<MemAddressDTO> AddrList = service.addressCheck(add_m_id,mdto,adto,add_num );
       model.addAttribute("count", count);
       model.addAttribute("add_m_id", add_m_id);
       model.addAttribute("AddrList", AddrList);
       model.addAttribute("mdto", mdto);
       model.addAttribute("adto", adto);
       
       return "member/address/addressForm";
    }
   
    
 
  
    @RequestMapping("insertAddr")
    public String insertAddr(MemAddressDTO adto,Model model, Authentication authentication,String add_mem_address1,String add_mem_address2) {
       String add_m_id = authentication.getName();
       adto.setAdd_m_id(add_m_id);
       adto.setAdd_mem_address1(add_mem_address1);
       adto.setAdd_mem_address2(add_mem_address2);
       model.addAttribute("adto",adto);
       service.insertAddr(adto);
       return "redirect:/member/addressForm";   
    }
    
    @RequestMapping("deleteAddr")
    public String deleteAddr(MemAddressDTO adto, Authentication authentication) {
       String add_m_id = authentication.getName();
       adto.setAdd_m_id(add_m_id);
       int check = service.deleteAddr(adto.getAdd_num(),add_m_id);
    if (check == 1) {
        return "redirect:/member/addressForm";      
     } else {
        return "error";
     }   
    
    } 
    
    @RequestMapping("/updateAddr")
    public String updateAddr(@ModelAttribute("adto") MemAddressDTO adto, Authentication authentication, Model model, MemberDTO mdto,
                             @RequestParam String add_mem_address1, @RequestParam String add_mem_address2, @RequestParam int add_num) {
        String add_m_id = authentication.getName();
        adto.setAdd_m_id(add_m_id);
        adto.setAdd_num(add_num);  

        List<MemAddressDTO> AddrList = service.addressCheck(add_m_id, mdto, adto, add_num);


        service.updateAddr(adto, add_m_id, AddrList, add_num, add_mem_address1, add_mem_address2);
        return "redirect:/member/addressForm";
    }

    


    
    @RequestMapping("shoppingCartForm")
    public String shoppingCartForm(
 		   Principal seid,
 		   Model model,
 		   @RequestParam(value="page",defaultValue = "1") int page  
 		   )
    {	
    	String id = seid.getName();
    	memberMap.put("id", id);
    	memberMap.put("page", page);
    	
    	List<OrderwithCouponDTO> cartdto = service.ShoppingCartAndProduct(id,page,model);
    	for(OrderwithCouponDTO temp : cartdto) {
    	memberMap.put("p_num", temp.getP_num());
    	//temp.setAddressList(service.combined_address(id));
    	 temp.setCoupons(service.getProductCoupon(memberMap));
    	 
    	}
    	
    	model.addAttribute("cartdto", cartdto);
    	
//    	String shop_m_id = (String) seid.getName(); 
//    	ShoppingCartDTO sdto = service.shoppingCartCheck(shop_m_id);
//    	cdto//
//    	pdetail//
//    	pdto//product
//    	//shoppincartdto
// 	   int cp_num=sdto.getCp_num(); //
// 	   int cp_price=sdto.getCp_price(); //
// 	   int p_num=pdto.getP_num(); //
// 	   
// 	   int totalPrice = sdto.getShop_quantity() * sdto.getP_price(); //
// 	  
// 	   List<CouponDTO> cList = service.howmuchCoupon(shop_m_id);
// 	   List<ShoppingCartDTO> shoppingCartList = service.getShoppingCartItemsPaged2(shop_m_id, page, pageSize, sdto, pdto,pddto,cList,cdto);
// 	   for(ShoppingCartDTO dto : shoppingCartList) {
// 		  int sp_num= dto.getShop_p_num();
// 		 memberMap.put("id", shop_m_id);
// 		  memberMap.put("p_num", sp_num);
// 		  dto.setCoupons(service.getProductCoupon(memberMap));
// 	   }
// 	   
// 	   int count = service.couponCount(shop_m_id);  
// 	//  int checkCoupon=service.CouponForyou(shop_m_id,cdto,sdto);
//         
//         
// 	   int totalItemCount = service.getTotalShoppingCartItems(shop_m_id);
// 	   
// 	   int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
// 	   
// 	   System.out.println("shoppingCartList================================"+shoppingCartList);
// 	   System.out.println("cp_num================================"+cp_num);
// 	   System.out.println("cp_price================================"+cp_price);
// 	   System.out.println("selectedProducts================================"+selectedProducts);
// 	   
// 	   model.addAttribute("shoppingCartList", shoppingCartList);
// 	   model.addAttribute("totalPrice", totalPrice);
// 	   model.addAttribute("page", page);
// 	   model.addAttribute("pageSize", pageSize);
// 	   model.addAttribute("totalPage", totalPage);
// 	   model.addAttribute("selectedProducts", selectedProducts);
 	   return "member/shoppingCart/shoppingCartForm";
    }
  
    @RequestMapping("orderPageOne")
    public String orderPageOne(Principal peid,Model model,OrderwithCouponDTO dto) {
        
       String add_m_id = peid.getName();   
       
       System.out.println("id======"+add_m_id);
          dto.setAdd_m_id(add_m_id);
          int [] test = dto.getArr_shop_num();
          int [] cptest= dto.getArr_cp_num();
          System.out.println("===DTO"+test[0]);
          System.out.println("===CPNUM"+cptest[0]);
          List<String> AddrList = service.combined_address(add_m_id);
        MemberDTO mdto = service.getUser(add_m_id);         
        
        List<CouponDTO> cList = service.howmuchCoupon(add_m_id);
        System.out.println("cList================================="+cList);
        model.addAttribute("add_m_id", add_m_id);
        model.addAttribute("AddrList", AddrList);
        model.addAttribute("mdto", mdto);
        model.addAttribute("dto", dto);
        return "member/order/orderPageOne";
    } 
    
    
     
    @RequestMapping("orderPageTwo")
    public String orderPageTwo(Principal peid, Model model ,OrderwithCouponDTO dto) {
    	ArrayList<OrderwithCouponDTO> cdto = new ArrayList<OrderwithCouponDTO>();
        dto.setOrder_dere_pay(2500);
        String add_m_id = peid.getName();
        MemberDTO mdto = service.getUser(add_m_id);         
        memberMap.put("m_id", add_m_id);
            int [] temp_shop_num = dto.getArr_shop_num();
            int totquantity =0;
            int totalprice=0;
            int price=0;
            for (int i = 0; i < temp_shop_num.length; i++) {
            	System.out.println("=====shopnum"+temp_shop_num[i]);
            	System.out.println("=====shopnum"+dto.getArr_cp_num()[i]);
            }
            for (int i = 0; i < temp_shop_num.length; i++) {
				OrderwithCouponDTO tempdto = new OrderwithCouponDTO();
				memberMap.put("shop_num", dto.getArr_shop_num()[i]);
				memberMap.put("cp_num", dto.getArr_cp_num()[i]);
				tempdto=service.getCartbyNum(memberMap);
				totquantity+=tempdto.getShop_quantity();
				if(dto.getArr_cp_num()[i]==0) {
					tempdto.setCp_price(0);
				}
				price+=tempdto.getP_price()*tempdto.getShop_quantity()+dto.getOrder_dere_pay()-tempdto.getCp_price();
				tempdto.setTot_price(price);
				totalprice+=price;
				price=0;
				System.out.println(tempdto);
				System.out.println(tempdto.getTot_price());
				System.out.println("cp_price===="+tempdto.getCp_price());
				cdto.add(tempdto);
			}
            dto.setTotal_quantity(totquantity);
            dto.setTotal_amount(totalprice);
            dto.setTot_price(totalprice);
            model.addAttribute("cdto", cdto);
            model.addAttribute("dto", dto);
            model.addAttribute("mdto", mdto);
          //  model.addAttribute("selectedCoupon", selectedCoupon);
            
        
        return "member/order/orderPageTwo";
    }

	
	    
    
    @RequestMapping("showMeTheMoney")
    public @ResponseBody ArrayList<MOrderDTO> showMeTheMoney(Principal peid, Model model,MemberPayDTO dto) {
    		ArrayList<MOrderDTO> list = new ArrayList<MOrderDTO>();
    		int count = dto.getArr_shop_num().length;
    		for (int i = 0; i < count; i++) {
				MOrderDTO tempdto = new MOrderDTO();
				tempdto.setOrder_m_id(dto.getArr_order_m_id()[i]);
				System.out.println("===finalshopnum"+dto.getArr_shop_num()[i]);
				list.add(tempdto);
			}
    	
    	return list;
        } 
    
//	order_num
//	order_m_id
//	order_cp_num
//	order_p_num
//	order_p_price
//	order_dere_num
//	order_dere_pay
//	order_addr
//	order_quantity
//	order_paytype
//	order_status
//	order_discount
//	order_totalprice
//	order_memo
//	order_paydate
    
    @RequestMapping("PaymentHistory")
    public String PaymentHistory(
          Principal seid,
          Model model,
          @RequestParam(defaultValue = "1") int page,
          @RequestParam(defaultValue = "15") int pageSize
          ) {

       String order_m_id = (String) seid.getName();
       if (order_m_id != null) {
           List<MOrderDTO> paypageList = service.paypage(order_m_id, page, pageSize);
           int totalItemCount = service.PaymentCount(order_m_id);
           int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
           model.addAttribute("paypageList", paypageList);
           model.addAttribute("page", page);
           model.addAttribute("pageSize", pageSize);
           model.addAttribute("totalPage", totalPage);
       } else {
           // order_m_id가 null인 경우에 대한 처리
           // 예를 들어, 로그를 출력하거나 다른 기본값을 설정할 수 있습니다.
           model.addAttribute("errorMessage", "order_m_id is null");
       }

       return "member/order/PaymentHistory";
    }
    
    //여기서 부터 지환
    
    @GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청이 들어옴!");
		System.out.println("이메일 인증 이메일 : " + email);
		return service.joinEmail(email);
	}
    
    //아이디 찾기
    @RequestMapping("idfind")
    public String idfind(Model model,  HttpSession session){
    	Integer check = (Integer) session.getAttribute("check");
    	model.addAttribute("check", check);
    	session.removeAttribute("check");
    	return "member/loginSequrity/idfind";
    } 
    
    @RequestMapping("idfindPro")
    public String idfindPro(Model model,MemberDTO memberdto, HttpSession session){
 	
    	int check = service.findId(memberdto); // 아이디 전화번호 맞는지 확인    	
    	
    	if(check == 1) {
    		service.getDbId(model, memberdto);
    		return "member/loginSequrity/idfindweb";
    	}else {
    		session.setAttribute("check", check);
    	}
 
    	return "redirect:/member/idfind?"+"check="+check;
    } 
    
    
    
    
    @RequestMapping("pwfind")
    public String pwfind(Model model,  HttpSession session){
    	Integer check = (Integer) session.getAttribute("check");
    	model.addAttribute("check", check);
    	session.removeAttribute("check");
    	return "member/loginSequrity/pwfind";
    } 
    
    
    @RequestMapping("pwfindPro")
    public String pwfindPro(Model model, MemberDTO memberdto, HttpSession session){
    	int check = service.findPw(memberdto); // 비밀번호 찾을때 정보  	
    	
    	if(check == 1) {
    		service.getDbPw(model, memberdto);
    		return "member/loginSequrity/pwfindweb";
    	}else {
    		 session.setAttribute("check", check);
    	}
  
    	return "redirect:/member/pwfind?"+"check="+check;
    } 
    
   
    @RequestMapping("pwChange")
    public String pwChange(MemberDTO memberdto, String passwd, String passwd2){
    	if(passwd.equals(passwd2)){
    		memberdto.setPasswd(passwd);
    		service.changePw(memberdto);
    	}
    	   	
    	return "redirect:/member/customLogin";
    } 

    
}
 
