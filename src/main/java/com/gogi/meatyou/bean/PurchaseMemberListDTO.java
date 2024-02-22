package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class PurchaseMemberListDTO {
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
	   
	   private int order_num;  // ��Ϲ�ȣ
		private String order_m_id;  // �Ǹ���
		private int order_cp_num; // ������ȣ
		private int order_p_num; //��ǰ��ȣ
		private int order_p_price;  //��ǰ����
		private int order_dere_num;  // �����ȣ
		private int order_dere_pay;  // ��ۺ�
		private String order_addr;  //����ּ�
		private int order_quantity;  //����
		private String order_paytype;  // ������
		private int order_status;   //������Ȳ
		private int order_discount;  //���αݾ�
		private int order_totalprice;  //�� �����ݾ�
		private String order_memo;  // ��� �޸�
		private Date order_paydate;  //������
		private Date order_canceldate;  //���� �����
		
		private Date order_dere_start;
		private Date order_dere_end;
		
		private int mstat_m_status;
		private String mstat_detail;
		private String mstat_auth;
		
		private String m_id;
	    private String passwd;
	    private String m_name;
	    private String birth;
	    private String m_addr1;
	    private String m_addr2;
	    private String email;
	    private String telep;
	    private String phone;
	    private String m_reg_date;
	    private int m_status;
}
