package com.gogi.meatyou.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.repository.CustomersMapper;

@Service
public  class CustomersServiceImpl implements CustomersService {
   @Autowired
   private HashMap boardMap;
   
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
   public void list(Model model, String id, int pageNum) {  //상품갯수받아오기 , 정보들
      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      int count = mapper.itemcount(id);   //id에 맞는 상품갯수 받아오기
      List<ProductDTO> list = Collections.EMPTY_LIST;
      
      if (count > 0) {
         boardMap.put("start", startRow);
         boardMap.put("end", endRow);
         boardMap.put("id", id);
         list = mapper.list(boardMap); // id에 맞는 상품목록 리스트 가져오기
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
   
      int paycount = mapper.paycount(id); //id에 맞는 유료결제 품목갯수
      int M_status =mapper.member_status(id);   // 회원등급에 맞게 가져오기
      
      List<CusOrderDTO> cus_order = mapper.cus_order(id); // id에 맞는 상품목록 리스트 가져오기

      model.addAttribute("cus_order", cus_order); // id에 맞는 유료결제코드가져오기
      model.addAttribute("M_status", M_status); // id에 맞는 등급가져오기
      model.addAttribute("count", count);  // count에 상품갯수 담기
      model.addAttribute("paycount", paycount);  // paycount에 품목 유료결재한 갯수 담기
      model.addAttribute("list", list);
   }
   
   
   @Override
   public void listout(Model model, String id, int pageNum) { //판매종료된 페이지
      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      int countout= mapper.itemcountout(id); // 판매중인 상품갯수 담기
      List<ProductDTO> listout = Collections.EMPTY_LIST;
      
      if (countout > 0) {
         boardMap.put("start", startRow);
         boardMap.put("end", endRow);
         boardMap.put("id", id);
         listout = mapper.listout(boardMap); // id에 맞는 상품목록 리스트 가져오기
         
      }

      model.addAttribute("listout", listout);
      model.addAttribute("countout", countout);
      model.addAttribute("pageNum", pageNum);
      model.addAttribute("pageSize", pageSize);

      // page
      int pageCount = countout / pageSize + (countout % pageSize == 0 ? 0 : 1);
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

      int M_status =mapper.member_status(id);   // 회원등급에 맞게 가져오기
      
      List<CusOrderDTO> cus_order = mapper.cus_order(id); // id에 맞는 상품목록 리스트 가져오기
      
      
      model.addAttribute("cus_order", cus_order); // id에 맞는 유료결제코드가져오기
      model.addAttribute("M_status", M_status); // id에 맞는 등급가져오기
      model.addAttribute("countout", countout);  // counting에 판매중인 상품갯수 담기   
      model.addAttribute("listout", listout); // id에 맞는 상품목록 리스트 가져오기
   }

   @Override
   public void statusChange(ProductDTO productdto) { //판매자의 판매상태변경                  
      
      if(productdto.getP_status()==0 || productdto.getP_status()==2 || productdto.getP_status()== 3 || productdto.getP_status() ==1) {
         mapper.conumchange(productdto); //유료결제, 대기중 등에서  판매중으로 변경시  CUS_ORDER 번호값을 null로
      }  
            
      if(productdto.getP_status() ==1) {         
         int cuscheck = mapper.cuscheck(productdto);// 우선 기존에 값이 있는지 찾는다 있으면 1번  없으면 0         
         if(cuscheck==1) {             
            mapper.gijon(productdto); // 기존의 값이 있어서 1이 나오면 기존의 값을 status 2(판매대기)로 바꾼다
            mapper.gijonCoNum(productdto);   //기존에 가지고 있던 cus_order의 co_p_num을  null로 바꾼다
            //내꺼 해재하는것 여기에서 if로 내가 해재할때 이프문을 만들어야함               
         }
         mapper.cus_numdelete(productdto); //신규로 등록하려는데 cus_order에 이미 유료결제 코드가 있다면 null
         mapper.cus_num(productdto); // 신규 cus_order 유료결제 코드 번호를 설정하는곳
      }    
      mapper.statusChange(productdto); // product의 0, 1, 2, 3 으로 상태변경   스테이터스 값 변경       
   }

   @Override
   public void statusChangeouut(ProductDTO productdto) {
      mapper.statusChange(productdto);
   }
   
   
   //아래로는 상품 수정 관련
      @Override
      public void lister(Model model, int p_num) {  //상품 정보수정
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

   
      //재고 현황관리   
      @Override
      public void stocklist(Model model, String id, int pageNum) {  // 전체 상품 재고 조회
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int stockcount = mapper.stockcount(id);  // 전체 상품 목록 갯수
         List<ProductDTO> stocklist = Collections.EMPTY_LIST;
         
         int[] aree = null;
         
         if (stockcount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            stocklist = mapper.stocklist(boardMap); // id에 맞는 제고 및 상품 전체 리스트 가져오기
            aree = new int [stocklist.size()];
            
            for (int i = 0; i < stocklist.size(); i++) {
               boardMap.put("p_num", stocklist.get(i).getP_num());
               aree[i] = mapper.stocklistcount(boardMap);
            }
         }

         model.addAttribute("stocklist", stocklist);
         model.addAttribute("stockcount", stockcount);
         model.addAttribute("aree", aree);
         model.addAttribute("pageNum", pageNum);
         model.addAttribute("pageSize", pageSize);

         // page
         int pageCount = stockcount / pageSize + (stockcount % pageSize == 0 ? 0 : 1);
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
   
      }

      @Override
      public void onStock(Model model, String id, int pageNum) { 
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int stockcount = mapper.itemcounting(id); // 판매중인 상품 목록 갯수
         List<ProductDTO> stockonlist = Collections.EMPTY_LIST;      
         
         if (stockcount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            stockonlist = mapper.stockonlist(boardMap);      // id에 맞는상품 제고 중 판매중인 리스트 가져오기 
         }

         model.addAttribute("stockonlist", stockonlist);
         model.addAttribute("stockcount", stockcount);
         model.addAttribute("pageNum", pageNum);
         model.addAttribute("pageSize", pageSize);

         // page
         int pageCount = stockcount / pageSize + (stockcount % pageSize == 0 ? 0 : 1);
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
      }
      

      @Override
      public void stockPro(PDetailDTO pdetaildto) { //히든으로 넘긴 번호를 통해 (전체 목록중 재고 변경)
         mapper.stockPro(pdetaildto);
      }

      @Override
      public void stockOnPro(PDetailDTO pdetaildto) { //위와 동일 (판매중인 상품 제고변경)
         mapper.stockPro(pdetaildto);      
      }
      
   
      
      
      
      //아래는 유료결제

      @Override
      public void pay(Model model, String id) { // 유료결재 갯수, 정보 가져오기
         int listPayCount = mapper.listPayCount(id); // 품목확장 유료결제 전체갯수
         int listpaynowcount = mapper.listpaynowcount(id); // 품목확장 유료결제 남은 갯수
         int powerPayCount = mapper.powerPayCount(id); // 파워링크 유료결제 전체 갯수
         
         List<CusOrderDTO> powerlist = mapper.powerlist(id); //파워링크 정보
         List<CusOrderDTO> paylist = mapper.paylist(id);  // 상품 확장 정보
         
         model.addAttribute("listPayCount", listPayCount);
         model.addAttribute("listpaynowcount", listpaynowcount);
         model.addAttribute("powerPayCount", powerPayCount);
         model.addAttribute("powerlist", powerlist);
         model.addAttribute("paylist", paylist);               
      }
      
      @Override // 파워링크 구매기록 상세보기 페이지
      public void payOne(Model model, String id, int pageNum) {
         
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int powerPayCount = mapper.powerPayCount(id); // 파워링크 유료결제 전체 갯수   
         List<CusOrderDTO> powerlistOne = Collections.EMPTY_LIST;      
            
         
         if (powerPayCount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            powerlistOne = mapper.powerlistOne(boardMap); //파워링크 정보
         }

         model.addAttribute("powerlistOne", powerlistOne);
         model.addAttribute("powerPayCount", powerPayCount);
         model.addAttribute("pageNum", pageNum);
         model.addAttribute("pageSize", pageSize);

         // page
         int pageCount = powerPayCount / pageSize + (powerPayCount % pageSize == 0 ? 0 : 1);
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
      }
      
      
      @Override
      public void payTwo(Model model, String id, int pageNum) {
         
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int listPayCount = mapper.listPayCount(id); // 품목확장 유료결제 전체갯수   
         List<CusOrderDTO> paylist = Collections.EMPTY_LIST;               
         
         if (listPayCount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            paylist = mapper.paylistTwo(boardMap);  // 상품 확장 정보
         }

         model.addAttribute("paylist", paylist);
         model.addAttribute("listPayCount", listPayCount);
         model.addAttribute("pageNum", pageNum);
         model.addAttribute("pageSize", pageSize);

         // page
         int pageCount = listPayCount / pageSize + (listPayCount % pageSize == 0 ? 0 : 1);
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
      
         int listpaynowcount = mapper.listpaynowcount(id); // 품목확장 유료결제 남은 갯수   
         model.addAttribute("listpaynowcount", listpaynowcount);
      }
      
      
      //여기는 파워링크
      @Override
      public void powerlist(Model model, String id) {
         int counting = mapper.itemcounting(id);   //id에 맞는 판매중인 상품 갯수 가져오기
         int countter = mapper.countter(id); //판매중이나 아직 상위노출 안하고 있는 상품 갯수
         int powerPayCount = mapper.powerPayCount(id); // 파워링크 유료결제 전체 갯수 
         List<ProductDTO> poweredlist = mapper.poweredlist(id); //판매중이나 아직 상위노출 안하고 있는 상품
         
         
         model.addAttribute("counting", counting);  //id에 맞는 판매중인 상품 갯수 가져오기
         model.addAttribute("countter", countter);  //판매중이나 아직 상위노출 안하고 있는 상품 갯수
         model.addAttribute("powerPayCount", powerPayCount);  //상위노출 중인 갯수
         model.addAttribute("poweredlist", poweredlist);  //판매중이나 아직 상위노출 안하고 있는 상품
         
      }

      @Override  //결재 직전 단계
      public void payment(Model model, ProductDTO productdto) { //파워링크 결제 페이지에서 결제할목록 보여주기      
       ProductDTO payMentItem = mapper.payMentItem( productdto);// 결제할 상품번호의 정보를 담음           
       
       model.addAttribute("payMentItem", payMentItem);
      }

      @Override
      public void payFinish(CusOrderDTO cusorderDTO) { //파워링크 결재완료
         mapper.payFinish(cusorderDTO);      
      }

      @Override
      public void itempayFinish(CusOrderDTO cusorderDTO) {   //품목결재완료      
         mapper.itempayFinish(cusorderDTO);
      }

      //매출액 보는곳
      @Override
      public void getprofit(Model model, int check) {
         model.addAttribute("check",check);   
      }

      @Override
      public void getCheckprofit(Model model, int check, String start, String end) {
         String[] startarr = start.split("/");
         String[] endarr=end.split("/");
         start = startarr[2]+"-"+startarr[0]+"-"+startarr[1];
         end = endarr[2]+"-"+endarr[0]+"-"+endarr[1];
         boardMap.put("start", start);
         boardMap.put("end",end);
         
         model.addAttribute("check",check);
      }

   

      

      

      

   

   

   
   
   
   
}