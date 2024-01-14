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

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
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
	//로그인  20231225 이도준
	
	
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
		  

	//	return "member/loginSequrity/login";
			return "member/loginSequrity/login";
	}
	
	
	//로그아웃
	@RequestMapping("customLogout")
	public String doLogout(HttpSession session) {
			session.invalidate();
	//	return "member/loginSequrity/login";
			return "redirect:../../main/main";
	}
	
	
	//회원가입 
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) {
        return "member/inputForm";
        
    }
    
    
    //회원가입 
    @RequestMapping("inputPro")
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {
        int check = service.insertMember(dto);
        
        if (check > 0) {
            // insertMember가 정상적으로 실행되었다면 다른 서비스 메소드들을 호출
            service.shoppingCart(dto.getM_id()); // 예시: insertShoppingCart 메소드 호출
            service.shoppingCart_seq(dto.getM_id()); // 예시: insertShoppingCart 메소드 호출
            
            service.pick_me(dto.getM_id()); // 예시: insertPickMe 메소드 호출
            service.pick_me_seq(dto.getM_id()); 
            
            service.p_pick(dto.getM_id()); // 예시: insertPPick 메소드 호출
            service.p_pick_seq(dto.getM_id());
            service.prefer(dto.getM_id());
            // 다른 서비스 메소드들도 유사하게 호출
            
            model.addAttribute("check", check);
            return "member/inputPro";
        } else {
            // insertMember가 실패했을 경우 처리
            // 실패에 대한 로직 구현
            return "errorPage"; // 실패에 대한 페이지로 리다이렉트 또는 처리
        }
    }

    //판매자 신청  1050으로 스테이터스변경 
    @RequestMapping("sallerInputForm")
    public String sallerInputForm(Model model, Authentication authentication,CusDetailDTO cdto) {
    	
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
    	
    	return "member/saller/sallerInputForm";
    	
    }
    
    //판매자 신청  1050으로 스테이터스변경 
    @RequestMapping("sallerInputPro")
    public String sallerInputPro(MemberDTO dto,CusDetailDTO cdto, Authentication authentication) {
        String m_id = authentication.getName();
        // 사용자 정보 업데이트
        dto.setM_id(m_id);

        // 회원 상태 업데이트
        Map<String, Object> statusParamMap = new HashMap<>();
        statusParamMap.put("m_id", m_id);
        service.updateMemberStatus(dto);
        service.insertIntoCusDetail(cdto);
    	return "member/saller/sallerInputPro"; // 이 부분이 정상적으로 실행되고 있는지 확인
    }
    
    
    
    
    
    //회원정보수정 
    @RequestMapping("/modify")
    public String modify(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modify";
    }
//정보확인 
    @RequestMapping("/modifyForm")
    public String modifyForm(Model model, Authentication authentication) {
        String username = authentication.getName();
        MemberDTO dto = service.getUser(username);
        model.addAttribute("dto", dto);
        return "member/myPage/modifyForm";
    }
  //정보수정 
    @RequestMapping("/modifyPro")
    public String modifyPro(MemberDTO dto, Authentication authentication) {
        String m_id = authentication.getName();
        dto.setM_id(m_id);
        service.userUpdate(dto);
        return "member/myPage/modifyPro";
    }
	//회원탈퇴  (사실상 status 변경)
	@RequestMapping("deleteForm")
	public String deleteForm() {
		return "member/delete/deleteForm";
	}
	//회원 탈퇴  : 스테이터스 1000으로 변경
	@RequestMapping("deletePro")
	public String deletePro(Model model , String passwd , HttpSession session,MemberDTO dto) {
		String m_id =(String)session.getAttribute("m_id");
		int check = service.userDelete(dto);
		if(check == 1) {
			session.invalidate();
		}
		model.addAttribute("check",check);
		return "member/delete/deletePro";
	}
	
 
	

	
	//장바구니 보이기 + 페이징 처리 
		@RequestMapping("shoppingCartForm")
		public String shoppingCartForm(
		        Principal seid,
		        Model model,
		        @RequestParam(defaultValue = "1") int page,  // 현재 페이지 번호, 기본값 1
		        @RequestParam(defaultValue = "10") int pageSize,  // 페이지당 표시할 항목 수, 기본값 7
		        ShoppingCartDTO sdto,
		        ProductDTO pdto
		) {
		    String shop_m_id = (String) seid.getName();
		    int totalPrice = sdto.getQuantity() * sdto.getP_price();
		    System.out.print("시큐리티 확인======================================================"+shop_m_id);

		    // 여기서 service를 통해 해당 회원의 특정 범위의 장바구니 정보를 가져옵니다.
		    List<ShoppingCartDTO> shoppingCartList = service.getShoppingCartItemsPaged(shop_m_id, page, pageSize, sdto, pdto);

		    // 여기서 service를 통해 해당 회원의 장바구니 총 상품 개수를 가져옵니다.
		    int totalItemCount = service.getTotalShoppingCartItems(shop_m_id);

		    // 페이징 처리를 위한 계산
		    int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
		    
		    	System.out.println("페이지 크기  ============= ="+pageSize);
		    	System.out.println("페이지 ============ ="+page);
		    	System.out.println("총페이지는 ============= ="+totalPage);
		    	System.out.println("총 카운트   =================="+totalItemCount);
		    // 모델에 장바구니 정보 및 페이징 관련 정보를 추가합니다.
		    model.addAttribute("shoppingCartList", shoppingCartList);
		    model.addAttribute("totalPrice", totalPrice);
		    model.addAttribute("page", page);
		    model.addAttribute("pageSize", pageSize);
		    model.addAttribute("totalPage", totalPage);

		    return "member/shoppingCart/shoppingCartForm";
		}
		
		//수량변경 
		@RequestMapping("updateQuantity")
		public @ResponseBody String updateQuantity(Principal seid,int shop_num,int quantity) {
			  String shop_m_id = (String) seid.getName();
			
		    // 여기에서 수량 업데이트 로직을 수행합니다.
		    // 실제로는 이 부분을 비즈니스 로직에 맞게 수정해야 합니다.
		    service.updateQuantity(shop_num, quantity, shop_m_id);
		    	
		    return "success"; // 또는 업데이트가 성공했을 때의 응답 메시지
		}	
	    //@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
