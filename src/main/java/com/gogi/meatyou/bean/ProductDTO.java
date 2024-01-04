package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDTO {
   private int p_num;  //상품아이디
   private String p_m_id;  //판매자아이디
   private String p_name; //상품명
   private int p_category; // 카테고리선택 111 삼겹살
   private int p_s_category;  // 단품세트
   private String thumb;  // 썸네일 사진
   private int p_price;  // 가격
   private int p_rcount;  // 조회수 
   private Date p_reg_date;  // 등록일
   private String startdate;  // 판매시작일
   private String enddate;  // 판매종료일
   private int p_status; // 판매상태
   
   
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