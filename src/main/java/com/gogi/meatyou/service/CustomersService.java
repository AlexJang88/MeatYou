package com.gogi.meatyou.service;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.KakaoApproveResponse;
import com.gogi.meatyou.bean.KakaoReadyResponse;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

   public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto); //상품등록
   public int itemcount(String id); // 아이디에 맞는 상품 등록 갯수 불러오기
   public void list(Model model, String id); // 아이디에 맞는 상품갯수 및 정보들 모델에 담아서 불러오기
   public void listing(Model model, String id); //판매중인 상품 모델에 담아서 불러오기
   public void listout(Model model, String id); //판매종료된 상품 모델에 담아서 불러오기
   public int statusChange(ProductDTO productdto); //회원의 판매상태를 변경
   
   //아래로는 상품 수정
   public void lister(Model model, int p_num); // 번호에 맞는 상품 정보 가져오기   
   public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto); //상품정보수정
   
   //재고 현황
   public void stocklist(Model model, String id); //아이디와 모델 넘기기/ 전체 재고 목록 
   public void onStock(Model model, String id); //아이디와 모델 넘기기 // 판매중인 제고 목록
   public void stockPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경 (전체)
   public void stockOnPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경(판매중)
   
   //유료결제란
   public void pay(Model model, String id); //상품 유료결제 목록 불러오기
   
   
}