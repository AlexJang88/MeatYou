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
 // ��ȯ Ÿ���� List<ShoppingCartDTO>�� ����
	public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) ;
	public void userUpdate(MemberDTO dto);
	
	public MemberDTO getUser(String m_id);
	
	public int userDelete(MemberDTO dto);
	//ȸ�� Ż��
	public int statusChange(MemberDTO dto);
	//Ż���ϸ� ü�����Ǿ�� �ϴϱ� 
	
	public int updateMemberStatus  (MemberDTO dto);
	
	public int insertIntoCusDetail(CusDetailDTO cdto) ;
		
	
	
	
		public void shoppingCart(String m_id);
	    public void shoppingCart_seq(String m_id);
	    
	    public void pick_me(String m_id);
	    public void pick_me_seq(String m_id);
	    
	    public void p_pick(String m_id);
	    public void p_pick_seq(String m_id);
	    public void prefer(String m_id);

	    	// ��������
	    

	    	//��ٱ��� ����
		public void updateQuantity(int  shop_num,int  quantity, String shop_m_id) ;
	    List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto);

	    int getTotalShoppingCartItems(String shop_m_id);
		/* īƮ ���� */
		public int deleteCart(int shop_num,String shop_m_id);

		
		
		/*
		//����� 
		List<PickMeDTO> pickMeCountPages(String pm_m_id, int startRow, int pageSize, PickMeDTO pdto, CusDetailDTO cdto);
		
		int pickMeCount(String pm_m_id);
		/* īƮ ���� */
	//	public int deleteHim(int pm_num,String pm_m_id);
		
		
		
		
		
		
		
		
		
}
