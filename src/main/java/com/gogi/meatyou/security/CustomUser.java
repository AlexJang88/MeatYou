package com.gogi.meatyou.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;

@Getter
public class CustomUser extends User{

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	//private MemberDTO dto;
	
	//public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	//	super(username, password, authorities);
	//}
	
	//public CustomUser(MemberDTO dto) {
	//	super(dto.getUserid(),dto.getUserpw(),dto.getAuthList().stream().map(auth-> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
	//	this.dto = dto;
	//}

}
