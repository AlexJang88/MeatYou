package com.gogi.meatyou.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}

	//@Autowired
	//private SecurityMapper mapper;
	
	//@Override
	//public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//		MemberDTO dto= mapper.read(username);
			
	//	return dto==null ? null:new CustomUser(dto);
	//}
	
}
