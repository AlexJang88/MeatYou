package com.gogi.meatyou.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.gogi.meatyou.bean.CusDetailDTO;
import com.gogi.meatyou.bean.MemberDTO;

public interface MemberMapper {
	public MemberDTO read(String m_id);
	
		//회원가입
	
	 public int insertMember(MemberDTO dto);
	 

	    // Spring Security에서 사용자 정보를 가져오기 위한 메서드
	    public MemberDTO loadUserByUsername(String username);
		
	 	public List<MemberDTO> memberList();
	 
		public MemberDTO member(String m_id);
		
		
		
		public void memberUpdate(MemberDTO dto);
		
		
		public int statusAdminChange(MemberDTO dto);
		
		public int statusChange(MemberDTO dto);
		
		
		
		  // 회원 상태 업데이트
	    public int updateMemberStatus(MemberDTO dto);

	    // Cus_detail에 데이터 인서트
	    public int insertIntoCusDetail(CusDetailDTO cdto);
}
