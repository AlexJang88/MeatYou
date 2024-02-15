package com.gogi.meatyou.service;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.OrderwithCouponDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.bean.UserPayDTO;

public interface MemberService  {
	public void userPaycomplete(ArrayList<MOrderDTO> list,int[] shop_num,int[]cp_num,String id);
	public OrderwithCouponDTO getProductInfo(int p_num);
	public OrderwithCouponDTO getCartbyNum(HashMap hashmap);
	public OrderwithCouponDTO getCouponNum(int cp_num);
    int insertMember(MemberDTO dto);
    int insertKaKao(MemberDTO dto);
    //kakao Token
    public String getAccessToken(String authorize_code) throws Throwable;
    public void tokenLogout(String accessToken) ;
    public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable;
    
    public int idCheck(String m_id);
    public int eMailCheck(String email);
    public int deleteCheck( String passwd) ;
    int twoNextPay(
    		OrderwithCouponDTO mdto,int shop_num,int order_p_num,String order_memo
    		,@Param("order_m_id") String order_m_id,int order_cp_num,int order_p_price,
    		@Param("order_dere_pay") int order_dere_pay ,@Param("order_addr") String order_addr,@Param("order_discount") int order_discount,@Param("order_quantity") int order_quantity
    		,@Param("order_totalprice") int order_totalprice  );
    
    
    public List<UserPayDTO> findshop_p_num(HashMap hashmap);
    public MemberDTO member(String m_id);   
    public List<ShoppingCartDTO> shoppingCartCheck(String m_id);
   public List<OrderwithCouponDTO> ShoppingCartAndProduct(String shop_m_id,int page,Model model) ;
   public void userUpdate(MemberDTO dto);
   
   public MemberDTO getUser(String m_id);
   
   public List<MemAddressDTO>addressCheck(String add_m_id,MemberDTO mdto,MemAddressDTO adto,int add_num) ; 
   public List<String> combined_address(String id)  ; 
   public int deleteAddr(@Param("add_num")  int add_num,@Param("add_m_id") String add_m_id);
   public  int addressCount(@Param("add_m_id")String add_m_id,@Param("add_num")int add_num  ) ;       
  // public int  updateAddr(@Param("add_num")  int add_num,@Param("add_m_id") String add_m_id,@Param("add_mem_address1")String add_mem_address1,@Param("add_mem_address2") String add_mem_address2);
   public void  updateAddr(MemAddressDTO adto,@Param("add_m_id") String add_m_id,List<MemAddressDTO> AddrList,int add_num,@Param("add_mem_address1") String add_mem_address1,@Param("add_mem_address2") String add_mem_address2);
   
   public void  insertAddr(MemAddressDTO  adto);
   
   public int userDelete(MemberDTO dto);
   public int sallerDelete(MemberDTO dto);
   public int statusChange(MemberDTO dto);
   public int cusDelete(CusDetailDTO cdto,MemberDTO dto,@Param("m_id") String m_id);
   
   public int updateMemberStatus  (MemberDTO dto);
   
   public int insertIntoCusDetail(CusDetailDTO cdto) ;
      
   public CouponDTO findCouponToCpNum(int cp_num);
   
   public int ShoppingCartCNT(String shop_m_id);
   public int pickCNT(String ppic_m_id);
   public int pick_P_CNT(String ppic_m_id);

      public void shoppingCart(String m_id);
       public void shoppingCart_seq(String m_id);
       
       public void pick_me(String m_id);
       public void pick_me_seq(String m_id);
       
       public void p_pick(String m_id);
       public void p_pick_seq(String m_id);
       public void prefer(String m_id);
      public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) ;
      List<ShoppingCartDTO> getShoppingCartItemsPaged2(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto,PDetailDTO pddto,List<CouponDTO> cList,CouponDTO cdto  );
      	public int CouponForyou(String shop_m_id,CouponDTO cdto,ShoppingCartDTO sdto);
      int getTotalShoppingCartItems(String shop_m_id);
      public int deleteCart(int shop_num,String shop_m_id);
      public void deleteSelectedItems(List<Long> selectedShopNums ,String shop_m_id);
      List<ShoppingCartDTO> orderpage(String shop_m_id, int startRow, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto);
      int orderpageCartItems(String shop_m_id);
      List<PickMeDTO> pickMeCountPage(String pm_m_id, int page, int pageSize, PickMeDTO pdto, CusDetailDTO cdto);
      int pickMeCount( String pm_m_id,@Param("p_m_id")String p_m_id );
         public int deleteHim(int pm_num,String pm_m_id);
         public int checkHim(String pm_m_id);
         
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
      
      int  ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id,@Param("pm_num") int pm_num);
      int couponCount(@Param("cp_m_id") String cp_m_id);
      List<CouponDTO>   howmuchCoupon(@Param("cp_m_id") String cp_m_id);
 
      // 占쎈뼄�몴占� 占쎈툡占쎌뒄占쎈립 筌롫뗄苑뚳옙諭띰옙諭얏�⑨옙 占쎈맙�뙼占� �빊遺쏙옙
      ShoppingCartDTO getSelectedProducts2(int shop_p_num, @Param("add_m_id") String add_m_id );
      
      
      
      List<MOrderDTO> paypage(@Param("order_m_id") String order_m_id , int page, int pageSize );
      int PaymentCount(@Param("order_m_id") String order_m_id );
      
      public String joinEmail(String email); //占쎌뵠筌롫뗄�뵬筌ｋ똾寃�
      
      public int findId(MemberDTO memberdto);//占쎈툡占쎌뵠占쎈탵 筌≪뼐由� 筌띿쉶�뮉筌욑옙 �뜮袁㏉꺍
      public int findPw(MemberDTO memberdto);//占쎈툡占쎌뵠占쎈탵 筌≪뼐由� 筌띿쉶�뮉筌욑옙 �뜮袁㏉꺍
      public void getDbId(Model model, MemberDTO memberdto); // 占쎈뼄占쎌젫 占쎈툡占쎌뵠占쎈탵 揶쏉옙占쎌죬占쎌궎疫뀐옙
      public void getDbPw(Model model, MemberDTO memberdto); // 占쎈뼄占쎌젫 �뜮袁⑥쓰 揶쏉옙占쎌죬占쎌궎疫뀐옙
      public void changePw(MemberDTO memberdto); //�뜮袁⑨옙甕곕뜇�깈癰귨옙野껓옙
      public List<CouponDTO> getProductCoupon(HashMap hashmap);
      	public boolean memberList( @Param("m_id") String m_id);
}