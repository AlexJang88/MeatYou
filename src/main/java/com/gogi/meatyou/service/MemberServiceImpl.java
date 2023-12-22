package com.gogi.meatyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.meatyou.repository.MemberMapper;

@Service
public class MemberServiceImpl {
	
	
	@Autowired
	private MemberMapper mapper;
}
