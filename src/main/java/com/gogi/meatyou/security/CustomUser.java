package com.gogi.meatyou.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gogi.meatyou.bean.MemberDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User{
	
	private MemberDTO dto;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}
	public CustomUser(MemberDTO dto) {
		//super(dto.getMId(),dto.getPasswd(),dto.getMstatAuthList().stream().map(auth-> new SimpleGrantedAuthority(auth.getMstatAuth())).collect(Collectors.toList()));
		
		super(dto.getM_id(),dto.getPasswd(),Collections.singletonList(new SimpleGrantedAuthority(dto.getMstat_auth())));
		this.dto = dto;
	}

//	public CustomUser(MemberDTO dto) {
//	    super(dto.getMId(), dto.getPasswd(), authoritiesFromMstatAuthList(dto.getMstatAuthList()));
//	    this.dto = dto;
//	}

	
	
	
	
	
	
	

	
	
	
	//public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
	//	super(username, password, authorities);
	//}
	
	//public CustomUser(MemberDTO dto) {
	//	super(dto.getUserid(),dto.getUserpw(),dto.getAuthList().stream().map(auth-> new SimpleGrantedAuthority(auth.getAuth())).collect(Collectors.toList()));
	//	this.dto = dto;
	//}

}
