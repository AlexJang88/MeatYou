package com.gogi.meatyou.service;


import java.util.List;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MemberService {
    int insertMember(MemberDTO dto);
    
    
    
    public MemberDTO member(String m_id);	
    public List<ShoppingCartDTO> shoppingCartCheck(String m_id);
    //public void     ShoppingCartAndProduct(String m_id, ProductDTO pdto, ShoppingCartDTO sdto, int p_price, String p_name, String thumb);
    


	public void ShoppingCartAndProduct( String shop_m_id,ShoppingCartDTO sdto) ;
    
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








}
