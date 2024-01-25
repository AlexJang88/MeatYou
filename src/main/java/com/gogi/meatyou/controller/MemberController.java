package com.gogi.meatyou.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.junit.experimental.theories.PotentialAssignment.CouldNotGenerateValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
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
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.service.MemberService;




@Configuration
@EnableWebSecurity
@Controller
@RequestMapping("/member/*")
public class MemberController {
   @Autowired
   private MemberService service;
 //嚥≪뮄�젃占쎌뵥  20231225 占쎌뵠占쎈즲餓ο옙
   
   
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
      return "member/loginSequrity/accessError";
   }
   
   @RequestMapping("customLogin")
   public String doLogin(Model model, MemberDTO dto,MemStatusDTO  mdto,HttpSession  session) {
       session.setAttribute("status",dto.getM_status());
       session.setAttribute("level",mdto.getMstat_auth());
        
       Integer status = (Integer) session.getAttribute("status");
       Integer level = (Integer) session.getAttribute("status");
        

   //   return "member/loginSequrity/login";
         return "member/loginSequrity/login";
   }
   

	//嚥≪뮄�젃占쎈툡占쎌뜍
   @RequestMapping("customLogout")
   public String doLogout(HttpSession session) {
         session.invalidate();
   //   return "member/loginSequrity/login";
         return "redirect:../../main/main";
   }
   
	
	//占쎌돳占쎌뜚揶쏉옙占쎌뿯 
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) {
        return "member/inputForm";
        
    }
    
    
    //占쎌돳占쎌뜚揶쏉옙占쎌뿯 
    @RequestMapping("inputPro")
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {
        int check = service.insertMember(dto);
        
        if (check > 0) {
            service.shoppingCart(dto.getM_id());  
            service.shoppingCart_seq(dto.getM_id());  
            
            service.pick_me(dto.getM_id()); 
            service.pick_me_seq(dto.getM_id()); 
            
            service.p_pick(dto.getM_id());  
            service.p_pick_seq(dto.getM_id());
            service.prefer(dto.getM_id());
            
            model.addAttribute("check", check);
            return "member/inputPro";
        } else {
            return "errorPage";  
        }
    }
     
    
    
    
    @RequestMapping("sallerInputForm")
    public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {
    	
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
    	
    	return "member/saller/sallerInputForm";
    	
    }
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {
        String m_id = authentication.getName();
        // 占쎄텢占쎌뒠占쎌쁽 占쎌젟癰귨옙 占쎈씜占쎈쑓占쎌뵠占쎈뱜
        dto.setM_id(m_id);

        // 占쎌돳占쎌뜚 占쎄맒占쎄묶 占쎈씜占쎈쑓占쎌뵠占쎈뱜
        Map<String, Object> statusParamMap = new HashMap<>();
        statusParamMap.put("m_id", m_id);
        service.updateMemberStatus(dto);
        service.insertIntoCusDetail(cdto);
    	return "member/saller/sallerInputPro"; // 占쎌뵠 �겫占썽겫袁⑹뵠 占쎌젟占쎄맒占쎌읅占쎌몵嚥∽옙 占쎈뼄占쎈뻬占쎈┷�⑨옙 占쎌뿳占쎈뮉筌욑옙 占쎌넇占쎌뵥
    }
    
    
    
    
    
    //占쎌돳占쎌뜚占쎌젟癰귣똻�땾占쎌젟 
    @RequestMapping("/modify")
    public String modify(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modify";
    }
  //占쎌젟癰귣똾�넇占쎌뵥 
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
  //占쎌돳占쎌뜚占쎄퉱占쎈닚  (占쎄텢占쎈뼄占쎄맒 status 癰귨옙野껓옙)
   @RequestMapping("deleteForm")
   public String deleteForm() {
      return "member/delete/deleteForm";
   }
   
 //占쎌돳占쎌뜚 占쎄퉱占쎈닚  : 占쎈뮞占쎈�믭옙�뵠占쎄숲占쎈뮞 1000占쎌몵嚥∽옙 癰귨옙野껓옙
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
   
   //占쎌돳占쎌뜚占쎄퉱占쎈닚  (占쎄텢占쎈뼄占쎄맒 status 癰귨옙野껓옙)
   @RequestMapping("sallerDelete")
   public String sallerDelete() {
      return "member/deleteSaller/sallerDelete";
   }
   
   //占쎌돳占쎌뜚 占쎄퉱占쎈닚  : 占쎈뮞占쎈�믭옙�뵠占쎄숲占쎈뮞 1001占쎌몵嚥∽옙 癰귨옙野껓옙
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
   
 
   

   
 //占쎌삢獄쏅떽�럡占쎈빍 癰귣똻�뵠疫뀐옙 + 占쎈읂占쎌뵠筌욑옙 筌ｌ꼶�봺 
      @RequestMapping("shoppingCartForm")
      public String shoppingCartForm(
              Principal seid,
              Model model,
              @RequestParam(defaultValue = "1") int page,  
              @RequestParam(defaultValue = "10") int pageSize,  
              ShoppingCartDTO sdto,
              ProductDTO pdto,
              PDetailDTO pddto,
              @RequestParam(value = "selectedProducts", required = false) List<String> selectedProducts)
    	  {
    	  
    	  int p_num=pdto.getP_num();
          String shop_m_id = (String) seid.getName();
          int totalPrice = sdto.getShop_quantity() * sdto.getP_price();
          System.out.print("�뜝�럥六삣뜝�럡愿좑옙逾놂옙�뿫堉� �뜝�럩�꼪�뜝�럩逾�======================================================"+shop_m_id);

          List<ShoppingCartDTO> shoppingCartList = service.getShoppingCartItemsPaged(shop_m_id, page, pageSize, sdto, pdto,pddto);

          int totalItemCount = service.getTotalShoppingCartItems(shop_m_id);

          int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
          
          System.out.println("占쎈읂占쎌뵠筌욑옙 占쎄쾿疫뀐옙  ============= ="+pageSize);
	    	System.out.println("占쎈읂占쎌뵠筌욑옙 ============ ="+page);
	    	System.out.println("�룯�빜�읂占쎌뵠筌욑옙占쎈뮉 ============= ="+totalPage);
	    	System.out.println("�룯占� 燁삳똻�뒲占쎈뱜   =================="+totalItemCount);
          model.addAttribute("shoppingCartList", shoppingCartList);
          model.addAttribute("totalPrice", totalPrice);
          model.addAttribute("page", page);
          model.addAttribute("pageSize", pageSize);
          model.addAttribute("totalPage", totalPage);
          // �꽑�깮�맂 �긽�뭹 紐⑸줉�쓣 紐⑤뜽�뿉 異붽��븯�뿬 �겢�씪�씠�뼵�듃�뿉 �떎�떆 �쟾�떖
          model.addAttribute("selectedProducts", selectedProducts);
          return "member/shoppingCart/shoppingCartForm";
      }
      
    //占쎈땾占쎌쎗癰귨옙野껓옙 
      @RequestMapping("updateQuantity")
      public @ResponseBody String updateQuantity(Principal seid,int shop_num,int shop_quantity) {
           String shop_m_id = (String) seid.getName();
         
           // 占쎈연疫꿸퀣肉됵옙苑� 占쎈땾占쎌쎗 占쎈씜占쎈쑓占쎌뵠占쎈뱜 嚥≪뮇彛낉옙�뱽 占쎈땾占쎈뻬占쎈�占쎈빍占쎈뼄.
		    // 占쎈뼄占쎌젫嚥≪뮆�뮉 占쎌뵠 �겫占썽겫袁⑹뱽 �뜮袁⑹グ占쎈빍占쎈뮞 嚥≪뮇彛낉옙肉� 筌띿쉳苡� 占쎈땾占쎌젟占쎈퉸占쎈튊 占쎈�占쎈빍占쎈뼄.
          service.updateQuantity(shop_num, shop_quantity, shop_m_id);
             
          return "success";  // 占쎌굢占쎈뮉 占쎈씜占쎈쑓占쎌뵠占쎈뱜揶쏉옙 占쎄쉐�⑤벏六쏙옙�뱽 占쎈르占쎌벥 占쎌벓占쎈뼗 筌롫뗄�뻻筌욑옙
      }   
      
      
      @PostMapping("delete")
      public String deleteItems(Principal seid,ShoppingCartDTO sdto) {
    	  
    	// 疫꿸퀣�� �굜遺얜굡
    				// shop_m_id = sdto.getShop_m_id();

    				  String shop_m_id = (String) seid.getName();

    				int check = service.deleteCart(sdto.getShop_num(), shop_m_id);
    				if (check == 1) {
    				   	
    					  return "redirect:/member/shoppingCartForm"; // 占쎌굢占쎈뮉 占쎈씜占쎈쑓占쎌뵠占쎈뱜揶쏉옙 占쎄쉐�⑤벏六쏙옙�뱽 占쎈르占쎌벥 占쎌벓占쎈뼗 筌롫뗄�뻻筌욑옙
    				} else {
    					return "error";
    				}	
    			}
      
      
      //�꽑�깮 �궘�젣
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
    	  System.out.print("占쎌겱占쎌삺 嚥≪뮄�젃占쎌뵥占쎈립 占쎈솇筌띲끉�쁽 占쎈툡占쎌뵠占쎈탵======================================================"+pm_c_id);
    	  System.out.print("占쎄돌�몴占� 筌≪뮉釉� 占쎌돳占쎌뜚  占쎈툡占쎌뵠占쎈탵======================================================"+pm_m_id);
    	  
       
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
         System.out.print("占쎌겱占쎌삺 嚥≪뮄�젃占쎌뵥占쎈립 占쎄텢占쎌뒠占쎌쁽 占쎈툡占쎌뵠占쎈탵======================================================" + ppic_m_id);
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
          return "redirect:/member/pPickList"; // 占쎄쉐�⑨옙 占쎈뻻 獄쏆꼹�넎占쎈막 �눧紐꾩쁽占쎈였
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

        System.out.println("pm_num=================================" + pm_num);
        System.out.println("pm_c_id=================================" + pm_c_id);
        System.out.println("pm_m_id=================================" + pm_m_id);
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
  	  
  	  String add_m_id = (String) seid.getName();
  	  System.out.print("add_m_id  ======================================================"+add_m_id);
  	  
  	  List<MemAddressDTO> AddrList = service.addressCheck(add_m_id,mdto,adto );
  	  model.addAttribute("add_m_id", add_m_id);
  	  model.addAttribute("AddrList", AddrList);
  	  model.addAttribute("mdto", mdto);
  	  model.addAttribute("adto", adto);
  	  
  	  return "member/address/addressForm";
    }
   
    
 
    
    @RequestMapping("updateAddr")
    public String updateAddr(MemAddressDTO adto, Authentication authentication) {
        String add_m_id = authentication.getName();
        adto.setAdd_m_id(add_m_id);
        service.updateAddr(adto);
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
    


}