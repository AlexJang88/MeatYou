package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;


@Data
public class ProductDTO {
   private int p_num;
   private String p_m_id;
   private String p_name;
   private int p_category;
   private int p_s_category;
   private String thumb;
   private int p_price;
   private int p_rcount;
   private Date p_reg_date;
   private Date startdate;
   private Date enddate;
   private int p_status;
}