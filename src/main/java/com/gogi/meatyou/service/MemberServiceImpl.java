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
                model.addAttribute("message", " 뜝 럥 맶 뜝 럥 쑋占쎌뼚횞 떋 띿삕 몭 궪 삕占쎄뎡  뜝 럥 맶 뜝 럥 쐾 뜝 럥彛▼뜝 럥 맶 뜝 럥 쐾占쎈닱筌롡뫀 맶 뜝 럥 쑅鶯ㅼ룊 삕  뜝 럥 맶 뜝 럥 쐾嶺뚮씭占쏙퐢 맶 뜝 럥 쑅占쎈쐻占쎈윥 굜 뜉 삕占쎄뎡占쎈쐻占쎈윥 뤃占  占쎈쐻占쎈윥占쎈굘占쎈쐻占쎈윥 댚 렱 쐻占쎈윞占쎈뼎 뜝 럥 맶 뜝 럥 쑅嶺뚮ギ 벉 맶 뜝 럥 쑅 뜏類Ｅ 占쎌맶 뜝 럥 쑅占쎈쑏占쎌뵢占쎌맶 뜝 럥 쑅占쎌젂 뜝占 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " 뜝 럥 맶 뜝 럥 쐾嶺뚮씭占쏙퐢 맶 뜝 럥 쑅占쎈쐻占쎈윥 굜 뜉 삕占쎄뎡占쎈쐻占쎈윥 뤃占   뜝 럥 맶 뜝 럥 쐾 뜝 럥彛▼뜝 럥 맶 뜝 럥 쐾占쎈닱筌롡뫀 맶 뜝 럥 쑅嶺뚮ギ 벉 맶 뜝 럥 쑅 뜏類Ｅ 占쎌맶 뜝 럥 쑅占쎈쑏占쎌뵢占쎌맶 뜝 럥 쑅占쎌젂 뜝占 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          //  뜝 럥 맶 뜝 럥 쑋占쎌뼚횞 떋 띿삕 몭 궪 삕占쎄뎡  솾 꺂 뒖占쎈１ 뜏類ｋ룏占쎌굲占쎌뼲 삕  뜝 럥 맶 뜝 럥 쑅 뜝 럥 룎 뜝 럥 맶 뜝 럥 쑅 뜝 럡 맖  뜝 럥 맶 뜝 럥 쑋占쎄덩占쎈 섓옙 맶 뜝 럥 쑅占쎌젂熬곥끉 븸 슖 볥걙占쎄뎡  솾 꺂 뒖占쎈１ 뜏類ｋ룏占쎌굲 뛾占썲뜝占   뜝 럥 맶 뜝 럥 쑅 뜝 럥琉끻뜝 럥 맶 뜝 럥 쑋 뜝 럩議 
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " 뜝 럥 맶 뜝 럥 쑋占쎌뼚횞 떋 띿삕 몭 궪 삕占쎄뎡  뜝 럥 맶 뜝 럥 쐾 뜝 럥彛▼뜝 럥 맶 뜝 럥 쐾占쎈닱筌롡뫀 맶 뜝 럥 쑅鶯ㅼ룊 삕  뜝 럥 맶 뜝 럥 쐾嶺뚮씭占쏙퐢 맶 뜝 럥 쑅占쎈쐻占쎈윥 굜 뜉 삕占쎄뎡占쎈쐻占쎈윥 뤃占  占쎈쐻占쎈윥占쎈굘占쎈쐻占쎈윥 댚 렱 쐻占쎈윞占쎈뼎 뜝 럥 맶 뜝 럥 쑅嶺뚮ギ 벉 맶 뜝 럥 쑅 뜏類Ｅ 占쎌맶 뜝 럥 쑅占쎈쑏占쎌뵢占쎌맶 뜝 럥 쑅占쎌젂 뜝占 .");
          }
       } else {
          //  솾 꺂 뒖占쎈１ 뜏類ｋ룏占쎌굲占쎌뼲 삕  뜝 럥 맶 뜝 럥 쑅 뜝 럥 룎 뜝 럥 맶 뜝 럥 쑅 뜝 럡 맖  뜝 럥 맶 뜝 럥 쑋占쎄덩占쎈 욅빊占썲뜝 럩 뤈 뜝 럩援   뜝 럥 맶 뜝 럥 쑅占쎈ご占쎄뭍占쎌맶 뜝 럥 쑅占쎌젂熬곥끉 븸 슖 볥걙占쎄뎡  솾 꺂 뒖占쎈１ 뜏類ｋ룏占쎌굲 뛾占썲뜝占  占쎈쐻占쎈윥占쎈룾 뜝 럡猿  뜝 럥吏쀥뜝 럩援 
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " 뜝 럥 맶 뜝 럥 쐾嶺뚮씭占쏙퐢 맶 뜝 럥 쑅占쎈쐻占쎈윥 굜 뜉 삕占쎄뎡占쎈쐻占쎈윥 뤃占   뜝 럥 맶 뜝 럥 쐾 뜝 럥彛▼뜝 럥 맶 뜝 럥 쐾占쎈닱筌롡뫀 맶 뜝 럥 쑅嶺뚮ギ 벉 맶 뜝 럥 쑅 뜏類Ｅ 占쎌맶 뜝 럥 쑅占쎈쑏占쎌뵢占쎌맶 뜝 럥 쑅占쎌젂 뜝占 .");
          }
       }
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
             System.out.println(" 뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩留썲뜝 럥 맶 뜝 럥 쑅鶯ㅼ룆 굫占쎌굲 뜝 럩留띰옙 쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎄콞占쎈쐻占쎈윥 땻醫묒삕占쎈쳟占쎈쭒占쎈뙕占쎈 욑옙 맚嶺뚮Ĳ猷귨옙援   뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐앭슖 떜媛  걫 뜝 럩留뗰옙 쐻占쎈윪 뤃轅⑤쐻占쎈윥占쎈ぁ占쎈쐻占쎈윥 댆 뜴 쐻占쎈윪筌띾쑚 쐻占쎈윥占쎈㎍ 뜝 럥 맶占쎈쐻 뜝占  -  뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐쀥뜝 럥 맶 뜝 럥 쑋 뛾占쏙옙沅랃옙 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐앾옙 쐻占쎈윪 젆 떐 렭占쎈쭫 뜮 벩 쐻占쎈쑕占쎌맶 뜝 럥 쑋 뜝 럥夷  뜝 럥 맶 뜝 럥 쑋占쎈쨨 뜝占 : " + page + ",  뜝 럥 맶 뜝 럥 쑅 뜝 럥 뱥占쎈쐻占쎈윥占쎈눁占쎈쐻占쎈윥占쎈젒 뜝 럥 맶 뜝 럥 쑋占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈윪 뤃轅댁뜏 뜝 룞 삕 뤃占썲뜝 럩援   뜝 럥 맶 뜝 럥 쑅勇싲·猿딆맶 뜝 럥 쑅嶺뚯빖諭븝옙 맚嶺뚮㉡ 맊 뙴琉룸쐻占쎈윪 뤃 먯삕占쎌맶 뜝 럥 쑅占쎈닱 뜝占 : " + result.size());
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
            System.out.println(" 뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩留썲뜝 럥 맶 뜝 럥 쑅鶯ㅼ룆 굫占쎌굲 뜝 럩留띰옙 쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎄콞占쎈쐻占쎈윥 땻醫묒삕占쎈쳟占쎈쭒占쎈뙕占쎈 욑옙 맚嶺뚮Ĳ猷귨옙援   뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐앭슖 떜媛  걫 뜝 럩留뗰옙 쐻占쎈윪 뤃轅⑤쐻占쎈윥占쎈ぁ占쎈쐻占쎈윥 댆 뜴 쐻占쎈윪筌띾쑚 쐻占쎈윥占쎈㎍ 뜝 럥 맶占쎈쐻 뜝占  -  뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐쀥뜝 럥 맶 뜝 럥 쑋 뛾占쏙옙沅랃옙 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐앾옙 쐻占쎈윪 젆 떐 렭占쎈쭫 뜮 벩 쐻占쎈쑕占쎌맶 뜝 럥 쑋 뜝 럥夷  뜝 럥 맶 뜝 럥 쑋占쎈쨨 뜝占 : " + page + ",  뜝 럥 맶 뜝 럥 쑅 뜝 럥 뱥占쎈쐻占쎈윥占쎈눁占쎈쐻占쎈윥占쎈젒 뜝 럥 맶 뜝 럥 쑋占쎈쐻占쎈짗占쎌굲占쎈쐻占쎈윪 뤃轅댁뜏 뜝 룞 삕 뤃占썲뜝 럩援   뜝 럥 맶 뜝 럥 쑅勇싲·猿딆맶 뜝 럥 쑅嶺뚯빖諭븝옙 맚嶺뚮㉡ 맊 뙴琉룸쐻占쎈윪 뤃 먯삕占쎌맶 뜝 럥 쑅占쎈닱 뜝占 : " + result.size());
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
                   
                   
                  
                   
                   List<PPicDTO> result = mapper.pPickCountPage(parameters); //  뜝 럩 뀋 뜝 럡 땽 뜝 럥裕쏉옙 쐻占쎈윥占쎄샵 뜝 럥 맶 뜝 럥 쐾占쎄턀占쎈쾫占쎌맶 뜝 럥 쑅 뜝 럥裕앶썒占쏙옙援욅몭 궪 삕占쎄뎡占쎈쐻占쎈윥塋딅쵓 삕占쎌맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅嶺뚯쉸占싸살맶 뜝 럥 쑋占쎈쨨占쎈Ŋ 굲 뜝 럩留띰옙 쐻占쎈윥占쎌몗 뜝 럥 몡占쎈쐻 뜝占   뜝 럥 맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐앭뜝 럥 맶 뜝 럥 쑋 뵓怨뚮굫占쎌맶 뜝 럥 쑅 뜝 럥 럪 뜝 럥 맶 뜝 럥 쑅 뜝 럩紐쀯옙 쐻占쎈윪占쎌읆 뜝 럥 맶占쎈쐻 뜝占 
                   
                    
                   
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