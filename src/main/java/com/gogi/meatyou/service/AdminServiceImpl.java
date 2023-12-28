package com.gogi.meatyou.service;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.MemberDTO;
import com.gogi.meatyou.repository.AdminMapper;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	private HashMap adminMap;

//	@Override
//	public void list(int pageNum, Model model) {
//		
//
//	}
	
	@Autowired
	private AdminMapper mapper;
	
	public void memberList(int check,Model model,int pageNum) {
		if(check==1) {
			int pageSize = 10;
			int startRow = (pageNum - 1) * pageSize + 1;
			int endRow = pageNum * pageSize;
			int count = mapper.memCount();
			List<MemberDTO> list = Collections.EMPTY_LIST;

			if (count > 0) {
				adminMap.put("start", startRow);
				adminMap.put("end", endRow);
				list = mapper.memberList(adminMap);
			}

			model.addAttribute("list", list);
			model.addAttribute("count", count);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageSize", pageSize);

			// page
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage = (int) (pageNum / 10) * 10 + 1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("startPage", startPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("endPage", endPage);
			model.addAttribute("check", check);
		}else if(check==2) {
			int pageSize = 10;
			int startRow = (pageNum - 1) * pageSize + 1;
			int endRow = pageNum * pageSize;
			int count = mapper.cusCount();
			List<MemberDTO> list = Collections.EMPTY_LIST;

			if (count > 0) {
				adminMap.put("start", startRow);
				adminMap.put("end", endRow);
				list = mapper.customList(adminMap);
			}

			model.addAttribute("list", list);
			System.out.println(list);
			model.addAttribute("count", count);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageSize", pageSize);

			// page
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage = (int) (pageNum / 10) * 10 + 1;
			int pageBlock = 10;
			int endPage = startPage + pageBlock - 1;
			if (endPage > pageCount) {
				endPage = pageCount;
			}
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("startPage", startPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("endPage", endPage);
			model.addAttribute("check", check);
		}
	}

	@Override
	public List<MemberDTO> customList(HashMap hashmap) {
		return mapper.customList(hashmap);
	}

	@Override
	public List<MemberDTO> memberList(HashMap hashmap) {
		return mapper.memberList(hashmap);
	}

	@Override
	public int cusCount() {
		// TODO Auto-generated method stub
		return mapper.cusCount();
	}

	@Override
	public int memCount() {
		// TODO Auto-generated method stub
		return mapper.memCount();
	}

	@Override
	public List<String> goodMember() {
		// TODO Auto-generated method stub
		return mapper.goodMember();
	}

	@Override
	public List<String> bestMember() {
		return mapper.bestMember();
	}

	@Override
	public void goodMemberUpdate(List<String> id) {
		if(goodMember().size()>0) {
		mapper.goodMemberUpdate(mapper.goodMember());
		}
	}

	@Override
	public void bestMemberUpdate(List<String> id) {
		if(bestMember().size()>0) {
		mapper.bestMemberUpdate(mapper.bestMember());
		}
	}

	@Override
	public MemberDTO test(String m_id) {
		return mapper.test(m_id);
	}
	
	
}
