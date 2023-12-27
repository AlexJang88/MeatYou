package com.gogi.meatyou.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.gogi.meatyou.bean.MemberDTO;

import lombok.Getter;

@Getter
public class CustomUser extends User {

    public CustomUser(String mId, String passswd, Collection<? extends GrantedAuthority> authorities) {
        super(mId, passswd, authorities);
    }

    private MemberDTO dto;

    public CustomUser(MemberDTO dto) {
        super(dto.getM_Id(), dto.getPasswd(),
                dto.getMStatusList().stream().map(auth -> new SimpleGrantedAuthority(String.valueOf(auth.getMStatus())))
                        .collect(Collectors.toList()));
        this.dto = dto;
        // ����� �α�
        System.out.println("CustomUser ���� �Ϸ� �Է��Ͻ� ���̵� : " + dto.getM_Id() + " ��й�ȣ :"+dto.getPasswd());
    }
}
