package com.gogi.meatyou.controller;

import java.security.Provider.Service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.service.CustomersService;

@Controller
@RequestMapping("/customers/*")
public class CustomersController {
	
	@Autowired
	private CustomersService service;
	
	@RequestMapping("customer") //Ȩ
	public String home(HttpSession session) {		
		session.setAttribute("memId", "user5");
		return "customer/customer";
	}
	
	@RequestMapping("itemUpdate") //��ǰ���
	public String update() {
		return "customer/itemUpdate";
	}

	@RequestMapping("itemUpdatePro") //��ǰ��� Ȯ��
	public String itemUpdatePro( ProductDTO productdto, PDetailDTO pdetaildto) {		
		service.itemUpdate(productdto,pdetaildto);		
		return "redirect:/customers/customer";
	}
	
	@RequestMapping("itemListing") //�Ǹ����� ��ǰ���
	public String itemListing(Model model, HttpSession session ) {		
		String id = (String)session.getAttribute("memId"); // ������ ��Ʈ������ ��ȯ
		service.listing(model, id);  // ���̵� �ѱ��		
		return "customer/itemListing";
	}
	
	@RequestMapping("/statusChangeing") //�Ǹ����� ��ǰ��� ���������� �����ϸ� ���ƿ��°�����
	public String statusChangeing(String p_m_id, int p_status, int p_num) {	
		ProductDTO productdto = new ProductDTO();  //�Ʒ� 3���� ����
		productdto.setP_m_id(p_m_id);	// ���̵�
		productdto.setP_status(p_status); // ����� ��ǰ ���°�
		productdto.setP_num(p_num); //��ǰ��ȣ��
		
		service.statusChange(productdto); // ȸ���� �ǸŻ��¸� ����
		return "redirect:/customers/itemListing";
	}
		
	@RequestMapping("itemList") //����� ��ǰ���
	public String itemList(Model model, HttpSession session ) {		
		String id = (String)session.getAttribute("memId"); // ������ ��Ʈ������ ��ȯ
		service.list(model, id);  // ���̵� �ѱ��		
		return "customer/itemList";
	}
	
	@RequestMapping("/statusChange") //��ǰ������������� �����ϸ� ���ƿ��°�����
	public String statusChange(String p_m_id, int p_status, int p_num) {	
		ProductDTO productdto = new ProductDTO();  //�Ʒ� 3���� ����
		productdto.setP_m_id(p_m_id);	// ���̵�
		productdto.setP_status(p_status); // ����� ��ǰ ���°�
		productdto.setP_num(p_num); //��ǰ��ȣ��
		
		service.statusChange(productdto); // ȸ���� �ǸŻ��¸� ����
		return "redirect:/customers/itemList";
	}
	
	@RequestMapping("itemListOut") //�Ǹ������ ��ǰ���
	public String itemListOut(Model model, HttpSession session ) {		
		String id = (String)session.getAttribute("memId"); // ������ ��Ʈ������ ��ȯ
		service.listout(model, id);  // ���̵� �ѱ��		
		
		return "customer/itemListOut";
	}
	
	@RequestMapping("/statusChangeout") //�Ǹ������ ��ǰ��� ���������� �����ϸ� ���ƿ��°�����
	public String statusChangeout(String p_m_id, int p_status, int p_num) {	
		ProductDTO productdto = new ProductDTO();  //�Ʒ� 3���� ����
		productdto.setP_m_id(p_m_id);	// ���̵�
		productdto.setP_status(p_status); // ����� ��ǰ ���°�
		productdto.setP_num(p_num); //��ǰ��ȣ��
		
		service.statusChange(productdto); // ȸ���� �ǸŻ��¸� ����
		return "redirect:/customers/itemListOut";
	}
	
	@RequestMapping("content") //��ǰ ��������
	public String content(Model model, int p_num, HttpSession session ) {
		model.addAttribute("p_num",p_num);
		
		return "customer/content";
	}
	
	
	@RequestMapping("itemRevise") //��ǰ �������� (�� Ȯ���ϱ�
	public String itemRevise(Model model, int p_num ) {
		model.addAttribute("p_num",p_num);
		service.lister(model, p_num);			
		return "customer/itemRevise";
	}
	
	
	@RequestMapping("itemRevisePro") //��ǰ �������� ����������
	public String itemRevisePro( ProductDTO productdto, PDetailDTO pdetaildto) {
			
		//productdto.setStartdate(productdto.getStartdate().substring(0,10)); ��¥ �߶󺸱� ���� ����
		//productdto.setEnddate(productdto.getEnddate().substring(0,10));
		//System.out.println("================"+productdto.getEnddate().substring(0,10)+"========"); ��¥ �߶󺸱� ���� ����
		service.updateitemPro(productdto,pdetaildto);
				
		return "redirect:/customers/itemList";
	}
	
	
	
	
	
	
	@RequestMapping("profit") //������Ȳ
	public String profit() {
		return "customer/profit";
	}
	
	@RequestMapping("stock") //�����Ȳ
	public String stock() {
		return "customer/stock";
	}
		
	@RequestMapping("consumerList") //����ȸ��
	public String consumerList() {
		return "customer/consumerList";
	}
	
	@RequestMapping("pay") //�������
	public String pat() {
		return "customer/pay";
	}
	
	@RequestMapping("deliver") //�����Ȳ
	public String deliver() {
		return "customer/deliver";
	}
	
	@RequestMapping("total") //����ȳ�
	public String total() {
		return "customer/total";
	}
	
	@RequestMapping("cusQna") //�����ϱ�
	public String cusQna() {
		return "customer/cusQna";
	}
	
	@RequestMapping("cusCoupon") //������������Ʈ
	public String cusCoupon() {
		return "customer/cusCoupon";
	}
	
}
