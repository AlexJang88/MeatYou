package com.gogi.meatyou.service;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.KaKaoPayDTO;
import com.gogi.meatyou.bean.KakaoApproveResponse;
import com.gogi.meatyou.bean.KakaoReadyResponse;
import com.gogi.meatyou.bean.MOrderDTO;
import com.gogi.meatyou.bean.MemberPayDTO;
import com.gogi.meatyou.bean.ProductDTO;

public interface KaKaoPaymentService {
	 public KakaoReadyResponse kakaoPayReady(KaKaoPayDTO dto);
	 public KakaoReadyResponse kakaoPayReadytwo(KaKaoPayDTO dto);
	 public KakaoReadyResponse kakaoPayReadymem(KaKaoPayDTO dto);
	 public KakaoApproveResponse ApproveMemResponse(String pgToken,MemberPayDTO dto);
	 public KakaoApproveResponse ApproveResponse(String pgToken,KaKaoPayDTO dto);
	 
	
}
