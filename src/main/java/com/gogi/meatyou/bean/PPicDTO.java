package com.gogi.meatyou.bean;

import lombok.Data;

@Data
public class PPicDTO {
   private int ppic_num;
   private int ppic_p_num;
   private String ppic_m_id;

   
   
   private int p_num;
   private String p_name;
   private String thumb;
   private int p_price;
   private String p_reg_date;
   private String startdate;
   private String enddate;
   private int p_status;
   private String cus_address1; 
   private String cus_address2; 
   private String  cus_m_id; 
   private String  p_m_id; 
   private String  p_c_id; 
   
   private String pm_m_id;
   private String pm_c_id;
   private int pm_num;
   
}