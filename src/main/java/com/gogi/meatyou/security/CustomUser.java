package com.gogi.meatyou.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gogi.meatyou.bean.MemberDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User{

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	
	//20231226 ÀÌµµÁØ
	private MemberDTO dto;

	public CustomUser(MemberDTO dto) {
		super(dto.getMId(),dto.getPasswd(),
				dto.getM_status().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getM_status()))
									.collect(Collectors.toList()));
		this.dto = dto;
	}
}