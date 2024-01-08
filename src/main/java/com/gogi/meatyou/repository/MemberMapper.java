package com.gogi.meatyou.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MemberMapper {
	public MemberDTO read(String m_id);
	
		//회원가입
	
	 public int insertMember(MemberDTO dto);
	 

	    // Spring Security에서 사용자 정보를 가져오기 위한 메서드
	    public MemberDTO loadUserByUsername(String username);
		
	 	public List<MemberDTO> memberList();
	 
		public MemberDTO member(String m_id);
		
		
		public List<ShoppingCartDTO> shoppingcartCheck(String m_id);
		public void memberUpdate(MemberDTO dto);
		
		
		public int statusAdminChange(MemberDTO dto);
		
		public int statusChange(MemberDTO dto);
		
		
		
		  // 회원 상태 업데이트
	    public int updateMemberStatus(MemberDTO dto);

	    // Cus_detail에 데이터 인서트
	    public int insertIntoCusDetail(CusDetailDTO cdto);
	    public void shoppingCart(String m_id);
	    public void shoppingCart_seq(String m_id);
	    public void pick_me(String m_id);
	    public void pick_me_seq(String m_id);
	    public void p_pick(String m_id);
	    public void p_pick_seq(String m_id);
	    public void prefer(String m_id);



	//	public void ShoppingCartAndProduct(String shop_m_id ) ;
	 // 반환 타입을 List<ShoppingCartDTO>로 수정
	    List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id);
	    
	  // 장바구니 수량 변경 
	    public int upquantity(ShoppingCartDTO sdto);

	    public  int downquantity(ShoppingCartDTO sdto);
	    
}






