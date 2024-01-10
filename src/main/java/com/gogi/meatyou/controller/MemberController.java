package com.gogi.meatyou.controller;

import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	//�α���  20231225 �̵���
	
	
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
	
	
	//�α׾ƿ�
	@RequestMapping("customLogout")
	public String doLogout(HttpSession session) {
			session.invalidate();
	//	return "member/loginSequrity/login";
			return "redirect:../../main/main";
	}
	
	
	//ȸ������ 
    @RequestMapping("inputForm")
    public String inputForm(Model model, HttpSession session) {
        return "member/inputForm";
        
    }
    
    
    
    @RequestMapping("inputPro")
    public String inputPro(Model model, MemberDTO dto, HttpSession session) {
        int check = service.insertMember(dto);
        
        if (check > 0) {
            // insertMember�� ���������� ����Ǿ��ٸ� �ٸ� ���� �޼ҵ���� ȣ��
            service.shoppingCart(dto.getM_id()); // ����: insertShoppingCart �޼ҵ� ȣ��
            service.shoppingCart_seq(dto.getM_id()); // ����: insertShoppingCart �޼ҵ� ȣ��
            
            service.pick_me(dto.getM_id()); // ����: insertPickMe �޼ҵ� ȣ��
            service.pick_me_seq(dto.getM_id()); 
            
            service.p_pick(dto.getM_id()); // ����: insertPPick �޼ҵ� ȣ��
            service.p_pick_seq(dto.getM_id());
            service.prefer(dto.getM_id());
            // �ٸ� ���� �޼ҵ�鵵 �����ϰ� ȣ��
            
            model.addAttribute("check", check);
            return "member/inputPro";
        } else {
            // insertMember�� �������� ��� ó��
            // ���п� ���� ���� ����
            return "errorPage"; // ���п� ���� �������� �����̷�Ʈ �Ǵ� ó��
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
        // ����� ���� ������Ʈ
        dto.setM_id(m_id);

        // ȸ�� ���� ������Ʈ
        Map<String, Object> statusParamMap = new HashMap<>();
        statusParamMap.put("m_id", m_id);
        service.updateMemberStatus(dto);
        service.insertIntoCusDetail(cdto);
    	return "member/saller/sallerInputPro"; // �� �κ��� ���������� ����ǰ� �ִ��� Ȯ��
    }
    
    
    
    
    
    //ȸ���������� 
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
	//ȸ��Ż��  (��ǻ� status ����)
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
	    System.out.print("��ť��Ƽ Ȯ��======================================================"+shop_m_id);
	    
	    // ���⼭ service�� ���� �ش� ȸ���� ��ٱ��� ������ �����ɴϴ�.
	    List<ShoppingCartDTO> shoppingCartList = service.ShoppingCartAndProduct(shop_m_id,sdto,pdto );
	    
	    // �𵨿� ��ٱ��� ������ �߰��մϴ�.
	    model.addAttribute("shoppingCartList", shoppingCartList);
	    model.addAttribute("totalPrice", totalPrice);

	    return "member/shoppingCart/shoppingCartForm";
	}
	
	
	@RequestMapping("updateQuantity")
	public @ResponseBody String updateQuantity(Principal seid,int shop_num,int quantity) {
		  String shop_m_id = (String) seid.getName();
		
	    // ���⿡�� ���� ������Ʈ ������ �����մϴ�.
	    // �����δ� �� �κ��� ����Ͻ� ������ �°� �����ؾ� �մϴ�.
	    service.updateQuantity(shop_num, quantity, shop_m_id);
	    	
	    return "success"; // �Ǵ� ������Ʈ�� �������� ���� ���� �޽���
	}
}
