package com.gogi.meatyou.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
                dto.getMstatus_list().stream().map(auth -> new SimpleGrantedAuthority(String.valueOf(auth.getMstat_auth())))
                        .collect(Collectors.toList()));
        this.dto = dto;
        // ����� �α�
        
        System.out.println("CustomUser ���� �Ϸ�!!!!"+dto.getM_name()+"�Բ����Է��Ͻ� ���̵� : " + dto.getM_id() + " ��й�ȣ :"+dto.getPasswd()+"�����:"+dto.getMstatus_list().get(0).getMstat_auth()+"�̸� "+dto.getMstat_detail()+"�� �Դϴ�");
        
    }
}