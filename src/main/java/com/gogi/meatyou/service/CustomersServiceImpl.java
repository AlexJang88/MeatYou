package com.gogi.meatyou.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductMorderDTO;
import com.gogi.meatyou.bean.PurchaseMemberListDTO;
import com.gogi.meatyou.repository.CustomersMapper;
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
   public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto, String id) {
	  productdto.setAdd_m_id(id);
	  productdto.setP_m_id(id);
      mapper.productUp(productdto);  //상품정보
      pdetaildto.setPd_p_num(mapper.productCurrval());
      mapper.P_DETAILUp(pdetaildto);  // 상품 상세정보
      mapper.mem_addressUp(productdto);// 반품주소
      
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
		public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto) {
			mapper.itemUP(productdto);  
			mapper.itemDpUP(pdetaildto);  	
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
			int countter = mapper.countter(id); 
			int powerPayCount = mapper.useCount(id); 
			List<ProductDTO> poweredlist = mapper.poweredlist(id); 
			
			
			model.addAttribute("counting", counting);  
			model.addAttribute("countter", countter); 
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

		@Override  //荑좏룿 �젣怨듯븳 由ъ뒪�듃
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
		public void fileUpload(Model model,MultipartFile thumbs, String filePath) {
			String fileName = thumbs.getOriginalFilename();  //원래사진이름
			if(!fileName.equals("")) {  //사진이름이 빈칸이 아니라면
				String ext = fileName.substring(fileName.lastIndexOf(".")); //점 뒤로 자른다
				fileName = "file_"+11+"_"+ext; //사진이름만들어줌
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

		

   
   
   
   
}