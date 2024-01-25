package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PDetailDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;

    @Override
    public int insertMember(MemberDTO dto) {
        return mapper.insertMember(dto);
    }
  
    //筌ｋ똾寃� 占쎌뜎
    @Override
    public int ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, @Param("pm_num") int pm_num) {
        return mapper.ppickAndpickMeCount( pm_m_id, pm_c_id ,pm_num);
    }
    
    public void pick_me_delete(Model model,PickMeDTO pdto,ProductDTO ppdto,@Param("pm_m_id")String pm_m_id,
 			@Param("pm_c_id") String pm_c_id
		 ,@Param("pm_num") int pm_num ){
    }
 
    
    //占쎌돳占쎌뜚占쎈�믭옙�뵠�뇡占� 筌∽옙 �빊遺쏙옙 獄쏉옙 占쎄텣占쎌젫 
    @Override
    public void pickMeInsert(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
        // 占쎌뵠沃섓옙 筌≪뮇�뵠 占쎈┷占쎈선 占쎌뿳占쎈뮉筌욑옙 占쎌넇占쎌뵥
        int pickCount = mapper.ppickAndpickMeCount(pm_m_id, pm_c_id, pm_num);

        if (pickCount > 0) {
            // 占쎌뵠沃섓옙 筌≪뮇�뵠 占쎈┷占쎈선 占쎌뿳占쎈뼄筌롳옙 筌≪뮇�뱽 占쎈퉸占쎌젫
            int deleteResult = mapper.deletePickMeByCId(pm_m_id, pm_c_id);
            if (deleteResult > 0) {
                model.addAttribute("message", "占쎌뵠沃섓옙 占쎄퐨占쎄문占쎈쭆 占쎄맒占쎈�뱄옙�뱽 �뿆�뫁�꺖占쎈뻥占쎈뮸占쎈빍占쎈뼄.");
            }
        } else {
            // 筌≪뮇�뵠 占쎈┷占쎈선 占쎌뿳筌욑옙 占쎈륫占쎈뼄筌롳옙 筌≪뮇�뱽 �빊遺쏙옙
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", "占쎄맒占쎈�뱄옙�뱽 占쎄퐨占쎄문占쎈뻥占쎈뮸占쎈빍占쎈뼄.");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
    	int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
    	
    	if (pickCount > 0) {
    		// 占쎌뵠沃섓옙 筌≪뮇�뵠 占쎈┷占쎈선 占쎌뿳占쎈뼄筌롳옙 筌≪뮇�뱽 占쎈퉸占쎌젫
    		int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
    		if (deleteResult > 0) {
    			model.addAttribute("message", "占쎌뵠沃섓옙 占쎄퐨占쎄문占쎈쭆 占쎄맒占쎈�뱄옙�뱽 �뿆�뫁�꺖占쎈뻥占쎈뮸占쎈빍占쎈뼄.");
    		}
    	} else {
    		// 筌≪뮇�뵠 占쎈┷占쎈선 占쎌뿳筌욑옙 占쎈륫占쎈뼄筌롳옙 筌≪뮇�뱽 �빊遺쏙옙
    		int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
    		if (insertResult > 0) {
    			model.addAttribute("message", "占쎄맒占쎈�뱄옙�뱽 占쎄퐨占쎄문占쎈뻥占쎈뮸占쎈빍占쎈뼄.");
    		}
    	}
    }
    
    
    
    
 // pm_c_id筌띾슣�몵嚥∽옙 占쎄텣占쎌젫
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
       public List<MemAddressDTO>addressCheck(String add_m_id,MemberDTO mdto,MemAddressDTO adto) {
        	 
        	 Map<String, Object> parameters = new HashMap<>();
        	 parameters.put("add_m_id", add_m_id);
        	 List<MemAddressDTO> result = mapper.addressCheck(parameters);
			return result;

       }
       @Override
       public int deleteAddr(@Param("add_num") int add_num ,String add_m_id) {
    	return mapper.deleteAddr(add_num,add_m_id);   
       }
       
       @Override
       public void  updateAddr(MemAddressDTO  adto) {
    	  mapper.updateAddr(adto);   
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
         
         
         
         public List<ShoppingCartDTO> getShoppingCartItemsPaged(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto,PDetailDTO pddto) {
             int startRow = (page - 1) * pageSize + 1;
             int endRow = startRow + pageSize - 1;

             Map<String, Object> parameters = new HashMap<>();
             parameters.put("shop_m_id", shop_m_id);
             parameters.put("startRow", startRow);
             parameters.put("endRow", endRow);

          //   return mapper.getShoppingCartItemsPaged(parameters);
             List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged(parameters);
             System.out.println("占쎈쐻占쎈윞占쎈쭓�뜝�럥�몡�넭怨ｋ쳳獒뺧옙 占쎈쐻占쎈윪繹먮뜉�삕�뜮�벩�쐻�뜝占� - 占쎈쐻占쎈윥占쎌뱻占쎈쐻占쎈윪�얠쥉異�占쎌돸占쎌굲: " + page + ", 占쎈눇�뙼�맪占쎌���삕亦낉옙 占쎈쨬占쎈즵獒뺣돍�삕占쎈묄: " + result.size());
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
        	 System.out.println("占쎈쐻占쎈윞占쎈쭓�뜝�럥�몡�넭怨ｋ쳳獒뺧옙 占쎈쐻占쎈윪繹먮뜉�삕�뜮�벩�쐻�뜝占� - 占쎈쐻占쎈윥占쎌뱻占쎈쐻占쎈윪�얠쥉異�占쎌돸占쎌굲: " + page + ", 占쎈눇�뙼�맪占쎌���삕亦낉옙 占쎈쨬占쎈즵獒뺣돍�삕占쎈묄: " + result.size());
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
      
             @Transactional
             @Override
             public void deleteSelectedItems(List<Long> selectedShopNums, String shop_m_id) {
                 Map<String, Object> paramMap = new HashMap<>();
                 paramMap.put("selectedShopNums", selectedShopNums);
                 paramMap.put("shop_m_id", shop_m_id);

                 System.out.print("list =======================" + selectedShopNums);
                 System.out.print("shop_m_id =======================" + shop_m_id);
                 
                 mapper.deleteSelectedItems(paramMap);
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
                System.out.println("占쎈쐻占쎈윞占쎈쭓�뜝�럥�몡�넭怨ｋ쳳獒뺧옙 占쎈쐻占쎈윪繹먮뜉�삕�뜮�벩�쐻�뜝占� - 占쎈쐻占쎈윥占쎌뱻占쎈쐻占쎈윪�얠쥉異�占쎌돸占쎌굲: " + page + ", 占쎈눇�뙼�맪占쎌���삕亦낉옙 占쎈쨬占쎈즵獒뺣돍�삕占쎈묄: " + result.size());
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
            	 System.out.println("占쎈쐻占쎈윞占쎈쭓�뜝�럥�몡�넭怨ｋ쳳獒뺧옙 占쎈쐻占쎈윪繹먮뜉�삕�뜮�벩�쐻�뜝占� - 占쎈쐻占쎈윥占쎌뱻占쎈쐻占쎈윪�얠쥉異�占쎌돸占쎌굲: " + page + ", 占쎈눇�뙼�맪占쎌���삕亦낉옙 占쎈쨬占쎈즵獒뺣돍�삕占쎈묄: " + result.size());
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
                   
                   
                   System.out.println(("id占쎈쐻占쎈윥獒뺧옙 �뜝�럥�떛�뜝�럥�깓癲뉖낌�뒻占쎌굲�뜝�럥�뜲占쎈쨬占쎈즸占쎌굲占쎈쐻占쎈윪占쎈�� ~~~=-============================")+parameters.get("ppic_m_id"));
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); // 癲ル슢�뵞占쎄콨占쎈슣沅섓옙�룱占쎈쐻占쎈짗占쎌굲�뜝�럥鍮� 占쎈쐻占쎈윪占쎌벁占쎈쐻占쎈윥�젆占�
                   
                    
                    System.out.println("占쎈쐻占쎈윞占쎈쭓�뜝�럥�몡�넭怨ｋ쳳獒뺧옙 占쎈쐻占쎈윪繹먮뜉�삕�뜮�벩�쐻�뜝占� - 占쎈쐻占쎈윥占쎌뱻占쎈쐻占쎈윪�얠쥉異�占쎌돸占쎌굲: " + page + ", 占쎈눇�뙼�맪占쎌���삕亦낉옙 占쎈쨬占쎈즵獒뺣돍�삕占쎈묄: " + result.size());
                   
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