package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
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
  
    @Override
    public int ppickAndpickMeCount(@Param("pm_m_id")String pm_m_id,@Param("pm_c_id")String pm_c_id, @Param("pm_num") int pm_num) {
        return mapper.ppickAndpickMeCount( pm_m_id, pm_c_id ,pm_num);
    }
    
    public void pick_me_delete(Model model,PickMeDTO pdto,ProductDTO ppdto,@Param("pm_m_id")String pm_m_id,
          @Param("pm_c_id") String pm_c_id
       ,@Param("pm_num") int pm_num ){
    }
 
    
    @Override
    public void pickMeInsert(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
        int pickCount = mapper.ppickAndpickMeCount(pm_m_id, pm_c_id, pm_num);

        if (pickCount > 0) {
            int deleteResult = mapper.deletePickMeByCId(pm_m_id, pm_c_id);
            if (deleteResult > 0) {
                model.addAttribute("message", " �쐻 �윥 留� �쐻 �윥 �몝�뜝�럩堉싴슎 �뼀 �씮�굲 紐� 沅� �굲�뜝�럡�렊  �쐻 �윥 留� �쐻 �윥 �맽 �쐻 �윥壤쎻뼹�쐻 �윥 留� �쐻 �윥 �맽�뜝�럥�떛嶺뚮　維� 留� �쐻 �윥 �몗傭��끉猷� �굲  �쐻 �윥 留� �쐻 �윥 �맽蒻븍슢�뵯�뜝�룞�맊 留� �쐻 �윥 �몗�뜝�럥�맶�뜝�럥�쑅 援� �쐣 �굲�뜝�럡�렊�뜝�럥�맶�뜝�럥�쑅 琉껃뜝  �뜝�럥�맶�뜝�럥�쑅�뜝�럥援섇뜝�럥�맶�뜝�럥�쑅 �뙕 �젿 �맶�뜝�럥�쐾�뜝�럥堉� �쐻 �윥 留� �쐻 �윥 �몗蒻븍슢�궙 踰� 留� �쐻 �윥 �몗 �쐪窈욑샵 �뜝�럩留� �쐻 �윥 �몗�뜝�럥�몡�뜝�럩逾℡뜝�럩留� �쐻 �윥 �몗�뜝�럩�쟼 �쐻�뜝 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " �쐻 �윥 留� �쐻 �윥 �맽蒻븍슢�뵯�뜝�룞�맊 留� �쐻 �윥 �몗�뜝�럥�맶�뜝�럥�쑅 援� �쐣 �굲�뜝�럡�렊�뜝�럥�맶�뜝�럥�쑅 琉껃뜝   �쐻 �윥 留� �쐻 �윥 �맽 �쐻 �윥壤쎻뼹�쐻 �윥 留� �쐻 �윥 �맽�뜝�럥�떛嶺뚮　維� 留� �쐻 �윥 �몗蒻븍슢�궙 踰� 留� �쐻 �윥 �몗 �쐪窈욑샵 �뜝�럩留� �쐻 �윥 �몗�뜝�럥�몡�뜝�럩逾℡뜝�럩留� �쐻 �윥 �몗�뜝�럩�쟼 �쐻�뜝 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " �쐻 �윥 留� �쐻 �윥 �몝�뜝�럩堉싴슎 �뼀 �씮�굲 紐� 沅� �굲�뜝�럡�렊  �쐻 �윥 留� �쐻 �윥 �맽 �쐻 �윥壤쎻뼹�쐻 �윥 留� �쐻 �윥 �맽�뜝�럥�떛嶺뚮　維� 留� �쐻 �윥 �몗傭��끉猷� �굲  �쐻 �윥 留� �쐻 �윥 �맽蒻븍슢�뵯�뜝�룞�맊 留� �쐻 �윥 �몗�뜝�럥�맶�뜝�럥�쑅 援� �쐣 �굲�뜝�럡�렊�뜝�럥�맶�뜝�럥�쑅 琉껃뜝  �뜝�럥�맶�뜝�럥�쑅�뜝�럥援섇뜝�럥�맶�뜝�럥�쑅 �뙕 �젿 �맶�뜝�럥�쐾�뜝�럥堉� �쐻 �윥 留� �쐻 �윥 �몗蒻븍슢�궙 踰� 留� �쐻 �윥 �몗 �쐪窈욑샵 �뜝�럩留� �쐻 �윥 �몗�뜝�럥�몡�뜝�럩逾℡뜝�럩留� �쐻 �윥 �몗�뜝�럩�쟼 �쐻�뜝 .");
          }
       } else {
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " �쐻 �윥 留� �쐻 �윥 �맽蒻븍슢�뵯�뜝�룞�맊 留� �쐻 �윥 �몗�뜝�럥�맶�뜝�럥�쑅 援� �쐣 �굲�뜝�럡�렊�뜝�럥�맶�뜝�럥�쑅 琉껃뜝   �쐻 �윥 留� �쐻 �윥 �맽 �쐻 �윥壤쎻뼹�쐻 �윥 留� �쐻 �윥 �맽�뜝�럥�떛嶺뚮　維� 留� �쐻 �윥 �몗蒻븍슢�궙 踰� 留� �쐻 �윥 �몗 �쐪窈욑샵 �뜝�럩留� �쐻 �윥 �몗�뜝�럥�몡�뜝�럩逾℡뜝�럩留� �쐻 �윥 �몗�뜝�럩�쟼 �쐻�뜝 .");
          }
       }
    }
    
  @Override
  public int couponCount(@Param("cp_m_id") String cp_m_id) {
    	return mapper.couponCount(cp_m_id);
    }
    
    
    @Override
   public  List<CouponDTO>  howmuchCoupon(@Param("cp_m_id") String cp_m_id) {
    	return mapper.howmuchCoupon(cp_m_id);
    }
    
    
    
    
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
       public List<MemAddressDTO>addressCheck(String add_m_id,MemberDTO mdto,MemAddressDTO adto,int add_num) {
            
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("add_m_id", add_m_id);
            List<MemAddressDTO> result = mapper.addressCheck(parameters);
         return result;

       }
       
       @Override        
       public List<MemAddressDTO> combined_address(String add_m_id,String combined_address ,MemAddressDTO adto )  {
         
                
          Map<String, Object> parameters=new HashMap<>();
          parameters.put("add_m_id",add_m_id);
          parameters.put("combined_address",combined_address);
             List<MemAddressDTO> result =mapper.combined_address(parameters);
          
                      return result;
        
          
       }
       @Override
       public int deleteAddr(@Param("add_num") int add_num ,String add_m_id) {
       return mapper.deleteAddr(add_num,add_m_id);   
       }
     
       
       @Override
       public  int addressCount(@Param("add_m_id")String add_m_id,@Param("add_num")int add_num  ) {
          return mapper.addressCount(add_m_id, add_num  );           
       }
          
       @Override         
       public void updateAddr(MemAddressDTO adto,@Param("add_m_id") String add_m_id,List<MemAddressDTO> AddrList,@Param("add_num")int add_num,@Param("add_mem_address1") String add_mem_address1,@Param("add_mem_address2") String add_mem_address2) {
          mapper. updateAddr( adto,  add_m_id,add_mem_address1,add_mem_address2,add_num);
       }
       @Override
       public void  insertAddr(MemAddressDTO  adto) {
          
          mapper.insertAddr(adto);  
          
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
             System.out.println(" �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪�븰�뜴�쐻 �윥 留� �쐻 �윥 �몗傭��끉猷� 援ュ뜝�럩援� �쐻 �윪�븰�씛�삕 �맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅 �빝�넫臾믪굲�뜝�럥爾잌뜝�럥彛믣뜝�럥�솗�뜝�럥 �쉻�삕 留싧떵�슢캉�뙴洹⑥삕�뤃   �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯빆�뒙 �뼔揶�  嫄� �쐻 �윪�븰�뿰�삕 �맶�뜝�럥�쑋 琉껇퐛�뫀�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑅 �뙀 �쑕 �맶�뜝�럥�쑋嶺뚮씭�몱 �맶�뜝�럥�쑅�뜝�럥�럪 �쐻 �윥 留뜹뜝�럥�맶 �쐻�뜝  -  �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯�λ쐻 �윥 留� �쐻 �윥 �몝 �쎗�뜝�룞�삕亦낅엪�삕 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯빢�삕 �맶�뜝�럥�쑋 �젂 �뼆 �젺�뜝�럥彛� �쑏 踰� �맶�뜝�럥�몧�뜝�럩留� �쐻 �윥 �몝 �쐻 �윥鸚�  �쐻 �윥 留� �쐻 �윥 �몝�뜝�럥夷� �쐻�뜝 : " + page + ",  �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 諭ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�늸�뜝�럥�맶�뜝�럥�쑅�뜝�럥�젔 �쐻 �윥 留� �쐻 �윥 �몝�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�쑋 琉껇퐛�똻�쐪 �쐻 猷� �굲 琉껃뜝�뜴�쐻 �윪�뤃   �쐻 �윥 留� �쐻 �윥 �몗�땱�떜쨌�뙼�봿留� �쐻 �윥 �몗蒻븍슣鍮뽬キ釉앹삕 留싧떵�슢�돘 留� �쇀�릧猷몄맶�뜝�럥�쑋 琉� 癒��굲�뜝�럩留� �쐻 �윥 �몗�뜝�럥�떛 �쐻�뜝 : " + result.size());
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
            System.out.println(" �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪�븰�뜴�쐻 �윥 留� �쐻 �윥 �몗傭��끉猷� 援ュ뜝�럩援� �쐻 �윪�븰�씛�삕 �맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅�뜝�럡肄욃뜝�럥�맶�뜝�럥�쑅 �빝�넫臾믪굲�뜝�럥爾잌뜝�럥彛믣뜝�럥�솗�뜝�럥 �쉻�삕 留싧떵�슢캉�뙴洹⑥삕�뤃   �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯빆�뒙 �뼔揶�  嫄� �쐻 �윪�븰�뿰�삕 �맶�뜝�럥�쑋 琉껇퐛�뫀�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑅 �뙀 �쑕 �맶�뜝�럥�쑋嶺뚮씭�몱 �맶�뜝�럥�쑅�뜝�럥�럪 �쐻 �윥 留뜹뜝�럥�맶 �쐻�뜝  -  �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯�λ쐻 �윥 留� �쐻 �윥 �몝 �쎗�뜝�룞�삕亦낅엪�삕 留� �쐻 �윥 �몗 �쐻 �윥 �윫 �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윪榮먯빢�삕 �맶�뜝�럥�쑋 �젂 �뼆 �젺�뜝�럥彛� �쑏 踰� �맶�뜝�럥�몧�뜝�럩留� �쐻 �윥 �몝 �쐻 �윥鸚�  �쐻 �윥 留� �쐻 �윥 �몝�뜝�럥夷� �쐻�뜝 : " + page + ",  �쐻 �윥 留� �쐻 �윥 �몗 �쐻 �윥 諭ε뜝�럥�맶�뜝�럥�쑅�뜝�럥�늸�뜝�럥�맶�뜝�럥�쑅�뜝�럥�젔 �쐻 �윥 留� �쐻 �윥 �몝�뜝�럥�맶�뜝�럥吏쀥뜝�럩援꿨뜝�럥�맶�뜝�럥�쑋 琉껇퐛�똻�쐪 �쐻 猷� �굲 琉껃뜝�뜴�쐻 �윪�뤃   �쐻 �윥 留� �쐻 �윥 �몗�땱�떜쨌�뙼�봿留� �쐻 �윥 �몗蒻븍슣鍮뽬キ釉앹삕 留싧떵�슢�돘 留� �쇀�릧猷몄맶�뜝�럥�쑋 琉� 癒��굲�뜝�럩留� �쐻 �윥 �몗�뜝�럥�떛 �쐻�뜝 : " + result.size());
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
                   
                   
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); 
                   
                    
                   
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

    
    
           
         @Override
         public  List<ShoppingCartDTO> getSelectedProducts(List<String> selectedShopNums, @Param("add_m_id") String add_m_id) {
             return mapper.getSelectedProducts(selectedShopNums, add_m_id);
         }
            
            
         
            
            
             
             
             
}