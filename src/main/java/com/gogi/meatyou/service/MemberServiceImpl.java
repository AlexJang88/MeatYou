package com.gogi.meatyou.service;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ShoppingCartDTO;
import com.gogi.meatyou.repository.MemberMapper;

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
		
			
			


			@Override

			public void ShoppingCartAndProduct( String shop_m_id,ShoppingCartDTO sdto) {
			    mapper.ShoppingCartAndProduct(shop_m_id);
				
			}







    	
    }

