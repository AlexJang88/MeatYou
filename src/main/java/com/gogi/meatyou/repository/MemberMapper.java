package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.web.bind.annotation.RequestParam;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.bean.UserPayDTO;

public interface MemberMapper {
   public MemberDTO read(String m_id);
   
   
    public int insertMember(MemberDTO dto);
    public int twoNextPay(MOrderDTO mdto,int shop_num ,@Param("order_p_num")int order_p_num,
    		@Param("order_memo") String order_memo,@Param("order_m_id") String order_m_id,@Param("order_cp_num") int order_cp_num,@Param("order_p_price") int order_p_price
    		,@Param("order_dere_pay") int order_dere_pay,@Param("order_addr") String order_addr,@Param("order_discount") int order_discount,@Param("order_quantity") int order_quantity
    		,@Param("order_totalprice") int order_totalprice
    		
    		
    		);

   

         
       public MemberDTO loadUserByUsername(String username);
      
       public List<MemberDTO> memberList();
    
      public MemberDTO member(String m_id);
      
      
      public List<UserPayDTO> findshop_p_num(HashMap hashmap);
      
      List<MemAddressDTO> addressCheck(Map<String, Object> parameters);
      List<MemAddressDTO> combined_address(Map<String, Object> parameters);
      public int deleteAddr(@Param("add_num") int add_num,@Param("add_m_id") String add_m_id);
      
     // public int  updateAddr(@Param("add_num") int add_num,@Param("add_m_id") String add_m_id ,@Param("add_mem_address1") String add_mem_address1,@Param("add_mem_address2") String add_mem_address2);
      void updateAddr(@Param("adto") MemAddressDTO adto, @Param("add_m_id") String add_m_id,@Param("add_mem_address1") String add_mem_address1,@Param("add_mem_address2") String add_mem_address2,@Param("add_num")  int add_num);
      
      public void  insertAddr(MemAddressDTO  adto);
      
      
      public List<ShoppingCartDTO> shoppingcartCheck(String m_id);
      public void memberUpdate(MemberDTO dto);
      
      
      public int statusAdminChange(MemberDTO dto);
      
      public int statusChange(MemberDTO dto);
      public int statusChange2(MemberDTO dto);
      public int cusDelete(CusDetailDTO cdto,MemberDTO dto,@Param("m_id") String m_id);
      
      
      
       public int updateMemberStatus(MemberDTO dto);

       public int insertIntoCusDetail(CusDetailDTO cdto);
       public void shoppingCart(String m_id);
       public void shoppingCart_seq(String m_id);
       public void pick_me(String m_id);
       public void pick_me_seq(String m_id);
       public void p_pick(String m_id);
       public void p_pick_seq(String m_id);
       public void prefer(String m_id);


       List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id);
       public void updateQuantity(@Param("shop_num") int shop_num, @Param("shop_quantity") int shop_quantity,@Param("shop_m_id")   String shop_m_id);
     

       
       List<ShoppingCartDTO> getPaginatedShoppingCart(
               @Param("shop_m_id") String shop_m_id,
               @Param("startRow") int startRow,
               @Param("pageSize") int pageSize,
               @Param("sdto") ShoppingCartDTO sdto,
               @Param("pdto") ProductDTO pdto);
       
       List<ShoppingCartDTO> getShoppingCartItemsPaged(Map<String, Object> params);
       List<ShoppingCartDTO> getShoppingCartItemsPaged2(Map<String, Object> params);
       
       List<ShoppingCartDTO> orderpage(Map<String, Object> params);
       
       int getTotalShoppingCartItems(String shop_m_id);
       
       int orderpageCartItems(String shop_m_id);
       
       CouponDTO findCouponToCpNum(int cp_num);
       
       
       public  int deleteCart(@Param("shop_num") int shop_num, @Param("shop_m_id") String shop_m_id);
       
       
       void deleteSelectedItems(Map<String, Object> paramMap);
       
          List<PickMeDTO> pickMeCountPages(
                  @Param("pm_m_id") String pm_m_id,
                  @Param("startRow") int startRow,
                  @Param("pageSize") int pageSize,
                  @Param("pdto") PickMeDTO pdto,
                  @Param("cdto") CusDetailDTO cdto);
          
          
          List<PickMeDTO> SallerpickMeCountPage(Map<String, Object> params);
          
          int SallerpickMeCount(Map<String, Object> params);       
          public  int SallerdeleteHim(@Param("pm_num") int shop_num, @Param("pm_m_id") String pm_m_id,@Param("pm_c_id")String pm_c_id);
          
          int couponCount(@Param("cp_m_id") String cp_m_id);
          List<CouponDTO> howmuchCoupon(@Param("cp_m_id") String cp_m_id);

          List<PickMeDTO> pickMeCountPage(Map<String, Object> params);
          int pickMeCount(@Param("pm_m_id")String pm_m_id,@Param("p_m_id")String p_m_id  );       
          int addressCount(@Param("add_m_id")String add_m_id,@Param("add_num")int add_num  );       
          public  int deleteHim(@Param("pm_num") int shop_num, @Param("pm_m_id") String pm_m_id);
       
         
          List<PPicDTO> pPickCountPage(Map<String, Object> params);
             int pPickCount(@Param("ppic_m_id") String ppic_m_id, @Param("ppic_num") int ppic_num );
             public  int deleteP_item(@Param("ppic_num") int ppic_num, @Param("ppic_m_id") String ppic_m_id);


             int ppickAndpickMeCount( @Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id ,@Param("pm_num") int pm_num );
             int ppickAndpickMeCount2( @Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id ,@Param("pm_num") int pm_num );
            public void pick_me_delete(PickMeDTO pdto,@Param("pm_m_id")String pm_m_id,@Param("pm_c_id") String pm_c_id,  @Param("pm_num") int pm_num );
      
            int deletePickMeByCId(@Param("pm_m_id") String pm_m_id,      @Param("pm_c_id") String pm_c_id);
            int pickMeInsert(PickMeDTO pdto, @Param("pm_m_id") String pm_m_id , @Param("pm_c_id")String pm_c_id,@Param("pm_num") int pm_num );
            
            int deletePickMeByCId2(@Param("pm_m_id") String pm_m_id,      @Param("pm_c_id") String pm_c_id);
            int pickMeInsert2(PickMeDTO pdto, @Param("pm_m_id") String pm_m_id , @Param("pm_c_id")String pm_c_id,@Param("pm_num") int pm_num );
            
            ShoppingCartDTO getSelectedProducts( @Param("shop_num") int shop_num,  @Param("add_m_id") String add_m_id );
            ShoppingCartDTO getSelectedProducts2( @Param("shop_p_num") int shop_p_num,  @Param("add_m_id") String add_m_id );
            
            
            List<MOrderDTO> paypage(Map<String, Object> params);
            
            int PaymentCount(Map<String, Object> params);       
            
            public String dbName(MemberDTO memberdto); //디비속 이름
            public String dbPhone(MemberDTO memberdto); //디비속 전화번호
            public String dbId(MemberDTO memberdto); //디비속 전화번호
            public String getDbId(MemberDTO memberdto);//실제아이디
            public String getDbPw(MemberDTO memberdto);//실제아이디
            
            
   }