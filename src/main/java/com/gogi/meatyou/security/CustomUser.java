package com.gogi.meatyou.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User {

    public CustomUser(String mId, String passswd, Collection<? extends GrantedAuthority> authorities) {
        super(mId, passswd, authorities);
    }

    private MemberDTO dto;

    public CustomUser(MemberDTO dto ) {
    	
        super(dto.getM_id(), dto.getPasswd(),
                dto.getMstatus_list().stream().map(auth -> new SimpleGrantedAuthority(String.valueOf(auth.getMsta_m_status())))
                        .collect(Collectors.toList()));
        this.dto = dto;
        // 디버그 로그
        
        System.out.println("CustomUser 생성 완료!!!!"+dto.getM_name()+"님께서입력하신 아이디 : " + dto.getM_id() + " 비밀번호 :"+dto.getPasswd()+"등급은:"+dto.getMstat_auth()+"이며 "+dto.getMstat_detail()+"님 입니다");
        
    }
}
