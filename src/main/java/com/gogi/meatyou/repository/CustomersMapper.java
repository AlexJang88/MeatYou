package com.gogi.meatyou.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;


public interface CustomersMapper {

	public void productUp(ProductDTO productdto); //��ǰ���
	public void P_DETAILUp(PDetailDTO pdetaildto);  // ��ǰ�󼼵��
	
	public int itemcount(String id); // ��ǰ�����������  �� ��� ����
	public int paycount(String id); // ǰ�� ��������� ����
	public int itemcounting(String id); // �Ǹ����� ��ǰ ���������� �Ǹ����� ����
	public int itemcountout(String id); // �Ǹ����� ��ǰ ���������� �Ǹ����� ����
	
	public List<ProductDTO> list(String id); // ��ǰ��� ��ü ������
	public List<ProductDTO> listing(String id); // �Ǹ����� ��ǰ���� ���̴� ������ 
	public List<ProductDTO> listout(String id); // �Ǹ����� ��ǰ���� ���̴� ������ 
	
	public int member_status(String id); // ��ǰ��Ͽ��� �������� ǥ��� ��޿����� ������ ( 2001 2002 2003 2004 �̳��´�)
	public int statusChange(ProductDTO productdto); //ȸ���� �ǸŻ��¸� ����
	
	//�Ʒ��δ� ��ǰ ����
	public ProductDTO lister(int p_num); // ��ȣ�� �´� ��ǰ ���� ��������
	public PDetailDTO listerPD(int p_num); // ��ȣ�� �´� ��ǰ�� ���� ��������
	public void itemUP(ProductDTO productdto); //��ǰ ��������
	public void itemDpUP(PDetailDTO pdetaildto); //��ǰ�� ��������
	
}
