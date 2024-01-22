package com.gogi.meatyou.service;


import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
   public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) ;
   public void userUpdate(MemberDTO dto);
   
   public MemberDTO getUser(String m_id);
   
   public int userDelete(MemberDTO dto);
   public int sallerDelete(MemberDTO dto);
   public int statusChange(MemberDTO dto);
   public int cusDelete(CusDetailDTO cdto,MemberDTO dto,@Param("m_id") String m_id);
   
   public int updateMemberStatus  (MemberDTO dto);
   
   public int insertIntoCusDetail(CusDetailDTO cdto) ;
      
   
   
   
      public void shoppingCart(String m_id);
       public void shoppingCart_seq(String m_id);
       
       public void pick_me(String m_id);
       public void pick_me_seq(String m_id);
       
       public void p_pick(String m_id);
       public void p_pick_seq(String m_id);
       public void prefer(String m_id);

      public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) ;
      
      List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto);

       int getTotalShoppingCartItems(String shop_m_id);
      public int deleteCart(int shop_num,String shop_m_id);

      
      
      
      List<PickMeDTO> pickMeCountPage(String pm_m_id, int page, int pageSize, PickMeDTO pdto, CusDetailDTO cdto);
      
      int pickMeCount( String pm_m_id,@Param("p_m_id")String p_m_id );
         public int deleteHim(int pm_num,String pm_m_id);
         
         
         List<PickMeDTO> SallerpickMeCountPage(@Param("pm_c_id") String pm_c_id,@Param("pm_m_id")String pm_m_id, int page, int pageSize, PickMeDTO pdto);
         int SallerpickMeCount(@Param("pm_m_id") String pm_m_id ,@Param("pm_c_id")String pm_c_id );
         public int SallerdeleteHim(int pm_num,String pm_m_id, String pm_c_id);
         
         
      
         List<PPicDTO> pPickCountPages(@Param("ppic_m_id")String ppic_m_id,Map<String, Object> params,int page, int pageSize, PPicDTO ppdto, ProductDTO pdto,MemberDTO mdto,@Param("ppic_num")int ppic_num,CusDetailDTO cdto ) ;
       
         int pPickCount(@Param("ppic_m_id")String ppic_m_id,@Param("ppic_num")int ppic_num ) ;
      public int deleteP_item(int ppic_num,String ppic_m_id);
   
      
      										
      void pick_me_delete(Model model,PickMeDTO pdto,ProductDTO ppdto,@Param("pm_m_id")String pm_m_id,
     			@Param("pm_c_id") String pm_c_id
    			,@Param("pm_num") int pm_num );   
      
      public void pickMeInsert(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, @Param("pm_num") int pm_num);   
      void deletePickMeByCId(String pm_m_id, String pm_c_id);
      
      public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, @Param("pm_num") int pm_num);   
      void deletePickMeByCId2(String pm_m_id, String pm_c_id);
      
      //媛숈�媛믪씠�엲�뒗吏� �솗�씤
      int  ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id,@Param("pm_num") int pm_num);
      
      
    
      
}