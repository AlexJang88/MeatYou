package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PPicDTO;
import com.gogi.meatyou.bean.PickMeDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.SelectedProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.bean.UserPayDTO;
import com.gogi.meatyou.repository.MemberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper mapper;
    
    @Autowired
	private JavaMailSenderImpl mailSender;
	private int authNumber; 
    
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
                model.addAttribute("message", " �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥먨뜝�럥�맶�뜝�럥�쑋占쎌젂占쎈뼖占쎈뮂 �뜝�럥占� �뜝�럩逾겼뜝�럡�렊 嶺뚮쪋�삕 雅��굢�삕 �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃  �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒 �뜝�럩留� �뜝�럩�몗傭��끉�젻�젆諭꾩삕占쎈㎍ �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒�뜝�럥�맶�뜝�럥�쑅�뜝�럥堉볡솾�꺂�뒧占쏙옙力놂옙�뜝占� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬占쎈쇀�뜝占� �뜝�럡�렊  �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒占쎈�꿴뇡�씮�뮛�뜝�럥�럯�뜝�럥�맶�뜝�럥吏쀥뜝�럥彛� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 占쎈쨨�뜝占� �뜝�럩留� �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 嶺뚮슡�뜉占쎌맶  �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎈쨨占쎄섈占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� �뜝�럥�넇 �뜝�럩議� �뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅占쎌젂�뜝占� �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙裕꿴뇡�씮�뮛�뜝�럡�뀮 �뵓怨ㅼ삕 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥� �뜝�럩留싷┤�뜆�돸占쎄틣 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑋占쎌뻹苑믭옙�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑋�뜝�럩�쓥 �뜝�럩留뜹뜝�럥�맶 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒占쎈�꿴뇡�씮�뮛�뜝�럥�럯�뜝�럥�맶�뜝�럥吏쀥뜝�럥彛� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 占쎈쨨�뜝占� �뜝�럩留� �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 嶺뚮슡�뜉占쎌맶   �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒 �뜝�럩留� �뜝�럩�몗傭��끉�젻�젆諭꾩삕占쎈㎍ �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒�뜝�럥�맶�뜝�럥�쑅�뜝�럥堉볡솾�꺂�뒧占쏙옙力놂옙�뜝占� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙裕꿴뇡�씮�뮛�뜝�럡�뀮 �뵓怨ㅼ삕 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥� �뜝�럩留싷┤�뜆�돸占쎄틣 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑋占쎌뻹苑믭옙�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑋�뜝�럩�쓥 �뜝�럩留뜹뜝�럥�맶 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥먨뜝�럥�맶�뜝�럥�쑋占쎌젂占쎈뼖占쎈뮂 �뜝�럥占� �뜝�럩逾겼뜝�럡�렊 嶺뚮쪋�삕 雅��굢�삕 �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃  �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒 �뜝�럩留� �뜝�럩�몗傭��끉�젻�젆諭꾩삕占쎈㎍ �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒�뜝�럥�맶�뜝�럥�쑅�뜝�럥堉볡솾�꺂�뒧占쏙옙力놂옙�뜝占� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙沅섇뜝�룞�삕占쎄콬占쎈쇀�뜝占� �뜝�럡�렊  �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒占쎈�꿴뇡�씮�뮛�뜝�럥�럯�뜝�럥�맶�뜝�럥吏쀥뜝�럥彛� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 占쎈쨨�뜝占� �뜝�럩留� �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 嶺뚮슡�뜉占쎌맶  �뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐쀥뜝�럥�맶�뜝�럥�쑅占쎈쨨占쎄섈占쎌맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� �뜝�럥�넇 �뜝�럩議� �뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩留썲뜝�럥�맶�뜝�럥�쑅占쎌젂�뜝占� �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙裕꿴뇡�씮�뮛�뜝�럡�뀮 �뵓怨ㅼ삕 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥� �뜝�럩留싷┤�뜆�돸占쎄틣 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑋占쎌뻹苑믭옙�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑋�뜝�럩�쓥 �뜝�럩留뜹뜝�럥�맶 .");
          }
       } else {
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒占쎈�꿴뇡�씮�뮛�뜝�럥�럯�뜝�럥�맶�뜝�럥吏쀥뜝�럥彛� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 占쎈쨨�뜝占� �뜝�럩留� �뜝�럡�렊�뜝�럥�맶�뜝�럥�쐾�뜝�럥�젃�뜝�럥�맶�뜝�럥�쑅�뜝�럥�럪�뜝�럥�맶�뜝�럥�쑅�뜝�럩紐� 嶺뚮슡�뜉占쎌맶   �뜝�럩留� �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒 �뜝�럩留� �뜝�럩�몗傭��끉�젻�젆諭꾩삕占쎈㎍ �뜝�럩�몗 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥�렒�뜝�럥�맶�뜝�럥�쑅�뜝�럥堉볡솾�꺂�뒧占쏙옙力놂옙�뜝占� 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥딉옙裕꿴뇡�씮�뮛�뜝�럡�뀮 �뵓怨ㅼ삕 嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥� �뜝�럩留싷┤�뜆�돸占쎄틣 �뜝�럥�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑅�뜝�럥�걖�뜝�럥�맶�뜝�럥�쑋占쎌뻹苑믭옙�맶�뜝�럥�쑋嶺뚮엪�삕 �뜝�럩留� �뜝�럩�몗 �뜝�럥泥듿뜝�럥�맶�뜝�럥�쑋�뜝�럩�쓥 �뜝�럩留뜹뜝�럥�맶 .");
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
         
         
         
 
          
         public List<ShoppingCartDTO> getShoppingCartItemsPaged2(String shop_m_id, int page, int pageSize, ShoppingCartDTO sdto, ProductDTO pdto,PDetailDTO pddto) {
        	 int startRow = (page - 1) * pageSize + 1;
        	 int endRow = startRow + pageSize - 1;
        	 
        	 Map<String, Object> parameters = new HashMap<>();
        	 parameters.put("shop_m_id", shop_m_id);
        	 parameters.put("startRow", startRow);
        	 parameters.put("endRow", endRow);
        	 
        	 //   return mapper.getShoppingCartItemsPaged(parameters);
        	 List<ShoppingCartDTO> result = mapper.getShoppingCartItemsPaged2(parameters);
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
         public  ShoppingCartDTO getSelectedProducts(int shop_num, @Param("add_m_id") String add_m_id ) {
             return mapper.getSelectedProducts(shop_num, add_m_id );
         }
         @Override
         public  ShoppingCartDTO getSelectedProducts2(int shop_p_num, @Param("add_m_id") String add_m_id ) {
        	 return mapper.getSelectedProducts2(shop_p_num, add_m_id );
         }

		@Override
		public CouponDTO findCouponToCpNum(int cp_num) {
			return mapper.findCouponToCpNum(cp_num);
		}

		@Override
		public List<UserPayDTO> findshop_p_num(HashMap hashmap) {
			// TODO Auto-generated method stub
			return mapper.findshop_p_num(hashmap);
		 
		}
		
		
		
		@Override			 
		public int twoNextPay(MOrderDTO mdto,int shop_num,int order_p_num,String order_memo
				,@Param("order_m_id") String order_m_id,int order_cp_num,int order_p_price,
				@Param("order_dere_pay") int order_dere_pay ,@Param("order_addr") String order_addr,@Param("order_discount") int order_discount,@Param("order_quantity") int order_quantity
	    		,@Param("order_totalprice") int order_totalprice ) {
		
			return mapper.twoNextPay( mdto, shop_num ,  order_p_num, order_memo, order_m_id, order_cp_num,  order_p_price  , order_dere_pay, order_addr, order_discount, order_quantity
		    		, order_totalprice)  ;
		}

		@Override
		public List<MOrderDTO> paypage(@Param("order_m_id") String order_m_id , int page, int pageSize ){
			  int startRow = (page  - 1) * pageSize + 1;
              int endRow = startRow + pageSize - 1;
              
              Map<String, Object> parameters = new HashMap<>();
              parameters.put("order_m_id", order_m_id);
              parameters.put("startRow", startRow);
              parameters.put("endRow", endRow);
              
              //   return mapper.getShoppingCartItemsPaged(parameters);
              List<MOrderDTO> result = mapper.paypage(parameters);
              return result;
		}

		 
		@Override
		public int PaymentCount(@Param("order_m_id") String order_m_id ) {
			 Map<String, Object> parameters = new HashMap<>();
             parameters.put("pm_m_id", order_m_id);
             return mapper.PaymentCount(parameters);
		}
		
		
		//여기서 부터 이메일
		
		public void makeRandomNumber() {
			// 난수의 범위 111111 ~ 999999 (6자리 난수)
			Random r = new Random();
			int checkNum = r.nextInt(888888) + 111111;
			System.out.println("인증번호 : " + checkNum);
			authNumber = checkNum;
		}
		
		@Override
		public String joinEmail(String email) {
			makeRandomNumber();
			String setFrom = "jaus0708@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력 
			String toMail = email;
			String title = "아이디/비밀번호 찾기 메일입니다."; // 이메일 제목 
			String content = 
					"홈페이지를 방문해주셔서 감사합니다." + 	//html 형식으로 작성 ! 
		            "<br><br>" + 
		            "인증 번호는 " + authNumber + "입니다." + 
		            "<br>" + 
				    "해당 인증번호를 인증번호 확인란에 기입하여 주세요."; //이메일 내용 삽입
			mailSend(setFrom, toMail, title, content);
			return Integer.toString(authNumber);
		}

		
		//이메일 전송 메소드
				public void mailSend(String setFrom, String toMail, String title, String content) { 
					MimeMessage message = mailSender.createMimeMessage();
					// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
					try {
						MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
						helper.setFrom(setFrom);
						helper.setTo(toMail);
						helper.setSubject(title);
						// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
						helper.setText(content,true);
						mailSender.send(message);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

		@Override
		public int findId(MemberDTO memberdto) {
			int a= 0;
			String dbName = null;
			String dbPhone = null;
			dbName = mapper.dbName(memberdto); //디비속 이름
			dbPhone =mapper.dbPhone(memberdto);// 디비속 전화번호			
					
			String SName = memberdto.getM_name(); //입력한 이름
	    	String Sphone = memberdto.getPhone();  //입력한 전화번호
	    	
	    	if (dbName != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone)) {
	            a = 1;
	        }
			return a;
		}

		@Override
		public void getDbId(Model model, MemberDTO memberdto) {
			String m_id = mapper.getDbId(memberdto);//실제 아이디가져오기			
			model.addAttribute("m_id", m_id);
		}

		@Override
		public int findPw(MemberDTO memberdto) {
			int a= 0;
			String dbName = null;
			String dbPhone = null;
			String dbId = null;
			
			dbName = mapper.dbName(memberdto); //디비속 이름
			dbPhone =mapper.dbPhone(memberdto);// 디비속 전화번호
			dbId = mapper.dbId(memberdto); // 디비속 아이디
					
			String SName = memberdto.getM_name(); //입력한 이름
	    	String Sphone = memberdto.getPhone();  //입력한 전화번호
			String SId = memberdto.getM_id(); // 입력한 아이디
			
			if(dbName != null && dbId != null && dbPhone != null && dbName.equals(SName) && dbPhone.equals(Sphone) && dbId.equals(SId) ) {
				a = 1;
			}
			return a;
		}

		@Override
		public void getDbPw(Model model, MemberDTO memberdto) {
			String passwd = mapper.getDbPw(memberdto);//실제 비번가져오기
			
			model.addAttribute("passwd", passwd);			
			model.addAttribute("id", memberdto.getM_id());			
			model.addAttribute("phone", memberdto.getPhone());			
		}
           
         
            
             
             
             
}