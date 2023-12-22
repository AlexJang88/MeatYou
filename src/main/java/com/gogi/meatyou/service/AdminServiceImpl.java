package com.gogi.meatyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.meatyou.repository.AdminMapper;

@Service
public class AdminServiceImpl {

	
	@Autowired
	private AdminMapper mapper;
	
}
