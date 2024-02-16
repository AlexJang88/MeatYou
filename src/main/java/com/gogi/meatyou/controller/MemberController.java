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
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.gogi.meatyou.repository.MemberMapper;
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
		model.addAttribute("key", "fcaac1b29853acd91d3df7f95bfa316f");
		model.addAttribute("uri", "http://localhost:8080/member/loginpro");
   //   return "member/loginSequrity/login";
         return "member/loginSequrity/login";
   }

	@RequestMapping("login")
	public String login(Model model) {
		model.addAttribute("key", "fcaac1b29853acd91d3df7f95bfa316f");
		model.addAttribute("uri", "http://localhost:8080/member/loginpro");
		//model.addAttribute("uri", "http://localhost:8080/test/loginpro");
		return "member/login";
	} 
	 
   
	@RequestMapping("loginpro")
	public String loginPro(@RequestParam(value = "code", required = false) String code, MemberDTO dto, HttpSession session) throws Throwable {
	    String token = service.getAccessToken(code);
	    String m_id = dto.getM_id();

	    HashMap<String, Object> userInfo = service.getUserInfo(token); 
	    Object mIdObject = userInfo.get("m_id");

	    String mId = (mIdObject != null) ? mIdObject.toString() : null;

	    boolean isUserRegistered = service.memberList(mId);
	    
	    System.out.println("m_id에 대한 memberList 결과: " + dto.getM_id() + userInfo.get("m_id") + "는 " + isUserRegistered);
	    System.out.println("Database Check Result: " + isUserRegistered);

	    
	    	
	    // Register the user if not already registered
	    if(isUserRegistered) {
	    	String m_id2=dto.getM_id();
	    	String passwd2=dto.getPasswd();
	    	setLoggedInUser((String) userInfo.get("m_id"),dto.getPasswd());
	    	return "redirect:/main/main?m_id="+m_id2+"&passwd="+passwd2;
		        
	    }
	    else if (!isUserRegistered) {
	        // Populate MemberDTO with Kakao user information
	        dto.setM_id((String) userInfo.get("m_id"));
	        dto.setEmail((String) userInfo.get("email"));
	        dto.setM_name((String) userInfo.get("name"));
	        dto.setPhone((String) userInfo.get("phone_number"));

	        // Register the user
	        service.insertKaKao(dto);
	        service.shoppingCart(dto.getM_id()); 
	        service.shoppingCart_seq(dto.getM_id());
	        service.pick_me_seq(dto.getM_id());
	        service.pick_me(dto.getM_id());
	        service.p_pick_seq(dto.getM_id());
	        service.prefer(dto.getM_id());
	        service.p_pick(dto.getM_id());

	        // Set up Spring Security authentication
	        setLoggedInUser(dto.getM_id(), dto.getPasswd());
	        System.out.println("사용자가 등록되어 있지 않습니다. m_id: " + userInfo.get("m_id"));
	        return "redirect:/main/main";
	    } else {
	        // Set up Spring Security authentication for existing user
	        setLoggedInUser((String) userInfo.get("m_id"), dto.getPasswd());
	        System.out.println("이미 가입된 사용자입니다. m_id: " + userInfo.get("m_id"));
	        return "redirect:/main/main";
	    }

	    // Redirect to the main page
	}

	private void setLoggedInUser(String m_id, String passwd) {
	    // Set up Spring Security authentication
	    Authentication authentication = new UsernamePasswordAuthenticationToken(m_id, passwd, new ArrayList<>());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}
    
	@GetMapping("/tokenLogout")
	public String kakaoLogout(HttpSession session) {
	    // 세션에서 액세스 토큰 얻기
	    String accessToken = (String) session.getAttribute("access_token");

	    if (accessToken != null) {
	        // 얻은 액세스 토큰을 사용하여 로그아웃
	        service.tokenLogout(accessToken);

	        // 세션에서 액세스 토큰 제거
	        session.removeAttribute("access_token");
	    }

	    // 로그아웃 성공 페이지로 리다이렉트
	    return "redirect:/main/main";
	}
	 
	
	    @GetMapping("callback")
	    public String kakaoCallback(String code, HttpSession session)throws Throwable {
	        // Handle Kakao callback and obtain access token
	        String accessToken = service.getAccessToken(code); 

	        // Store access token in session or database
	        session.setAttribute("access_token", accessToken);

	        // Redirect to a success page
	        return "redirect:/main/main";
	    }
	
   
   
   
   

   @RequestMapping("customLogout")
   public String doLogout(HttpSession session) {session.invalidate();  return "redirect:../../main/main";  }
   
   
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) { 
    	
    	return "member/inputForm";
 }
    
    
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
 
  //아이디 중복체크
  		@PostMapping("/idCheck")
  		@ResponseBody
  		public int idCheck(@RequestParam("m_id") String m_id) {
  			int check = service.idCheck(m_id);
  			return check;
  		}
  		
  		//이메일 중복체크
  		@PostMapping("/eMailCheck")
  		@ResponseBody
  		public int emailCheck(@RequestParam("email") String email) {
  			int check = service.eMailCheck(email);
  			return check;
  		}
  		
  		@PostMapping("/deleteCheck")
  		@ResponseBody
  		public int deleteCheck(@RequestParam("passwd") String passwd) {
  			int check = service.deleteCheck(passwd);
  			return check;
  		}
  		
  		
  		
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {  String m_id = authentication.getName(); dto.setM_id(m_id); Map<String, Object> statusParamMap = new HashMap<>();  statusParamMap.put("m_id", m_id);  service.updateMemberStatus(dto);  service.insertIntoCusDetail(cdto);
       return "member/saller/sallerInputPro"; 
    }
    
    
    
    @RequestMapping("/sallerInputForm")
	public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {
    	String username = authentication.getName(); 
    	MemberDTO dto = service.getUser(username); model.addAttribute("dto", dto);
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
   public String deletePro(Model model, String passwd, HttpSession session, MemberDTO dto) {
       String m_id = (String) session.getAttribute("m_id");
       int check= service.userDelete(dto);

       if (check == 1) {
           session.invalidate();
           model.addAttribute("check", check);
       }
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
    public String shoppingCartForm(Principal seid,Model model,@RequestParam(value="page",defaultValue = "1") int page){	
    	
    	String id = seid.getName();
    	System.out.println("id==========="+id);
    	List<OrderwithCouponDTO> cartdto =Collections.EMPTY_LIST;
		System.out.println("page====="+page);
    	cartdto = service.ShoppingCartAndProduct(id,page,model);
    	
    	
    	String m_id="";
        int CartCNT=0;
        
        if(seid != null) {
      	 m_id = (String)seid.getName(); 
           model.addAttribute("m_id", m_id);
           String shop_m_id = (String)seid.getName();
           CartCNT = service.ShoppingCartCNT(shop_m_id);
          model.addAttribute("CartCNT", CartCNT);
        } else {
           CartCNT=0;
           model.addAttribute("CartCNT", CartCNT);
        }

        int pickCNT=0;
        int pick_P_CNT=0;
        if(seid != null) {
      	  String ppic_m_id = (String)seid.getName();
      	  pickCNT = service.pickCNT(ppic_m_id);
      	  pick_P_CNT = service.pick_P_CNT(ppic_m_id);
      	  model.addAttribute("pickCNT", pickCNT);
      	  model.addAttribute("pick_P_CNT", pick_P_CNT);
        } else {
      	  pickCNT=0;
      	  pick_P_CNT=0;
      	  model.addAttribute("pickCNT", pickCNT);
      	  model.addAttribute("pick_P_CNT", pick_P_CNT);
        }


    	for(OrderwithCouponDTO temp : cartdto) {
    	memberMap.put("p_num", temp.getP_num());
    	//temp.setAddressList(service.combined_address(id));
    	 temp.setCoupons(service.getProductCoupon(memberMap));
    	}
    	model.addAttribute("cartdto", cartdto);
    	
 	   return "member/shoppingCart/shoppingCartForm";
    }
  
    @RequestMapping("orderPageOne")
    public String orderPageOne(Principal peid,Model model,OrderwithCouponDTO dto) {
        
       String add_m_id = peid.getName();   
       		for(int i=0;i<dto.getArr_cp_num().length;i++) {
       			System.out.println("==pageone=="+dto.getArr_cp_num()[i]);
       		}
          dto.setAdd_m_id(add_m_id);
          List<String> AddrList = service.combined_address(add_m_id);
          MemberDTO mdto = service.getUser(add_m_id);         
        model.addAttribute("add_m_id", add_m_id);
        model.addAttribute("AddrList", AddrList);
        model.addAttribute("mdto", mdto);
        model.addAttribute("dto", dto);
        return "member/order/orderPageOne";
    } 
    
    
     
    @RequestMapping("orderPageTwo")
    public String orderPageTwo(Principal peid, Model model ,OrderwithCouponDTO dto) {
    	ArrayList<OrderwithCouponDTO> cdto = new ArrayList<OrderwithCouponDTO>();
        String add_m_id = peid.getName();
        MemberDTO mdto = service.getUser(add_m_id);         
        memberMap.put("m_id", add_m_id);
            int [] temp_shop_num = dto.getArr_shop_num();
            int totquantity =0;
            int totalprice=0;
            int dere_pay = 0;
            int price=0;
            int discount=0;
            int totalAmount=0;
            for (int i = 0; i < temp_shop_num.length; i++) {
            	System.out.println("test==="+dto.getArr_cp_num()[i]);
				OrderwithCouponDTO tempdto = new OrderwithCouponDTO();
				memberMap.put("shop_num", dto.getArr_shop_num()[i]);
				memberMap.put("cp_num", dto.getArr_cp_num()[i]);
				tempdto=service.getCartbyNum(memberMap);
				totalprice+=tempdto.getShop_quantity()*tempdto.getP_price();
				totquantity+=tempdto.getShop_quantity();
				tempdto.setOrder_dere_pay(2500);
				dere_pay+= dto.getOrder_dere_pay()+tempdto.getOrder_dere_pay();
				tempdto.setCp_num(dto.getArr_cp_num()[i]);
				tempdto.setSelectedAddress(dto.getSelectedAddress());
				if(dto.getArr_cp_num()[i]==0) {
					tempdto.setCp_price(0);
				}else {
					discount+=service.getCouponPrice(dto.getArr_cp_num()[i]);
					tempdto.setCp_price(service.getCouponPrice(dto.getArr_cp_num()[i]));
				}
				cdto.add(tempdto);
			}
            dto.setOrder_dere_pay(dere_pay);
            dto.setTotal_quantity(totquantity);
            dto.setTotal_amount(totalprice+dere_pay-discount);
            dto.setTot_price(totalprice);
            dto.setCp_price(discount);
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
           // order_m_id揶쏉옙 null占쎌뵥 野껋럩�뒭占쎈퓠 占쏙옙占쎈립 筌ｌ꼶�봺
           // 占쎌굙�몴占� 占쎈굶占쎈선, 嚥≪뮄�젃�몴占� �빊�뮆�젾占쎈릭椰꾧퀡援� 占쎈뼄�몴占� 疫꿸퀡�궚揶쏅�れ뱽 占쎄퐬占쎌젟占쎈막 占쎈땾 占쎌뿳占쎈뮸占쎈빍占쎈뼄.
           model.addAttribute("errorMessage", "order_m_id is null");
       }

       return "member/order/PaymentHistory";
    }
    
    //占쎈연疫꿸퀣苑� �겫占쏙옙苑� 筌욑옙占쎌넎
    
    @GetMapping("/mailCheck")
	@ResponseBody
	public String mailCheck(String email,String phoneInput,String m_name,String m_id) {
		System.out.println("占쎌뵠筌롫뗄�뵬 占쎌뵥筌앾옙 占쎌뒄筌ｏ옙占쎌뵠 占쎈굶占쎈선占쎌긾!");
		System.out.println("占쎌뵠筌롫뗄�뵬 占쎌뵥筌앾옙 占쎌뵠筌롫뗄�뵬 : " + email);
		return service.joinEmail(email,m_name,phoneInput,m_id);
	}
    
    //占쎈툡占쎌뵠占쎈탵 筌≪뼐由�
    @RequestMapping("idfind")
    public String idfind(Model model,  HttpSession session){
    	Integer check = (Integer) session.getAttribute("check");
    	model.addAttribute("check", check);
    	session.removeAttribute("check");
    	return "member/loginSequrity/idfind";
    } 
    
    @RequestMapping("idfindPro")
    public String idfindPro(Model model,MemberDTO memberdto, HttpSession session){
    	
    	int check = service.findId(memberdto); // 占쎈툡占쎌뵠占쎈탵 占쎌읈占쎌넅甕곕뜇�깈 筌띿쉶�뮉筌욑옙 占쎌넇占쎌뵥    	
    	String url="";
    	if(check == 1) {
    		service.getDbId(model, memberdto);
    		url= "member/loginSequrity/idfindweb";
    	}else {
    		session.setAttribute("check", check);
    		url="redirect:/member/idfind?check="+check;
    	}
 
    	return url;
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
    	int check = service.findPw(memberdto); // �뜮袁⑨옙甕곕뜇�깈 筌≪뼚�뱽占쎈르 占쎌젟癰귨옙  	
    	
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
 