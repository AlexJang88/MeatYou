package com.gogi.meatyou.service;

import java.util.HashMap;

public interface testService {
	
	public String getAccessToken(String authorize_code) throws Throwable;
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable;
	public String getPriceinfo();
	public String getdi();
}
