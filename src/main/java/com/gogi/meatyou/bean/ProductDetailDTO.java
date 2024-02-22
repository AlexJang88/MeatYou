package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class ProductDetailDTO {
   private int p_num;
   private String p_m_id;
   private String p_name;
   private int p_category;
   private int p_s_category;
   private String thumb;
   private int p_price;
   private int p_rcount;
   private Date p_reg_date;
   private String startdate;
   private String enddate;
   private int p_status;
   private int count;
   
   private int pd_p_num;
   private String pd_p_desc;  
   private String origin;
   private String local;
   private int weight;
   private String butchery;
   private int serialNum;
   private String retain;
   private int stock;
   private String pd_duedate;
   private int pd_p_status;
   private PDetailDTO pdetaildto;
   private String category1;
   private String category2;
   private String category3;
   private double star;   //별점평균
   private String ppic_m_id;
   private int ppic_p_num;
   private int reviewAllCNT;
   private int r_p_num;  //상품아이디
}