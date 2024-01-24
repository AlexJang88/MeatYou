
package com.gogi.meatyou.controller;

import java.security.Principal;
import java.security.Provider.Service;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;

@Controller
@RequestMapping("/customers/*")
public class CustomersController {
   
   
   @Autowired
   private CustomersService service;
   
   @RequestMapping("customer") //Ȩ
   public String home(Principal pc) {      
      String id = pc.getName();
      return "customer/customer";
   }
   
   @RequestMapping("itemUpdate") //��ǰ���
   public String update(Principal pc) {
	   String id = pc.getName();
      return "customer/itemUpdate";
   }

   @RequestMapping("itemUpdatePro") //��ǰ���Ȯ��
   public String itemUpdatePro( Principal pc, ProductDTO productdto, PDetailDTO pdetaildto) {
	   productdto.setP_m_id(pc.getName());
      service.itemUpdate(productdto,pdetaildto);      
      return "redirect:/customers/customer";
   }
   

  
      
   @RequestMapping("itemList") //����� ��ǰ���
	public String itemList(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) { 
			String id = pc.getName();
		service.list(model, id, pageNum);  // ���̵� �ѱ��		
		return "customer/itemList";
	}
   
   @RequestMapping("/statusChange") //��ǰ������������� �����ϸ� ���ƿ��°�����
   public String statusChange(Model model, Principal pc, int p_status, int p_num, int pageNum, ProductDTO productdto) {	   
	   model.addAttribute("pageNum", pageNum);
	   String id = pc.getName();
	   System.out.println("===++"+p_status);
       int co_num=p_status;	     
	    if(p_status!=0 && p_status!=2 && p_status!=3) {
	    	co_num=p_status;
	    	p_status=1;     	   
	   }      
       productdto.setP_m_id(id);    // ���̵�
       productdto.setP_status(p_status); // ����� ��ǰ ���°�
       productdto.setP_num(p_num); //��ǰ��ȣ��
       productdto.setCo_num(co_num); //��ǰ��ȣ�� 1���ϋ�����
        
       System.out.println("===++"+id);
       System.out.println("===++"+p_status);
       System.out.println("===++"+p_num);
       System.out.println("===++"+co_num);
       
       
       service.statusChange(productdto); // ȸ���� �ǸŻ��¸� ����
       return "redirect:/customers/itemList?pageNum="+pageNum;
   }
   
   @RequestMapping("itemListOut") //�Ǹ������ ��ǰ���
 	public String itemListOut(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum ) {
	    String id= pc.getName();		
 		service.listout(model, id, pageNum); // ���̵� �ѱ��			
 		return "customer/itemListOut";
 	}
   
   
   
   @RequestMapping("/statusChangeout") //�Ǹ������ ��ǰ��� ���������� �����ϸ� ���ƿ��°�����
	public String statusChangeout(Principal pc,  int p_status, int p_num,  ProductDTO productdto, int pageNum) {	   
	   String id= pc.getName();
	   productdto.setP_m_id(id); // ����� ��ǰ ���°�	
	   productdto.setP_num(p_num); // ����� ��ǰ ���°�	
		productdto.setP_status(p_status); // ����� ��ǰ ���°�	
		
		service.statusChangeouut(productdto); // ȸ���� �ǸŻ��¸� ����	
		return "redirect:/customers/itemListOut?pageNum="+pageNum;
	}
   
   @RequestMapping("content") //��ǰ ��������
	public String content(Model model, int p_num ) {
		model.addAttribute("p_num",p_num);		
		return "customer/content";
	}
   
   
 //����� ��������	
 	@RequestMapping("itemRevise") //��ǰ �������� (�� Ȯ���ϱ�
 	public String itemRevise(Model model, int p_num ) {
 		model.addAttribute("p_num",p_num);
 		service.lister(model, p_num);			
 		return "customer/itemRevise";
 	}
      
 	@RequestMapping("itemRevisePro") //��ǰ �������� ����������
	public String itemRevisePro( ProductDTO productdto, PDetailDTO pdetaildto) {					
		service.updateitemPro(productdto,pdetaildto);			
		return "redirect:/customers/itemList";
	}
   
   
 	//����� �����Ȳ�ľ�
	
 		@RequestMapping("stock") //��ü ��ǰ �����Ȳ
 		public String stock(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
 		    String id= pc.getName();		
 			service.stocklist(model, id, pageNum);  // ���̵� �ѱ��		
 			return "customer/stock";
 		}
   
 		@RequestMapping("onStock") //�Ǹ����� ��ǰ �����Ȳ
 		public String onStock(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
 		    String id= pc.getName();
 			service.onStock(model, id, pageNum);		
 			return "customer/onStock";
 		}
 		
