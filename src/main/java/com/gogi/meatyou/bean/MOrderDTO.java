package com.gogi.meatyou.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class MOrderDTO {
	private int order_num;  // 占쏙옙球占싫� 
	private String order_id;// 占쌍뱄옙占쏙옙호 01-23 占쏙옙占쏙옙占쌩곤옙
	private int order_item_id;// vam_order_item 占썩본키 01-23 占쏙옙占쏙옙占쌩곤옙       --db占쏙옙占쏙옙
	private double book_discount;//占쏙옙품 占쏙옙占쏙옙占쏙옙 占썩본키 01-23 占쏙옙占쏙옙占쌩곤옙           --db占쏙옙占쏙옙
	private int order_discount;  //占쏙옙占쏙옙 占쏙옙占쏙옙 占쏙옙 占쌥억옙
	private String order_m_id;  // 占실몌옙占쏙옙
	private int order_cp_num; // 占쏙옙占쏙옙占쏙옙호
	private int order_p_num; //占쏙옙품占쏙옙호
	private int order_p_price;  //占쏙옙품占쏙옙占쏙옙
	private int order_dere_num;  // 占쏙옙占쏙옙占싫�
	private int order_dere_pay;  // 占쏙옙舫占�
	private String order_addr;  //占쏙옙占쏙옙玲占�
	private int order_quantity;  //占쏙옙占쏙옙    o
	private String order_paytype;  // 占쏙옙占쏙옙占쏙옙
	private int order_status;   //占쏙옙占쏙옙占쏙옙황
	private int order_totalprice;  //占쏙옙 占쏙옙占쏙옙占쌥억옙
	private String order_memo;  // 占쏙옙占� 占쌨몌옙
	private Date order_paydate;  //占쏙옙占쏙옙占쏙옙
	private Date order_canceldate;  //占쏙옙占쏙옙 占쏙옙占쏙옙占�
	private Date order_dere_start;  //占쏙옙占쏙옙 占쏙옙占쏙옙占�
	private Date ordere_end;  //占쏙옙占쏙옙 占쏙옙占쏙옙占�
	
	
	   private List<Integer> numbers;

	    // Getter and Setter for numbers
	    public List<Integer> getNumbers() {
	        return numbers;
	    }

	    public void setNumbers(List<Integer> numbers) {
	        this.numbers = numbers;
	    }
	
}	
	
/*  db	

order_num  | order_m_id | order_cp_num | order_p_num | order_p_price |order_dere_num | order_dere_pay |order_quantity | order_paytype | drder_status | order_totalprice | order_memo | order_memo | order_paydate | order_canceldate | order_dere_start | order_dere_end 
	
	
	
	
	
	
	*/

