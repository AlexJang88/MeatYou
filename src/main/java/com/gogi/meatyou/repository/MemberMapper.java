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
	
		//ȸ������
	
	 public int insertMember(MemberDTO dto);
	 

	    // Spring Security���� ����� ������ �������� ���� �޼���
	    public MemberDTO loadUserByUsername(String username);
		
	 	public List<MemberDTO> memberList();
	 
		public MemberDTO member(String m_id);
		
		
		public List<ShoppingCartDTO> shoppingcartCheck(String m_id);
		public void memberUpdate(MemberDTO dto);
		
		
		public int statusAdminChange(MemberDTO dto);
		
		public int statusChange(MemberDTO dto);
		
		
		
		  // ȸ�� ���� ������Ʈ
	    public int updateMemberStatus(MemberDTO dto);

	    // Cus_detail�� ������ �μ�Ʈ
	    public int insertIntoCusDetail(CusDetailDTO cdto);
	    public void shoppingCart(String m_id);
	    public void shoppingCart_seq(String m_id);
	    public void pick_me(String m_id);
	    public void pick_me_seq(String m_id);
	    public void p_pick(String m_id);
	    public void p_pick_seq(String m_id);
	    public void prefer(String m_id);



	//	public void ShoppingCartAndProduct(String shop_m_id ) ;
	 // ��ȯ Ÿ���� List<ShoppingCartDTO>�� ����
	    List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id);
	    
	  // ��ٱ��� ���� ���� 
	    public int upquantity(ShoppingCartDTO sdto);

	    public  int downquantity(ShoppingCartDTO sdto);
	    
}






