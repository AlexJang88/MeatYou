package com.gogi.meatyou.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class testServiceImpl implements testService{

	@Override
	public String getAccessToken(String authorize_code) throws Throwable {
		String access_Token = "";
		String refresh_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			
			sb.append("&client_id=fcaac1b29853acd91d3df7f95bfa316f"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&redirect_uri=http://localhost:8080/test/loginpro"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&code=" + authorize_code);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : " + result);

			// jackson objectmapper 객체 생성
			ObjectMapper objectMapper = new ObjectMapper();
			// JSON String -> Map
			Map<String, Object> jsonMap = objectMapper.readValue(result, new TypeReference<Map<String, Object>>() {
			});

			access_Token = jsonMap.get("access_token").toString();
			refresh_Token = jsonMap.get("refresh_token").toString();

			System.out.println("access_token : " + access_Token);
			System.out.println("refresh_token : " + refresh_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
	}

	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) throws Throwable {
		 HashMap<String,Object> resultMap =new HashMap<>();
         String reqURL = "https://kapi.kakao.com/v2/user/me";
          try {
              URL url = new URL(reqURL);
              HttpURLConnection conn = (HttpURLConnection) url.openConnection();
              conn.setRequestMethod("GET");

             //요청에 필요한 Header에 포함될 내용
              conn.setRequestProperty("Authorization", "Bearer " + access_Token);

              int responseCode = conn.getResponseCode();
              System.out.println("responseCode : " + responseCode);
              BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));

              String br_line = "";
              String result = "";


              while ((br_line = br.readLine()) != null) {
                  result += new String(URLDecoder.decode(br_line, "UTF-8"));
              }
             System.out.println("response:" + result);


              JsonParser parser = new JsonParser();
              JsonElement element = parser.parse(result);
              JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
              JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
              String id = element.getAsJsonObject().get("id").getAsString();
              String nickname = properties.getAsJsonObject().get("nickname").getAsString();
              String email = kakao_account.getAsJsonObject().get("email").getAsString();
              String name = kakao_account.getAsJsonObject().get("name").getAsString();
              String birthyear = kakao_account.getAsJsonObject().get("birthyear").getAsString();
              String birthday = kakao_account.getAsJsonObject().get("birthday").getAsString();
              String phone = kakao_account.getAsJsonObject().get("phone_number").getAsString();
              
              resultMap.put("nickname", nickname);
              resultMap.put("id", id);
              resultMap.put("email", email);
              resultMap.put("name", name);
              resultMap.put("birthyear", birthyear);
              resultMap.put("birthday", birthday);
              resultMap.put("phone_number", phone);
              

          } catch (IOException e) {
              e.printStackTrace();
          }
          return resultMap;
      }

	@Override
	public String getPriceinfo() {
		String reqURL = "http://www.kamis.co.kr/service/price/xml.do";
		 String result = "";
		try {
			URL url = new URL(reqURL);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("action=dailySalesList");
			
			sb.append("&p_cert_key=262ecfe0-6ae1-46c1-b97c-6988b68b30f2"); // REST_API키 본인이 발급받은 key 넣어주기
			sb.append("&p_cert_id=jaus0708@gamil.com"); // REDIRECT_URI 본인이 설정한 주소 넣어주기

			sb.append("&p_returntype=json");
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode);

			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
             String br_line = "";

             while ((br_line = br.readLine()) != null) {
                 result += new String(URLDecoder.decode(br_line, "UTF-8"));
             }
				br.close();
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String getdi() {
		String key = "1c9a14382163bb7dc822492a3dca9b9a8841b3782755afedd33d3b5879c98e94";
		String reqURL = "http://211.237.50.150:7080/openapi/"+key+"/xml/Grid_20151204000000000316_1/1/5";
		 String result = "";
		 
		 try {
//			URL url = new URL(reqURL);

//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
			//conn.setRequestProperty("Accept", "application/json");
			//conn.setRequestProperty("Content-type", "application/json");
//			conn.setRequestMethod("POST");
//			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
			DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(reqURL);
			Element root = document.getDocumentElement();
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
//			StringBuilder sb = new StringBuilder();
//			sb.append("OCCRRNC_DE=20240110");
//			
//			bw.write(sb.toString());
//			bw.flush();
//
//			// 결과 코드가 200이라면 성공
//			int responseCode = conn.getResponseCode();
//			System.out.println("responseCode : " + responseCode);
//
//			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
//			 BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
//            String br_line = "";
//
//            while ((br_line = br.readLine()) != null) {
//                result += new String(URLDecoder.decode(br_line, "UTF-8"));
//            }
//				br.close();
//				bw.close();
//				System.out.println("result ===== "+result);
			System.out.println("root====="+root.getAttribute("result"));
			result="{\"Grid_20151204000000000316_1\":{\"totalCnt\":3,\"startRow\":1,\"endRow\":5,\"result\":{\"code\":\"INFO-000\",\"message\":\"정상 처리되었습니다.\"},\"row\":[{\"ROW_NUM\":1,\"ICTSD_OCCRRNC_NO\":\"00347584\",\"LKNTS_NM\":\"결핵병\",\"FARM_NM\":\"광부 농장\",\"FARM_LOCPLC_LEGALDONG_CODE\":\"4684034025\",\"FARM_LOCPLC\":\"전라남도 무안군 현경면 현화리\",\"OCCRRNC_DE\":\"20240105\",\"LVSTCKSPC_CODE\":\"412002\",\"LVSTCKSPC_NM\":\"소-한우\",\"OCCRRNC_LVSTCKCNT\":2,\"DGNSS_ENGN_CODE\":\"6460950\",\"DGNSS_ENGN_NM\":\"전남 동물위생시험소\",\"CESSATION_DE\":\"\"},{\"ROW_NUM\":2,\"ICTSD_OCCRRNC_NO\":\"00348709\",\"LKNTS_NM\":\"결핵병\",\"FARM_NM\":\"안동민속한우 2농장\",\"FARM_LOCPLC_LEGALDONG_CODE\":\"4717033030\",\"FARM_LOCPLC\":\"경상북도 안동시 서후면 대두서리\",\"OCCRRNC_DE\":\"20240105\",\"LVSTCKSPC_CODE\":\"412002\",\"LVSTCKSPC_NM\":\"소-한우\",\"OCCRRNC_LVSTCKCNT\":1,\"DGNSS_ENGN_CODE\":\"6471193\",\"DGNSS_ENGN_NM\":\"경북 북부지소\",\"CESSATION_DE\":\"\"},{\"ROW_NUM\":3,\"ICTSD_OCCRRNC_NO\":\"00350839\",\"LKNTS_NM\":\"브루셀라병\",\"FARM_NM\":\"다인\",\"FARM_LOCPLC_LEGALDONG_CODE\":\"4783025324\",\"FARM_LOCPLC\":\"경상북도 고령군 대가야읍 장기리\",\"OCCRRNC_DE\":\"20240105\",\"LVSTCKSPC_CODE\":\"412002\",\"LVSTCKSPC_NM\":\"소-한우\",\"OCCRRNC_LVSTCKCNT\":2,\"DGNSS_ENGN_CODE\":\"6471188\",\"DGNSS_ENGN_NM\":\"경북 동물위생시험소\",\"CESSATION_DE\":\"\"}]}}";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
