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
   
      //�쉶�썝媛��엯
   
    public int insertMember(MemberDTO dto);
    public int pick_mem_Insert(PickMeDTO pdto);
    public int pick_saller_Insert(PickMeDTO pdto);
    

       // Spring Security�뿉�꽌 �궗�슜�옄 �젙蹂대�� 媛��졇�삤湲� �쐞�븳 硫붿꽌�뱶
       public MemberDTO loadUserByUsername(String username);
      
       public List<MemberDTO> memberList();
    
      public MemberDTO member(String m_id);
      
      
      public List<ShoppingCartDTO> shoppingcartCheck(String m_id);
      public void memberUpdate(MemberDTO dto);
      
      
      public int statusAdminChange(MemberDTO dto);
      
      public int statusChange(MemberDTO dto);
      
      
      
        // �쉶�썝 �긽�깭 �뾽�뜲�씠�듃
       public int updateMemberStatus(MemberDTO dto);

       // Cus_detail�뿉 �뜲�씠�꽣 �씤�꽌�듃
       public int insertIntoCusDetail(CusDetailDTO cdto);
       public void shoppingCart(String m_id);
       public void shoppingCart_seq(String m_id);
       public void pick_me(String m_id);
       public void pick_me_seq(String m_id);
       public void p_pick(String m_id);
       public void p_pick_seq(String m_id);
       public void prefer(String m_id);



   //   public void ShoppingCartAndProduct(String shop_m_id ) ;
    // 諛섑솚 ���엯�쓣 List<ShoppingCartDTO>濡� �닔�젙
       List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id);
       
     // �옣諛붽뎄�땲 �닔�웾 蹂�寃� 
       public int upquantity(ShoppingCartDTO sdto);
       public  int downquantity(ShoppingCartDTO sdto);
      
       public void updateQuantity(@Param("shop_num") int shop_num, @Param("shop_quantity") int shop_quantity,@Param("shop_m_id")   String shop_m_id);
     

       
   
     //�옣諛붽뎄�땲 紐⑸줉 +�럹�씠吏� 泥섎━�븯湲곗쐞�븳 由ъ뒪�듃      
       List<ShoppingCartDTO> getPaginatedShoppingCart(
               @Param("shop_m_id") String shop_m_id,
               @Param("startRow") int startRow,
               @Param("pageSize") int pageSize,
               @Param("sdto") ShoppingCartDTO sdto,
               @Param("pdto") ProductDTO pdto);
       
       List<ShoppingCartDTO> getShoppingCartItemsPaged(Map<String, Object> params);

       int getTotalShoppingCartItems(String shop_m_id);

      
       public  int deleteCart(@Param("shop_num") int shop_num, @Param("shop_m_id") String shop_m_id);
         
       
       
       
       
       
       
      // 李� �뾽泥� 紐⑸줉 �떆�옉 
       
       
        //�럹�씠吏� 泥섎━�븯湲곗쐞�븳 由ъ뒪�듃      
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
             

          //�럹�씠吏� 泥섎━�븯湲곗쐞�븳 由ъ뒪�듃      
    List<PPicDTO> pPickCountPage(Map<String, Object> params);

          
          
          
          
             int pPickCount(@Param("ppic_m_id") String ppic_m_id, @Param("ppic_num") int ppic_num );

            
             public  int deleteP_item(@Param("ppic_num") int ppic_num, @Param("ppic_m_id") String ppic_m_id);


                         
             
             
             
             
   }






