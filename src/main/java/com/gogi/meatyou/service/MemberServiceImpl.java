package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.repository.MemberMapper;

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

			
			
	
				//수량변경 
			 @Override
			    public int increaseQuantity(ShoppingCartDTO sdto) {
			        return mapper.upquantity(sdto);
			    }

			    @Override
			    public int decreaseQuantity(ShoppingCartDTO sdto) {
			        return mapper.downquantity(sdto);
			    }

    	
			
			
			@Override
			public int modifyQuantity(ShoppingCartDTO sdto) {
			    int result;
			    if (sdto.getQuantity() > 0) {
			        result = mapper.upquantity(sdto);
			    } else {
			        result = mapper.downquantity(sdto);
			    }
			    return result;
			}
			
			
			@Override
			public void updateQuantity(int  shop_num,int  quantity, String shop_m_id) {
				mapper.updateQuantity(shop_num, quantity ,shop_m_id);
		    }
			
    }

