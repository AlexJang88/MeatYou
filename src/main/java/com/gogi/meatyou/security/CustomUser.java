package com.gogi.meatyou.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gogi.meatyou.bean.MemStatusDTO;
import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.bean.UsersDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User {
	//카카오 로그인때문에 
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(CustomUser.class);
	private UsersDTO udto;
	//카카오 로그인때문에 
	
	
	
    public CustomUser(String mId, String passswd, Collection<? extends GrantedAuthority> authorities) {
        super(mId, passswd, authorities);
    }

    private MemberDTO dto;

    public CustomUser(MemberDTO dto ) {
    	
        super(dto.getM_id(), dto.getPasswd(),
                dto.getMstatus_list().stream().map(auth -> new SimpleGrantedAuthority(String.valueOf(auth.getMstat_auth())))
                        .collect(Collectors.toList()));
        this.dto = dto;
        // 占쏙옙占쏙옙占� 占싸깍옙
        
        System.out.println("CustomUser 占쏙옙占쏙옙 占싹뤄옙!!!!"+dto.getM_name()+"占쌉뀐옙占쏙옙占쌉뤄옙占싹쏙옙 占쏙옙占싱듸옙 : " + dto.getM_id() + " 占쏙옙橘占싫� :"+dto.getPasswd()+"占쏙옙占쏙옙占�:"+dto.getMstatus_list().get(0).getMstat_auth()+"占싱몌옙 "+dto.getMstat_detail()+"占쏙옙 占쌉니댐옙");
        
        
        
        
        
    }
 
    
	public CustomUser(UsersDTO udto) {
		super(udto.getUsername(), udto.getPassword(), udto.getGrade().stream().map(grade -> 
		new SimpleGrantedAuthority(grade.getGrade())).collect(Collectors.toList()));
		logger.info("=========CustomUser=========");
		this.udto = udto;
		logger.info("========="+dto+"=========");
	}
}