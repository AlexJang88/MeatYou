package com.gogi.meatyou.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.repository.CustomersMapper;

@Service
public class CustomersServiceImpl implements CustomersService {

	@Autowired
	private CustomersMapper mapper;

	@Override
	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto) { //��ǰ���		
		mapper.productUp(productdto);
		mapper.P_DETAILUp(pdetaildto);
	}
	
	@Override  //���̵� �´� ��ǰ ���� �ҷ�����
	public int itemcount(String id) {		
		return mapper.itemcount(id);
	}
	
	@Override // ���̵� �´� ��ǰ���� �ҷ��ͼ� �𵨿� ��Ƽ� �� �������� (��ǰ��ü���)
	public void list(Model model, String id) {  //��ǰ�����޾ƿ��� , ������
		int count = mapper.itemcount(id);   //id�� �´� ��ǰ���� �޾ƿ���
		int paycount = mapper.paycount(id); //id�� �´� ������� ǰ�񰹼�
		int M_status =mapper.member_status(id);		
		List<ProductDTO> list = mapper.list(id); // id�� �´� ��ǰ��� ����Ʈ ��������
		
		model.addAttribute("M_status", M_status); // id�� �´� ��ް�������
		model.addAttribute("count", count);  // count�� ��ǰ���� ���
		model.addAttribute("paycount", paycount);  // paycount�� ǰ�� ��������� ���� ���
		model.addAttribute("list", list);
		
	}
	
	@Override
	public void listing(Model model, String id) { //�Ǹ����� ��ǰ
		int counting = mapper.itemcounting(id); // �Ǹ����� ��ǰ���� ���
		int paycount = mapper.paycount(id); //id�� �´� ������� ǰ�񰹼�
		int M_status =mapper.member_status(id);		 //���̵� �ְ� ��ް�������
		List<ProductDTO> listing = mapper.listing(id); // id�� �´� ��ǰ��� ����Ʈ ��������
		
		model.addAttribute("M_status", M_status); // id�� �´� ��ް�������
		model.addAttribute("counting", counting);  // counting�� �Ǹ����� ��ǰ���� ���
		model.addAttribute("paycount", paycount);  // paycount�� ǰ�� ��������� ���� ���
		model.addAttribute("listing", listing); // id�� �´� ��ǰ��� ����Ʈ ��������
	}
	
	@Override
	public void listout(Model model, String id) { //�Ǹ������ ������
		int countout= mapper.itemcountout(id); // �Ǹ����� ��ǰ���� ���
		int M_status =mapper.member_status(id);		 //���̵� �ְ� ��ް�������
		List<ProductDTO> listout = mapper.listout(id); // id�� �´� ��ǰ��� ����Ʈ ��������
		
		model.addAttribute("M_status", M_status); // id�� �´� ��ް�������
		model.addAttribute("countout", countout);  // counting�� �Ǹ����� ��ǰ���� ���
		model.addAttribute("listout", listout); // id�� �´� ��ǰ��� ����Ʈ ��������
	}

	@Override
	public int statusChange(ProductDTO productdto) { //�Ǹ����� �ǸŻ��º���			
		return mapper.statusChange(productdto);
	}

	
	//�Ʒ��δ� ��ǰ ���� ����
	@Override
	public void lister(Model model, int p_num) {  //��ǰ ��������
		ProductDTO lister = mapper.lister(p_num); // ��ȣ�� �´� ��ǰ���� ��������
		PDetailDTO listerPD = mapper.listerPD(p_num); //��ȣ�� �´� ������ ��������
		model.addAttribute("lister", lister);		
		model.addAttribute("listerPD", listerPD);		
	}
	
	@Override
	public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto) {
		mapper.itemUP(productdto);  //��������
		mapper.itemDpUP(pdetaildto);  // �������� ��	
	}

	
	//��� ��Ȳ����	
	@Override
	public void stocklist(Model model, String id) {  // ��ü ��ǰ ��� ��ȸ
		int stockcount = mapper.itemcount(id);  // ��ü ��ǰ ��� ����
		List<ProductDTO> stocklist = mapper.stocklist(id); // id�� �´� ���� �� ��ǰ ��ü ����Ʈ ��������
		model.addAttribute("stockcount", stockcount);
		model.addAttribute("stocklist", stocklist);
	}

	@Override
	public void onStock(Model model, String id) { 
		int stockcount = mapper.itemcounting(id); // �Ǹ����� ��ǰ ��� ����
		List<ProductDTO> stockonlist = mapper.stockonlist(id); // id�� �´»�ǰ ���� �� �Ǹ����� ����Ʈ ��������		
		model.addAttribute("stockcount", stockcount);
		model.addAttribute("stockonlist", stockonlist);
	}

	@Override
	public void stockPro(PDetailDTO pdetaildto) { //�������� �ѱ� ��ȣ�� ���� (��ü ����� ��� ����)
		mapper.stockPro(pdetaildto);
	}

	@Override
	public void stockOnPro(PDetailDTO pdetaildto) { //���� ���� (�Ǹ����� ��ǰ ������)
		mapper.stockPro(pdetaildto);		
	}

	@Override
	public void pay(Model model, String id) { // ������� ����, ���� ��������
		int listPayCount = mapper.listPayCount(id); // ǰ��Ȯ�� ������� ��ü����
		int listpaynowcount = mapper.listpaynowcount(id); // ǰ��Ȯ�� ������� ���� ����
		int powerPayCount = mapper.powerPayCount(id); // �Ŀ���ũ ������� ��ü ����
		
		List<CusOrderDTO> powerlist = mapper.powerlist(id); //�Ŀ���ũ ����
		List<CusOrderDTO> paylist = mapper.paylist(id);  // ��ǰ Ȯ�� ����
		
		model.addAttribute("listPayCount", listPayCount);
		model.addAttribute("listpaynowcount", listpaynowcount);
		model.addAttribute("powerPayCount", powerPayCount);
		model.addAttribute("powerlist", powerlist);
		model.addAttribute("paylist", paylist);
				
	}

	

	

	
	
	
	
}
