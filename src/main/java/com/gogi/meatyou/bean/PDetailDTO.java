package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PDetailDTO {
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
