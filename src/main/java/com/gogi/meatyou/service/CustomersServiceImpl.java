package com.gogi.meatyou.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusFileDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemAddressDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.PreferDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductMorderDTO;
import com.gogi.meatyou.bean.PurchaseMemberListDTO;
import com.gogi.meatyou.bean.customerGraphDTO;
import com.gogi.meatyou.repository.CustomersMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public  class CustomersServiceImpl implements CustomersService {
   
   private int imgcnt = 0;
   
   @Autowired
   private ArrayList<String> productoldname;
   
   
   @Autowired
   private HashMap boardMap;
   
   @Autowired
   private CustomersMapper mapper;

   @Override
   public String ChartData(Model model, String id, String selectedYear) {
      JsonObject jobject = new JsonObject();
      
      String ddyy= "-01-01";
      String ydate = selectedYear + ddyy ;
      customerGraphDTO cusgrDTO = new customerGraphDTO();
      cusgrDTO.setId(id);
      cusgrDTO.setYdate(ydate);
      
      
      List<customerGraphDTO> graphList = mapper.graph(cusgrDTO);
      ArrayList<Integer> mon_sal = new ArrayList<Integer>();
      ArrayList<Integer> net_profit=new ArrayList<Integer>();
      
      for(customerGraphDTO dto : graphList) {
         mon_sal.add(dto.getMon_sal());
         net_profit.add(dto.getNet_profit());
      }
      
      jobject.add("mon_sal", new Gson().toJsonTree(mon_sal));
      jobject.add("net_profit", new Gson().toJsonTree(net_profit));
      
      ydate = ydate.substring(0,4);
      
      jobject.addProperty("selectedYear", ydate);
      
      String a = jobject.toString();
      
      return a;
   }

   @Override  //누적매출액
   public void totalsumMoney(Model model, String id) {
      int totalmoneysum = mapper.totalsumMoney(id); //누적매출액
      
      model.addAttribute("totalmoneysum", totalmoneysum);
   }


   
   @Override 
   public int itemcount(String id) {      
      return mapper.itemcount(id);
   }
   
   @Override 
   public void list(Model model, String id, int pageNum) {  
      

      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      int count = mapper.itemcount(id);   
      List<ProductDTO> list = Collections.EMPTY_LIST;
      
      if (count > 0) {
         boardMap.put("start", startRow);
         boardMap.put("end", endRow);
         boardMap.put("id", id);
         list = mapper.list(boardMap); 
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
   
      int paycount = mapper.paycount(id); 
      int M_status =mapper.member_status(id);   
      
      List<CusOrderDTO> cus_order = mapper.cus_order(id); 

      model.addAttribute("cus_order", cus_order); 
      model.addAttribute("M_status", M_status); 
      model.addAttribute("count", count);  
      model.addAttribute("paycount", paycount);  
      model.addAttribute("list", list);
   }
   
  
   @Override
   public void listout(Model model, String id, int pageNum) { 
      int pageSize = 10;
      int startRow = (pageNum - 1) * pageSize + 1;
      int endRow = pageNum * pageSize;
      int countout= mapper.itemcountout(id); 
      List<ProductDTO> listout = Collections.EMPTY_LIST;
      
      if (countout > 0) {
         boardMap.put("start", startRow);
         boardMap.put("end", endRow);
         boardMap.put("id", id);
         listout = mapper.listout(boardMap); 
         
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

      int M_status =mapper.member_status(id);   
      
      List<CusOrderDTO> cus_order = mapper.cus_order(id); 
      
      
      model.addAttribute("cus_order", cus_order); 
      model.addAttribute("M_status", M_status); 
      model.addAttribute("countout", countout);     
      model.addAttribute("listout", listout); 
   }

   @Override
   public void statusChange(Model model, ProductDTO productdto) {                   
      String id = productdto.getP_m_id();       
                  
   
   
      if(productdto.getP_status()==0 || productdto.getP_status()==2 || productdto.getP_status()== 3 || productdto.getP_status() ==1) {
         mapper.conumchange(productdto); 
      }  
         
      if(productdto.getP_status() ==1) {         
         int cuscheck = mapper.cuscheck(productdto);   
         if(cuscheck==1) {             
            mapper.gijon(productdto); 
            mapper.gijonCoNum(productdto);                  
         }
         mapper.cus_numdelete(productdto); 
         mapper.cus_num(productdto); 
      }    
      mapper.statusChange(productdto);        
   
   }

   
   
   
   @Override
   public void statusChangeouut(ProductDTO productdto) {
      mapper.statusChange(productdto);
   }
   
  

      @Override
      public void lister(Model model, int p_num) {  
         ProductDTO lister = mapper.lister(p_num); 
         PDetailDTO listerPD = mapper.listerPD(p_num); 
         model.addAttribute("lister", lister);      
         model.addAttribute("listerPD", listerPD);      
      }

      //에디터만들기
      
      @Override
      public String uploadProductImageFile(MultipartFile multipartFile, HttpServletRequest request) {
         JsonObject jsonObject = new JsonObject();
         ArrayList<String> fname = new ArrayList<String>();
         
         int n_num = 0;  // 임시 방편으로 해놈
         
         String path = request.getServletContext().getRealPath("/resources/file/product/"+n_num+"/");
         
         String originalFileName = multipartFile.getOriginalFilename();  //원래 파일 이름 불러오기
         String extension = originalFileName.substring(originalFileName.lastIndexOf(".")); // 확장자 가져오기
         
         String savedFileName = "product_"+n_num+"_"+(++imgcnt)+extension; // 사진이름만들기 / 상품+번호+0번+확장자
         
         productoldname.add(savedFileName); //옛날 이름을 담아놓음
         
         File targetFile = new File(path + savedFileName); // 파일과 이름을 넣는 것을 알려준다
         
         try {
            if(!targetFile.exists()) {
               targetFile.mkdir();  //폴더가 없으면 폴더를 만듬 <- 위에 경로가 있음
            }
            multipartFile.transferTo(targetFile);// 안에 사진을 넣음
            jsonObject.addProperty("url", "/resources/file/product/"+n_num+"/"+ savedFileName);
            jsonObject.addProperty("responseCode","success");            
         }catch(IOException e){
            jsonObject.addProperty("responseCode","error");
            e.printStackTrace();
         }
            String a = jsonObject.toString();
         return a;
      }
      
      
         
      @Override
      public void stocklist(Model model, String id, int pageNum) {  
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int stockcount = mapper.stockcount(id);  
         List<ProductDTO> stocklist = Collections.EMPTY_LIST;
         
         int[] aree = null;
         
         if (stockcount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            stocklist = mapper.stocklist(boardMap); 
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
         int stockcount = mapper.itemcounting(id); 
         List<ProductDTO> stockonlist = Collections.EMPTY_LIST;      
         
         int [] aree =null;
         
         if (stockcount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            stockonlist = mapper.stockonlist(boardMap);      
            aree = new int[stockonlist.size()];
            
            for (int i = 0; i < stockonlist.size(); i++) {
               boardMap.put("p_num", stockonlist.get(i).getP_num());
               aree[i] = mapper.stocklistcount(boardMap);
            }
            
         }

         model.addAttribute("stockonlist", stockonlist);
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
      public void stockPro(PDetailDTO pdetaildto) { 
         mapper.stockPro(pdetaildto);
      }

      @Override
      public void stockOnPro(PDetailDTO pdetaildto) { 
         mapper.stockPro(pdetaildto);      
      }
      
   
      
      
      
      

      @Override
      public void pay(Model model, String id) { 
         int listPayCount = mapper.listPayCount(id); 
         int listpaynowcount = mapper.listpaynowcount(id); 
         int powerPayCount = mapper.powerPayCount(id); 
         
         List<CusOrderDTO> powerlist = mapper.powerlist(id); 
         List<CusOrderDTO> paylist = mapper.paylist(id);  
         
         model.addAttribute("listPayCount", listPayCount);
         model.addAttribute("listpaynowcount", listpaynowcount);
         model.addAttribute("powerPayCount", powerPayCount);
         model.addAttribute("powerlist", powerlist);
         model.addAttribute("paylist", paylist);               
      }
      
      @Override 
      public void payOne(Model model, String id, int pageNum) {
         
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int powerPayCount = mapper.powerPayCount(id);    
         List<CusOrderDTO> powerlistOne = Collections.EMPTY_LIST;      
            
         
         if (powerPayCount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            powerlistOne = mapper.powerlistOne(boardMap); 
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
         int listPayCount = mapper.listPayCount(id);    
         List<CusOrderDTO> paylist = Collections.EMPTY_LIST;               
         
         if (listPayCount > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);            
            paylist = mapper.paylistTwo(boardMap);  
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
      
         int listpaynowcount = mapper.listpaynowcount(id);    
         model.addAttribute("listpaynowcount", listpaynowcount);
      }
      
      
      
      @Override
      public void powerlist(Model model, String id) {
         int counting = mapper.itemcounting(id);   
          
         int powerPayCount = mapper.useCount(id); 
         List<ProductDTO> poweredlist = mapper.poweredlist(id); 
         
         
         model.addAttribute("counting", counting);  
      
         model.addAttribute("powerPayCount", powerPayCount);  
         model.addAttribute("poweredlist", poweredlist);  
         
      }

      @Override  
      public void payment(Model model, ProductDTO productdto) {       
       ProductDTO payMentItem = mapper.payMentItem(productdto);           
       
       model.addAttribute("payMentItem", payMentItem);
      }

      @Override
      public void payFinish(CusOrderDTO cusorderDTO) { 
         mapper.payFinish(cusorderDTO);      
      }

      @Override
      public void itempayFinish(CusOrderDTO cusorderDTO) {      
         mapper.itempayFinish(cusorderDTO);
      }

      
      
      
   
      @Override
      public void getprofit(Model model, int check, String id) {
          model.addAttribute("ptm", mapper.getProductTotalmoney(check, id)); 
          model.addAttribute("totalCount", mapper.getTotalCount(check, id)); 
          model.addAttribute("coponPay", mapper.getCoponPay(check, id)); 
          model.addAttribute("deliveryPay", mapper.getDeliveryPay(check, id)); 
          model.addAttribute("HOT", mapper.getHOT(check, id)); 
          
         model.addAttribute("check",check);   
      }

      @Override 
      public void getCheckprofit(Model model, int check, String start, String end, String id) {
         String[] startarr = start.split("/");
         String[] endarr=end.split("/");
         start = startarr[2]+"-"+startarr[0]+"-"+startarr[1];
         end = endarr[2]+"-"+endarr[0]+"-"+endarr[1];
         boardMap.put("start", start);
         boardMap.put("end",end);
         
         model.addAttribute("check",check);
      }

      @Override  
      public void getProfitItem(Model model, int check, String id, int pageNum) {
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int count = mapper.ProfitItemcount(check, id);
         List<ProductMorderDTO> getProfitItemlist = Collections.EMPTY_LIST;
         
         if (count > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);
            boardMap.put("check", check);            
            getProfitItemlist = mapper.ProfitItemlist(boardMap);
            
         }
         
         model.addAttribute("itemList", getProfitItemlist);
         model.addAttribute("count",count);
         model.addAttribute("check",check);
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
         
      }

      @Override
       public Date calculateTargetDate(Date currentDate, int check) {
           Calendar cal = Calendar.getInstance();
           cal.setTime(currentDate);
           cal.add(Calendar.MONTH, check);
           return cal.getTime();
       }

      
      
      @Override
      public void consumerList(Model model, int check, int pageNum, String id) {
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int count = mapper.getConsumerCount(check, id);   
         List<PurchaseMemberListDTO> memberlist = Collections.EMPTY_LIST;
         
         if (count > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("id", id);
            boardMap.put("check", check);
            memberlist = mapper.memberlist(boardMap); // 
         }
         
         model.addAttribute("check",check);
         model.addAttribute("memberlist", memberlist);
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
   
      }
      
      @Override  
      public void companynum(Model model, String id) {
         int companynum = mapper.companynum(id);
         
         model.addAttribute("companynum", companynum);
      }
      
      @Override 
      public void itemList(Model model, String id) {
         List <ProductDTO> itemList = mapper.itemList(id);
         
         model.addAttribute("itemList", itemList);
      }

      
      @Override  
      public void cusCouponPro(Model model, String p_m_id, String id, int point, int companynum, CouponDTO coupondto, int p_status, int couponUse) {
         coupondto.setCp_cus_num(companynum);  
         coupondto.setCp_price(point); 
         coupondto.setCp_m_id(p_m_id);  
         coupondto.setCp_cus_id(id); 
         coupondto.setC_type(couponUse); 
         
         
         if(couponUse == 2) { 
            coupondto.setCp_p_num(p_status); 
         }else { 
            coupondto.setCp_p_num(0); 
         }   
          mapper.cusCouponPro(coupondto);  
                
      }

      @Override  //荑좏룿  젣怨듯븳 由ъ뒪 듃
      public void couponList(Model model, String id, int pageNum) {
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int companynum = mapper.companynum(id);
         int count = mapper.userCouponCount(companynum);   
         List<CouponDTO> couponList = Collections.EMPTY_LIST;
         
         if (count > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("companynum", companynum);
            couponList = mapper.couponList(boardMap); 
         }

         model.addAttribute("couponList", couponList);
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
      }

      
      @Override  
      public void deliverout(Model model, int check, int pageNum, String id) {
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int count = mapper.deliverOutCount(check, id);   
         List<PurchaseMemberListDTO> deliverOutList = Collections.EMPTY_LIST;
         
         if (count > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("check", check);
            boardMap.put("id", id);
            deliverOutList = mapper.deliverOutList(boardMap); 
         }

         model.addAttribute("deliverOutList", deliverOutList);
         model.addAttribute("count", count);
         model.addAttribute("check", check);
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
      }

      @Override
      public void delivering(Model model, int check, int pageNum, String id) {
         int pageSize = 10;
         int startRow = (pageNum - 1) * pageSize + 1;
         int endRow = pageNum * pageSize;
         int count = mapper.deliveringCount(check, id);   
         List<PurchaseMemberListDTO> deliveringList = Collections.EMPTY_LIST;
         
         if (count > 0) {
            boardMap.put("start", startRow);
            boardMap.put("end", endRow);
            boardMap.put("check", check);
            boardMap.put("id", id);
            deliveringList = mapper.deliveringList(boardMap); 
         }

         model.addAttribute("deliveringList", deliveringList);
         model.addAttribute("count", count);
         model.addAttribute("check", check);
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
         
      }

      @Override  // 배송현황변경
      public void deliverStatus(int order_num, int order_status) {
         int num = order_num;
         int status = order_status;
                  
         if(status == 1) {
            mapper.statusOut(num, status); //1일때 배송일, 배송완료일  null
         } else if(status == 2) {
            mapper.statusing(num, status); // 2일때 배송일 up, 배송완룡일 null
         } else {
            mapper.statused(num, status);  //3일때 배송완료일 up
         }
      }

      @Override
      public int getco_num() {  //conum 받기
         return  mapper.getco_num();
                  
      }

      @Override  //사진실험
      public void fileUpload(Model model,MultipartFile thumbs, String filePath, ProductDTO productdto) {                  
         String fileName = thumbs.getOriginalFilename();  //원래사진이름
         if(!fileName.equals("")) {  //사진이름이 빈칸이 아니라면
            String ext = fileName.substring(fileName.lastIndexOf(".")); //점 뒤로 자른다
            fileName = UUID.randomUUID()+ext; //사진이름만들어줌
            File copy = new File(filePath + fileName); //파일이 올라갈 위치의 사진위치와 이름 지정
            try {
               thumbs.transferTo(copy);
            }catch(Exception e) {
               e.printStackTrace();
            }                           
         }
      }

      

      @Override
      public void powerlink(Model model, int p_num, int clickpay) {
         int co_quantity = 0;
            if (clickpay == 10000) {
               co_quantity = 110;
            } else if (clickpay == 30000) {
               co_quantity = 330;
            } else if (clickpay == 50000) {
               co_quantity = 565;
            } else if (clickpay == 100000) {
               co_quantity = 1160;
            } else {
               co_quantity = 2400;
            }
            int co_num=mapper.getco_num();
            System.out.println("powerlink======"+co_num);
            ProductDTO productdto = new ProductDTO();
            productdto.setP_num(p_num); // 상품번호값
            productdto.setClickpay(clickpay);
            productdto.setCo_quantity(co_quantity);
            productdto.setCo_name("파워링크(" + co_quantity + "회)");
            productdto.setCo_num(mapper.getco_num());// co넘버값

            model.addAttribute("co_num", co_num);
            model.addAttribute("clickpay", clickpay);
            model.addAttribute("quantity", co_quantity);
            model.addAttribute("payMentItem", productdto); // 결제하는곳으로 상품 번호넘기기

      }

      @Override
      public void insert_cusorder(CusOrderDTO cusorderdto) {  //카카오톡 결재 인서트 부분
         mapper.insert_cusorder(cusorderdto);
   
         
      }

      @Override
      public void geterco_num(Model model) {
         int co_num = mapper.getco_num();
      
         model.addAttribute("co_num", co_num);
                  
      }

      @Override
      public void insert_cusorderTwo(CusOrderDTO cusorderdto) {
         mapper.insert_cusordertwo(cusorderdto);
         System.out.println("==========++"+cusorderdto.getCo_m_id());
         System.out.println("==========++"+cusorderdto.getCo_num());
         System.out.println("==========++"+cusorderdto.getCo_p_num());
         System.out.println("==========++"+cusorderdto.getCo_quantity());
      }

      //아래 에디터

      @Override
      public void productReg(ProductDTO productdto, String realPath,MultipartFile file) {
               // 기존 temp폴더에 저장된 이미지 표시를 위해 에디터에는 /temp로 경로가 지정되어 있다 
               // 이를 마지막 게시글 다음 번호로 설정한다.
              int board_num=mapper.getProductNEXTNum();
              System.out.println("=======++"+board_num);

              String oname =file.getOriginalFilename();
              String extention = oname.substring(oname.lastIndexOf("."));
              String filename=UUID.randomUUID()+extention;
              try {
              File copy = new File(realPath+"/temporary/"+filename);
              if(copy.exists()) {
                 copy.delete();
              }else {
                 file.transferTo(copy);
              }
              } catch (Exception e) {
                 e.printStackTrace();
              }
              productdto.setPd_p_desc(productdto.getPd_p_desc().replaceAll("/temporary", "/" + board_num));
              
               // temp 폴더 안의 이미지를 게시글 저장소로 이동
               String path_folder1 = realPath + "/temporary/";
               String path_folder2 = realPath + "/"+board_num+"/";
               
               productdto.setP_num(board_num);
               // 폴더 복사 함수
             
               productdto.setThumb(filename);
               
               productdto.setAdd_m_id(productdto.getP_m_id());
               
                mapper.productUp(productdto);  //상품정보
                
                mapper.P_DETAILUp(productdto);  // 상품 상세정보
                mapper.mem_addressUp(productdto);// 반품주소
                fileUpload(path_folder1, path_folder2,board_num);
                deleteFolder(path_folder1);
               
   }
      
      
      
      
        private void fileUpload(String path_folder1, String path_folder2,int num) {
              // path_folder1에서 path_folder2로 파일을 복사하는 함수입니다.
           System.out.println("fileUpload====");
              File folder1;
              File folder2;
              folder1 = new File(path_folder1);
              folder2 = new File(path_folder2);

              // 복사할 폴더들이 존재하지 않으면 생성합니다.
              if (!folder1.exists())
                  folder1.mkdirs();
              if (!folder2.exists())
                  folder2.mkdirs();

              // 폴더1에서 파일 목록을 가져옵니다.
              File[] target_file = folder1.listFiles();
              for (File file : target_file) {
                  // 복사 대상 파일의 경로와 이름을 설정합니다.
                  File temp = new File(folder2.getAbsolutePath() + File.separator + file.getName());

                  if (file.isDirectory()) {
                      temp.mkdir();
                  } else {
                      FileInputStream fis = null;
                      FileOutputStream fos = null;
                      try {
                          // 파일 복사를 위해 FileInputStream과 FileOutputStream을 생성합니다.
                          fis = new FileInputStream(file);
                          fos = new FileOutputStream(temp);

                          byte[] b = new byte[4096];
                          int cnt = 0;
                          while ((cnt = fis.read(b)) != -1) {
                              // 버퍼를 사용하여 파일 내용을 읽고 복사합니다.
                              fos.write(b, 0, cnt);
                          }
                          CusFileDTO dto = new CusFileDTO();
                          System.out.println("fileupload num === "+num);
                          if(num!=0) {
                             System.out.println("fileupload after if ==="+num);
                             dto.setCf_p_num(num);
                             dto.setCf_filename(file.getName());
                             mapper.ProductFileReg(dto);
                          }
                      } catch (Exception e) {
                          e.printStackTrace();
                      } finally {
                          // FileInputStream과 FileOutputStream을 닫습니다.
                          try {
                              fis.close();
                              fos.close();
                          } catch (IOException e) {
                              e.printStackTrace();
                          }
                      }
                  }
              }
          }
      // 하위 폴더 삭제
          private void deleteFolder(String path) {
              // 주어진 경로에 있는 폴더와 파일을 재귀적으로 삭제하는 함수입니다.

              File folder = new File(path);
              try {
                  if (folder.exists()) {
                      File[] folder_list = folder.listFiles();
                      for (int i = 0; i < folder_list.length; i++) {
                          if (folder_list[i].isFile())
                              // 파일인 경우, 파일을 삭제합니다.
                              folder_list[i].delete();
                          else
                              // 폴더인 경우, 재귀적으로 폴더 내부의 파일 및 폴더를 삭제합니다.
                              deleteFolder(folder_list[i].getPath());
                          // 파일이나 폴더를 삭제합니다.
                          folder_list[i].delete();
                      }
                      // 폴더를 삭제합니다.
                      folder.delete();
                  }
              } catch (Exception e) {
                  e.getStackTrace();
              }
          }

          // 위치값으로 내부 파일 이름 가져오기
          private List<String> getFileNamesFromFolder(String folderName) {
              // 파일 이름을 저장할 리스트 생성
              List<String> fileNames = new ArrayList<>();

              // 주어진 폴더 경로를 기반으로 폴더 객체 생성
              File folder = new File(folderName);
              // 폴더 내의 파일들을 가져옴
              File[] files = folder.listFiles();
              if (files != null) {
                  // 파일인 경우 파일 이름을 리스트에 추가
                  for (File file : files) {
                      if (file.isFile()) {
                          fileNames.add(file.getName());
                      }
                  }
              }
              // 파일 이름을 담은 리스트 반환
              return fileNames;
          }

          // 더미 파일 삭제
          private void removeDummyFiles(List<String> fileNames, String filePath, String contents,String thumb ) {
              // 주어진 파일 이름 리스트를 기반으로 파일을 삭제
              for (String fileName : fileNames) {
                  // contents 문자열에 파일 이름이 포함되어 있지 않은 경우 파일 삭제
                  if (!contents.contains(fileName)) {
                     if(!thumb.contains(fileName)) {   
                     deleteFile(filePath, fileName);
                      mapper.productUnitDelete(fileName);
                     }
                  }
              }
          }

          // 파일 하나 삭제
          private void deleteFile(String filePath, String fileName) {
              // 주어진 파일 경로와 이름을 기반으로 파일 경로 객체 생성
              Path path = Paths.get(filePath, fileName);
              try {
                  Files.delete(path);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
         @Override
         public void productUpdate(String realPath, int num, Model model) {
               
               
                 // 폴더 내부 파일 삭제 함수
                 deleteFolder(realPath+"temporary/");

                 // 게시글 저장소에 있는 파일들을 temp 파일로 업로드 
                 // 게시글 수정중 변심으로 페이지 벗어놔도 원본 게시글의 이미지는 보존된다.
                 String path_folder1 = realPath + num + "/";
                 String path_folder2 = realPath + "temporary/";
                 
                 // temp로 임시저장
                 fileUpload(path_folder1, path_folder2,0);
                 ProductDTO pdto= mapper.lister(num);
                 PDetailDTO ddto=mapper.listerPD(num);
                 MemAddressDTO mdto = mapper.adressUP(num);
                 
                 // 본글에있던 이미지 경로를 temp로 바꿔준다
                 ddto.setPd_p_desc(ddto.getPd_p_desc().replaceAll(num + "/", "temporary/"));
                // pdto.setThumb(pdto.getThumb().replaceAll(num + "/", "temporary/"));
                 model.addAttribute("lister",pdto);
                 model.addAttribute("listerPD", ddto);
                 model.addAttribute("listemd", mdto);
              
         }
         
         
         @Override
         public void productUpdateReg(String realPath, ProductDTO productdto, MultipartFile thumbs) {         
            
            if(thumbs.isEmpty()) {
               productdto.setThumb(mapper.lister(productdto.getP_num()).getThumb());
            }else {
               productdto.setThumb("nothing");
            }
            
            
            productdto.setPd_p_desc(productdto.getPd_p_desc().replaceAll("temporary/", productdto.getP_num()+"/"));
                 // 본문에 안들어간 파일들 삭제(temp 폴더)
                 String filePath = realPath + "temporary/";
                 
                 // 더미 파일 삭제함수 매개변수 : 파일 목록, 파일 경로, 검사할 본문
                 removeDummyFiles(getFileNamesFromFolder(filePath), filePath, productdto.getPd_p_desc(),productdto.getThumb());
                 
                 // 본글의 폴더 비우기
                 filePath = realPath + productdto.getP_num() + "/";
                 for (String fileName : getFileNamesFromFolder(filePath)) {
                     deleteFile(filePath, fileName);
                     mapper.productAllFileDelete(productdto.getP_num());
                 }

                 // temp 에서 저장된 데이터들 업로드
                 String path_folder1 = realPath + "temporary/";
                 String path_folder2 = realPath + productdto.getP_num() + "/";

                 fileUpload(path_folder1, path_folder2, productdto.getP_num());
                 if(thumbs != null && !thumbs.isEmpty()) {
                  String oname =thumbs.getOriginalFilename();
                    String extention = oname.substring(oname.lastIndexOf("."));
                    String filename=UUID.randomUUID()+extention;
                    System.out.println("========+"+filename);
                    try {
                    File copy = new File(realPath+productdto.getP_num()+"/"+filename);
                    if(copy.exists()) {
                       copy.delete();
                    }else {
                       thumbs.transferTo(copy);
                    }
                    productdto.setThumb(filename);
                    System.out.println("========++"+productdto.getThumb());
                    } catch (Exception e) {
                       e.printStackTrace();
                    }
                  
               
               }
                 
                 
               mapper.itemUP(productdto);  
               mapper.itemDpUP(productdto);
               mapper.itemadUP(productdto);
               
              deleteFolder(path_folder1);                 
         }
         
         
         @Override
         public String productImgUpload(MultipartFile multipartFile, String realPath) {
             JsonObject jsonObject = new JsonObject();

                 // 이미지 파일이 저장될 경로 설정
                 String contextRoot = realPath + "temporary/";
                 String fileRoot = contextRoot;

                 // 업로드된 파일의 원본 파일명과 확장자 추출
                 String originalFileName = multipartFile.getOriginalFilename();
                 String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

                 // 새로운 파일명 생성 (고유한 식별자 + 확장자)
                 String savedFileName = UUID.randomUUID() + extension;

                 // 저장될 파일의 경로와 파일명을 나타내는 File 객체 생성
                 File targetFile = new File(fileRoot + savedFileName);

                 try {
                     // 업로드된 파일의 InputStream 얻기
                     java.io.InputStream fileStream = multipartFile.getInputStream();

                     // 업로드된 파일을 지정된 경로에 저장
                     FileUtils.copyInputStreamToFile(fileStream, targetFile);

                     // JSON 객체에 이미지 URL과 응답 코드 추가
                     jsonObject.addProperty("url", "/resources/file/product/temporary/" + savedFileName);
                     jsonObject.addProperty("responseCode", "success");
                 } catch (IOException e) {
                     // 파일 저장 중 오류가 발생한 경우 해당 파일 삭제 및 에러 응답 코드 추가
                     FileUtils.deleteQuietly(targetFile);
                     jsonObject.addProperty("responseCode", "error");
                     e.printStackTrace();
                 }

                 // JSON 객체를 문자열로 변환하여 반환
                 String a = jsonObject.toString();
                 return a;
         }
         @Override
         public void productImgDel(String fileName, String realPath) {

              // 폴더 위치
              String filePath = realPath + "temporary/";

              // 해당 파일 삭제
              deleteFile(filePath, fileName);
         }
         
         
         
         @Override
         public void productContent(Model model, int num) {
            ProductDTO pdto= mapper.lister(num);
            PDetailDTO ddto= mapper.listerPD(num); 
            model.addAttribute("lister", pdto);
            model.addAttribute("listerPD", ddto);
            model.addAttribute("p_num", num);
         }
         @Override
         public void productDelete(int num) {
            ProductDTO dto = mapper.lister(num);
            dto.setP_status(4);
            mapper.statusChange(dto);
            
         }

		@Override
		public void survey1(String id, int selectedAnimal) {
			PreferDTO prefer = new PreferDTO();
			prefer.setPre0_response(selectedAnimal);
			prefer.setPre_m_id(id);
			mapper.survey1(prefer);
		}

		@Override
		public void survey2(String id, int selectedAnimal) {
			PreferDTO prefer = new PreferDTO();
			
			prefer.setPre1_response(selectedAnimal);
			prefer.setPre_m_id(id);
			mapper.survey2(prefer);
		}



         


      

   
}