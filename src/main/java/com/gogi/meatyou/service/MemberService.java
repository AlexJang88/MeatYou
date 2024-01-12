package com.gogi.meatyou.service;


import java.util.List;


import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MemberService  {
    int insertMember(MemberDTO dto);
    
    
    
    public MemberDTO member(String m_id);	
    public List<ShoppingCartDTO> shoppingCartCheck(String m_id);
 // 반환 타입을 List<ShoppingCartDTO>로 수정
	public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) ;
	public void userUpdate(MemberDTO dto);
	
	public MemberDTO getUser(String m_id);
	
	public int userDelete(MemberDTO dto);
	//회원 탈퇴
	public int statusChange(MemberDTO dto);
	//탈퇴하면 체인지되어야 하니까 
	
	public int updateMemberStatus  (MemberDTO dto);
	
	public int insertIntoCusDetail(CusDetailDTO cdto) ;
		
	
	
	
		public void shoppingCart(String m_id);
	    public void shoppingCart_seq(String m_id);
	    
	    public void pick_me(String m_id);
	    public void pick_me_seq(String m_id);
	    
	    public void p_pick(String m_id);
	    public void p_pick_seq(String m_id);
	    public void prefer(String m_id);

	    	// 수량변경
	    

	    	//장바구니 관련
		public void updateQuantity(int  shop_num,int  quantity, String shop_m_id) ;
	    List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto);

	    int getTotalShoppingCartItems(String shop_m_id);
		/* 카트 삭제 */
		public int deleteCart(int shop_num,String shop_m_id);

		
		
		/*
		//찜관련 
		List<PickMeDTO> pickMeCountPages(String pm_m_id, int startRow, int pageSize, PickMeDTO pdto, CusDetailDTO cdto);
		
		int pickMeCount(String pm_m_id);
		/* 카트 삭제 */
	//	public int deleteHim(int pm_num,String pm_m_id);
		
		
		
		
		
		
		
		
		
}
