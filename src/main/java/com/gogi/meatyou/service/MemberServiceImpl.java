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
import org.springframework.dao.DuplicateKeyException;
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
  
    //泥댄겕 �썑
    @Override
    public int ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, @Param("pm_num") int pm_num) {
        return mapper.ppickAndpickMeCount( pm_m_id, pm_c_id ,pm_num);
    }
    
    public void pick_me_delete(Model model,PickMeDTO pdto,ProductDTO ppdto,@Param("pm_m_id")String pm_m_id,
 			@Param("pm_c_id") String pm_c_id
		 ,@Param("pm_num") int pm_num ){
    	//   return mapper.pick_me_delete(  pdto,  pm_m_id, pm_c_id,   p_m_id, int pm_num);
    }
 
    
    //�쉶�썝�뀒�씠釉� 李� 異붽� 諛� �궘�젣 
    @Override
    public void pickMeInsert(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
        // �씠誘� 李쒖씠 �릺�뼱 �엳�뒗吏� �솗�씤
        int pickCount = mapper.ppickAndpickMeCount(pm_m_id, pm_c_id, pm_num);

        if (pickCount > 0) {
            // �씠誘� 李쒖씠 �릺�뼱 �엳�떎硫� 李쒖쓣 �빐�젣
            int deleteResult = mapper.deletePickMeByCId(pm_m_id, pm_c_id);
            if (deleteResult > 0) {
                model.addAttribute("message", "�씠誘� �꽑�깮�맂 �긽�뭹�쓣 痍⑥냼�뻽�뒿�땲�떎.");
            }
        } else {
            // 李쒖씠 �릺�뼱 �엳吏� �븡�떎硫� 李쒖쓣 異붽�
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", "�긽�뭹�쓣 �꽑�깮�뻽�뒿�땲�떎.");
            }
        }
    }
    
    //�뙋留ㅼ옄 �뀒�씠釉� 李� 異붽� 諛� �궘�젣 
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
    	// �씠誘� 李쒖씠 �릺�뼱 �엳�뒗吏� �솗�씤
    	int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
    	
    	if (pickCount > 0) {
    		// �씠誘� 李쒖씠 �릺�뼱 �엳�떎硫� 李쒖쓣 �빐�젣
    		int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
    		if (deleteResult > 0) {
    			model.addAttribute("message", "�씠誘� �꽑�깮�맂 �긽�뭹�쓣 痍⑥냼�뻽�뒿�땲�떎.");
    		}
    	} else {
    		// 李쒖씠 �릺�뼱 �엳吏� �븡�떎硫� 李쒖쓣 異붽�
    		int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
    		if (insertResult > 0) {
    			model.addAttribute("message", "�긽�뭹�쓣 �꽑�깮�뻽�뒿�땲�떎.");
    		}
    	}
    }
    
    
    
    
 // pm_c_id留뚯쑝濡� �궘�젣
    @Override
    public void deletePickMeByCId(String pm_m_id, String pm_c_id) {
        mapper.deletePickMeByCId(pm_m_id, pm_c_id);
    }
    @Override
    public void deletePickMeByCId2(String pm_m_id, String pm_c_id) {
    	mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
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
        public int sallerDelete(MemberDTO dto) {
        	return mapper.statusChange2(dto);
        }

        public int cusDelete(CusDetailDTO cdto,MemberDTO dto,@Param("m_id") String m_id) {
        	return mapper.cusDelete(cdto,dto,m_id);
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
      
         
         

         
         @Override
         public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) {
             return mapper.ShoppingCartAndProduct(shop_m_id);
         }

         
         
   
      
         
         @Override
         public void updateQuantity(int  shop_num,int  shop_quantity, String shop_m_id) {
            mapper.updateQuantity(shop_num, shop_quantity ,shop_m_id);
          }
         
         
         
         public List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto) {
             int startRow = (page - 1) * pageSize + 1;
             int endRow = startRow + pageSize - 1;

             Map<String, Object> parameters = new HashMap<>();
             parameters.put("shop_m_id", shop_m_id);
             parameters.put("startRow", startRow);
             parameters.put("endRow", endRow);

          //   return mapper.getShoppingCartItemsPaged(parameters);
             List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged(parameters);
             System.out.println("�뜝�럡�맋占쎈쑏熬곣뫖裕� �뜝�럩源덌옙鍮듿뜝占� - �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕: " + page + ", �뇦猿됲�쀯옙沅� �뤆�룇裕뉛옙�빢: " + result.size());
             return result;
         }
          
         
         
         public List<ShoppingCartDTO> orderpage(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto) {
        	 int startRow = (page - 1) * pageSize + 1;
        	 int endRow = startRow + pageSize - 1;
        	 
        	 Map<String, Object> parameters = new HashMap<>();
        	 parameters.put("shop_m_id", shop_m_id);
        	 parameters.put("startRow", startRow);
        	 parameters.put("endRow", endRow);
        	 
        	 //   return mapper.getShoppingCartItemsPaged(parameters);
        	 List<ShoppingCartDTO> result = mapper.orderpage(parameters);
        	 System.out.println("�뜝�럡�맋占쎈쑏熬곣뫖裕� �뜝�럩源덌옙鍮듿뜝占� - �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕: " + page + ", �뇦猿됲�쀯옙沅� �뤆�룇裕뉛옙�빢: " + result.size());
        	 return result;
         }
         
         
         
             @Override
             public int getTotalShoppingCartItems(String shop_m_id) {
                 return mapper.getTotalShoppingCartItems(shop_m_id);
             }
             
             
             @Override
             public int orderpageCartItems(String shop_m_id) {
            	 return mapper.orderpageCartItems(shop_m_id);
             }
             
                          
             
             @Override
            public int deleteCart(int shop_num,@Param("shop_m_id")String shop_m_id) {

             return mapper.deleteCart(shop_num,shop_m_id);

            }
      
             // 선택된 상품들 삭제
             public int deleteSelectedProducts(int shop_num[],@Param("shop_m_id")String shop_m_id) {
            	  return  mapper.deleteCart2(shop_num,shop_m_id);
             }
 
             
             
             
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
                System.out.println("�뜝�럡�맋占쎈쑏熬곣뫖裕� �뜝�럩源덌옙鍮듿뜝占� - �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕: " + page + ", �뇦猿됲�쀯옙沅� �뤆�룇裕뉛옙�빢: " + result.size());
                return result;
            }
       
			
             @Override
             public int pickMeCount( @Param("pm_m_id")String pm_m_id,@Param("p_m_id")String p_m_id   ) {
                 return mapper.pickMeCount(pm_m_id,p_m_id  );
             }             
             @Override
            public int deleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id){
                return mapper.deleteHim(pm_num,pm_m_id);
               }
             
             
               
             
             @Override
             public   List<PickMeDTO> SallerpickMeCountPage(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, int page, int pageSize, PickMeDTO pdto ) {
            	 int startRow = (page  - 1) * pageSize + 1;
            	 int endRow = startRow + pageSize - 1;
            	 
            	 Map<String, Object> parameters = new HashMap<>();
            	 parameters.put("pm_m_id", pm_m_id);
            	 parameters.put("pm_c_id", pm_c_id);
            	 parameters.put("startRow", startRow);
            	 parameters.put("endRow", endRow);
            	 
            	 //   return mapper.getShoppingCartItemsPaged(parameters);
            	 List<PickMeDTO> result = mapper.SallerpickMeCountPage(parameters);
            	 System.out.println("�뜝�럡�맋占쎈쑏熬곣뫖裕� �뜝�럩源덌옙鍮듿뜝占� - �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕: " + page + ", �뇦猿됲�쀯옙沅� �뤆�룇裕뉛옙�빢: " + result.size());
            	 return result;
             }
             
             
             @Override
             public int SallerpickMeCount( @Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id) {
            	 Map<String, Object> parameters = new HashMap<>();
            	 parameters.put("pm_m_id", pm_m_id);
            	 parameters.put("pm_c_id", pm_c_id);
            	 return mapper.SallerpickMeCount(parameters);
             }             
             @Override
             public int SallerdeleteHim(@Param("pm_num")int pm_num,@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id){
            	 return mapper.SallerdeleteHim(pm_num,pm_m_id,pm_c_id);
             }
             
             
             
            @Override
            public   List<PPicDTO> pPickCountPages(@Param("ppic_m_id")String ppic_m_id,Map<String, Object> params,int page, int pageSize, PPicDTO ppdto, ProductDTO pdto,MemberDTO mdto,@Param("ppic_num")int ppic_num,CusDetailDTO cdto ){
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
                   
                   
                   System.out.println(("id�뜝�럥裕� 占쎈닱占쎈샍驪볦슱�삕占쎈데�뤆�룊�삕�뜝�럩�뭵 ~~~=-============================")+parameters.get("ppic_m_id"));
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); // 嶺뚮씞�걝�뚯궘�돦�뜝�룞�삕占쎈빢 �뜝�럩�쓧�뜝�럥堉�
                   
                    
                    System.out.println("�뜝�럡�맋占쎈쑏熬곣뫖裕� �뜝�럩源덌옙鍮듿뜝占� - �뜝�럥�쓡�뜝�럩逾좂춯�쉻�삕: " + page + ", �뇦猿됲�쀯옙沅� �뤆�룇裕뉛옙�빢: " + result.size());
                   
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
			@Override
			public MemberDTO member(String m_id) {
				// TODO Auto-generated method stub
				return null;
			}
		 
	 
			 
             
             
             
             
             
             
            
            
         
            
            
             
             
             
}