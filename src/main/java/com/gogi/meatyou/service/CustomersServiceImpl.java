package com.gogi.meatyou.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.repository.CustomersMapper;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersMapper mapper;

	@Override
	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto) { //상품등록		
		mapper.productUp(productdto);
		mapper.P_DETAILUp(pdetaildto);
	}
	
	@Override  //아이디에 맞는 상품 갯수 불러오기
	public int itemcount(String id) {		
		return mapper.itemcount(id);
	}
	
	@Override // 아이디에 맞는 상품갯수 불러와서 모델에 담아서 값 가져오기 (상품전체목록)
	public void list(Model model, String id) {  //상품갯수받아오기 , 정보들
		int count = mapper.itemcount(id);   //id에 맞는 상품갯수 받아오기
		int paycount = mapper.paycount(id); //id에 맞는 유료결제 품목갯수
		int M_status =mapper.member_status(id);		
		List<ProductDTO> list = mapper.list(id); // id에 맞는 상품목록 리스트 가져오기
		
		model.addAttribute("M_status", M_status); // id에 맞는 등급가져오기
		model.addAttribute("count", count);  // count에 상품갯수 담기
		model.addAttribute("paycount", paycount);  // paycount에 품목 유료결재한 갯수 담기
		model.addAttribute("list", list);
		
	}
	
	@Override
	public void listing(Model model, String id) { //판매중인 상품
		int counting = mapper.itemcounting(id); // 판매중인 상품갯수 담기
		int paycount = mapper.paycount(id); //id에 맞는 유료결제 품목갯수
		int M_status =mapper.member_status(id);		 //아이디를 넣고 등급가져오기
		List<ProductDTO> listing = mapper.listing(id); // id에 맞는 상품목록 리스트 가져오기
		
		model.addAttribute("M_status", M_status); // id에 맞는 등급가져오기
		model.addAttribute("counting", counting);  // counting에 판매중인 상품갯수 담기
		model.addAttribute("paycount", paycount);  // paycount에 품목 유료결재한 갯수 담기
		model.addAttribute("listing", listing); // id에 맞는 상품목록 리스트 가져오기
	}
	
	@Override
	public void listout(Model model, String id) { //판매종료된 페이지
		int countout= mapper.itemcountout(id); // 판매중인 상품갯수 담기
		int M_status =mapper.member_status(id);		 //아이디를 넣고 등급가져오기
		List<ProductDTO> listout = mapper.listout(id); // id에 맞는 상품목록 리스트 가져오기
		
		model.addAttribute("M_status", M_status); // id에 맞는 등급가져오기
		model.addAttribute("countout", countout);  // counting에 판매중인 상품갯수 담기
		model.addAttribute("listout", listout); // id에 맞는 상품목록 리스트 가져오기
	}

	@Override
	public int statusChange(ProductDTO productdto) { //판매자의 판매상태변경			
		return mapper.statusChange(productdto);
	}

	
	//아래로는 상품 수정 관련
	@Override
	public void lister(Model model, int p_num) {
		ProductDTO lister = mapper.lister(p_num); // 번호에 맞는 상품정보 가져오기
		PDetailDTO listerPD = mapper.listerPD(p_num); //번호에 맞는 상세정보 가져오기
		model.addAttribute("lister", lister);		
		model.addAttribute("listerPD", listerPD);		
	}
	

	@Override
	public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto) {
		mapper.itemUP(productdto);  //정보수정
		mapper.itemDpUP(pdetaildto);  // 정보수정 상세
		
	}

	

	

	
	
	
	
}
