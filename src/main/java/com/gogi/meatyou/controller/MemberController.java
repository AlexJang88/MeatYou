package com.gogi.meatyou.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.service.MemberService;





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
	//회원탈퇴  (사실상 status 변경)
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
		return "member/delete/deletePro";
	}
	
	 
	
	@RequestMapping("shoppingCartForm")
	public String shoppingCartForm(Principal seid, Model model,ShoppingCartDTO sdto,ProductDTO pdto ) {
	    String shop_m_id = (String) seid.getName();
	    int totalPrice=sdto.getQuantity()*sdto.getP_price();
	    System.out.print("시큐리티 확인======================================================"+shop_m_id);
	    
	    // 여기서 service를 통해 해당 회원의 장바구니 정보를 가져옵니다.
	    List<ShoppingCartDTO> shoppingCartList = service.ShoppingCartAndProduct(shop_m_id,sdto,pdto );
	    
	    // 모델에 장바구니 정보를 추가합니다.
	    model.addAttribute("shoppingCartList", shoppingCartList);
	    model.addAttribute("totalPrice", totalPrice);

	    return "member/shoppingCart/shoppingCartForm";
	}
	
    
    /*
	// 수량 올리기 
	@PostMapping("/shoppingCartFormPro")
	public String modifyQuantity(@RequestParam("quantity") ShoppingCartDTO sdto,  @RequestParam("shop_num") int shopNum, Model model) {
	    int result = service.increaseQuantity(sdto);

	    // result를 이용하여 추가적인 처리가 필요한 경우 구현

	    // 예시로 모델에 결과 값을 추가하여 뷰에 전달
	    model.addAttribute("result", result);

	    return "redirect:/member/shoppingCart/shoppingCartFormPro"; // 적절한 뷰로 리다이렉트
	}

	// 수량 내리기 
	@PostMapping("/decreaseQuantity")
	public String decreaseQuantity(@ModelAttribute("shoppingCartDTO") ShoppingCartDTO sdto, Model model) {
	    int result = service.decreaseQuantity(sdto);

	    // result를 이용하여 추가적인 처리가 필요한 경우 구현

	    // 예시로 모델에 결과 값을 추가하여 뷰에 전달
	    model.addAttribute("result", result);

	    return "redirect:/member/shoppingCart/shoppingCartFormPro"; // 적절한 뷰로 리다이렉트
	}
    
    */
	/*
// 수량 증가 처리 방법1  updateQuantity
	 @PostMapping("/updateQuantity")
	    @ResponseBody
	    public String updateQuantity(@RequestParam("shop_num") int shop_num,
	    							@RequestParam("quantity") int    quantity,
	                                 @RequestParam("shop_m_id") 	String shop_m_id) {
	        try {
	        	 service.updateQuantity(shop_num,   quantity, shop_m_id);
	            return "success";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "error";
	        }
	    }
	 */
	 
	 
	 @PostMapping("/updateQuantity")
	 @ResponseBody
	 public ResponseEntity<String> updateQuantity(@RequestParam("shop_num") int shop_num,
	                                             @RequestParam("quantity") int quantity,
	                                             @RequestParam("shop_m_id") String shop_m_id,
	                                             Principal seid
			 													) {
		 	
	     try {
	         // 여기서 비즈니스 로직을 수행
	         // 예시: 수량 업데이트
	         service.updateQuantity(shop_num, quantity, shop_m_id);

	         // 성공 시 ResponseEntity.ok() 반환
	         return ResponseEntity.ok("success");
	     } catch (Exception e) {
	         e.printStackTrace();
	         // 실패 시 ResponseEntity.status()를 사용하여 적절한 HTTP 상태 코드와 메시지 반환
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
	     }
	 }
}
