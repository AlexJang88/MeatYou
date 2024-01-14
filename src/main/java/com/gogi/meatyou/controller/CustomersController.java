package com.gogi.meatyou.controller;

import java.security.Provider.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.KakaoApproveResponse;
import com.gogi.meatyou.bean.KakaoReadyResponse;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;

@Controller
@RequestMapping("/customers/*")
public class CustomersController {
   
	
   @Autowired
   private CustomersService service;
   
   @RequestMapping("customer") //홈
   public String home(HttpSession session) {      
      session.setAttribute("memId", "user5");
      return "customer/customer";
   }
   
   @RequestMapping("itemUpdate") //상품등록
   public String update() {
      return "customer/itemUpdate";
   }

   @RequestMapping("itemUpdatePro") //상품등록 확인
   public String itemUpdatePro( ProductDTO productdto, PDetailDTO pdetaildto) {      
      service.itemUpdate(productdto,pdetaildto);      
      return "redirect:/customers/customer";
   }
   
   @RequestMapping("itemListing") //판매중인 상품목록
   public String itemListing(Model model, HttpSession session ) {      
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환
      service.listing(model, id);  // 아이디값 넘기기      
      return "customer/itemListing";
   }
   
   @RequestMapping("/statusChangeing") //판매중인 상품목록 페이지에서 변경하면 돌아오는곳설정
   public String statusChangeing(String p_m_id, int p_status, int p_num) {   
      ProductDTO productdto = new ProductDTO();  //아래 3개를 묶음
      productdto.setP_m_id(p_m_id);   // 아이디값
      productdto.setP_status(p_status); // 변경된 상품 상태값
      productdto.setP_num(p_num); //상품번호값      
      service.statusChange(productdto); // 회원의 판매상태를 변경
      return "redirect:/customers/itemListing";
   }
      
   @RequestMapping("itemList") //등록한 상품목록
   public String itemList(Model model, HttpSession session ) {      
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환
      service.list(model, id);  // 아이디값 넘기기      
      return "customer/itemList";
   }
   
   @RequestMapping("/statusChange") //상품목록페이지에서 변경하면 돌아오는곳설정
   public String statusChange(String p_m_id, int p_status, int p_num) {   
      ProductDTO productdto = new ProductDTO();  //아래 3개를 묶음
      productdto.setP_m_id(p_m_id);   // 아이디값
      productdto.setP_status(p_status); // 변경된 상품 상태값
      productdto.setP_num(p_num); //상품번호값   
      service.statusChange(productdto); // 회원의 판매상태를 변경
      return "redirect:/customers/itemList";
   }
   
   @RequestMapping("itemListOut") //판매종료된 상품목록
   public String itemListOut(Model model, HttpSession session ) {      
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환
      service.listout(model, id);  // 아이디값 넘기기         
      return "customer/itemListOut";
   }
   
   @RequestMapping("/statusChangeout") //판매종료된 상품목록 페이지에서 변경하면 돌아오는곳설정
   public String statusChangeout(String p_m_id, int p_status, int p_num) {   
      ProductDTO productdto = new ProductDTO();  //아래 3개를 묶음
      productdto.setP_m_id(p_m_id);   // 아이디값
      productdto.setP_status(p_status); // 변경된 상품 상태값
      productdto.setP_num(p_num); //상품번호값      
      service.statusChange(productdto); // 회원의 판매상태를 변경
      return "redirect:/customers/itemListOut";
   }
   
   @RequestMapping("content") //상품 정보보기
   public String content(Model model, int p_num, HttpSession session ) {
      model.addAttribute("p_num",p_num);      
      return "customer/content";
   }
   
   
   //여기는 정보수정   
   @RequestMapping("itemRevise") //상품 정보수정 (값 확인하기
   public String itemRevise(Model model, int p_num ) {
      model.addAttribute("p_num",p_num);
      service.lister(model, p_num);         
      return "customer/itemRevise";
   }
      
   @RequestMapping("itemRevisePro") //상품 정보수정 프로페이지
   public String itemRevisePro( ProductDTO productdto, PDetailDTO pdetaildto) {         
      //productdto.setStartdate(productdto.getStartdate().substring(0,10)); 날짜 잘라보기 위해 실험
      //productdto.setEnddate(productdto.getEnddate().substring(0,10));
      //System.out.println("================"+productdto.getEnddate().substring(0,10)+"========"); 날짜 잘라보기 위해 실험
      service.updateitemPro(productdto,pdetaildto);         
      return "redirect:/customers/itemList";
   }
   
   
   //여기는 재고현황파악
   
   @RequestMapping("stock") //전체 상품 재고현황
   public String stock(Model model, HttpSession session) {
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환         
      service.stocklist(model, id);  // 아이디값 넘기기      
      return "customer/stock";
   }
   
   @RequestMapping("onStock") //판매중인 상품 재고현황
   public String onStock(Model model, HttpSession session) {
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환
      service.onStock(model, id);      
      return "customer/onStock";
   }
   
   @RequestMapping("stockPro") //상품 전체목록 중 재고 변경
   public String stockPro(PDetailDTO pdetaildto) {      
      service.stockPro(pdetaildto);      
      return "redirect:/customers/stock";
   }
   
   @RequestMapping("stockOnPro") //판매중인 상품중 중 재고 변경
   public String stockOnPro(PDetailDTO pdetaildto) {      
      service.stockOnPro(pdetaildto);      
      return "redirect:/customers/onStock";
   }
   
   
   //유료결제란
   @RequestMapping("pay") //유료결제
   public String pay(Model model, HttpSession session) { //유료결제 항목 불러오기
      String id = (String)session.getAttribute("memId"); // 세션을 스트링으로 변환
      service.pay(model, id);
      return "customer/pay";
   }
   
   
   
   
   
   
   
   
   
   @RequestMapping("profit") //매출현황
   public String profit() {
      return "customer/profit";
   }
   
   
   @RequestMapping("consumerList") //구매회원
   public String consumerList() {
      return "customer/consumerList";
   }
   
   
   
   @RequestMapping("deliver") //배송현황
   public String deliver() {
      return "customer/deliver";
   }
   
   @RequestMapping("total") //정산안내
   public String total() {
      return "customer/total";
   }
   
   @RequestMapping("cusQna") //문의하기
   public String cusQna() {
      return "customer/cusQna";
   }
   
   @RequestMapping("cusCoupon") //쿠폰제공리스트
   public String cusCoupon() {
      return "customer/cusCoupon";
   }
   
}