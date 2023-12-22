package com.gogi.meatyou.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gogi.meatyou.repository.CustomersMapper;

@Service
public class CustomersServiceImpl {

	@Autowired
	private CustomersMapper mapper;
}
