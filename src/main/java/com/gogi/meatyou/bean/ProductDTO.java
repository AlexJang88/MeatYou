package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
 	private int p_num;  //��ǰ���̵�
	  private String p_m_id;  //�Ǹ��ھ��̵�
	 	private String p_name; //��ǰ��
		private int p_category; // ī�װ����� 111 ����
		private int p_s_category;  // ��ǰ��Ʈ
		private String thumb;  
		private int p_price;  // ����
		private int p_rcount;  // ��ȸ�� 
		private Date p_reg_date;  // �����
		private String startdate;  // �ǸŽ�����
		private String enddate;  // �Ǹ�������	  
		private String co_name;
		private int co_num; // ������� �ڵ��ȣ
		private int co_p_num; // ������� ��ǰ��ȣ
		private int clickpay;
		private int co_quantity;
	   	
	  private int p_status; // �ǸŻ���   
	  private int pd_p_num;  //��ǰid
		private String pd_p_desc;  //��ǰ����	
		private String origin; // ������
		private String local;  // �����ּ�
		private int weight;  // �߷�
		private String butchery;  // ������
		private int serialNum;  // �̷� ��ȣ
		private String retain; // �������
		private int stock;  //�����
		private String pd_duedate;  //�������
		private int pd_p_status;  // �������
		private PDetailDTO pdetaildto;  // ������ ���
		private double star;	//�������
		private int count;	// �Ǹŷ�
		private int r_cnt;	// ���� ��
		private String category1;	// ī�װ���1(����/����)
		private String category2;	// ī�װ���2(����/��)
		private String category3;	// ī�װ���3(�� ����)
		private String ppic_m_id;
		private int ppic_p_num;
		private int reviewAllCNT;
		private int r_p_num;  //��ǰ���̵�
		private int add_num;
		private String add_m_id;
		private String add_mem_address1;
		private String add_mem_address2;
		private int add_type;
   }