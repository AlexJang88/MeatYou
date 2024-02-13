package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class DiseaseDTO {
	private int d_num;
	private String ictsd_occrrnc_no;
	private String lknts_nm;
	private String farm_nm;
	private String farm_locplc_legaldong_code;
	private String farm_locplc;
	private Date occrrnc_de;
	private String lvstckspc_code;
	private String lvstckspc_nm;
	private String occrrnc_lvstckcnt;
	private String dgnss_engn_code;
	private String dgnss_engnNm;
	private Date cessation_de;
	private Date reg_date;
}
