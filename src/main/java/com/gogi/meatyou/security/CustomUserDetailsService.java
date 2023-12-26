package com.gogi.meatyou.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.repository.MemberMapper;




public class CustomUserDetailsService implements UserDetailsService{
	//1226���� ����
		
		@Autowired
		private MemberMapper mapper;
	
		//1226���ؿ�����
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MemberDTO dto = mapper.read(username);

		return dto == null ? null : new CustomUser(dto);
	}

	
	
}
