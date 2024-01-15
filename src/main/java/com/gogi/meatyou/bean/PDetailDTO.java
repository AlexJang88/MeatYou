package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class PDetailDTO {
	private int pd_p_num;  //상품id
	private String pd_p_desc;  //상품설명	
	private String origin; // 원산진
	private String local;  // 농장주소
	private int weight;  // 중량
	private String butchery;  // 도축장
	private int serialNum;  // 이력 번호
	private String retain; // 보관방법
	private int stock;  //재고수
	private String pd_duedate;  //유통기한
	private int pd_p_status;  // 노출상태
	private PDetailDTO pdetaildto;  // 디테일 축약
}
