package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusFileDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductMorderDTO;
import com.gogi.meatyou.bean.PurchaseMemberListDTO;


public interface CustomersMapper {

	public void productUp(ProductDTO productdto); //상품등록
	public int productCurrval();//등록된 상품번호 가져오기
	public void P_DETAILUp(ProductDTO pdetaildto);  // 상품상세등록
	public void mem_addressUp(ProductDTO productdto);//반품주소
	public int productNextval();//에디터 관련
	public int getco_num();
	
	
	public int itemcount(String id); // 상품목록페이지의  총 등록 갯수
	public int paycount(String id); // 품목 유료결제한 갯수	
	public int itemcounting(String id); // 판매중인 상품 페이지에서 판매중인 갯수
	public int itemcountout(String id); // 판매중인 상품 페이지에서 판매중인 갯수
	
	
	
	public List<ProductDTO> list(HashMap map); // 상품목록 전체 페이지
	public List<ProductDTO> listout(HashMap map); // 판매중인 상품들이 보이는 페이지 
	public List<CusOrderDTO> cus_order (String id); // 유료결제 코드 가져오기
	public int member_status(String id); // 상품목록에서 노출비노출 표기시 등급에따라 나오게 ( 2001 2002 2003 2004 이나온다)
	
	//회원 등급을 바꾸는 코드
	public int cuscheck(ProductDTO productdto);//우선 기존에 값이 있는지 찾는다 있으면 1번  없으면 0	
	public void gijon(ProductDTO productdto); // 기존의 값이 있어서 1이 나오면 기존의 값을 status 2(판매대기)로 바꾼다
	public void gijonCoNum(ProductDTO productdto); // 기존에 가지고 있던 cus_order의 co_p_num을  null로 바꾼다
	public void conumchange(ProductDTO productdto); // 유료결제, 대기중 등에서  판매중으로 변경시  CUS_ORDER 번호값을 null로	
	public void statusChange(ProductDTO productdto); //product의 0, 1, 2, 3 으로 상태변경	스테이터스 값 변경
	public void cus_num(ProductDTO productdto); //cus oder 유료결제 코드 번호를 설정하는곳
	public void cus_numdelete(ProductDTO productdto); //cus oder 유료결제 코드 번호를 설정하는곳
	public void productUnitDelete(String filename);
	//파일업로드
	public void ProductFileReg(CusFileDTO dto);
	
	//아래로는 상품 수정
	public ProductDTO lister(int p_num); // 번호에 맞는 상품 정보 가져오기
	public PDetailDTO listerPD(int p_num); // 번호에 맞는 상품상세 정보 가져오기
	public void itemUP(ProductDTO productdto); //상품 정보수정
	public void itemDpUP(ProductDTO productdto); //상품상세 정보수정
	public void productAllFileDelete(int num);
	
	//재고현황	
	public List<ProductDTO> stocklist(HashMap map);  // 아이디에 맞는 상품 제고 및 목록 가져오기
	public List<ProductDTO> stockonlist(HashMap map);  // id에 맞는상품 제고 중 판매중인 리스트 가져오기
	public void stockPro (PDetailDTO pdetaildto); // 번호에 맞는 상품 재고 변경
	public int stockcount (String id); // 보유한 전체상품 
	public int stocklistcount(HashMap map);
	
	//유료결제
	public int listPayCount (String id); // 품목확장 유료결제 갯수 
	public int listpaynowcount (String id); // 품목확장 유료결제 갯수 
	public int powerPayCount (String id); // 파워링크 유료결제 횟수	
	public int useCount(String id);//파워링크 결제 가능 갯수
	public List<CusOrderDTO> powerlist (String id); // 상위노출 목록 리스트로 정보 가져오기
	public List<CusOrderDTO> powerlistOne (HashMap map); // 상위노출 목록 리스트로 정보 가져오기
	public List<CusOrderDTO> paylist (String id); // 상위노출 목록 리스트로 정보 가져오기
	public List<CusOrderDTO> paylistTwo (HashMap map); // 상위노출 목록 리스트로 정보 가져오기
	
	//상위노출
	
    public List<ProductDTO> poweredlist(String id); // 판매중이나 아직 상위노출 안하고 있는 상품 
    public ProductDTO payMentItem(ProductDTO productdto); // 결제 번호에 맞는 상품 정보 가져오기
    public void payFinish(CusOrderDTO cusorderDTO); //파워링크 결제 페이지에서 결제할목록 보여주기
    public void itempayFinish(CusOrderDTO cusorderDTO);//품목 확장 결제 완료
    
    // 매출액 보는 화면
    public int getProductTotalmoney(@Param ("check") int check, @Param ("id") String id); // 총 매출액 월별로 넘기기
    public int getTotalCount(@Param ("check") int check, @Param ("id") String id); // 총구매수량 넘기기
    public int getDeliveryPay(@Param ("check") int check, @Param ("id") String id); // 총배송비 구하기
    public int getCoponPay(@Param ("check") int check, @Param ("id") String id); // 총 쿠폰 사용비용 구하기 (이때, 관리자가 준 쿠폰은 비용에서 제외)
    public Integer getHOT(@Param ("check") int check, @Param ("id") String id); // 판매량 높은 것 구하기
    
    
    //판매된 상품 리스트 뽑기  
    public List<ProductMorderDTO> ProfitItemlist(HashMap map);  // 판매된 상품 날짜 미선택 확인
    public int ProfitItemcount(@Param ("check") int check, @Param ("id") String id); //월별 구매 진행 횟수 
    
    
    //월별 구매회원보기
    public int getConsumerCount (@Param ("check") int check, @Param ("id") String id); //월별 구매한 회원 카운트
    public List<PurchaseMemberListDTO> memberlist(HashMap map); //구매한 회원의 정보를 보는곳
    public int companynum(String id); //사업자 실별번호받는곳
    public List<ProductDTO> itemList(String id); //판매자 판매목록 보기
    public void cusCouponPro(CouponDTO coupondto); //쿠폰제공하는 곳
    public int userCouponCount(int companynum);  //쿠폰 제공한 갯수
    public List<CouponDTO> couponList(HashMap map); //쿠폰을 제공한 리스트
    
    // 배송현황 분류
    public int deliverOutCount(@Param ("check") int check, @Param ("id") String id);//취소건 카운트
    public List<PurchaseMemberListDTO> deliverOutList(HashMap map); // 취소된건들의 리스트를 가져옴
    public int deliveringCount(@Param ("check") int check, @Param ("id") String id);//배송 건 정보 카운트
    public List<PurchaseMemberListDTO> deliveringList(HashMap map); // 취소된건들의 리스트를 가져옴
    
    //배송현황 status
    public void statusOut(@Param ("num") int num, @Param ("status") int status); //1일때 배송일, 배송완료일  null
    public void statusing(@Param ("num") int num, @Param ("status") int status); //2일때 배송일 up, 배송완룡일 null
    public void statused(@Param ("num") int num, @Param ("status") int status); //3일때 배송완료일 up
	public void insert_cusorder(CusOrderDTO cusorderdto); // 카카오톡 인서트
	public void insert_cusordertwo(CusOrderDTO cusorderdto); // 카카오톡 인서트
	
	public int getProductNEXTNum();  //상품등록시 다음번호
    
    
}