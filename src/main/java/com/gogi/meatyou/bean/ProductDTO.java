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
   private int count;
}
