package com.gogi.meatyou.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
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
	   
	    public void updateQuantity(@Param("shop_num") int shop_num, @Param("quantity") int quantity,@Param("shop_m_id")	String shop_m_id);
	  

	    
	
	  //��ٱ��� ��� +����¡ ó���ϱ����� ����Ʈ   	
	    List<ShoppingCartDTO> getPaginatedShoppingCart(
	            @Param("shop_m_id") String shop_m_id,
	            @Param("startRow") int startRow,
	            @Param("pageSize") int pageSize,
	            @Param("sdto") ShoppingCartDTO sdto,
	            @Param("pdto") ProductDTO pdto);
	    
	    List<ShoppingCartDTO> getShoppingCartItemsPaged(Map<String, Object> params);

	    int getTotalShoppingCartItems(String shop_m_id);

	   
	    public  int deleteCart(@Param("shop_num") int shop_num, @Param("shop_m_id") String shop_m_id);
		   
	    
	    
	    
	    
	    
	    
		// �� ��ü ��� ���� 
	    
	    
		  //����¡ ó���ϱ����� ����Ʈ   	
		    List<PickMeDTO> pickMeCountPages(
		            @Param("pm_m_id") String pm_m_id,
		            @Param("startRow") int startRow,
		            @Param("pageSize") int pageSize,
		            @Param("pdto") PickMeDTO pdto,
		            @Param("cdto") CusDetailDTO cdto);
		    
		
		    
		    List<PickMeDTO> pickMeCountPage(Map<String, Object> params);

		    int pickMeCount(String pm_m_id);

		   
		    public  int deleteHim(@Param("pm_num") int shop_num, @Param("pm_m_id") String pm_m_id);
	    
	    
		    
		    
 // 
			    
		//    List<PPicDTO> pPickCountPage(Map<String, Object> params);
		    //    List<PPicDTO> pPickCountPage(Map<String, Object> params);
			    

		    //����¡ ó���ϱ����� ����Ʈ   	
		    List<PPicDTO> pPickCountPage(Map<String, Object> params,
		            @Param("ppic_m_id") String ppic_m_id,
		            @Param("startRow") int startRow,
		            @Param("pageSize") int pageSize,
		            @Param("ppdto") PPicDTO ppdto,
		            ProductDTO pdto, @Param("ppic_num")int ppic_num
		            	
		    		);
		    
			    int pPickCount( @Param("ppic_num") int ppic_num, @Param("ppic_m_id") String ppic_m_id);

			   
			    public  int deleteP_item(@Param("ppic_num") int ppic_num, @Param("ppic_m_id") String ppic_m_id);


			    				
			    
			    
			    
			    
	}







