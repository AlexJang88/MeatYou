package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class MOrderDTO {
	private int order_num;  // ��Ϲ�ȣ 
	private String order_id;// �ֹ���ȣ 01-23 �����߰�
	private int order_item_id;// vam_order_item �⺻Ű 01-23 �����߰�       --db����
	private double book_discount;//��ǰ ������ �⺻Ű 01-23 �����߰�           --db����
	private int order_discount;  //���� ���� �� �ݾ�
	private String order_m_id;  // �Ǹ���
	private int order_cp_num; // ������ȣ
	private int order_p_num; //��ǰ��ȣ
	private int order_p_price;  //��ǰ����
	private int order_dere_num;  // �����ȣ
	private int order_dere_pay;  // ��ۺ�
	private String order_Addr;  //����ּ�
	private int order_quantity;  //����    o
	private String order_paytype;  // ������
	private int order_status;   //������Ȳ
	private int order_totalprice;  //�� �����ݾ�
	private String order_memo;  // ��� �޸�
	private Date order_paydate;  //������
	private Date order_canceldate;  //���� �����
	
}	
	
/*  db	

order_num  | order_m_id | order_cp_num | order_p_num | order_p_price |order_dere_num | order_dere_pay |order_quantity | order_paytype | drder_status | order_totalprice | order_memo | order_memo | order_paydate | order_canceldate | order_dere_start | order_dere_end 
	
	
	
	
	
	
	*/

