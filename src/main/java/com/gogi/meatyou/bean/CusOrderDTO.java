package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CusOrderDTO {
   private int co_num;  //�ڵ��ȣ
   private int co_category;  // ī�װ�
   private int co_quantity; // ����
   private int co_pay;  // �ݾ�
   private String co_m_id;  //�Ǹ��� ���̵�
   private int autoPay;  // �ڵ�����
   private int co_p_num; // ��ǰ���̵�
   private Date co_paydate;  //������
   private Date co_payenddate; //������
}