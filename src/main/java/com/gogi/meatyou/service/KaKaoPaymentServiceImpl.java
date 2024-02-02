package com.gogi.meatyou.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.gogi.meatyou.bean.CusOrderDTO;
import com.gogi.meatyou.bean.KaKaoPayDTO;
import com.gogi.meatyou.bean.KakaoApproveResponse;
import com.gogi.meatyou.bean.KakaoReadyResponse;
import com.gogi.meatyou.bean.ProductDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class KaKaoPaymentServiceImpl implements KaKaoPaymentService{
	

	

		 static final String cid = "TC0ONETIME"; // 가맹점 테스트 코드
		    static final String admin_Key = "9564a41a4ae8f0d5741e6b28e7905833"; // 공개 조심! 본인 애플리케이션의 어드민 키를 넣어주세요
		    private KakaoReadyResponse kakaoReady;
		    
		    
		    public KakaoReadyResponse kakaoPayReady(KaKaoPayDTO dto) {
		    	
		         // 카카오페이 요청 양식
		        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		        parameters.add("cid", cid);
		        parameters.add("partner_order_id",dto.getPartner_order_id());
		        parameters.add("partner_user_id", dto.getPartner_user_id());
		        parameters.add("item_name", dto.getItem_name());
		        parameters.add("quantity",dto.getQuantity());
		        parameters.add("total_amount", dto.getTotal_amount());
		        parameters.add("tax_free_amount", dto.getTax_free_amount());
		        parameters.add("vat_amount",dto.getVat_amount());
		        parameters.add("approval_url", "http://localhost:8080/kakaopay/powerlinkpayPro"); // 성공 시 redirect url
		        parameters.add("cancel_url", "http://localhost:8080/kakaopay/cancel"); // 취소 시 redirect url
		        parameters.add("fail_url", "http://localhost:8080/kakaopay/fail"); // 실패 시 redirect url
		        
		        
		        // 파라미터, 헤더
		        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		        
		        // 외부에 보낼 url
		        RestTemplate restTemplate = new RestTemplate();

		        kakaoReady = restTemplate.postForObject(
		                "https://kapi.kakao.com/v1/payment/ready",
		                requestEntity,
		                KakaoReadyResponse.class);
		                
		        return kakaoReady;
		    }
		    
		    /**
		     * 카카오 요구 헤더값
		     */
		    private HttpHeaders getHeaders() {
		        HttpHeaders httpHeaders = new HttpHeaders();

		        String auth = "KakaoAK " + admin_Key;

		        httpHeaders.set("Authorization", auth);
		        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		        return httpHeaders;
		    }
		    public KakaoApproveResponse ApproveResponse(String pgToken,KaKaoPayDTO dto) {
		        
		        // 카카오 요청
		        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		        parameters.add("cid", cid);
		        parameters.add("tid", kakaoReady.getTid());
		        parameters.add("partner_order_id", dto.getPartner_order_id());
		        parameters.add("partner_user_id",dto.getPartner_user_id());
		        parameters.add("pg_token", pgToken);

		        // 파라미터, 헤더
		        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		        
		        // 외부에 보낼 url
		        RestTemplate restTemplate = new RestTemplate();
		        
		        KakaoApproveResponse approveResponse = restTemplate.postForObject(
		                "https://kapi.kakao.com/v1/payment/approve",
		                requestEntity,
		                KakaoApproveResponse.class);
		                
		        return approveResponse;
		    }

			@Override
			public KakaoReadyResponse kakaoPayReadytwo(KaKaoPayDTO dto) {
				 // 카카오페이 요청 양식
		        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
		        parameters.add("cid", cid);
		        parameters.add("partner_order_id",dto.getPartner_order_id());
		        parameters.add("partner_user_id", dto.getPartner_user_id());
		        parameters.add("item_name", dto.getItem_name());
		        parameters.add("quantity",dto.getQuantity());
		        parameters.add("total_amount", dto.getTotal_amount());
		        parameters.add("tax_free_amount", dto.getTax_free_amount());
		        parameters.add("vat_amount",dto.getVat_amount());
		        parameters.add("approval_url", "http://localhost:8080/kakaopay/powerlinkpayProTwo"); // 성공 시 redirect url
		        parameters.add("cancel_url", "http://localhost:8080/kakaopay/cancel"); // 취소 시 redirect url
		        parameters.add("fail_url", "http://localhost:8080/kakaopay/fail"); // 실패 시 redirect url
		        
		        
		        // 파라미터, 헤더
		        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(parameters, this.getHeaders());
		        
		        // 외부에 보낼 url
		        RestTemplate restTemplate = new RestTemplate();

		        kakaoReady = restTemplate.postForObject(
		                "https://kapi.kakao.com/v1/payment/ready",
		                requestEntity,
		                KakaoReadyResponse.class);
		                
		        return kakaoReady;
		
			}

			
	}

