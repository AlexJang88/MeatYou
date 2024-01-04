package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class CusOrderDTO {
   private int co_num;  //코드번호
   private int co_category;  // 카테고리
   private int co_quantity; // 갯수
   private int co_pay;  // 금액
   private String co_m_id;  //판매자 아이디
   private int autoPay;  // 자동결제
   private int co_p_num; // 상품아이디
   private Date co_paydate;  //결제일
   private Date co_payenddate; //종료일
}