package com.gogi.meatyou.service;

import org.springframework.http.HttpHeaders;

import com.gogi.meatyou.bean.KaKaoPayDTO;
import com.gogi.meatyou.bean.ProductDTO;
import com.gogi.meatyou.test.KakaoApproveResponse;
import com.gogi.meatyou.test.KakaoReadyResponse;

public interface KaKaoPaymentService {
	 public KakaoReadyResponse kakaoPayReady(KaKaoPayDTO dto);
	 public KakaoApproveResponse ApproveResponse(String pgToken,KaKaoPayDTO dto);
}
