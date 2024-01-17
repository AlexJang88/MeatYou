package com.gogi.meatyou.service;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;

public interface MemberService  {
    int insertMember(MemberDTO dto);
    
    
    
    public MemberDTO member(String m_id);   
    public List<ShoppingCartDTO> shoppingCartCheck(String m_id);
 // 諛섑솚 ���엯�쓣 List<ShoppingCartDTO>濡� �닔�젙
   public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) ;
   public void userUpdate(MemberDTO dto);
   
   public MemberDTO getUser(String m_id);
   
   public int userDelete(MemberDTO dto);
   //�쉶�썝 �깉�눜
   public int statusChange(MemberDTO dto);
   //�깉�눜�븯硫� 泥댁씤吏��릺�뼱�빞 �븯�땲源� 
   
   public int updateMemberStatus  (MemberDTO dto);
   
   public int insertIntoCusDetail(CusDetailDTO cdto) ;
      
   
   
   
      public void shoppingCart(String m_id);
       public void shoppingCart_seq(String m_id);
       
       public void pick_me(String m_id);
       public void pick_me_seq(String m_id);
       
       public void p_pick(String m_id);
       public void p_pick_seq(String m_id);
       public void prefer(String m_id);

          // �닔�웾蹂�寃�
       

          //�옣諛붽뎄�땲 愿��젴
      public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) ;
      
      List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto);

       int getTotalShoppingCartItems(String shop_m_id);
      /* 移댄듃 �궘�젣 */
      public int deleteCart(int shop_num,String shop_m_id);

      
      
      
      //李� �뾽泥� 愿��젴 
      List<PickMeDTO> pickMeCountPage(String pm_m_id, int startRow, int pageSize, PickMeDTO pdto, CusDetailDTO cdto);
      
      int pickMeCount(String pm_m_id);
      /* 移댄듃 �궘�젣 */
         public int deleteHim(int pm_num,String pm_m_id);
      
         
         
      //李�  愿��젴 
         List<PPicDTO> pPickCountPages(@Param("ppic_m_id")String ppic_m_id,Map<String, Object> params,int page, int pageSize, PPicDTO ppdto, ProductDTO pdto,MemberDTO mdto,@Param("ppic_num")int ppic_num,CusDetailDTO cdto) ;
       
         int pPickCount(@Param("ppic_m_id")String ppic_m_id,@Param("ppic_num")int ppic_num) ;
      /* 移댄듃 �궘�젣 */
      public int deleteP_item(int ppic_num,String ppic_m_id);
   

      
      
      
      
      
      
      
      
}