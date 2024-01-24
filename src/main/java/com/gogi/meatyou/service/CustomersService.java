package com.gogi.meatyou.service;

import java.util.Date;

import org.springframework.ui.Model;

import com.gogi.meatyou.bean.CouponDTO;
import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.PDetailDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface CustomersService {

	public void itemUpdate(ProductDTO productdto, PDetailDTO pdetaildto); //��ǰ���
	
	public int itemcount(String id); // ���̵� �´� ��ǰ ��� ���� �ҷ�����
	
	public void list(Model model, String id, int pageNum); // ���̵� �´� ��ǰ���� �� ������ �𵨿� ��Ƽ� �ҷ�����
	public void listout(Model model, String id, int pageNum);  //�Ǹ������ ��ǰ �𵨿� ��Ƽ� �ҷ�����
	public void statusChange(ProductDTO productdto); //ȸ���� �ǸŻ��¸� ����
	public void statusChangeouut(ProductDTO productdto); //ȸ���� �ǸŻ��¸� ����
	
	//�Ʒ��δ� ��ǰ ����
	public void lister(Model model, int p_num); // ��ȣ�� �´� ��ǰ ���� ��������	
	public void updateitemPro(ProductDTO productdto, PDetailDTO pdetaildto); //��ǰ��������
	
	//��� ��Ȳ
	public void stocklist(Model model, String id, int pageNum); //���̵�� �� �ѱ��/ ��ü ��� ��� 
	public void onStock(Model model, String id, int pageNum); //���̵�� �� �ѱ�� // �Ǹ����� ���� ���
	public void stockPro (PDetailDTO pdetaildto); // ��ȣ�� �´� ��ǰ ��� ���� (��ü)
	public void stockOnPro (PDetailDTO pdetaildto); // ��ȣ�� �´� ��ǰ ��� ����(�Ǹ���)

	
	//���������
	public void pay(Model model, String id); //��ǰ ������� ��� �ҷ�����
	public void payOne(Model model, String id, int pageNum); //��ǰ �Ŀ���ũ ���Ÿ�� �󼼺���
	public void payTwo(Model model, String id, int pageNum); //��ǰ �Ŀ���ũ ���Ÿ�� �󼼺���
	public void powerlist(Model model, String id); //��ǰ ������� ��� �ҷ�����
	public void payment(Model model, ProductDTO productdto); //�Ŀ���ũ ���� ���������� �����Ҹ�� �����ֱ�
	public void payFinish(CusOrderDTO cusorderDTO); //�Ŀ���ũ ���� ���������� �����Ҹ�� �����ֱ�
    public void itempayFinish(CusOrderDTO cusorderDTO);//ǰ�� Ȯ�� ���� �Ϸ�
    public void powerlink(Model model,int p_num,int clickpay);
    //�����
    public void getprofit(Model model,int check, String id); //üũ�� ��������
    public void getCheckprofit(Model model,int check,String start,String end, String id); //üũ��������
    //���� �ǸŻ�ǰ
    public void  getProfitItem(Model model,int check, String id, int pageNum); //üũ�� ��������
    //��¥ ���
    Date calculateTargetDate(Date currentDate, int check);
    
    //��ǰ����ȸ�� �� ���� �ֱ�
    public void consumerList(Model model, int check, int pageNum, String id); //��ǰ ���Ÿ���Ʈȸ��
    public void cusCouponPro(Model model, String p_m_id, String  id, int point, int companynum, CouponDTO coupondto, int p_status, int couponUse);  //������ ���������� �Ѿ�°�
    public void companynum(Model model, String id);//����ڹ�ȣ�ޱ�
    public void itemList(Model model, String id);//��ǰ����Ʈ �ޱ�
    public void couponList(Model model, String id, int pageNum);//���� ���� ����Ʈ
    
    //�ֹ� �� �����Ȳ
    public void deliverout(Model model, int check, int pageNum, String id); //�����Ȳ
    
    
    
    
    
    
    
    
    
}