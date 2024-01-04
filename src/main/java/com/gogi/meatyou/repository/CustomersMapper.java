package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;


public interface CustomersMapper {

	public void productUp(ProductDTO productdto); //상품등록
	public void P_DETAILUp(PDetailDTO pdetaildto);  // 상품상세등록
	
	public int itemcount(String id); // 상품목록페이지의  총 등록 갯수
	public int paycount(String id); // 품목 유료결제한 갯수	
	public int itemcounting(String id); // 판매중인 상품 페이지에서 판매중인 갯수
	public int itemcountout(String id); // 판매중인 상품 페이지에서 판매중인 갯수
	
	public List<ProductDTO> list(String id); // 상품목록 전체 페이지
	public List<ProductDTO> listout(String id); // 판매중인 상품들이 보이는 페이지 
	public List<CusOrderDTO> cus_order (String id); // 유료결제 코드 가져오기
	
	public int member_status(String id); // 상품목록에서 노출비노출 표기시 등급에따라 나오게 ( 2001 2002 2003 2004 이나온다)
	public void statusChange(ProductDTO productdto); //회원의 판매상태를 변경
	public void cus_num(ProductDTO productdto); //회원의 유료결제 현황의 상품을 변경
	
	//아래로는 상품 수정
	public ProductDTO lister(int p_num); // 번호에 맞는 상품 정보 가져오기
	public PDetailDTO listerPD(int p_num); // 번호에 맞는 상품상세 정보 가져오기
	public void itemUP(ProductDTO productdto); //상품 정보수정
	public void itemDpUP(PDetailDTO pdetaildto); //상품상세 정보수정
	
	//재고현황	
	public List<ProductDTO> stocklist(String id);  // 아이디에 맞는 상품 제고 및 목록 가져오기
	public List<ProductDTO> stockonlist(String id);  // id에 맞는상품 제고 중 판매중인 리스트 가져오기
	public void stockPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경
	
	//유료결제
	public int listPayCount (String id); // 품목확장 유료결제 갯수 
	public int listpaynowcount (String id); // 품목확장 유료결제 갯수 
	public int powerPayCount (String id); // 파워링크 유료결제 횟수	
	public List<CusOrderDTO> powerlist (String id); // 상위노출 목록 리스트로 정보 가져오기
	public List<CusOrderDTO> paylist (String id); // 상위노출 목록 리스트로 정보 가져오기
	
   
   
   
   
}