package com.gogi.meatyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.meatyou.repository.MainMapper;

@Service
public class MainServiceImpl {
	
	@Autowired
	private MainMapper mapper;
	
}
