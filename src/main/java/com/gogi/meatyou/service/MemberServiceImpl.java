package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.repository.MemberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
    }
       
    
       @Override
       public void userUpdate(MemberDTO dto) {
          mapper.memberUpdate(dto);
       }
        
        
        @Override
       public int userDelete(MemberDTO dto) {
          return mapper.statusChange(dto);
       }

    
       @Override
       public int statusChange(MemberDTO dto) {
          return mapper.statusAdminChange(dto);      
       }
       
        
       @Override
       public MemberDTO getUser(String m_id) {
          return mapper.member(m_id);
       }


      @Override
      public MemberDTO member(String m_id) {
         // TODO Auto-generated method stub
         return null;
      }
            
      
      
      
        @Override
          public int updateMemberStatus(MemberDTO dto) {
          return  mapper.updateMemberStatus(dto);
          }

          @Override
          public int insertIntoCusDetail(CusDetailDTO cdto) {
             return   mapper.insertIntoCusDetail(cdto);
          }
      
      
          
          
          public void shoppingCart(String m_id) {
             mapper.shoppingCart(m_id);
          };
          public void shoppingCart_seq(String m_id) {
             mapper.shoppingCart_seq(m_id);
          };
          
          ;
          
          public void pick_me(String m_id) {
             mapper.pick_me(m_id);
          }
          public void pick_me_seq(String m_id){
             mapper.pick_me_seq(m_id);
          };
          public void p_pick(String m_id) {
             mapper.p_pick(m_id);
             
          };
          public void p_pick_seq(String m_id) {
             mapper.p_pick_seq(m_id);
          }
          public void prefer(String m_id) {
             mapper.prefer(m_id);
          }


         @Override
         public List<ShoppingCartDTO> shoppingCartCheck(String m_id) {
               return mapper.shoppingcartCheck(m_id);
         };
      
         
         

         
         // 諛섑솚 ���엯�쓣 List<ShoppingCartDTO>濡� �닔�젙
         @Override
         public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) {
             return mapper.ShoppingCartAndProduct(shop_m_id);
         }

         
         
   
      
         
         @Override
         public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) {
            mapper.updateQuantity(shop_num, shop_quantity ,shop_m_id);
          }
         
         
         //�옣諛붽뎄�땲 由ъ뒪�듃 
         
         public List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto) {
             int startRow = (page - 1) * pageSize + 1;
             int endRow = startRow + pageSize - 1;

             Map<String, Object> parameters = new HashMap<>();
             parameters.put("shop_m_id", shop_m_id);
             parameters.put("startRow", startRow);
             parameters.put("endRow", endRow);

          //   return mapper.getShoppingCartItemsPaged(parameters);
             List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged(parameters);
             System.out.println("�꽌鍮꾩뒪 �샇異� - �럹�씠吏�: " + page + ", 寃곌낵 媛쒖닔: " + result.size());
             return result;
         }

         
         
             @Override
             public int getTotalShoppingCartItems(String shop_m_id) {
                 return mapper.getTotalShoppingCartItems(shop_m_id);
             }
             
                          
             
             @Override
            public int deleteCart(int shop_num,@Param("shop_m_id")String shop_m_id) {

             return mapper.deleteCart(shop_num,shop_m_id);

            }
      
             
 
             
             
             
           //李� �뾽泥� 愿��젴 .
            @Override
            public   List<PickMeDTO> pickMeCountPage(String pm_m_id, int page, int pageSize, PickMeDTO pdto, CusDetailDTO cdto){
                int startRow = (page  - 1) * pageSize + 1;
                int endRow = startRow + pageSize - 1;

                Map<String, Object> parameters = new HashMap<>();
                parameters.put("pm_m_id", pm_m_id);
                parameters.put("startRow", startRow);
                parameters.put("endRow", endRow);

             //   return mapper.getShoppingCartItemsPaged(parameters);
                List<PickMeDTO> result = mapper.pickMeCountPage(parameters);
                System.out.println("�꽌鍮꾩뒪 �샇異� - �럹�씠吏�: " + page + ", 寃곌낵 媛쒖닔: " + result.size());
                return result;
            }
       
            
            
             @Override
             public int pickMeCount(String pm_m_id) {
                 return mapper.pickMeCount(pm_m_id);
             }             
             @Override
            public int deleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id){

                return mapper.deleteHim(pm_num,pm_m_id);

               }
                      
             
            
            
          // �꽌鍮꾩뒪�쓽 pPickCountPages 硫붿꽌�뱶 �닔�젙
            @Override
            public List<PPicDTO> pPickCountPages( @Param("ppic_m_id")String ppic_m_id,Map<String, Object> params, int page,
                  int pageSize, PPicDTO ppdto, ProductDTO pdto, MemberDTO mdto,@Param("ppic_num") int ppic_num,CusDetailDTO cdto) {
                int startRow = (page - 1) * pageSize + 1;
                    int endRow = startRow + pageSize - 1;
                    Map<String, Object> parameters = new HashMap<>();
                    parameters.put("ppic_m_id",ppic_m_id);
                    parameters.put("ppic_num",ppic_num);
                   parameters.put("startRow",startRow);
                   parameters.put("endRow", endRow);
                   parameters.put("page", page);
                   parameters.put("endRow", endRow);
                  // parameters.put("ppic_m_id",mdto.getM_id());
                   
                   
                   System.out.println(("id�뒗 臾댁뾿�씤媛��슂 ~~~=-============================")+parameters.get("ppic_m_id"));
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); // 留ㅺ컻蹂��닔 �쟾�떖
                   
                    
                    System.out.println("�꽌鍮꾩뒪 �샇異� - �럹�씠吏�: " + page + ", 寃곌낵 媛쒖닔: " + result.size());
                   
                    return result;                              
            }
            
            
            
            
            
             
             @Override
             public int pPickCount( @Param("ppic_m_id")String ppic_m_id,@Param("ppic_num")int ppic_num) {
                return mapper.pPickCount(ppic_m_id,ppic_num);
             }
             @Override
             public int deleteP_item(@Param("ppic_num")int ppic_num,@Param("ppic_m_id")String ppic_m_id){
                
                return mapper.deleteP_item(ppic_num,ppic_m_id);
                
             }


         
             
             
             
             
             
             
             
            
            
         
            
            
             
             
             
}
