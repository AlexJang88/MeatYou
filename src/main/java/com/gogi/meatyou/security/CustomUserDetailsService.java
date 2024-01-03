package com.gogi.meatyou.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.repository.MemberMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
		private MemberMapper mapper;

		MemStatusDTO mdto=new MemStatusDTO();
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			MemberDTO dto = mapper.read(username);

			return dto == null ? null : new CustomUser(dto );
		}
	
	
	
	
	//@Autowired
	//private AdminMapper mapper;
	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//	MemberDTO dto = mapper.read(username);
	//	
	//	return dto==null ? null:new CustomUser(dto);
	//}

	//@Autowired
	//private SecurityMapper mapper;
	
	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//		MemberDTO dto= mapper.read(username);
			
	//	return dto==null ? null:new CustomUser(dto);
	//}
	
}
