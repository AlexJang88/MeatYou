package com.gogi.meatyou.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.extern.log4j.Log4j;


public class CustomNoopPasswordEncoder implements PasswordEncoder {
	//DB�� �н����� ���ڵ� ó���ؼ� ����
	public String encode(CharSequence rawPassword) {


		return rawPassword.toString();
		
	}
	//�Է��� �н������ ���ڵ��� �н����尡 ������ Ȯ��
	public boolean matches(CharSequence rawPassword, String encodedPassword) {


		return rawPassword.toString().equals(encodedPassword);
	}
}
