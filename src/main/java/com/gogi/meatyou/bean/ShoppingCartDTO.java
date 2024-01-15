package com.gogi.meatyou.bean;

import java.util.List;

import lombok.Data;

@Data
public class ShoppingCartDTO {
   private int shop_num;
   private String shop_m_id;
   private int shop_p_num;
   private int quantity; 
   
   private int p_num ;
   private String p_name ;
   private String thumb;
   private int p_price ;

   private int checkit;
   private int new_quantity;
   private int salePrice;
   private int totalPrice;
   /* 상품 이미지 */
   

   
}

