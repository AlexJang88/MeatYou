package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.repository.MemberMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
		    	return	mapper.insertIntoCusDetail(cdto);
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
		
			
			

			
			// 반환 타입을 List<ShoppingCartDTO>로 수정
			@Override
			public List<ShoppingCartDTO> ShoppingCartAndProduct(String shop_m_id,ShoppingCartDTO sdto,ProductDTO pdto) {
			    return mapper.ShoppingCartAndProduct(shop_m_id);
			}

			
			
	
		
			
			
			@Override
			public void updateQuantity(int  shop_num,int  quantity, String shop_m_id) {
				mapper.updateQuantity(shop_num, quantity ,shop_m_id);
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
			    System.out.println("서비스 호출 - 페이지: " + page + ", 결과 개수: " + result.size());
			    return result;
			}

			    @Override
			    public int getTotalShoppingCartItems(String shop_m_id) {
			        return mapper.getTotalShoppingCartItems(shop_m_id);
			    }
			    
			    
			    
			    
			 // 삭제 
			    @Override
			    public List<ShoppingCartDTO> deleteSelectedItems(List<String> selectedItems, String shop_m_id,	int shop_num) {
			        Map<String, Object> paramMap = new HashMap<>();
			        paramMap.put("selectedItems", selectedItems);
			        paramMap.put("shop_m_id", shop_m_id);
			        paramMap.put("shop_num", shop_num);
			        // Mapper를 통해 DB에서 선택된 상품 삭제
			        mapper.deleteSelectedItems(paramMap);
					return null;
			    }
			   /* 
			 // 삭제 
			    @Override
			    public List<ShoppingCartDTO> deleteSelectedItems(List<String> selectedItems, String shop_m_id, int shop_num) {
			        Map<String, Object> paramMap = new HashMap<>();
			        paramMap.put("selectedItems", selectedItems);
			        paramMap.put("shop_m_id", shop_m_id);
			        paramMap.put("shop_num", shop_num);
			        // Mapper를 통해 DB에서 선택된 상품 삭제
			        mapper.deleteSelectedItems(paramMap);
			        return null;
			    }
*/
			    
			    
}

