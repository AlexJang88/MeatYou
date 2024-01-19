package com.gogi.meatyou.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.bean.ProductMorderDTO;
import com.gogi.meatyou.bean.PurchaseMemberListDTO;
import com.gogi.meatyou.repository.CustomersMapper;

@Service
public  class CustomersServiceImpl implements CustomersService {
   @Autowired
   private HashMap boardMap;
   
   @Autowired
   private CustomersMapper mapper;

   @Override
   public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto) { //占쎄맒占쎈�뱄옙踰묉에占�      
      mapper.productUp(productdto);
      pdetaildto.setPd_p_num(mapper.productCurrval());
      mapper.P_DETAILUp(pdetaildto);
      System.out.println(pdetaildto.getPd_p_num());
   }
   
   @Override  //�븘�씠�뵒�뿉 留욌뒗 �긽�뭹 媛��닔 遺덈윭�삤湲�
	public int itemcount(String id) {		
		return mapper.itemcount(id);
	}
	
	@Override // �븘�씠�뵒�뿉 留욌뒗 �긽�뭹媛��닔 遺덈윭���꽌 紐⑤뜽�뿉 �떞�븘�꽌 媛� 媛��졇�삤湲� (�긽�뭹�쟾泥대ぉ濡�)
	public void list(Model model, String id, int pageNum) {  //�긽�뭹媛��닔諛쏆븘�삤湲� , �젙蹂대뱾
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		int count = mapper.itemcount(id);   //id�뿉 留욌뒗 �긽�뭹媛��닔 諛쏆븘�삤湲�
		List<ProductDTO> list = Collections.EMPTY_LIST;
		
		if (count > 0) {
			boardMap.put("start", startRow);
			boardMap.put("end", endRow);
			boardMap.put("id", id);
			list = mapper.list(boardMap); // id�뿉 留욌뒗 �긽�뭹紐⑸줉 由ъ뒪�듃 媛��졇�삤湲�
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
	
		int paycount = mapper.paycount(id); //id�뿉 留욌뒗 �쑀猷뚭껐�젣 �뭹紐⑷갗�닔
		int M_status =mapper.member_status(id);	// �쉶�썝�벑湲됱뿉 留욊쾶 媛��졇�삤湲�
		
		List<CusOrderDTO> cus_order = mapper.cus_order(id); // id�뿉 留욌뒗 �긽�뭹紐⑸줉 由ъ뒪�듃 媛��졇�삤湲�

		model.addAttribute("cus_order", cus_order); // id�뿉 留욌뒗 �쑀猷뚭껐�젣肄붾뱶媛��졇�삤湲�
		model.addAttribute("M_status", M_status); // id�뿉 留욌뒗 �벑湲됯��졇�삤湲�
		model.addAttribute("count", count);  // count�뿉 �긽�뭹媛��닔 �떞湲�
		model.addAttribute("paycount", paycount);  // paycount�뿉 �뭹紐� �쑀猷뚭껐�옱�븳 媛��닔 �떞湲�
		model.addAttribute("list", list);
	}
	
  
	@Override
	public void listout(Model model, String id, int pageNum) { //�뙋留ㅼ쥌猷뚮맂 �럹�씠吏�
		int pageSize = 10;
		int startRow = (pageNum - 1) * pageSize + 1;
		int endRow = pageNum * pageSize;
		int countout= mapper.itemcountout(id); // �뙋留ㅼ쨷�씤 �긽�뭹媛��닔 �떞湲�
		List<ProductDTO> listout = Collections.EMPTY_LIST;
		
		if (countout > 0) {
			boardMap.put("start", startRow);
			boardMap.put("end", endRow);
			boardMap.put("id", id);
			listout = mapper.listout(boardMap); // id�뿉 留욌뒗 �긽�뭹紐⑸줉 由ъ뒪�듃 媛��졇�삤湲�
			
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

		int M_status =mapper.member_status(id);	// �쉶�썝�벑湲됱뿉 留욊쾶 媛��졇�삤湲�
		
		List<CusOrderDTO> cus_order = mapper.cus_order(id); // id�뿉 留욌뒗 �긽�뭹紐⑸줉 由ъ뒪�듃 媛��졇�삤湲�
		
		
		model.addAttribute("cus_order", cus_order); // id�뿉 留욌뒗 �쑀猷뚭껐�젣肄붾뱶媛��졇�삤湲�
		model.addAttribute("M_status", M_status); // id�뿉 留욌뒗 �벑湲됯��졇�삤湲�
		model.addAttribute("countout", countout);  // counting�뿉 �뙋留ㅼ쨷�씤 �긽�뭹媛��닔 �떞湲�	
		model.addAttribute("listout", listout); // id�뿉 留욌뒗 �긽�뭹紐⑸줉 由ъ뒪�듃 媛��졇�삤湲�
	}

	@Override
	public void statusChange(ProductDTO productdto) { //�뙋留ㅼ옄�쓽 �뙋留ㅼ긽�깭蹂�寃�						
		
		if(productdto.getP_status()==0 || productdto.getP_status()==2 || productdto.getP_status()== 3 || productdto.getP_status() ==1) {
			mapper.conumchange(productdto); //�쑀猷뚭껐�젣, ��湲곗쨷 �벑�뿉�꽌  �뙋留ㅼ쨷�쑝濡� 蹂�寃쎌떆  CUS_ORDER 踰덊샇媛믪쓣 null濡�
		}  
				
		if(productdto.getP_status() ==1) {			
			int cuscheck = mapper.cuscheck(productdto);// �슦�꽑 湲곗〈�뿉 媛믪씠 �엳�뒗吏� 李얜뒗�떎 �엳�쑝硫� 1踰�  �뾾�쑝硫� 0			
			if(cuscheck==1) {				 
				mapper.gijon(productdto); // 湲곗〈�쓽 媛믪씠 �엳�뼱�꽌 1�씠 �굹�삤硫� 湲곗〈�쓽 媛믪쓣 status 2(�뙋留ㅻ�湲�)濡� 諛붽씔�떎
				mapper.gijonCoNum(productdto);	//湲곗〈�뿉 媛�吏�怨� �엳�뜕 cus_order�쓽 co_p_num�쓣  null濡� 諛붽씔�떎
				//�궡爰� �빐�옱�븯�뒗寃� �뿬湲곗뿉�꽌 if濡� �궡媛� �빐�옱�븷�븣 �씠�봽臾몄쓣 留뚮뱾�뼱�빞�븿					
			}
			mapper.cus_numdelete(productdto); //�떊洹쒕줈 �벑濡앺븯�젮�뒗�뜲 cus_order�뿉 �씠誘� �쑀猷뚭껐�젣 肄붾뱶媛� �엳�떎硫� null
			mapper.cus_num(productdto); // �떊洹� cus_order �쑀猷뚭껐�젣 肄붾뱶 踰덊샇瑜� �꽕�젙�븯�뒗怨�
		} 	
		mapper.statusChange(productdto); // product�쓽 0, 1, 2, 3 �쑝濡� �긽�깭蹂�寃�	�뒪�뀒�씠�꽣�뒪 媛� 蹂�寃�	 	
	}

	@Override
	public void statusChangeouut(ProductDTO productdto) {
		mapper.statusChange(productdto);
	}
	
  
	//�븘�옒濡쒕뒗 �긽�뭹 �닔�젙 愿��젴
		@Override
		public void lister(Model model, int p_num) {  //�긽�뭹 �젙蹂댁닔�젙
			ProductDTO lister = mapper.lister(p_num); // 踰덊샇�뿉 留욌뒗 �긽�뭹�젙蹂� 媛��졇�삤湲�
			PDetailDTO listerPD = mapper.listerPD(p_num); //踰덊샇�뿉 留욌뒗 �긽�꽭�젙蹂� 媛��졇�삤湲�
			model.addAttribute("lister", lister);		
			model.addAttribute("listerPD", listerPD);		
		}
		
		@Override
		public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto) {
			mapper.itemUP(productdto);  //�젙蹂댁닔�젙
			mapper.itemDpUP(pdetaildto);  // �젙蹂댁닔�젙 �긽�꽭	
		}

  
		//�옱怨� �쁽�솴愿�由�	
		@Override
		public void stocklist(Model model, String id, int pageNum) {  // �쟾泥� �긽�뭹 �옱怨� 議고쉶
			int pageSize = 10;
			int startRow = (pageNum - 1) * pageSize + 1;
			int endRow = pageNum * pageSize;
			int stockcount = mapper.stockcount(id);  // �쟾泥� �긽�뭹 紐⑸줉 媛��닔
			List<ProductDTO> stocklist = Collections.EMPTY_LIST;
			
			int[] aree = null;
			
			if (stockcount > 0) {
				boardMap.put("start", startRow);
				boardMap.put("end", endRow);
				boardMap.put("id", id);				
				stocklist = mapper.stocklist(boardMap); // id�뿉 留욌뒗 �젣怨� 諛� �긽�뭹 �쟾泥� 由ъ뒪�듃 媛��졇�삤湲�
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
			int stockcount = mapper.itemcounting(id); // �뙋留ㅼ쨷�씤 �긽�뭹 紐⑸줉 媛��닔
			List<ProductDTO> stockonlist = Collections.EMPTY_LIST;		
			
			int [] aree =null;
			
			if (stockcount > 0) {
				boardMap.put("start", startRow);
				boardMap.put("end", endRow);
				boardMap.put("id", id);				
				stockonlist = mapper.stockonlist(boardMap);		// id�뿉 留욌뒗�긽�뭹 �젣怨� 以� �뙋留ㅼ쨷�씤 由ъ뒪�듃 媛��졇�삤湲� 
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
		public void stockPro(PDetailDTO pdetaildto) { //�엳�뱺�쑝濡� �꽆湲� 踰덊샇瑜� �넻�빐 (�쟾泥� 紐⑸줉以� �옱怨� 蹂�寃�)
			mapper.stockPro(pdetaildto);
		}

		@Override
		public void stockOnPro(PDetailDTO pdetaildto) { //�쐞�� �룞�씪 (�뙋留ㅼ쨷�씤 �긽�뭹 �젣怨좊�寃�)
			mapper.stockPro(pdetaildto);		
		}
		
	
		
		
		
		//�븘�옒�뒗 �쑀猷뚭껐�젣

		@Override
		public void pay(Model model, String id) { // �쑀猷뚭껐�옱 媛��닔, �젙蹂� 媛��졇�삤湲�
			int listPayCount = mapper.listPayCount(id); // �뭹紐⑺솗�옣 �쑀猷뚭껐�젣 �쟾泥닿갗�닔
			int listpaynowcount = mapper.listpaynowcount(id); // �뭹紐⑺솗�옣 �쑀猷뚭껐�젣 �궓�� 媛��닔
			int powerPayCount = mapper.powerPayCount(id); // �뙆�썙留곹겕 �쑀猷뚭껐�젣 �쟾泥� 媛��닔
			
			List<CusOrderDTO> powerlist = mapper.powerlist(id); //�뙆�썙留곹겕 �젙蹂�
			List<CusOrderDTO> paylist = mapper.paylist(id);  // �긽�뭹 �솗�옣 �젙蹂�
			
			model.addAttribute("listPayCount", listPayCount);
			model.addAttribute("listpaynowcount", listpaynowcount);
			model.addAttribute("powerPayCount", powerPayCount);
			model.addAttribute("powerlist", powerlist);
			model.addAttribute("paylist", paylist);					
		}
		
		@Override // �뙆�썙留곹겕 援щℓ湲곕줉 �긽�꽭蹂닿린 �럹�씠吏�
		public void payOne(Model model, String id, int pageNum) {
			
			int pageSize = 10;
			int startRow = (pageNum - 1) * pageSize + 1;
			int endRow = pageNum * pageSize;
			int powerPayCount = mapper.powerPayCount(id); // �뙆�썙留곹겕 �쑀猷뚭껐�젣 �쟾泥� 媛��닔	
			List<CusOrderDTO> powerlistOne = Collections.EMPTY_LIST;		
				
			
			if (powerPayCount > 0) {
				boardMap.put("start", startRow);
				boardMap.put("end", endRow);
				boardMap.put("id", id);				
				powerlistOne = mapper.powerlistOne(boardMap); //�뙆�썙留곹겕 �젙蹂�
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
			int listPayCount = mapper.listPayCount(id); // �뭹紐⑺솗�옣 �쑀猷뚭껐�젣 �쟾泥닿갗�닔	
			List<CusOrderDTO> paylist = Collections.EMPTY_LIST;					
			
			if (listPayCount > 0) {
				boardMap.put("start", startRow);
				boardMap.put("end", endRow);
				boardMap.put("id", id);				
				paylist = mapper.paylistTwo(boardMap);  // �긽�뭹 �솗�옣 �젙蹂�
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
		
			int listpaynowcount = mapper.listpaynowcount(id); // �뭹紐⑺솗�옣 �쑀猷뚭껐�젣 �궓�� 媛��닔	
			model.addAttribute("listpaynowcount", listpaynowcount);
		}
		
		
		//�뿬湲곕뒗 �뙆�썙留곹겕
		@Override
		public void powerlist(Model model, String id) {
			int counting = mapper.itemcounting(id);   //id�뿉 留욌뒗 �뙋留ㅼ쨷�씤 �긽�뭹 媛��닔 媛��졇�삤湲�
			int countter = mapper.countter(id); //�뙋留ㅼ쨷�씠�굹 �븘吏� �긽�쐞�끂異� �븞�븯怨� �엳�뒗 �긽�뭹 媛��닔
			int powerPayCount = mapper.powerPayCount(id); // �뙆�썙留곹겕 �쑀猷뚭껐�젣 �쟾泥� 媛��닔 
			List<ProductDTO> poweredlist = mapper.poweredlist(id); //�뙋留ㅼ쨷�씠�굹 �븘吏� �긽�쐞�끂異� �븞�븯怨� �엳�뒗 �긽�뭹
			
			
			model.addAttribute("counting", counting);  //id�뿉 留욌뒗 �뙋留ㅼ쨷�씤 �긽�뭹 媛��닔 媛��졇�삤湲�
			model.addAttribute("countter", countter);  //�뙋留ㅼ쨷�씠�굹 �븘吏� �긽�쐞�끂異� �븞�븯怨� �엳�뒗 �긽�뭹 媛��닔
			model.addAttribute("powerPayCount", powerPayCount);  //�긽�쐞�끂異� 以묒씤 媛��닔
			model.addAttribute("poweredlist", poweredlist);  //�뙋留ㅼ쨷�씠�굹 �븘吏� �긽�쐞�끂異� �븞�븯怨� �엳�뒗 �긽�뭹
			
		}

		@Override  //寃곗옱 吏곸쟾 �떒怨�
		public void payment(Model model, ProductDTO productdto) { //�뙆�썙留곹겕 寃곗젣 �럹�씠吏��뿉�꽌 寃곗젣�븷紐⑸줉 蹂댁뿬二쇨린		
		 ProductDTO payMentItem = mapper.payMentItem( productdto);// 寃곗젣�븷 �긽�뭹踰덊샇�쓽 �젙蹂대�� �떞�쓬		 	 
		 
		 model.addAttribute("payMentItem", payMentItem);
		}

		@Override
		public void payFinish(CusOrderDTO cusorderDTO) { //�뙆�썙留곹겕 寃곗옱�셿猷�
			mapper.payFinish(cusorderDTO);		
		}

		@Override
		public void itempayFinish(CusOrderDTO cusorderDTO) {	//�뭹紐⑷껐�옱�셿猷�		
			mapper.itempayFinish(cusorderDTO);
		}

		
		
		
		
		
		
		//留ㅼ텧�븸 蹂대뒗怨� 泥댄겕 �븞 �뻽�쓣�븣
		@Override
		public void getprofit(Model model, int check, String id) {
			 model.addAttribute("ptm", mapper.getProductTotalmoney(check, id)); //珥앸ℓ異쒖븸
			 model.addAttribute("totalCount", mapper.getTotalCount(check, id)); // 珥� �뙋留ㅺ갗�닔
			 //model.addAttribute("coponPay", mapper.getCoponPay(check, id)); // 珥� 荑좏룿�궗�슜猷� �븘吏� �뾾�쓬
			 model.addAttribute("deliveryPay", mapper.getDeliveryPay(check, id)); // 珥� �뙋留ㅺ갗�닔
			 model.addAttribute("HOT", mapper.getHOT(check, id)); // 珥� �뙋留ㅺ갗�닔
			 
			model.addAttribute("check",check);	
		}

		@Override // 留ㅼ텧�븸 �궇吏� 泥댄겕 �뻽�쓣 �븣
		public void getCheckprofit(Model model, int check, String start, String end, String id) {
			String[] startarr = start.split("/");
			String[] endarr=end.split("/");
			start = startarr[2]+"-"+startarr[0]+"-"+startarr[1];
			end = endarr[2]+"-"+endarr[0]+"-"+endarr[1];
			boardMap.put("start", start);
			boardMap.put("end",end);
			
			model.addAttribute("check",check);
		}

		@Override //�뙋留ㅼ긽�뭹 �궇吏� 泥댄겕 �븞�뻽�쓣�븣 
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
				getProfitItemlist = mapper.ProfitItemlist(boardMap);// �뙋留ㅻ맂 �긽�뭹 �궇吏� 泥댄겕 �븞�뻽�쓣�븣 �굹�삤�뒗媛�
				
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

		
		
		@Override// 구매회원
		public void consumerList(Model model, int check, int pageNum, String id) {
			int pageSize = 10;
			int startRow = (pageNum - 1) * pageSize + 1;
			int endRow = pageNum * pageSize;
			int count = mapper.getConsumerCount(check, id);   // 내 상품을 구매한 회원목록 숫자 (월별)
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
		
		@Override  //쿠폰 주는화면에서 사업자 식별 번호가 필요함 ex) 450
		public void companynum(Model model, String id) {
			int companynum = mapper.companynum(id);
			
			model.addAttribute("companynum", companynum);
		}
		
		@Override //쿠폰주는 화면에서 상품번호고르게끔하려고
		public void itemList(Model model, String id) {
			List <ProductDTO> itemList = mapper.itemList(id);
			
			model.addAttribute("itemList", itemList);
		}

		
		@Override  //쿠폰제공
		public void cusCouponPro(Model model, String p_m_id, String id, int point, int companynum, CouponDTO coupondto, int p_status, int couponUse) {
			coupondto.setCp_cus_num(companynum);  //사업자식별번호
			coupondto.setCp_price(point); //금액
			coupondto.setCp_m_id(p_m_id);  //회원아이디
			coupondto.setCp_cus_id(id); //판매자
			coupondto.setC_type(couponUse); //사용처
			
			
			if(couponUse == 2) { //판매자 특정상품만
				coupondto.setCp_p_num(p_status); //상품번호
			}else { //판매자 상품 전체중
				coupondto.setCp_p_num(0); //이건 sql에서 null로 바꿈 
			}
		
			 mapper.cusCouponPro(coupondto);  //쿠폰 제공
			 		
		}

		

		

   
   
   
   
}