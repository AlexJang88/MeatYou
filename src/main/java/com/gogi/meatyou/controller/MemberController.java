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
 //濡쒓렇�씤  20231225 �씠�룄以�
   
   
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
   

	//濡쒓렇�븘�썐
   @RequestMapping("customLogout")
   public String doLogout(HttpSession session) {
         session.invalidate();
   //   return "member/loginSequrity/login";
         return "redirect:../../main/main";
   }
   
	
	//�쉶�썝媛��엯 
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) {
        return "member/inputForm";
        
    }
    
    
    //�쉶�썝媛��엯 
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
        // �궗�슜�옄 �젙蹂� �뾽�뜲�씠�듃
        dto.setM_id(m_id);

        // �쉶�썝 �긽�깭 �뾽�뜲�씠�듃
        Map<String, Object> statusParamMap = new HashMap<>();
        statusParamMap.put("m_id", m_id);
        service.updateMemberStatus(dto);
        service.insertIntoCusDetail(cdto);
    	return "member/saller/sallerInputPro"; // �씠 遺�遺꾩씠 �젙�긽�쟻�쑝濡� �떎�뻾�릺怨� �엳�뒗吏� �솗�씤
    }
    
    
    
    
    
    //�쉶�썝�젙蹂댁닔�젙 
    @RequestMapping("/modify")
    public String modify(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modify";
    }
  //�젙蹂댄솗�씤 
    @RequestMapping("/modifyForm")
    public String modifyForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modifyForm";
    }
    
    //�젙蹂댄솗�씤 
    @RequestMapping("/modifyPro")
    public String modifyPro(MemberDTO dto, Authentication authentication) {
        String m_id = authentication.getName();
        dto.setM_id(m_id);
        service.userUpdate(dto);
        return "member/myPage/modifyPro";
    }
  //�쉶�썝�깉�눜  (�궗�떎�긽 status 蹂�寃�)
   @RequestMapping("deleteForm")
   public String deleteForm() {
      return "member/delete/deleteForm";
   }
   
 //�쉶�썝 �깉�눜  : �뒪�뀒�씠�꽣�뒪 1000�쑝濡� 蹂�寃�
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
   
   //�쉶�썝�깉�눜  (�궗�떎�긽 status 蹂�寃�)
   @RequestMapping("sallerDelete")
   public String sallerDelete() {
      return "member/deleteSaller/sallerDelete";
   }
   
   //�쉶�썝 �깉�눜  : �뒪�뀒�씠�꽣�뒪 1001�쑝濡� 蹂�寃�
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
   
 
   

   
 //�옣諛붽뎄�땲 蹂댁씠湲� + �럹�씠吏� 泥섎━ 
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
          System.out.print("占쎈뻻占쎄괠�뵳�뗫뼒 占쎌넇占쎌뵥======================================================"+shop_m_id);

          List<ShoppingCartDTO> shoppingCartList = service.getShoppingCartItemsPaged(shop_m_id, page, pageSize, sdto, pdto,pddto);

          int totalItemCount = service.getTotalShoppingCartItems(shop_m_id);

          int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
          
          System.out.println("�럹�씠吏� �겕湲�  ============= ="+pageSize);
	    	System.out.println("�럹�씠吏� ============ ="+page);
	    	System.out.println("珥앺럹�씠吏��뒗 ============= ="+totalPage);
	    	System.out.println("珥� 移댁슫�듃   =================="+totalItemCount);
          model.addAttribute("shoppingCartList", shoppingCartList);
          model.addAttribute("totalPrice", totalPrice);
          model.addAttribute("page", page);
          model.addAttribute("pageSize", pageSize);
          model.addAttribute("totalPage", totalPage);
          // 선택된 상품 목록을 모델에 추가하여 클라이언트에 다시 전달
          model.addAttribute("selectedProducts", selectedProducts);
          return "member/shoppingCart/shoppingCartForm";
      }
      
    //�닔�웾蹂�寃� 
      @RequestMapping("updateQuantity")
      public @ResponseBody String updateQuantity(Principal seid,int shop_num,int shop_quantity) {
           String shop_m_id = (String) seid.getName();
         
           // �뿬湲곗뿉�꽌 �닔�웾 �뾽�뜲�씠�듃 濡쒖쭅�쓣 �닔�뻾�빀�땲�떎.
		    // �떎�젣濡쒕뒗 �씠 遺�遺꾩쓣 鍮꾩쫰�땲�뒪 濡쒖쭅�뿉 留욊쾶 �닔�젙�빐�빞 �빀�땲�떎.
          service.updateQuantity(shop_num, shop_quantity, shop_m_id);
             
          return "success";  // �삉�뒗 �뾽�뜲�씠�듃媛� �꽦怨듯뻽�쓣 �븣�쓽 �쓳�떟 硫붿떆吏�
      }   
      
      
      @PostMapping("delete")
      public String deleteItems(Principal seid,ShoppingCartDTO sdto) {
    	  
    	// 湲곗〈 肄붾뱶
    				// shop_m_id = sdto.getShop_m_id();

    				  String shop_m_id = (String) seid.getName();

    				int check = service.deleteCart(sdto.getShop_num(), shop_m_id);
    				if (check == 1) {
    				   	
    					  return "redirect:/member/shoppingCartForm"; // �삉�뒗 �뾽�뜲�씠�듃媛� �꽦怨듯뻽�쓣 �븣�쓽 �쓳�떟 硫붿떆吏�
    				} else {
    					return "error";
    				}	
    			}
      
      
      //선택 삭제
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

      //선택 주문
      @PostMapping("/orderSelectedItems")
      public void orderSelectedItems(@RequestBody List<String> selectedShopNums, HttpSession session) {
          // 세션에 선택한 상품 정보 저장
          session.setAttribute("selectedShopNums",selectedShopNums);
          System.out.println("selectedShopNums================================"+selectedShopNums);
          // 처리 완료 후 리로드 또는 다른 동작 수행
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
        //  int totalPrice = sdto.getQuantity() * sdto.getP_price();
          System.out.print("�쁽�옱 濡쒓렇�씤�븳 �궗�슜�옄 �븘�씠�뵒======================================================"+pm_m_id);

          // �꽌鍮꾩뒪�뿉�꽌 媛��졇�삩 怨좉컼�쓽 �뵿誘� 紐⑸줉�쓣 �럹�씠吏뺥븯�뿬 媛��졇�샃�땲�떎.
          List<PickMeDTO> pickMekList = service.pickMeCountPage(pm_m_id, page, pageSize, pdto, cdto);
          // �꽌鍮꾩뒪�뿉�꽌 媛��졇�삩 怨좉컼�쓽 �뵿誘� 紐⑸줉�쓽 珥� �븘�씠�뀥 �닔瑜� 媛��졇�샃�땲�떎.
          int totalItemCount = service.pickMeCount(pm_m_id,p_m_id );
          // 珥� �럹�씠吏� �닔瑜� 怨꾩궛�빀�땲�떎.
          int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
          
             System.out.println("�럹�씠吏� �겕湲�  ============= ="+pageSize);
             System.out.println("�쁽�옱 �럹�씠吏� ============ ="+page);
             System.out.println("珥� �럹�씠吏��닔 ============= ="+totalPage);
             System.out.println("珥� �븘�씠�뀥 �닔   =================="+totalItemCount);
          // 酉� �럹�씠吏�濡� �쟾�떖�븷 紐⑤뜽�뿉 �뜲�씠�꽣瑜� 異붽��빀�땲�떎.
          model.addAttribute("pickMekList", pickMekList);
          //model.addAttribute("totalPrice", totalPrice);
          model.addAttribute("page", page);
          model.addAttribute("pageSize", pageSize);
          model.addAttribute("totalPage", totalPage);

          return "member/pickMe/pickMe";
      }
      
      // �뵿誘� 紐⑸줉 媛��졇�삤湲�~~~~~~~~~~
      
      @RequestMapping("SallerPickMe")
      public String SallerPickMeList(
    		  Principal seid,
    		  Model model,
    		  @RequestParam(defaultValue = "1") int page,  // �쁽�옱 �럹�씠吏� 踰덊샇, 湲곕낯媛� 1
    		  @RequestParam(defaultValue = "7") int pageSize,  // �럹�씠吏��떦 蹂댁뿬吏� �빆紐� �닔, 湲곕낯媛� 7
    		  PickMeDTO pdto
    		  ) {
    	  
    	  String pm_c_id = (String) seid.getName();
    	  String pm_m_id=pdto.getPm_m_id();
    	  //  int totalPrice = sdto.getQuantity() * sdto.getP_price();
    	  System.out.print("�쁽�옱 濡쒓렇�씤�븳 �뙋留ㅼ옄 �븘�씠�뵒======================================================"+pm_c_id);
    	  System.out.print("�굹瑜� 李쒗븳 �쉶�썝  �븘�씠�뵒======================================================"+pm_m_id);
    	  
       
    	  // �꽌鍮꾩뒪�뿉�꽌 媛��졇�삩 怨좉컼�쓽 �뵿誘� 紐⑸줉�쓣 �럹�씠吏뺥븯�뿬 媛��졇�샃�땲�떎.
    	  List<PickMeDTO> SallerPickMeList = service.SallerpickMeCountPage(pm_m_id,pm_c_id, page, pageSize, pdto );
    	  // �꽌鍮꾩뒪�뿉�꽌 媛��졇�삩 怨좉컼�쓽 �뵿誘� 紐⑸줉�쓽 珥� �븘�씠�뀥 �닔瑜� 媛��졇�샃�땲�떎.
    	  int totalItemCount = service.SallerpickMeCount(pm_m_id,pm_c_id);
    	  // 珥� �럹�씠吏� �닔瑜� 怨꾩궛�빀�땲�떎.
    	  int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
    	  
    	  System.out.println("�럹�씠吏� �겕湲�  ============= ="+pageSize);
    	  System.out.println("�쁽�옱 �럹�씠吏� ============ ="+page);
    	  System.out.println("珥� �럹�씠吏��닔 ============= ="+totalPage);
    	  System.out.println("珥� �븘�씠�뀥 �닔   =================="+totalItemCount);
    	  // 酉� �럹�씠吏�濡� �쟾�떖�븷 紐⑤뜽�뿉 �뜲�씠�꽣瑜� 異붽��빀�땲�떎.
    	  model.addAttribute("SallerPickMeList", SallerPickMeList);
    	  //model.addAttribute("totalPrice", totalPrice);
    	  model.addAttribute("page", page);
    	  model.addAttribute("pageSize", pageSize);
    	  model.addAttribute("totalPage", totalPage);
    	  
    	  return "member/SallerPickMe/SallerPickMe";
      }
      
      

      @PostMapping("deleteHim")  
      public String deleteHim(Principal seid, PickMeDTO pdto) {
         // �씠�쟾 二쇱꽍 �궡�슜
         // shop_m_id = sdto.getShop_m_id();

         // �닔�젙 �썑 二쇱꽍
         String pm_m_id = (String) seid.getName();

         int check = service.deleteHim(pdto.getPm_num(), pm_m_id);
         if (check == 1) {
        	  return "redirect:/member/pickMe"; // �꽦怨� �떆 諛섑솚�븷 臾몄옄�뿴
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
         // 濡쒓렇�씤�븳 �궗�슜�옄�쓽 �븘�씠�뵒瑜� 媛��졇�샃�땲�떎.
         String ppic_m_id = (String) seid.getName();
         pppdto.setPm_m_id(ppic_m_id);
         System.out.print("�쁽�옱 濡쒓렇�씤�븳 �궗�슜�옄 �븘�씠�뵒======================================================" + ppic_m_id);

         // �삱諛붾Ⅸ 二쇱꽍
         // �삱諛붾Ⅸ 二쇱꽍 �궡�슜�쓣 �뿬湲곗뿉 �옉�꽦�빀�땲�떎.
         // 留� �삎�깭�쓽 �뙆�씪誘명꽣, �궗�슜�옄 �븘�씠�뵒, �럹�씠吏� 踰덊샇, �럹�씠吏� �겕湲�, PPicDTO, ProductDTO, MemberDTO, PPic 踰덊샇
         List<PPicDTO> pPickList = service.pPickCountPages(ppic_m_id, params, page, pageSize, ppdto, pdto,mdto, ppic_num,cdto  );
         
         // �꽌鍮꾩뒪瑜� �넻�빐 媛��졇�삩 �뵿 由ъ뒪�듃�쓽 珥� �븘�씠�뀥 �닔瑜� 怨꾩궛�빀�땲�떎.
         int totalItemCount = service.pPickCount(ppic_m_id, ppic_num );

         // �럹�씠吏� �닔瑜� 怨꾩궛�빀�땲�떎.
         int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
         
         // �뵒踰꾧퉭�슜 異쒕젰臾�
         System.out.println("�럹�씠吏� �겕湲�  ============= =" + pageSize);
         System.out.println("�쁽�옱 �럹�씠吏� ============ =" + page);
         System.out.println("珥� �럹�씠吏��닔 ============= =" + totalPage);
         System.out.println("珥� �븘�씠�뀥 �닔   ==================" + totalItemCount);

         // 紐⑤뜽�뿉 �뜲�씠�꽣瑜� 異붽��빀�땲�떎.
         model.addAttribute("pPickList", pPickList);
         model.addAttribute("page", page);
         model.addAttribute("pageSize", pageSize);
         model.addAttribute("totalPage", totalPage);
         model.addAttribute("ppic_num", ppic_num);
         model.addAttribute("ppic_m_id", ppic_m_id);

         // 酉� �럹�씠吏�濡� �씠�룞�빀�땲�떎.
         return "member/ppick/pPickList";
      }

   
    @PostMapping("deletePick")  
    public String deletePick(Principal seid, PPicDTO ppdto) {
       // �씠�쟾 二쇱꽍 �궡�슜
       // shop_m_id = sdto.getShop_m_id();

       // �닔�젙 �썑 二쇱꽍
       String ppic_m_id = (String) seid.getName();

       int check = service.deleteP_item(ppdto.getPpic_num(), ppic_m_id);
       if (check == 1) {
          return "redirect:/member/pPickList"; // �꽦怨� �떆 諛섑솚�븷 臾몄옄�뿴
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
  	  System.out.print("�굹瑜� 李쒗븳 �쉶�썝  �븘�씠�뵒======================================================"+add_m_id);
  	  
  	  List<MemAddressDTO> AddrList = service.addressCheck(add_m_id,mdto,adto );
  	  model.addAttribute("add_m_id", add_m_id);
  	  //model.addAttribute("totalPrice", totalPrice);
  	  model.addAttribute("mdto", mdto);
  	  model.addAttribute("adto", adto);
  	  
  	  return "member/address/addressForm";
    }
    
    
    /*
     
    @RequestMapping("/addressForm")
    public String addressForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemAddressDTO adto = service.addressCheck(username);
        model.addAttribute("dto", adto);
        return "member/address/addressForm";
    }
    */
    @RequestMapping("/updateAddr")
    public String updateAddr(MemAddressDTO adto, Authentication authentication) {
        String add_m_id = authentication.getName();
        adto.setAdd_m_id(add_m_id);
        service.updateAddr(adto);
        return "redirect:/member/addressForm";	
    }
    
    @RequestMapping("/deleteAddr")
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