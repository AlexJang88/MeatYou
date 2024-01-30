package com.gogi.meatyou.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

   
   public void totalsumMoney(Model model, String id); // 메인에 누적 매출액
   public String ChartData(Model model, String id, String selectedYear); // 메인에 누적 매출액
   
   public int itemcount(String id); // 아이디에 맞는 상품 등록 갯수 불러오기
   
   public void list(Model model, String id, int pageNum); // 아이디에 맞는 상품갯수 및 정보들 모델에 담아서 불러오기
   public void listout(Model model, String id, int pageNum);  //판매종료된 상품 모델에 담아서 불러오기
   public void statusChange(Model model, ProductDTO productdto); //회원의 판매상태를 변경
   public void statusChangeouut(ProductDTO productdto); //회원의 판매상태를 변경
   public String uploadProductImageFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request);//에디터 만들기
   public int getco_num();
   public void geterco_num(Model model);
   
   //아래로는 상품 수정
   public void lister(Model model, int p_num); // 번호에 맞는 상품 정보 가져오기   
   
   
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
    
    public void powerlink(Model model,int p_num,int clickpay); // 의석
    
    
    //매출액
    public void getprofit(Model model,int check, String id); //체크를 안했을때
    public void getCheckprofit(Model model,int check,String start,String end, String id); //체크를했을때
    //월별 판매상품
    public void  getProfitItem(Model model,int check, String id, int pageNum); //체크를 안했을때
    //날짜 계산
    Date calculateTargetDate(Date currentDate, int check);
    
    //상품구매회원 및 쿠폰 주기
    public void consumerList(Model model, int check, int pageNum, String id); //상품 구매리스트회원
    public void cusCouponPro(Model model, String p_m_id, String  id, int point, int companynum, CouponDTO coupondto, int p_status, int couponUse);  //쿠폰을 제공했을때 넘어가는값
    public void companynum(Model model, String id);//사업자번호받기
    public void itemList(Model model, String id);//상품리스트 받기
    public void couponList(Model model, String id, int pageNum);//쿠폰 제공 리스트
    
    //주문 및 취소현황
    public void deliverout(Model model, int check, int pageNum, String id); //취소현황
    public void delivering(Model model, int check, int pageNum, String id); //배송현황
    //배송 현황 변경
    public void deliverStatus(int order_num, int order_status); //배송현황변경
    
    
    
    //사진실험
    public void fileUpload(Model model, MultipartFile thumbs, String filePath, ProductDTO productdto);

   public void insert_cusorder(CusOrderDTO cusorderdto);
   public void insert_cusorderTwo(CusOrderDTO cusorderdto);
    
   //아래 에디터
   public void productReg(ProductDTO productdto, String realPath,MultipartFile file);
   public void productUpdate(String realPath,int num,Model model);
   public void productUpdateReg(String realPath, ProductDTO productdto,MultipartFile thumbs);
   public String productImgUpload(MultipartFile multipartFile, String realPath);
   public void productImgDel(String fileName, String realPath);
   public void productContent(Model model,int num);
   public void productDelete(int num);
    
    
    
    
}