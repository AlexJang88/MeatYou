package com.gogi.meatyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.repository.MemberMapper;


@Service
public class MemberServiceImpl  implements MemberService{
	
	
	@Autowired
	private MemberMapper mapper;
	

	@Override
	public int insertMember(MemberDTO dto,String m_id,String passwd) {
	    return mapper.insertMember(dto);
	}

	}

