package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;
@Data
public class CouponDTO {
	private int cp_num; //쿠폰번호
	private int cp_cus_num;//사업자 식별번호
	private int cp_price; // 쿠폰금액
	private String cp_m_id; //유저아이디
	private int cp_status; //사용여부
	private Date exdate;  // 만료기한
	private int cp_p_num;  //사용처
	private String cp_cus_id;  //판매자 아이디
	private int c_type;  //쿠폰타입
	private Date usedate;  //사용일
	private Date publishdate; //발행일
	
	private int couponUse; //지환이꺼임
	
}