 		@RequestMapping("stockPro") //��ǰ ��ü��� �� ��� ����
 		public String stockPro(PDetailDTO pdetaildto) {		
 			service.stockPro(pdetaildto);		
 			return "redirect:/customers/stock";
 		}
 		
 		@RequestMapping("stockOnPro") //�Ǹ����� ��ǰ�� �� ��� ����
 		public String stockOnPro(PDetailDTO pdetaildto) {		
 			service.stockOnPro(pdetaildto);		
 			return "redirect:/customers/onStock";
 		}
 		
 	
 		
 		
 		
   
   
 		//���������
 		@RequestMapping("pay") //�������
 		public String pay(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum ) {
 		    String id= pc.getName();
 		   model.addAttribute("pageNum",pageNum);
		    
 			service.pay(model, id);
 			return "customer/pay";
 		}
 		
		
 		@RequestMapping("payOne") //�������
 		public String payOne(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum ) {
 		    String id= pc.getName();
 		   model.addAttribute("pageNum",pageNum);
 		   
 			service.payOne(model, id, pageNum);
 			return "customer/payOne";
 		}
 		
 		@RequestMapping("payTwo") //�������
 		public String payTwo(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum ) {
 		    String id= pc.getName();
 		   model.addAttribute("pageNum",pageNum);
 		   
 			service.payTwo(model, id, pageNum);
 			return "customer/payTwo";
 		}
 		
 		
 		@RequestMapping("powerlink") //�Ŀ���ũ ������� ������
 		public String powerlink(Model model, Principal pc ) {
 		    String id= pc.getName();
 			service.powerlist(model, id);  // ���̵� �ѱ��			
 			return "customer/powerlink";
 		}
 		
 		@RequestMapping("itemplus") //ǰ��Ȯ�� ������� ������
 		public String itemplus(Model model, Principal pc) { 		
 			String id = pc.getName();
 			model.addAttribute("id",id);
 			return "customer/itemplus";
 		}
 		
 		@RequestMapping("powerlinkpay") //�Ŀ���ũ ���� â  // �̰� ���� �ȵ�
 		public String powerlinkpay(Model model, int p_num, Principal pc, int co_num, int clickpay) { 
 			service.powerlink(model, p_num, clickpay);
 			return "customer/powerlinkpay";
 		}
 		
 		@RequestMapping("powerlinkpayPro") //�Ŀ���ũ ���� â  // �̰� ���� �ȵ�
 		public String powerlinkpayPro(int clickcount,int clickpay, CusOrderDTO cusorderDTO, Principal pc, int p_num) { 
 			String id = pc.getName();		
 			cusorderDTO.setCo_m_id(id);
 			cusorderDTO.setCo_p_num(p_num);
 			cusorderDTO.setCo_quantity(clickcount);
 			cusorderDTO.setCo_pay(clickpay);
 			
 			service.payFinish(cusorderDTO);
 		
 			return "customer/powerlinkpayPro";
 		}
 		
 		
 		@RequestMapping("itemplusPro") //ǰ����� �Ϸ�
 		public String itemplusPro(CusOrderDTO cusorderDTO, Principal pc) { 					
 			cusorderDTO.setCo_m_id(pc.getName());
 			
 			service.itempayFinish(cusorderDTO);
 			return "customer/itemplusPro";
 		}
 		
   
 		
 		
 		
   
 		@RequestMapping("profit") //������Ȳ
 		public String profit(Model model,@RequestParam(value="check",defaultValue="0")int check,String daterange, Principal pc) {
 			String id = pc.getName();
 			
 		// ���� ��¥
 	        Date currentDate = new Date();
 	        // check�� ���� ��¥�� ���
 	        Date targetDate = service.calculateTargetDate(currentDate, check);
 	        // SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
 	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��");
 	        String formattedDate = sdf.format(targetDate);
 	        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
 	        model.addAttribute("currentYear", targetDate.getYear() + 1900);
 		
 			if(check<=0) {	
 				service.getprofit(model,check,id);
 			}else {
 				String start = daterange.substring(0,10);
 				String end = daterange.substring(13, 23);
 				//service.getCheckprofit(model,check,start,end,id); ���� ����
 			}			
 			return "customer/profit";
 		}	
 		
