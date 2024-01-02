package com.gogi.meatyou.service;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto); //상품등록
	public int itemcount(String id); // 아이디에 맞는 상품 등록 갯수 불러오기
	public void list(Model model, String id); // 아이디에 맞는 상품갯수 및 정보들 모델에 담아서 불러오기
	public void listing(Model model, String id); //판매중인 상품 모델에 담아서 불러오기
	public void listout(Model model, String id); //판매종료된 상품 모델에 담아서 불러오기
	public int statusChange(ProductDTO productdto); //회원의 판매상태를 변경
}
