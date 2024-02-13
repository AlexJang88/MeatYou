package com.gogi.meatyou.bean;

import java.util.Date;

import lombok.Data;

@Data
public class DiseaseDTO {
	private int D_NUM;
	private String ICTSD_OCCRRNC_NO;
	private String LKNTS_NM;
	private String FARM_NM;
	private String FARM_LOCPLC_LEGALDONG_CODE;
	private String FARM_LOCPLC;
	private String OCCRRNC_DE;
	private String LVSTCKSPC_CODE;
	private String LVSTCKSPC_NM;
	private String OCCRRNC_LVSTCKCNT;
	private String DGNSS_ENGN_CODE;
	private String DGNSS_ENGN_NM;
	private String CESSATION_DE;
	
	private Date REG_DATE;
	
}
