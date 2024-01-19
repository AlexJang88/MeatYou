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
import org.springframework.ui.Model;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
    }
       
    public void pickMeInsert(Model model, PickMeDTO pdto, String pm_m_id,String pm_c_id ,String ppic_m_id,String p_m_id ) {
    	int result;
		result = mapper.pick_me_p_numCNT(pm_m_id,pm_c_id ,ppic_m_id );
		if(result == 0) {
			mapper.pickMeInsert(pdto,pm_m_id,pm_c_id ,ppic_m_id,p_m_id );   
		} else {
			mapper.pick_me_delete(pdto,pm_m_id,pm_c_id ,ppic_m_id );
		}
		model.addAttribute("pdto" , pdto);
    	
    };
	   public int pick_me_p_numCNT(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id") String pm_c_id, @Param("pm_num")int pm_num,@Param("ppic_m_id") String ppic_m_id ,@Param("p_num") int p_num) {
		   return mapper.pick_me_p_numCNT(pm_m_id,pm_c_id, ppic_m_id );
	   };  
	   
    
    
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
      
         
         

         
         // 獄쏆꼹�넎 占쏙옙占쎌뿯占쎌뱽 List<ShoppingCartDTO>嚥∽옙 占쎈땾占쎌젟
         @Override
         public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) {
             return mapper.ShoppingCartAndProduct(shop_m_id);
         }

         
         
   
      
         
         @Override
         public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) {
            mapper.updateQuantity(shop_num, shop_quantity ,shop_m_id);
          }
         
         
         //占쎌삢獄쏅떽�럡占쎈빍 �뵳�딅뮞占쎈뱜 
         
         public List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto) {
             int startRow = (page - 1) * pageSize + 1;
             int endRow = startRow + pageSize - 1;

             Map<String, Object> parameters = new HashMap<>();
             parameters.put("shop_m_id", shop_m_id);
             parameters.put("startRow", startRow);
             parameters.put("endRow", endRow);

          //   return mapper.getShoppingCartItemsPaged(parameters);
             List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged(parameters);
             System.out.println("占쎄퐣�뜮袁⑸뮞 占쎌깈�빊占� - 占쎈읂占쎌뵠筌욑옙: " + page + ", 野껉퀗�궢 揶쏆뮇�땾: " + result.size());
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
      
             
 
             
             
             
           //筌∽옙 占쎈씜筌ｏ옙 �꽴占쏙옙�졃 .
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
                System.out.println("占쎄퐣�뜮袁⑸뮞 占쎌깈�빊占� - 占쎈읂占쎌뵠筌욑옙: " + page + ", 野껉퀗�궢 揶쏆뮇�땾: " + result.size());
                return result;
            }
       
            
            
             @Override
             public int pickMeCount(String pm_m_id,int p_num) {
                 return mapper.pickMeCount(pm_m_id,p_num);
             }             
             @Override
            public int deleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id){

                return mapper.deleteHim(pm_num,pm_m_id);

               }
                      
            @Override
            public List<PPicDTO> pPickCountPages(@Param("ppic_m_id")String ppic_m_id,Map<String, Object> params,int page, int pageSize, PPicDTO ppdto, ProductDTO pdto,MemberDTO mdto,@Param("ppic_num")int ppic_num,CusDetailDTO cdto,@Param("p_num") int p_num ){
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
                   
                   
                   System.out.println(("id占쎈뮉 �눧�똻毓울옙�뵥揶쏉옙占쎌뒄 ~~~=-============================")+parameters.get("ppic_m_id"));
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); // 筌띲끆而삭퉪占쏙옙�땾 占쎌읈占쎈뼎
                   
                    
                    System.out.println("占쎄퐣�뜮袁⑸뮞 占쎌깈�빊占� - 占쎈읂占쎌뵠筌욑옙: " + page + ", 野껉퀗�궢 揶쏆뮇�땾: " + result.size());
                   
                    return result;                              
            }
            
            
            
            
            
             
             @Override
             public int pPickCount( @Param("ppic_m_id")String ppic_m_id,@Param("ppic_num")int ppic_num ) {
                return mapper.pPickCount(ppic_m_id,ppic_num);
             }
             @Override
             public int deleteP_item(@Param("ppic_num")int ppic_num,@Param("ppic_m_id")String ppic_m_id){
                
                return mapper.deleteP_item(ppic_num,ppic_m_id);
                
             } 
             
             
             
             
             
             
             
            
            
         
            
            
             
             
             
}
