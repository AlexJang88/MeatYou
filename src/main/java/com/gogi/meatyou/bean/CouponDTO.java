package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class CouponDTO {
	private int cp_num; //������ȣ
	private int cp_cus_num;//����� �ĺ���ȣ
	private int cp_price; // �����ݾ�
	private String cp_m_id; //�������̵�
	private int cp_status; //��뿩��
	private Date exdate;  // �������
	private int cp_p_num;  //���ó
	private String cp_cus_id;  //�Ǹ��� ���̵�
	private int c_type;  //����Ÿ��
	private Date usedate;  //�����
	private Date publishdate; //������
	
	private int couponUse; //��ȯ�̲���
	
}
