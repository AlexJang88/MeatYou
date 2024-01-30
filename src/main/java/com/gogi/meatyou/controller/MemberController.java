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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
   
  // @Autowired
 //  private JavaMailSender javaMailSender;
//    @Autowired 
//    private MailSendService mailService;

   // @Autowired
   // private JavaMailSender  mailSender;
//   @Autowired
//   UsersRepository usersRepository;
   
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
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {  int check = service.insertMember(dto); if (check > 0) { service.shoppingCart(dto.getM_id());  service.shoppingCart_seq(dto.getM_id());    service.pick_me_seq(dto.getM_id());  service.pick_me(dto.getM_id());       service.p_pick_seq(dto.getM_id());  service.prefer(dto.getM_id());     service.p_pick(dto.getM_id());        model.addAttribute("check", check);
            return "member/inputPro";
        } else {
            return "errorPage";  
        }    }
    @RequestMapping("sallerInputForm")
    public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {  String username = authentication.getName(); MemberDTO dto = service.getUser(username); model.addAttribute("dto", dto);
       return "member/saller/sallerInputForm";
    }
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {  String m_id = authentication.getName(); dto.setM_id(m_id); Map<String, Object> statusParamMap = new HashMap<>();  statusParamMap.put("m_id", m_id);  service.updateMemberStatus(dto);  service.insertIntoCusDetail(cdto);
       return "member/saller/sallerInputPro"; 
    }
    
    
    
    
    
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

          List<ShoppingCartDTO> shoppingCartList = service.getShoppingCartItemsPaged(shop_m_id, page, pageSize, sdto, pdto,pddto);

          int totalItemCount = service.getTotalShoppingCartItems(shop_m_id);

          int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
          
          model.addAttribute("shoppingCartList", shoppingCartList);
          model.addAttribute("totalPrice", totalPrice);
          model.addAttribute("page", page);
          model.addAttribute("pageSize", pageSize);
          model.addAttribute("totalPage", totalPage);
          // 占쎈쐻占쎈윞占쎈쭡占쎈쐻占쎈윞 눧硫⑤쐻占쎈윥壤쏉옙 占쎈쐻占쎈윞筌띾 ｋ쐻占쎈윥 뜝 룞 삕 癲ル슢 뀈泳 戮⑤뭄占쎈㎜占쎌굲 뜝 럥援  癲ル슢 뀈泳 占썲뜝 럩紐띰옙 쐻占쎈윥占쎈군  뜝 럥 돯占쎄껀占쎈짗占쎌굲占쎈쐻占쎈윥 뵳占쏙옙 쐻占쎈윥占쎈염 占쎈쐻占쎈윞繹먯궍 쐻占쎈윪 앗껊쐻占쎈윪 얠± 쐻占쎈윥占쎄퐯占쎈쐻占쎈윥獄  뭿 쐻占쎈윥占쎈군 占쎈쐻占쎈윥 젆袁  쐻占쎈윥筌묕옙 占쎈쐻占쎈윪占쎌벁占쎈쐻占쎈윥 젆占 
          model.addAttribute("selectedProducts", selectedProducts);
          return "member/shoppingCart/shoppingCartForm";
      }
      
      @RequestMapping("updateQuantity")
      public @ResponseBody String updateQuantity(Principal seid,int shop_num,int shop_quantity) {
           String shop_m_id = (String) seid.getName();
          service.updateQuantity(shop_num, shop_quantity, shop_m_id);
             
          return "success";  //  뜝 럥 맶 뜝 럥 쑋占쎈쨨占쎄퐩占쎌맶 뜝 럥 쑅 뜏類㏃삕  뜝 럥 맶 뜝 럥 쑅 뜝 럥   뜝 럥 맶 뜝 럥 쑅 뜝 럥 걛 뜝 럥 맶 뜝 럥 쑋占쎌뼚짹占쎌맶 뜝 럥 쑅 뛾占쏙옙裕 筌뚮냵 삕 뙴 뵃 삕占쎄뎡  뜝 럥 맶 뜝 럥 쐾 뜝 럥 젾占쎈쐻占쎈쑆泳 占썹뵓怨뺤챾壤쏆룊 삕 뙴 쉻 삕占쎄뎡占쎈쐻占쎈윥 뤃占   뜝 럥 맶 뜝 럥 쑅占쎈ご占쎈뼠占쎌맶 뜝 럥 쑋 뵓怨ㅼ삕  뜝 럥 맶 뜝 럥 쑋 뵓怨뺥 ワ옙 맶 뜝 럥 쑅占쎌젂 뜝占   솾 꺂 뒧占쎈떔 뜝 럥 뵒占쎈쐻占쎈윥筌묕퐦 뵾占쎌뒩占쎈뤈 뜝 럩援 
      }   
      
      
      @PostMapping("delete")
      public String deleteItems(Principal seid,ShoppingCartDTO sdto) {

                  String shop_m_id = (String) seid.getName();

                int check = service.deleteCart(sdto.getShop_num(), shop_m_id);
                if (check == 1) {
                      
                     return "redirect:/member/shoppingCartForm"; //  뜝 럥 맶 뜝 럥 쑋占쎈쨨占쎄퐩占쎌맶 뜝 럥 쑅 뜏類㏃삕  뜝 럥 맶 뜝 럥 쑅 뜝 럥   뜝 럥 맶 뜝 럥 쑅 뜝 럥 걛 뜝 럥 맶 뜝 럥 쑋占쎌뼚짹占쎌맶 뜝 럥 쑅 뛾占쏙옙裕 筌뚮냵 삕 뙴 뵃 삕占쎄뎡  뜝 럥 맶 뜝 럥 쐾 뜝 럥 젾占쎈쐻占쎈쑆泳 占썹뵓怨뺤챾壤쏆룊 삕 뙴 쉻 삕占쎄뎡占쎈쐻占쎈윥 뤃占   뜝 럥 맶 뜝 럥 쑅占쎈ご占쎈뼠占쎌맶 뜝 럥 쑋 뵓怨ㅼ삕  뜝 럥 맶 뜝 럥 쑋 뵓怨뺥 ワ옙 맶 뜝 럥 쑅占쎌젂 뜝占   솾 꺂 뒧占쎈떔 뜝 럥 뵒占쎈쐻占쎈윥筌묕퐦 뵾占쎌뒩占쎈뤈 뜝 럩援 
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
        adto.setAdd_num(add_num);  // set add_num in adto

        List<MemAddressDTO> AddrList = service.addressCheck(add_m_id, mdto, adto, add_num);

        // System.out.println("add_mem_address1 ===================== " + AddrList.get(0).getAdd_mem_address1());

        service.updateAddr(adto, add_m_id, AddrList, add_num, add_mem_address1, add_mem_address2);
        return "redirect:/member/addressForm";
    }
    
    
    
     /*
     @RequestMapping("/find") public String findIDPW() { return"all/find"; }
     
     @GetMapping("/mailCheck")
     @ResponseBody public String mailCheck(String email) {
     System.out.println("이메일 인증 요청이 들어옴!"); System.out.println("이메일 인증 이메일 : " +
      email); return mailService.joinEmail(email); }
   
   @PostMapping("/checkSuccess")
   public String mail(String userEmail1, String userEmail2, Model model) {
      String email = userEmail1+userEmail2;
      int result;
      MemberDTO mdto = usersRepository.FindByEmail(email);      
      if(mdto == null) {
         result = 0;
         model.addAttribute("result", result);
         model.addAttribute("email", email);
      }else {
         result = 1;
         String m_name = mdto.getM_name();
         String m_id = mdto.getM_name();
         model.addAttribute("result", result);
         model.addAttribute("m_name", m_name);
         model.addAttribute("email", email);
      }
      return "user/mail";
   }
*/
    @RequestMapping("orderPageOne")
    public String orderPageOne(Principal peid,Model model,MemAddressDTO adto,MemberDTO mdto,String combined_address) {
        
       String add_m_id = peid.getName();   
          adto.setAdd_m_id(add_m_id);

          List<MemAddressDTO> AddrList = service.combined_address(add_m_id,combined_address, adto);
        MemberDTO dto = service.getUser(add_m_id);         
        
      
      
      
        model.addAttribute("adto", adto);
        model.addAttribute("add_m_id", add_m_id);
        model.addAttribute("AddrList", AddrList);
        model.addAttribute("dto", dto);
        return "member/order/orderPageOne";
    }
    
    @RequestMapping("orderPageTwo")
    public String orderPageTwo(Principal peid,Model model,MemAddressDTO adto,MemberDTO mdto,String combined_address) {
       
       String add_m_id = peid.getName();   
       adto.setAdd_m_id(add_m_id);
       
       List<MemAddressDTO> AddrList = service.combined_address(add_m_id,combined_address, adto);
       MemberDTO dto = service.getUser(add_m_id);         
       model.addAttribute("adto", adto);
       model.addAttribute("add_m_id", add_m_id);
       model.addAttribute("AddrList", AddrList);
       model.addAttribute("dto", dto);
       return "member/order/orderPageTwo";
    }
    
    
    
    
    
    
    
    
    
   /*
    * //이메일 인증
    * 
    * @GetMapping("/mailCheck")
    * 
    * @ResponseBody public String mailCheck(String email) { return
    * mailService.joinEmail(email); }
    */


}


/*
 * @PostMapping("/renamePass") public String renamePass(Model model, String
 * username, Principal principal) { if(username == null) { username =
 * principal.getName(); } model.addAttribute("username", username); return
 * "user/renamePass"; }
 * 
 * @PostMapping("/passPro")
 * 
 * @ResponseBody public String passPro(String password, String username,
 * Principal principal) {
 * 
 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); password
 * = passwordEncoder.encode(password); usersRepository.changePass(password,
 * username); return "success"; }
 */