//		@RequestMapping("delete")
		@PostMapping("delete")
		public String deleteItems(Principal seid,ShoppingCartDTO sdto) {
			// 기존 코드
			// shop_m_id = sdto.getShop_m_id();

			// 수정 후 코드
			  String shop_m_id = (String) seid.getName();

			int check = service.deleteCart(sdto.getShop_num(), shop_m_id);
			if (check == 1) {
				 return "redirect:../shoppingCart/shoppingCartForm";
			} else {
				return "error";
			}	
		}
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//판매자 찜~~~~~~~~~~
	
	

	
	@RequestMapping("pickMe")
	public String pPickList(
	        Principal seid,
	        Model model,
	        @RequestParam(defaultValue = "1") int page,  // 현재 페이지 번호, 기본값 1
	        @RequestParam(defaultValue = "7") int pageSize,  // 페이지당 표시할 항목 수, 기본값 7
	        PickMeDTO pdto, CusDetailDTO cdto	) {
	    String pm_m_id = (String) seid.getName();
	  //  int totalPrice = sdto.getQuantity() * sdto.getP_price();
	    System.out.print("시큐리티 확인======================================================"+pm_m_id);

	    // 여기서 service를 통해 해당 회원의 특정 범위의 장바구니 정보를 가져옵니다.
	    List<PickMeDTO> pPickList = service.pickMeCountPage(pm_m_id, page, pageSize,pdto, cdto);

	    // 여기서 service를 통해 해당 회원의 장바구니 총 상품 개수를 가져옵니다.
	    int totalItemCount = service.pickMeCount(pm_m_id);

	    // 페이징 처리를 위한 계산
	    int totalPage = (int) Math.ceil((double) totalItemCount / pageSize);
	    
	    	System.out.println("페이지 크기  ============= ="+pageSize);
	    	System.out.println("페이지 ============ ="+page);
	    	System.out.println("총페이지는 ============= ="+totalPage);
	    	System.out.println("총 카운트   =================="+totalItemCount);
	    // 모델에 장바구니 정보 및 페이징 관련 정보를 추가합니다.
	    model.addAttribute("pPickList", pPickList);
	    //model.addAttribute("totalPrice", totalPrice);
	    model.addAttribute("page", page);
	    model.addAttribute("pageSize", pageSize);
	    model.addAttribute("totalPage", totalPage);

	    return "member/pickMe/pickMe";
	}
	

    //@PreAuthorize("hasRole('ROLE_ANONYMOUS')")
//	@RequestMapping("delete")				
	@PostMapping("deleteHim")  
	public String deleteHim(Principal seid,PickMeDTO pdto) {
		// 기존 코드
		// shop_m_id = sdto.getShop_m_id();

		// 수정 후 코드
		  String pm_m_id = (String) seid.getName();

		int check = service.deleteHim(pdto.getPm_num(), pm_m_id);
		if (check == 1) {
			 return "redirect:../pickMe/pickMe";
		} else {
			return "error";
		}	
	}
	
	

	
	
	
	
	
	
	
	
	
	
    
}
