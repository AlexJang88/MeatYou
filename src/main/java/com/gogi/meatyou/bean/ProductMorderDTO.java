package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class ProductMorderDTO {
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
	   //�Ʒ��� m_order ���̺�
	   	private int order_num;  // ��Ϲ�ȣ
		private String order_m_id;  // �Ǹ���
		private int order_cp_num; // ������ȣ
		private int order_p_num; //��ǰ��ȣ
		private int order_p_price;  //��ǰ����
		private int order_dere_num;  // �����ȣ
		private int order_dere_pay;  // ��ۺ�
		private String order_Addr;  //����ּ�
		private int order_quantity;  //����
		private String order_paytype;  // ������
		private int order_status;   //������Ȳ
		private int order_discount;  //���αݾ�
		private int order_totalprice;  //�� �����ݾ�
		private String order_memo;  // ��� �޸�
		private Date order_paydate;  //������
		private Date order_canceldate;  //���� �����
}
