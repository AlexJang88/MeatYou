package com.gogi.meatyou.service;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto); //��ǰ���
	public int itemcount(String id); // ���̵� �´� ��ǰ ��� ���� �ҷ�����
	public void list(Model model, String id); // ���̵� �´� ��ǰ���� �� ������ �𵨿� ��Ƽ� �ҷ�����
	public void listing(Model model, String id); //�Ǹ����� ��ǰ �𵨿� ��Ƽ� �ҷ�����
	public void listout(Model model, String id); //�Ǹ������ ��ǰ �𵨿� ��Ƽ� �ҷ�����
	public int statusChange(ProductDTO productdto); //ȸ���� �ǸŻ��¸� ����
	
	//�Ʒ��δ� ��ǰ ����
	public void lister(Model model, int p_num); // ��ȣ�� �´� ��ǰ ���� ��������	
	public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto); //��ǰ��������
	
	//��� ��Ȳ
	public void stocklist(Model model, String id); //���̵�� �� �ѱ��/ ��ü ��� ��� 
	public void onStock(Model model, String id); //���̵�� �� �ѱ�� // �Ǹ����� ���� ���
	public void stockPro (PDetailDTO pdetaildto); // ��ȣ�� �´� ��ǰ ��� ���� (��ü)
	public void stockOnPro (PDetailDTO pdetaildto); // ��ȣ�� �´� ��ǰ ��� ����(�Ǹ���)
	
	//���������
	public void pay(Model model, String id); //��ǰ ������� ��� �ҷ�����
	
	
}