 		@RequestMapping("profitItem") //�ǸŻ�ǰ��Ȳ
 		public String profitItem(Model model,@RequestParam(value="check",defaultValue="0")int check,String daterange, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
 			String id = pc.getName();
 			
 		// ���� ��¥
 	        Date currentDate = new Date();
 	        // check�� ���� ��¥�� ���
 	        Date targetDate = service.calculateTargetDate(currentDate, check);
 	        // SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
 	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��");
 	        String formattedDate = sdf.format(targetDate);
 	        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
 	        model.addAttribute("currentYear", targetDate.getYear() + 1900);

 			
 			if(check<=0) {	
 				service.getProfitItem(model,check,id, pageNum);
 			}else {
 				String start = daterange.substring(0,10);
 				String end = daterange.substring(13, 23);
 				//service.getCheckProfitItem(model,check,start,end,id); ��������
 			}			
 			return "customer/profitItem";
 		}
 		
 		
 		
 		
 		
 		

		@RequestMapping("consumerList") //����ȸ��
 		public String consumerList(Model model,@RequestParam(value="check",defaultValue="0")int check, @RequestParam(value="pageNum", defaultValue = "1") int pageNum, Principal pc) {
			String id = pc.getName();
			
			// ���� ��¥
 	        Date currentDate = new Date();
 	        // check�� ���� ��¥�� ���
 	        Date targetDate = service.calculateTargetDate(currentDate, check);
 	        // SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
 	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��");
 	        String formattedDate = sdf.format(targetDate);
 	        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
 	        model.addAttribute("currentYear", targetDate.getYear() + 1900);
			
			service.consumerList(model, check, pageNum, id);
 			return "customer/consumerList";
 		}
 		
		
		@RequestMapping("cusCoupon") //���������ϴ� ������
 		public String cusCoupon(Model model, String p_m_id, Principal pc) {
			String id = pc.getName();
			
			service.companynum(model, id);
			service.itemList(model, id);
			
			model.addAttribute("p_m_id", p_m_id);
			model.addAttribute("id", id);
 			return "customer/cusCoupon";
 		}
		
		
		@RequestMapping("cusCouponPro") //���������ϴ� ������
 		public String cusCouponPro(Model model, String p_m_id, Principal pc, int point, CouponDTO coupondto, int companynum, int p_status,int couponUse) {
			String id = pc.getName();
			
			service.cusCouponPro(model, p_m_id, id, point,companynum, coupondto, p_status, couponUse);					
 			return "redirect:/customers/CouponList";
 		}
		
		
		
		
		
		@RequestMapping("CouponList") //���������� ������
 		public String CouponList(Model model, Principal pc, @RequestParam(value="pageNum", defaultValue = "1") int pageNum) {
			String id = pc.getName();
			
			service.couponList(model, id, pageNum); //���� ������ ����Ʈ
 			return "customer/CouponList";
 		}
		
		
		
		
		
 		
 		
 		@RequestMapping("deliverout") // �������
 		public String deliver(Model model,@RequestParam(value="check",defaultValue="0")int check,@RequestParam(value="pageNum", defaultValue = "1") int pageNum, Principal pc) {
 			String id = pc.getName();
 			
 			//���� ��¥
 	        Date currentDate = new Date();
 	        // check�� ���� ��¥�� ���
 	        Date targetDate = service.calculateTargetDate(currentDate, check);
 	        // SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
 	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��");
 	        String formattedDate = sdf.format(targetDate);
 	        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
 	        model.addAttribute("currentYear", targetDate.getYear() + 1900);
		
 	        service.deliverout(model, check, pageNum, id);
		
 			return "customer/deliverout";
 		}
 			
 		
 		@RequestMapping("delivering") //�����Ϸ�, �����, ��ۿϷ� , 
 		public String delivering() {
 			return "customer/delivering";
 		}
 		
 		@RequestMapping("delivered") //����Ȯ��
 		public String delivered(Model model,@RequestParam(value="check",defaultValue="0")int check,@RequestParam(value="pageNum", defaultValue = "1") int pageNum, Principal pc) {
 			String id = pc.getName();
 			
 			//���� ��¥
 	        Date currentDate = new Date();
 	        // check�� ���� ��¥�� ���
 	        Date targetDate = service.calculateTargetDate(currentDate, check);
 	        // SimpleDateFormat�� ����Ͽ� ���ϴ� �������� ��¥�� ���ڿ��� ��ȯ
 	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy�� MM��");
 	        String formattedDate = sdf.format(targetDate);
 	        model.addAttribute("currentMonth", targetDate.getMonth() + 1);
 	        model.addAttribute("currentYear", targetDate.getYear() + 1900);
 			
 			
 	        
 			return "customer/delivered";
 		}
 		
 		
 		
 		
 		
 		@RequestMapping("total") //����ȳ�
 		public String total() {
 			return "customer/total";
 		}
 		
 		@RequestMapping("cusQna") //�����ϱ�
 		public String cusQna() {
 			return "customer/cusQna";
 		}
 		
 	
   
}