package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class PickMeDTO {
	private int pm_num;
	private String pm_m_id;
	private String pm_c_id;
	

	private int cus_num ;
	private String ceoname;
	private String company;
	private int cus_address1 ;
	private int cus_address2 ;

	private int cus_pnum;
	/* 상품 이미지 */
	
}
