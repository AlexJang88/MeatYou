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
   private String thumb;  // ����� ����
   private int p_price;  // ����
   private int p_rcount;  // ��ȸ�� 
   private Date p_reg_date;  // �����
   private String startdate;  // �ǸŽ�����
   private String enddate;  // �Ǹ�������
   private int p_status; // �ǸŻ���
   
   
   private int pd_p_num;  //��ǰid
	private String pd_p_desc;  //��ǰ����	
	private String origin; // ������
	private String local;  // �����ּ�
	private int weight;  // �߷�
	private String butchery;  // ������
	private int serialNum;  // �̷� ��ȣ
	private String retain; // �������
	private int stock;  //����
	private String pd_duedate;  //�������
	private int pd_p_status;  // �������
	private PDetailDTO pdetaildto;  // ������ ���
   
}
