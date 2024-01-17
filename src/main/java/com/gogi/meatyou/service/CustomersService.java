package com.gogi.meatyou.service;

import java.util.Date;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto); //상품등록
	
	public int itemcount(String id); // 아이디에 맞는 상품 등록 갯수 불러오기
	
	public void list(Model model, String id, int pageNum); // 아이디에 맞는 상품갯수 및 정보들 모델에 담아서 불러오기
	public void listout(Model model, String id, int pageNum);  //판매종료된 상품 모델에 담아서 불러오기
	public void statusChange(ProductDTO productdto); //회원의 판매상태를 변경
	public void statusChangeouut(ProductDTO productdto); //회원의 판매상태를 변경
	
	//아래로는 상품 수정
	public void lister(Model model, int p_num); // 번호에 맞는 상품 정보 가져오기	
	public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto); //상품정보수정
	
	//재고 현황
	public void stocklist(Model model, String id, int pageNum); //아이디와 모델 넘기기/ 전체 재고 목록 
	public void onStock(Model model, String id, int pageNum); //아이디와 모델 넘기기 // 판매중인 제고 목록
	public void stockPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경 (전체)
	public void stockOnPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경(판매중)

	
	//유료결제란
	public void pay(Model model, String id); //상품 유료결제 목록 불러오기
	public void payOne(Model model, String id, int pageNum); //상품 파워링크 구매목록 상세보기
	public void payTwo(Model model, String id, int pageNum); //상품 파워링크 구매목록 상세보기
	public void powerlist(Model model, String id); //상품 유료결제 목록 불러오기
	public void payment(Model model, ProductDTO productdto); //파워링크 결제 페이지에서 결제할목록 보여주기
	public void payFinish(CusOrderDTO cusorderDTO); //파워링크 결제 페이지에서 결제할목록 보여주기
    public void itempayFinish(CusOrderDTO cusorderDTO);//품목 확장 결제 완료
    
    //매출액
    public void getprofit(Model model,int check, String id); //체크를 안했을때
    public void getCheckprofit(Model model,int check,String start,String end, String id); //체크를했을때
    //월별 판매상품
    public void  getProfitItem(Model model,int check, String id, int pageNum); //체크를 안했을때
    //날짜 계산
    Date calculateTargetDate(Date currentDate, int check);
   
   
}