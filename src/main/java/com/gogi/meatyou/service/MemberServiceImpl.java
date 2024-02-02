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
import com.gogi.meatyou.bean.UserPayDTO;
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
                model.addAttribute("message", " 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윪�젆�떞�뒑 占쎈� 占쎌뵰占쎄뎡 筌륅옙 亦낉옙 占쎄뎡占쎈쐻占쎈윞占쎈젇  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧ 占쎌맶 占쎌쑅鶯ㅼ렮堉뱄옙�맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧占쎈쐻占쎈윥占쎈뼓癲ル슢��泳�占� 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�궘占쏙옙�걠�뙴占� 占쎄뎡  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧�뮲釉띿뒧占쎈뎐占쎈쐻占쎈짗占쎈쭒 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 �뤃占� 占쎌맋 占쎄뎡占쎈쐻占쎈윞占쎈젇占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 筌뚭퍌�쐻  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥�뤃�꼪�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈솗 占쎌졒 占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�젆占� 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒧占쎄텤 甕곤옙 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맚囹덉쉻�꺏 占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈ぁ占쎈쐻占쎈윪�얄꽒�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윪占쎌읆 占쎌맶占쎈쐻 .");
            }
        } else {
            int insertResult = mapper.pickMeInsert(pdto, pm_m_id, pm_c_id, pm_num);
            if (insertResult > 0) {
                model.addAttribute("message", " 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧�뮲釉띿뒧占쎈뎐占쎈쐻占쎈짗占쎈쭒 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 �뤃占� 占쎌맋 占쎄뎡占쎈쐻占쎈윞占쎈젇占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 筌뚭퍌�쐻   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧ 占쎌맶 占쎌쑅鶯ㅼ렮堉뱄옙�맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧占쎈쐻占쎈윥占쎈뼓癲ル슢��泳�占� 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒧占쎄텤 甕곤옙 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맚囹덉쉻�꺏 占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈ぁ占쎈쐻占쎈윪�얄꽒�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윪占쎌읆 占쎌맶占쎈쐻 .");
            }
        }
    }
    
    @Override
    public void pickMeInsert2(Model model, PickMeDTO pdto, ProductDTO ppdto, String pm_m_id, String pm_c_id, int pm_num) {
       int pickCount = mapper.ppickAndpickMeCount2(pm_m_id, pm_c_id, pm_num);
       
       if (pickCount > 0) {
          int deleteResult = mapper.deletePickMeByCId2(pm_m_id, pm_c_id);
          if (deleteResult > 0) {
             model.addAttribute("message", " 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윪�젆�떞�뒑 占쎈� 占쎌뵰占쎄뎡 筌륅옙 亦낉옙 占쎄뎡占쎈쐻占쎈윞占쎈젇  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧ 占쎌맶 占쎌쑅鶯ㅼ렮堉뱄옙�맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧占쎈쐻占쎈윥占쎈뼓癲ル슢��泳�占� 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�궘占쏙옙�걠�뙴占� 占쎄뎡  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧�뮲釉띿뒧占쎈뎐占쎈쐻占쎈짗占쎈쭒 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 �뤃占� 占쎌맋 占쎄뎡占쎈쐻占쎈윞占쎈젇占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 筌뚭퍌�쐻  占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥�뤃�꼪�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈솗 占쎌졒 占쎈㎍占쎈쐻占쎈윥占쎌맽占쎈쐻占쎈윥�젆占� 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒧占쎄텤 甕곤옙 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맚囹덉쉻�꺏 占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈ぁ占쎈쐻占쎈윪�얄꽒�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윪占쎌읆 占쎌맶占쎈쐻 .");
          }
       } else {
          int insertResult = mapper.pickMeInsert2(pdto, pm_m_id, pm_c_id, pm_num);
          if (insertResult > 0) {
             model.addAttribute("message", " 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧�뮲釉띿뒧占쎈뎐占쎈쐻占쎈짗占쎈쭒 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 �뤃占� 占쎌맋 占쎄뎡占쎈쐻占쎈윞占쎈젇占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 筌뚭퍌�쐻   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧ 占쎌맶 占쎌쑅鶯ㅼ렮堉뱄옙�맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈㎧占쎈쐻占쎈윥占쎈뼓癲ル슢��泳�占� 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒧占쎄텤 甕곤옙 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맚囹덉쉻�꺏 占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈ぁ占쎈쐻占쎈윪�얄꽒�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윪占쎌읆 占쎌맶占쎈쐻 .");
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
             System.out.println(" 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋占쎈린占쎈쑕占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�궘占쏙옙�걠�뙴占� �뤃�깷�쐻占쎈윪�뤃占� 占쎌맶 占쎌쑋占쎈린占쎌뵛占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈튉占쎈꽞�눧誘り뎡占쎈쐻占쎈윥�댚�엺�쐻占쎈윥壤쏅�ｋ쐻占쎈윥占쎌넇占쎈쐻占쎈윥 占쎌돸占쎌굲 筌띿떑�뼲占쎌뒧罹됵옙�쇀域밟뫁�굲占쎈쨨   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�鍮놅옙�뮋 占쎈폇�뤆占�  椰꾬옙 占쎌맶 占쎌쑋占쎈린占쎈염占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몝 筌뚭퍐�맀占쎈�占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎄콞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈� 占쎌몧 占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け 占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 占쎌맶 占쎌쑅 筌띾쑚�쐻占쎈윥占쎈㎍ 占쎌맶占쎈쐻  -  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�占싸살맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐 占쎌럸占쎈쐻占쎈짗占쎌굲雅��굝�뿪占쎌굲 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�鍮�占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몝 占쎌쟼 占쎈펵 占쎌졋占쎈쐻占쎈윥壤쏉옙 占쎌몡 甕곤옙 占쎈㎍占쎈쐻占쎈윥占쎈ぇ占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첐 占쎌맶 占쎌쑅勇싷옙  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윥鸚뤄옙 占쎌맶占쎈쐻 : " + page + ",  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 獄�琯�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈듃占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젘 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝 筌뚭퍐�맀占쎈샍占쎌맚 占쎌맶 �뙴占� 占쎄뎡 筌뚭퍌�쐻占쎈쑕占쎌맶 占쎌쑋占쎈쨨   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈빋占쎈뼔夷뚳옙�쇊占쎈늉筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒩�뜮戮с궘�뇡�빘�굲 筌띿떑�뼲占쎌뒧占쎈룜 筌랃옙 占쎌�占쎈├�뙴紐꾨㎍占쎈쐻占쎈윥占쎌몝 筌뚳옙 �솒占쏙옙援뀐옙�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈뼓 占쎌맶占쎈쐻 : " + result.size());
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
            System.out.println(" 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋占쎈린占쎈쑕占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊�궘占쏙옙�걠�뙴占� �뤃�깷�쐻占쎈윪�뤃占� 占쎌맶 占쎌쑋占쎈린占쎌뵛占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윪筌륁�λ쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윞�굜�쉩�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈튉占쎈꽞�눧誘り뎡占쎈쐻占쎈윥�댚�엺�쐻占쎈윥壤쏅�ｋ쐻占쎈윥占쎌넇占쎈쐻占쎈윥 占쎌돸占쎌굲 筌띿떑�뼲占쎌뒧罹됵옙�쇀域밟뫁�굲占쎈쨨   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�鍮놅옙�뮋 占쎈폇�뤆占�  椰꾬옙 占쎌맶 占쎌쑋占쎈린占쎈염占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몝 筌뚭퍐�맀占쎈�占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎄콞占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗 占쎈� 占쎌몧 占쎈㎍占쎈쐻占쎈윥占쎌몝癲ル슢�뵯占쎈け 占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈윫 占쎌맶 占쎌쑅 筌띾쑚�쐻占쎈윥占쎈㎍ 占쎌맶占쎈쐻  -  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�占싸살맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐 占쎌럸占쎈쐻占쎈짗占쎌굲雅��굝�뿪占쎌굲 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 占쎌쑌 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑋礖�癒�鍮�占쎌굲 占쎈㎍占쎈쐻占쎈윥占쎌몝 占쎌쟼 占쎈펵 占쎌졋占쎈쐻占쎈윥壤쏉옙 占쎌몡 甕곤옙 占쎈㎍占쎈쐻占쎈윥占쎈ぇ占쎈쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첐 占쎌맶 占쎌쑅勇싷옙  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윥鸚뤄옙 占쎌맶占쎈쐻 : " + page + ",  占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊 占쎌맶 占쎌쑅 獄�琯�쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎈듃占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몗占쎈쐻占쎈윥占쎌젘 占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첐占쎈쐻占쎈윥占쎈㎍占쎈쐻占쎈윥筌욎�λ쐻占쎈윪�뤃轅⑤쐻占쎈윥占쎈㎍占쎈쐻占쎈윥占쎌몝 筌뚭퍐�맀占쎈샍占쎌맚 占쎌맶 �뙴占� 占쎄뎡 筌뚭퍌�쐻占쎈쑕占쎌맶 占쎌쑋占쎈쨨   占쎌맶 占쎌쑅 筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈빋占쎈뼔夷뚳옙�쇊占쎈늉筌랃옙 占쎌맶 占쎌쑅 占쎈첊�뮲釉띿뒩�뜮戮с궘�뇡�빘�굲 筌띿떑�뼲占쎌뒧占쎈룜 筌랃옙 占쎌�占쎈├�뙴紐꾨㎍占쎈쐻占쎈윥占쎌몝 筌뚳옙 �솒占쏙옙援뀐옙�쐻占쎈윪筌랃옙 占쎌맶 占쎌쑅 占쎈첊占쎈쐻占쎈윥占쎈뼓 占쎌맶占쎈쐻 : " + result.size());
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
         public  ShoppingCartDTO getSelectedProducts(int shop_num, @Param("add_m_id") String add_m_id) {
             return mapper.getSelectedProducts(shop_num, add_m_id);
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
            
            
         
            
            
             
             
             
}